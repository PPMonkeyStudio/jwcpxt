package com.pphgzs.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.pphgzs.dao.ServiceDao;
import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_service_distribution;
import com.pphgzs.domain.DO.jwcpxt_service_instance;
import com.pphgzs.domain.DO.jwcpxt_unit;
import com.pphgzs.domain.DO.jwcpxt_user;
import com.pphgzs.domain.DTO.ServiceDefinitionDTO;
import com.pphgzs.domain.DTO.ServiceDistributionDTO;
import com.pphgzs.domain.DTO.ServiceInstanceDTO;
import com.pphgzs.domain.VO.ServiceDefinitionVO;
import com.pphgzs.domain.VO.ServiceDistributionVO;
import com.pphgzs.domain.VO.ServiceInstanceVO;
import com.pphgzs.service.ServiceService;
import com.pphgzs.service.UnitService;
import com.pphgzs.service.UserService;
import com.pphgzs.util.TimeUtil;
import com.pphgzs.util.uuidUtil;

public class ServiceServiceImpl implements ServiceService {

	private ServiceDao serviceDao;

	private UnitService unitService;

	private UserService userService;

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

	public ServiceDao getServiceDao() {
		return serviceDao;
	}

	public void setServiceDao(ServiceDao serviceDao) {
		this.serviceDao = serviceDao;
	}
	/*
	 * 
	 * 
	 */

	@Override
	public ServiceDefinitionVO get_serviceDefinitionVO() {

		ServiceDefinitionVO serviceDefinitionVO = new ServiceDefinitionVO();
		List<ServiceDefinitionDTO> serviceDefinitionDTOList = new ArrayList<ServiceDefinitionDTO>();
		List<jwcpxt_service_definition> serviceDefinitionList = serviceDao.list_serviceDefinition_all();

		for (jwcpxt_service_definition serviceDefinition : serviceDefinitionList) {

			serviceDefinitionDTOList.add(get_serviceDefinitionDTO_byServiceDefinitionID(
					serviceDefinition.getJwcpxt_service_definition_id()));
		}
		serviceDefinitionVO.setServiceDefinitionDTOList(serviceDefinitionDTOList);
		serviceDefinitionVO.setTotalRecords(serviceDao.get_serviceDefinitionTotalRecords());

		return serviceDefinitionVO;
	}

	@Override
	public ServiceInstanceVO get_serviceInstanceVO() {
		ServiceInstanceVO serviceInstanceVO = new ServiceInstanceVO();
		List<ServiceInstanceDTO> serviceInstanceDTOList = new ArrayList<ServiceInstanceDTO>();
		List<jwcpxt_service_instance> serviceInstanceList = serviceDao.list_serviceInstance_all();
		// 根据实例查询实例DTO
		for (jwcpxt_service_instance serviceInstance : serviceInstanceList) {
			ServiceInstanceDTO serviceInstanceDTO = get_serviceInstanceDTO_byServiceInstanceID(
					serviceInstance.getJwcpxt_service_instance_id());
			serviceInstanceDTOList.add(serviceInstanceDTO);
		}
		serviceInstanceVO.setServiceInstanceDTOList(serviceInstanceDTOList);
		serviceInstanceVO.setTotalRecords(serviceDao.get_serviceInstanceTotalRecords());
		return serviceInstanceVO;
	}

	@Override
	public ServiceDistributionVO get_serviceDistributionVO() {
		ServiceDistributionVO serviceDistributionVO = new ServiceDistributionVO();
		List<ServiceDistributionDTO> serviceDistributionDTOList = new ArrayList<ServiceDistributionDTO>();
		List<jwcpxt_service_distribution> serviceDistributionList = serviceDao.list_serviceDistribution_all();
		for (jwcpxt_service_distribution serviceDistribution : serviceDistributionList) {
			ServiceDistributionDTO serviceDistributionDTO = get_serviceDistributionDTO_byServiceDistributionID(
					serviceDistribution.getJwcpxt_service_distribution_id());
			serviceDistributionDTOList.add(serviceDistributionDTO);
		}
		serviceDistributionVO.setServiceDistributionDTOList(serviceDistributionDTOList);
		serviceDistributionVO.setTotalRecords(serviceDao.get_serviceDistributionTotalRecords());
		return serviceDistributionVO;
	}

