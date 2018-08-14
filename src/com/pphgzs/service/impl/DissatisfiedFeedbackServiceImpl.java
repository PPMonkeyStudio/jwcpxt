package com.pphgzs.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.pphgzs.dao.DissatisfiedFeedbackDao;
import com.pphgzs.domain.DO.jwcpxt_dissatisfied_feedback;
import com.pphgzs.domain.DO.jwcpxt_feedback_rectification;
import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_instance;
import com.pphgzs.domain.DO.jwcpxt_unit;
import com.pphgzs.domain.DO.jwcpxt_user;
import com.pphgzs.domain.DTO.DissatisfiedQuestionDTO;
import com.pphgzs.domain.DTO.FeedbackRectificationDTO;
import com.pphgzs.domain.DTO.SecondDistatisDTO;
import com.pphgzs.domain.VO.CheckFeedbackRectificationVO;
import com.pphgzs.domain.VO.DissatisfiedQuestionVO;
import com.pphgzs.domain.VO.FeedbackRectificationExceedTimeVO;
import com.pphgzs.domain.VO.FeedbackRectificationVO;
import com.pphgzs.domain.VO.SecondDistatisVO;
import com.pphgzs.service.DissatisfiedFeedbackService;
import com.pphgzs.service.QuestionService;
import com.pphgzs.service.ServiceService;
import com.pphgzs.service.UnitService;
import com.pphgzs.service.UserService;
import com.pphgzs.util.SendMessageUtil;
import com.pphgzs.util.TimeUtil;
import com.pphgzs.util.uuidUtil;

public class DissatisfiedFeedbackServiceImpl implements DissatisfiedFeedbackService {
	private DissatisfiedFeedbackDao dissatisfiedFeedbackDao;
	private ServiceService serviceService;
	private QuestionService questionService;
	private UnitService unitService;
	private UserService userService;

	/**
	 * 
	 */
	@Override
	public SecondDistatisVO get_sercondDisStatisExceedTimeVO(SecondDistatisVO secondDistatisVO) {
		List<SecondDistatisDTO> listSecondDistatisDTO = new ArrayList<>();
		int totalRecords = get_secondDisStatisCountExceedTime(secondDistatisVO);
		int totalPages = ((totalRecords - 1) / secondDistatisVO.getPageSize()) + 1;
		listSecondDistatisDTO = dissatisfiedFeedbackDao.get_sercondDisStatisExceedTimeVO(secondDistatisVO);
		secondDistatisVO.setTotalCount(totalRecords);
		secondDistatisVO.setListSecondDistatisDTO(listSecondDistatisDTO);
		secondDistatisVO.setTotalPage(totalPages);
		return secondDistatisVO;
	}

	/**
	 * 二次回访仍然为不满意数量
	 */
	@Override
	public int get_secondDisStatisCountExceedTime(SecondDistatisVO secondDistatisVO) {
		return dissatisfiedFeedbackDao.get_secondDisStatisCountExceedTime(secondDistatisVO);
	}

	/**
	 * 
	 */
	@Override
	public FeedbackRectificationExceedTimeVO get_checkFeedbackRectificationVO(
			FeedbackRectificationExceedTimeVO feedbackRectificationExceedTimeVO) {
		List<FeedbackRectificationDTO> listFeedbackRectificationDTO = new ArrayList<>();
		int totalRecords = get_countExceedTimeFive(feedbackRectificationExceedTimeVO);
		int totalPages = ((totalRecords - 1) / feedbackRectificationExceedTimeVO.getPageSize()) + 1;
		listFeedbackRectificationDTO = dissatisfiedFeedbackDao
				.get_checkFeedbackRectificationVO(feedbackRectificationExceedTimeVO);
		feedbackRectificationExceedTimeVO.setListFeedbackRectificationDTO(listFeedbackRectificationDTO);
		feedbackRectificationExceedTimeVO.setTotalCount(totalRecords);
		feedbackRectificationExceedTimeVO.setTotalPage(totalPages);
		return feedbackRectificationExceedTimeVO;
	}

