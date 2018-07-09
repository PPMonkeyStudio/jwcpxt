package com.pphgzs.domain.DO;

public class jwcpxt_option {
	private String jwcpxt_option_id;
	private String option_describe;
	private String option_question;
	private int option_sort;
	private double option_grade;
	private String option_push;
	private String option_gmt_create;
	private String option_gmt_modified;

	public String getJwcpxt_option_id() {
		return jwcpxt_option_id;
	}

	public String getOption_push() {
		return option_push;
	}

	public void setOption_push(String option_push) {
		this.option_push = option_push;
	}

	public void setJwcpxt_option_id(String jwcpxt_option_id) {
		this.jwcpxt_option_id = jwcpxt_option_id;
	}

	public String getOption_describe() {
		return option_describe;
	}

	public void setOption_describe(String option_describe) {
		this.option_describe = option_describe;
	}

	public String getOption_question() {
		return option_question;
	}

	public void setOption_question(String option_question) {
		this.option_question = option_question;
	}

	public int getOption_sort() {
		return option_sort;
	}

	public void setOption_sort(int option_sort) {
		this.option_sort = option_sort;
	}

	public double getOption_grade() {
		return option_grade;
	}

	public void setOption_grade(double option_grade) {
		this.option_grade = option_grade;
	}

	public String getOption_gmt_create() {
		return option_gmt_create;
	}

	public void setOption_gmt_create(String option_gmt_create) {
		this.option_gmt_create = option_gmt_create;
	}

	public String getOption_gmt_modified() {
		return option_gmt_modified;
	}

	public void setOption_gmt_modified(String option_gmt_modified) {
		this.option_gmt_modified = option_gmt_modified;
	}

	@Override
	public String toString() {
		return "jwcpxt_option [jwcpxt_option_id=" + jwcpxt_option_id + ", option_describe=" + option_describe
				+ ", option_question=" + option_question + ", option_sort=" + option_sort + ", option_grade="
				+ option_grade + ", option_push=" + option_push + ", option_gmt_create=" + option_gmt_create
				+ ", option_gmt_modified=" + option_gmt_modified + "]";
	}

}
