package com.pphgzs.service;

import com.pphgzs.domain.DO.jwcpxt_dissatisfied_feedback;
import com.pphgzs.domain.DO.jwcpxt_feedback_rectification;
import com.pphgzs.domain.VO.DissatisfiedQuestionVO;

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
	public boolean updade_dissatisfiedFeedbackState_toPush(jwcpxt_dissatisfied_feedback dissatisfiedFeedback,jwcpxt_feedback_rectification feedbackRectification);

}
