package com.pphgzs.domain.DO;

public class jwcpxt_unit {
	private String jwcpxt_unit_id;
	private String unit_name;
	private String unit_father = "none";
	private String unit_num = "none";// 机构代码，只有三级机构需要
	private int unit_grade;// 等级，方便查询
	private String unit_account;
	private String unit_password;
	private String unit_phone;
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

	public String getUnit_gmt_create() {
		return unit_gmt_create;
	}

	public String getUnit_father() {
		return unit_father;
	}

	public void setUnit_father(String unit_father) {
		this.unit_father = unit_father;
	}

	public String getUnit_num() {
		return unit_num;
	}

	public void setUnit_num(String unit_num) {
		this.unit_num = unit_num;
	}

	public int getUnit_grade() {
		return unit_grade;
	}

	public void setUnit_grade(int unit_grade) {
		this.unit_grade = unit_grade;
	}

	public String getUnit_account() {
		return unit_account;
	}

	public void setUnit_account(String unit_account) {
		this.unit_account = unit_account;
	}

	public String getUnit_password() {
		return unit_password;
	}

	public void setUnit_password(String unit_password) {
		this.unit_password = unit_password;
	}

	public String getUnit_phone() {
		return unit_phone;
	}

	public void setUnit_phone(String unit_phone) {
		this.unit_phone = unit_phone;
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
		return "jwcpxt_unit [jwcpxt_unit_id=" + jwcpxt_unit_id + ", unit_name=" + unit_name + ", unit_father="
				+ unit_father + ", unit_num=" + unit_num + ", unit_grade=" + unit_grade + ", unit_account="
				+ unit_account + ", unit_password=" + unit_password + ", unit_phone=" + unit_phone
				+ ", unit_gmt_create=" + unit_gmt_create + ", unit_gmt_modified=" + unit_gmt_modified + "]";
	}

}
