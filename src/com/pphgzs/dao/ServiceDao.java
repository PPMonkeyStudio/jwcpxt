package com.pphgzs.dao;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DTO.ServiceDefinitionDTO;
import com.pphgzs.domain.VO.ServiceDefinitionVO;

public interface ServiceDao {

	List<ServiceDefinitionDTO> list_serviceDefinitionDTO_byUserVO(ServiceDefinitionVO serviceDefinitionVO);

	int get_serviceDefinitionTotalCount_byServiceDefinitionVO(ServiceDefinitionVO serviceDefinitionVO);

	boolean ifExist_serviceDefinition_byServiceDefinitionDescribe(String service_definition_describe);

	boolean save_serviceDefinition(jwcpxt_service_definition serviceDefinition);

	jwcpxt_service_definition get_serviceDefinition_byServiceDefinitionID(String jwcpxt_service_definition_id);

	boolean update_serviceDefinition(jwcpxt_service_definition serviceDefinitionOld);

}
