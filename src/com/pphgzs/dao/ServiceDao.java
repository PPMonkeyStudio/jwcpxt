package com.pphgzs.dao;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_grab_instance;
import com.pphgzs.domain.DO.jwcpxt_grab_journal;
import com.pphgzs.domain.DO.jwcpxt_question;
import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_service_grab;
import com.pphgzs.domain.DO.jwcpxt_service_instance;
import com.pphgzs.domain.DO.jwcpxt_unit;
import com.pphgzs.domain.DO.jwcpxt_unit_service;
import com.pphgzs.domain.DO.jwcpxt_user;
import com.pphgzs.domain.DTO.ClientInfoDTO;
import com.pphgzs.domain.DTO.ClientInstanceDTO;
import com.pphgzs.domain.DTO.ClientNotSatisfiedQusetionAndOptionDTO;
import com.pphgzs.domain.DTO.ServiceConnectDTO;
import com.pphgzs.domain.DTO.ServiceDefinitionDTO;
import com.pphgzs.domain.VO.ClientInfoVO;
import com.pphgzs.domain.VO.CountFinishReturnVisitVo;
import com.pphgzs.domain.VO.ServiceDefinitionVO;
import com.pphgzs.domain.VO.ServiceInstanceVO;

public interface ServiceDao {
	/**
	 * 获取 ClientInstanceDTO
	 * 
	 * @param jwcpxt_user_id
	 * @return
	 */
	public ClientInstanceDTO get_notServiceClientDTO_byJudge_general(String userId);

	public ClientInstanceDTO get_notServiceClientDTO_byJudge_revisit(String jwcpxt_user_id);

	public ClientInstanceDTO get_notServiceClientDTO_byJudge_specified(String jwcpxt_service_client_id);

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

	/**
	 * 根据id获取当事人
	 * 
	 * @param trim
	 * @return
	 */
	public jwcpxt_service_client get_serviceClientDo_byId(String trim);

	public boolean update_grabInstance(jwcpxt_grab_instance grabInstance);

	public int get_serviceInstanceCount_byServiceDefinitionAndUnit(String service_definition_id, String unit_id);

	public int get_serviceInstanceCount_byServiceDefinitionAndFatherUnitID(String service_definition_id,
			String unit_id);

	public jwcpxt_grab_instance get_grabInstance_byServiceDefinitionIDAndOrganizationCode_notDistribution_random(
			String serviceDefinitionID, String organizationCode);

	public jwcpxt_grab_instance get_grabInstance_byServiceDefinitionIDAndFatherOrganizationCode_notDistribution_random(
			String serviceDefinitionID, String organizationCode);

	public List<jwcpxt_service_definition> list_serviceDefinitionDOList_byUnitID(String unitID);

	/**
	 * 根据VO获取当事人列表
	 * 
	 * @param clientInfoVO
	 * @return
	 */
	public List<String> get_clientInfoVO_byUserId(ClientInfoVO clientInfoVO);

	/**
	 * 根据VO获取数量
	 * 
	 * @param clientInfoVO
	 * @return
	 */
	public int get_clientInfoVOCount_byUserId(ClientInfoVO clientInfoVO);

	/**
	 * 通过当事人ID获取有关业务实例关联的所有信息，放入ClientInfoDTO内
	 * 
	 * @param client
	 * @return
	 */
	public ClientInfoDTO get_clientInfoVO_byClientId(jwcpxt_service_client client);

	/**
	 * 测评员列表
	 * 
	 * @return
	 */
	public List<jwcpxt_user> list_userDO();

	/**
	 * 根据机构代码获取单位
	 * 
	 * @param orginId
	 * @return
	 */
	public jwcpxt_unit get_unitDo_byOrginaiId(String orginId);

	/**
	 * 获取(单个，全部)回访员（当天，时间区间）内的成功测评的数量
	 * 
	 * @param countFinishReturnVisitVo
	 * @return
	 */
	public int get_countFinishReturnVisit_inDateAndByUserId(CountFinishReturnVisitVo countFinishReturnVisitVo);

	/**
	 * 通过业务id获取所有的问题
	 * 
	 * @param jwcpxt_service_definition_id
	 * @return
	 */
	public List<jwcpxt_question> get_AllQuestion_ByServiceId(String jwcpxt_service_definition_id);

	/**
	 * 通过问题id和问题类型和当事人ID去获取当事人对该问题的回答
	 * 
	 * @param question
	 * @param jwcpxt_service_client_id
	 * @return
	 */

	public String get_ClientAnswer_ByQuestionAndClientId(jwcpxt_question question, String jwcpxt_service_client_id);

	/**
	 * 选项获取追问(在DAO做判断)
	 * 
	 * @param question
	 * @param jwcpxt_service_client_id
	 * @return
	 */
	public List<jwcpxt_question> get_askQusetionList_ByQuestionAndClientId(jwcpxt_question question,
			String jwcpxt_service_client_id);

	/**
	 * 通过当事人ID获取业务实例DO
	 * 
	 * @param serviceClient
	 * @return
	 */
	public jwcpxt_service_instance get_serviceInstanceDo_byServiceClientID(jwcpxt_service_client serviceClient);

	/**
	 * 判断电话是否在七天内被访问过
	 * 
	 * @param grab_instance_client_phone
	 */
	public List<jwcpxt_service_client> getClientByPhoneDate(String grab_instance_client_phone);

}
