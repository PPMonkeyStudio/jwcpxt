package com.pphgzs.domain.DO;

public class jwcpxt_unit {
private String jwcpxt_unit_id;
private String unit_name;
private String unit_reorganizer;
private String unit_gmt_create;
private String unit_gmt_modified;
public String getJwcpxt_unit_id() {
	return jwcpxt_unit_id;
}
public void setJwcpxt_unit_id(String jwcpxt_unit_id) {
	this.jwcpxt_unit_id = jwcpxt_unit_id;
}
public String getUnit_name() {
	return unit_name;
}
public void setUnit_name(String unit_name) {
	this.unit_name = unit_name;
}
public String getUnit_reorganizer() {
	return unit_reorganizer;
}
public void setUnit_reorganizer(String unit_reorganizer) {
	this.unit_reorganizer = unit_reorganizer;
}
public String getUnit_gmt_create() {
	return unit_gmt_create;
}
public void setUnit_gmt_create(String unit_gmt_create) {
	this.unit_gmt_create = unit_gmt_create;
}
public String getUnit_gmt_modified() {
	return unit_gmt_modified;
}
public void setUnit_gmt_modified(String unit_gmt_modified) {
	this.unit_gmt_modified = unit_gmt_modified;
}
@Override
public String toString() {
	return "jwcpxt_unit [jwcpxt_unit_id=" + jwcpxt_unit_id + ", unit_name=" + unit_name + ", unit_reorganizer="
			+ unit_reorganizer + ", unit_gmt_create=" + unit_gmt_create + ", unit_gmt_modified=" + unit_gmt_modified
			+ "]";
}

}
