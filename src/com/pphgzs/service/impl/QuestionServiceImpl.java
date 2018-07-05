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

	@Override
	public QuestionVO get_questionVO(QuestionVO questionVO) {
		// 新建
		List<jwcpxt_question> questionList = new ArrayList<>();
		// 根据业务Id获取业务对象
		ServiceDefinitionDTO serviceDefinitionDTO = new ServiceDefinitionDTO(null, null);
		// 首先根据业务定义Id获取业务定义对象
		/*serviceDefinitionDTO = serviceService.get_serviceDefinitionDTO_byServiceDefinitionID(
				questionVO.getServiceDefinitionDTO().getServiceDefinition().getJwcpxt_service_definition_id());*/
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

}