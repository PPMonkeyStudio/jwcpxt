package com.pphgzs.service;

import com.pphgzs.domain.DO.jwcpxt_unit;
import com.pphgzs.domain.DO.jwcpxt_user;

public interface LoginAndLogoutService {

	public jwcpxt_user userLogin(String account, String password);

	public jwcpxt_unit unitLogin(String account, String password);

}
