package com.pphgzs.dao;

import com.pphgzs.domain.DTO.ServiceGradeDTO;

public interface StatisticsDao {
	// 统计一个单位下的一个业务的分数
	// serviceGradeDTO中放了一个所需要统计的业务id以及这项业务所占得分数，unitId放的是一个单位，后面两个是需要统计的时间区间
	int geteStatisticsGrade(ServiceGradeDTO serviceGradeDTO, String unitId, String searchTimeStart,
			String searchTimeEnd);

}
