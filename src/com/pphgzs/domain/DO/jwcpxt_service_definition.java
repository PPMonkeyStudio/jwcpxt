package com.pphgzs.domain.DO;

public class jwcpxt_service_definition {
	private String jwcpxt_service_definition_id;
	private String service_definition_describe;
	private String service_definition_unit;
	private int service_definition_sampling_proportion = 0;
	private String service_definition_gmt_create;
	private String service_definition_gmt_modified;

	public int getService_definition_sampling_proportion() {
		return service_definition_sampling_proportion;
	}

	public void setService_definition_sampling_proportion(int service_definition_sampling_proportion) {
		this.service_definition_sampling_proportion = service_definition_sampling_proportion;
	}

	public String getJwcpxt_service_definition_id() {
		return jwcpxt_service_definition_id;
	}

	public void setJwcpxt_service_definition_id(String jwcpxt_service_definition_id) {
		this.jwcpxt_service_definition_id = jwcpxt_service_definition_id;
	}

	public String getService_definition_describe() {
		return service_definition_describe;
	}

	public void setService_definition_describe(String service_definition_describe) {
		this.service_definition_describe = service_definition_describe;
	}

	public String getService_definition_unit() {
		return service_definition_unit;
	}

	public void setService_definition_unit(String service_definition_unit) {
		this.service_definition_unit = service_definition_unit;
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
		return "jwcpxt_service_definition 【\njwcpxt_service_definition_id=" + jwcpxt_service_definition_id
				+ ", \nservice_definition_describe=" + service_definition_describe + ", \nservice_definition_unit="
				+ service_definition_unit + ", \nservice_definition_gmt_create=" + service_definition_gmt_create
				+ ", \nservice_definition_gmt_modified=" + service_definition_gmt_modified + "\n】";
	}

}
