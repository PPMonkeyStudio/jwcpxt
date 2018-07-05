package com.pphgzs.dao;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_question;
import com.pphgzs.domain.VO.QuestionVO;

public interface QuestionDao {

	public List<jwcpxt_question> list_question_byQuestionVO(QuestionVO questionVO);

	public int get_questionTotalCount_byQuestionVO(QuestionVO questionVO);

	public int get_question_max_sort(String question_service_definition);

	public void saveOrUpdateObject(Object obj);

	public jwcpxt_question get_question_byQuestionId(String trim);

}
