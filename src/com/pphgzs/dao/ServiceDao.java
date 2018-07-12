package com.pphgzs.dao;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_grab_journal;
import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_service_grab;
import com.pphgzs.domain.DO.jwcpxt_service_instance;
import com.pphgzs.domain.DO.jwcpxt_unit_service;
import com.pphgzs.domain.DTO.ClientInstanceDTO;
import com.pphgzs.domain.DTO.ServiceConnectDTO;
import com.pphgzs.domain.DTO.ServiceDefinitionDTO;
import com.pphgzs.domain.VO.ServiceDefinitionVO;
import com.pphgzs.domain.VO.ServiceInstanceVO;

public interface ServiceDao {
	/**
	 * 获取 ClientInstanceDTO
	 * 
	 * @param jwcpxt_user_id
	 * @return
	 */
	public ClientInstanceDTO get_notServiceClientDTO_byServiceClientId(String userId);

	/**
	 * 根据业务定义id获取抓取表
	 * 
	 * @param jwcpxt_service_definition_id
	 * @return
	 */
	public List<jwcpxt_service_grab> list_serviceGrab_byServiceDefinitionId(String jwcpxt_service_definition_id);

	/**
	 * 删除记录
	 * 
	 * @param serviceGrab
	 */
	public boolean delete_serviceGrab_byServiceGrabId(jwcpxt_service_grab serviceGrab);

	/**
	 * 根据id获取单位业务表
	 */
	public jwcpxt_unit_service get_unitService_byUnitServiceId(String trim);

	/**
	 * 获取所有关联某单位的业务
	 * 
	 * @param trim
	 * @return
	 */
	public List<ServiceConnectDTO> list_serviceDefinitionDTO_connectService(String trim);

	/**
	 * 获取未关联某单位的所有业务
	 * 
	 * @param trim
	 * @return
	 */
	public List<jwcpxt_service_definition> list_serviceDefinition_notConnectService(String trim);

	/**
	 * 
	 * 保存对象
	 */
	public void saveOrUpdateObject(Object obj);

	/**
	 * 获取业务定义列表
	 * 
	 * @return
	 */
	public List<jwcpxt_service_definition> list_serviceDefinitionDO_all();

	/**
	 * 
	 * 
	 */

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

	jwcpxt_service_grab get_serviceGrabDO_byServiceDefinitionID(String serviceDefinitionID);

	jwcpxt_grab_journal get_grabJournal_byServiceDefinitionIDAndDate(String serviceDefinitionID, String date);

	boolean save_grabJournal(jwcpxt_grab_journal grabJournal);

}
