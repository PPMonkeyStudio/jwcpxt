package com.pphgzs.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.pphgzs.dao.UnitDao;
import com.pphgzs.domain.DO.jwcpxt_unit;
import com.pphgzs.domain.DTO.UnitDTO;
import com.pphgzs.domain.VO.UnitVO;
import com.pphgzs.service.UnitService;
import com.pphgzs.service.UserService;
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

		if (unitDao.get_unit_byUnitID(unit.getJwcpxt_unit_id()) == null) {
			unit.setJwcpxt_unit_id(uuidUtil.getUuid());
			unit.setUnit_reorganizer("none");
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
	public boolean update_unit(jwcpxt_unit newUnit) {
		System.out.println(newUnit);
		jwcpxt_unit oldUnit = unitDao.get_unit_byUnitID(newUnit.getJwcpxt_unit_id());
		oldUnit.setUnit_name(newUnit.getUnit_name());
		oldUnit.setUnit_reorganizer(newUnit.getUnit_reorganizer());
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
	public jwcpxt_unit get_unit_byUnitID(String unitID) {
		return unitDao.get_unit_byUnitID(unitID);
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
			unitDTO.setUser(userService.get_user_byUserID(unit.getUnit_reorganizer()));
			unitDTOList.add(unitDTO);
		}

		unitVO.setUnitDTOList(unitDTOList);
		unitVO.setTotalRecords(unitDao.get_unitTotalRecords());

		return unitVO;
	}

}
