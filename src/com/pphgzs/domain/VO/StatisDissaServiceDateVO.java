package com.pphgzs.domain.VO;

import java.util.List;

import com.pphgzs.domain.DTO.StatisDIssaServiceDateDTO;

/**
 * 
 * @author JXX
 *
 */
public class StatisDissaServiceDateVO {
	private List<StatisDIssaServiceDateDTO> listStatisDIssaServiceDateDTO;
	// 单位
	private String screenUnit = "";
	private String startTime = "";
	private String endTime = "";
	//
	private String timeType = "1";

	@Override
	public String toString() {
		return "StatisDissaServiceDateVO [listStatisDIssaServiceDateDTO=" + listStatisDIssaServiceDateDTO
				+ ", screenUnit=" + screenUnit + ", startTime=" + startTime + ", endTime=" + endTime + ", timeType="
				+ timeType + "]";
	}

	public List<StatisDIssaServiceDateDTO> getListStatisDIssaServiceDateDTO() {
		return listStatisDIssaServiceDateDTO;
	}

	public void setListStatisDIssaServiceDateDTO(List<StatisDIssaServiceDateDTO> listStatisDIssaServiceDateDTO) {
		this.listStatisDIssaServiceDateDTO = listStatisDIssaServiceDateDTO;
	}

	public String getScreenUnit() {
		return screenUnit;
	}

	public void setScreenUnit(String screenUnit) {
		this.screenUnit = screenUnit;
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

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public StatisDissaServiceDateVO(List<StatisDIssaServiceDateDTO> listStatisDIssaServiceDateDTO, String screenUnit,
			String startTime, String endTime, String timeType) {
		super();
		this.listStatisDIssaServiceDateDTO = listStatisDIssaServiceDateDTO;
		this.screenUnit = screenUnit;
		this.startTime = startTime;
		this.endTime = endTime;
		this.timeType = timeType;
	}

	public StatisDissaServiceDateVO() {
		super();
	}

}
