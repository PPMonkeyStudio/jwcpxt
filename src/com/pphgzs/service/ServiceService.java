package com.pphgzs.service;

import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.VO.ServiceDefinitionVO;

public interface ServiceService {

	ServiceDefinitionVO get_serviceDefinitionVO(ServiceDefinitionVO serviceDefinitionVO);

	boolean save_serviceDefinition(jwcpxt_service_definition serviceDefinition);

}
