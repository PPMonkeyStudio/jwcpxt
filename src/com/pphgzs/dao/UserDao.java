package com.pphgzs.dao;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_entry_exit;
import com.pphgzs.domain.DO.jwcpxt_user;

public interface UserDao {

	public List<jwcpxt_user> list_user_all();

	public boolean save_user(jwcpxt_user user);

	public boolean update_user(jwcpxt_user user);

	public jwcpxt_user get_userDO_byUserID(String userID);

	public jwcpxt_user get_user_byAccount(String user_account);

	public jwcpxt_user get_userDO_byRandomAndTypeCP();

	public String getUnitCode_ByUnitName(String stringCellValue);

	public boolean saveObject(jwcpxt_entry_exit entry_exit);

}
