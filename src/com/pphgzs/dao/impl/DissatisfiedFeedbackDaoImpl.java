package com.pphgzs.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pphgzs.dao.DissatisfiedFeedbackDao;
import com.pphgzs.domain.DO.jwcpxt_dissatisfied_feedback;
import com.pphgzs.domain.DO.jwcpxt_feedback_rectification;
import com.pphgzs.domain.DTO.DissatisfiedQuestionDTO;
import com.pphgzs.domain.VO.DissatisfiedQuestionVO;

public class DissatisfiedFeedbackDaoImpl implements DissatisfiedFeedbackDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
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
	}

	@Override
	public String get_maxMounthFeedbackRectifi() {
		Session session = getSession();
		String hql = " from jwcpxt_feedback_rectification "//
				+ " order by feedback_rectification_no desc ";
		//
		Query query = session.createQuery(hql);
		query.setMaxResults(1);
		//
		jwcpxt_feedback_rectification jwcpxt_feedback_rectification = (jwcpxt_feedback_rectification) query
				.uniqueResult();
		session.clear();
		return jwcpxt_feedback_rectification.getFeedback_rectification_no();
	}

	@Override
	public int get_countDissatisfiedQuestionVO(DissatisfiedQuestionVO dissatisfiedQuestionVO) {
		Session session = getSession();
		String hql = "select count(*) from jwcpxt_dissatisfied_feedback "//
				+ " where dissatisfied_feedback_state like :screenState "//
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
			query.setParameter("screenStartTime", dissatisfiedQuestionVO.getScreenStartTime());
		}
		if (dissatisfiedQuestionVO.getScreenEndTime().equals("")) {
			query.setParameter("screenEndTime", "9999-99-99");
		} else {
			query.setParameter("screenEndTime", dissatisfiedQuestionVO.getScreenEndTime());
		}
		//
		int count = ((Number) query.uniqueResult()).intValue();
		session.clear();
		return count;
	}

	@Override
	public List<DissatisfiedQuestionDTO> get_dataDissatisfiedQuestionVO(DissatisfiedQuestionVO dissatisfiedQuestionVO) {
		Session session = getSession();
		String hql = "select new com.pphgzs.domain.DTO.DissatisfiedQuestionDTO(dessatisfiedFeedback,question) "//
				+ " from jwcpxt_dissatisfied_feedback dessatisfiedFeedback,"//
				+ " jwcpxt_answer_choice choice,"//
				+ " jwcpxt_question question "//
				+ " where dessatisfiedFeedback.dissatisfied_feedback_answer_choice = choice.jwcpxt_answer_choice_id "//
				+ " and choice.answer_choice_question = question.jwcpxt_question_id "//
				+ " and dessatisfiedFeedback.dissatisfied_feedback_state  like :screenState " //
				+ " and dessatisfiedFeedback.dissatisfied_feedback_gmt_create >= :screenStartTime "//
				+ " and dessatisfiedFeedback.dissatisfied_feedback_gmt_create <= :screenEndTime "//
				+ " order by dessatisfiedFeedback.dissatisfied_feedback_gmt_create desc";
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
			query.setParameter("screenStartTime", dissatisfiedQuestionVO.getScreenStartTime());
		}
		if (dissatisfiedQuestionVO.getScreenEndTime().equals("")) {
			query.setParameter("screenEndTime", "9999-99-99");
		} else {
			query.setParameter("screenEndTime", dissatisfiedQuestionVO.getScreenEndTime());
		}
		//
		List<DissatisfiedQuestionDTO> list = query.list();
		session.clear();
		return list;
	}

	@Override
	public jwcpxt_dissatisfied_feedback get_dissatisfiedFeedbackDo_byId(String jwcpxt_dissatisfied_feedback_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
