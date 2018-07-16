package com.pphgzs.domain.VO;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_unit;
import com.pphgzs.domain.DTO.StatisticsDissatisfiedDayDataDTO;

public class StatisticsDissatisfiedDayDataVO {

	private jwcpxt_unit unit;

	private String startTime = "0000-00-00";
	private String endTime = "9999-99-99";

	// 存的是所有的业务的数据
	private List<StatisticsDissatisfiedDayDataDTO> statisticsDissatisfiedDayData;

	@Override
	public String toString() {
		return "StatisticsDissatisfiedDayDataVO 【\nunit=" + unit + ", \nstartTime=" + startTime + ", \nendTime="
				+ endTime + ", \nstatisticsDissatisfiedDayData=" + statisticsDissatisfiedDayData + "\n】";
	}

	public jwcpxt_unit getUnit() {
		return unit;
	}

	public void setUnit(jwcpxt_unit unit) {
		this.unit = unit;
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

	public List<StatisticsDissatisfiedDayDataDTO> getStatisticsDissatisfiedDayData() {
		return statisticsDissatisfiedDayData;
	}

	public void setStatisticsDissatisfiedDayData(List<StatisticsDissatisfiedDayDataDTO> statisticsDissatisfiedDayData) {
		this.statisticsDissatisfiedDayData = statisticsDissatisfiedDayData;
	}

}
