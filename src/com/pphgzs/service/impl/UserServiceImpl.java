package com.pphgzs.service.impl;

import java.util.List;

import com.pphgzs.dao.UserDao;
import com.pphgzs.domain.DO.jwcpxt_user;
import com.pphgzs.domain.VO.UserVO;
import com.pphgzs.service.UserService;
import com.pphgzs.util.TimeUtil;
import com.pphgzs.util.uuidUtil;

public class UserServiceImpl implements UserService {

	private UserDao userDao;

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
	public boolean save_user(jwcpxt_user user) {
		if (userDao.ifExist_user_byUserAccount(user.getUser_account())) {
			return false;
		} else {
			user.setJwcpxt_user_id(uuidUtil.getUuid());
			// 账号作为密码
			user.setUser_password(user.getUser_account());
			// 时间初始化
			String time = TimeUtil.getStringSecond();
			user.setUser_gmt_create(time);
			user.setUser_gmt_modified(time);
			// 存入
			userDao.save_user(user);
			return true;
		}
	}

	@Override
	public boolean delete_user(jwcpxt_user user) {

		if (userDao.delete_user(user)) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean update_user(jwcpxt_user newUser) {
		jwcpxt_user oldUser = userDao.get_user_byUserID(newUser.getJwcpxt_user_id());
		oldUser.setUser_name(newUser.getUser_name());
		oldUser.setUser_Jurisdiction_evaluate(newUser.getUser_Jurisdiction_evaluate());
		oldUser.setUser_Jurisdiction_statistics(newUser.getUser_Jurisdiction_statistics());
		oldUser.setUser_Jurisdiction_review(newUser.getUser_Jurisdiction_review());
		oldUser.setUser_gmt_modified(TimeUtil.getStringSecond());
		if (userDao.update_user(oldUser)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<jwcpxt_user> list_user_all() {
		return userDao.list_user_all();
	}

	@Override
	public jwcpxt_user get_user_byUserID(String userID) {
		return userDao.get_user_byUserID(userID);
	}

	@Override
	public UserVO get_userVO() {
		UserVO userVO = new UserVO();
		userVO.setUser_List(userDao.list_user_all());
		userVO.setTotalRecords(userDao.get_userTotalRecords_all());
		return userVO;
	}

}
