package com.pphgzs.domain.DTO;

import java.util.List;

/**
 * 
 * @author JXX
 *
 */
public class StatisDissaQuestionDateDTO {
	private String dateScale;
	private List<StatisQuestionDTO> listStatisQuestionDTO;

	public List<StatisQuestionDTO> getListStatisQuestionDTO() {
		return listStatisQuestionDTO;
	}

	public void setListStatisQuestionDTO(List<StatisQuestionDTO> listStatisQuestionDTO) {
		this.listStatisQuestionDTO = listStatisQuestionDTO;
	}

	public String getDateScale() {
		return dateScale;
	}

	@Override
	public String toString() {
		return "StatisDissaQuestionDateDTO [dateScale=" + dateScale + ", listStatisQuestionDTO=" + listStatisQuestionDTO
				+ "]";
	}

	public void setDateScale(String dateScale) {
		this.dateScale = dateScale;
	}

	public StatisDissaQuestionDateDTO() {
		super();
	}

}
