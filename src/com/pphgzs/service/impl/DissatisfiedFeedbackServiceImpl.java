package com.pphgzs.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.pphgzs.dao.DissatisfiedFeedbackDao;
import com.pphgzs.domain.DO.jwcpxt_dissatisfied_feedback;
import com.pphgzs.domain.DO.jwcpxt_feedback_rectification;
import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_unit;
import com.pphgzs.domain.DTO.DissatisfiedQuestionDTO;
import com.pphgzs.domain.VO.CheckFeedbackRectificationVO;
import com.pphgzs.domain.VO.DissatisfiedQuestionVO;
import com.pphgzs.domain.VO.FeedbackRectificationVO;
import com.pphgzs.service.DissatisfiedFeedbackService;
import com.pphgzs.service.QuestionService;
import com.pphgzs.service.ServiceService;
import com.pphgzs.service.UnitService;
import com.pphgzs.util.TimeUtil;
import com.pphgzs.util.uuidUtil;

public class DissatisfiedFeedbackServiceImpl implements DissatisfiedFeedbackService {
	private DissatisfiedFeedbackDao dissatisfiedFeedbackDao;
	private ServiceService serviceService;
	private QuestionService questionService;
	private UnitService unitService;

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
		//
		if (feedbackRectification != null && feedbackRectification.getJwcpxt_feedback_rectification_id() != null
				&& feedbackRectification.getJwcpxt_feedback_rectification_id().trim().length() > 0) {
			checkFeedbackRectification = dissatisfiedFeedbackDao
					.get_feedbackRectficationDO_byId(feedbackRectification.getJwcpxt_feedback_rectification_id());
		}
		if (checkFeedbackRectification == null) {
			return false;
		}
		/*
		 * if (unit.getUnit_grade() == 1) { if
		 * (feedbackRectification.getFeedback_rectification_audit_state() != null &&
		 * "2".equals(feedbackRectification.getFeedback_rectification_audit_state())) {
		 * checkFeedbackRectification.setFeedback_rectification_audit_state("4"); } else
		 * if (feedbackRectification.getFeedback_rectification_audit_state() != null &&
		 * "3".equals(feedbackRectification.getFeedback_rectification_audit_state())) {
		 * checkFeedbackRectification.setFeedback_rectification_audit_state("5"); } }
		 * else if (unit.getUnit_grade() == 2) {
		 * 
		 * }
		 */
		//
		if (feedbackRectification.getFeedback_rectification_audit_state() != null
				&& "2".equals(feedbackRectification.getFeedback_rectification_audit_state())) {
			// 通过
			if (unit.getUnit_grade() == 0) {
				return false;
			}
			if (unit.getUnit_grade() == 1) {
				checkFeedbackRectification.setFeedback_rectification_audit_state("4");
			} else if (unit.getUnit_grade() == 2) {
				checkFeedbackRectification.setFeedback_rectification_audit_state("2");
			}

		} else if (feedbackRectification.getFeedback_rectification_audit_state() != null
				&& "3".equals(feedbackRectification.getFeedback_rectification_audit_state())) {
			if (unit.getUnit_grade() == 0) {
				return false;
			}
			if (unit.getUnit_grade() == 1) {
				checkFeedbackRectification.setFeedback_rectification_audit_state("5");
			} else if (unit.getUnit_grade() == 2) {
				checkFeedbackRectification.setFeedback_rectification_audit_state("3");
			}
		}
		return true;
	}

	/**
	 * 获取审核的整改反馈表
	 */
	@Override
	public CheckFeedbackRectificationVO get_checkFeedbackRectificationVO(
			CheckFeedbackRectificationVO checkFeedbackRectificationVO, jwcpxt_unit unit) {
		// 定义
		List<jwcpxt_feedback_rectification> listFeedbackRectification = new ArrayList<>();
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
		listFeedbackRectification = dissatisfiedFeedbackDao
				.get_checkFeedbackRectificationVO(checkFeedbackRectificationVO, unit);
		// set
		checkFeedbackRectificationVO.setTotalCount(totalRecords);
		checkFeedbackRectificationVO.setTotalPage(totalPages);
		checkFeedbackRectificationVO.setListCheckFeedbackRectification(listFeedbackRectification);
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
		List<jwcpxt_feedback_rectification> listFeedbackRectification = new ArrayList<>();
		// 获取总记录数
		int totalRecords = dissatisfiedFeedbackDao.get_countFeedbackRectificationVO(feedbackRectificationVO, unit);
		// 总页数
		int totalPages = ((totalRecords - 1) / feedbackRectificationVO.getPageSize()) + 1;
		// 获取数据
		listFeedbackRectification = dissatisfiedFeedbackDao.get_feedbackRectificationVO(feedbackRectificationVO, unit);
		// set
		feedbackRectificationVO.setTotalCount(totalRecords);
		feedbackRectificationVO.setTotalPage(totalPages);
		feedbackRectificationVO.setListFeedbackRectification(listFeedbackRectification);
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
				.get_serviceClient_byDisFeedbackId(disFeedback.getJwcpxt_dissatisfied_feedback_id());
		feedbackRectification.setFeedback_rectification_client_name(serviceClient.getService_client_name());
		feedbackRectification.setFeedback_rectification_client_phone(serviceClient.getService_client_phone());
		// 责任单位
		unit = dissatisfiedFeedbackDao.get_unit_byDisFeedbackId(disFeedback.getJwcpxt_dissatisfied_feedback_id());
		feedbackRectification.setFeedback_rectification_unit_name(unit.getUnit_name());
		feedbackRectification.setFeedback_rectification_unit_name(unit.getUnit_contacts_name());
		feedbackRectification.setFeedback_rectification_unit_people_phone(unit.getUnit_phone());
		feedbackRectification.setFeedback_rectification_handle_state("1");
		feedbackRectification.setFeedback_rectification_audit_state("1");
		feedbackRectification.setFeedback_rectification_gmt_create(TimeUtil.getStringSecond());
		feedbackRectification
				.setFeedback_rectification_gmt_modified(feedbackRectification.getFeedback_rectification_gmt_create());
		dissatisfiedFeedbackDao.saveOrUpdateObject(feedbackRectification);
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

	public DissatisfiedFeedbackDao getDissatisfiedFeedbackDao() {
		return dissatisfiedFeedbackDao;
	}

	public void setDissatisfiedFeedbackDao(DissatisfiedFeedbackDao dissatisfiedFeedbackDao) {
		this.dissatisfiedFeedbackDao = dissatisfiedFeedbackDao;
	}

}
