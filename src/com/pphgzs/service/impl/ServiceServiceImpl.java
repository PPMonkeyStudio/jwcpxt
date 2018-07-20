package com.pphgzs.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.pphgzs.dao.ServiceDao;
import com.pphgzs.domain.DO.jwcpxt_grab_instance;
import com.pphgzs.domain.DO.jwcpxt_grab_journal;
import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_service_grab;
import com.pphgzs.domain.DO.jwcpxt_service_instance;
import com.pphgzs.domain.DO.jwcpxt_unit;
import com.pphgzs.domain.DO.jwcpxt_unit_service;
import com.pphgzs.domain.DO.jwcpxt_user;
import com.pphgzs.domain.DTO.ClientInfoDTO;
import com.pphgzs.domain.DTO.ClientInstanceDTO;
import com.pphgzs.domain.DTO.ServiceConnectDTO;
import com.pphgzs.domain.DTO.ServiceDefinitionDTO;
import com.pphgzs.domain.DTO.ServiceInstanceDTO;
import com.pphgzs.domain.VO.ClientInfoVO;
import com.pphgzs.domain.VO.ServiceDefinitionVO;
import com.pphgzs.domain.VO.ServiceInstanceVO;
import com.pphgzs.service.ServiceService;
import com.pphgzs.service.UnitService;
import com.pphgzs.service.UserService;
import com.pphgzs.util.TimeUtil;
import com.pphgzs.util.uuidUtil;

public class ServiceServiceImpl implements ServiceService {

	private ServiceDao serviceDao;

	private UnitService unitService;

	private UserService userService;

	public ServiceDao getServiceDao() {
		return serviceDao;
	}

	public void setServiceDao(ServiceDao serviceDao) {
		this.serviceDao = serviceDao;
	}

	public UnitService getUnitService() {
		return unitService;
	}

