package com.pphgzs.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.pphgzs.domain.DTO.ServiceGradeDTO;
import com.pphgzs.domain.VO.StatisticsDissatisfiedDayDataVO;
import com.pphgzs.domain.VO.StatisticsVO;

public interface StatisticsService {

	StatisticsVO getGradeByCondition(String[] unitIds, String searchTimeStart, String searchTimeEnd,
			List<ServiceGradeDTO> serviceGradeDTOList);

	void writeStatisticsExcel(StatisticsVO statisticsVO, HSSFWorkbook wb);

	StatisticsDissatisfiedDayDataVO get_StatisticsDissatisfiedDayDataVO(
			StatisticsDissatisfiedDayDataVO statisticsDissatisfiedDayDataVO);

}
