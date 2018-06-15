package com.pphgzs.service;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_user;

public interface UserService {

	public boolean save_user(jwcpxt_user user);

	public boolean delete_user(jwcpxt_user user);

	public boolean update_user(jwcpxt_user newUser);

	public List<jwcpxt_user> list_user_all();

}
