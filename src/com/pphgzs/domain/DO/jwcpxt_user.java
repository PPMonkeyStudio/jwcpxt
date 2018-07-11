package com.pphgzs.domain.DO;

public class jwcpxt_user {
	private String jwcpxt_user_id;
	private String user_account;
	private String user_password;
	private String user_name;
	private int user_type = 1;
	private int user_state = 1;
	private String user_gmt_create;
	private String user_gmt_modified;

	@Override
	public String toString() {
		return "jwcpxt_user 【\njwcpxt_user_id=" + jwcpxt_user_id + ", \nuser_account=" + user_account
				+ ", \nuser_password=" + user_password + ", \nuser_name=" + user_name + ", \nuser_type=" + user_type
				+ ", \nuser_state=" + user_state + ", \nuser_gmt_create=" + user_gmt_create + ", \nuser_gmt_modified="
				+ user_gmt_modified + "\n】";
	}

	public String getJwcpxt_user_id() {
		return jwcpxt_user_id;
	}

	public void setJwcpxt_user_id(String jwcpxt_user_id) {
		this.jwcpxt_user_id = jwcpxt_user_id;
	}

	public String getUser_account() {
		return user_account;
	}

	public void setUser_account(String user_account) {
		this.user_account = user_account;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getUser_type() {
		return user_type;
	}

	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}

	public int getUser_state() {
		return user_state;
	}

	public void setUser_state(int user_state) {
		this.user_state = user_state;
	}

	public String getUser_gmt_create() {
		return user_gmt_create;
	}

	public void setUser_gmt_create(String user_gmt_create) {
		this.user_gmt_create = user_gmt_create;
	}

	public String getUser_gmt_modified() {
		return user_gmt_modified;
	}

	public void setUser_gmt_modified(String user_gmt_modified) {
		this.user_gmt_modified = user_gmt_modified;
	}

}
