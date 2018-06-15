package com.pphgzs.domain.DTO;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_question;

public class QuestionDTO {

	private jwcpxt_question question;

	private ServiceDefinitionDTO serviceDefinitionDTO;

	private List<OptionDTO> optionDTOList;

	@Override
	public String toString() {
		return "QuestionDTO 【\nquestion=" + question + ", \nserviceDefinitionDTO=" + serviceDefinitionDTO
				+ ", \noptionDTOList=" + optionDTOList + "\n】";
	}

	public jwcpxt_question getQuestion() {
		return question;
	}

	public void setQuestion(jwcpxt_question question) {
		this.question = question;
	}

	public ServiceDefinitionDTO getServiceDefinitionDTO() {
		return serviceDefinitionDTO;
	}

	public void setServiceDefinitionDTO(ServiceDefinitionDTO serviceDefinitionDTO) {
		this.serviceDefinitionDTO = serviceDefinitionDTO;
	}

	public List<OptionDTO> getOptionDTOList() {
		return optionDTOList;
	}

	public void setOptionDTOList(List<OptionDTO> optionDTOList) {
		this.optionDTOList = optionDTOList;
	}

}
