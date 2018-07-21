package com.pphgzs.dao;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_option;
import com.pphgzs.domain.DO.jwcpxt_question;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DTO.QuestionOptionAnswerDTO;
import com.pphgzs.domain.DTO.ServiceGradeDTO;
import com.pphgzs.domain.DTO.StatisticsDissatisfiedOptionDTO;
import com.pphgzs.domain.VO.StatisDissaQuestionDateVO;
import com.pphgzs.domain.VO.StatisDissaServiceDateVO;
import com.pphgzs.domain.VO.StatisDissatiDateVO;

public interface StatisticsDao {

	// 统计一个单位下的一个业务的分数
	// serviceGradeDTO中放了一个所需要统计的业务id以及这项业务所占得分数，unitId放的是一个单位，后面两个是需要统计的时间区间
	double geteStatisticsGrade(ServiceGradeDTO serviceGradeDTO, String unitId, String searchTimeStart,
			String searchTimeEnd);

	double geteStatisticsGrade_byFatherUnit(ServiceGradeDTO serviceGradeDTO, String fatherUnitId,
			String searchTimeStart, String searchTimeEnd);

	int get_dayNum_byServiceDefinitionIDAndDate(String jwcpxt_service_definition_id, String jwcpxt_unit_id, String day);

	int get_dayNum_byServiceDefinitionIDAndDate_byFatherUnit(String jwcpxt_service_definition_id, String jwcpxt_unit_id,
			String format);

	int get_StatisticsDissatisfiedDateCount(String jwcpxt_service_definition_id, String jwcpxt_unit_id,
			String startTime, String endTime);

	int get_StatisticsDissatisfiedDateCount_byFatherUnit(String jwcpxt_service_definition_id, String jwcpxt_unit_id,
			String startTime, String endTime);

	public List<String> get_pushOption_byTime(StatisDissatiDateVO statisDissatiDateVO);

	public int get_countOption_byTime(StatisDissatiDateVO statisDissatiDateVO, String string, String string2,
			String option);

	/**
	 * 获取对应时间段里面的业务
	 * 
	 * @param statisDissaServiceDateVO
	 * @param string
	 * @param string2
	 * @return
	 */
	public List<jwcpxt_service_definition> get_pushService_byTime(StatisDissaServiceDateVO statisDissaServiceDateVO);

	/**
	 * 获取对应时间段里面的业务量
	 * 
	 * @param statisDissaServiceDateVO
	 * @param string
	 * @param string2
	 * @return
	 */
	public int statisticsDaoget_countService_byTime(StatisDissaServiceDateVO statisDissaServiceDateVO, String startTime,
			String endTime, jwcpxt_service_definition serviceDefinition);

	/**
	 * 根据VO获取该业务的所有问题以及选项
	 * 
	 * @param statisDissaQuestionDateVO
	 * @return
	 */
	public List<QuestionOptionAnswerDTO> get_pushQuestionOption(StatisDissaQuestionDateVO statisDissaQuestionDateVO);

	/**
	 * 根据VO获取改业务的所有不满意问题数量
	 * 
	 * @param statisDissaQuestionDateVO
	 * @param string
	 * @param string2
	 * @param question
	 * @return
	 */
	public int get_countStatisDateDTO(StatisDissaQuestionDateVO statisDissaQuestionDateVO, String string,
			String string2, QuestionOptionAnswerDTO questionOptionAnswerDTO);

}
