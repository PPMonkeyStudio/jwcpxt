package com.pphgzs.service;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_service_grab;
import com.pphgzs.domain.DO.jwcpxt_service_instance;
import com.pphgzs.domain.DTO.ServiceDefinitionDTO;
import com.pphgzs.domain.DTO.ServiceInstanceDTO;
import com.pphgzs.domain.VO.ServiceDefinitionVO;
import com.pphgzs.domain.VO.ServiceInstanceVO;

public interface ServiceService {

	/*
	 * 
	 */

	public boolean save_serviceGrab(jwcpxt_service_grab serviceGrab);

	/**
	 * 
	 */

	/**
	 * 手动分配实例给测评人员
	 * 
	 * @param serviceInstanceID
	 * @param serviceInstanceJudge
	 * @return
	 */
	boolean distribution_judge(String serviceInstanceID, String serviceInstanceJudge);

	/**
	 * 自动分配任务实例的线程执行方法
	 */
	// void distribution_serviceInstance_auto();

	/**
	 * 随机分配给定数量的任务实例给测评人员
	 */
	// void
	// distributionRandom_serviceInstance_byNoJudgeAndNumAndServiceDefinitionIDAndDate(int
	// num,
	// String serviceDefinitionID, String date);

	jwcpxt_service_definition get_serviceDefinitionDO_byServiceDefinitionID(String serviceDefinitionID);

	ServiceDefinitionDTO get_serviceDefinitionDTO_byServiceDefinitionID(String serviceDefinitionID);

	ServiceDefinitionVO get_serviceDefinitionVO(ServiceDefinitionVO serviceDefinitionVO);

	/**
	 * 根据任务定义查出当天已分配的业务实例
	 * 
	 * @param serviceDefinitionID
	 * @return
	 */
	// int
	// get_serviceInstanceDistributionCount_byTodayAndServiceDefinitionID(String
	// serviceDefinitionID);

	/**
	 * 通过业务实例的ID获取业务实例的DTO
	 * 
	 * @param serviceInstanceID
	 * @return
	 */
	ServiceInstanceDTO get_serviceInstanceDTO_byServiceInstanceID(String serviceInstanceID);

	/**
	 * 根据任务定义查出当天所有的业务实例
	 * 
	 * @param serviceDefinitionID
	 * @return
	 */
	int get_serviceInstanceTotalCount_byTodayAndServiceDefinitionID(String serviceDefinitionID);

	ServiceInstanceVO get_serviceInstanceVO(ServiceInstanceVO serviceInstanceVO);

	List<jwcpxt_service_client> list_clientDO_byServiceInstanceID(String serviceInstanceID);

	List<jwcpxt_service_definition> list_serviceDefinitionDO_all();

	/**
	 * 在给定任务定义中，随机抽取给定数量的任务实例
	 */
	// List<jwcpxt_service_instance>
	// list_serviceInstance_byNoJudgeAndRandomAndNumAndServiceDefinitionIDAndDate(int
	// num,
	// String serviceDefinitionID, String date);

	List<ServiceInstanceDTO> list_serviceInstanceDTO_byServiceDefinitionID(String serviceDefinitionID);

	/**
	 * 根据业务实例DO列表获取业务实例DTO列表
	 * 
	 * @param serviceInstanceList
	 * @return
	 */
	List<ServiceInstanceDTO> list_ServiceInstanceDTO_byServiceInstanceList(
			List<jwcpxt_service_instance> serviceInstanceList);

	boolean save_serviceDefinition(jwcpxt_service_definition serviceDefinition);

	boolean update_serviceDefinition(jwcpxt_service_definition serviceDefinition);

	boolean update_serviceGrab(jwcpxt_service_grab serviceGrab);

	jwcpxt_service_grab get_serviceGrabDO_byServiceDefinitionID(String serviceDefinitionID);

	void grab_serviceInstance_auto();

	List<jwcpxt_service_instance> grab_serviceInstance_byServiceDefinitionID(String serviceDefinitionID);

	/**
	 * 获取业务定义list
	 * 
	 * @return
	 */
	public List<jwcpxt_service_definition> list_serviceDefinition();

}
