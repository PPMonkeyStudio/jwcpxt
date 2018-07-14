package com.pphgzs.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.pphgzs.dao.StatisticsDao;
import com.pphgzs.dao.UnitDao;
import com.pphgzs.domain.DTO.ServiceGradeBelongUnitDTO;
import com.pphgzs.domain.DTO.ServiceGradeDTO;
import com.pphgzs.domain.DTO.UnitHaveServiceGradeDTO;
import com.pphgzs.domain.VO.StatisticsVO;
import com.pphgzs.service.ServiceService;
import com.pphgzs.service.StatisticsService;

public class StatisticsServiceImpl implements StatisticsService {
	private StatisticsDao statisticsDao;
	private UnitDao unitDao;
	private ServiceService serviceService;

	@Override
	public StatisticsVO getGradeByCondition(String[] unitIds, String searchTimeStart, String searchTimeEnd,
			List<ServiceGradeDTO> serviceGradeDTOList) {
		// TODO Auto-generated method stub
		StatisticsVO statisticsVO = new StatisticsVO();
		List<UnitHaveServiceGradeDTO> unitHaveServiceGradeDTOList = new ArrayList<UnitHaveServiceGradeDTO>();
		UnitHaveServiceGradeDTO unitHaveServiceGradeDTO;
		for (int i = 0; i < unitIds.length; i++) {
			// 创建一个单位
			unitHaveServiceGradeDTO = new UnitHaveServiceGradeDTO();
			unitHaveServiceGradeDTO.setUnit(unitDao.get_unitDO_byID(unitIds[i]));
			// 创建一个业务分数list
			List<ServiceGradeBelongUnitDTO> serviceGradeBelongUnitDTOList = new ArrayList<ServiceGradeBelongUnitDTO>();
			// 遍历需要统计的业务，查询这项业务分数
			for (ServiceGradeDTO serviceGradeDTO : serviceGradeDTOList) {
				// 创建一个业务分数DTO
				ServiceGradeBelongUnitDTO serviceGradeBelongUnitDTO = new ServiceGradeBelongUnitDTO();
				// 将业务DO放入到DTO中
				serviceGradeBelongUnitDTO.setServiceDefinition(
						serviceService.get_serviceDefinitionDO_byServiceDefinitionID(serviceGradeDTO.getService_id()));
				// 统计这个单位下这个业务所得分
				int statisticsGrade = statisticsDao.geteStatisticsGrade(serviceGradeDTO, unitIds[i], searchTimeStart,
						searchTimeEnd);
				// 将所得分放入到DTO中
				serviceGradeBelongUnitDTO.setGrade(statisticsGrade);
				// 将一个业务及他的分数的DTO放入到DTO列表中
				serviceGradeBelongUnitDTOList.add(serviceGradeBelongUnitDTO);
			}
			// 这个单位下所有统计的业务DTO放入到单位列表中
			unitHaveServiceGradeDTOList.add(unitHaveServiceGradeDTO);
		}
		// 将统计完的所有单位放到VO中
		statisticsVO.setUnitHaveServiceGradeDTOList(unitHaveServiceGradeDTOList);
		return statisticsVO;
	}

	public StatisticsDao getStatisticsDao() {
		return statisticsDao;
	}

	public void setStatisticsDao(StatisticsDao statisticsDao) {
		this.statisticsDao = statisticsDao;
	}

	public UnitDao getUnitDao() {
		return unitDao;
	}

	public void setUnitDao(UnitDao unitDao) {
		this.unitDao = unitDao;
	}

	public ServiceService getServiceService() {
		return serviceService;
	}

	public void setServiceService(ServiceService serviceService) {
		this.serviceService = serviceService;
	}

}
