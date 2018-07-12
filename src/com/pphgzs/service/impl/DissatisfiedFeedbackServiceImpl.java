package com.pphgzs.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.pphgzs.dao.DissatisfiedFeedbackDao;
import com.pphgzs.domain.DTO.DissatisfiedQuestionDTO;
import com.pphgzs.domain.VO.DissatisfiedQuestionVO;
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
