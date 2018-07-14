package com.pphgzs.domain.VO;

import java.util.List;

import com.pphgzs.domain.DTO.UnitHaveServiceGradeDTO;

//统计VO表
public class StatisticsVO {
	private List<UnitHaveServiceGradeDTO> unitHaveServiceGradeDTOList;

	public List<UnitHaveServiceGradeDTO> getUnitHaveServiceGradeDTOList() {
		return unitHaveServiceGradeDTOList;
	}

	public void setUnitHaveServiceGradeDTOList(List<UnitHaveServiceGradeDTO> unitHaveServiceGradeDTOList) {
		this.unitHaveServiceGradeDTOList = unitHaveServiceGradeDTOList;
	}

}
