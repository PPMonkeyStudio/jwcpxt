package com.pphgzs.domain.DTO;
/**
 * 
 * @author JXX
 *
 */

import com.pphgzs.domain.DO.jwcpxt_option;
import com.pphgzs.domain.DO.jwcpxt_question;

public class QuestionOptionAnswerDTO {
	private jwcpxt_question question;
	private jwcpxt_option option;

	public jwcpxt_question getQuestion() {
		return question;
	}

	public void setQuestion(jwcpxt_question question) {
		this.question = question;
	}

	public jwcpxt_option getOption() {
		return option;
	}

	public void setOption(jwcpxt_option option) {
		this.option = option;
	}

	@Override
	public String toString() {
		return "QuestionOptionAnswerDTO [question=" + question + ", option=" + option + "]";
	}

	public QuestionOptionAnswerDTO(jwcpxt_question question, jwcpxt_option option) {
		super();
		this.question = question;
		this.option = option;
	}

	public QuestionOptionAnswerDTO() {
		super();
	}

}
