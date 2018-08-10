package com.pphgzs.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.pphgzs.domain.DO.jwcpxt_dissatisfied_feedback;
import com.pphgzs.domain.DO.jwcpxt_feedback_rectification;
import com.pphgzs.domain.DO.jwcpxt_unit;
import com.pphgzs.domain.VO.CheckFeedbackRectificationVO;
import com.pphgzs.domain.VO.DissatisfiedQuestionVO;
import com.pphgzs.domain.VO.FeedbackRectificationExceedTimeVO;
import com.pphgzs.domain.VO.FeedbackRectificationVO;
import com.pphgzs.domain.VO.SecondDistatisVO;
import com.pphgzs.service.DissatisfiedFeedbackService;

@SuppressWarnings("serial")
public class DissatisfiedFeedbackAction extends ActionSupport implements ServletResponseAware, ServletRequestAware {

	private DissatisfiedFeedbackService dissatisfiedFeedbackService;
	private HttpServletResponse http_response;
	private HttpServletRequest http_request;
	private jwcpxt_feedback_rectification feedbackRectification;
	private DissatisfiedQuestionVO dissatisfiedQuestionVO;
	private jwcpxt_dissatisfied_feedback dissatisfiedFeedback;
	private FeedbackRectificationVO feedbackRectificationVO;
	private CheckFeedbackRectificationVO checkFeedbackRectificationVO;
	private FeedbackRectificationExceedTimeVO feedbackRectificationExceedTimeVO;
	private SecondDistatisVO secondDistatisVO;

	/**
	 * 获取二次回访还是不满意的VO
	 * 
	 * @throws IOException
	 */
	public void get_sercondDisStatisExceedTimeVO() throws IOException {
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		secondDistatisVO = dissatisfiedFeedbackService.get_sercondDisStatisExceedTimeVO(secondDistatisVO);
		http_response.getWriter().write(gson.toJson(secondDistatisVO));
	}

