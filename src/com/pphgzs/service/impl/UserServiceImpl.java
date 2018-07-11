package com.pphgzs.service.impl;

import com.pphgzs.dao.UserDao;
import com.pphgzs.domain.DO.jwcpxt_user;
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
	@Override
	public jwcpxt_user get_userDO_byUserID(String userID) {
		return userDao.get_userDO_byUserID(userID);
	}
}
