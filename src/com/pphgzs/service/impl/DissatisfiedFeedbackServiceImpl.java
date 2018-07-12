package com.pphgzs.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.pphgzs.dao.DissatisfiedFeedbackDao;
import com.pphgzs.domain.DO.jwcpxt_dissatisfied_feedback;
import com.pphgzs.domain.DTO.DissatisfiedQuestionDTO;
import com.pphgzs.domain.VO.DissatisfiedQuestionVO;
import com.pphgzs.service.DissatisfiedFeedbackService;
import com.pphgzs.service.QuestionService;
import com.pphgzs.service.ServiceService;
import com.pphgzs.service.UnitService;
import com.pphgzs.util.TimeUtil;

public class DissatisfiedFeedbackServiceImpl implements DissatisfiedFeedbackService {
	private DissatisfiedFeedbackDao dissatisfiedFeedbackDao;
	private ServiceService serviceService;
	private QuestionService questionService;
	private UnitService unitService;

	/**
	 * 推送
	 */
	@Override
	public boolean updade_dissatisfiedFeedbackState_toPush(jwcpxt_dissatisfied_feedback dissatisfiedFeedback) {
		
		return false;
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
		disFeedback.setDissatisfied_feedback_state(3);
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
