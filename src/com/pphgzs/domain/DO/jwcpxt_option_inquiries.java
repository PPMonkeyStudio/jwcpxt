package com.pphgzs.domain.DO;

public class jwcpxt_option_inquiries {
	private String jwcpxt_option_inquiries_id;
	private String option_inquiries_describe;
	private String option_inquiries_type;
	private String option_inquiries_option;
	private String option_inquiries_gmt_create;
	private String option_inquiries_gmt_modified;

	public String getJwcpxt_option_inquiries_id() {
		return jwcpxt_option_inquiries_id;
	}

	public void setJwcpxt_option_inquiries_id(String jwcpxt_option_inquiries_id) {
		this.jwcpxt_option_inquiries_id = jwcpxt_option_inquiries_id;
	}

	public String getOption_inquiries_describe() {
		return option_inquiries_describe;
	}

	public void setOption_inquiries_describe(String option_inquiries_describe) {
		this.option_inquiries_describe = option_inquiries_describe;
	}

	public String getOption_inquiries_type() {
		return option_inquiries_type;
	}

	public void setOption_inquiries_type(String option_inquiries_type) {
		this.option_inquiries_type = option_inquiries_type;
	}

	public String getOption_inquiries_option() {
		return option_inquiries_option;
	}

	public void setOption_inquiries_option(String option_inquiries_option) {
		this.option_inquiries_option = option_inquiries_option;
	}

	public String getOption_inquiries_gmt_create() {
		return option_inquiries_gmt_create;
	}

	public void setOption_inquiries_gmt_create(String option_inquiries_gmt_create) {
		this.option_inquiries_gmt_create = option_inquiries_gmt_create;
	}

	public String getOption_inquiries_gmt_modified() {
		return option_inquiries_gmt_modified;
	}

	public void setOption_inquiries_gmt_modified(String option_inquiries_gmt_modified) {
		this.option_inquiries_gmt_modified = option_inquiries_gmt_modified;
	}

	@Override
	public String toString() {
		return "jwcpxt_option_inquiries 【\njwcpxt_option_inquiries_id=" + jwcpxt_option_inquiries_id
				+ ", \noption_inquiries_describe=" + option_inquiries_describe + ", \noption_inquiries_type="
				+ option_inquiries_type + ", \noption_inquiries_option=" + option_inquiries_option
				+ ", \noption_inquiries_gmt_create=" + option_inquiries_gmt_create
				+ ", \noption_inquiries_gmt_modified=" + option_inquiries_gmt_modified + "\n】";
	}

}
