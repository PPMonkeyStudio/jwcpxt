package com.pphgzs.dao;

import java.util.List;
import com.pphgzs.domain.DO.jwcpxt_answer_choice;
import com.pphgzs.domain.DO.jwcpxt_answer_open;
import com.pphgzs.domain.DO.jwcpxt_option;
import com.pphgzs.domain.DO.jwcpxt_question;
import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.VO.QuestionVO;

public interface QuestionDao {

	public List<jwcpxt_question> list_question_byQuestionVO(QuestionVO questionVO);

	public int get_questionTotalCount_byQuestionVO(QuestionVO questionVO);

	public int get_question_max_sort(String question_service_definition);

	public void saveOrUpdateObject(Object obj);

	public jwcpxt_question get_question_byQuestionId(String trim);

	public int get_question_min_sort(String question_service_definition);

	public jwcpxt_question get_question_moveUpPosition_sort(jwcpxt_question question);

	public jwcpxt_question get_question_moveDownPosition_sort(jwcpxt_question question);

	public boolean delete_question(String jwcpxt_question_id);

	public List<jwcpxt_option> get_option_byQuestionId(String trim);

	public int get_option_max_sort(String option_question);

	public jwcpxt_option get_option_byOptionId(String trim);

	public int get_option_min_sort(String trim);

	public jwcpxt_option get_option_moveUpPosition_sort(jwcpxt_option option);

	public jwcpxt_option get_option_moveDownPosition_sort(jwcpxt_option option);

	public List<jwcpxt_answer_choice> list_choiceAnswer_byQuestionId(String trim);

	public boolean delete_question_byOptionId(String trim);

	public boolean delete_option_byOptionId(String trim);

	public List<jwcpxt_answer_open> get_answerOpen_byQuestionId(String trim);

	public boolean delete_option_byQuestionId(String trim);

	/**
	 * 根据选项Id获取 选择题回答列表
	 * 
	 * @param trim
	 * @return
	 */
	public List<jwcpxt_answer_choice> list_choice_byOptionId(String optionId);

	public List<jwcpxt_question> list_question_byServiceDefinition(String trim);

	/**
	 * 根据id获取当事人对象
	 * 
	 * @param clientId
	 * @return
	 */
	public jwcpxt_service_client get_serviceClient_byClientId(String clientId);

	/**
	 * 
	 * @param serviceDefinition
	 * @return
	 */
	public List<jwcpxt_question> get_listQuestionBy_serviceDefiniId(jwcpxt_service_definition serviceDefinition);

	public List<jwcpxt_option> get_listOptionBy_questionId(jwcpxt_question question);

	public jwcpxt_answer_choice get_answerChoice_byClientAndQuestion(String jwcpxt_service_client_id,
			String jwcpxt_question_id);

	public jwcpxt_answer_open get_answerOpen_byClientAndQuestion(String jwcpxt_service_client_id,
			String jwcpxt_question_id);

}
