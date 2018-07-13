package com.pphgzs.service;

import com.pphgzs.domain.DO.jwcpxt_user;
import com.pphgzs.domain.VO.UserVO;

public interface UserService {

	jwcpxt_user get_userDO_byUserID(String userID);

	UserVO get_userVO();

	boolean save_user(jwcpxt_user user);

	boolean reset_userPassword_byUserID(String userID);

	boolean ban_user_byUserID(String userID);

	boolean update_userPassword(jwcpxt_user user);

	jwcpxt_user get_userDO_byRandomAndTypeCP();

}
