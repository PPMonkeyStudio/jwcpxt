package com.pphgzs.domain.DO;

public class jwcpxt_admin {
     private String jwcpxt_admin_id;
     private String admin_account;
     private String	admin_password;
     private String admin_gmt_create;
     private String admin_gmt_modified;
	public String getJwcpxt_admin_id() {
		return jwcpxt_admin_id;
	}
	public void setJwcpxt_admin_id(String jwcpxt_admin_id) {
		this.jwcpxt_admin_id = jwcpxt_admin_id;
	}
	public String getAdmin_account() {
		return admin_account;
	}
	public void setAdmin_account(String admin_account) {
		this.admin_account = admin_account;
	}
	public String getAdmin_password() {
		return admin_password;
	}
	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}
	public String getAdmin_gmt_create() {
		return admin_gmt_create;
	}
	public void setAdmin_gmt_create(String admin_gmt_create) {
		this.admin_gmt_create = admin_gmt_create;
	}
	public String getAdmin_gmt_modified() {
		return admin_gmt_modified;
	}
	public void setAdmin_gmt_modified(String admin_gmt_modified) {
		this.admin_gmt_modified = admin_gmt_modified;
	}
	@Override
	public String toString() {
		return "jwcpxt_admin [jwcpxt_admin_id=" + jwcpxt_admin_id + ", admin_account=" + admin_account
				+ ", admin_password=" + admin_password + ", admin_gmt_create=" + admin_gmt_create
				+ ", admin_gmt_modified=" + admin_gmt_modified + "]";
	}
     
}
