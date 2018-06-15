package com.pphgzs.dao;

import com.pphgzs.domain.DO.jwcpxt_admin;
import com.pphgzs.domain.DO.jwcpxt_user;

public interface LoginAndLogoutDao {
	/**
	 * 
	 * @param account
	 * @return
	 */
	public jwcpxt_user getUserByAccount(String account);

	public jwcpxt_admin getAdminByAccount(String account);
	/*
	 * 
	 */
}
