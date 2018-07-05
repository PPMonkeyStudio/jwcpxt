package com.pphgzs.service;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_service_instance;
import com.pphgzs.domain.DTO.ServiceDefinitionDTO;
import com.pphgzs.domain.DTO.ServiceInstanceDTO;
import com.pphgzs.domain.VO.ServiceDefinitionVO;

public interface ServiceService {

	ServiceDefinitionVO get_serviceDefinitionVO(ServiceDefinitionVO serviceDefinitionVO);

	boolean save_serviceDefinition(jwcpxt_service_definition serviceDefinition);

	boolean update_serviceDefinition(jwcpxt_service_definition serviceDefinition);

	jwcpxt_service_definition get_serviceDefinition_byServiceDefinitionID(String serviceDefinitionID);

	List<jwcpxt_service_client> list_client_byServiceInstanceID(String serviceInstanceID);

	List<ServiceInstanceDTO> list_serviceInstanceDTO_byServiceDefinitionID(String serviceDefinitionID);

	/**
	 * 根据业务实例DO列表获取业务实例DTO列表
	 * 
	 * @param serviceInstanceList
	 * @return
	 */
	List<ServiceInstanceDTO> list_ServiceInstanceDTO_byServiceInstanceList(
			List<jwcpxt_service_instance> serviceInstanceList);

	/**
	 * 通过业务实例的ID获取业务实例的DTO
	 * 
	 * @param serviceInstanceID
	 * @return
	 */
	ServiceInstanceDTO get_serviceInstanceDTO_byServiceInstanceID(String serviceInstanceID);

	ServiceDefinitionDTO get_serviceDefinitionDTO_byServiceDefinitionID(String serviceDefinitionID);

	boolean distribution_judge(String serviceInstanceID, String serviceInstanceJudge);

}
