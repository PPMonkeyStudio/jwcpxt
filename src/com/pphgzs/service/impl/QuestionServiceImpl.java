package com.pphgzs.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.pphgzs.dao.QuestionDao;
import com.pphgzs.domain.DO.jwcpxt_answer_choice;
import com.pphgzs.domain.DO.jwcpxt_answer_open;
import com.pphgzs.domain.DO.jwcpxt_dissatisfied_feedback;
import com.pphgzs.domain.DO.jwcpxt_option;
import com.pphgzs.domain.DO.jwcpxt_question;
import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_service_instance;
import com.pphgzs.domain.DO.jwcpxt_user;
import com.pphgzs.domain.DTO.AnswerDTO;
import com.pphgzs.domain.DTO.InquiriesOptionDTO;
import com.pphgzs.domain.DTO.OptionDTO;
import com.pphgzs.domain.DTO.QuestionDTO;
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
		ServiceDefinitionDTO serviceDefinitionDTO = new ServiceDefinitionDTO();
		// 首先根据业务定义Id获取业务定义对象
		serviceDefinitionDTO = serviceService.get_serviceDefinitionDTO_byServiceDefinitionID(
				questionVO.getServiceDefinitionDTO().getServiceDefinition().getJwcpxt_service_definition_id());
		// 获取分页里面的question
		questionList = questionDao.list_question_byQuestionVO(questionVO);
		// 获取总记录数
		int totalRecords = questionDao.get_questionTotalCount_byQuestionVO(questionVO);
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
	@Override
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
		// oldQuestion.setQuestion_type(question.getQuestion_type());
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
					return false;
				}
				// 否则就能进行移动
				// 获取比他小的最大的问题对象
				moveQuestion = questionDao.get_question_moveUpPosition_sort(question);
			} else if ("2".equals(moveQuestionType.trim())) {
				// 如果是最小面的就不能进行移动
				if (maxQuestionSort == currentQuestionSort) {
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
			// 遍历进行删除选项
			for (jwcpxt_option deleteOption : listOption) {
				delete_option(deleteOption);
			}
		} else if ("2".equals(deleteQuestion.getQuestion_type())) {
			// 如果是开放题
			// 判断是否有回答
			listOpenAnswer = questionDao.get_answerOpen_byQuestionId(question.getJwcpxt_question_id().trim());
			if (listOpenAnswer.size() > 0) {
				return false;
			}
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
	/*
	 * @Override public List<OptionDTO> list_optionDTO(jwcpxt_question question) {
	 * // 定义 OptionDTO optionDTO = new OptionDTO(); List<OptionDTO> listOptionDTO =
	 * new ArrayList<>(); List<jwcpxt_option> listOption = new ArrayList<>();
	 * List<jwcpxt_option_inquiries> listOptionInquireies = new ArrayList<>(); //
	 * 1.获取问题对象 if (question != null && question.getJwcpxt_question_id() != null &&
	 * question.getJwcpxt_question_id().trim().length() > 0) { // 获取问题对象 question =
	 * questionDao.get_question_byQuestionId(question.getJwcpxt_question_id().
	 * trim() ); } else { return null; } // 2.判断问题类型是否是选择题类型 if
	 * ("1".equals(question.getQuestion_type())) { // 获取选项列表 listOption =
	 * questionDao.get_option_byQuestionId(question.getJwcpxt_question_id().trim
	 * ()); // 遍历选项 for (jwcpxt_option jwcpxt_option : listOption) { optionDTO = new
	 * OptionDTO(); listOptionInquireies = new ArrayList<>(); // 根据选项获取选项追问表 if
	 * (jwcpxt_option != null && jwcpxt_option.getJwcpxt_option_id() != null &&
	 * jwcpxt_option.getJwcpxt_option_id().trim().length() > 0) { // 获取选项追问
	 * listOptionInquireies = questionDao
	 * .get_optionInquireies_byOptionId(jwcpxt_option.getJwcpxt_option_id().trim
	 * ()); } optionDTO.setOption(jwcpxt_option);
	 * optionDTO.setInquiriesList(listOptionInquireies);
	 * listOptionDTO.add(optionDTO); } } else { return null; } return listOptionDTO;
	 * }
	 */
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
		oldOption.setOption_push(option.getOption_push());
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
					return false;
				}
				// 否则就能进行移动
				// 获取比他小的最大的问题对象
				moveOption = questionDao.get_option_moveUpPosition_sort(option);
			} else if ("2".equals(moveOptionType.trim())) {
				// 如果是最小面的就不能进行移动
				if (maxOptionSort == currentOptionSort) {
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

	/**
	 * 删除追问
	 */
	@Override
	public boolean delete_questionInquiries(jwcpxt_question question) {
		// 定义
		List<jwcpxt_answer_open> listAnswerOpen = new ArrayList<>();
		List<jwcpxt_answer_choice> listChoiceAnswer = new ArrayList<>();
		// 根据追问的id获取追问对象
		jwcpxt_question questionInquiries = new jwcpxt_question();
		if (question != null && question.getJwcpxt_question_id() != null
				&& question.getJwcpxt_question_id().trim().length() > 0) {
			questionInquiries = questionDao.get_question_byQuestionId(question.getJwcpxt_question_id().trim());
		}
		// 判断对象
		if (questionInquiries == null) {
			return false;
		}
		// 判断追问的类型
		if (questionInquiries.getQuestion_type() != null && questionInquiries.getQuestion_type().trim().length() > 0) {
			// 如果追问是开放题
			if ("3".equals(questionInquiries.getQuestion_type().trim())) {
				// 判断是否有该追问的回答
				listAnswerOpen = questionDao.get_answerOpen_byQuestionId(question.getJwcpxt_question_id().trim());
				if (listAnswerOpen.size() > 0) {
					return false;
				}
			} else if ("4".equals(questionInquiries.getQuestion_type().trim())) {
				// 如果是选择题
				// 判断是否有该追问的回答
				listChoiceAnswer = questionDao.list_choiceAnswer_byQuestionId(question.getJwcpxt_question_id().trim());
				if (listChoiceAnswer.size() > 0) {
					return false;
				}
				// 删除该追问的选择题选项
				questionDao.delete_option_byQuestionId(question.getJwcpxt_question_id().trim());
			}
		}
		// 删除追问本身
		questionDao.delete_question(question.getJwcpxt_question_id().trim());
		return true;
	}

	/**
	 * 删除选项
	 */
	@Override
	public boolean delete_option(jwcpxt_option option) {
		// 定义
		List<jwcpxt_answer_choice> listChoiceAnswer = new ArrayList<>();
		List<jwcpxt_question> listInQuestion = new ArrayList<>();
		// 根据选项id获取选项对象
		jwcpxt_option deleteOption = new jwcpxt_option();
		if (option != null && option.getJwcpxt_option_id() != null
				&& option.getJwcpxt_option_id().trim().length() > 0) {
			deleteOption = questionDao.get_option_byOptionId(option.getJwcpxt_option_id().trim());
		}
		if (deleteOption == null) {
			return false;
		}
		// 判断该选项是否有回答
		listChoiceAnswer = questionDao.list_choice_byOptionId(option.getJwcpxt_option_id().trim());
		if (listChoiceAnswer.size() > 0) {
			return false;
		}
		// 获取该选项所有追问
		listInQuestion = questionDao.list_question_byServiceDefinition(option.getJwcpxt_option_id().trim());
		// 遍历追问进行删除
		for (jwcpxt_question deleteQuestion : listInQuestion) {
			delete_questionInquiries(deleteQuestion);
		}
		// 删除选项
		questionDao.delete_option_byOptionId(option.getJwcpxt_option_id().trim());
		return true;
	}

	/**
	 * 根据问题id
	 */
	@Override
	public QuestionDTO get_questionDTO_byQuestionId(jwcpxt_question question) {
		// 定义
		List<jwcpxt_option> listOption = new ArrayList<>();
		List<jwcpxt_question> listZhuiWenQuestion = new ArrayList<>();
		QuestionDTO questionDTO = new QuestionDTO();
		List<OptionDTO> listOptionDTO = new ArrayList<>();
		OptionDTO optionDTO = new OptionDTO();
		List<InquiriesOptionDTO> listInquiriesOptionDTO = new ArrayList<>();
		InquiriesOptionDTO inquiriesOptionDTO = new InquiriesOptionDTO();
		List<jwcpxt_option> listInquiriesOption = new ArrayList<>();
		// 根据问题id获取问题对象
		jwcpxt_question jQuestion = new jwcpxt_question();
		if (question != null && question.getJwcpxt_question_id() != null
				&& question.getJwcpxt_question_id().length() > 0) {
			jQuestion = questionDao.get_question_byQuestionId(question.getJwcpxt_question_id().trim());
		}
		if (jQuestion == null) {
			return null;
		}
		// 根据问题类型来判断下面的内容
		if (jQuestion.getQuestion_type() == null || jQuestion.getQuestion_type().trim().length() <= 0) {
			return null;
		}
		// 如果是选择题
		if ("1".equals(jQuestion.getQuestion_type())) {
			// 获取选项
			listOption = questionDao.get_option_byQuestionId(question.getJwcpxt_question_id().trim());
			// 遍历所有选项，获取其中的内容值
			for (jwcpxt_option jwcpxtOption : listOption) {
				// 定义
				optionDTO = new OptionDTO();
				listInquiriesOptionDTO = new ArrayList<>();
				listZhuiWenQuestion = new ArrayList<>();
				// 获取该选项的所有追问
				if (jwcpxtOption != null && jwcpxtOption.getJwcpxt_option_id() != null
						&& jwcpxtOption.getJwcpxt_option_id().trim().length() > 0) {
					// 所有追问
					listZhuiWenQuestion = questionDao
							.list_question_byServiceDefinition(jwcpxtOption.getJwcpxt_option_id().trim());
					// 遍历追问
					for (jwcpxt_question jwcpxt_question : listZhuiWenQuestion) {
						// 定义
						listInquiriesOption = new ArrayList<>();
						inquiriesOptionDTO = new InquiriesOptionDTO();
						// 判断追问类型
						if (jwcpxt_question != null && jwcpxt_question.getQuestion_type() != null
								&& jwcpxt_question.getQuestion_type().trim().length() > 0) {
							// 如果是开放追问,不做操作
							if ("4".equals(jwcpxt_question.getQuestion_type().trim())) {
								// 如果是选择追问
								// 获取该追问的所有选项
								listInquiriesOption = questionDao
										.get_option_byQuestionId(jwcpxt_question.getJwcpxt_question_id().trim());

							}
						}
						inquiriesOptionDTO.setInquiriesQuestion(jwcpxt_question);
						inquiriesOptionDTO.setListInquiriesOption(listInquiriesOption);
						listInquiriesOptionDTO.add(inquiriesOptionDTO);
					}
				}
				optionDTO.setListInquiriesOptionDTO(listInquiriesOptionDTO);
				optionDTO.setOption(jwcpxtOption);
				listOptionDTO.add(optionDTO);
			}
		}
		questionDTO.setListOptionDTO(listOptionDTO);
		questionDTO.setQuestion(jQuestion);
		return questionDTO;
	}

	/**
	 * 获取一个业务的所有问题
	 */
	@Override
	public List<QuestionDTO> list_questionDTO_byServiceDefinition(jwcpxt_service_definition serviceDefinition) {
		// 获取一个业务定义里面的所有问题
		List<jwcpxt_question> listQuestion = new ArrayList<>();
		List<QuestionDTO> list_questionDTO = new ArrayList<>();
		QuestionDTO questionDTO = new QuestionDTO();
		if (serviceDefinition != null && serviceDefinition.getJwcpxt_service_definition_id() != null
				&& serviceDefinition.getJwcpxt_service_definition_id().trim().length() > 0) {
			listQuestion = questionDao
					.list_question_byServiceDefinition(serviceDefinition.getJwcpxt_service_definition_id().trim());
		}
		for (jwcpxt_question jwcpxt_question : listQuestion) {
			questionDTO = new QuestionDTO();
			if (jwcpxt_question != null && jwcpxt_question.getJwcpxt_question_id() != null
					&& jwcpxt_question.getJwcpxt_question_id().trim().length() > 0) {
				questionDTO = get_questionDTO_byQuestionId(jwcpxt_question);
			}
			list_questionDTO.add(questionDTO);
		}
		return list_questionDTO;
	}

	/**
	 * 保存回答
	 */
	@Override
	public boolean save_answer(List<AnswerDTO> listAnswerDTO, jwcpxt_service_client serviceClient, jwcpxt_user user) {
		// 定义
		List<QuestionDTO> listQuestionDTO = new ArrayList<>();
		AnswerDTO answerD = new AnswerDTO();
		jwcpxt_service_definition serviceDefinition = new jwcpxt_service_definition();
		jwcpxt_question question = new jwcpxt_question();
		jwcpxt_option option = new jwcpxt_option();
		jwcpxt_answer_choice answerChoice = new jwcpxt_answer_choice();
		jwcpxt_answer_open answerOpen = new jwcpxt_answer_open();
		jwcpxt_dissatisfied_feedback dissatisfiedFeedback = new jwcpxt_dissatisfied_feedback();
		
		/**
		 * hy
		 */
		jwcpxt_service_instance serviceInstance = serviceService.get_serviceInstanceDo_byServiceClientID(serviceClient);
		
		// 根据当事人id获取当事人信息 确保当事人信息正确
		jwcpxt_service_client client = new jwcpxt_service_client();
		if (serviceClient != null && serviceClient.getJwcpxt_service_client_id() != null
				&& serviceClient.getJwcpxt_service_client_id().trim().length() > 0) {
			client = questionDao.get_serviceClient_byClientId(serviceClient.getJwcpxt_service_client_id().trim());
		}
		if (client == null) {
			return false;
		}
		// 遍历回答列表
		for (AnswerDTO answerDTO : listAnswerDTO) {
			
			
			listQuestionDTO = new ArrayList<>();
			serviceDefinition = new jwcpxt_service_definition();
			// 定义
			question = new jwcpxt_question();
			option = new jwcpxt_option();
			answerChoice = new jwcpxt_answer_choice();
			answerOpen = new jwcpxt_answer_open();
			answerOpen = answerDTO.getAnswerOpen();
			dissatisfiedFeedback = new jwcpxt_dissatisfied_feedback();
			//
			// 根据问题id获取问题对象
			if (answerDTO.getQuestion() != null && answerDTO.getQuestion().getJwcpxt_question_id() != null
					&& answerDTO.getQuestion().getJwcpxt_question_id().trim().length() > 0) {
				question = questionDao
						.get_question_byQuestionId(answerDTO.getQuestion().getJwcpxt_question_id().trim());
			}
			if (question == null)
				continue;
			// 如果是选择题
			if ("1".equals(question.getQuestion_type().trim()) || "4".equals(question.getQuestion_type().trim())) {
				// 获取选中的选项表
				if (answerDTO.getOption() != null && answerDTO.getOption().getJwcpxt_option_id() != null
						&& answerDTO.getOption().getJwcpxt_option_id().trim().length() > 0) {
					option = questionDao.get_option_byOptionId(answerDTO.getOption().getJwcpxt_option_id().trim());
				}
				if (option == null) {
					continue;
				}
				// 存储选择题回答
				answerChoice.setJwcpxt_answer_choice_id(uuidUtil.getUuid());
				answerChoice.setAnswer_choice_client(serviceClient.getJwcpxt_service_client_id());
				answerChoice.setAnswer_choice_option(option.getJwcpxt_option_id());
				answerChoice.setAnswer_choice_question(question.getJwcpxt_question_id());
				answerChoice.setAnswer_choice_gmt_create(TimeUtil.getStringSecond());
				answerChoice.setAnswer_choice_gmt_modified(answerChoice.getAnswer_choice_gmt_create());
				questionDao.saveOrUpdateObject(answerChoice);
				// 如果属于需要推送的选项，则生成
				// 1.是不是推送？
				// 2.如果不是 如果他的开放题追问被回答就推送
				if ("1".equals(option.getOption_push())) {
					dissatisfiedFeedback.setJwcpxt_dissatisfied_feedback_id(uuidUtil.getUuid());
					dissatisfiedFeedback
							.setDissatisfied_feedback_answer_choice(answerChoice.getJwcpxt_answer_choice_id());
					// dissatisfiedFeedback.setDissatisfied_feedback_time(TimeUtil.getStringSecond());
					dissatisfiedFeedback.setDissatisfied_feedback_state("1");
					dissatisfiedFeedback.setDissatisfied_feedback_gmt_create(TimeUtil.getStringSecond());
					dissatisfiedFeedback.setDissatisfied_feedback_gmt_modified(
							dissatisfiedFeedback.getDissatisfied_feedback_gmt_create());
					questionDao.saveOrUpdateObject(dissatisfiedFeedback);
				} else if ("2".equals(option.getOption_push())) {
					// 判断是否有追问
					// 通过选项id获取选项list
					serviceDefinition.setJwcpxt_service_definition_id(option.getJwcpxt_option_id());
					listQuestionDTO = list_questionDTO_byServiceDefinition(serviceDefinition);
					// 如果有追问
					if (listQuestionDTO != null && listQuestionDTO.size() > 0) {
						// 在list中判断追问开放题是否被回答
						// 遍历
						for (QuestionDTO questionDTO : listQuestionDTO) {
							// 所有追问
							// 判断回答
							if (questionDTO.getQuestion() != null
									&& questionDTO.getQuestion().getQuestion_type() != null
									&& "3".equals(questionDTO.getQuestion().getQuestion_type())) {
								for (AnswerDTO answerJu : listAnswerDTO) {
									if ((questionDTO.getQuestion().getJwcpxt_question_id())
											.equals(answerJu.getQuestion().getJwcpxt_question_id())) {
										dissatisfiedFeedback.setJwcpxt_dissatisfied_feedback_id(uuidUtil.getUuid());
										dissatisfiedFeedback.setDissatisfied_feedback_answer_choice(
												answerChoice.getJwcpxt_answer_choice_id());
										// dissatisfiedFeedback.setDissatisfied_feedback_time(TimeUtil.getStringSecond());
										dissatisfiedFeedback.setDissatisfied_feedback_state("1");
										dissatisfiedFeedback
												.setDissatisfied_feedback_gmt_create(TimeUtil.getStringSecond());
										dissatisfiedFeedback.setDissatisfied_feedback_gmt_modified(
												dissatisfiedFeedback.getDissatisfied_feedback_gmt_create());
										questionDao.saveOrUpdateObject(dissatisfiedFeedback);
										break;
									}
								}
								break;
							}
						}
					}
				}
				/*
				 * 此时应该还需要生成通知表，但是通知还没有确定，所有暂时还没有写 7.8 9:23 AM
				 */
			} else if ("2".equals(question.getQuestion_type().trim())
					|| "3".equals(question.getQuestion_type().trim())) {
				// 如果是开放题
				answerOpen.setJwcpxt_answer_open_id(uuidUtil.getUuid());
				answerOpen.setAnswer_open_client(serviceClient.getJwcpxt_service_client_id());
				answerOpen.setAnswer_open_content(answerDTO.getAnswerOpen().getAnswer_open_content());
				answerOpen.setAnswer_open_question(question.getJwcpxt_question_id());
				answerOpen.setAnswer_open_gmt_create(TimeUtil.getStringSecond());
				answerOpen.setAnswer_open_gmt_modified(answerOpen.getAnswer_open_gmt_create());
				questionDao.saveOrUpdateObject(answerOpen);
			}
		}
		client.setService_client_visit("1");
		client.setService_client_gmt_modified(TimeUtil.getStringSecond());
		questionDao.saveOrUpdateObject(client);

		// serviceService.distributionNewServiceInstance_toUser(user.getJwcpxt_user_id());

		/*
		 *处理 当事人回访时候，在当事人业务回访记录还是刚开始分配的测评员的问题，由另一个测评员回访，则需要改变分配的测评员 
		 */
		serviceInstance.setService_instance_judge(user.getJwcpxt_user_id());
		questionDao.saveOrUpdateObject(serviceInstance);
		return true;
	}

}