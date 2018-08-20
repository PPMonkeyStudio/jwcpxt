package com.pphgzs.domain.VO;

import com.pphgzs.domain.DTO.MonthDayMountDTO;

public class MonthDayMountVO {
	// 数据
	private MonthDayMountDTO monthDayMountDTO;
	// 开始时间
	private String startTime = "0000-00-00";
	// 结束时间
	private String endTime = "9999-99-99";

	private String userId = "";

	public MonthDayMountDTO getMonthDayMountDTO() {
		return monthDayMountDTO;
	}

	public void setMonthDayMountDTO(MonthDayMountDTO monthDayMountDTO) {
		this.monthDayMountDTO = monthDayMountDTO;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	@Override
	public String toString() {
		return "MonthDayMountVO [monthDayMountDTO=" + monthDayMountDTO + ", startTime=" + startTime + ", endTime="
				+ endTime + "]";
	}

}
