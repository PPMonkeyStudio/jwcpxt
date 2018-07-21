package com.pphgzs.domain.VO;

import java.util.List;

import com.pphgzs.domain.DTO.StatisDissaQuestionDateDTO;

/*
 * 
 */
public class StatisDissaQuestionDateVO {
	// private List<StatisDissaQuestionDateDTO> listStatisDissaDTO;
	// private List<QuestionOptionAnswerDTO> listQuestionOptionDTO;
	private List<StatisDissaQuestionDateDTO> listStatisQuestionDTO;
	// 单位
	private String screenUnit = "";
	private String screenService = "";
	private String startTime = "";
	private String endTime = "";
	//
	private String timeType = "1";

	//

	public String getScreenUnit() {
		return screenUnit;
	}

	public void setScreenUnit(String screenUnit) {
		this.screenUnit = screenUnit;
	}

	public String getScreenService() {
		return screenService;
	}

	public void setScreenService(String screenService) {
		this.screenService = screenService;
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
		return "StatisDissaQuestionDateVO [listStatisQuestionDTO=" + listStatisQuestionDTO + ", screenUnit="
				+ screenUnit + ", screenService=" + screenService + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", timeType=" + timeType + "]";
	}

	public List<StatisDissaQuestionDateDTO> getListStatisQuestionDTO() {
		return listStatisQuestionDTO;
	}

	public void setListStatisQuestionDTO(List<StatisDissaQuestionDateDTO> listStatisQuestionDTO) {
		this.listStatisQuestionDTO = listStatisQuestionDTO;
	}

	public StatisDissaQuestionDateVO() {
		super();
	}

}
