package com.pphgzs.service;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_unit;

public interface UnitService {

	public boolean delete_unit(jwcpxt_unit unit);

	public boolean update_unit(jwcpxt_unit newUnit);

	public List<jwcpxt_unit> list_unit_all();

	public boolean save_unit(jwcpxt_unit unit);

	public jwcpxt_unit get_unit_byUnitID(String unitID);

}
