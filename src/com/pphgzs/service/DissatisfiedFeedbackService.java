package com.pphgzs.service;

import com.pphgzs.domain.DO.jwcpxt_feedback_rectification;
import com.pphgzs.domain.VO.DissatisfiedFeedbackVO;

public interface DissatisfiedFeedbackService {

	/**
	 * 获取反馈整改VO
	 * 
	 * @param dissatisfiedFeedbackVO
	 * @return
	 */
	public DissatisfiedFeedbackVO get_dissatisfiedFeedbackVO(DissatisfiedFeedbackVO dissatisfiedFeedbackVO);

	/**
	 * 根据反馈整改id获取反馈整改
	 * 
	 * @param feedbackRectification
	 * @return
	 */
	public jwcpxt_feedback_rectification get_feedbackRectification_byRectificationId(
			jwcpxt_feedback_rectification feedbackRectification);

}
