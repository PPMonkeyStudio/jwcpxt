package com.pphgzs.dao;

import java.util.List;

import com.pphgzs.domain.DTO.ServiceDefinitionDTO;
import com.pphgzs.domain.VO.ServiceDefinitionVO;

public interface ServiceDao {

	List<ServiceDefinitionDTO> list_serviceDefinitionDTO_byUserVO(ServiceDefinitionVO serviceDefinitionVO);

	int get_serviceDefinitionTotalCount_byServiceDefinitionVO(ServiceDefinitionVO serviceDefinitionVO);

}
