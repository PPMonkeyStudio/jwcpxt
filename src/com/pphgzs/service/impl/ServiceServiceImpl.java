package com.pphgzs.service.impl;

import java.util.List;

import com.pphgzs.dao.ServiceDao;
import com.pphgzs.domain.DTO.ServiceDefinitionDTO;
import com.pphgzs.domain.VO.ServiceDefinitionVO;
import com.pphgzs.service.ServiceService;
import com.pphgzs.service.UnitService;
import com.pphgzs.service.UserService;

public class ServiceServiceImpl implements ServiceService {

	private ServiceDao serviceDao;

	private UnitService unitService;

	private UserService userService;

	public ServiceDao getServiceDao() {
		return serviceDao;
	}

	public void setServiceDao(ServiceDao serviceDao) {
		this.serviceDao = serviceDao;
	}

	public UnitService getUnitService() {
		return unitService;
	}

	public void setUnitService(UnitService unitService) {
		this.unitService = unitService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public ServiceDefinitionVO get_serviceDefinitionVO(ServiceDefinitionVO serviceDefinitionVO) {
		List<ServiceDefinitionDTO> serviceDefinitionDTOList = serviceDao
				.list_serviceDefinitionDTO_byUserVO(serviceDefinitionVO);
		serviceDefinitionVO.setServiceDefinitionDTOList(serviceDefinitionDTOList);
		// 总记录数
		serviceDefinitionVO
				.setTotalCount(serviceDao.get_serviceDefinitionTotalCount_byServiceDefinitionVO(serviceDefinitionVO));
		// 总页数
		serviceDefinitionVO
				.setTotalPage(((serviceDefinitionVO.getTotalCount() - 1) / serviceDefinitionVO.getPageSize()) + 1);
		//
		return serviceDefinitionVO;
	}

}