	public void setUnitService(UnitService unitService) {
		this.unitService = unitService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 根据VO获取当事人VO
	 */
	@Override
	public ClientInfoVO get_clientInfoVO_byUserId(ClientInfoVO clientInfoVO) {
		List<ClientInfoDTO> listClientInfo = new ArrayList<>();
		listClientInfo = serviceDao.get_clientInfoVO_byUserId(clientInfoVO);
		int totalRecords = serviceDao.get_clientInfoVOCount_byUserId(clientInfoVO);
		int totalPages = ((totalRecords - 1) / clientInfoVO.getPageSize()) + 1;
		clientInfoVO.setListClientInfoDTO(listClientInfo);
		clientInfoVO.setTotalCount(totalRecords);
		clientInfoVO.setTotalPage(totalPages);
		return clientInfoVO;
	}

	@Override
	public void saveServiceClient(jwcpxt_service_client newServiceClient) {
		serviceDao.saveOrUpdateObject(newServiceClient);

	}

	@Override
	public jwcpxt_service_definition get_serviceDefinitionDo_byId(jwcpxt_service_definition serviceDefinition) {
		if (serviceDefinition != null && serviceDefinition.getJwcpxt_service_definition_id() != null
				&& serviceDefinition.getJwcpxt_service_definition_id().trim().length() > 0) {
			serviceDefinition = serviceDao
					.get_serviceDefinition_byServiceDefinitionID(serviceDefinition.getJwcpxt_service_definition_id());
		}
		return serviceDefinition;
	}

	@Override
	public List<jwcpxt_service_definition> list_serviceDefinitionDOList_byUnitID(String unitID) {
		return serviceDao.list_serviceDefinitionDOList_byUnitID(unitID);
	}

	@Override
	public jwcpxt_service_client get_serviceClientDo_byId(jwcpxt_service_client serviceClient) {
		if (serviceClient != null && serviceClient.getJwcpxt_service_client_id() != null
				&& serviceClient.getJwcpxt_service_client_id().trim().length() > 0) {
			serviceClient = serviceDao.get_serviceClientDo_byId(serviceClient.getJwcpxt_service_client_id().trim());
		}
		return serviceClient;
	}

	/**
	 * 更改当事人状态
	 */
	@Override
	public boolean update_serviceClient_byId(jwcpxt_service_client serviceClient) {
		if (serviceClient != null && serviceClient.getJwcpxt_service_client_id() != null
				&& !"".equals(serviceClient.getJwcpxt_service_client_id())) {
			serviceClient = serviceDao.get_serviceClientDo_byId(serviceClient.getJwcpxt_service_client_id().trim());
		}
		if (serviceClient == null) {
			return false;
		}
		serviceClient.setService_client_visit(serviceClient.getService_client_visit());
		serviceClient.setService_client_gmt_modified(TimeUtil.getStringSecond());
		serviceDao.saveOrUpdateObject(serviceClient);
		return true;
	}

	/**
	 * 获取当事人信息及所涉及的业务
	 */
	@Override
	public ClientInstanceDTO get_notServiceClient_byServiceClientId(jwcpxt_user user) {
		//
		ClientInstanceDTO clientInstanceDTO = new ClientInstanceDTO();
		//
		if (user != null && user.getJwcpxt_user_id() != null && user.getJwcpxt_user_id().trim().length() > 0) {
			user = userService.get_userDO_byUserID(user.getJwcpxt_user_id());
		}
		if (user == null) {
			return null;
		}
		clientInstanceDTO = serviceDao.get_notServiceClientDTO_byServiceClientId(user.getJwcpxt_user_id());
		if (clientInstanceDTO == null) {
			System.out.println("fempei");
			distributionNewServiceInstance_toUser(user.getJwcpxt_user_id());
			clientInstanceDTO = serviceDao.get_notServiceClientDTO_byServiceClientId(user.getJwcpxt_user_id());
		}
		return clientInstanceDTO;
	}

	@Override
	public boolean distributionNewServiceInstance_toUser(String userID) {
		/*
		 * 最后分配新的实例给测评人员
		 * 
		 * 1、查询所有单位关联业务表
		 * 
		 * 2、当天的抓取实例中，属于这个单位的，且属于这个业务定义的数量
		 * 
		 * 3、对比数量和单位关联业务表中填写的需求数量，数量大于需求数量的，就移出list
		 * 
		 * 4、在剩下的list中随机选取一个单位业务关联DO，通过这个DO，随机取一个抓取实例分配到业务实例中给这个测评员
		 * 
		 * 
		 * 
		 */

		// 查询所有单位关联业务表
		List<jwcpxt_unit_service> unitServiceList = unitService.list_unitServiceDO_all();
		System.out.println("查询所有单位关联业务表:" + unitServiceList.size());
		// 当天业务实例中，属于这个单位的，且属于这个业务定义的数量
		for (jwcpxt_unit_service unitServiceDO : unitServiceList) {
			// 需求数量
			int wantNum = unitServiceDO.getEvaluation_count();
			// System.out.println("需求数量：" + wantNum);
			/*
			 * 如果这个单位是二级单位，那么就查出他所有子单位已分配的实例数量
			 * 
			 */
			int currNum = 0;
			jwcpxt_unit unit = unitService.get_unitDO_byID(unitServiceDO.getUnit_id());
			if (unit.getUnit_grade() == 2) {
				// 二级单位
				currNum = get_serviceInstanceCount_byServiceDefinitionAndFatherUnitID(
						unitServiceDO.getService_definition_id(), unitServiceDO.getUnit_id());
			} else {
				// 已分配数量：获取当天该单位该业务的业务实例的数量
				currNum = get_serviceInstanceCount_byServiceDefinitionAndUnit(unitServiceDO.getService_definition_id(),
						unitServiceDO.getUnit_id());
				// System.out.println("三级单位当前业务实例数量：" + currNum);
			}

			// 分配足够了的就移出列表
			if (currNum >= wantNum) {
				unitServiceList.remove(unitServiceDO);
			}
		}
		// 遍历一下这个列表
		/*
		 * for (jwcpxt_unit_service jwcpxt_unit_service : unitServiceList) {
		 * System.out.println("单位业务关联表：" + jwcpxt_unit_service); }
		 */

		// 随机取一个单位业务关联DO作为分配，如果这个单位没有数据，那么就换一个单位
		jwcpxt_grab_instance grabInstance = null;
		jwcpxt_unit_service thisUnitService = null;
		jwcpxt_unit unit = null;
		while (unitServiceList.size() > 0) {
			int random = (int) (Math.random() * unitServiceList.size());
			thisUnitService = unitServiceList.get(random);
			unit = unitService.get_unitDO_byID(thisUnitService.getUnit_id());
			if (unit.getUnit_grade() == 2) {
				// 二级单位
				grabInstance = get_grabInstance_byServiceDefinitionIDAndFatherOrganizationCode_notDistribution_random(
						thisUnitService.getService_definition_id(), unit.getUnit_account());
			} else {
				// 随机此业务，此单位，未被分配的一个抓取实例
				grabInstance = get_grabInstance_byServiceDefinitionIDAndOrganizationCode_notDistribution_random(
						thisUnitService.getService_definition_id(), unit.getUnit_account());
			}
			if (grabInstance != null) {
				break;
			} else {
				unitServiceList.remove(random);
			}
		}
		if (grabInstance == null) {
			/*
			 * 说明没有东西可以分配，今天就这样不分配了，明天上线再说
			 */
			return true;
		}
		// 分配生成业务实例
		jwcpxt_service_instance serviceInstance = new jwcpxt_service_instance();
		serviceInstance.setJwcpxt_service_instance_id(uuidUtil.getUuid());
		serviceInstance.setService_instance_gmt_create(TimeUtil.getStringSecond());
		serviceInstance.setService_instance_gmt_modified(serviceInstance.getService_instance_gmt_create());
		serviceInstance.setService_instance_service_definition(grabInstance.getGrab_instance_service_definition());
		serviceInstance.setService_instance_belong_unit(thisUnitService.getUnit_id());
		serviceInstance.setService_instance_judge(userID);
		serviceInstance.setService_instance_nid(grabInstance.getGrab_instance_unique_id());
		/*
		 * if (grabInstance.getGrab_instance_service_time() == null ||
		 * "".equals(grabInstance.getGrab_instance_service_time())) {
		 * serviceInstance.setService_instance_date(TimeUtil.getStringDay()); }else {
		 * serviceInstance.setService_instance_date(
		 * TimeUtil.longDateFormatDate(grabInstance.getGrab_instance_service_time())); }
		 */
		try {
			serviceInstance.setService_instance_date(
					TimeUtil.longDateFormatDate(grabInstance.getGrab_instance_service_time()));
		} catch (ParseException e) {
			System.err.println(e);
			serviceInstance.setService_instance_date(TimeUtil.getStringDay());
		}
		// serviceInstance.setService_instance_date(grabInstance.getGrab_instance_service_time());
		saveServiceInstance(serviceInstance);
		// 分配生成当事人
		jwcpxt_service_client newServiceClient = new jwcpxt_service_client();
		newServiceClient.setJwcpxt_service_client_id(uuidUtil.getUuid());
		newServiceClient.setService_client_service_instance(serviceInstance.getJwcpxt_service_instance_id());
		newServiceClient.setService_client_name(grabInstance.getGrab_instance_client_name());
		newServiceClient.setService_client_sex(grabInstance.getGrab_instance_client_sex());
		newServiceClient.setService_client_phone(grabInstance.getGrab_instance_client_phone());
		newServiceClient.setService_client_visit("2");
		newServiceClient.setService_client_gmt_create(TimeUtil.getStringSecond());
		newServiceClient.setService_client_gmt_modified(newServiceClient.getService_client_gmt_create());
		serviceDao.saveOrUpdateObject(newServiceClient);
		//

		// 并且更新抓取实例的状态
		grabInstance.setGrab_instance_distribution("1");
		update_grabInstance(grabInstance);
		//
		return true;
	}

	/**
	 * 获取抓取
	 */
	@Override
	public jwcpxt_service_grab get_serviceGrab(jwcpxt_service_grab serviceGrab) {
		jwcpxt_service_grab serviceG = new jwcpxt_service_grab();
		if (serviceGrab != null && serviceGrab.getJwcpxt_service_grab_id() != null
				&& serviceGrab.getJwcpxt_service_grab_id().trim().length() > 0) {
			serviceG = serviceDao.get_serviceGrab_byServiceGrabID(serviceGrab.getJwcpxt_service_grab_id().trim());
		}
		return serviceG;
	}

	/**
	 * 根据业务定义id获取抓取表
	 */
	@Override
	public List<jwcpxt_service_grab> list_serviceGrab_byServiceDefinitionId(
			jwcpxt_service_definition serviceDefinition) {
		// 定义
		jwcpxt_service_definition serviceDe = new jwcpxt_service_definition();
		List<jwcpxt_service_grab> listServiceGrab = new ArrayList<>();
		if (serviceDefinition != null && serviceDefinition.getJwcpxt_service_definition_id() != null
				&& serviceDefinition.getJwcpxt_service_definition_id().trim().length() > 0) {
			serviceDe = serviceDao.get_serviceDefinition_byServiceDefinitionID(
					serviceDefinition.getJwcpxt_service_definition_id().trim());
		}
		if (serviceDe == null) {
			return null;
		}
		//
		listServiceGrab = serviceDao
				.list_serviceGrab_byServiceDefinitionId(serviceDefinition.getJwcpxt_service_definition_id());
		return listServiceGrab;
	}

	/**
	 * 修改抓取记录
	 */
	@Override
	public boolean update_serviceGrab_byServiceGrabId(jwcpxt_service_grab serviceGrab) {
		// 定义
		jwcpxt_service_grab grab = new jwcpxt_service_grab();

		// 根据id获取抓取记录
		if (serviceGrab != null && serviceGrab.getJwcpxt_service_grab_id() != null
				&& serviceGrab.getJwcpxt_service_grab_id().trim().length() > 0) {
			grab = serviceDao.get_serviceGrab_byServiceGrabID(serviceGrab.getJwcpxt_service_grab_id().trim());
		}
		if (grab == null) {
			return false;
		}
		grab.setService_grab_service_num(serviceGrab.getService_grab_service_num());
		grab.setService_grab_interface_identifying(serviceGrab.getService_grab_interface_identifying());
		grab.setService_grab_source_username(serviceGrab.getService_grab_source_username());
		grab.setService_grab_source_password(serviceGrab.getService_grab_source_password());
		grab.setService_grab_machine_ip(serviceGrab.getService_grab_machine_ip());
		grab.setService_grab_single_table(serviceGrab.getService_grab_single_table());
		grab.setService_grab_project_name(serviceGrab.getService_grab_project_name());
		grab.setService_grab_interface_one(serviceGrab.getService_grab_interface_one());
		grab.setService_grab_interface_two(serviceGrab.getService_grab_interface_two());
		grab.setService_grab_field_name(serviceGrab.getService_grab_field_name());
		grab.setService_grab_name_field(serviceGrab.getService_grab_name_field());
		grab.setService_grab_sex_field(serviceGrab.getService_grab_sex_field());
		grab.setService_grab_phone_field(serviceGrab.getService_grab_phone_field());
		grab.setService_grab_handle_time_field(serviceGrab.getService_grab_handle_time_field());
		grab.setService_grab_connect_one_field(serviceGrab.getService_grab_connect_one_field());
		grab.setService_grab_connect_two_field(serviceGrab.getService_grab_connect_two_field());
		grab.setService_grab_gmt_modified(TimeUtil.getStringSecond());
		serviceDao.saveOrUpdateObject(grab);
		return true;
	}

	/**
	 * 删除抓取记录
	 */
	@Override
	public boolean delete_serviceGrab_byServiceGrabId(jwcpxt_service_grab serviceGrab) {
		if (serviceGrab != null && serviceGrab.getJwcpxt_service_grab_id() != null
				&& serviceGrab.getJwcpxt_service_grab_id().trim().length() > 0) {
			serviceGrab = serviceDao.get_serviceGrab_byServiceGrabID(serviceGrab.getJwcpxt_service_grab_id().trim());
		}
		if (serviceGrab == null) {
			return false;
		}
		serviceDao.delete_serviceGrab_byServiceGrabId(serviceGrab);
		return true;
	}

	/**
	 * 获取关联表
	 */
	@Override
	public jwcpxt_unit_service get_untServic_byUnitServicId(jwcpxt_unit_service unitServic) {
		jwcpxt_unit_service unitServi = new jwcpxt_unit_service();
		if (unitServic != null && unitServic.getJwcpxt_unit_service_id() != null
				&& unitServic.getJwcpxt_unit_service_id().trim().length() > 0) {
			unitServi = serviceDao.get_unitService_byUnitServiceId(unitServic.getJwcpxt_unit_service_id().trim());
		}
		return unitServi;
	}

	@Override
	public boolean update_grabInstance(jwcpxt_grab_instance grabInstance) {
		return serviceDao.update_grabInstance(grabInstance);
	}

	/**
	 * 更改评测数量
	 */
	@Override
	public boolean update_unitServiceCount_byUnitServiceId(jwcpxt_unit_service unitSer) {
		jwcpxt_unit_service connectUnitService = new jwcpxt_unit_service();
		// 获取关系表
		if (unitSer != null && unitSer.getJwcpxt_unit_service_id() != null
				&& unitSer.getJwcpxt_unit_service_id().trim().length() > 0) {
			connectUnitService = serviceDao.get_unitService_byUnitServiceId(unitSer.getJwcpxt_unit_service_id().trim());
		}
		if (connectUnitService == null) {
			return false;
		}
		connectUnitService.setEvaluation_count(unitSer.getEvaluation_count());
		connectUnitService.setUnit_service_gmt_modified(TimeUtil.getStringSecond());
		serviceDao.saveOrUpdateObject(connectUnitService);
		return true;
	}

	/**
	 * 获取所有关联某单位的业务
	 */
	@Override
	public List<ServiceConnectDTO> list_serviceDefinitionDTO_connectService(jwcpxt_unit unit) {
		if (unit != null && unit.getJwcpxt_unit_id() != null && unit.getJwcpxt_unit_id().trim().length() > 0) {
			unit = unitService.get_unitDO_byID(unit.getJwcpxt_unit_id().trim());
		}
		List<ServiceConnectDTO> listServiceDefinitionDTO = new ArrayList<>();
		listServiceDefinitionDTO = serviceDao.list_serviceDefinitionDTO_connectService(unit.getJwcpxt_unit_id().trim());
		return listServiceDefinitionDTO;
	}

	/**
	 * 创建单位业务表
	 */
	@Override
	public boolean save_unitService(jwcpxt_unit_service unitSer) {
		if (unitSer != null) {
			if (unitSer.getUnit_id() != null && unitSer.getUnit_id().trim().length() > 0) {
				if (unitService.get_unitDO_byID(unitSer.getUnit_id().trim()) == null)
					return false;
			} else {
				return false;
			}
			if (unitSer.getService_definition_id() != null && unitSer.getService_definition_id().trim().length() > 0) {
				if (serviceDao.get_serviceDefinition_byServiceDefinitionID(
						unitSer.getService_definition_id().trim()) == null) {
					return false;
				}
			} else {
				return false;
			}
			/**
			 * 存储
			 */
			unitSer.setJwcpxt_unit_service_id(uuidUtil.getUuid());
			unitSer.setUnit_service_gmt_create(TimeUtil.getStringSecond());
			unitSer.setUnit_service_gmt_modified(unitSer.getUnit_service_gmt_create());
			serviceDao.saveOrUpdateObject(unitSer);
		} else {
			return false;
		}
		return true;
	}

	/**
	 * 获取某单位未关联的业务定义
	 */
	@Override
	public List<jwcpxt_service_definition> list_serviceDefinition_notConnectService(jwcpxt_unit unit) {
		if (unit != null && unit.getJwcpxt_unit_id() != null && unit.getJwcpxt_unit_id().trim().length() > 0) {
			unit = unitService.get_unitDO_byID(unit.getJwcpxt_unit_id().trim());
		}
		if (unit == null) {
			return null;
		}
		List<jwcpxt_service_definition> listServiceDefinition = new ArrayList<>();
		listServiceDefinition = serviceDao.list_serviceDefinition_notConnectService(unit.getJwcpxt_unit_id().trim());
		return listServiceDefinition;
	}

	/**
	 * 
	 */
	/**
	 * 业务定义列表
	 */
	@Override
	public List<jwcpxt_service_definition> list_serviceDefinition() {
		List<jwcpxt_service_definition> listServiceDefinition = new ArrayList<>();
		listServiceDefinition = serviceDao.list_serviceDefinitionDO_all();
		return listServiceDefinition;
	}

	/**
	 * 根据业务定义id获取业务定义DTO
	 */
	@Override
	public ServiceDefinitionDTO get_serviceDefinitionDTO_byServiceDefinitionID(String serviceDefinitionID) {
		return serviceDao.get_serviceDefinitionDTO_byServiceDefinitionID(serviceDefinitionID);
	}

	@Override
	public void saveServiceInstance(jwcpxt_service_instance serviceInstance) {
		serviceDao.saveOrUpdateObject(serviceInstance);
	}

	/**
	 * 创建业务抓取表
	 */
	@Override
	public boolean save_serviceGrab(jwcpxt_service_grab serviceGrab) {
		if (serviceGrab == null) {
			return false;
		}
		serviceGrab.setJwcpxt_service_grab_id(uuidUtil.getUuid());
		serviceGrab.setService_grab_gmt_create(TimeUtil.getStringSecond());
		serviceGrab.setService_grab_gmt_modified(serviceGrab.getService_grab_gmt_create());
		serviceDao.save_serviceGrab(serviceGrab);
		return true;
	}

	/**
	 * 创建业务定义
	 */
	@Override
	public boolean save_serviceDefinition(jwcpxt_service_definition serviceDefinition) {
		if (serviceDefinition == null) {
			return false;
		}
		serviceDefinition.setJwcpxt_service_definition_id(uuidUtil.getUuid());
		serviceDefinition.setService_definition_gmt_create(TimeUtil.getStringSecond());
		serviceDefinition.setService_definition_gmt_modified(serviceDefinition.getService_definition_gmt_create());
		serviceDao.saveOrUpdateObject(serviceDefinition);
		return true;
	}

	/**
	 * 
	 */

	// @Override
	// public void distribution_serviceInstance_auto() {
	//
	// // 获取所有的业务定义
	// List<jwcpxt_service_definition> serviceDefinitionList =
	// list_serviceDefinitionDO_all();
	// // 分别查出所有业务定义的分配情况
	// for (jwcpxt_service_definition serviceDefinition : serviceDefinitionList)
	// {
	// // 总数
	// int totalCount =
	// get_serviceInstanceTotalCount_byTodayAndServiceDefinitionID(
	// serviceDefinition.getJwcpxt_service_definition_id());
	// System.out.println("\n该业务当日总数：" + totalCount);
	// if (totalCount == 0) {
	// continue;
	// }
	// // 已分配的数量
	// int distributionCount =
	// get_serviceInstanceDistributionCount_byTodayAndServiceDefinitionID(
	// serviceDefinition.getJwcpxt_service_definition_id());
	// System.out.println("该业务当日已分配的数量：" + distributionCount);
	// // 实际分配比例
	// Double samplingProportionNow = (distributionCount / (double) totalCount);
	// System.out
	// .println("该业务要求分配百分比：" +
	// serviceDefinition.getService_definition_sampling_proportion() * 10 +
	// "%");
	// System.out.println("该业务实际分配百分比：" + samplingProportionNow * 100 + "%");
	// // 判断是否超过了比例
	// if ((samplingProportionNow * 10) >=
	// serviceDefinition.getService_definition_sampling_proportion()) {
	// // 停止分配
	// continue;
	// } else {
	// // 算出差多少个，然后继续分配
	// double dev =
	// ((serviceDefinition.getService_definition_sampling_proportion() *
	// totalCount) / 10.0)
	// - distributionCount;
	// System.out.println("该业务当日实际相差的数量：" + dev);
	// int devFinally = (int) Math.ceil(dev);
	// System.out.println("该业务当日实际还需要分配实例数：" + devFinally);
	// //
	// distributionRandom_serviceInstance_byNoJudgeAndNumAndServiceDefinitionIDAndDate(devFinally,
	// serviceDefinition.getJwcpxt_service_definition_id(),
	// TimeUtil.getStringDay());
	// }
	//
	// }
	//
	// }

	// @Override
	// public void
	// distributionRandom_serviceInstance_byNoJudgeAndNumAndServiceDefinitionIDAndDate(int
	// num,
	// String serviceDefinitionID, String date) {
	// jwcpxt_service_definition serviceDefinition =
	// get_serviceDefinitionDO_byServiceDefinitionID(
	// serviceDefinitionID);
	// // 获取随机到的业务实例
	// List<jwcpxt_service_instance> serviceInstanceSampleList =
	// list_serviceInstance_byNoJudgeAndRandomAndNumAndServiceDefinitionIDAndDate(
	// num, serviceDefinitionID, date);
	// // 如果没有可以分配的了，就溜
	// if (serviceInstanceSampleList.size() == 0) {
	// return;
	// }
	// // 获取测评人员
	// jwcpxt_user sreenUser = new jwcpxt_user();
	// sreenUser.setUser_Jurisdiction_evaluate("have");
	// sreenUser.setUser_Jurisdiction_review("");
	// sreenUser.setUser_Jurisdiction_statistics("");
	// List<jwcpxt_user> userList =
	// userService.list_user_byJurisdiction(sreenUser);
	// System.out.println("测评人员数：" + userList.size());
	// if (userList.size() == 0) {
	// return;
	// }
	// // 获取当天总共有多少个实例数
	// int serviceInstanceNumToday =
	// get_serviceInstanceTotalCount_byTodayAndServiceDefinitionID(serviceDefinitionID);
	//
	// double serviceInstanceActualNumToday =
	// serviceDefinition.getService_definition_sampling_proportion() / 10.0
	// * serviceInstanceNumToday;
	// // 平均每个人最多要有多少个实例
	// int userDistributionBigNum = (int)
	// Math.ceil(serviceInstanceActualNumToday / userList.size());
	// System.out.println("平均每个人最多要有多少个实例：" + userDistributionBigNum);
	// // 按照最高的分配
	// for (jwcpxt_user user : userList) {
	// int userDistributionNum =
	// userService.get_userDistributionNum_byToday(user.getJwcpxt_user_id());
	// // 如果分配完了就溜
	// if (userDistributionNum >= userDistributionBigNum) {
	// continue;
	// }
	// // 如果没满
	// // 差几个就分配几个
	// int dev = userDistributionBigNum - userDistributionNum;
	// System.out.println("已分配：" + userDistributionNum);
	// System.out.println("继续分配：" + dev);
	// for (; (serviceInstanceSampleList.size() > 0 && dev != 0);) {
	// distribution_judge(serviceInstanceSampleList.get(0).getJwcpxt_service_instance_id(),
	// user.getJwcpxt_user_id());
	// serviceInstanceSampleList.remove(0);
	// dev--;
	// }
	//
	// }
	//
	// }

	// @Override
	// public List<jwcpxt_service_instance>
	// list_serviceInstance_byNoJudgeAndRandomAndNumAndServiceDefinitionIDAndDate(
	// int num, String serviceDefinitionID, String date) {
	// return
	// serviceDao.list_serviceInstance_byNoJudgeAndRandomAndNumAndServiceDefinitionIDAndDate(num,
	// serviceDefinitionID, date);
	// }

	// @Override
	// public int
	// get_serviceInstanceDistributionCount_byTodayAndServiceDefinitionID(String
	// serviceDefinitionID) {
	// return
	// serviceDao.get_serviceInstanceDistributionCount_byDateAndServiceDefinitionID(TimeUtil.getStringDay(),
	// serviceDefinitionID);
	// }

	@Override
	public int get_serviceInstanceTotalCount_byTodayAndServiceDefinitionID(String serviceDefinitionID) {
		return serviceDao.get_serviceInstanceTotalCount_byDateAndServiceDefinitionID(TimeUtil.getStringDay(),
				serviceDefinitionID);
	}

	@Override
	public List<jwcpxt_service_definition> list_serviceDefinitionDO_all() {
		return serviceDao.list_serviceDefinitionDO_all();
	}

	@Override
	public ServiceDefinitionVO get_serviceDefinitionVO(ServiceDefinitionVO serviceDefinitionVO) {
		List<ServiceDefinitionDTO> serviceDefinitionDTOList = serviceDao
				.list_serviceDefinitionDTO_byServiceDefinitionVO(serviceDefinitionVO);
		serviceDefinitionVO.setServiceDefinitionDTOList(serviceDefinitionDTOList);
		// 总记录数
		serviceDefinitionVO
				.setTotalCount(serviceDao.get_serviceDefinitionTotalCount_byServiceDefinitionVO(serviceDefinitionVO));
		// 总页数
		serviceDefinitionVO
				.setTotalPage(((serviceDefinitionVO.getTotalCount() - 1) / serviceDefinitionVO.getPageSize()) + 1);
		//
		return serviceDefinitionVO;
	}

	@Override
	public ServiceInstanceVO get_serviceInstanceVO(ServiceInstanceVO serviceInstanceVO) {
		List<jwcpxt_service_instance> serviceInstanceList = serviceDao
				.list_serviceInstance_byServiceInstanceVO(serviceInstanceVO);
		//
		List<ServiceInstanceDTO> serviceInstanceDTOList = list_ServiceInstanceDTO_byServiceInstanceList(
				serviceInstanceList);
		serviceInstanceVO.setServiceInstanceDTOList(serviceInstanceDTOList);
		// 总记录数
		serviceInstanceVO
				.setTotalCount(serviceDao.get_serviceInstanceTotalCount_byServiceInstanceVO(serviceInstanceVO));
		// 总页数
		serviceInstanceVO.setTotalPage(((serviceInstanceVO.getTotalCount() - 1) / serviceInstanceVO.getPageSize()) + 1);
		//
		return serviceInstanceVO;
	}

	@Override
	public boolean distribution_judge(String serviceInstanceID, String serviceInstanceJudge) {

		jwcpxt_service_instance serviceInstanceOld = serviceDao
				.get_serviceInstance_byServiceInstanceID(serviceInstanceID);
		if (serviceInstanceOld == null) {
			return false;
		}
		//
		serviceInstanceOld.setService_instance_judge(serviceInstanceJudge);
		//
		if (serviceDao.update_serviceInstance(serviceInstanceOld)) {
			return true;
		}
		return false;

	}

	@Override
	public boolean update_serviceDefinition(jwcpxt_service_definition serviceDefinitionNew) {
		jwcpxt_service_definition serviceDefinitionOld = serviceDao
				.get_serviceDefinition_byServiceDefinitionID(serviceDefinitionNew.getJwcpxt_service_definition_id());
		if (serviceDefinitionOld == null) {
			return false;
		}
		// 修改描述
		serviceDefinitionOld.setService_definition_describe(serviceDefinitionNew.getService_definition_describe());

		// 修改比例
		//
		serviceDefinitionOld.setService_definition_gmt_modified(TimeUtil.getStringSecond());
		if (serviceDao.update_serviceDefinition(serviceDefinitionOld)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean update_serviceGrab(jwcpxt_service_grab serviceGrabNew) {
		jwcpxt_service_grab serviceGrabOld = serviceDao
				.get_serviceGrab_byServiceGrabID(serviceGrabNew.getJwcpxt_service_grab_id());
		if (serviceGrabOld == null) {
			return false;
		}
		serviceGrabOld.setService_grab_single_table(serviceGrabNew.getService_grab_single_table());
		serviceGrabOld.setService_grab_project_name(serviceGrabNew.getService_grab_project_name());
		serviceGrabOld.setService_grab_interface_one(serviceGrabNew.getService_grab_interface_one());
		serviceGrabOld.setService_grab_interface_two(serviceGrabNew.getService_grab_interface_two());
		serviceGrabOld.setService_grab_field_name(serviceGrabNew.getService_grab_field_name());
		serviceGrabOld.setService_grab_name_field(serviceGrabNew.getService_grab_name_field());
		serviceGrabOld.setService_grab_sex_field(serviceGrabNew.getService_grab_sex_field());
		serviceGrabOld.setService_grab_phone_field(serviceGrabNew.getService_grab_phone_field());
		serviceGrabOld.setService_grab_handle_time_field(serviceGrabNew.getService_grab_handle_time_field());
		serviceGrabOld.setService_grab_connect_one_field(serviceGrabNew.getService_grab_connect_two_field());
		serviceGrabOld.setService_grab_connect_two_field(serviceGrabNew.getService_grab_connect_two_field());

		//
		serviceGrabOld.setService_grab_gmt_modified(TimeUtil.getStringSecond());
		if (serviceDao.update_serviceGrab(serviceGrabOld)) {
			return true;
		}
		return false;
	}

	@Override
	public jwcpxt_service_definition get_serviceDefinitionDO_byServiceDefinitionID(String serviceDefinitionID) {
		return serviceDao.get_serviceDefinition_byServiceDefinitionID(serviceDefinitionID);
	}

	@Override
	public jwcpxt_service_grab get_serviceGrabDO_byServiceDefinitionID(String serviceDefinitionID) {
		return serviceDao.get_serviceGrabDO_byServiceDefinitionID(serviceDefinitionID);
	}

	@Override
	public List<jwcpxt_service_client> list_clientDO_byServiceInstanceID(String serviceInstanceID) {
		return serviceDao.list_client_byServiceInstanceID(serviceInstanceID);
	}

	@Override
	public List<ServiceInstanceDTO> list_serviceInstanceDTO_byServiceDefinitionID(String serviceDefinitionID) {

		// 通过业务定义ID获取所有相关的业务实例
		List<jwcpxt_service_instance> serviceInstanceList = serviceDao
				.list_serviceInstance_byServiceDefinitionID(serviceDefinitionID);
		//
		List<ServiceInstanceDTO> serviceInstanceDTOList = list_ServiceInstanceDTO_byServiceInstanceList(
				serviceInstanceList);
		//
		return serviceInstanceDTOList;
	}

	@Override
	public List<ServiceInstanceDTO> list_ServiceInstanceDTO_byServiceInstanceList(
			List<jwcpxt_service_instance> serviceInstanceList) {

		List<ServiceInstanceDTO> serviceInstanceDTOList = new ArrayList<ServiceInstanceDTO>();

		for (jwcpxt_service_instance serviceInstance : serviceInstanceList) {
			ServiceInstanceDTO serviceInstanceDTO = new ServiceInstanceDTO();
			//
			serviceInstanceDTO = get_serviceInstanceDTO_byServiceInstanceID(
					serviceInstance.getJwcpxt_service_instance_id());
			//
			serviceInstanceDTOList.add(serviceInstanceDTO);

		}

		return serviceInstanceDTOList;
	}

	@Override
	public ServiceInstanceDTO get_serviceInstanceDTO_byServiceInstanceID(String serviceInstanceID) {

		ServiceInstanceDTO serviceInstanceDTO = new ServiceInstanceDTO();
		// 获取业务实例
		jwcpxt_service_instance serviceInstance = serviceDao.get_serviceInstance_byServiceInstanceID(serviceInstanceID);
		serviceInstanceDTO.setServiceInstance(serviceInstance);
		// 获取业务定义DTO
		ServiceDefinitionDTO serviceDefinitionDTO = serviceDao.get_serviceDefinitionDTO_byServiceDefinitionID(
				serviceInstance.getService_instance_service_definition());
		serviceInstanceDTO.setServiceDefinitionDTO(serviceDefinitionDTO);
		// 获取分配的人
		jwcpxt_user judge = userService.get_userDO_byUserID(serviceInstance.getService_instance_judge());
		serviceInstanceDTO.setJudge(judge);
		// 获取所有当事人列表
		List<jwcpxt_service_client> serviceClientList = serviceDao
				.list_serviceClient_byServiceInstanceID(serviceInstance.getJwcpxt_service_instance_id());
		serviceInstanceDTO.setServiceClientList(serviceClientList);
		//
		return serviceInstanceDTO;
	}

	@Override
	public void grab_serviceInstance_auto() {
		// 获取所有的业务定义
		List<jwcpxt_service_definition> serviceDefinitionList = list_serviceDefinitionDO_all();
		// 分别查出所有业务定义的日志和抓取情况
		for (jwcpxt_service_definition serviceDefinition : serviceDefinitionList) {
			//
			jwcpxt_grab_journal grabJournal = serviceDao.get_grabJournal_byServiceDefinitionIDAndDate(
					serviceDefinition.getJwcpxt_service_definition_id(), TimeUtil.getStringDay());
			if (grabJournal == null) {
				// 创建此业务当天的抓取日志记录
				grabJournal = new jwcpxt_grab_journal();
				grabJournal.setJwcpxt_grab_journal_id(uuidUtil.getUuid());
				grabJournal.setGrab_journal_date(TimeUtil.getStringDay());
				grabJournal.setGrab_journal_if_grab("2");
				grabJournal.setGrab_journal_service_definition(serviceDefinition.getJwcpxt_service_definition_id());
				grabJournal.setGrab_journal_time("none");
				String time = TimeUtil.getStringSecond();
				grabJournal.setGrab_journal_gmt_modified(time);
				grabJournal.setGrab_journal_gmt_create(time);
				serviceDao.save_grabJournal(grabJournal);
				// 抓取这个业务
				List<jwcpxt_service_instance> serviceInstanceList = grab_serviceInstance_byServiceDefinitionID(
						serviceDefinition.getJwcpxt_service_definition_id());
				// 然后保存进数据库
				/*
				 * 
				 */
			} else {
				// 判断这个业务是否抓取
				if (grabJournal.getGrab_journal_if_grab().equals("1")) {
					// 已抓取
				} else {
					// 抓取这个业务
					List<jwcpxt_service_instance> serviceInstanceList = grab_serviceInstance_byServiceDefinitionID(
							serviceDefinition.getJwcpxt_service_definition_id());
					// 然后保存进数据库
				}
			}
			//
		}

	}

	/**
	 * 这个方法是废弃的吗？
	 */
	@Override
	public List<jwcpxt_service_instance> grab_serviceInstance_byServiceDefinitionID(String serviceDefinitionID) {
		/*
		 * 抓取业务
		 */
		return null;
	}

	@Override
	public int get_serviceInstanceCount_byServiceDefinitionAndUnit(String service_definition_id, String unit_id) {
		return serviceDao.get_serviceInstanceCount_byServiceDefinitionAndUnit(service_definition_id, unit_id);
	}

	@Override
	public int get_serviceInstanceCount_byServiceDefinitionAndFatherUnitID(String service_definition_id,
			String unit_id) {
		return serviceDao.get_serviceInstanceCount_byServiceDefinitionAndFatherUnitID(service_definition_id, unit_id);
	}

	@Override
	public jwcpxt_grab_instance get_grabInstance_byServiceDefinitionIDAndOrganizationCode_notDistribution_random(
			String serviceDefinitionID, String organizationCode) {
		return serviceDao.get_grabInstance_byServiceDefinitionIDAndOrganizationCode_notDistribution_random(
				serviceDefinitionID, organizationCode);
	}

	@Override
	public jwcpxt_grab_instance get_grabInstance_byServiceDefinitionIDAndFatherOrganizationCode_notDistribution_random(
			String serviceDefinitionID, String organizationCode) {
		return serviceDao.get_grabInstance_byServiceDefinitionIDAndFatherOrganizationCode_notDistribution_random(
				serviceDefinitionID, organizationCode);
	}
	/*
	 * 
	 */

}
