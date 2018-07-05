package com.pphgzs.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.pphgzs.dao.QuestionDao;
import com.pphgzs.domain.DO.jwcpxt_question;
import com.pphgzs.domain.DTO.ServiceDefinitionDTO;
import com.pphgzs.domain.VO.QuestionVO;
import com.pphgzs.service.QuestionService;
import com.pphgzs.service.ServiceService;
import com.pphgzs.service.UnitService;
import com.pphgzs.util.TimeUtil;
import com.pphgzs.util.uuidUtil;

public class QuestionServiceImpl implements QuestionService {
	QuestionDao questionDao;
	ServiceService serviceService;

	UnitService unitService;

	public QuestionDao getQuestionDao() {
		return questionDao;
	}

	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
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

	/**
	 * 获取某业务的问题列表
	 */
	@Override
	public QuestionVO get_questionVO(QuestionVO questionVO) {
		// 新建
		List<jwcpxt_question> questionList = new ArrayList<>();
		// 根据业务Id获取业务对象
		ServiceDefinitionDTO serviceDefinitionDTO = new ServiceDefinitionDTO(null, null);
		// 首先根据业务定义Id获取业务定义对象
		serviceDefinitionDTO = serviceService.get_serviceDefinitionDTO_byServiceDefinitionID(
				questionVO.getServiceDefinitionDTO().getServiceDefinition().getJwcpxt_service_definition_id());
		// 获取分页里面的question
		questionList = questionDao.list_question_byQuestionVO(questionVO);
		// 获取总记录数
		int totalRecords = questionDao.get_questionTotalCount_byQuestionVO(questionVO);
		// 总页数
		int totalPages = ((questionVO.getTotalCount() - 1) / questionVO.getPageSize()) + 1;
		questionVO.setServiceDefinitionDTO(serviceDefinitionDTO);
		questionVO.setQuestionList(questionList);
		questionVO.setTotalCount(totalRecords);
		questionVO.setTotalPage(totalPages);
		return questionVO;
	}

	/**
	 * 保存问题
	 */
	public boolean save_question(jwcpxt_question question) {
		question.setJwcpxt_question_id(uuidUtil.getUuid());
		question.setQuestion_gmt_create(TimeUtil.getStringSecond());
		question.setQuestion_gmt_modified(TimeUtil.getStringSecond());
		// 问题顺序
		// 根据业务定义id获取该业务的最大问题顺序
		int questionSort = questionDao.get_question_max_sort(question.getQuestion_service_definition());
		question.setQuestion_sort(questionSort + 1);
		try {
			questionDao.saveOrUpdateObject(question);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 更新问题
	 */
	@Override
	public boolean update_question(jwcpxt_question question) {
		// 根据问题Id获取问题信息
		jwcpxt_question oldQuestion = new jwcpxt_question();
		if (question != null && question.getQuestion_service_definition() != null
				&& question.getQuestion_service_definition().trim().length() > 0) {
			oldQuestion = questionDao.get_question_byQuestionId(question.getQuestion_service_definition().trim());
		} else {
			return false;
		}
		if (oldQuestion == null) {
			return false;
		}
		// 更改信息
		oldQuestion.setQuestion_describe(question.getQuestion_describe());
		oldQuestion.setQuestion_type(question.getQuestion_type());
		oldQuestion.setQuestion_gmt_modified(TimeUtil.getStringSecond());
		try {
			questionDao.saveOrUpdateObject(question);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}