	/**
	 * 得到超过5天的期限的数量
	 */
	@Override
	public int get_countExceedTimeFive(FeedbackRectificationExceedTimeVO feedbackRectificationExceedTimeVO) {
		return dissatisfiedFeedbackDao.get_countExceedTimeFive(feedbackRectificationExceedTimeVO);
	}

	/**
	 * 审核操作
	 */
	@Override
	public boolean checkFeedbackRectification(jwcpxt_feedback_rectification feedbackRectification, jwcpxt_unit unit) {
		//
		if (unit == null) {
			return false;
		}
		if (unit.getJwcpxt_unit_id() != null && unit.getJwcpxt_unit_id().trim().length() > 0) {
			// 获取unit
			unit = unitService.get_unitDO_byID(unit.getJwcpxt_unit_id());
		}
		if (unit == null) {
			return false;
		}
		if (unit.getUnit_grade() == 0) {
			return false;
		}
		// 定义
		jwcpxt_feedback_rectification checkFeedbackRectification = new jwcpxt_feedback_rectification();
		jwcpxt_feedback_rectification newFeedbackRectification = new jwcpxt_feedback_rectification();
		//
		if (feedbackRectification != null && feedbackRectification.getJwcpxt_feedback_rectification_id() != null
				&& feedbackRectification.getJwcpxt_feedback_rectification_id().trim().length() > 0) {
			checkFeedbackRectification = dissatisfiedFeedbackDao
					.get_feedbackRectficationDO_byId(feedbackRectification.getJwcpxt_feedback_rectification_id());
		}
		if (checkFeedbackRectification == null) {
			return false;
		}
		// 通过
		if (feedbackRectification.getFeedback_rectification_audit_state() != null
				&& "2".equals(feedbackRectification.getFeedback_rectification_audit_state())) {
			if (unit.getUnit_grade() == 0) {
				return false;
			}
			if (unit.getUnit_grade() == 1) {
				checkFeedbackRectification.setFeedback_rectification_audit_state("4");
				checkFeedbackRectification.setFeedback_rectification_cpzx_opinion(
						feedbackRectification.getFeedback_rectification_cpzx_opinion());
				checkFeedbackRectification.setFeedback_rectification_gmt_modified(TimeUtil.getStringSecond());
				dissatisfiedFeedbackDao.saveOrUpdateObject(checkFeedbackRectification);
				/*
				 * 测评中心通过之后，生成业务实例
				 */
				jwcpxt_service_instance serviceInstance = new jwcpxt_service_instance();

				// 设置业务定义为revisit
				serviceInstance.setService_instance_service_definition("revisit");

				// 设置所属单位，通过反馈整改和不满意反馈一路查出来
				serviceInstance.setService_instance_belong_unit(dissatisfiedFeedbackDao
						.get_unit_byDisFeedbackId(
								checkFeedbackRectification.getFeedback_rectification_dissatisfied_feedback())
						.getJwcpxt_unit_id());
				// 直接随机分配评测员
				jwcpxt_user user = userService.get_userDO_byRandomAndTypeCP();
				serviceInstance.setService_instance_judge(user.getJwcpxt_user_id());
				// 业务唯一识别编号，存反馈整改表的编号
				serviceInstance.setService_instance_nid(checkFeedbackRectification.getFeedback_rectification_no());

				// 业务办理时间，用反馈最后修改的时间
				serviceInstance
						.setService_instance_date(checkFeedbackRectification.getFeedback_rectification_gmt_modified());
				// 时间和ID
				serviceInstance.setJwcpxt_service_instance_id(uuidUtil.getUuid());
				serviceInstance.setService_instance_gmt_create(TimeUtil.getStringSecond());
				serviceInstance.setService_instance_gmt_modified(serviceInstance.getService_instance_gmt_create());

				// 保存
				serviceService.saveServiceInstance(serviceInstance);
				/*
				 * 
				 */
				jwcpxt_service_client oldService_client = dissatisfiedFeedbackDao
						.get_serviceClient_byDissatisfiedFeedbackId(
								checkFeedbackRectification.getFeedback_rectification_dissatisfied_feedback());
				// 分配生成当事人
				jwcpxt_service_client newServiceClient = new jwcpxt_service_client();
				newServiceClient.setJwcpxt_service_client_id(uuidUtil.getUuid());
				newServiceClient.setService_client_service_instance(serviceInstance.getJwcpxt_service_instance_id());
				newServiceClient.setService_client_name(oldService_client.getService_client_name());
				newServiceClient.setService_client_sex(oldService_client.getService_client_sex());
				newServiceClient.setService_client_phone(oldService_client.getService_client_phone());
				newServiceClient.setService_client_visit("2");
				newServiceClient.setService_client_gmt_create(TimeUtil.getStringSecond());
				newServiceClient.setService_client_gmt_modified(newServiceClient.getService_client_gmt_create());
				serviceService.saveServiceClient(newServiceClient);

				/*
				 * 
				 */

			} else if (unit.getUnit_grade() == 2) {
				checkFeedbackRectification.setFeedback_rectification_audit_state("2");
				checkFeedbackRectification.setFeedback_rectification_sjzgbm_opinion(
						feedbackRectification.getFeedback_rectification_cpzx_opinion());
				checkFeedbackRectification.setFeedback_rectification_gmt_modified(TimeUtil.getStringSecond());
				dissatisfiedFeedbackDao.saveOrUpdateObject(checkFeedbackRectification);
			}

			return true;
		} else if (feedbackRectification.getFeedback_rectification_audit_state() != null
				&& "3".equals(feedbackRectification.getFeedback_rectification_audit_state())) {
			// 驳回
			if (unit.getUnit_grade() == 0) {
				return false;
			}
			if (unit.getUnit_grade() == 1) {
				checkFeedbackRectification.setFeedback_rectification_audit_state("5");
				checkFeedbackRectification.setFeedback_rectification_cpzx_opinion(
						feedbackRectification.getFeedback_rectification_cpzx_opinion());
			} else if (unit.getUnit_grade() == 2) {
				checkFeedbackRectification.setFeedback_rectification_audit_state("3");
				checkFeedbackRectification.setFeedback_rectification_sjzgbm_opinion(
						feedbackRectification.getFeedback_rectification_cpzx_opinion());
			}
			checkFeedbackRectification.setFeedback_rectification_gmt_modified(TimeUtil.getStringSecond());
			dissatisfiedFeedbackDao.saveOrUpdateObject(checkFeedbackRectification);
			newFeedbackRectification = checkFeedbackRectification;
			newFeedbackRectification.setJwcpxt_feedback_rectification_id(uuidUtil.getUuid());
			// 获取当月最大反馈整改表数
			String maxMounthFeedbackRectifi = dissatisfiedFeedbackDao.get_maxMounthFeedbackRectifi();
			newFeedbackRectification
					.setFeedback_rectification_no((Integer.parseInt(maxMounthFeedbackRectifi) + 1) + "");
			newFeedbackRectification.setFeedback_rectification_handle_state("1");
			newFeedbackRectification.setFeedback_rectification_date("");
			newFeedbackRectification.setFeedback_rectification_content("");
			newFeedbackRectification.setFeedback_rectification_sjzgbm_opinion("");
			newFeedbackRectification.setFeedback_rectification_cpzx_opinion("");
			newFeedbackRectification.setFeedback_rectification_audit_state("1");
			newFeedbackRectification.setFeedback_rectification_gmt_create(TimeUtil.getStringSecond());
			newFeedbackRectification.setFeedback_rectification_gmt_modified(
					newFeedbackRectification.getFeedback_rectification_gmt_create());
			dissatisfiedFeedbackDao.saveOrUpdateObject(newFeedbackRectification);
			if (unit.getUnit_grade() == 1) {
				// 一级单位驳回
				jwcpxt_unit dutyUnit = new jwcpxt_unit();
				dutyUnit = dissatisfiedFeedbackDao.get_unit_byDisFeedbackId(
						newFeedbackRectification.getFeedback_rectification_dissatisfied_feedback());
				if (dutyUnit != null) {
					// 通知责任单位
					String unitDXNR = "测评中心驳回了编号为" + newFeedbackRectification.getFeedback_rectification_no()
							+ "的反馈整改,请在5个自然日内完成整改，并由主管单位进行审核";
					// 发短信
					SendMessageUtil sendMessageUtil = new SendMessageUtil(dutyUnit.getUnit_phone(), unitDXNR);
					/*
					 * try { sendMessageUtil.send(); } catch (IOException e) { e.printStackTrace();
					 * }
					 */
					// 通知上级单位
					jwcpxt_unit dutyUnitFather = new jwcpxt_unit();
					dutyUnitFather = unitService.get_unitDO_byID(dutyUnit.getUnit_father());
					if (dutyUnitFather != null) {
						// 通知责任单位
						unitDXNR = "测评中心驳回了编号为" + newFeedbackRectification.getFeedback_rectification_no()
								+ "的反馈整改,请在5个自然日内监督下属单位整改并进行审核";
						// 发短信
						sendMessageUtil = new SendMessageUtil(dutyUnitFather.getUnit_phone(), unitDXNR);
						/*
						 * try { sendMessageUtil.send(); } catch (IOException e) { e.printStackTrace();
						 * }
						 */
					}
				}
			} else if (unit.getUnit_grade() == 2) {
				// 二级单位进行驳回
				jwcpxt_unit dutyUnit = new jwcpxt_unit();
				dutyUnit = dissatisfiedFeedbackDao.get_unit_byDisFeedbackId(
						newFeedbackRectification.getFeedback_rectification_dissatisfied_feedback());
				if (dutyUnit != null) {
					// 通知责任单位
					String unitDXNR = "主管单位驳回了编号为" + newFeedbackRectification.getFeedback_rectification_no()
							+ "的反馈整改,请在5个自然日内完成整改，并由主管单位重新进行审核";
					// 发短信
					SendMessageUtil sendMessageUtil = new SendMessageUtil(dutyUnit.getUnit_phone(), unitDXNR);
					/*
					 * try { sendMessageUtil.send(); } catch (IOException e) { e.printStackTrace();
					 * }
					 */
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * 获取审核的整改反馈表
	 */
	@Override
	public CheckFeedbackRectificationVO get_checkFeedbackRectificationVO(
			CheckFeedbackRectificationVO checkFeedbackRectificationVO, jwcpxt_unit unit) {
		// 定义
		List<FeedbackRectificationDTO> listFeedbackRectificationDTO = new ArrayList<>();
		if (unit.getJwcpxt_unit_id() != null && unit.getJwcpxt_unit_id().trim().length() > 0) {
			// 获取unit
			unit = unitService.get_unitDO_byID(unit.getJwcpxt_unit_id());
		}
		if (unit == null) {
			return checkFeedbackRectificationVO;
		}
		// 获取总记录数
		int totalRecords = dissatisfiedFeedbackDao.get_checkFeedbackRectificationVOCount(checkFeedbackRectificationVO,
				unit);
		// 总页数
		int totalPages = ((totalRecords - 1) / checkFeedbackRectificationVO.getPageSize()) + 1;
		listFeedbackRectificationDTO = dissatisfiedFeedbackDao
				.get_checkFeedbackRectificationVO(checkFeedbackRectificationVO, unit);
		// set
		checkFeedbackRectificationVO.setTotalCount(totalRecords);
		checkFeedbackRectificationVO.setTotalPage(totalPages);
		checkFeedbackRectificationVO.setListFeedbackRectificationDTO(listFeedbackRectificationDTO);
		return checkFeedbackRectificationVO;
	}

	/**
	 * 整改VO
	 */
	@Override
	public FeedbackRectificationVO get_feedbackRectificationVO(FeedbackRectificationVO feedbackRectificationVO,
			jwcpxt_unit unit) {
		if (unit == null) {
			return feedbackRectificationVO;
		}
		if (unit.getJwcpxt_unit_id() != null && unit.getJwcpxt_unit_id().trim().length() > 0) {
			// 获取unit
			unit = unitService.get_unitDO_byID(unit.getJwcpxt_unit_id());
		}
		if (unit == null) {
			return null;
		}
		List<FeedbackRectificationDTO> listFeedbackRectificationDTO = new ArrayList<>();
		// 获取总记录数
		int totalRecords = dissatisfiedFeedbackDao.get_countFeedbackRectificationVO(feedbackRectificationVO, unit);
		// 总页数
		int totalPages = ((totalRecords - 1) / feedbackRectificationVO.getPageSize()) + 1;
		// 获取数据
		listFeedbackRectificationDTO = dissatisfiedFeedbackDao.get_feedbackRectificationVO(feedbackRectificationVO,
				unit);
		// set
		feedbackRectificationVO.setTotalCount(totalRecords);
		feedbackRectificationVO.setTotalPage(totalPages);
		feedbackRectificationVO.setListFeedbackRectificationDTO(listFeedbackRectificationDTO);
		return feedbackRectificationVO;
	}

	/**
	 * 办结操作
	 */
	@Override
	public boolean update_dissatisfiedFeedbackState_toEnd(jwcpxt_feedback_rectification feedbackRectification) {
		jwcpxt_feedback_rectification feeRectification = new jwcpxt_feedback_rectification();
		if (feedbackRectification != null && feedbackRectification.getJwcpxt_feedback_rectification_id() != null
				&& feedbackRectification.getJwcpxt_feedback_rectification_id().trim().length() > 0) {
			feeRectification = dissatisfiedFeedbackDao
					.get_feedbackRectficationDO_byId(feedbackRectification.getJwcpxt_feedback_rectification_id());
		}
		if (feeRectification == null) {
			return false;
		}
		feeRectification.setFeedback_rectification_handle_state("2");
		feeRectification.setFeedback_rectification_date(TimeUtil.getStringSecond());
		feeRectification.setFeedback_rectification_content(feedbackRectification.getFeedback_rectification_content());
		feeRectification.setFeedback_rectification_gmt_modified(TimeUtil.getStringSecond());
		dissatisfiedFeedbackDao.saveOrUpdateObject(feeRectification);
		// 发送短信到主管单位
		jwcpxt_unit unit = new jwcpxt_unit();
		jwcpxt_unit unitFather = new jwcpxt_unit();
		unit = dissatisfiedFeedbackDao
				.get_unit_byDisFeedbackId(feeRectification.getFeedback_rectification_dissatisfied_feedback());
		unitFather = unitService.get_unitDO_byID(unit.getUnit_father());
		if (unitFather != null) {
			// 发短信lo
			String unitDXNR = "下级单位<" + unit.getUnit_name() + ">已经完成编号为"
					+ feeRectification.getFeedback_rectification_no() + "的反馈整改,请尽快对该整改情况进行审核。";
			// 发短信
			SendMessageUtil sendMessageUtil = new SendMessageUtil(unitFather.getUnit_phone(), unitDXNR);
			/*
			 * try { sendMessageUtil.send(); } catch (IOException e) { e.printStackTrace();
			 * }
			 */
		}
		return true;
	}

	/**
	 * 通过整改反馈id获得一条记录
	 * 
	 * @param feedbackRectification
	 * @return
	 */
	@Override
	public jwcpxt_feedback_rectification get_feedbackRectficationDO_byId(
			jwcpxt_feedback_rectification feedbackRectification) {
		if (feedbackRectification != null && feedbackRectification.getJwcpxt_feedback_rectification_id() != null
				&& feedbackRectification.getJwcpxt_feedback_rectification_id().trim().length() > 0) {
			feedbackRectification = dissatisfiedFeedbackDao
					.get_feedbackRectficationDO_byId(feedbackRectification.getJwcpxt_feedback_rectification_id());
		}
		return feedbackRectification;
	}

	/**
	 * 通过不满意反馈id获得一条记录
	 * 
	 * @param dissatisfiedFeedback
	 * @return
	 */
	@Override
	public jwcpxt_dissatisfied_feedback get_dissatisfiedFeedbackDO_byId(
			jwcpxt_dissatisfied_feedback dissatisfiedFeedback) {
		if (dissatisfiedFeedback != null && dissatisfiedFeedback.getJwcpxt_dissatisfied_feedback_id() != null
				&& dissatisfiedFeedback.getJwcpxt_dissatisfied_feedback_id().trim().length() > 0) {
			dissatisfiedFeedback = dissatisfiedFeedbackDao
					.get_dissatisfiedFeedbackDo_byId(dissatisfiedFeedback.getJwcpxt_dissatisfied_feedback_id());
		}
		return dissatisfiedFeedback;
	}

	/**
	 * 推送
	 */
	@Override
	public boolean updade_dissatisfiedFeedbackState_toPush(jwcpxt_dissatisfied_feedback dissatisfiedFeedback,
			jwcpxt_feedback_rectification feedbackRectification) {
		// 定义
		jwcpxt_dissatisfied_feedback disFeedback = new jwcpxt_dissatisfied_feedback();
		jwcpxt_service_client serviceClient = new jwcpxt_service_client();
		jwcpxt_unit unit = new jwcpxt_unit();
		// 更改不满意反馈表的状态
		if (dissatisfiedFeedback != null && dissatisfiedFeedback.getJwcpxt_dissatisfied_feedback_id() != null
				&& dissatisfiedFeedback.getJwcpxt_dissatisfied_feedback_id().trim().length() > 0) {
			disFeedback = dissatisfiedFeedbackDao
					.get_dissatisfiedFeedbackDo_byId(dissatisfiedFeedback.getJwcpxt_dissatisfied_feedback_id());
		}
		if (disFeedback == null) {
			return false;
		}
		disFeedback.setDissatisfied_feedback_state("2");
		disFeedback
				.setDissatisfied_feedback_audit_opinion(dissatisfiedFeedback.getDissatisfied_feedback_audit_opinion());
		disFeedback.setDissatisfied_feedback_gmt_modified(TimeUtil.getStringSecond());
		dissatisfiedFeedbackDao.saveOrUpdateObject(disFeedback);
		// 生成反馈整改表
		feedbackRectification.setJwcpxt_feedback_rectification_id(uuidUtil.getUuid());
		feedbackRectification
				.setFeedback_rectification_dissatisfied_feedback(disFeedback.getJwcpxt_dissatisfied_feedback_id());
		// 编号
		// 获取当月最大反馈整改表数
		String maxMounthFeedbackRectifi = dissatisfiedFeedbackDao.get_maxMounthFeedbackRectifi();
		feedbackRectification.setFeedback_rectification_no((Integer.parseInt(maxMounthFeedbackRectifi) + 1) + "");
		// 收集时间----不反馈
		feedbackRectification.setFeedback_rectification_collect_time(disFeedback.getDissatisfied_feedback_gmt_create());
		serviceClient = dissatisfiedFeedbackDao
				.get_serviceClient_byDissatisfiedFeedbackId(disFeedback.getJwcpxt_dissatisfied_feedback_id());
		feedbackRectification.setFeedback_rectification_client_name(serviceClient.getService_client_name());
		feedbackRectification.setFeedback_rectification_client_phone(serviceClient.getService_client_phone());
		// 责任单位
		unit = dissatisfiedFeedbackDao.get_unit_byDisFeedbackId(disFeedback.getJwcpxt_dissatisfied_feedback_id());
		feedbackRectification.setFeedback_rectification_unit_name(unit.getUnit_name());
		feedbackRectification.setFeedback_rectification_unit_people(unit.getUnit_contacts_name());
		feedbackRectification.setFeedback_rectification_unit_people_phone(unit.getUnit_phone());
		feedbackRectification.setFeedback_rectification_handle_state("1");
		feedbackRectification.setFeedback_rectification_audit_state("1");
		feedbackRectification.setFeedback_rectification_gmt_create(TimeUtil.getStringSecond());
		feedbackRectification
				.setFeedback_rectification_gmt_modified(feedbackRectification.getFeedback_rectification_gmt_create());
		dissatisfiedFeedbackDao.saveOrUpdateObject(feedbackRectification);
		// 直接更改同一当事人在统一单位的其他不满意反馈
		dissatisfiedFeedbackDao.updateDissatisfiedClient(serviceClient.getJwcpxt_service_client_id(),
				unit.getJwcpxt_unit_id());
		/*
		 * try {
		 *//**
			 * 推送的时候需要短信通知
			 *//*
				 * // 责任单位短信 if (unit != null) { String unitDXNR = "单位<" + unit.getUnit_name() +
				 * ">接受到一条整改信息,请在5个自然日内对该整改做出反馈,并完成主管部门的审核。"; // 发短信 SendMessageUtil
				 * sendMessageUtil = new SendMessageUtil(unit.getUnit_phone(), unitDXNR); //
				 * sendMessageUtil.send(); } // 根据责任单位获取该责任单位的上级单位 unitFather =
				 * unitService.get_unitDO_byID(unit.getUnit_father()); // unitFather = //
				 * dissatisfiedFeedbackDao.get_unitDO_byChildrenUnit(unit.getJwcpxt_unit_id());
				 * if (unitFather != null) { // 通知上级单位的短信 String fatherUnitDXNR = "您的下属单位<" +
				 * unit.getUnit_name() + ">,接收到一条整改要求,请在5个自然日内监督下属单位进行整改，并对整改结果进行审核"; // 发短信
				 * SendMessageUtil sendMessageUtil = new
				 * SendMessageUtil(unitFather.getUnit_phone(), fatherUnitDXNR);
				 * sendMessageUtil.send(); } } catch (IOException e) { e.printStackTrace(); }
				 */
		return true;
	}

	/**
	 * 驳回不满意反馈表
	 */
	@Override
	public boolean update_dissatisfiedFeedbackState_toReject(jwcpxt_dissatisfied_feedback dissatisfiedFeedback) {
		// 定义
		jwcpxt_dissatisfied_feedback disFeedback = new jwcpxt_dissatisfied_feedback();
		// 根据id获取 不满意反馈表
		if (dissatisfiedFeedback != null && dissatisfiedFeedback.getJwcpxt_dissatisfied_feedback_id() != null
				&& dissatisfiedFeedback.getJwcpxt_dissatisfied_feedback_id().trim().length() > 0) {
			disFeedback = dissatisfiedFeedbackDao
					.get_dissatisfiedFeedbackDo_byId(dissatisfiedFeedback.getJwcpxt_dissatisfied_feedback_id());
		}
		if (dissatisfiedFeedback == null) {
			return false;
		}
		disFeedback.setDissatisfied_feedback_state("3");
		disFeedback
				.setDissatisfied_feedback_audit_opinion(dissatisfiedFeedback.getDissatisfied_feedback_audit_opinion());
		disFeedback.setDissatisfied_feedback_gmt_modified(TimeUtil.getStringSecond());
		dissatisfiedFeedbackDao.saveOrUpdateObject(disFeedback);

		return true;
	}

	/**
	 * 获取不满意反馈表VO
	 */
	@Override
	public DissatisfiedQuestionVO get_dissatisfiedQuestionVO(DissatisfiedQuestionVO dissatisfiedQuestionVO) {
		List<DissatisfiedQuestionDTO> listDissatisfiedQuestionDTO = new ArrayList<>();
		// 获取总数量
		int totalRecords = dissatisfiedFeedbackDao.get_countDissatisfiedQuestionVO(dissatisfiedQuestionVO);
		// 总页数
		int totalPages = ((totalRecords - 1) / dissatisfiedQuestionVO.getPageSize()) + 1;
		// 获取分页中的数据
		listDissatisfiedQuestionDTO = dissatisfiedFeedbackDao.get_dataDissatisfiedQuestionVO(dissatisfiedQuestionVO);
		dissatisfiedQuestionVO.setTotalCount(totalRecords);
		dissatisfiedQuestionVO.setTotalPage(totalPages);
		dissatisfiedQuestionVO.setListDissatisfiedQuestionDTO(listDissatisfiedQuestionDTO);
		return dissatisfiedQuestionVO;
	}

	/**
	 * 
	 * 
	 */
	public QuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public ServiceService getServiceService() {
		return serviceService;
	}

	public void setServiceService(ServiceService serviceService) {
		this.serviceService = serviceService;
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

	public DissatisfiedFeedbackDao getDissatisfiedFeedbackDao() {
		return dissatisfiedFeedbackDao;
	}

	public void setDissatisfiedFeedbackDao(DissatisfiedFeedbackDao dissatisfiedFeedbackDao) {
		this.dissatisfiedFeedbackDao = dissatisfiedFeedbackDao;
	}

}
