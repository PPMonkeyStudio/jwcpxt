package com.pphgzs.domain.DO;

public class jwcpxt_answer_open {
	private String jwcpxt_answer_open_id;
	private String answer_open_content;
	private String answer_open_client;
	private String answer_open_question;
	private String answer_open_gmt_create;
	private String answer_open_gmt_modified;

	public String getJwcpxt_answer_open_id() {
		return jwcpxt_answer_open_id;
	}

	public String getAnswer_open_client() {
		return answer_open_client;
	}

	public void setAnswer_open_client(String answer_open_client) {
		this.answer_open_client = answer_open_client;
	}

	public void setJwcpxt_answer_open_id(String jwcpxt_answer_open_id) {
		this.jwcpxt_answer_open_id = jwcpxt_answer_open_id;
	}

	public String getAnswer_open_content() {
		return answer_open_content;
	}

	public void setAnswer_open_content(String answer_open_content) {
		this.answer_open_content = answer_open_content;
	}

	public String getAnswer_open_question() {
		return answer_open_question;
	}

	public void setAnswer_open_question(String answer_open_question) {
		this.answer_open_question = answer_open_question;
	}

	public String getAnswer_open_gmt_create() {
		return answer_open_gmt_create;
	}

	public void setAnswer_open_gmt_create(String answer_open_gmt_create) {
		this.answer_open_gmt_create = answer_open_gmt_create;
	}

	public String getAnswer_open_gmt_modified() {
		return answer_open_gmt_modified;
	}

	public void setAnswer_open_gmt_modified(String answer_open_gmt_modified) {
		this.answer_open_gmt_modified = answer_open_gmt_modified;
	}

	@Override
	public String toString() {
		return "jwcpxt_answer_open [jwcpxt_answer_open_id=" + jwcpxt_answer_open_id + ", answer_open_content="
				+ answer_open_content + ", answer_open_client=" + answer_open_client + ", answer_open_question="
				+ answer_open_question + ", answer_open_gmt_create=" + answer_open_gmt_create
				+ ", answer_open_gmt_modified=" + answer_open_gmt_modified + "]";
	}

}
