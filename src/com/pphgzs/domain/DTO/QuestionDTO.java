package com.pphgzs.domain.DTO;

import com.pphgzs.domain.DO.jwcpxt_question;

public class QuestionDTO {

	private jwcpxt_question question;

	public jwcpxt_question getQuestion() {
		return question;
	}

	@Override
	public String toString() {
		return "QuestionDTO [question=" + question + "]";
	}

	public void setQuestion(jwcpxt_question question) {
		this.question = question;
	}
}
