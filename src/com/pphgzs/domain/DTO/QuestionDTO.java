package com.pphgzs.domain.DTO;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_question;

public class QuestionDTO {

	/**
	 * 问题
	 */
	private jwcpxt_question question;

	/**
	 * 选择题
	 * 
	 */
	private List<OptionDTO> listOptionDTO;

	public jwcpxt_question getQuestion() {
		return question;
	}

	public void setQuestion(jwcpxt_question question) {
		this.question = question;
	}

	public List<OptionDTO> getListOptionDTO() {
		return listOptionDTO;
	}

	public void setListOptionDTO(List<OptionDTO> listOptionDTO) {
		this.listOptionDTO = listOptionDTO;
	}

	@Override
	public String toString() {
		return "QuestionDTO [question=" + question + ", listOptionDTO=" + listOptionDTO + "]";
	}

}
