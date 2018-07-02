package com.pphgzs.dao;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_question;
import com.pphgzs.domain.VO.QuestionVO;

public interface QuestionDao {

	public List<jwcpxt_question> list_question_byQuestionVO(QuestionVO questionVO);

	public int get_questionTotalCount_byQuestionVO(QuestionVO questionVO);

}
