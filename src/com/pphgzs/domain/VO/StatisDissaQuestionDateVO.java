package com.pphgzs.domain.VO;

import java.util.List;

import com.pphgzs.domain.DTO.QuestionOptionAnswerDTO;
import com.pphgzs.domain.DTO.StatisDissaQuestionDateDTO;

/*
 * 
 */
public class StatisDissaQuestionDateVO {
	private List<StatisDissaQuestionDateDTO> listStatisDissaDTO;
	private List<QuestionOptionAnswerDTO> listQuestionOptionDTO;
	// 单位
	private String screenUnit = "";
	private String screenService = "";
	private String startTime = "";
	private String endTime = "";
	//
	private String timeType = "1";

	//
	public List<StatisDissaQuestionDateDTO> getListStatisDissaDTO() {
		return listStatisDissaDTO;
	}

	public void setListStatisDissaDTO(List<StatisDissaQuestionDateDTO> listStatisDissaDTO) {
		this.listStatisDissaDTO = listStatisDissaDTO;
	}

	public List<QuestionOptionAnswerDTO> getListQuestionOptionDTO() {
		return listQuestionOptionDTO;
	}

	public void setListQuestionOptionDTO(List<QuestionOptionAnswerDTO> listQuestionOptionDTO) {
		this.listQuestionOptionDTO = listQuestionOptionDTO;
	}

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
		return "StatisDissaQuestionDateVO [listStatisDissaDTO=" + listStatisDissaDTO + ", listQuestionOptionDTO="
				+ listQuestionOptionDTO + ", screenUnit=" + screenUnit + ", screenService=" + screenService
				+ ", startTime=" + startTime + ", endTime=" + endTime + ", timeType=" + timeType + "]";
	}

	public StatisDissaQuestionDateVO(List<StatisDissaQuestionDateDTO> listStatisDissaDTO,
			List<QuestionOptionAnswerDTO> listQuestionOptionDTO, String screenUnit, String screenService,
			String startTime, String endTime, String timeType) {
		super();
		this.listStatisDissaDTO = listStatisDissaDTO;
		this.listQuestionOptionDTO = listQuestionOptionDTO;
		this.screenUnit = screenUnit;
		this.screenService = screenService;
		this.startTime = startTime;
		this.endTime = endTime;
		this.timeType = timeType;
	}

	public StatisDissaQuestionDateVO() {
		super();
	}

}
