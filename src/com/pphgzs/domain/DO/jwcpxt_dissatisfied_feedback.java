package com.pphgzs.domain.DO;

/**
 * 
 * @author JXX
 *
 */
public class jwcpxt_dissatisfied_feedback {
	private String jwcpxt_dissatisfied_feedback_id;
	private String dissatisfied_feedback_answer_choice;
	private String dissatisfied_feedback_time;
	private String dissatisfied_feedback_state;
	private String dissatisfied_feedback_gmt_create;
	private String dissatisfied_feedback_gmt_modified;

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

	public String getDissatisfied_feedback_time() {
		return dissatisfied_feedback_time;
	}

	public void setDissatisfied_feedback_time(String dissatisfied_feedback_time) {
		this.dissatisfied_feedback_time = dissatisfied_feedback_time;
	}

	public String getDissatisfied_feedback_state() {
		return dissatisfied_feedback_state;
	}

	public void setDissatisfied_feedback_state(String dissatisfied_feedback_state) {
		this.dissatisfied_feedback_state = dissatisfied_feedback_state;
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

	@Override
	public String toString() {
		return "jwcpxt_dissatisfied_feedback [jwcpxt_dissatisfied_feedback_id=" + jwcpxt_dissatisfied_feedback_id
				+ ", dissatisfied_feedback_answer_choice=" + dissatisfied_feedback_answer_choice
				+ ", dissatisfied_feedback_time=" + dissatisfied_feedback_time + ", dissatisfied_feedback_state="
				+ dissatisfied_feedback_state + ", dissatisfied_feedback_gmt_create=" + dissatisfied_feedback_gmt_create
				+ ", dissatisfied_feedback_gmt_modified=" + dissatisfied_feedback_gmt_modified + "]";
	}

}
