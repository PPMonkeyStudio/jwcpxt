package com.pphgzs.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.pphgzs.dao.UnitDao;
import com.pphgzs.domain.DO.jwcpxt_unit;
import com.pphgzs.domain.DTO.UnitDTO;
import com.pphgzs.domain.VO.UnitVO;
import com.pphgzs.service.UnitService;
import com.pphgzs.service.UserService;
import com.pphgzs.util.MD5Util;
import com.pphgzs.util.TimeUtil;
import com.pphgzs.util.uuidUtil;

public class UnitServiceImpl implements UnitService {

	private UnitDao unitDao;

	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public UnitDao getUnitDao() {
		return unitDao;
	}

	public void setUnitDao(UnitDao unitDao) {
		this.unitDao = unitDao;
	}
	/*
	 * 
	 * 
	 */

	@Override
	public boolean save_unit(jwcpxt_unit unit) {
		// 名称不可重复
		if (unitDao.get_unit_byNameOrAccount(unit.getUnit_name(), unit.getUnit_account()) == null) {
			unit.setJwcpxt_unit_id(uuidUtil.getUuid());
			// 如果父单位查不到，说明是一级单位
			jwcpxt_unit fatherUnit = unitDao.get_unitDO_byID(unit.getUnit_father());
			if (fatherUnit == null) {
				unit.setUnit_grade(1);
			} else {
				if (fatherUnit.getUnit_grade() == 1) {
					unit.setUnit_grade(2);
				} else {
					unit.setUnit_grade(3);
				}
			}
			//
			unit.setUnit_password(MD5Util.GetMD5Code(unit.getUnit_account()));
			//
			String time = TimeUtil.getStringSecond();
			unit.setUnit_gmt_create(time);
			unit.setUnit_gmt_modified(time);
			unitDao.save_unit(unit);
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean delete_unit(jwcpxt_unit unit) {
		if (unitDao.delete_unit(unit)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean reset_unitPassword(jwcpxt_unit newUnit) {
		if (unitDao.get_unitDO_byID(newUnit.getJwcpxt_unit_id()) == null) {
			return false;
		}
		jwcpxt_unit oldUnit = unitDao.get_unitDO_byID(newUnit.getJwcpxt_unit_id());
		oldUnit.setUnit_password(MD5Util.GetMD5Code(oldUnit.getUnit_account()));

		unitDao.update_unit(oldUnit);
		return true;
	}

	@Override
	public boolean update_unitPassword(jwcpxt_unit newUnit) {
		if (unitDao.get_unitDO_byID(newUnit.getJwcpxt_unit_id()) == null) {
			return false;
		}
		jwcpxt_unit oldUnit = unitDao.get_unitDO_byID(newUnit.getJwcpxt_unit_id());
		if (newUnit.getUnit_password() == null) {
			return false;
		}
		oldUnit.setUnit_password(newUnit.getUnit_password());

		unitDao.update_unit(oldUnit);
		return true;
	}

	@Override
	public boolean update_unit(jwcpxt_unit newUnit) {
		jwcpxt_unit oldUnit = unitDao.get_unitDO_byID(newUnit.getJwcpxt_unit_id());

		if (unitDao.get_unit_byNameOrAccount(newUnit.getUnit_name(), newUnit.getUnit_account()) == null) {
			// 修改名称
			oldUnit.setUnit_name(newUnit.getUnit_name());
			// 账号
			oldUnit.setUnit_account(newUnit.getUnit_account());
		} else {
			return false;
		}

		// 手机号码
		oldUnit.setUnit_phone(newUnit.getUnit_phone());
		oldUnit.setUnit_gmt_modified(TimeUtil.getStringSecond());

		unitDao.update_unit(oldUnit);
		return true;
	}

	@Override
	public List<jwcpxt_unit> list_unit_all() {
		List<jwcpxt_unit> unitList = unitDao.list_unit_all();
		return unitList;
	}

	@Override
	public jwcpxt_unit get_unitDO_byID(String unitID) {
		return unitDao.get_unitDO_byID(unitID);
	}

	@Override
	public UnitVO get_unitVO() {
		UnitVO unitVO = new UnitVO();

		UnitDTO unitDTO;
		List<UnitDTO> unitDTOList = new ArrayList<UnitDTO>();
		List<jwcpxt_unit> unitList = unitDao.list_unit_all();

		for (jwcpxt_unit unit : unitList) {
			unitDTO = new UnitDTO();
			unitDTO.setUnit(unit);
			unitDTOList.add(unitDTO);
		}

		unitVO.setUnitDTOList(unitDTOList);
		unitVO.setTotalRecords(unitDao.get_unitTotalRecords());

		return unitVO;
	}

}
