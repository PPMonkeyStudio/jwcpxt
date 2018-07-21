package com.pphgzs.domain.DTO;

import java.util.List;

/**
 * 
 * @author JXX
 *
 */
public class StatisticsDissatisfiedDateDTO {
	private String dateScale;
	private List<StatisticsDissatisfiedOptionDTO> listDissaOptionDTO;

	public String getDateScale() {
		return dateScale;
	}

	public void setDateScale(String dateScale) {
		this.dateScale = dateScale;
	}

	public List<StatisticsDissatisfiedOptionDTO> getListDissaOptionDTO() {
		return listDissaOptionDTO;
	}

	public void setListDissaOptionDTO(List<StatisticsDissatisfiedOptionDTO> listDissaOptionDTO) {
		this.listDissaOptionDTO = listDissaOptionDTO;
	}

	@Override
	public String toString() {
		return "StatisticsDissatisfiedDateDTO [dateScale=" + dateScale + ", listDissaOptionDTO=" + listDissaOptionDTO
				+ "]";
	}

	public StatisticsDissatisfiedDateDTO(String dateScale, List<StatisticsDissatisfiedOptionDTO> listDissaOptionDTO) {
		super();
		this.dateScale = dateScale;
		this.listDissaOptionDTO = listDissaOptionDTO;
	}

	public StatisticsDissatisfiedDateDTO() {
		super();
	}

}
