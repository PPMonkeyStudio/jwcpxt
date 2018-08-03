package com.pphgzs.domain.DTO;

/**
 * 
 * @author JXX
 *
 */
public class ClientAttentionServiceDTO {
	private String attentionService;
	private int count;

	public String getAttentionService() {
		return attentionService;
	}

	public void setAttentionService(String attentionService) {
		this.attentionService = attentionService;
	}

	@Override
	public String toString() {
		return "ClientAttentionServiceDTO [attentionService=" + attentionService + ", count=" + count + "]";
	}

	public ClientAttentionServiceDTO(String attentionService, int count) {
		super();
		this.attentionService = attentionService;
		this.count = count;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public ClientAttentionServiceDTO() {
		super();
	}

}
