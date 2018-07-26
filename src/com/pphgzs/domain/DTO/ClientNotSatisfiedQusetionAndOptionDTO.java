package com.pphgzs.domain.DTO;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_question;

public class ClientNotSatisfiedQusetionAndOptionDTO {
	private jwcpxt_question question;
	private String answer;
	private List<ClientNotSatisfiedQusetionAndOptionDTO> askQusetionAndOptionDTO;

	public jwcpxt_question getQuestion() {
		return question;
	}

	public void setQuestion(jwcpxt_question question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public List<ClientNotSatisfiedQusetionAndOptionDTO> getAskQusetionAndOptionDTO() {
		return askQusetionAndOptionDTO;
	}

	public void setAskQusetionAndOptionDTO(List<ClientNotSatisfiedQusetionAndOptionDTO> askQusetionAndOptionDTO) {
		this.askQusetionAndOptionDTO = askQusetionAndOptionDTO;
	}

	@Override
	public String toString() {
		return "ClientNotSatisfiedQusetionAndOptionDTO [question=" + question + ", answer=" + answer
				+ ", askQusetionAndOptionDTO=" + askQusetionAndOptionDTO + ", getQuestion()=" + getQuestion()
				+ ", getAnswer()=" + getAnswer() + ", getAskQusetionAndOptionDTO()=" + getAskQusetionAndOptionDTO()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
