package com.pphgzs.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pphgzs.dao.DissatisfiedFeedbackDao;
import com.pphgzs.domain.DO.jwcpxt_dissatisfied_feedback;
import com.pphgzs.domain.DO.jwcpxt_feedback_rectification;
import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_unit;
import com.pphgzs.domain.DTO.DissatisfiedQuestionDTO;
import com.pphgzs.domain.DTO.FeedbackRectificationDTO;
import com.pphgzs.domain.DTO.SecondDistatisDTO;
import com.pphgzs.domain.VO.CheckFeedbackRectificationVO;
import com.pphgzs.domain.VO.DissatisfiedQuestionVO;
import com.pphgzs.domain.VO.FeedbackRectificationExceedTimeVO;
import com.pphgzs.domain.VO.FeedbackRectificationVO;
import com.pphgzs.domain.VO.SecondDistatisVO;
import com.pphgzs.util.TimeUtil;

public class DissatisfiedFeedbackDaoImpl implements DissatisfiedFeedbackDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	/**
	 * VO
	 */
	@Override
	public List<SecondDistatisDTO> get_sercondDisStatisExceedTimeVO(SecondDistatisVO secondDistatisVO) {
		Session session = getSession();
		String hql = "select new com.pphgzs.domain.DTO.SecondDistatisDTO(answerChoice,question,serviceDefinition,_option,serviceClient,serviceInstance,unit)"//
				+ " from "//
				+ " jwcpxt_answer_choice answerChoice,"//
				+ " jwcpxt_question question,"//
				+ " jwcpxt_service_definition serviceDefinition,"//
				+ " jwcpxt_option _option,"//
				+ " jwcpxt_service_client serviceClient,"//
				+ " jwcpxt_service_instance serviceInstance,"//
				+ " jwcpxt_unit unit"//
				+ " where "//
				+ " answerChoice.answer_choice_option = _option.jwcpxt_option_id"//
				+ " and _option.option_question = question.jwcpxt_question_id"//
				+ " and question.question_service_definition = serviceDefinition.jwcpxt_service_definition_id"//
				+ " and answerChoice.answer_choice_client = serviceClient.jwcpxt_service_client_id"//
				+ " and serviceClient.service_client_service_instance = serviceInstance.jwcpxt_service_instance_id"//
				+ " and serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id"//
				+ " "//
				+ " and serviceDefinition.jwcpxt_service_definition_id = 'revisit'"//
				+ " and question.question_describe like '请问您是否对整改结果满意'"//
				+ " and _option.option_describe like '不满意'"//
				+ " order by "//
				+ " answerChoice.answer_choice_gmt_create desc";//

		//
		Query query = session.createQuery(hql);
		query.setFirstResult((secondDistatisVO.getCurrPage() - 1) * secondDistatisVO.getPageSize());
		query.setMaxResults(secondDistatisVO.getPageSize());
		List<SecondDistatisDTO> list = new ArrayList<>();
		list = query.list();
		session.clear();
		return list;
	}

	/**
	 * 获取对二次整改仍然为不满意
	 */
	@Override
	public int get_secondDisStatisCountExceedTime() {
		Session session = getSession();
		String hql = "select count(*) from "//
				+ " jwcpxt_answer_choice answerChoice,"//
				+ " jwcpxt_question question,"//
				+ " jwcpxt_service_definition serviceDefinition,"//
				+ " jwcpxt_option _option,"//
				+ " jwcpxt_service_client serviceClient,"//
				+ " jwcpxt_service_instance serviceInstance,"//
				+ " jwcpxt_unit unit"//
				+ " where "//
				+ " answerChoice.answer_choice_option = _option.jwcpxt_option_id"//
				+ " and _option.option_question = question.jwcpxt_question_id"//
				+ " and question.question_service_definition = serviceDefinition.jwcpxt_service_definition_id"//
				+ " and answerChoice.answer_choice_client = serviceClient.jwcpxt_service_client_id"//
				+ " and serviceClient.service_client_service_instance = serviceInstance.jwcpxt_service_instance_id"//
				+ " and serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id"//
				+ " "//
				+ " and serviceDefinition.jwcpxt_service_definition_id = 'revisit'"//
				+ " and question.question_describe like '请问您是否对整改结果满意'"//
				+ " and _option.option_describe like '不满意'";//
		//
		Query query = session.createSQLQuery(hql);
		try {
			int count = ((Number) query.uniqueResult()).intValue();
			return count;
		} catch (ClassCastException e) {
			return 0;
		} finally {
			session.clear();
		}
	}

	/**
	 * 反馈整改表
	 */
	@Override
	public List<FeedbackRectificationDTO> get_checkFeedbackRectificationVO(
			FeedbackRectificationExceedTimeVO feedbackRectificationExceedTimeVO) {
		Session session = getSession();
		String hql = "select new com.pphgzs.domain.DTO.FeedbackRectificationDTO(feedbackRectification,dissatisfiedFeedback,question,serviceClient"//
				+ " ,serviceInstance,serviceDefinition,unit)"//
				+ " from "//
				+ " jwcpxt_feedback_rectification feedbackRectification , "//
				+ " jwcpxt_dissatisfied_feedback dissatisfiedFeedback , "//
				+ " jwcpxt_answer_choice answerChoice , "//
				+ " jwcpxt_question question , "//
				+ " jwcpxt_service_definition serviceDefinition,"//
				+ " jwcpxt_service_client serviceClient , "//
				+ " jwcpxt_service_instance serviceInstance , "//
				+ " jwcpxt_unit unit "//
				+ " where "//
				+ " feedbackRectification.feedback_rectification_dissatisfied_feedback = dissatisfiedFeedback.jwcpxt_dissatisfied_feedback_id "//
				+ " and dissatisfiedFeedback.dissatisfied_feedback_answer_choice=answerChoice.jwcpxt_answer_choice_id "//
				+ " and answerChoice.answer_choice_client=serviceClient.jwcpxt_service_client_id "//
				+ " and answerChoice.answer_choice_question = question.jwcpxt_question_id"//
				+ " and serviceClient.service_client_service_instance=serviceInstance.jwcpxt_service_instance_id "//
				+ " and serviceInstance.service_instance_belong_unit=unit.jwcpxt_unit_id "//
				+ " and serviceInstance.service_instance_service_definition = serviceDefinition.jwcpxt_service_definition_id"//
				+ " and feedbackRectification.feedback_rectification_handle_state = 1"//
				+ " and feedbackRectification.feedback_rectification_gmt_create < :beforeDate"//
				+ " order by feedback_rectification_gmt_create asc";//
		Query query = session.createQuery(hql);
		// 获取五天前
		query.setParameter("beforeDate", TimeUtil.getDateBefore(new Date(), 5));
		query.setFirstResult((feedbackRectificationExceedTimeVO.getCurrPage() - 1)
				* feedbackRectificationExceedTimeVO.getPageSize());
		query.setMaxResults(feedbackRectificationExceedTimeVO.getPageSize());
		List<FeedbackRectificationDTO> list = new ArrayList<>();
		list = query.list();
		session.clear();
		return list;
	}

	/**
	 * 获取超过五天的总记录数
	 */
	@Override
	public int get_countExceedTimeFive() {
		Session session = getSession();
		String hql = "select count(*)"//
				+ " from "//
				+ " jwcpxt_feedback_rectification feedbackRectification , "//
				+ " jwcpxt_dissatisfied_feedback dissatisfiedFeedback , "//
				+ " jwcpxt_answer_choice answerChoice , "//
				+ " jwcpxt_question question , "//
				+ " jwcpxt_service_definition serviceDefinition,"//
				+ " jwcpxt_service_client serviceClient , "//
				+ " jwcpxt_service_instance serviceInstance , "//
				+ " jwcpxt_unit unit "//
				+ " where "//
				+ " feedbackRectification.feedback_rectification_dissatisfied_feedback = dissatisfiedFeedback.jwcpxt_dissatisfied_feedback_id "//
				+ " and dissatisfiedFeedback.dissatisfied_feedback_answer_choice=answerChoice.jwcpxt_answer_choice_id "//
				+ " and answerChoice.answer_choice_client=serviceClient.jwcpxt_service_client_id "//
				+ " and answerChoice.answer_choice_question = question.jwcpxt_question_id"//
				+ " and serviceClient.service_client_service_instance=serviceInstance.jwcpxt_service_instance_id "//
				+ " and serviceInstance.service_instance_belong_unit=unit.jwcpxt_unit_id "//
				+ " and serviceInstance.service_instance_service_definition = serviceDefinition.jwcpxt_service_definition_id"//
				+ " and feedbackRectification.feedback_rectification_handle_state = 1"//
				+ " and feedbackRectification.feedback_rectification_gmt_create < DATE_SUB(DATE_FORMAT(CURRENT_TIME(),'%Y-%m-%d %T'),INTERVAL 5 day)";//
		//
		Query query = session.createSQLQuery(hql);
		try {
			int count = ((Number) query.uniqueResult()).intValue();
			return count;
		} catch (ClassCastException e) {
			return 0;
		} finally {
			session.clear();
		}
	}

	/**
	 * 保存
	 * 
	 * @param obj
	 */
	@Override
	public void saveOrUpdateObject(Object obj) {
		Session session = getSession();
		session.saveOrUpdate(obj);
		session.flush();
		session.clear();
	}

	@Override
	public String get_maxMounthFeedbackRectifi() {
		Session session = getSession();
		String hql = " from "//
				+ " jwcpxt_feedback_rectification "//
				+ " where "//
				+ " feedback_rectification_gmt_create >= :toMonth "//
				+ " order by "//
				+ " feedback_rectification_no desc ";
		//
		Query query = session.createQuery(hql);

		query.setParameter("toMonth", TimeUtil.getStringDay().substring(0, 7));
		query.setMaxResults(1);
		//
		jwcpxt_feedback_rectification jwcpxt_feedback_rectification = (jwcpxt_feedback_rectification) query
				.uniqueResult();
		session.clear();
		//
		String no = "";
		if (jwcpxt_feedback_rectification == null) {
			no = TimeUtil.getStringDay().substring(0, 4) + TimeUtil.getStringDay().substring(5, 7) + "0000";
		} else {
			no = jwcpxt_feedback_rectification.getFeedback_rectification_no();
		}
		//
		return no;
	}

	@Override
	public int get_countDissatisfiedQuestionVO(DissatisfiedQuestionVO dissatisfiedQuestionVO) {
		Session session = getSession();
		String hql = "select count(*) "//
				+ " from "//
				+ " jwcpxt_dissatisfied_feedback "//
				+ " where "//
				+ " dissatisfied_feedback_state like :screenState "//
				+ " and dissatisfied_feedback_gmt_create >= :screenStartTime "//
				+ " and dissatisfied_feedback_gmt_create <= :screenEndTime ";
		Query query = session.createQuery(hql);
		//
		if (dissatisfiedQuestionVO.getScreenState().equals("-1")) {
			query.setParameter("screenState", "%%");
		} else {
			query.setParameter("screenState", dissatisfiedQuestionVO.getScreenState() + "");
		}
		if (dissatisfiedQuestionVO.getScreenStartTime().equals("")) {
			query.setParameter("screenStartTime", "0000-00-00");
		} else {
			query.setParameter("screenStartTime", dissatisfiedQuestionVO.getScreenStartTime() + " 00:00:00");
		}
		if (dissatisfiedQuestionVO.getScreenEndTime().equals("")) {
			query.setParameter("screenEndTime", "9999-99-99");
		} else {
			query.setParameter("screenEndTime", dissatisfiedQuestionVO.getScreenEndTime() + " 23:59:59");
		}
		//
		int count = ((Number) query.uniqueResult()).intValue();
		session.clear();
		return count;
	}

	@Override
	public List<DissatisfiedQuestionDTO> get_dataDissatisfiedQuestionVO(DissatisfiedQuestionVO dissatisfiedQuestionVO) {
		Session session = getSession();
		String hql = "select "//
				+ " new com.pphgzs.domain.DTO.DissatisfiedQuestionDTO(dessatisfiedFeedback,question,serviceClient,serviceInstance,serviceDefinition,unit,_user) "//
				+ " from "//
				+ " jwcpxt_dissatisfied_feedback dessatisfiedFeedback , "//
				+ " jwcpxt_answer_choice choice , "//
				+ " jwcpxt_question question ,"//
				+ " jwcpxt_service_client serviceClient,"//
				+ " jwcpxt_service_instance serviceInstance,"//
				+ " jwcpxt_service_definition serviceDefinition,"//
				+ " jwcpxt_unit unit,"//
				+ " jwcpxt_user _user"//
				+ " where "//
				+ " dessatisfiedFeedback.dissatisfied_feedback_answer_choice = choice.jwcpxt_answer_choice_id "//
				+ " and choice.answer_choice_question = question.jwcpxt_question_id "//
				+ " and choice.answer_choice_client = serviceClient.jwcpxt_service_client_id "//
				+ " and serviceClient.service_client_service_instance = serviceInstance.jwcpxt_service_instance_id "//
				+ " and serviceInstance.service_instance_service_definition = serviceDefinition.jwcpxt_service_definition_id "//
				+ " and serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id "//
				+ " and serviceInstance.service_instance_judge = _user.jwcpxt_user_id "//
				+ " and dessatisfiedFeedback.dissatisfied_feedback_state  like :screenState " //
				+ " and dessatisfiedFeedback.dissatisfied_feedback_gmt_create >= :screenStartTime "//
				+ " and dessatisfiedFeedback.dissatisfied_feedback_gmt_create <= :screenEndTime "//
				+ " order by "//
				+ " dessatisfiedFeedback.dissatisfied_feedback_gmt_create "//
				+ " desc ";
		Query query = session.createQuery(hql);
		//
		if (dissatisfiedQuestionVO.getScreenState().equals("-1")) {
			query.setParameter("screenState", "%%");
		} else {
			query.setParameter("screenState", dissatisfiedQuestionVO.getScreenState() + "");
		}
		if (dissatisfiedQuestionVO.getScreenStartTime().equals("")) {
			query.setParameter("screenStartTime", "0000-00-00");
		} else {
			query.setParameter("screenStartTime", dissatisfiedQuestionVO.getScreenStartTime() + " 00:00:00");
		}
		if (dissatisfiedQuestionVO.getScreenEndTime().equals("")) {
			query.setParameter("screenEndTime", "9999-99-99");
		} else {
			query.setParameter("screenEndTime", dissatisfiedQuestionVO.getScreenEndTime() + " 23:59:59");
		}
		//
		query.setFirstResult((dissatisfiedQuestionVO.getCurrPage() - 1) * dissatisfiedQuestionVO.getPageSize());
		query.setMaxResults(dissatisfiedQuestionVO.getPageSize());
		//
		List<DissatisfiedQuestionDTO> list = query.list();
		session.clear();
		return list;
	}

	@Override
	public jwcpxt_service_client get_serviceClient_byDissatisfiedFeedbackId(String jwcpxt_dissatisfied_feedback_id) {
		Session session = getSession();
		String hql = " select serviceClient "//
				+ " from "//
				+ " jwcpxt_service_client serviceClient , "//
				+ " jwcpxt_dissatisfied_feedback dissatisfiedFeedback , "//
				+ " jwcpxt_answer_choice answerChoice "//
				+ " where "//
				+ " dissatisfiedFeedback.dissatisfied_feedback_answer_choice=answerChoice.jwcpxt_answer_choice_id "//
				+ " and answerChoice.answer_choice_client=serviceClient.jwcpxt_service_client_id "//
				+ " and dissatisfiedFeedback.jwcpxt_dissatisfied_feedback_id=:jwcpxt_dissatisfied_feedback_id";
		//
		Query query = session.createQuery(hql);
		query.setParameter("jwcpxt_dissatisfied_feedback_id", jwcpxt_dissatisfied_feedback_id);
		//
		jwcpxt_service_client jwcpxt_service_client = (jwcpxt_service_client) query.uniqueResult();
		session.clear();
		//
		return jwcpxt_service_client;
	}

	@Override
	public jwcpxt_dissatisfied_feedback get_dissatisfiedFeedbackDo_byId(String jwcpxt_dissatisfied_feedback_id) {
		Session session = getSession();
		String hql = " from "//
				+ " jwcpxt_dissatisfied_feedback "//
				+ " where "//
				+ " jwcpxt_dissatisfied_feedback_id = :jwcpxt_dissatisfied_feedback_id";
		//
		Query query = session.createQuery(hql);
		query.setParameter("jwcpxt_dissatisfied_feedback_id", jwcpxt_dissatisfied_feedback_id);
		//
		jwcpxt_dissatisfied_feedback jwcpxt_dissatisfied_feedback = (jwcpxt_dissatisfied_feedback) query.uniqueResult();
		session.clear();
		//
		return jwcpxt_dissatisfied_feedback;
	}

	@Override
	public jwcpxt_unit get_unit_byDisFeedbackId(String jwcpxt_dissatisfied_feedback_id) {
		Session session = getSession();
		String hql = " select unit "//
				+ " from "//
				+ " jwcpxt_dissatisfied_feedback dissatisfiedFeedback , "//
				+ " jwcpxt_answer_choice answerChoice , "//
				+ " jwcpxt_service_client serviceClient , "//
				+ " jwcpxt_service_instance serviceInstance , "//
				+ " jwcpxt_unit unit "//
				+ " where "//
				+ " dissatisfiedFeedback.dissatisfied_feedback_answer_choice=answerChoice.jwcpxt_answer_choice_id "//
				+ " and answerChoice.answer_choice_client=serviceClient.jwcpxt_service_client_id "//
				+ " and serviceClient.service_client_service_instance=serviceInstance.jwcpxt_service_instance_id "//
				+ " and serviceInstance.service_instance_belong_unit=unit.jwcpxt_unit_id "//
				+ " and dissatisfiedFeedback.jwcpxt_dissatisfied_feedback_id=:jwcpxt_dissatisfied_feedback_id";
		//
		Query query = session.createQuery(hql);
		query.setParameter("jwcpxt_dissatisfied_feedback_id", jwcpxt_dissatisfied_feedback_id);
		//
		jwcpxt_unit jwcpxt_unit = (jwcpxt_unit) query.uniqueResult();
		session.clear();
		//
		return jwcpxt_unit;
	}

	@Override
	public jwcpxt_feedback_rectification get_feedbackRectficationDO_byId(String jwcpxt_feedback_rectification_id) {
		Session session = getSession();
		String hql = " from "//
				+ " jwcpxt_feedback_rectification "//
				+ " where "//
				+ " jwcpxt_feedback_rectification_id = :jwcpxt_feedback_rectification_id";
		//
		Query query = session.createQuery(hql);
		query.setParameter("jwcpxt_feedback_rectification_id", jwcpxt_feedback_rectification_id);
		//
		jwcpxt_feedback_rectification jwcpxt_feedback_rectification = (jwcpxt_feedback_rectification) query
				.uniqueResult();
		session.clear();
		//
		return jwcpxt_feedback_rectification;
	}

	@Override
	public int get_countFeedbackRectificationVO(FeedbackRectificationVO feedbackRectificationVO, jwcpxt_unit unit) {
		Session session = getSession();
		String hql = "select count(*) "//
				+ " from "//
				+ " jwcpxt_feedback_rectification feedbackRectification , "//
				+ " jwcpxt_dissatisfied_feedback dissatisfiedFeedback , "//
				+ " jwcpxt_answer_choice answerChoice , "//
				+ " jwcpxt_question question , "//
				+ " jwcpxt_service_definition serviceDefinition,"//
				+ " jwcpxt_service_client serviceClient , "//
				+ " jwcpxt_service_instance serviceInstance , "//
				+ " jwcpxt_unit unit "//
				+ " where "//
				+ " feedbackRectification.feedback_rectification_handle_state like :screenHandleState "//
				+ " and feedbackRectification.feedback_rectification_audit_state like :screenAuditState "//
				+ " and feedbackRectification.feedback_rectification_title like :screenSearch "//
				+ " and unit.jwcpxt_unit_id like :unitID "//
				//
				+ " and feedbackRectification.feedback_rectification_dissatisfied_feedback = dissatisfiedFeedback.jwcpxt_dissatisfied_feedback_id "//
				+ " and dissatisfiedFeedback.dissatisfied_feedback_answer_choice=answerChoice.jwcpxt_answer_choice_id "//
				+ " and answerChoice.answer_choice_client=serviceClient.jwcpxt_service_client_id "//
				+ " and answerChoice.answer_choice_question = question.jwcpxt_question_id"//
				+ " and serviceClient.service_client_service_instance=serviceInstance.jwcpxt_service_instance_id "//
				+ " and serviceInstance.service_instance_belong_unit=unit.jwcpxt_unit_id "//
				+ " and serviceInstance.service_instance_service_definition = serviceDefinition.jwcpxt_service_definition_id"//
				//
				+ " and feedbackRectification.feedback_rectification_gmt_create >= :screenStartTime "//
				+ " and feedbackRectification.feedback_rectification_gmt_create <= :screenEndTime ";
		Query query = session.createQuery(hql);

		// 办结情况
		if (feedbackRectificationVO.getScreenHandleState().equals("-1")) {
			query.setParameter("screenHandleState", "%%");
		} else {
			query.setParameter("screenHandleState", feedbackRectificationVO.getScreenHandleState());
		}
		// 审核状态
		if (feedbackRectificationVO.getScreenAuditState().equals("-1")) {
			query.setParameter("screenAuditState", "%%");
		} else {
			query.setParameter("screenAuditState", feedbackRectificationVO.getScreenAuditState());
		}
		// 搜索
		query.setParameter("screenSearch", "%" + feedbackRectificationVO.getScreenSearch() + "%");
		// 单位
		if (unit == null || ("").equals(unit.getJwcpxt_unit_id())) {
			query.setParameter("unitID", "%%");
		} else {
			query.setParameter("unitID", unit.getJwcpxt_unit_id());
		}
		//
		if (feedbackRectificationVO.getScreenStartTime().equals("")) {
			query.setParameter("screenStartTime", "0000-00-00");
		} else {
			query.setParameter("screenStartTime", feedbackRectificationVO.getScreenStartTime() + " 00:00:00");
		}
		if (feedbackRectificationVO.getScreenEndTime().equals("")) {
			query.setParameter("screenEndTime", "9999-99-99");
		} else {
			query.setParameter("screenEndTime", feedbackRectificationVO.getScreenEndTime() + " 23:59:59");
		}
		if (query.uniqueResult() == null) {
			return 0;
		}
		//
		int count = ((Number) query.uniqueResult()).intValue();
		session.clear();
		return count;
	}

	@Override
	public List<FeedbackRectificationDTO> get_feedbackRectificationVO(FeedbackRectificationVO feedbackRectificationVO,
			jwcpxt_unit unit) {
		Session session = getSession();
		String hql = "select new com.pphgzs.domain.DTO.FeedbackRectificationDTO(feedbackRectification,dissatisfiedFeedback,question,serviceClient"
				+ ",serviceInstance,serviceDefinition,unit) "//
				+ " from "//
				+ " jwcpxt_feedback_rectification feedbackRectification , "//
				+ " jwcpxt_dissatisfied_feedback dissatisfiedFeedback , "//
				+ " jwcpxt_answer_choice answerChoice , "//
				+ " jwcpxt_question question , "//
				+ " jwcpxt_service_definition serviceDefinition,"//
				+ " jwcpxt_service_client serviceClient , "//
				+ " jwcpxt_service_instance serviceInstance , "//
				+ " jwcpxt_unit unit "//
				+ " where "//
				+ " feedbackRectification.feedback_rectification_handle_state like :screenHandleState "//
				+ " and feedbackRectification.feedback_rectification_audit_state like :screenAuditState "//
				+ " and feedbackRectification.feedback_rectification_title like :screenSearch "//
				+ " and unit.jwcpxt_unit_id like :unitID "//
				//
				+ " and feedbackRectification.feedback_rectification_dissatisfied_feedback = dissatisfiedFeedback.jwcpxt_dissatisfied_feedback_id "//
				+ " and dissatisfiedFeedback.dissatisfied_feedback_answer_choice=answerChoice.jwcpxt_answer_choice_id "//
				+ " and answerChoice.answer_choice_client=serviceClient.jwcpxt_service_client_id "//
				+ " and answerChoice.answer_choice_question = question.jwcpxt_question_id"//
				+ " and serviceClient.service_client_service_instance=serviceInstance.jwcpxt_service_instance_id "//
				+ " and serviceInstance.service_instance_belong_unit=unit.jwcpxt_unit_id "//
				+ " and serviceInstance.service_instance_service_definition = serviceDefinition.jwcpxt_service_definition_id"//
				//
				+ " and feedbackRectification.feedback_rectification_gmt_create >= :screenStartTime "//
				+ " and feedbackRectification.feedback_rectification_gmt_create <= :screenEndTime "//
				+ " order by "//
				+ " feedbackRectification.feedback_rectification_gmt_create "//
				+ " desc ";
		Query query = session.createQuery(hql);
		// 办结情况
		if (feedbackRectificationVO.getScreenHandleState().equals("-1")) {
			query.setParameter("screenHandleState", "%%");
		} else {
			query.setParameter("screenHandleState", feedbackRectificationVO.getScreenHandleState());
		}
		// 审核状态
		if (feedbackRectificationVO.getScreenAuditState().equals("-1")) {
			query.setParameter("screenAuditState", "%%");
		} else {
			query.setParameter("screenAuditState", feedbackRectificationVO.getScreenAuditState());
		}
		// 搜索
		query.setParameter("screenSearch", "%" + feedbackRectificationVO.getScreenSearch() + "%");
		// 单位
		if (unit == null || unit.getJwcpxt_unit_id().equals("")) {
			query.setParameter("unitID", "%%");
		} else {
			query.setParameter("unitID", unit.getJwcpxt_unit_id());
		}
		//
		if (feedbackRectificationVO.getScreenStartTime().equals("")) {
			query.setParameter("screenStartTime", "0000-00-00");
		} else {
			query.setParameter("screenStartTime", feedbackRectificationVO.getScreenStartTime() + " 00:00:00");
		}
		if (feedbackRectificationVO.getScreenEndTime().equals("")) {
			query.setParameter("screenEndTime", "9999-99-99");
		} else {
			query.setParameter("screenEndTime", feedbackRectificationVO.getScreenEndTime() + " 23:59:59");
		}
		//
		query.setFirstResult((feedbackRectificationVO.getCurrPage() - 1) * feedbackRectificationVO.getPageSize());
		query.setMaxResults(feedbackRectificationVO.getPageSize());
		//
		List<FeedbackRectificationDTO> list = query.list();
		session.clear();
		return list;
	}

	@Override
	public List<FeedbackRectificationDTO> get_checkFeedbackRectificationVO(
			CheckFeedbackRectificationVO checkFeedbackRectificationVO, jwcpxt_unit unit) {
		Session session = getSession();
		String hql = "select new com.pphgzs.domain.DTO.FeedbackRectificationDTO(feedbackRectification,dissatisfiedFeedback,question,serviceClient "
				+ " ,serviceInstance,serviceDefinition,unit) "//
				+ " from "//
				+ " jwcpxt_feedback_rectification feedbackRectification , "//
				+ " jwcpxt_dissatisfied_feedback dissatisfiedFeedback , "//
				+ " jwcpxt_answer_choice answerChoice , "//
				+ " jwcpxt_question question , "//
				+ " jwcpxt_service_definition serviceDefinition,"//
				+ " jwcpxt_service_client serviceClient , "//
				+ " jwcpxt_service_instance serviceInstance , "//
				+ " jwcpxt_unit unit "//
				+ " where "//
				+ " feedbackRectification.feedback_rectification_audit_state like :screenCheckState "//
				//
				+ " and "//
				+ " ( feedbackRectification.feedback_rectification_title like :screenSearch "//
				+ " or feedbackRectification.feedback_rectification_client_name like :screenSearch "//
				+ " or feedbackRectification.feedback_rectification_unit_name like :screenSearch ) "//
				//
				+ " and unit.unit_father like :unitID "// 上级单位是传过来的单位
				//
				+ " and feedbackRectification.feedback_rectification_dissatisfied_feedback = dissatisfiedFeedback.jwcpxt_dissatisfied_feedback_id "//
				+ " and dissatisfiedFeedback.dissatisfied_feedback_answer_choice=answerChoice.jwcpxt_answer_choice_id "//
				+ " and answerChoice.answer_choice_client=serviceClient.jwcpxt_service_client_id "//
				+ " and answerChoice.answer_choice_question = question.jwcpxt_question_id"//
				+ " and serviceClient.service_client_service_instance=serviceInstance.jwcpxt_service_instance_id "//
				+ " and serviceInstance.service_instance_belong_unit=unit.jwcpxt_unit_id "//
				+ " and serviceInstance.service_instance_service_definition = serviceDefinition.jwcpxt_service_definition_id"//
				//
				+ " and feedbackRectification.feedback_rectification_gmt_create >= :screenStartTime "//
				+ " and feedbackRectification.feedback_rectification_gmt_create <= :screenEndTime "//
				+ " order by "//
				+ " feedbackRectification.feedback_rectification_gmt_create "//
				+ " desc ";
		Query query = session.createQuery(hql);
		// 办结情况
		if (checkFeedbackRectificationVO.getScreenCheckState().equals("-1")) {
			query.setParameter("screenCheckState", "%%");
		} else {
			query.setParameter("screenCheckState", checkFeedbackRectificationVO.getScreenCheckState());
		}
		// 搜索
		query.setParameter("screenSearch", "%" + checkFeedbackRectificationVO.getScreenSearch() + "%");
		// 单位
		if (unit == null || unit.getJwcpxt_unit_id().equals("")) {
			query.setParameter("unitID", "%%");
		} else if (unit.getUnit_grade() == 1) {
			query.setParameter("unitID", "%%");
		} else {
			query.setParameter("unitID", unit.getJwcpxt_unit_id());
		}

		//
		if (checkFeedbackRectificationVO.getScreenStartTime().equals("")) {
			query.setParameter("screenStartTime", "0000-00-00");
		} else {
			query.setParameter("screenStartTime", checkFeedbackRectificationVO.getScreenStartTime() + " 00:00:00");
		}
		if (checkFeedbackRectificationVO.getScreenEndTime().equals("")) {
			query.setParameter("screenEndTime", "9999-99-99");
		} else {
			query.setParameter("screenEndTime", checkFeedbackRectificationVO.getScreenEndTime() + " 23:59:59");
		}
		query.setFirstResult(
				(checkFeedbackRectificationVO.getCurrPage() - 1) * checkFeedbackRectificationVO.getPageSize());
		query.setMaxResults(checkFeedbackRectificationVO.getPageSize());
		//
		List<FeedbackRectificationDTO> list = query.list();
		session.clear();
		return list;
	}

	@Override
	public int get_checkFeedbackRectificationVOCount(CheckFeedbackRectificationVO checkFeedbackRectificationVO,
			jwcpxt_unit unit) {
		Session session = getSession();
		String hql = "select count(*)"//
				+ " from "//
				+ " jwcpxt_feedback_rectification feedbackRectification , "//
				+ " jwcpxt_dissatisfied_feedback dissatisfiedFeedback , "//
				+ " jwcpxt_answer_choice answerChoice , "//
				+ " jwcpxt_question question , "//
				+ " jwcpxt_service_definition serviceDefinition,"//
				+ " jwcpxt_service_client serviceClient , "//
				+ " jwcpxt_service_instance serviceInstance , "//
				+ " jwcpxt_unit unit "//
				+ " where "//
				+ " feedbackRectification.feedback_rectification_audit_state like :screenCheckState "//
				//
				+ " and "//
				+ " ( feedbackRectification.feedback_rectification_title like :screenSearch "//
				+ " or feedbackRectification.feedback_rectification_client_name like :screenSearch "//
				+ " or feedbackRectification.feedback_rectification_unit_name like :screenSearch ) "//
				//
				+ " and unit.unit_father like :unitID "// 上级单位是传过来的单位
				//
				+ " and feedbackRectification.feedback_rectification_dissatisfied_feedback = dissatisfiedFeedback.jwcpxt_dissatisfied_feedback_id "//
				+ " and dissatisfiedFeedback.dissatisfied_feedback_answer_choice=answerChoice.jwcpxt_answer_choice_id "//
				+ " and answerChoice.answer_choice_client=serviceClient.jwcpxt_service_client_id "//
				+ " and answerChoice.answer_choice_question = question.jwcpxt_question_id"//
				+ " and serviceClient.service_client_service_instance=serviceInstance.jwcpxt_service_instance_id "//
				+ " and serviceInstance.service_instance_belong_unit=unit.jwcpxt_unit_id "//
				+ " and serviceInstance.service_instance_service_definition = serviceDefinition.jwcpxt_service_definition_id"//
				//
				+ " and feedbackRectification.feedback_rectification_gmt_create >= :screenStartTime "//
				+ " and feedbackRectification.feedback_rectification_gmt_create <= :screenEndTime ";
		Query query = session.createQuery(hql);
		// 办结情况
		if (checkFeedbackRectificationVO.getScreenCheckState().equals("-1")) {
			query.setParameter("screenCheckState", "%%");
		} else {
			query.setParameter("screenCheckState", checkFeedbackRectificationVO.getScreenCheckState());
		}
		// 搜索
		query.setParameter("screenSearch", "%" + checkFeedbackRectificationVO.getScreenSearch() + "%");
		// 单位
		if (unit == null || unit.getJwcpxt_unit_id().equals("")) {
			query.setParameter("unitID", "%%");
		} else if (unit.getUnit_grade() == 1) {
			query.setParameter("unitID", "%%");
		} else {
			query.setParameter("unitID", unit.getJwcpxt_unit_id());
		}
		//
		if (checkFeedbackRectificationVO.getScreenStartTime().equals("")) {
			query.setParameter("screenStartTime", "0000-00-00");
		} else {
			query.setParameter("screenStartTime", checkFeedbackRectificationVO.getScreenStartTime() + " 00:00:00");
		}
		if (checkFeedbackRectificationVO.getScreenEndTime().equals("")) {
			query.setParameter("screenEndTime", "9999-99-99");
		} else {
			query.setParameter("screenEndTime", checkFeedbackRectificationVO.getScreenEndTime() + " 23:59:59");
		}
		//
		int count = ((Number) query.uniqueResult()).intValue();
		session.clear();
		return count;
	}

	/**
	 * 根据责任单位获取该责任单位的上级单位
	 */
	@Override
	public jwcpxt_unit get_unitDO_byChildrenUnit(String unitChildrenId) {
		jwcpxt_unit unitFather = new jwcpxt_unit();
		Session session = getSession();
		String hql = "from jwcpxt_unit where unit_father = :unitChildrenId";
		Query query = session.createQuery(hql);
		query.setParameter("unitChildrenId", unitChildrenId);
		unitFather = (jwcpxt_unit) query.uniqueResult();
		return unitFather;
	}

}
