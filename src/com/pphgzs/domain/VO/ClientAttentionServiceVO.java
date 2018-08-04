package com.pphgzs.domain.VO;

import java.util.List;

import com.pphgzs.domain.DTO.ClientAttentionServiceDTO;

/**
 * 
 * @author JXX
 *
 */
public class ClientAttentionServiceVO {
	private List<ClientAttentionServiceDTO> listClientAttentionServiceDTO;
	private String tmp = "";

	public String getTmp() {
		return tmp;
	}

	public void setTmp(String tmp) {
		this.tmp = tmp;
	}

	public List<ClientAttentionServiceDTO> getListClientAttentionServiceDTO() {
		return listClientAttentionServiceDTO;
	}

	public void setListClientAttentionServiceDTO(List<ClientAttentionServiceDTO> listClientAttentionServiceDTO) {
		this.listClientAttentionServiceDTO = listClientAttentionServiceDTO;
	}

	@Override
	public String toString() {
		return "ClientAttentionServiceVO [listClientAttentionServiceDTO=" + listClientAttentionServiceDTO + "]";
	}

	public ClientAttentionServiceVO(List<ClientAttentionServiceDTO> listClientAttentionServiceDTO) {
		super();
		this.listClientAttentionServiceDTO = listClientAttentionServiceDTO;
	}

	public ClientAttentionServiceVO() {
		super();
	}

}
