package com.pphgzs.domain.DTO;

import com.pphgzs.domain.DO.jwcpxt_answer_choice;
import com.pphgzs.domain.DO.jwcpxt_option;
import com.pphgzs.domain.DO.jwcpxt_question;
import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_service_instance;
import com.pphgzs.domain.DO.jwcpxt_unit;

public class SecondDistatisDTO {
	private jwcpxt_answer_choice answerChoice;
	private jwcpxt_question question;
	private jwcpxt_service_definition serviceDefinition;
	private jwcpxt_option _option;
	private jwcpxt_service_client serviceClient;
	private jwcpxt_service_instance serviceInstance;
	private jwcpxt_unit unit;

	public jwcpxt_answer_choice getAnswerChoice() {
		return answerChoice;
	}

	public void setAnswerChoice(jwcpxt_answer_choice answerChoice) {
		this.answerChoice = answerChoice;
	}

	public jwcpxt_question getQuestion() {
		return question;
	}

	public void setQuestion(jwcpxt_question question) {
		this.question = question;
	}

	public jwcpxt_service_definition getServiceDefinition() {
		return serviceDefinition;
	}

	public void setServiceDefinition(jwcpxt_service_definition serviceDefinition) {
		this.serviceDefinition = serviceDefinition;
	}

	public jwcpxt_option get_option() {
		return _option;
	}

	public void set_option(jwcpxt_option _option) {
		this._option = _option;
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

	public jwcpxt_unit getUnit() {
		return unit;
	}

	public void setUnit(jwcpxt_unit unit) {
		this.unit = unit;
	}

	@Override
	public String toString() {
		return "SecondDistatisVO [answerChoice=" + answerChoice + ", question=" + question + ", serviceDefinition="
				+ serviceDefinition + ", _option=" + _option + ", serviceClient=" + serviceClient + ", serviceInstance="
				+ serviceInstance + ", unit=" + unit + "]";
	}

	public SecondDistatisDTO(jwcpxt_answer_choice answerChoice, jwcpxt_question question,
			jwcpxt_service_definition serviceDefinition, jwcpxt_option _option, jwcpxt_service_client serviceClient,
			jwcpxt_service_instance serviceInstance, jwcpxt_unit unit) {
		super();
		this.answerChoice = answerChoice;
		this.question = question;
		this.serviceDefinition = serviceDefinition;
		this._option = _option;
		this.serviceClient = serviceClient;
		this.serviceInstance = serviceInstance;
		this.unit = unit;
	}

	public SecondDistatisDTO() {
		super();
	}

}
