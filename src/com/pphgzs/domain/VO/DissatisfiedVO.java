package com.pphgzs.domain.VO;

import java.util.List;

import com.pphgzs.domain.DTO.DissatisfiedDTO;

/**
 * 
 * @author JXX
 *
 */
public class DissatisfiedVO {
	private List<DissatisfiedDTO> listAttentionDTO;
	private String tmp = "";

	public String getTmp() {
		return tmp;
	}

	public void setTmp(String tmp) {
		this.tmp = tmp;
	}

	public List<DissatisfiedDTO> getListAttentionDTO() {
		return listAttentionDTO;
	}

	public void setListAttentionDTO(List<DissatisfiedDTO> listAttentionDTO) {
		this.listAttentionDTO = listAttentionDTO;
	}

	@Override
	public String toString() {
		return "AttentionVO [listAttentionDTO=" + listAttentionDTO + "]";
	}

	public DissatisfiedVO(List<DissatisfiedDTO> listAttentionDTO) {
		super();
		this.listAttentionDTO = listAttentionDTO;
	}

	public DissatisfiedVO() {
		super();
	}

}
