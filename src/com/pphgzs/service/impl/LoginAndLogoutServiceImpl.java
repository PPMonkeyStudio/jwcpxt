package com.pphgzs.service.impl;

import com.pphgzs.dao.LoginAndLogoutDao;
import com.pphgzs.domain.DO.jwcpxt_unit;
import com.pphgzs.domain.DO.jwcpxt_user;
import com.pphgzs.service.LoginAndLogoutService;

public class LoginAndLogoutServiceImpl implements LoginAndLogoutService {
	private LoginAndLogoutDao loginAndLogoutDao;

	public LoginAndLogoutDao getLoginAndLogoutDao() {
		return loginAndLogoutDao;
	}

	public void setLoginAndLogoutDao(LoginAndLogoutDao loginAndLogoutDao) {
		this.loginAndLogoutDao = loginAndLogoutDao;
	}

	@Override
	public jwcpxt_unit unitLogin(String account, String password) {
		jwcpxt_unit unit = loginAndLogoutDao.getunitByAccount(account);
		if (unit == null) {
			return null;
		} else if (unit.getUnit_password().equals(password)) {
			return unit;
		} else {
			return null;
		}
	}

	@Override
	public jwcpxt_user userLogin(String account, String password) {
		jwcpxt_user user = loginAndLogoutDao.getUserByAccount(account);

		if (user == null) {
			return null;
		} else if (user.getUser_password().equals(password)) {
			return user;
		} else {
			return null;
		}
	}

}
