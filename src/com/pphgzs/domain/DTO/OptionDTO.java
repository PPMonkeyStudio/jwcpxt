package com.pphgzs.domain.DTO;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_option;

/**
 * 
 * @author JXX
 *
 */
public class OptionDTO {
	private jwcpxt_option option;
	private List<InquiriesOptionDTO> listInquiriesOptionDTO;

	public jwcpxt_option getOption() {
		return option;
	}

	public void setOption(jwcpxt_option option) {
		this.option = option;
	}

	public List<InquiriesOptionDTO> getListInquiriesOptionDTO() {
		return listInquiriesOptionDTO;
	}

	public void setListInquiriesOptionDTO(List<InquiriesOptionDTO> listInquiriesOptionDTO) {
		this.listInquiriesOptionDTO = listInquiriesOptionDTO;
	}

	@Override
	public String toString() {
		return "OptionDTO [option=" + option + ", listInquiriesOptionDTO=" + listInquiriesOptionDTO + "]";
	}

}
