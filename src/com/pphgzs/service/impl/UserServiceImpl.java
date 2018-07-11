package com.pphgzs.service.impl;

import com.pphgzs.dao.UserDao;
import com.pphgzs.service.UnitService;
import com.pphgzs.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao userDao;
	private UnitService unitService;

	public UnitService getUnitService() {
		return unitService;
	}

	public void setUnitService(UnitService unitService) {
		this.unitService = unitService;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/*
	 * 
	 * 
	 */

}
