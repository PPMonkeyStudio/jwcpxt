package com.pphgzs.service.impl;

import java.util.List;

import com.pphgzs.dao.UnitDao;
import com.pphgzs.dao.UserDao;
import com.pphgzs.domain.DO.jwcpxt_unit;
import com.pphgzs.service.UnitService;
import com.pphgzs.util.TimeUtil;
import com.pphgzs.util.uuidUtil;

public class UnitServiceImpl implements UnitService {

	private UnitDao unitDao;
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
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

}
