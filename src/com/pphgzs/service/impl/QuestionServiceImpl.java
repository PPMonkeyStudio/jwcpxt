package com.pphgzs.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.pphgzs.dao.QuestionDao;
import com.pphgzs.domain.DO.jwcpxt_answer_choice;
import com.pphgzs.domain.DO.jwcpxt_answer_open;
import com.pphgzs.domain.DO.jwcpxt_option;
import com.pphgzs.domain.DO.jwcpxt_option_inquiries;
import com.pphgzs.domain.DO.jwcpxt_question;
import com.pphgzs.domain.DTO.OptionDTO;
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
		System.out.println("totalRecords:" + totalRecords);
		// 总页数
		// staffManagerVO.setTotalPages(((userInfoCount - 1) /
		// staffManagerVO.getPageSize()) + 1);
		int totalPages = ((totalRecords - 1) / questionVO.getPageSize()) + 1;
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
		question.setQuestion_gmt_modified(question.getQuestion_gmt_create());
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
		// 定义标识是否更改了问题类型
		// ==0 没有更改
		// ==1 从开放题变成选择题
		// int flag = 0;

		if (question != null && question.getJwcpxt_question_id() != null
				&& question.getJwcpxt_question_id().trim().length() > 0) {
			oldQuestion = questionDao.get_question_byQuestionId(question.getJwcpxt_question_id());
		} else {
			return false;
		}
		if (oldQuestion == null) {
			return false;
		}
		// 原有类型
		// String oldType = oldQuestion.getQuestion_type();
		// 更改信息
		oldQuestion.setQuestion_describe(question.getQuestion_describe());
		oldQuestion.setQuestion_type(question.getQuestion_type());
		oldQuestion.setQuestion_gmt_modified(TimeUtil.getStringSecond());
		try {
			questionDao.saveOrUpdateObject(oldQuestion);
		} catch (Exception e) {
			return false;
		}
		// 是否更改了问题类型
		return true;
	}

	/**
	 * 移动问题
	 */
	@Override
	public boolean move_question_sort(jwcpxt_question question, String moveQuestionType) {
		// 定义
		int maxQuestionSort = 0;
		int minQuestionSort = 0;
		int currentQuestionSort = 0;
		jwcpxt_question moveQuestion = new jwcpxt_question();
		// 判断参数
		if (question != null && question.getJwcpxt_question_id() != null
				&& question.getJwcpxt_question_id().trim().length() > 0) {
			// 获取问题对象
			question = questionDao.get_question_byQuestionId(question.getJwcpxt_question_id().trim());
		} else {
			return false;
		}
		if (question == null) {
			return false;
		}
		if (question != null && question.getQuestion_service_definition() != null
				&& question.getQuestion_service_definition().trim().length() > 0) {
			// 根据业务id获取该问题的最小排序以及最大排序
			maxQuestionSort = questionDao.get_question_max_sort(question.getQuestion_service_definition());
			minQuestionSort = questionDao.get_question_min_sort(question.getQuestion_service_definition());
		} else {
			return false;
		}
		if (moveQuestionType != null && moveQuestionType.trim().length() > 0) {
			// 1 为上移
			// 将当前值的位置进行存储
			currentQuestionSort = question.getQuestion_sort();
			if ("1".equals(moveQuestionType.trim())) {
				// 如果是最上面的就不能进行移动
				if (minQuestionSort == currentQuestionSort) {
					System.out.println("当前已经是第一个");
					return false;
				}
				// 否则就能进行移动
				// 获取比他小的最大的问题对象
				moveQuestion = questionDao.get_question_moveUpPosition_sort(question);
			} else if ("2".equals(moveQuestionType.trim())) {
				// 如果是最小面的就不能进行移动
				if (maxQuestionSort == currentQuestionSort) {
					System.out.println("当前已经是最后一个");
					return false;
				}
				// 否则就能进行移动
				// 获取比他大的最小的那个对象
				moveQuestion = questionDao.get_question_moveDownPosition_sort(question);
			}
		} else {
			return false;
		}
		// 分别进行存储
		// 存储当前行
		question.setQuestion_sort(moveQuestion.getQuestion_sort());
		question.setQuestion_gmt_modified(TimeUtil.getStringSecond());
		try {
			questionDao.saveOrUpdateObject(question);
		} catch (Exception e) {
			return false;
		}
		moveQuestion.setQuestion_sort(currentQuestionSort);
		moveQuestion.setQuestion_gmt_modified(TimeUtil.getStringSecond());
		try {
			questionDao.saveOrUpdateObject(moveQuestion);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 删除的前提是没有回答，如果有回答的话 那么就是删除失败的情况 删除问题
	 */
	@Override
	public boolean delete_question(jwcpxt_question question) {
		// 定义
		jwcpxt_question deleteQuestion = new jwcpxt_question();
		List<jwcpxt_answer_choice> listChoiceAnswer = new ArrayList<>();
		List<jwcpxt_answer_open> listOpenAnswer = new ArrayList<>();
		List<jwcpxt_option> listOption = new ArrayList<>();
		// 获取问题对象
		if (question != null && question.getJwcpxt_question_id() != null
				&& question.getJwcpxt_question_id().trim().length() > 0) {
			deleteQuestion = questionDao.get_question_byQuestionId(question.getJwcpxt_question_id().trim());
		} else {
			return false;
		}
		if (deleteQuestion == null) {
			return false;
		}
		// 根据问题的类型去查找,选择题
		if ("1".equals(deleteQuestion.getQuestion_type())) {
			// 根据问题查找
			listChoiceAnswer = questionDao.list_choiceAnswer_byQuestionId(question.getJwcpxt_question_id().trim());
			// 如果已经有回答了
			if (listChoiceAnswer.size() > 0) {
				return false;
			}
			// 如果没有回答,那么就是进行删除选项、删除追问
			// 获取对应问题的所有选项
			listOption = questionDao.get_option_byQuestionId(question.getJwcpxt_question_id().trim());
			// 遍历选项,进行删除追问
			for (jwcpxt_option jwcpxtOption : listOption) {
				// 删除所有追问
				if (jwcpxtOption != null && jwcpxtOption.getJwcpxt_option_id() != null
						&& jwcpxtOption.getJwcpxt_option_id().trim().length() > 0) {
					// 删除追问,如果删除失败？如何操作
					/*
					 * if
					 * (!questionDao.delete_question_byOptionId(jwcpxtOption.getJwcpxt_option_id().
					 * trim())) { throw new RuntimeException("删除追问失败"); }
					 */
					// 删除追问
				}
			}
			// 删除选项

			//
		} else if ("2".equals(deleteQuestion.getQuestion_type())) {

		}
		if (questionDao.delete_question(question.getJwcpxt_question_id())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取选项列表
	 */
	@Override
	public List<OptionDTO> list_optionDTO(jwcpxt_question question) {
		// 定义
		OptionDTO optionDTO = new OptionDTO();
		List<OptionDTO> listOptionDTO = new ArrayList<>();
		List<jwcpxt_option> listOption = new ArrayList<>();
		List<jwcpxt_option_inquiries> listOptionInquireies = new ArrayList<>();
		// 1.获取问题对象
		if (question != null && question.getJwcpxt_question_id() != null
				&& question.getJwcpxt_question_id().trim().length() > 0) {
			// 获取问题对象
			question = questionDao.get_question_byQuestionId(question.getJwcpxt_question_id().trim());
		} else {
			return null;
		}
		// 2.判断问题类型是否是选择题类型
		if ("1".equals(question.getQuestion_type())) {
			// 获取选项列表
			listOption = questionDao.get_option_byQuestionId(question.getJwcpxt_question_id().trim());
			// 遍历选项
			for (jwcpxt_option jwcpxt_option : listOption) {
				optionDTO = new OptionDTO();
				listOptionInquireies = new ArrayList<>();
				// 根据选项获取选项追问表
				if (jwcpxt_option != null && jwcpxt_option.getJwcpxt_option_id() != null
						&& jwcpxt_option.getJwcpxt_option_id().trim().length() > 0) {
					// 获取选项追问
					listOptionInquireies = questionDao
							.get_optionInquireies_byOptionId(jwcpxt_option.getJwcpxt_option_id().trim());
				}
				optionDTO.setOption(jwcpxt_option);
				optionDTO.setInquiriesList(listOptionInquireies);
				listOptionDTO.add(optionDTO);
			}
		} else {
			return null;
		}
		return listOptionDTO;
	}

	/**
	 * 保存选项
	 */
	@Override
	public boolean save_option(jwcpxt_option option) {
		jwcpxt_question question = new jwcpxt_question();
		// 1.保证所添加到的问题Id正确性
		if (option != null && option.getOption_question() != null && option.getOption_question().trim().length() > 0) {
			// 获取问题对象
			question = questionDao.get_question_byQuestionId(option.getOption_question().trim());
		} else {
			return false;
		}
		if (question == null) {
			// 所添加到的问题有误
			return false;
		}
		option.setJwcpxt_option_id(uuidUtil.getUuid());
		option.setOption_gmt_create(TimeUtil.getStringSecond());
		option.setOption_gmt_modified(option.getOption_gmt_create());
		// 选项顺序
		// 根据问题id获取该问题选项中的最大选项顺序
		int optionSort = questionDao.get_option_max_sort(option.getOption_question());
		option.setOption_sort(optionSort + 1);
		try {
			questionDao.saveOrUpdateObject(option);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 更新选项
	 */
	@Override
	public boolean update_option(jwcpxt_option option) {
		// 根据选项Id获取选项信息
		jwcpxt_option oldOption = new jwcpxt_option();
		if (option != null && option.getJwcpxt_option_id() != null
				&& option.getJwcpxt_option_id().trim().length() > 0) {
			oldOption = questionDao.get_option_byOptionId(option.getJwcpxt_option_id().trim());
		} else {
			return false;
		}
		if (oldOption == null) {
			return false;
		}
		// 更改信息
		oldOption.setOption_describe(option.getOption_describe());
		oldOption.setOption_grade(option.getOption_grade());
		oldOption.setOption_gmt_modified(TimeUtil.getStringSecond());
		try {
			questionDao.saveOrUpdateObject(oldOption);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean move_option(jwcpxt_option option, String moveOptionType) {
		// 定义
		int maxOptionSort = 0;
		int minOptionSort = 0;
		int currentOptionSort = 0;
		jwcpxt_option moveOption = new jwcpxt_option();
		// 判断参数
		if (option != null && option.getJwcpxt_option_id() != null
				&& option.getJwcpxt_option_id().trim().length() > 0) {
			// 获取选项对象
			option = questionDao.get_option_byOptionId(option.getJwcpxt_option_id().trim());
		} else {
			return false;
		}

		if (option == null) {
			return false;
		}

		if (option != null && option.getOption_question() != null && option.getOption_question().trim().length() > 0) {
			// 根据业务id获取该问题的最小排序以及最大排序
			maxOptionSort = questionDao.get_option_max_sort(option.getOption_question().trim());
			minOptionSort = questionDao.get_option_min_sort(option.getOption_question().trim());
		} else {
			return false;
		}
		if (moveOptionType != null && moveOptionType.trim().length() > 0) {
			// 1 为上移
			// 将当前值的位置进行存储
			currentOptionSort = option.getOption_sort();
			if ("1".equals(moveOptionType.trim())) {
				// 如果是最上面的就不能进行移动
				if (minOptionSort == currentOptionSort) {
					System.out.println("当前已经是第一个");
					return false;
				}
				// 否则就能进行移动
				// 获取比他小的最大的问题对象
				moveOption = questionDao.get_option_moveUpPosition_sort(option);
			} else if ("2".equals(moveOptionType.trim())) {
				// 如果是最小面的就不能进行移动
				if (maxOptionSort == currentOptionSort) {
					System.out.println("当前已经是最后一个");
					return false;
				}
				// 否则就能进行移动
				// 获取比他大的最小的那个对象
				moveOption = questionDao.get_option_moveDownPosition_sort(option);
			}
		} else {
			return false;
		}
		// 分别进行存储
		// 存储当前行
		option.setOption_sort(moveOption.getOption_sort());
		option.setOption_gmt_modified(TimeUtil.getStringSecond());
		try {
			questionDao.saveOrUpdateObject(option);
		} catch (Exception e) {
			return false;
		}
		moveOption.setOption_sort(currentOptionSort);
		moveOption.setOption_gmt_modified(TimeUtil.getStringSecond());
		try {
			questionDao.saveOrUpdateObject(moveOption);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}