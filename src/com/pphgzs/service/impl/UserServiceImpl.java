package com.pphgzs.service.impl;

import java.util.List;

import com.pphgzs.dao.UserDao;
import com.pphgzs.domain.DO.jwcpxt_user;
import com.pphgzs.domain.VO.UserVO;
import com.pphgzs.service.UnitService;
import com.pphgzs.service.UserService;
import com.pphgzs.util.MD5Util;
import com.pphgzs.util.TimeUtil;
import com.pphgzs.util.uuidUtil;

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

	@Override
	public boolean save_user(jwcpxt_user user) {
		// 名称不可重复
		if (userDao.get_user_byAccount(user.getUser_account()) == null) {
			user.setJwcpxt_user_id(uuidUtil.getUuid());
			//
			user.setUser_password(MD5Util.GetMD5Code(user.getUser_account()));
			//

			//
			String time = TimeUtil.getStringSecond();
			user.setUser_gmt_create(time);
			user.setUser_gmt_modified(time);
			userDao.save_user(user);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public UserVO get_userVO() {
		UserVO userVO = new UserVO();
		List<jwcpxt_user> userList = userDao.list_user_all();
		userVO.setUserList(userList);

		return userVO;
	}

	@Override
	public boolean reset_userPassword_byUserID(String userID) {
		jwcpxt_user user = userDao.get_userDO_byUserID(userID);

		if (user == null) {
			return false;
		}

		user.setUser_password(MD5Util.GetMD5Code(user.getUser_account()));
		userDao.update_user(user);
		return true;
	}

	@Override
	public boolean update_userPassword(jwcpxt_user newUser) {
		jwcpxt_user oldUser = userDao.get_userDO_byUserID(newUser.getJwcpxt_user_id());
		if (oldUser == null) {
			return false;
		}

		if (newUser.getUser_password() == null) {
			return false;
		}
		oldUser.setUser_password(MD5Util.GetMD5Code(newUser.getUser_password()));

		userDao.update_user(oldUser);
		return true;
	}

	@Override
	public boolean ban_user_byUserID(String userID) {
		jwcpxt_user user = userDao.get_userDO_byUserID(userID);

		if (user == null) {
			return false;
		}
		if (user.getUser_state() == 1) {
			user.setUser_state(2);
		} else {
			user.setUser_state(1);
		}

		userDao.update_user(user);
		return true;
	}

}
