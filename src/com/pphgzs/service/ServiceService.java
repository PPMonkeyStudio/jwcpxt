package com.pphgzs.service;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.VO.ServiceDefinitionVO;

public interface ServiceService {

	ServiceDefinitionVO get_serviceDefinitionVO(ServiceDefinitionVO serviceDefinitionVO);

	boolean save_serviceDefinition(jwcpxt_service_definition serviceDefinition);

	boolean update_serviceDefinition(jwcpxt_service_definition serviceDefinition);

	jwcpxt_service_definition get_serviceDefinition_byServiceDefinitionID(String serviceDefinitionID);

	List<jwcpxt_service_client> list_client_byServiceInstanceID(String serviceInstanceID);

}
