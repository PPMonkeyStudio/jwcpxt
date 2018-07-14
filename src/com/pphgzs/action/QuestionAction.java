package com.pphgzs.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.pphgzs.domain.DO.jwcpxt_option;
import com.pphgzs.domain.DO.jwcpxt_question;
import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_user;
import com.pphgzs.domain.DTO.AnswerDTO;
import com.pphgzs.domain.DTO.QuestionDTO;
import com.pphgzs.domain.VO.QuestionVO;
import com.pphgzs.service.QuestionService;

@SuppressWarnings("serial")
public class QuestionAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {

	private QuestionService questionService;
	private HttpServletResponse http_response;
	private HttpServletRequest http_request;
	//
	private jwcpxt_question question;
	private jwcpxt_option option;
	private jwcpxt_service_definition serviceDefinition;
	private QuestionVO questionVO;
	private String moveQuestionType;
	private String moveOptionType;
	// 一个问题的所有选择题
	private QuestionDTO questionDTO;
	// 一个业务定义的所有问题
	private List<QuestionDTO> listQuestionDTO;
	// 业务当事人
	private jwcpxt_service_client serviceClient;
	// 回答
	private List<AnswerDTO> listAnswerDTO;

	/**
	 * 根据业务定义Id 获取所有该业务的所有问题
	 * 
	 * @throws IOException
	 */
	public void list_questionDTO_byServiceDefinition() throws IOException {
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		listQuestionDTO = questionService.list_questionDTO_byServiceDefinition(serviceDefinition);
		http_response.getWriter().write(gson.toJson(listQuestionDTO));
	}

