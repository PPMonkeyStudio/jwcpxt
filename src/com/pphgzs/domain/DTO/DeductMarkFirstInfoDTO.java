package com.pphgzs.domain.DTO;

import com.pphgzs.domain.DO.jwcpxt_option;
import com.pphgzs.domain.DO.jwcpxt_question;
import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_unit;

/**
 * 
 * @author JXX
 *
 */
public class DeductMarkFirstInfoDTO {
	private jwcpxt_service_client serviceClient;
	private jwcpxt_service_definition serviceDefinition;
	private jwcpxt_unit unit;
	private jwcpxt_option firstOption;
	private jwcpxt_question firstQuestion;

	public jwcpxt_service_client getServiceClient() {
		return serviceClient;
	}

	public void setServiceClient(jwcpxt_service_client serviceClient) {
		this.serviceClient = serviceClient;
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

	public jwcpxt_option getFirstOption() {
		return firstOption;
	}

	public void setFirstOption(jwcpxt_option firstOption) {
		this.firstOption = firstOption;
	}

	public jwcpxt_question getFirstQuestion() {
		return firstQuestion;
	}

	public void setFirstQuestion(jwcpxt_question firstQuestion) {
		this.firstQuestion = firstQuestion;
	}

	@Override
	public String toString() {
		return "DeductMarkFirstInfoDTO [serviceClient=" + serviceClient + ", serviceDefinition=" + serviceDefinition
				+ ", unit=" + unit + ", firstOption=" + firstOption + ", firstQuestion=" + firstQuestion + "]";
	}

	public DeductMarkFirstInfoDTO(jwcpxt_service_client serviceClient, jwcpxt_service_definition serviceDefinition,
			jwcpxt_unit unit, jwcpxt_option firstOption, jwcpxt_question firstQuestion) {
		super();
		this.serviceClient = serviceClient;
		this.serviceDefinition = serviceDefinition;
		this.unit = unit;
		this.firstOption = firstOption;
		this.firstQuestion = firstQuestion;
	}

	public DeductMarkFirstInfoDTO() {
		super();
	}

}
