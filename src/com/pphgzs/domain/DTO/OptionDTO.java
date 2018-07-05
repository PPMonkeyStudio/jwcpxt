package com.pphgzs.domain.DTO;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_option;
import com.pphgzs.domain.DO.jwcpxt_option_inquiries;

public class OptionDTO {
	private jwcpxt_option option;
	private List<jwcpxt_option_inquiries> inquiriesList;

	@Override
	public String toString() {
		return "OptionDTO [option=" + option + ", inquiriesList=" + inquiriesList + "]";
	}

	public jwcpxt_option getOption() {
		return option;
	}

	public void setOption(jwcpxt_option option) {
		this.option = option;
	}

	public List<jwcpxt_option_inquiries> getInquiriesList() {
		return inquiriesList;
	}

	public void setInquiriesList(List<jwcpxt_option_inquiries> inquiriesList) {
		this.inquiriesList = inquiriesList;
	}

}
