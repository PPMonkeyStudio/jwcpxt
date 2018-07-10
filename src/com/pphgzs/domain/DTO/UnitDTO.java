package com.pphgzs.domain.DTO;

import com.pphgzs.domain.DO.jwcpxt_unit;

public class UnitDTO {
	private jwcpxt_unit unit;

	@Override
	public String toString() {
		return "UnitDTO 【\nunit=" + unit + "\n】";
	}

	public jwcpxt_unit getUnit() {
		return unit;
	}

	public void setUnit(jwcpxt_unit unit) {
		this.unit = unit;
	}

}
