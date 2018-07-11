package com.pphgzs.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;
import com.pphgzs.domain.DO.jwcpxt_feedback_rectification;
import com.pphgzs.domain.VO.DissatisfiedFeedbackVO;
import com.pphgzs.service.DissatisfiedFeedbackService;

@SuppressWarnings("serial")
public class DissatisfiedFeedbackAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {

	private DissatisfiedFeedbackService dissatisfiedFeedbackService;
	private HttpServletResponse http_response;
	private HttpServletRequest http_request;
	private DissatisfiedFeedbackVO dissatisfiedFeedbackVO;
	private jwcpxt_feedback_rectification feedbackRectification;

	/**
	 * 更改反馈状态
	 * 
	 * @throws IOException
	 * 
	 */
	/*public void update_feedbackRectificationState_byRectificationId() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (dissatisfiedFeedbackService.update_feedbackRectificationState_byRectificationId(feedbackRectification)) {
			http_response.getWriter().write("1");
		} else {
			http_response.getWriter().write("-1");
		}
	}*/

	/**
	 * 根据反馈整改id获取反馈整改表
	 * 
	 * @throws IOException
	 */
	public void get_feedbackRectification_byRectificationId() throws IOException {
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		feedbackRectification = dissatisfiedFeedbackService
				.get_feedbackRectification_byRectificationId(feedbackRectification);
		http_response.getWriter().write(gson.toJson(feedbackRectification));
	}

	/**
	 * 获取整改反馈VO
	 * 
	 * @throws IOException
	 * 
	 */
	public void get_dissatisfiedFeedbackVO() throws IOException {
		if (dissatisfiedFeedbackVO == null) {
			dissatisfiedFeedbackVO = new DissatisfiedFeedbackVO();
		}
		dissatisfiedFeedbackVO = dissatisfiedFeedbackService.get_dissatisfiedFeedbackVO(dissatisfiedFeedbackVO);
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(gson.toJson(dissatisfiedFeedbackVO));
	}

	/**
	 * 
	 */

	@Override
	public void setServletRequest(HttpServletRequest http_request) {
		this.http_request = http_request;
	}

	public jwcpxt_feedback_rectification getFeedbackRectification() {
		return feedbackRectification;
	}

	public void setFeedbackRectification(jwcpxt_feedback_rectification feedbackRectification) {
		this.feedbackRectification = feedbackRectification;
	}

	public DissatisfiedFeedbackVO getDissatisfiedFeedbackVO() {
		return dissatisfiedFeedbackVO;
	}

	public void setDissatisfiedFeedbackVO(DissatisfiedFeedbackVO dissatisfiedFeedbackVO) {
		this.dissatisfiedFeedbackVO = dissatisfiedFeedbackVO;
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
