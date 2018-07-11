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
	public Object login(String account, String password) {
		/*
		 * 查询用户
		 */
		jwcpxt_user user = loginAndLogoutDao.getUserByAccount(account);

		if (user == null) {
			/*
			 * 查询管理员
			 */
			jwcpxt_unit unit = loginAndLogoutDao.getunitByAccount(account);
			if (unit == null) {

			} else {

			}
			return null;

		}
		// 验证用户密码
		else if (user.getUser_password().equals(password)) {
			return user;
		}
		// 用户密码不对
		else {
			return null;
		}

	}

}
