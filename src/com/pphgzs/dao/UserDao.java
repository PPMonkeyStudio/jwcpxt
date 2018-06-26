package com.pphgzs.dao;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_user;
import com.pphgzs.domain.VO.UserVO;

public interface UserDao {

	public boolean delete_user(jwcpxt_user user);

	public jwcpxt_user get_user_byUserID(String userID);

	public int get_userTotalRecords_all();

	public boolean ifExist_user_byUserAccount(String account);

	public List<jwcpxt_user> list_user_all();

	public List<jwcpxt_user> list_user_byUserVO(UserVO userVO);

	public boolean save_user(jwcpxt_user user);

	public boolean update_user(jwcpxt_user user);
}
