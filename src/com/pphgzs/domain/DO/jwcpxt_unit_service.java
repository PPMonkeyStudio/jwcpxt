package com.pphgzs.domain.DO;

public class jwcpxt_unit_service {
	private String jwcpxt_unit_service_id;
	private String unit_id;
	private String service_definition_id;
	private int evaluation_count = 0;
	private String unit_service_gmt_modified;
	private String unit_service_gmt_create;

	public String getJwcpxt_unit_service_id() {
		return jwcpxt_unit_service_id;
	}

	public void setJwcpxt_unit_service_id(String jwcpxt_unit_service_id) {
		this.jwcpxt_unit_service_id = jwcpxt_unit_service_id;
	}

	public String getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(String unit_id) {
		this.unit_id = unit_id;
	}

	public String getService_definition_id() {
		return service_definition_id;
	}

	public void setService_definition_id(String service_definition_id) {
		this.service_definition_id = service_definition_id;
	}

	public int getEvaluation_count() {
		return evaluation_count;
	}

	public void setEvaluation_count(int evaluation_count) {
		this.evaluation_count = evaluation_count;
	}

	public String getUnit_service_gmt_modified() {
		return unit_service_gmt_modified;
	}

	public void setUnit_service_gmt_modified(String unit_service_gmt_modified) {
		this.unit_service_gmt_modified = unit_service_gmt_modified;
	}

	public String getUnit_service_gmt_create() {
		return unit_service_gmt_create;
	}

	public void setUnit_service_gmt_create(String unit_service_gmt_create) {
		this.unit_service_gmt_create = unit_service_gmt_create;
	}

	@Override
	public String toString() {
		return "jwcpxt_unit_service [jwcpxt_unit_service_id=" + jwcpxt_unit_service_id + ", unit_id=" + unit_id
				+ ", service_definition_id=" + service_definition_id + ", evaluation_count=" + evaluation_count
				+ ", unit_service_gmt_modified=" + unit_service_gmt_modified + ", unit_service_gmt_create="
				+ unit_service_gmt_create + "]";
	}

}
