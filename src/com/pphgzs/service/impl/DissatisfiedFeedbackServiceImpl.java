package com.pphgzs.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.pphgzs.dao.DissatisfiedFeedbackDao;
import com.pphgzs.domain.DO.jwcpxt_dissatisfied_feedback;
import com.pphgzs.domain.DO.jwcpxt_feedback_rectification;
import com.pphgzs.domain.DO.jwcpxt_unit;
import com.pphgzs.domain.DO.jwcpxt_user;
import com.pphgzs.domain.DTO.RectificationFeedbackDTO;
import com.pphgzs.domain.DTO.UnitDTO;
import com.pphgzs.domain.VO.DissatisfiedFeedbackVO;
import com.pphgzs.service.DissatisfiedFeedbackService;
import com.pphgzs.service.QuestionService;
import com.pphgzs.service.ServiceService;
import com.pphgzs.service.UnitService;

public class DissatisfiedFeedbackServiceImpl implements DissatisfiedFeedbackService {
	private DissatisfiedFeedbackDao dissatisfiedFeedbackDao;
	private ServiceService serviceService;
	private QuestionService questionService;
	private UnitService unitService;

	/**
	 * 获取反馈整改VO
	 */
	@Override
	public DissatisfiedFeedbackVO get_dissatisfiedFeedbackVO(DissatisfiedFeedbackVO dissatisfiedFeedbackVO) {
		// 定义
		List<RectificationFeedbackDTO> listRectificationFeedback = new ArrayList<>();
		// 获取DTO
		listRectificationFeedback = dissatisfiedFeedbackDao.get_listRectificationFeedbackDTO(dissatisfiedFeedbackVO);
		// 获取总记录数
		int totalRecords = dissatisfiedFeedbackDao.get_listRectificationFeedbackDTOCount(dissatisfiedFeedbackVO);
		int totalPages = ((totalRecords - 1) / dissatisfiedFeedbackVO.getPageSize()) + 1;
		dissatisfiedFeedbackVO.setListRectificationFeedback(listRectificationFeedback);
		dissatisfiedFeedbackVO.setTotalPage(totalPages);
		dissatisfiedFeedbackVO.setTotalCount(totalRecords);
		return dissatisfiedFeedbackVO;
	}

	/**
	 * 根据整改反馈id获取整改反馈
	 */
	@Override
	public jwcpxt_feedback_rectification get_feedbackRectification_byRectificationId(
			jwcpxt_feedback_rectification feedbackRectification) {
		jwcpxt_feedback_rectification feedBackRectification = new jwcpxt_feedback_rectification();
		if (feedbackRectification != null && feedbackRectification.getJwcpxt_feedback_rectification_id() != null
				&& feedbackRectification.getJwcpxt_feedback_rectification_id().trim().length() > 0) {
			feedBackRectification = dissatisfiedFeedbackDao.get_feedbackRectification_byRectificationId(
					feedbackRectification.getJwcpxt_feedback_rectification_id().trim());
		}
		return null;
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
