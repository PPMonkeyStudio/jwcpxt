package com.pphgzs.dao;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_dissatisfied_feedback;
import com.pphgzs.domain.DO.jwcpxt_feedback_rectification;
import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_unit;
import com.pphgzs.domain.DTO.DissatisfiedQuestionDTO;
import com.pphgzs.domain.DTO.FeedbackRectificationDTO;
import com.pphgzs.domain.DTO.SecondDistatisDTO;
import com.pphgzs.domain.VO.CheckFeedbackRectificationVO;
import com.pphgzs.domain.VO.DissatisfiedQuestionVO;
import com.pphgzs.domain.VO.FeedbackRectificationExceedTimeVO;
import com.pphgzs.domain.VO.FeedbackRectificationVO;
import com.pphgzs.domain.VO.SecondDistatisVO;

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

	/**
	 * 保存对象
	 * 
	 * @param obj
	 */
	public void saveOrUpdateObject(Object obj);

	/**
	 * 根据id获取不满意反馈表
	 * 
	 * @param jwcpxt_dissatisfied_feedback_id
	 * @return
	 */
	public jwcpxt_dissatisfied_feedback get_dissatisfiedFeedbackDo_byId(String jwcpxt_dissatisfied_feedback_id);

	/**
	 * 获取当月最大的编号
	 * 
	 * @return
	 */
	public String get_maxMounthFeedbackRectifi();

	/**
	 * 根据不反馈整改表获取当事人信息
	 * 
	 * @param jwcpxt_dissatisfied_feedback_id
	 * @return
	 */
	public jwcpxt_service_client get_serviceClient_byDissatisfiedFeedbackId(String jwcpxt_dissatisfied_feedback_id);

	/**
	 * 根据不反馈id获取单位信息
	 * 
	 * @param jwcpxt_dissatisfied_feedback_id
	 * @return
	 */
	public jwcpxt_unit get_unit_byDisFeedbackId(String jwcpxt_dissatisfied_feedback_id);

	/**
	 * 通过整改反馈id获得一条记录
	 * 
	 * @param jwcpxt_feedback_rectification_id
	 * @return
	 */
	public jwcpxt_feedback_rectification get_feedbackRectficationDO_byId(String jwcpxt_feedback_rectification_id);

	/**
	 * 获取整改总记录数
	 * 
	 * @param feedbackRectificationVO
	 * @param unit
	 * @return
	 */
	public int get_countFeedbackRectificationVO(FeedbackRectificationVO feedbackRectificationVO, jwcpxt_unit unit);

	/**
	 * 获取整改数据
	 * 
	 * @param feedbackRectificationVO
	 * @param unit
	 * @return
	 */
	public List<FeedbackRectificationDTO> get_feedbackRectificationVO(FeedbackRectificationVO feedbackRectificationVO,
			jwcpxt_unit unit);

	/**
	 * （审核）分页获得所有该单位下所需审核的整改反馈数量
	 * 
	 * @param checkFeedbackRectificationVO
	 * @param unit
	 * @return
	 */
	public int get_checkFeedbackRectificationVOCount(CheckFeedbackRectificationVO checkFeedbackRectificationVO,
			jwcpxt_unit unit);

	/**
	 * （审核）分页获得所有该单位下所需审核的整改反馈
	 * 
	 * @param checkFeedbackRectificationVO
	 * @param unit
	 * @return
	 */
	public List<FeedbackRectificationDTO> get_checkFeedbackRectificationVO(
			CheckFeedbackRectificationVO checkFeedbackRectificationVO, jwcpxt_unit unit);

	/**
	 * 根据责任单位获取该责任单位的上级单位
	 * 
	 * @param jwcpxt_unit_id
	 * @return
	 */
	public jwcpxt_unit get_unitDO_byChildrenUnit(String jwcpxt_unit_id);

	/**
	 * 获取5天仍然未进行整改的反馈整改
	 * 
	 * @return
	 */
	public int get_countExceedTimeFive(FeedbackRectificationExceedTimeVO feedbackRectificationExceedTimeVO);

	/**
	 * 获取5天仍然未进行整改的反馈整改记录
	 * 
	 * @return
	 */
	public List<FeedbackRectificationDTO> get_checkFeedbackRectificationVO(
			FeedbackRectificationExceedTimeVO feedbackRectificationExceedTimeVO);

	/**
	 * 
	 * @return
	 */
	public int get_secondDisStatisCountExceedTime(SecondDistatisVO secondDistatisVO);

	/**
	 * 
	 * @return
	 */
	public List<SecondDistatisDTO> get_sercondDisStatisExceedTimeVO(SecondDistatisVO secondDistatisVO);

	/**
	 * 直接更改同一当事人在统一单位的其他不满意反馈
	 * 
	 * @param jwcpxt_service_client_id
	 * @param jwcpxt_unit_id
	 */
	public void updateDissatisfiedClient(String jwcpxt_service_client_id, String jwcpxt_unit_id);

	/**
	 * 根据反馈整改表获取业务定义
	 * 
	 * @param jwcpxt_feedback_rectification_id
	 * @return
	 */
	public jwcpxt_service_definition getServiceDefinitionByFeedbackId(String jwcpxt_feedback_rectification_id);
}
