package com.pphgzs.domain.VO;

import java.util.List;

import com.pphgzs.domain.DTO.QuestionnaireDTO;

public class QuestionnaireVO {

	private List<QuestionnaireDTO> questionnaireDTOList;

	private int totalRecords;

	@Override
	public String toString() {
		return "QuestionnaireVO 【\nquestionnaireDTOList=" + questionnaireDTOList + ", \ntotalRecords=" + totalRecords
				+ "\n】";
	}

	public List<QuestionnaireDTO> getQuestionnaireDTOList() {
		return questionnaireDTOList;
	}

	public void setQuestionnaireDTOList(List<QuestionnaireDTO> questionnaireDTOList) {
		this.questionnaireDTOList = questionnaireDTOList;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

}
