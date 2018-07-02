package com.pphgzs.service.impl;

import java.util.List;

import com.pphgzs.dao.ServiceDao;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DTO.ServiceDefinitionDTO;
import com.pphgzs.domain.VO.ServiceDefinitionVO;
import com.pphgzs.service.ServiceService;
import com.pphgzs.service.UnitService;
import com.pphgzs.service.UserService;
import com.pphgzs.util.TimeUtil;
import com.pphgzs.util.uuidUtil;

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

	@Override
	public boolean save_serviceDefinition(jwcpxt_service_definition serviceDefinition) {
		if (serviceDao.ifExist_serviceDefinition_byServiceDefinitionDescribe(
				serviceDefinition.getService_definition_describe())) {
			return false;
		} else {
			serviceDefinition.setJwcpxt_service_definition_id(uuidUtil.getUuid());
			// 时间初始化
			String time = TimeUtil.getStringSecond();
			serviceDefinition.setService_definition_gmt_create(time);
			serviceDefinition.setService_definition_gmt_modified(time);
			// 存入
			serviceDao.save_serviceDefinition(serviceDefinition);
			return true;
		}

	}

	@Override
	public boolean update_serviceDefinition(jwcpxt_service_definition serviceDefinitionNew) {
		jwcpxt_service_definition serviceDefinitionOld = serviceDao
				.get_serviceDefinition_byServiceDefinitionID(serviceDefinitionNew.getJwcpxt_service_definition_id());
		if (serviceDefinitionOld == null) {
			return false;
		}
		serviceDefinitionOld.setService_definition_describe(serviceDefinitionNew.getService_definition_describe());
		if (serviceDao.update_serviceDefinition(serviceDefinitionOld)) {
			return true;
		}
		return false;
	}

	@Override
	public jwcpxt_service_definition get_serviceDefinition_byserviceDefinitionID(String serviceDefinitionID) {
		return serviceDao.get_serviceDefinition_byServiceDefinitionID(serviceDefinitionID);
	}
	/*
	 * 
	 */
}
