package com.pphgzs.dao;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_service_grab;
import com.pphgzs.domain.DO.jwcpxt_service_instance;
import com.pphgzs.domain.DTO.ServiceDefinitionDTO;
import com.pphgzs.domain.VO.ServiceDefinitionVO;
import com.pphgzs.domain.VO.ServiceInstanceVO;

public interface ServiceDao {

	jwcpxt_service_definition get_serviceDefinition_byServiceDefinitionID(String jwcpxt_service_definition_id);

	ServiceDefinitionDTO get_serviceDefinitionDTO_byServiceDefinitionID(String serviceDefinitionID);

	int get_serviceDefinitionTotalCount_byServiceDefinitionVO(ServiceDefinitionVO serviceDefinitionVO);

	jwcpxt_service_instance get_serviceInstance_byServiceInstanceID(String serviceInstanceID);

	int get_serviceInstanceDistributionCount_byDateAndServiceDefinitionID(String date, String serviceDefinitionID);

	int get_serviceInstanceTotalCount_byDateAndServiceDefinitionID(String date, String serviceDefinitionID);

	int get_serviceInstanceTotalCount_byServiceInstanceVO(ServiceInstanceVO serviceInstanceVO);

	int get_serviceInstanceTotalCount_byToday();

	boolean ifExist_serviceDefinition_byServiceDefinitionDescribe(String service_definition_describe);

	List<jwcpxt_service_client> list_client_byServiceInstanceID(String serviceInstanceID);

	List<jwcpxt_service_client> list_serviceClient_byServiceInstanceID(String serviceInstanceID);

	List<jwcpxt_service_definition> list_serviceDefinitionDO_all();

	List<ServiceDefinitionDTO> list_serviceDefinitionDTO_byServiceDefinitionVO(ServiceDefinitionVO serviceDefinitionVO);

	List<jwcpxt_service_instance> list_serviceInstance_byNoJudgeAndRandomAndNumAndServiceDefinitionIDAndDate(int num,
			String serviceDefinitionID, String date);

	List<jwcpxt_service_instance> list_serviceInstance_byServiceDefinitionID(String serviceDefinitionID);

	List<jwcpxt_service_instance> list_serviceInstance_byServiceInstanceVO(ServiceInstanceVO serviceInstanceVO);

	boolean save_serviceDefinition(jwcpxt_service_definition serviceDefinition);

	boolean save_serviceGrab(jwcpxt_service_grab serviceGrab);

	boolean update_serviceDefinition(jwcpxt_service_definition serviceDefinitionOld);

	boolean update_serviceInstance(jwcpxt_service_instance serviceInstance);

	jwcpxt_service_grab get_serviceGrab_byServiceGrabID(String serviceGrabId);

	boolean update_serviceGrab(jwcpxt_service_grab serviceGrabOld);

}