	@Override
	public ServiceDefinitionDTO get_serviceDefinitionDTO_byServiceDefinitionID(String serviceDefinitionID) {
		ServiceDefinitionDTO serviceDefinitionDTO = new ServiceDefinitionDTO();
		//
		jwcpxt_service_definition serviceDefinition = serviceDao.get_serviceDefinition_byID(serviceDefinitionID);
		serviceDefinitionDTO.setServiceDefinition(serviceDefinition);
		//
		jwcpxt_unit unit = unitService.get_unit_byUnitID(serviceDefinition.getService_definition_unit());
		serviceDefinitionDTO.setUnit(unit);
		//
		return serviceDefinitionDTO;
	}

	@Override
	public List<ServiceDefinitionDTO> list_serviceDefinitionDTO_all() {
		List<ServiceDefinitionDTO> serviceDefinitionDTOList = new ArrayList<ServiceDefinitionDTO>();
		List<jwcpxt_service_definition> serviceDefinitionList = serviceDao.list_serviceDefinition_all();
		for (jwcpxt_service_definition serviceDefinition : serviceDefinitionList) {
			ServiceDefinitionDTO serviceDefinitionDTO = get_serviceDefinitionDTO_byServiceDefinitionID(
					serviceDefinition.getJwcpxt_service_definition_id());
			serviceDefinitionDTOList.add(serviceDefinitionDTO);
		}
		return serviceDefinitionDTOList;
	}

	@Override
	public ServiceInstanceDTO get_serviceInstanceDTO_byServiceInstanceID(String serviceInstanceID) {
		ServiceInstanceDTO serviceInstanceDTO = new ServiceInstanceDTO();
		// 根据ID获取实例
		jwcpxt_service_instance serviceInstance = serviceDao.get_serviceInstance_byID(serviceInstanceID);
		serviceInstanceDTO.setServiceInstance(serviceInstance);
		// 实例中的定义DTO
		ServiceDefinitionDTO serviceDefinitionDTO = get_serviceDefinitionDTO_byServiceDefinitionID(
				serviceInstanceDTO.getServiceInstance().getService_instance_service_definition());
		serviceInstanceDTO.setServiceDefinitionDTO(serviceDefinitionDTO);
		// 实例的当事人列表
		List<jwcpxt_service_client> serviceClientList = serviceDao
				.list_serviceClient_byInstance(serviceInstance.getJwcpxt_service_instance_id());
		serviceInstanceDTO.setServiceClientList(serviceClientList);
		//
		return serviceInstanceDTO;
	}

	@Override
	public ServiceDistributionDTO get_serviceDistributionDTO_byServiceDistributionID(String serviceDistributionID) {
		ServiceDistributionDTO serviceDistributionDTO = new ServiceDistributionDTO();
		// 分配
		jwcpxt_service_distribution serviceDistribution = serviceDao
				.get_serviceDistribution_byID(serviceDistributionID);
		serviceDistributionDTO.setServiceDistribution(serviceDistribution);
		// 分配中的测评员
		jwcpxt_user user = userService.get_user_byUserID(serviceDistribution.getService_distribution_judge());
		serviceDistributionDTO.setUser(user);
		// 分配中的业务实例
		ServiceInstanceDTO serviceInstanceDTO = get_serviceInstanceDTO_byServiceInstanceID(
				serviceDistribution.getService_distribution_service_instance());
		serviceDistributionDTO.setServiceInstanceDTO(serviceInstanceDTO);
		//
		return serviceDistributionDTO;
	}

	@Override
	public boolean add_serviceDefinition(jwcpxt_service_definition serviceDefinition) {
		serviceDefinition.setJwcpxt_service_definition_id(uuidUtil.getUuid());
		String time = TimeUtil.getStringSecond();
		serviceDefinition.setService_definition_gmt_create(time);
		serviceDefinition.setService_definition_gmt_modified(time);
		if (serviceDao.add_serviceDefinition(serviceDefinition)) {
			return true;
		} else {
			return false;
		}
	}

}
