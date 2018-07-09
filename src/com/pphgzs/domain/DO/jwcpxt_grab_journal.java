package com.pphgzs.domain.DO;

public class jwcpxt_grab_journal {
	private String jwcpxt_grab_journal_id;
	private String grab_journal_date = "";
	private String grab_journal_if_grab = "2";
	private String grab_journal_service_definition = "";
	private String grab_journal_time = "";
	private String grab_journal_gmt_create;
	private String grab_journal_gmt_modified;

	@Override
	public String toString() {
		return "jwcpxt_grab_journal 【\njwcpxt_grab_journal_id=" + jwcpxt_grab_journal_id + ", \ngrab_journal_date="
				+ grab_journal_date + ", \ngrab_journal_if_grab=" + grab_journal_if_grab
				+ ", \ngrab_journal_service_definition=" + grab_journal_service_definition + ", \ngrab_journal_time="
				+ grab_journal_time + ", \ngrab_journal_gmt_create=" + grab_journal_gmt_create
				+ ", \ngrab_journal_gmt_modified=" + grab_journal_gmt_modified + "\n】";
	}

	public String getJwcpxt_grab_journal_id() {
		return jwcpxt_grab_journal_id;
	}

	public void setJwcpxt_grab_journal_id(String jwcpxt_grab_journal_id) {
		this.jwcpxt_grab_journal_id = jwcpxt_grab_journal_id;
	}

	public String getGrab_journal_date() {
		return grab_journal_date;
	}

	public void setGrab_journal_date(String grab_journal_date) {
		this.grab_journal_date = grab_journal_date;
	}

	public String getGrab_journal_if_grab() {
		return grab_journal_if_grab;
	}

	public void setGrab_journal_if_grab(String grab_journal_if_grab) {
		this.grab_journal_if_grab = grab_journal_if_grab;
	}

	public String getGrab_journal_service_definition() {
		return grab_journal_service_definition;
	}

	public void setGrab_journal_service_definition(String grab_journal_service_definition) {
		this.grab_journal_service_definition = grab_journal_service_definition;
	}

	public String getGrab_journal_time() {
		return grab_journal_time;
	}

	public void setGrab_journal_time(String grab_journal_time) {
		this.grab_journal_time = grab_journal_time;
	}

	public String getGrab_journal_gmt_create() {
		return grab_journal_gmt_create;
	}

	public void setGrab_journal_gmt_create(String grab_journal_gmt_create) {
		this.grab_journal_gmt_create = grab_journal_gmt_create;
	}

	public String getGrab_journal_gmt_modified() {
		return grab_journal_gmt_modified;
	}

	public void setGrab_journal_gmt_modified(String grab_journal_gmt_modified) {
		this.grab_journal_gmt_modified = grab_journal_gmt_modified;
	}

}
