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
	// 办理时间
	private String userId = "";
	private String startTime = "0000-00-00";

	// 回访时间
	private String startHTime = "0000-00-00";
	private String endHTime = "9999-99-99";

	public String getStartHTime() {
		return startHTime;
	}

	public void setStartHTime(String startHTime) {
		this.startHTime = startHTime;
	}

	public String getEndHTime() {
		return endHTime;
	}

	public void setEndHTime(String endHTime) {
		this.endHTime = endHTime;
	}

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

	public ReturnVisitVO(List<ReturnVisitDTO> listReturnVisitDTO, String userId, String startTime, String startHTime,
			String endHTime, String endTime) {
		super();
		this.listReturnVisitDTO = listReturnVisitDTO;
		this.userId = userId;
		this.startTime = startTime;
		this.startHTime = startHTime;
		this.endHTime = endHTime;
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return "ReturnVisitVO [listReturnVisitDTO=" + listReturnVisitDTO + ", userId=" + userId + ", startTime="
				+ startTime + ", startHTime=" + startHTime + ", endHTime=" + endHTime + ", endTime=" + endTime + "]";
	}

}
