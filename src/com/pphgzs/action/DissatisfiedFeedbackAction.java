package com.pphgzs.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.pphgzs.domain.DO.jwcpxt_dissatisfied_feedback;
import com.pphgzs.domain.DO.jwcpxt_feedback_rectification;
import com.pphgzs.domain.VO.DissatisfiedQuestionVO;
import com.pphgzs.service.DissatisfiedFeedbackService;

@SuppressWarnings("serial")
public class DissatisfiedFeedbackAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {

	private DissatisfiedFeedbackService dissatisfiedFeedbackService;
	private HttpServletResponse http_response;
	private HttpServletRequest http_request;
	private jwcpxt_feedback_rectification feedbackRectification;
	private DissatisfiedQuestionVO dissatisfiedQuestionVO;
	private jwcpxt_dissatisfied_feedback dissatisfiedFeedback;

	/**
	 * 驳回不满意反馈表
	 */
	public void update_dissatisfiedFeedbackState_toReject() {
		
	}

	/**
	 * 获取不满意反馈表VO
	 * 
	 * @throws IOException
	 */
	public void get_dissatisfiedQuestionVO() throws IOException {
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		dissatisfiedQuestionVO = dissatisfiedFeedbackService.get_dissatisfiedQuestionVO(dissatisfiedQuestionVO);
		http_response.getWriter().write(gson.toJson(dissatisfiedQuestionVO));
	}

	public jwcpxt_dissatisfied_feedback getDissatisfiedFeedback() {
		return dissatisfiedFeedback;
	}

	public void setDissatisfiedFeedback(jwcpxt_dissatisfied_feedback dissatisfiedFeedback) {
		this.dissatisfiedFeedback = dissatisfiedFeedback;
	}

	@Override
	public void setServletRequest(HttpServletRequest http_request) {
		this.http_request = http_request;
	}

	public DissatisfiedQuestionVO getDissatisfiedQuestionVO() {
		return dissatisfiedQuestionVO;
	}

	public void setDissatisfiedQuestionVO(DissatisfiedQuestionVO dissatisfiedQuestionVO) {
		this.dissatisfiedQuestionVO = dissatisfiedQuestionVO;
	}

	public jwcpxt_feedback_rectification getFeedbackRectification() {
		return feedbackRectification;
	}

	public void setFeedbackRectification(jwcpxt_feedback_rectification feedbackRectification) {
		this.feedbackRectification = feedbackRectification;
	}

	public DissatisfiedFeedbackService getDissatisfiedFeedbackService() {
		return dissatisfiedFeedbackService;
	}

	public void setDissatisfiedFeedbackService(DissatisfiedFeedbackService dissatisfiedFeedbackService) {
		this.dissatisfiedFeedbackService = dissatisfiedFeedbackService;
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

	public HttpServletRequest getHttp_request() {
		return http_request;
	}

	public void setHttp_request(HttpServletRequest http_request) {
		this.http_request = http_request;
	}

}
