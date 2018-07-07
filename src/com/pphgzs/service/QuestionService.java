package com.pphgzs.service;

import java.util.List;

import com.google.gson.JsonElement;
import com.pphgzs.domain.DO.jwcpxt_option;
import com.pphgzs.domain.DO.jwcpxt_question;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DTO.OptionDTO;
import com.pphgzs.domain.DTO.QuestionDTO;
import com.pphgzs.domain.VO.QuestionVO;

public interface QuestionService {

	public QuestionVO get_questionVO(QuestionVO questionVO);

	public boolean save_question(jwcpxt_question question);

	public boolean update_question(jwcpxt_question question);

	public boolean move_question_sort(jwcpxt_question question, String moveQuestionType);

	public boolean delete_question(jwcpxt_question question);

	public boolean save_option(jwcpxt_option option);

	public boolean update_option(jwcpxt_option option);

	public boolean move_option(jwcpxt_option option, String moveOptionType);

	public boolean delete_questionInquiries(jwcpxt_question question);

	public boolean delete_option(jwcpxt_option option);

	public QuestionDTO get_questionDTO_byQuestionId(jwcpxt_question question);

	public List<QuestionDTO> list_questionDTO_byServiceDefinition(jwcpxt_service_definition serviceDefinition);

}
