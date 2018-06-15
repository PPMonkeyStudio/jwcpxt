package com.pphgzs.domain.DO;

public class jwcpxt_service_distribution {
	private String jwcpxt_service_distribution_id;
	private String service_distribution_judge;
	private String service_distribution_service_instance;
	private String service_distribution_gmt_create;
	private String service_distribution_gmt_modified;

	public String getJwcpxt_service_distribution_id() {
		return jwcpxt_service_distribution_id;
	}

	public void setJwcpxt_service_distribution_id(String jwcpxt_service_distribution_id) {
		this.jwcpxt_service_distribution_id = jwcpxt_service_distribution_id;
	}

	public String getService_distribution_judge() {
		return service_distribution_judge;
	}

	public void setService_distribution_judge(String service_distribution_judge) {
		this.service_distribution_judge = service_distribution_judge;
	}

	public String getService_distribution_service_instance() {
		return service_distribution_service_instance;
	}

	public void setService_distribution_service_instance(String service_distribution_service_instance) {
		this.service_distribution_service_instance = service_distribution_service_instance;
	}

	public String getService_distribution_gmt_create() {
		return service_distribution_gmt_create;
	}

	public void setService_distribution_gmt_create(String service_distribution_gmt_create) {
		this.service_distribution_gmt_create = service_distribution_gmt_create;
	}

	public String getService_distribution_gmt_modified() {
		return service_distribution_gmt_modified;
	}

	public void setService_distribution_gmt_modified(String service_distribution_gmt_modified) {
		this.service_distribution_gmt_modified = service_distribution_gmt_modified;
	}

	@Override
	public String toString() {
		return "jwcpxt_service_distribution 【\njwcpxt_service_distribution_id=" + jwcpxt_service_distribution_id
				+ ", \nservice_distribution_judge=" + service_distribution_judge
				+ ", \nservice_distribution_service_instance=" + service_distribution_service_instance
				+ ", \nservice_distribution_gmt_create=" + service_distribution_gmt_create
				+ ", \nservice_distribution_gmt_modified=" + service_distribution_gmt_modified + "\n】";
	}

}
