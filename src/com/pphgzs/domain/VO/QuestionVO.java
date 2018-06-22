package com.pphgzs.domain.VO;

import java.util.List;

import com.pphgzs.domain.DTO.QuestionDTO;

public class QuestionVO {
	private List<QuestionDTO> questionDTOList;

	private int totalRecords = 0;

	@Override
	public String toString() {
		return "QuestionVO 【\nquestionDTOList=" + questionDTOList + ", \ntotalRecords=" + totalRecords + "\n】";
	}

	public List<QuestionDTO> getQuestionDTOList() {
		return questionDTOList;
	}

	public void setQuestionDTOList(List<QuestionDTO> questionDTOList) {
		this.questionDTOList = questionDTOList;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

}
