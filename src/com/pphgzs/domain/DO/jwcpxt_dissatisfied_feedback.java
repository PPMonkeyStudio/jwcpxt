package com.pphgzs.domain.DO;

public class jwcpxt_dissatisfied_feedback {
	private String jwcpxt_dissatisfied_feedback_id;

	private String dissatisfied_feedback_answer_choice;
	private int dissatisfied_feedback_state;
	private String dissatisfied_feedback_audit_opinion;
	private String dissatisfied_feedback_gmt_create;
	private String dissatisfied_feedback_gmt_modified;

	@Override
	public String toString() {
		return "jwcpxt_dissatisfied_feedback 【\njwcpxt_dissatisfied_feedback_id=" + jwcpxt_dissatisfied_feedback_id
				+ ", \ndissatisfied_feedback_answer_choice=" + dissatisfied_feedback_answer_choice
				+ ", \ndissatisfied_feedback_state=" + dissatisfied_feedback_state
				+ ", \ndissatisfied_feedback_audit_opinion=" + dissatisfied_feedback_audit_opinion
				+ ", \ndissatisfied_feedback_gmt_create=" + dissatisfied_feedback_gmt_create
				+ ", \ndissatisfied_feedback_gmt_modified=" + dissatisfied_feedback_gmt_modified + "\n】";
	}

	public String getJwcpxt_dissatisfied_feedback_id() {
		return jwcpxt_dissatisfied_feedback_id;
	}

	public void setJwcpxt_dissatisfied_feedback_id(String jwcpxt_dissatisfied_feedback_id) {
		this.jwcpxt_dissatisfied_feedback_id = jwcpxt_dissatisfied_feedback_id;
	}

	public String getDissatisfied_feedback_answer_choice() {
		return dissatisfied_feedback_answer_choice;
	}

	public void setDissatisfied_feedback_answer_choice(String dissatisfied_feedback_answer_choice) {
		this.dissatisfied_feedback_answer_choice = dissatisfied_feedback_answer_choice;
	}

	public int getDissatisfied_feedback_state() {
		return dissatisfied_feedback_state;
	}

	public void setDissatisfied_feedback_state(int dissatisfied_feedback_state) {
		this.dissatisfied_feedback_state = dissatisfied_feedback_state;
	}

	public String getDissatisfied_feedback_audit_opinion() {
		return dissatisfied_feedback_audit_opinion;
	}

	public void setDissatisfied_feedback_audit_opinion(String dissatisfied_feedback_audit_opinion) {
		this.dissatisfied_feedback_audit_opinion = dissatisfied_feedback_audit_opinion;
	}

	public String getDissatisfied_feedback_gmt_create() {
		return dissatisfied_feedback_gmt_create;
	}

	public void setDissatisfied_feedback_gmt_create(String dissatisfied_feedback_gmt_create) {
		this.dissatisfied_feedback_gmt_create = dissatisfied_feedback_gmt_create;
	}

	public String getDissatisfied_feedback_gmt_modified() {
		return dissatisfied_feedback_gmt_modified;
	}

	public void setDissatisfied_feedback_gmt_modified(String dissatisfied_feedback_gmt_modified) {
		this.dissatisfied_feedback_gmt_modified = dissatisfied_feedback_gmt_modified;
	}

}
