package com.pphgzs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pphgzs.dao.DissatisfiedFeedbackDao;
import com.pphgzs.domain.DO.jwcpxt_feedback_rectification;
import com.pphgzs.domain.VO.DissatisfiedFeedbackVO;

public class DissatisfiedFeedbackDaoImpl implements DissatisfiedFeedbackDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	/**
	 * 获取反馈整改表
	 */
	@Override
	public List<jwcpxt_feedback_rectification> get_listFeedbackRecitification(
			DissatisfiedFeedbackVO dissatisfiedFeedbackVO) {
		List<jwcpxt_feedback_rectification> listFeedbackRecitification = new ArrayList<>();
		Session session = getSession();
		String hql = "select feedbackRectification from jwcpxt_feedback_rectification feedbackRectification,"
				+ "jwcpxt_dissatisfied_feedback dissatisfiedFeedback";
		return null;
	}

	/**
	 * 根据VO获取反馈整改列表
	 */
	/*
	 * @Override public List<RectificationFeedbackDTO>
	 * list_rectificationFeedback_byDissatisfiedFeedbackVO( DissatisfiedFeedbackVO
	 * dissatisfiedFeedbackVO) { List<RectificationFeedbackDTO>
	 * list_rectificationFeedback = new ArrayList<>(); Session session =
	 * getSession(); String hql =
	 * "select new com.pphgzs.domain.DTO.RectificationFeedbackDTO(feedbackRectification,dissatisfiedFeedback,"
	 * +
	 * "new com.pphgzs.domain.DTO.UnitDTO(unit,user)) from jwcpxt_feedback_rectification feedbackRectification,"
	 * +
	 * "jwcpxt_dissatisfied_feedback dissatisfiedFeedback, jwcpxt_unit unit, jwcpxt_user user "
	 * +
	 * " jwcpxt_answer_choice answerChoice,jwcpxt_question question,jwcpxt_service_definition serviceDefinition "
	 * +
	 * " where feedbackRectification.feedback_rectification_dissatisfied = dissatisfiedFeedback.jwcpxt_dissatisfied_feedback_id "
	 * +
	 * " and dissatisfiedFeedback.dissatisfied_feedback_answer_choice =  answerChoice.jwcpxt_answer_choice_id"
	 * +
	 * " and answerChoice.answer_choice_question = question.jwcpxt_question_id and question.question_service_definition = serviceDefinition.jwcpxt_service_definition_id "
	 * +
	 * " and serviceDefinition.service_definition_unit = unit.jwcpxt_unit_id and unit.unit_reorganizer=user.jwcpxt_user_id"
	 * ; // Query query = session.createQuery(hql); // //
	 * query.setParameter("serviceDefinitionID", serviceDefinitionID); //
	 * list_rectificationFeedback = query.list(); session.clear(); return
	 * list_rectificationFeedback; }
	 */
}
