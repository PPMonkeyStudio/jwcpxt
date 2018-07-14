package com.pphgzs.service;

import java.util.List;

import com.pphgzs.domain.DTO.ServiceGradeDTO;
import com.pphgzs.domain.VO.StatisticsVO;

public interface StatisticsService {

	StatisticsVO getGradeByCondition(String[] unitIds, String searchTimeStart, String searchTimeEnd,
			List<ServiceGradeDTO> serviceGradeDTOList);

}
