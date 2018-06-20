package com.pphgzs.service;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DTO.ServiceDefinitionDTO;
import com.pphgzs.domain.DTO.ServiceDistributionDTO;
import com.pphgzs.domain.DTO.ServiceInstanceDTO;
import com.pphgzs.domain.VO.ServiceDefinitionVO;
import com.pphgzs.domain.VO.ServiceDistributionVO;
import com.pphgzs.domain.VO.ServiceInstanceVO;

public interface ServiceService {

	public ServiceDefinitionVO get_serviceDefinitionVO(ServiceDefinitionVO serviceDefinitionVO);

	public ServiceInstanceVO get_serviceInstanceVO();

	public ServiceDistributionVO get_serviceDistributionVO();

	public ServiceDefinitionDTO get_serviceDefinitionDTO_byServiceDefinitionID(String serviceDefinitionID);

	public ServiceDistributionDTO get_serviceDistributionDTO_byServiceDistributionID(String serviceDistributionID);

	public ServiceInstanceDTO get_serviceInstanceDTO_byServiceInstanceID(String serviceInstanceID);

	public List<ServiceDefinitionDTO> list_serviceDefinitionDTO_all();

	public boolean add_serviceDefinition(jwcpxt_service_definition serviceDefinition);

}
