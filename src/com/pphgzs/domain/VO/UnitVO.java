package com.pphgzs.domain.VO;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_unit;

public class UnitVO {

	private List<jwcpxt_unit> unitList;

	@Override
	public String toString() {
		return "UnitVO 【\nunitList=" + unitList + "\n】";
	}

	public List<jwcpxt_unit> getUnitList() {
		return unitList;
	}

	public void setUnitList(List<jwcpxt_unit> unitList) {
		this.unitList = unitList;
	}

}
