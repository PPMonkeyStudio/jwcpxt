package com.pphgzs.domain.DO;

public class jwcpxt_service_grab {
	private String jwcpxt_service_grab_id;
	private String service_grab_service_definition;
	private String service_grab_table = "none";
	private String service_grab_field_nid = "none";
	private String service_grab_field_client_name = "none";
	private String service_grab_field_client_sex = "none";
	private String service_grab_field_client_phone = "none";
	private String service_grab_field_date = "none";
	private String service_grab_gmt_create;
	private String service_grab_gmt_modified;

	@Override
	public String toString() {
		return "jwcpxt_service_grab 【\njwcpxt_service_grab_id=" + jwcpxt_service_grab_id
				+ ", \nservice_grab_service_definition=" + service_grab_service_definition + ", \nservice_grab_table="
				+ service_grab_table + ", \nservice_grab_field_nid=" + service_grab_field_nid
				+ ", \nservice_grab_field_client_name=" + service_grab_field_client_name
				+ ", \nservice_grab_field_client_sex=" + service_grab_field_client_sex
				+ ", \nservice_grab_field_client_phone=" + service_grab_field_client_phone
				+ ", \nservice_grab_field_date=" + service_grab_field_date + ", \nservice_grab_gmt_create="
				+ service_grab_gmt_create + ", \nservice_grab_gmt_modified=" + service_grab_gmt_modified + "\n】";
	}

	public String getJwcpxt_service_grab_id() {
		return jwcpxt_service_grab_id;
	}

	public void setJwcpxt_service_grab_id(String jwcpxt_service_grab_id) {
		this.jwcpxt_service_grab_id = jwcpxt_service_grab_id;
	}

	public String getService_grab_service_definition() {
		return service_grab_service_definition;
	}

	public void setService_grab_service_definition(String service_grab_service_definition) {
		this.service_grab_service_definition = service_grab_service_definition;
	}

	public String getService_grab_table() {
		return service_grab_table;
	}

	public void setService_grab_table(String service_grab_table) {
		this.service_grab_table = service_grab_table;
	}

	public String getService_grab_field_nid() {
		return service_grab_field_nid;
	}

	public void setService_grab_field_nid(String service_grab_field_nid) {
		this.service_grab_field_nid = service_grab_field_nid;
	}

	public String getService_grab_field_client_name() {
		return service_grab_field_client_name;
	}

	public void setService_grab_field_client_name(String service_grab_field_client_name) {
		this.service_grab_field_client_name = service_grab_field_client_name;
	}

	public String getService_grab_field_client_sex() {
		return service_grab_field_client_sex;
	}

	public void setService_grab_field_client_sex(String service_grab_field_client_sex) {
		this.service_grab_field_client_sex = service_grab_field_client_sex;
	}

	public String getService_grab_field_client_phone() {
		return service_grab_field_client_phone;
	}

	public void setService_grab_field_client_phone(String service_grab_field_client_phone) {
		this.service_grab_field_client_phone = service_grab_field_client_phone;
	}

	public String getService_grab_field_date() {
		return service_grab_field_date;
	}

	public void setService_grab_field_date(String service_grab_field_date) {
		this.service_grab_field_date = service_grab_field_date;
	}

	public String getService_grab_gmt_create() {
		return service_grab_gmt_create;
	}

	public void setService_grab_gmt_create(String service_grab_gmt_create) {
		this.service_grab_gmt_create = service_grab_gmt_create;
	}

	public String getService_grab_gmt_modified() {
		return service_grab_gmt_modified;
	}

	public void setService_grab_gmt_modified(String service_grab_gmt_modified) {
		this.service_grab_gmt_modified = service_grab_gmt_modified;
	}

}