	/**
	 * 回答问题
	 * 
	 * @throws IOException
	 */
	public void save_answer() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		jwcpxt_user pcry = (jwcpxt_user) ActionContext.getContext().getSession().get("user");
		if (questionService.save_answer(listAnswerDTO, serviceClient, pcry)) {
			http_response.getWriter().write("1");
		} else {
			http_response.getWriter().write("-1");
		}
	}

	/**
	 * 根据问题Id 获取该问题的所有内容
	 * 
	 * @throws IOException
	 */
	public void get_questionDTO_byQuestionId() throws IOException {
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		questionDTO = questionService.get_questionDTO_byQuestionId(question);
		http_response.getWriter().write(gson.toJson(questionDTO));
	}

	/**
	 * 删除选项
	 * 
	 * @throws IOException
	 */
	public void delete_option() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (questionService.delete_option(option)) {
			http_response.getWriter().write("1");
		} else {
			http_response.getWriter().write("-1");
		}
	}

	/**
	 * 删除追问
	 * 
	 * @throws IOException
	 */
	public void delete_questionInquiries() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (questionService.delete_questionInquiries(question)) {
			http_response.getWriter().write("1");
		} else {
			http_response.getWriter().write("-1");
		}
	}

	/**
	 * 移动选项
	 * 
	 * @throws IOException
	 */
	public void move_option() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (questionService.move_option(option, moveOptionType)) {
			http_response.getWriter().write("1");
		} else {
			http_response.getWriter().write("-1");
		}
	}

	/**
	 * 修改选项
	 * 
	 * @throws IOException
	 */
	public void update_option() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (questionService.update_option(option)) {
			http_response.getWriter().write("1");
		} else {
			http_response.getWriter().write("-1");
		}
	}

	/**
	 * 添加问题选项
	 * 
	 * @throws IOException
	 */
	public void save_option() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (questionService.save_option(option)) {
			http_response.getWriter().write("1");
		} else {
			http_response.getWriter().write("-1");
		}
	}

	/**
	 * 移动问题顺序
	 * 
	 * @throws IOException
	 */
	public void move_question_sort() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (questionService.move_question_sort(question, moveQuestionType)) {
			http_response.getWriter().write("1");
		} else {
			http_response.getWriter().write("-1");
		}
	}

	/**
	 * 获取问题VO
	 * 
	 * @throws IOException
	 */
	public void get_questionVO() throws IOException {
		if (questionVO == null) {
			questionVO = new QuestionVO();
		}
		questionVO = questionService.get_questionVO(questionVO);
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(questionVO));
	}

	/**
	 * 创建一个问题
	 * 
	 * @throws IOException
	 */
	public void save_question() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (questionService.save_question(question)) {
			http_response.getWriter().write("1");
		} else {
			http_response.getWriter().write("-1");
		}
	}

	/**
	 * 修改问题
	 * 
	 * @throws IOException
	 */
	public void update_question() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (questionService.update_question(question)) {
			http_response.getWriter().write("1");
		} else {
			http_response.getWriter().write("-1");
		}
	}

	/**
	 * 删除问题
	 * 
	 * @throws IOException
	 */
	public void delete_question() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (questionService.delete_question(question)) {
			http_response.getWriter().write("1");
		} else {
			http_response.getWriter().write("-1");
		}
	}

	/*
	 *  
	 */

	@Override
	public void setServletRequest(HttpServletRequest http_request) {
		this.http_request = http_request;
	}

	public List<AnswerDTO> getListAnswerDTO() {
		return listAnswerDTO;
	}

	public void setListAnswerDTO(List<AnswerDTO> listAnswerDTO) {
		this.listAnswerDTO = listAnswerDTO;
	}

	public jwcpxt_service_client getServiceClient() {
		return serviceClient;
	}

	public void setServiceClient(jwcpxt_service_client serviceClient) {
		this.serviceClient = serviceClient;
	}

	public QuestionDTO getQuestionDTO() {
		return questionDTO;
	}

	public void setQuestionDTO(QuestionDTO questionDTO) {
		this.questionDTO = questionDTO;
	}

	public List<QuestionDTO> getListQuestionDTO() {
		return listQuestionDTO;
	}

	public void setListQuestionDTO(List<QuestionDTO> listQuestionDTO) {
		this.listQuestionDTO = listQuestionDTO;
	}

	public String getMoveQuestionType() {
		return moveQuestionType;
	}

	public void setMoveQuestionType(String moveQuestionType) {
		this.moveQuestionType = moveQuestionType;
	}

	@Override
	public void setServletResponse(HttpServletResponse http_response) {
		this.http_response = http_response;

	}

	public HttpServletResponse getHttp_response() {
		return http_response;
	}

	public void setHttp_response(HttpServletResponse http_response) {
		this.http_response = http_response;
	}

	public String getMoveOptionType() {
		return moveOptionType;
	}

	public void setMoveOptionType(String moveOptionType) {
		this.moveOptionType = moveOptionType;
	}

	public HttpServletRequest getHttp_request() {
		return http_request;
	}

	public void setHttp_request(HttpServletRequest http_request) {
		this.http_request = http_request;
	}

	public QuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public jwcpxt_question getQuestion() {
		return question;
	}

	public void setQuestion(jwcpxt_question question) {
		this.question = question;
	}

	public jwcpxt_option getOption() {
		return option;
	}

	public void setOption(jwcpxt_option option) {
		this.option = option;
	}

	/*
	 * public int getMoveOptionAction() { return moveOptionAction; }
	 * 
	 * public void setMoveOptionAction(int moveOptionAction) {
	 * this.moveOptionAction = moveOptionAction; }
	 * 
	 * public int getMoveQuestionAction() { return moveQuestionAction; }
	 * 
	 * public void setMoveQuestionAction(int moveQuestionAction) {
	 * this.moveQuestionAction = moveQuestionAction; }
	 */

	public jwcpxt_service_definition getServiceDefinition() {
		return serviceDefinition;
	}

	public void setServiceDefinition(jwcpxt_service_definition serviceDefinition) {
		this.serviceDefinition = serviceDefinition;
	}

	public QuestionVO getQuestionVO() {
		return questionVO;
	}

	public void setQuestionVO(QuestionVO questionVO) {
		this.questionVO = questionVO;
	}

}
