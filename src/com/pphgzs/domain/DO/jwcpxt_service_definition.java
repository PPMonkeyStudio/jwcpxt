package com.pphgzs.domain.DO;

public class jwcpxt_service_definition {
	private String jwcpxt_service_definition_id;
	private String service_definition_describe;
	private String service_definition_gmt_create;
	private String service_definition_gmt_modified;

	public String getJwcpxt_service_definition_id() {
		return jwcpxt_service_definition_id;
	}

	public void setJwcpxt_service_definition_id(String jwcpxt_service_definition_id) {
		this.jwcpxt_service_definition_id = jwcpxt_service_definition_id;
	}

	public String getService_definition_describe() {
		return service_definition_describe;
	}

	public String getService_definition_gmt_create() {
		return service_definition_gmt_create;
	}

	public void setService_definition_gmt_create(String service_definition_gmt_create) {
		this.service_definition_gmt_create = service_definition_gmt_create;
	}

	public String getService_definition_gmt_modified() {
		return service_definition_gmt_modified;
	}

	public void setService_definition_gmt_modified(String service_definition_gmt_modified) {
		this.service_definition_gmt_modified = service_definition_gmt_modified;
	}

	@Override
	public String toString() {
		return "jwcpxt_service_definition [jwcpxt_service_definition_id=" + jwcpxt_service_definition_id
				+ ", service_definition_describe=" + service_definition_describe + ", service_definition_gmt_create="
				+ service_definition_gmt_create + ", service_definition_gmt_modified=" + service_definition_gmt_modified
				+ "]";
	}

}
