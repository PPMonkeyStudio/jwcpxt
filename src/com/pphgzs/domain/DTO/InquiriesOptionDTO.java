package com.pphgzs.domain.DTO;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_option;
import com.pphgzs.domain.DO.jwcpxt_question;

/**
 * 追问
 * 
 * @author JXX
 */
public class InquiriesOptionDTO {
	/**
	 * 问题
	 */
	private jwcpxt_question inquiriesQuestion;
	/**
	 * 选项
	 */
	private List<jwcpxt_option> listInquiriesOption;

	public jwcpxt_question getInquiriesQuestion() {
		return inquiriesQuestion;
	}

	public void setInquiriesQuestion(jwcpxt_question inquiriesQuestion) {
		this.inquiriesQuestion = inquiriesQuestion;
	}

	public List<jwcpxt_option> getListInquiriesOption() {
		return listInquiriesOption;
	}

	public void setListInquiriesOption(List<jwcpxt_option> listInquiriesOption) {
		this.listInquiriesOption = listInquiriesOption;
	}

	@Override
	public String toString() {
		return "InquiriesOptionDTO [inquiriesQuestion=" + inquiriesQuestion + ", listInquiriesOption="
				+ listInquiriesOption + "]";
	}

}
