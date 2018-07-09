package com.pphgzs.dao;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_feedback_rectification;
import com.pphgzs.domain.DTO.RectificationFeedbackDTO;
import com.pphgzs.domain.VO.DissatisfiedFeedbackVO;

public interface DissatisfiedFeedbackDao {

	/**
	 * 根据条件获取反馈整改表
	 * 
	 * @param dissatisfiedFeedbackVO
	 * @return
	 */
	public List<RectificationFeedbackDTO> get_listRectificationFeedbackDTO(
			DissatisfiedFeedbackVO dissatisfiedFeedbackVO);

	/**
	 * 获取VO的总记录数
	 * 
	 * @param dissatisfiedFeedbackVO
	 * @return
	 */
	public int get_listRectificationFeedbackDTOCount(DissatisfiedFeedbackVO dissatisfiedFeedbackVO);

	/**
	 * 根据id获取整改反馈表
	 * 
	 * @param trim
	 * @return
	 */
	public jwcpxt_feedback_rectification get_feedbackRectification_byRectificationId(String rectificationId);

	/*
	 * public List<RectificationFeedbackDTO>
	 * list_rectificationFeedback_byDissatisfiedFeedbackVO( DissatisfiedFeedbackVO
	 * dissatisfiedFeedbackVO);
	 */

}
