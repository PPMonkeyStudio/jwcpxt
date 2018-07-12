package com.pphgzs.domain.DTO;

import com.pphgzs.domain.DO.jwcpxt_dissatisfied_feedback;
import com.pphgzs.domain.DO.jwcpxt_question;

/**
 * 不满意反馈表以及问题表
 * 
 * @author JXX
 *
 */
public class DissatisfiedQuestionDTO {
	private jwcpxt_dissatisfied_feedback dessatisfiedFeedback;
	private jwcpxt_question question;

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
		return "DissatisfiedQuestionDTO [dessatisfiedFeedback=" + dessatisfiedFeedback + ", question=" + question + "]";
	}

}
