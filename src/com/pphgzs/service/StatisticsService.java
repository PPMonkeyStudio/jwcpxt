package com.pphgzs.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.pphgzs.domain.DTO.ServiceGradeDTO;
import com.pphgzs.domain.VO.ClientAttentionServiceVO;
import com.pphgzs.domain.VO.DeductMarkInfoVO;
import com.pphgzs.domain.VO.DissatisfiedVO;
import com.pphgzs.domain.VO.MonthDayMountVO;
import com.pphgzs.domain.VO.ReturnVisitVO;
import com.pphgzs.domain.VO.StatisDissaQuestionDateVO;
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

	/**
	 * 某业务不满意分布情况
	 * 
	 * @param statisDissaQuestionDateVO
	 * @return
	 */
	public StatisDissaQuestionDateVO get_statisDissaQuestionDateVO(StatisDissaQuestionDateVO statisDissaQuestionDateVO);

	/**
	 * 满意业务分布情况
	 * 
	 * @param statisDissaServiceDateVO
	 * @return
	 */
	public StatisDissaServiceDateVO get_statisQuestionDataVO(StatisDissaServiceDateVO statisDissaServiceDateVO);

	/**
	 * 获取返回的VO
	 * 
	 * @param returnVisitVO
	 * @return
	 */
	public ReturnVisitVO getUserCountVO(ReturnVisitVO returnVisitVO);

	/**
	 * 群众最关注的业务(满意)
	 * 
	 * @param clientAttentionServiceVO
	 * @return
	 */
	public ClientAttentionServiceVO get_clientAttentionService(ClientAttentionServiceVO clientAttentionServiceVO);

	/**
	 * 群众最不满意的项目
	 * 
	 * @param dissatisfiedVO
	 * @return
	 */
	public DissatisfiedVO get_clientDissatisfiedService(DissatisfiedVO dissatisfiedVO);

	/**
	 * 获取对应的数量
	 * 
	 * @return
	 */
	public MonthDayMountVO get_dataMonthDayMount(MonthDayMountVO monthDayMountVO);

	public DeductMarkInfoVO get_DeductMarkInfo(DeductMarkInfoVO deductMarkInfoVO);

}
