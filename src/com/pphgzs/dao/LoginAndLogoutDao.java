package com.pphgzs.dao;

import com.pphgzs.domain.DO.jwcpxt_unit;
import com.pphgzs.domain.DO.jwcpxt_user;

public interface LoginAndLogoutDao {

	public jwcpxt_user getUserByAccount(String account);

	public jwcpxt_unit getunitByAccount(String account);

}
