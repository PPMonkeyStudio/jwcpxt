package com.pphgzs.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pphgzs.dao.DissatisfiedFeedbackDao;
import com.pphgzs.domain.DO.jwcpxt_dissatisfied_feedback;
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
	public int get_countDissatisfiedQuestionVO(DissatisfiedQuestionVO dissatisfiedQuestionVO) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<DissatisfiedQuestionDTO> get_dataDissatisfiedQuestionVO(DissatisfiedQuestionVO dissatisfiedQuestionVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public jwcpxt_dissatisfied_feedback get_dissatisfiedFeedbackDo_byId(String jwcpxt_dissatisfied_feedback_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
