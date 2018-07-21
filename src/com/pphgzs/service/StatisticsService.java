package com.pphgzs.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.pphgzs.domain.DTO.ServiceGradeDTO;
import com.pphgzs.domain.VO.StatisDissaServiceDateVO;
import com.pphgzs.domain.VO.StatisDissatiDateVO;
import com.pphgzs.domain.VO.StatisticsDissatisfiedDateCountVO;
import com.pphgzs.domain.VO.StatisticsDissatisfiedDayDataVO;
import com.pphgzs.domain.VO.StatisticsVO;

public interface StatisticsService {

	StatisticsVO getGradeByCondition(String[] unitIds, String searchTimeStart, String searchTimeEnd,
			List<ServiceGradeDTO> serviceGradeDTOList);

	void writeStatisticsExcel(StatisticsVO statisticsVO, HSSFWorkbook wb);

	StatisticsDissatisfiedDayDataVO get_StatisticsDissatisfiedDayDataVO(
			StatisticsDissatisfiedDayDataVO statisticsDissatisfiedDayDataVO);

	StatisticsDissatisfiedDateCountVO get_StatisticsDissatisfiedDateCountVO(
			StatisticsDissatisfiedDateCountVO statisticsDissatisfiedDateCountVO);

	/**
	 * 获取时间结点里所有推送的选项描述，及其数量
	 * 
	 * @param statisDissatiDateVO
	 * @return
	 */
	public StatisDissatiDateVO get_statisDissatiDateVO(StatisDissatiDateVO statisDissatiDateVO);

	/**
	 * 获取业务不满意分布
	 * 
	 * @param statisDissaServiceDateVO
	 * @return
	 */
	public StatisDissaServiceDateVO get_statisDissaServiceDateVO(StatisDissaServiceDateVO statisDissaServiceDateVO);

}
