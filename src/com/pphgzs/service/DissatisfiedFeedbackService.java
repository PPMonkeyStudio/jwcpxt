package com.pphgzs.service;

import com.pphgzs.domain.DO.jwcpxt_dissatisfied_feedback;
import com.pphgzs.domain.DO.jwcpxt_feedback_rectification;
import com.pphgzs.domain.DO.jwcpxt_unit;
import com.pphgzs.domain.VO.CheckFeedbackRectificationVO;
import com.pphgzs.domain.VO.DissatisfiedQuestionVO;
import com.pphgzs.domain.VO.FeedbackRectificationVO;

public interface DissatisfiedFeedbackService {

	/**
	 * 获取不满意反馈VO
	 * 
	 * @param dissatisfiedQuestionVO
	 * @return
	 */
	public DissatisfiedQuestionVO get_dissatisfiedQuestionVO(DissatisfiedQuestionVO dissatisfiedQuestionVO);

	/**
	 * 驳回不满意反馈表
	 * 
	 * @param dissatisfiedFeedback
	 * @return
	 */
	public boolean update_dissatisfiedFeedbackState_toReject(jwcpxt_dissatisfied_feedback dissatisfiedFeedback);

	/**
	 * 推送不满意反馈表
	 * 
	 * @param dissatisfiedFeedback
	 * @return
	 */
	public boolean updade_dissatisfiedFeedbackState_toPush(jwcpxt_dissatisfied_feedback dissatisfiedFeedback,
			jwcpxt_feedback_rectification feedbackRectification);

	/**
	 * 根据id获取不满意反馈记录
	 * 
	 * @param dissatisfiedFeedback
	 * @return
	 */
	public jwcpxt_dissatisfied_feedback get_dissatisfiedFeedbackDO_byId(
			jwcpxt_dissatisfied_feedback dissatisfiedFeedback);

	/**
	 * 根据id获取反馈整改表
	 * 
	 * @param feedbackRectification
	 * @return
	 */
	public jwcpxt_feedback_rectification get_feedbackRectficationDO_byId(
			jwcpxt_feedback_rectification feedbackRectification);

	/**
	 * 获取整改反馈表VO
	 * 
	 * @param feedbackRectificationVO
	 * @return
	 */
	public FeedbackRectificationVO get_feedbackRectificationVO(FeedbackRectificationVO feedbackRectificationVO,
			jwcpxt_unit unit);

	/**
	 * 办结操作
	 * 
	 * @param feedbackRectification
	 * @return
	 */
	public boolean update_dissatisfiedFeedbackState_toEnd(jwcpxt_feedback_rectification feedbackRectification);

	/**
	 * （审核）分页获得所有该单位下所需审核的整改反馈
	 * 
	 * @param checkFeedbackRectificationVO
	 * @param unit
	 * @return
	 */
	public CheckFeedbackRectificationVO get_checkFeedbackRectificationVO(
			CheckFeedbackRectificationVO checkFeedbackRectificationVO, jwcpxt_unit unit);

	/**
	 * 审核操作
	 * 
	 * @param feedbackRectification
	 * @return
	 */
	public boolean checkFeedbackRectification(jwcpxt_feedback_rectification feedbackRectification,jwcpxt_unit unit);

}