	/**
	 * 获取二次回访还是不满意的数量
	 * 
	 * @throws IOException
	 */
	public void get_secondDisStatisCountExceedTime() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter().write(dissatisfiedFeedbackService.get_secondDisStatisCountExceedTime() + "");
	}

	/**
	 * @throws IOException
	 * 
	 */
	public void get_feedbackRectificationExceedTimeVO() throws IOException {
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		feedbackRectificationExceedTimeVO = dissatisfiedFeedbackService
				.get_checkFeedbackRectificationVO(feedbackRectificationExceedTimeVO);
		http_response.getWriter().write(gson.toJson(feedbackRectificationExceedTimeVO));
	}

	/**
	 * 得到超过五天仍然未进行整改的数量
	 * 
	 * @throws IOException
	 */
	public void get_countExceedTimeFive() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		http_response.getWriter()
				.write(dissatisfiedFeedbackService.get_countExceedTimeFive(feedbackRectificationExceedTimeVO) + "");
	}

	/**
	 * 审核操作
	 * 
	 * @throws IOException
	 */
	public void checkFeedbackRectification() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		jwcpxt_unit unit = new jwcpxt_unit();
		unit = (jwcpxt_unit) ActionContext.getContext().getSession().get("unit");
		if (dissatisfiedFeedbackService.checkFeedbackRectification(feedbackRectification, unit)) {
			http_response.getWriter().write("1");
		} else {
			http_response.getWriter().write("-1");
		}
	}

	/**
	 * 获取审核的整改反馈表
	 * 
	 * @throws IOException
	 */
	public void get_checkFeedbackRectificationVO() throws IOException {
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		jwcpxt_unit unit = new jwcpxt_unit();
		unit = (jwcpxt_unit) ActionContext.getContext().getSession().get("unit");
		checkFeedbackRectificationVO = dissatisfiedFeedbackService
				.get_checkFeedbackRectificationVO(checkFeedbackRectificationVO, unit);
		http_response.getWriter().write(gson.toJson(checkFeedbackRectificationVO));
	}

	/**
	 * （整改）获取整改反馈表VO
	 * 
	 * @throws IOException
	 */
	public void get_feedbackRectificationVO() throws IOException {
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		jwcpxt_unit unit = new jwcpxt_unit();
		unit = (jwcpxt_unit) ActionContext.getContext().getSession().get("unit");
		feedbackRectificationVO = dissatisfiedFeedbackService.get_feedbackRectificationVO(feedbackRectificationVO,
				unit);
		http_response.getWriter().write(gson.toJson(feedbackRectificationVO));
	}

	/**
	 * 办结操作
	 * 
	 * @throws IOException
	 */
	public void update_dissatisfiedFeedbackState_toEnd() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (dissatisfiedFeedbackService.update_dissatisfiedFeedbackState_toEnd(feedbackRectification)) {
			http_response.getWriter().write("1");
		} else {
			http_response.getWriter().write("-1");
		}
	}

	/**
	 * 通过整改反馈id获得一条记录
	 * 
	 * @throws IOException
	 */
	public void get_feedbackRectficationDO_byId() throws IOException {
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		feedbackRectification = dissatisfiedFeedbackService.get_feedbackRectficationDO_byId(feedbackRectification);
		http_response.getWriter().write(gson.toJson(feedbackRectification));
	}

	/**
	 * 通过不满意反馈id获得一条记录
	 * 
	 * @throws IOException
	 */
	public void get_dissatisfiedFeedbackDO_byId() throws IOException {
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		http_response.setContentType("text/html;charset=utf-8");
		dissatisfiedFeedback = dissatisfiedFeedbackService.get_dissatisfiedFeedbackDO_byId(dissatisfiedFeedback);
		http_response.getWriter().write(gson.toJson(dissatisfiedFeedback));
	}

	/**
	 * 推送
	 * 
	 * @throws IOException
	 */
	public void updade_dissatisfiedFeedbackState_toPush() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (dissatisfiedFeedbackService.updade_dissatisfiedFeedbackState_toPush(dissatisfiedFeedback,
				feedbackRectification)) {
			http_response.getWriter().write("1");
		} else {
			http_response.getWriter().write("-1");
		}
	}

	/**
	 * 驳回不满意反馈表
	 * 
	 * @throws IOException
	 */
	public void update_dissatisfiedFeedbackState_toReject() throws IOException {
		http_response.setContentType("text/html;charset=utf-8");
		if (dissatisfiedFeedbackService.update_dissatisfiedFeedbackState_toReject(dissatisfiedFeedback)) {
			http_response.getWriter().write("1");
		} else {
			http_response.getWriter().write("-1");
		}
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

	public FeedbackRectificationExceedTimeVO getFeedbackRectificationExceedTimeVO() {
		return feedbackRectificationExceedTimeVO;
	}

	public void setFeedbackRectificationExceedTimeVO(
			FeedbackRectificationExceedTimeVO feedbackRectificationExceedTimeVO) {
		this.feedbackRectificationExceedTimeVO = feedbackRectificationExceedTimeVO;
	}

	public CheckFeedbackRectificationVO getCheckFeedbackRectificationVO() {
		return checkFeedbackRectificationVO;
	}

	public void setCheckFeedbackRectificationVO(CheckFeedbackRectificationVO checkFeedbackRectificationVO) {
		this.checkFeedbackRectificationVO = checkFeedbackRectificationVO;
	}

	@Override
	public void setServletRequest(HttpServletRequest http_request) {
		this.http_request = http_request;
	}

	public SecondDistatisVO getSecondDistatisVO() {
		return secondDistatisVO;
	}

	public void setSecondDistatisVO(SecondDistatisVO secondDistatisVO) {
		this.secondDistatisVO = secondDistatisVO;
	}

	public DissatisfiedQuestionVO getDissatisfiedQuestionVO() {
		return dissatisfiedQuestionVO;
	}

	public FeedbackRectificationVO getFeedbackRectificationVO() {
		return feedbackRectificationVO;
	}

	public void setFeedbackRectificationVO(FeedbackRectificationVO feedbackRectificationVO) {
		this.feedbackRectificationVO = feedbackRectificationVO;
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
