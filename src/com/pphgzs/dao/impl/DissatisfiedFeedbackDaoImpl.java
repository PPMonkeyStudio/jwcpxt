package com.pphgzs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pphgzs.dao.DissatisfiedFeedbackDao;
import com.pphgzs.domain.DTO.RectificationFeedbackDTO;
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
	public List<RectificationFeedbackDTO> get_listRectificationFeedbackDTO(
			DissatisfiedFeedbackVO dissatisfiedFeedbackVO) {
		List<RectificationFeedbackDTO> listRectificationFeedbackDTO = new ArrayList<>();
		Session session = getSession();
		String hql = "select new com.pphgzs.domain.DTO.RectificationFeedbackDTO(feedbackRectification,dissatisfiedFeedback,unit,u_user) from jwcpxt_feedback_rectification feedbackRectification,"
				+ "jwcpxt_dissatisfied_feedback dissatisfiedFeedback,jwcpxt_answer_choice answerChoice,jwcpxt_question question,"
				+ "jwcpxt_service_definition serviceDefinition,jwcpxt_unit unit,jwcpxt_user u_user" + " where "
				+ "feedbackRectification.jwcpxt_feedback_rectification_id = dissatisfiedFeedback.dissatisfied_feedback and "
				+ " dissatisfiedFeedback.dissatisfied_feedback_answer_choice = answerChoice.jwcpxt_answer_choice_id and "
				+ " answerChoice.answer_choice_question = question.jwcpxt_question_id and question.question_service_definition = serviceDefinition.jwcpxt_service_definition_id and "
				+ " serviceDefinition.service_definition_unit = unit.jwcpxt_unit_id and unit.unit_reorganizer = u_user.jwcpxt_user_id and "
				+ " unit.jwcpxt_unit_id like :unitId and  feedbackRectification.feedback_rectification_time >= :rectificationStartTime and "
				+ " feedbackRectification.feedback_rectification_time <= :rectificationStopTime group by  dissatisfiedFeedback.dissatisfied_feedback";
		
		Query query = session.createQuery(hql);
		//
		if ("".equals(dissatisfiedFeedbackVO.getUnit())) {
			query.setParameter("unitId", "%%");
		} else {
			query.setParameter("unitId", "%" + dissatisfiedFeedbackVO.getUnit() + "%");
		}
		if ("".equals(dissatisfiedFeedbackVO.getRectificationStartTime())) {
			query.setParameter("rectificationStartTime", "0000-00-00");
		} else {
			query.setParameter("rectificationStartTime", dissatisfiedFeedbackVO.getRectificationStartTime());
		}
		if ("".equals(dissatisfiedFeedbackVO.getRectificationStopTime())) {
			query.setParameter("rectificationStopTime", "9999-99-99");
		} else {
			query.setParameter("rectificationStopTime", dissatisfiedFeedbackVO.getRectificationStopTime());
		}
		query.setFirstResult((dissatisfiedFeedbackVO.getCurrPage() - 1) * dissatisfiedFeedbackVO.getPageSize());
		query.setMaxResults(dissatisfiedFeedbackVO.getPageSize());
		listRectificationFeedbackDTO = query.list();
		return listRectificationFeedbackDTO;
	}

	@Override
	public int get_listRectificationFeedbackDTOCount(DissatisfiedFeedbackVO dissatisfiedFeedbackVO) {
		int count = 0;
		Session session = getSession();
		String hql = "select count(*) from jwcpxt_feedback_rectification feedbackRectification,"
				+ "jwcpxt_dissatisfied_feedback dissatisfiedFeedback,jwcpxt_answer_choice answerChoice,jwcpxt_question question,"
				+ "jwcpxt_service_definition serviceDefinition,jwcpxt_unit unit,jwcpxt_user u_user" + " where "
				+ "feedbackRectification.jwcpxt_feedback_rectification_id = dissatisfiedFeedback.dissatisfied_feedback and "
				+ " dissatisfiedFeedback.dissatisfied_feedback_answer_choice = answerChoice.jwcpxt_answer_choice_id and "
				+ " answerChoice.answer_choice_question = question.jwcpxt_question_id and question.question_service_definition = serviceDefinition.jwcpxt_service_definition_id and "
				+ " serviceDefinition.service_definition_unit = unit.jwcpxt_unit_id and unit.unit_reorganizer = u_user.jwcpxt_user_id and "
				+ " unit.jwcpxt_unit_id like :unitId and  feedbackRectification.feedback_rectification_time >= :rectificationStartTime and "
				+ " feedbackRectification.feedback_rectification_time <= :rectificationStopTime group by  dissatisfiedFeedback.dissatisfied_feedback";
		Query query = session.createQuery(hql);
		//
		if ("".equals(dissatisfiedFeedbackVO.getUnit())) {
			query.setParameter("unitId", "%%");
		} else {
			query.setParameter("unitId", "%" + dissatisfiedFeedbackVO.getUnit() + "%");
		}
		if ("".equals(dissatisfiedFeedbackVO.getRectificationStartTime())) {
			query.setParameter("rectificationStartTime", "0000-00-00");
		} else {
			query.setParameter("rectificationStartTime", dissatisfiedFeedbackVO.getRectificationStartTime());
		}
		if ("".equals(dissatisfiedFeedbackVO.getRectificationStopTime())) {
			query.setParameter("rectificationStopTime", "9999-99-99");
		} else {
			query.setParameter("rectificationStopTime", dissatisfiedFeedbackVO.getRectificationStopTime());
		}
		count = ((Number) query.uniqueResult()).intValue();
		return count;
	}

}
