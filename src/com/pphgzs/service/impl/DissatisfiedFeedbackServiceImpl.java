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
		List<jwcpxt_feedback_rectification> listFeedbackRectification = new ArrayList<>();
		RectificationFeedbackDTO rectificationFeedbackDTO = new RectificationFeedbackDTO();
		jwcpxt_feedback_rectification feedbackRectification = new jwcpxt_feedback_rectification();
		jwcpxt_dissatisfied_feedback dissatisfiedFeedback = new jwcpxt_dissatisfied_feedback();
		UnitDTO unitDTO = new UnitDTO();
		jwcpxt_unit unit = new jwcpxt_unit();
		jwcpxt_user user = new jwcpxt_user();
		/*
		 * listRectificationFeedback = dissatisfiedFeedbackDao
		 * .list_rectificationFeedback_byDissatisfiedFeedbackVO(dissatisfiedFeedbackVO);
		 */
		// 获取反馈整改表信息
		listFeedbackRectification = dissatisfiedFeedbackDao.get_listFeedbackRecitification(dissatisfiedFeedbackVO);

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
