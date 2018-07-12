package com.pphgzs.dao;

import java.util.List;

import com.pphgzs.domain.DTO.DissatisfiedQuestionDTO;
import com.pphgzs.domain.VO.DissatisfiedQuestionVO;

public interface DissatisfiedFeedbackDao {

	/**
	 * 根据VO获取数据的总数 1.根据创建时间筛选 2.根据状态筛选
	 * 
	 * @param dissatisfiedQuestionVO
	 * @return
	 */
	public int get_countDissatisfiedQuestionVO(DissatisfiedQuestionVO dissatisfiedQuestionVO);

	/**
	 * 获取VO里面的数据 DissatisfiedQuestionDTO： 结构jwcpxt_dissatisfied_feedback、question
	 * 
	 * @param dissatisfiedQuestionVO
	 * @return
	 */
	public List<DissatisfiedQuestionDTO> get_dataDissatisfiedQuestionVO(DissatisfiedQuestionVO dissatisfiedQuestionVO);

}
