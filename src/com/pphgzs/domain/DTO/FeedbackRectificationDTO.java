package com.pphgzs.domain.DTO;

import com.pphgzs.domain.DO.jwcpxt_dissatisfied_feedback;
import com.pphgzs.domain.DO.jwcpxt_feedback_rectification;
import com.pphgzs.domain.DO.jwcpxt_question;
import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_service_instance;
import com.pphgzs.domain.DO.jwcpxt_unit;

/**
 * 
 * @author JXX
 *
 */
public class FeedbackRectificationDTO {
	private jwcpxt_feedback_rectification feedbackRectification;
	private jwcpxt_dissatisfied_feedback dissatisfiedFeedback;
	private jwcpxt_question question;
	private jwcpxt_service_client serviceClient;
	private jwcpxt_service_instance serviceInstance;
	private jwcpxt_service_definition serviceDefinition;
	private jwcpxt_unit unit;

	public FeedbackRectificationDTO() {
		super();
	}

	public FeedbackRectificationDTO(jwcpxt_feedback_rectification feedbackRectification,
			jwcpxt_dissatisfied_feedback dissatisfiedFeedback, jwcpxt_question question,
			jwcpxt_service_client serviceClient, jwcpxt_service_instance serviceInstance,
			jwcpxt_service_definition serviceDefinition, jwcpxt_unit unit) {
		super();
		this.feedbackRectification = feedbackRectification;
		this.dissatisfiedFeedback = dissatisfiedFeedback;
		this.question = question;
		this.serviceClient = serviceClient;
		this.serviceInstance = serviceInstance;
		this.serviceDefinition = serviceDefinition;
		this.unit = unit;
	}

	public jwcpxt_feedback_rectification getFeedbackRectification() {
		return feedbackRectification;
	}

	public void setFeedbackRectification(jwcpxt_feedback_rectification feedbackRectification) {
		this.feedbackRectification = feedbackRectification;
	}

	public jwcpxt_dissatisfied_feedback getDissatisfiedFeedback() {
		return dissatisfiedFeedback;
	}

	public void setDissatisfiedFeedback(jwcpxt_dissatisfied_feedback dissatisfiedFeedback) {
		this.dissatisfiedFeedback = dissatisfiedFeedback;
	}

	public jwcpxt_question getQuestion() {
		return question;
	}

	public void setQuestion(jwcpxt_question question) {
		this.question = question;
	}

	public jwcpxt_service_client getServiceClient() {
		return serviceClient;
	}

	public void setServiceClient(jwcpxt_service_client serviceClient) {
		this.serviceClient = serviceClient;
	}

	public jwcpxt_service_instance getServiceInstance() {
		return serviceInstance;
	}

	public void setServiceInstance(jwcpxt_service_instance serviceInstance) {
		this.serviceInstance = serviceInstance;
	}

	public jwcpxt_service_definition getServiceDefinition() {
		return serviceDefinition;
	}

	public void setServiceDefinition(jwcpxt_service_definition serviceDefinition) {
		this.serviceDefinition = serviceDefinition;
	}

	public jwcpxt_unit getUnit() {
		return unit;
	}

	public void setUnit(jwcpxt_unit unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "FeedbackRectificationDTO [feedbackRectification=" + feedbackRectification + ", dissatisfiedFeedback="
				+ dissatisfiedFeedback + ", question=" + question + ", serviceClient=" + serviceClient
				+ ", serviceInstance=" + serviceInstance + ", serviceDefinition=" + serviceDefinition + ", unit=" + unit
				+ "]";
	}

}
