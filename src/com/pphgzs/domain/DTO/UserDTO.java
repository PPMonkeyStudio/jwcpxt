package com.pphgzs.domain.DTO;

import com.pphgzs.domain.DO.jwcpxt_user;

public class UserDTO {
	private jwcpxt_user user;

	@Override
	public String toString() {
		return "UserDTO 【\nuser=" + user + "\n】";
	}

	public jwcpxt_user getUser() {
		return user;
	}

	public void setUser(jwcpxt_user user) {
		this.user = user;
	}
}
