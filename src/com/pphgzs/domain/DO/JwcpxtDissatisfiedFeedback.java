package com.pphgzs.domain.DO;

/**
 * JwcpxtDissatisfiedFeedback entity. @author MyEclipse Persistence Tools
 */

public class JwcpxtDissatisfiedFeedback implements java.io.Serializable {

	// Fields

	private String jwcpxtDissatisfiedFeedbackId;
	private String dissatisfiedFeedbackAnswerChoice;
	private Integer dissatisfiedFeedbackState;
	private String dissatisfiedFeedbackAuditOpinion;
	private String dissatisfiedFeedbackGmtCreate;
	private String dissatisfiedFeedbackGmtModified;

	// Constructors

	/** default constructor */
	public JwcpxtDissatisfiedFeedback() {
	}

	/** minimal constructor */
	public JwcpxtDissatisfiedFeedback(String jwcpxtDissatisfiedFeedbackId) {
		this.jwcpxtDissatisfiedFeedbackId = jwcpxtDissatisfiedFeedbackId;
	}

	/** full constructor */
	public JwcpxtDissatisfiedFeedback(String jwcpxtDissatisfiedFeedbackId, String dissatisfiedFeedbackAnswerChoice,
			Integer dissatisfiedFeedbackState, String dissatisfiedFeedbackAuditOpinion,
			String dissatisfiedFeedbackGmtCreate, String dissatisfiedFeedbackGmtModified) {
		this.jwcpxtDissatisfiedFeedbackId = jwcpxtDissatisfiedFeedbackId;
		this.dissatisfiedFeedbackAnswerChoice = dissatisfiedFeedbackAnswerChoice;
		this.dissatisfiedFeedbackState = dissatisfiedFeedbackState;
		this.dissatisfiedFeedbackAuditOpinion = dissatisfiedFeedbackAuditOpinion;
		this.dissatisfiedFeedbackGmtCreate = dissatisfiedFeedbackGmtCreate;
		this.dissatisfiedFeedbackGmtModified = dissatisfiedFeedbackGmtModified;
	}

	// Property accessors

	public String getJwcpxtDissatisfiedFeedbackId() {
		return this.jwcpxtDissatisfiedFeedbackId;
	}

	public void setJwcpxtDissatisfiedFeedbackId(String jwcpxtDissatisfiedFeedbackId) {
		this.jwcpxtDissatisfiedFeedbackId = jwcpxtDissatisfiedFeedbackId;
	}

	public String getDissatisfiedFeedbackAnswerChoice() {
		return this.dissatisfiedFeedbackAnswerChoice;
	}

	public void setDissatisfiedFeedbackAnswerChoice(String dissatisfiedFeedbackAnswerChoice) {
		this.dissatisfiedFeedbackAnswerChoice = dissatisfiedFeedbackAnswerChoice;
	}

	public Integer getDissatisfiedFeedbackState() {
		return this.dissatisfiedFeedbackState;
	}

	public void setDissatisfiedFeedbackState(Integer dissatisfiedFeedbackState) {
		this.dissatisfiedFeedbackState = dissatisfiedFeedbackState;
	}

	public String getDissatisfiedFeedbackAuditOpinion() {
		return this.dissatisfiedFeedbackAuditOpinion;
	}

	public void setDissatisfiedFeedbackAuditOpinion(String dissatisfiedFeedbackAuditOpinion) {
		this.dissatisfiedFeedbackAuditOpinion = dissatisfiedFeedbackAuditOpinion;
	}

	public String getDissatisfiedFeedbackGmtCreate() {
		return this.dissatisfiedFeedbackGmtCreate;
	}

	public void setDissatisfiedFeedbackGmtCreate(String dissatisfiedFeedbackGmtCreate) {
		this.dissatisfiedFeedbackGmtCreate = dissatisfiedFeedbackGmtCreate;
	}

	public String getDissatisfiedFeedbackGmtModified() {
		return this.dissatisfiedFeedbackGmtModified;
	}

	public void setDissatisfiedFeedbackGmtModified(String dissatisfiedFeedbackGmtModified) {
		this.dissatisfiedFeedbackGmtModified = dissatisfiedFeedbackGmtModified;
	}

}