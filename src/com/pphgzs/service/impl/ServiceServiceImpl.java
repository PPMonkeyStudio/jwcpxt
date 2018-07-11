package com.pphgzs.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.pphgzs.dao.ServiceDao;
import com.pphgzs.domain.DO.jwcpxt_grab_journal;
import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_service_grab;
import com.pphgzs.domain.DO.jwcpxt_service_instance;
import com.pphgzs.domain.DO.jwcpxt_user;
import com.pphgzs.domain.DTO.ServiceDefinitionDTO;
import com.pphgzs.domain.DTO.ServiceInstanceDTO;
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
	 * 
	 */

	/**
	 * 根据业务定义id获取业务定义DTO
	 */
	@Override
	public ServiceDefinitionDTO get_serviceDefinitionDTO_byServiceDefinitionID(String serviceDefinitionID) {
		return serviceDao.get_serviceDefinitionDTO_byServiceDefinitionID(serviceDefinitionID);
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
				// TODO
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
					// TODO
				}
			}
			//
		}

	}

	@Override
	public List<jwcpxt_service_instance> grab_serviceInstance_byServiceDefinitionID(String serviceDefinitionID) {
		/*
		 * 抓取业务
		 */
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * 
	 */

}
