package com.pphgzs.domain.DTO;

import com.pphgzs.domain.DO.jwcpxt_option;
import com.pphgzs.domain.DO.jwcpxt_question;
import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_unit;

/**
 * 
 * @author JXX
 *
 */
public class DeductMarkInfoDTO {
	private DeductMarkFirstInfoDTO deductMarkFirstInfoDTO;
	private String secondQuestion;
	private String secondDescribe;

	public String getSecondQuestion() {
		return secondQuestion;
	}

	public void setSecondQuestion(String secondQuestion) {
		this.secondQuestion = secondQuestion;
	}

	public String getSecondDescribe() {
		return secondDescribe;
	}

	public void setSecondDescribe(String secondDescribe) {
		this.secondDescribe = secondDescribe;
	}

	public DeductMarkFirstInfoDTO getDeductMarkFirstInfoDTO() {
		return deductMarkFirstInfoDTO;
	}

	public void setDeductMarkFirstInfoDTO(DeductMarkFirstInfoDTO deductMarkFirstInfoDTO) {
		this.deductMarkFirstInfoDTO = deductMarkFirstInfoDTO;
	}

	public DeductMarkInfoDTO(DeductMarkFirstInfoDTO deductMarkFirstInfoDTO, String secondQuestion,
			String secondDescribe) {
		super();
		this.deductMarkFirstInfoDTO = deductMarkFirstInfoDTO;
		this.secondQuestion = secondQuestion;
		this.secondDescribe = secondDescribe;
	}

	@Override
	public String toString() {
		return "DeductMarkInfoDTO [deductMarkFirstInfoDTO=" + deductMarkFirstInfoDTO + ", secondQuestion="
				+ secondQuestion + ", secondDescribe=" + secondDescribe + "]";
	}

	public DeductMarkInfoDTO() {
		super();
	}

}
