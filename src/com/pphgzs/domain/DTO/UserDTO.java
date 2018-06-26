package com.pphgzs.domain.DTO;

import com.pphgzs.domain.DO.jwcpxt_unit;
import com.pphgzs.domain.DO.jwcpxt_user;

public class UserDTO {
	private jwcpxt_user user;
	private jwcpxt_unit unit;

	@Override
	public String toString() {
		return "UnitDTO 【\nunit=" + unit + ", \nuser=" + user + "\n】";
	}

	public jwcpxt_unit getUnit() {
		return unit;
	}

	public void setUnit(jwcpxt_unit unit) {
		this.unit = unit;
	}

	public jwcpxt_user getUser() {
		return user;
	}

	public void setUser(jwcpxt_user user) {
		this.user = user;
	}
}
