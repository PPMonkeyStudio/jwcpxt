package com.pphgzs.domain.DO;

public class jwcpxt_service_instance {
private String jwcpxt_service_instance_id;
private String service_instance_service_definition;
private String service_instance_nid;
private String service_instance_date;
private String service_instance_distribution;
private String service_instance_gmt_create;
private String service_instance_gmt_modified;
public String getJwcpxt_service_instance_id() {
	return jwcpxt_service_instance_id;
}
public void setJwcpxt_service_instance_id(String jwcpxt_service_instance_id) {
	this.jwcpxt_service_instance_id = jwcpxt_service_instance_id;
}
public String getService_instance_service_definition() {
	return service_instance_service_definition;
}
public void setService_instance_service_definition(String service_instance_service_definition) {
	this.service_instance_service_definition = service_instance_service_definition;
}
public String getService_instance_nid() {
	return service_instance_nid;
}
public void setService_instance_nid(String service_instance_nid) {
	this.service_instance_nid = service_instance_nid;
}
public String getService_instance_date() {
	return service_instance_date;
}
public void setService_instance_date(String service_instance_date) {
	this.service_instance_date = service_instance_date;
}
public String getService_instance_distribution() {
	return service_instance_distribution;
}
public void setService_instance_distribution(String service_instance_distribution) {
	this.service_instance_distribution = service_instance_distribution;
}
public String getService_instance_gmt_create() {
	return service_instance_gmt_create;
}
public void setService_instance_gmt_create(String service_instance_gmt_create) {
	this.service_instance_gmt_create = service_instance_gmt_create;
}
public String getService_instance_gmt_modified() {
	return service_instance_gmt_modified;
}
public void setService_instance_gmt_modified(String service_instance_gmt_modified) {
	this.service_instance_gmt_modified = service_instance_gmt_modified;
}
@Override
public String toString() {
	return "jwcpxt_service_instance [jwcpxt_service_instance_id=" + jwcpxt_service_instance_id
			+ ", service_instance_service_definition=" + service_instance_service_definition + ", service_instance_nid="
			+ service_instance_nid + ", service_instance_date=" + service_instance_date
			+ ", service_instance_distribution=" + service_instance_distribution + ", service_instance_gmt_create="
			+ service_instance_gmt_create + ", service_instance_gmt_modified=" + service_instance_gmt_modified + "]";
}

}
