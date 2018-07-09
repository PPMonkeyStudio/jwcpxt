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
	public List<jwcpxt_feedback_rectification> get_listFeedbackRecitification(
			DissatisfiedFeedbackVO dissatisfiedFeedbackVO);

	/*
	 * public List<RectificationFeedbackDTO>
	 * list_rectificationFeedback_byDissatisfiedFeedbackVO( DissatisfiedFeedbackVO
	 * dissatisfiedFeedbackVO);
	 */

}
