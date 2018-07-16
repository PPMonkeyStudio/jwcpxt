package com.pphgzs.domain.VO;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_unit;
import com.pphgzs.domain.DTO.StatisticsDissatisfiedDateCountDTO;

public class StatisticsDissatisfiedDateCountVO {
	private jwcpxt_unit unit;

	private String startTime = "0000-00-00";
	private String endTime = "9999-99-99";

	private List<StatisticsDissatisfiedDateCountDTO> statisticsDissatisfiedDateCountDTO;

	@Override
	public String toString() {
		return "StatisticsDissatisfiedDateCountVO 【\nunit=" + unit + ", \nstartTime=" + startTime + ", \nendTime="
				+ endTime + ", \nstatisticsDissatisfiedDateCountDTO=" + statisticsDissatisfiedDateCountDTO + "\n】";
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

	public List<StatisticsDissatisfiedDateCountDTO> getStatisticsDissatisfiedDateCountDTO() {
		return statisticsDissatisfiedDateCountDTO;
	}

	public void setStatisticsDissatisfiedDateCountDTO(
			List<StatisticsDissatisfiedDateCountDTO> statisticsDissatisfiedDateCountDTO) {
		this.statisticsDissatisfiedDateCountDTO = statisticsDissatisfiedDateCountDTO;
	}

}
