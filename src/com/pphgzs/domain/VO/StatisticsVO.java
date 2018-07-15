package com.pphgzs.domain.VO;

import java.util.List;

import com.pphgzs.domain.DTO.UnitHaveServiceGradeDTO;

//统计VO表
public class StatisticsVO {
	private List<UnitHaveServiceGradeDTO> unitHaveServiceGradeDTOList;

	private List<String> sheetHeadNameList;

	@Override
	public String toString() {
		return "StatisticsVO 【\nunitHaveServiceGradeDTOList=" + unitHaveServiceGradeDTOList + ", \nsheetHeadNameList="
				+ sheetHeadNameList + "\n】";
	}

	public List<String> getSheetHeadNameList() {
		return sheetHeadNameList;
	}

	public void setSheetHeadNameList(List<String> sheetHeadNameList) {
		this.sheetHeadNameList = sheetHeadNameList;
	}

	public List<UnitHaveServiceGradeDTO> getUnitHaveServiceGradeDTOList() {
		return unitHaveServiceGradeDTOList;
	}

	public void setUnitHaveServiceGradeDTOList(List<UnitHaveServiceGradeDTO> unitHaveServiceGradeDTOList) {
		this.unitHaveServiceGradeDTOList = unitHaveServiceGradeDTOList;
	}

}
