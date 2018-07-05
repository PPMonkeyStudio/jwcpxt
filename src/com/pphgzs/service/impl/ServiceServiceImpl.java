package com.pphgzs.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.pphgzs.dao.ServiceDao;
import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_service_instance;
import com.pphgzs.domain.DO.jwcpxt_user;
import com.pphgzs.domain.DTO.ServiceDefinitionDTO;
import com.pphgzs.domain.DTO.ServiceInstanceDTO;
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
	public boolean distribution_judge(String serviceInstanceID, String serviceInstanceJudge) {

		jwcpxt_service_instance serviceInstanceOld = serviceDao
				.get_serviceInstance_byServiceInstanceID(serviceInstanceID);
		if (serviceInstanceOld == null) {
			return false;
		}
		//
		serviceInstanceOld.setService_instance_judge(serviceInstanceJudge);
		//
		if (serviceDao.update_serviceInstance(serviceInstanceOld)) {
			return true;
		}
		return false;

	}

	@Override
	public boolean update_serviceDefinition(jwcpxt_service_definition serviceDefinitionNew) {
		jwcpxt_service_definition serviceDefinitionOld = serviceDao
				.get_serviceDefinition_byServiceDefinitionID(serviceDefinitionNew.getJwcpxt_service_definition_id());
		if (serviceDefinitionOld == null) {
			return false;
		}
		// 修改描述
		serviceDefinitionOld.setService_definition_describe(serviceDefinitionNew.getService_definition_describe());
		// 修改比例
		serviceDefinitionOld.setService_definition_sampling_proportion(
				serviceDefinitionNew.getService_definition_sampling_proportion());
		if (serviceDao.update_serviceDefinition(serviceDefinitionOld)) {
			return true;
		}
		return false;
	}

	@Override
	public jwcpxt_service_definition get_serviceDefinition_byServiceDefinitionID(String serviceDefinitionID) {
		return serviceDao.get_serviceDefinition_byServiceDefinitionID(serviceDefinitionID);
	}

	@Override
	public List<jwcpxt_service_client> list_client_byServiceInstanceID(String serviceInstanceID) {
		return serviceDao.list_client_byServiceInstanceID(serviceInstanceID);
	}

	@Override
	public List<ServiceInstanceDTO> list_serviceInstanceDTO_byServiceDefinitionID(String serviceDefinitionID) {

		// 通过业务定义ID获取所有相关的业务实例
		List<jwcpxt_service_instance> serviceInstanceList = serviceDao
				.list_serviceInstance_byServiceDefinitionID(serviceDefinitionID);
		//
		List<ServiceInstanceDTO> serviceInstanceDTOList = list_ServiceInstanceDTO_byServiceInstanceList(
				serviceInstanceList);
		//
		return serviceInstanceDTOList;
	}

	@Override
	public List<ServiceInstanceDTO> list_ServiceInstanceDTO_byServiceInstanceList(
			List<jwcpxt_service_instance> serviceInstanceList) {

		List<ServiceInstanceDTO> serviceInstanceDTOList = new ArrayList<ServiceInstanceDTO>();

		for (jwcpxt_service_instance serviceInstance : serviceInstanceList) {
			ServiceInstanceDTO serviceInstanceDTO = new ServiceInstanceDTO();
			//
			serviceInstanceDTO = get_serviceInstanceDTO_byServiceInstanceID(
					serviceInstance.getJwcpxt_service_instance_id());
			//
			serviceInstanceDTOList.add(serviceInstanceDTO);

		}

		return serviceInstanceDTOList;
	}

	@Override
	public ServiceInstanceDTO get_serviceInstanceDTO_byServiceInstanceID(String serviceInstanceID) {

		ServiceInstanceDTO serviceInstanceDTO = new ServiceInstanceDTO();
		// 获取业务实例
		jwcpxt_service_instance serviceInstance = serviceDao.get_serviceInstance_byServiceInstanceID(serviceInstanceID);
		serviceInstanceDTO.setServiceInstance(serviceInstance);
		// 获取业务定义DTO
		ServiceDefinitionDTO serviceDefinitionDTO = serviceDao.get_serviceDefinitionDTO_byServiceDefinitionID(
				serviceInstance.getService_instance_service_definition());
		serviceInstanceDTO.setServiceDefinitionDTO(serviceDefinitionDTO);
		// 获取分配的人
		jwcpxt_user judge = userService.get_user_byUserID(serviceInstance.getService_instance_judge());
		serviceInstanceDTO.setJudge(judge);
		// 获取所有当事人列表
		List<jwcpxt_service_client> serviceClientList = serviceDao
				.list_serviceClient_byServiceInstanceID(serviceInstance.getJwcpxt_service_instance_id());
		serviceInstanceDTO.setServiceClientList(serviceClientList);
		//
		return serviceInstanceDTO;
	}

	@Override
	public ServiceDefinitionDTO get_serviceDefinitionDTO_byServiceDefinitionID(String serviceDefinitionID) {
		return serviceDao.get_serviceDefinitionDTO_byServiceDefinitionID(serviceDefinitionID);
	}
	/*
	 * 
	 */

}
