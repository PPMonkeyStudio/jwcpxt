package com.pphgzs.dao;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_user;

public interface UserDao {

	public List<jwcpxt_user> list_user_all();

	public boolean save_user(jwcpxt_user user);

	public boolean update_user(jwcpxt_user user);

}
