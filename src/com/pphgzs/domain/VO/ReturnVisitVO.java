package com.pphgzs.domain.VO;

import java.util.List;

import com.pphgzs.domain.DTO.ReturnVisitDTO;

/**
 * 
 * @author JXX
 *
 */
public class ReturnVisitVO {
	private List<ReturnVisitDTO> listReturnVisitDTO = null;
	private String userId = "";
	private String startTime = "";

	public String getUserId() {
		return userId;
	}

	public List<ReturnVisitDTO> getListReturnVisitDTO() {
		return listReturnVisitDTO;
	}

	public void setListReturnVisitDTO(List<ReturnVisitDTO> listReturnVisitDTO) {
		this.listReturnVisitDTO = listReturnVisitDTO;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	private String endTime = "";

	public ReturnVisitVO() {
		super();
	}

	public ReturnVisitVO(List<ReturnVisitDTO> listReturnVisitDTO, String userId, String startTime, String endTime) {
		super();
		this.listReturnVisitDTO = listReturnVisitDTO;
		this.userId = userId;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "ReturnVisitVO [listReturnVisitDTO=" + listReturnVisitDTO + ", userId=" + userId + ", startTime="
				+ startTime + ", endTime=" + endTime + "]";
	}

}
