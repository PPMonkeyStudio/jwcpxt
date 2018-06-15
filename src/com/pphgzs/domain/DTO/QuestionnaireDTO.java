package com.pphgzs.domain.DTO;

import java.util.List;

public class QuestionnaireDTO {

	private ServiceDefinitionDTO serviceDefinitionDTO;

	private List<QuestionDTO> questionServiceDTOList;

	@Override
	public String toString() {
		return "QuestionnaireDTO 【\nserviceDefinitionDTO=" + serviceDefinitionDTO + ", \nquestionServiceDTOList="
				+ questionServiceDTOList + "\n】";
	}

	public ServiceDefinitionDTO getServiceDefinitionDTO() {
		return serviceDefinitionDTO;
	}

	public void setServiceDefinitionDTO(ServiceDefinitionDTO serviceDefinitionDTO) {
		this.serviceDefinitionDTO = serviceDefinitionDTO;
	}

	public List<QuestionDTO> getQuestionServiceDTOList() {
		return questionServiceDTOList;
	}

	public void setQuestionServiceDTOList(List<QuestionDTO> questionServiceDTOList) {
		this.questionServiceDTOList = questionServiceDTOList;
	}

}
