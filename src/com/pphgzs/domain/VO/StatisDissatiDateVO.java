package com.pphgzs.domain.VO;

import java.util.List;

import com.pphgzs.domain.DTO.StatisticsDissatisfiedDateDTO;

/**
 * 
 * @author JXX
 *
 */
public class StatisDissatiDateVO {
	private List<StatisticsDissatisfiedDateDTO> listStaDisDateDTO;
	// 单位
	private String screenUnit = "";
	private String startTime = "";
	private String endTime = "";
	//
	private String timeType = "1";

	public List<StatisticsDissatisfiedDateDTO> getListStaDisDateDTO() {
		return listStaDisDateDTO;
	}

	public void setListStaDisDateDTO(List<StatisticsDissatisfiedDateDTO> listStaDisDateDTO) {
		this.listStaDisDateDTO = listStaDisDateDTO;
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

	@Override
	public String toString() {
		return "StatisDissatiDateVO [listStaDisDateDTO=" + listStaDisDateDTO + ", screenUnit=" + screenUnit
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", timeType=" + timeType + "]";
	}

	public StatisDissatiDateVO(List<StatisticsDissatisfiedDateDTO> listStaDisDateDTO, String screenUnit,
			String startTime, String endTime, String timeType) {
		super();
		this.listStaDisDateDTO = listStaDisDateDTO;
		this.screenUnit = screenUnit;
		this.startTime = startTime;
		this.endTime = endTime;
		this.timeType = timeType;
	}

	public StatisDissatiDateVO() {
		super();
	}

}
