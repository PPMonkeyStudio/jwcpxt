package com.pphgzs.domain.VO;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_user;

public class UserVO {

	private List<jwcpxt_user> userList;

	/*
	 * 
	 */

	@Override
	public String toString() {
		return "UserVO 【\nuserList=" + userList + "\n】";
	}

	public List<jwcpxt_user> getUserList() {
		return userList;
	}

	public void setUserList(List<jwcpxt_user> userList) {
		this.userList = userList;
	}

}
