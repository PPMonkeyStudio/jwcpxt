package com.pphgzs.domain.DO;

/**
 * 
 * @author JXX
 *
 */
public class jwcpxt_feedback_rectification {
	private String jwcpxt_feedback_rectification_id;
	private String feedback_rectification_dissatisfied;
	private String feedback_rectification_content;
	private String feedback_rectification_state;
	private String feedback_rectification_time;
	private String feedback_rectification_gmt_create;
	private String feedback_rectification_gmt_modified;

	public String getJwcpxt_feedback_rectification_id() {
		return jwcpxt_feedback_rectification_id;
	}

	public void setJwcpxt_feedback_rectification_id(String jwcpxt_feedback_rectification_id) {
		this.jwcpxt_feedback_rectification_id = jwcpxt_feedback_rectification_id;
	}

	public String getFeedback_rectification_dissatisfied() {
		return feedback_rectification_dissatisfied;
	}

	public void setFeedback_rectification_dissatisfied(String feedback_rectification_dissatisfied) {
		this.feedback_rectification_dissatisfied = feedback_rectification_dissatisfied;
	}

	public String getFeedback_rectification_content() {
		return feedback_rectification_content;
	}

	public void setFeedback_rectification_content(String feedback_rectification_content) {
		this.feedback_rectification_content = feedback_rectification_content;
	}

	public String getFeedback_rectification_state() {
		return feedback_rectification_state;
	}

	public void setFeedback_rectification_state(String feedback_rectification_state) {
		this.feedback_rectification_state = feedback_rectification_state;
	}

	public String getFeedback_rectification_time() {
		return feedback_rectification_time;
	}

	public void setFeedback_rectification_time(String feedback_rectification_time) {
		this.feedback_rectification_time = feedback_rectification_time;
	}

	public String getFeedback_rectification_gmt_create() {
		return feedback_rectification_gmt_create;
	}

	public void setFeedback_rectification_gmt_create(String feedback_rectification_gmt_create) {
		this.feedback_rectification_gmt_create = feedback_rectification_gmt_create;
	}

	public String getFeedback_rectification_gmt_modified() {
		return feedback_rectification_gmt_modified;
	}

	public void setFeedback_rectification_gmt_modified(String feedback_rectification_gmt_modified) {
		this.feedback_rectification_gmt_modified = feedback_rectification_gmt_modified;
	}

	@Override
	public String toString() {
		return "jwcpxt_feedback_rectification [jwcpxt_feedback_rectification_id=" + jwcpxt_feedback_rectification_id
				+ ", feedback_rectification_dissatisfied=" + feedback_rectification_dissatisfied
				+ ", feedback_rectification_content=" + feedback_rectification_content
				+ ", feedback_rectification_state=" + feedback_rectification_state + ", feedback_rectification_time="
				+ feedback_rectification_time + ", feedback_rectification_gmt_create="
				+ feedback_rectification_gmt_create + ", feedback_rectification_gmt_modified="
				+ feedback_rectification_gmt_modified + "]";
	}

}
