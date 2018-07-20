package com.pphgzs.domain.DTO;

import com.pphgzs.domain.DO.jwcpxt_dissatisfied_feedback;
import com.pphgzs.domain.DO.jwcpxt_question;
import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_service_instance;
import com.pphgzs.domain.DO.jwcpxt_unit;
import com.pphgzs.domain.DO.jwcpxt_user;

/**
 * 不满意反馈表以及问题表
 * 
 * @author JXX
 *
 */
public class DissatisfiedQuestionDTO {
	private jwcpxt_dissatisfied_feedback dessatisfiedFeedback;
	private jwcpxt_question question;
	private jwcpxt_service_client serviceClient;
	private jwcpxt_service_instance serviceInstance;
	private jwcpxt_service_definition serviceDefinition;
	private jwcpxt_unit unit;
	private jwcpxt_user user;

	public DissatisfiedQuestionDTO() {
	}

	public DissatisfiedQuestionDTO(jwcpxt_dissatisfied_feedback dessatisfiedFeedback, jwcpxt_question question,
			jwcpxt_service_client serviceClient, jwcpxt_service_instance serviceInstance,
			jwcpxt_service_definition serviceDefinition, jwcpxt_unit unit, jwcpxt_user user) {
		super();
		this.dessatisfiedFeedback = dessatisfiedFeedback;
		this.question = question;
		this.serviceClient = serviceClient;
		this.serviceInstance = serviceInstance;
		this.serviceDefinition = serviceDefinition;
		this.unit = unit;
		this.user = user;
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

	public jwcpxt_user getUser() {
		return user;
	}

	public void setUser(jwcpxt_user user) {
		this.user = user;
	}

	public jwcpxt_dissatisfied_feedback getDessatisfiedFeedback() {
		return dessatisfiedFeedback;
	}

	public void setDessatisfiedFeedback(jwcpxt_dissatisfied_feedback dessatisfiedFeedback) {
		this.dessatisfiedFeedback = dessatisfiedFeedback;
	}

	public jwcpxt_question getQuestion() {
		return question;
	}

	public void setQuestion(jwcpxt_question question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "DissatisfiedQuestionDTO [dessatisfiedFeedback=" + dessatisfiedFeedback + ", question=" + question
				+ ", serviceClient=" + serviceClient + ", serviceInstance=" + serviceInstance + ", serviceDefinition="
				+ serviceDefinition + ", unit=" + unit + ", user=" + user + "]";
	}

}
