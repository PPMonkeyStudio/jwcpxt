package com.pphgzs.domain.DTO;

import com.pphgzs.domain.DO.jwcpxt_answer_choice;
import com.pphgzs.domain.DO.jwcpxt_answer_open;
import com.pphgzs.domain.DO.jwcpxt_option;
import com.pphgzs.domain.DO.jwcpxt_question;

/**
 * 回答问题
 * 
 * @author JXX
 *
 */
public class AnswerDTO {

	// 所属问题
	private jwcpxt_question question;
	// 选择题回答
	private jwcpxt_answer_choice answerChoice;
	// 所属选项
	private jwcpxt_option option;
	// 开放题回答
	private jwcpxt_answer_open answerOpen;

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

	public jwcpxt_answer_choice getAnswerChoice() {
		return answerChoice;
	}

	public void setAnswerChoice(jwcpxt_answer_choice answerChoice) {
		this.answerChoice = answerChoice;
	}

	public jwcpxt_answer_open getAnswerOpen() {
		return answerOpen;
	}

	public void setAnswerOpen(jwcpxt_answer_open answerOpen) {
		this.answerOpen = answerOpen;
	}

	@Override
	public String toString() {
		return "AnswerDTO ["
				+ "\n question=" + question + ","
				+ "\n answerChoice=" + answerChoice + ","
				+ "\n option=" + option + ","
				+ "\n answerOpen=" + answerOpen + "\n ]";
	}

}
