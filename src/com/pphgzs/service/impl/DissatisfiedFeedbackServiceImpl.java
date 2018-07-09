package com.pphgzs.service.impl;

import com.pphgzs.dao.DissatisfiedFeedbackDao;
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
	/**
	 * 
	 */
}
