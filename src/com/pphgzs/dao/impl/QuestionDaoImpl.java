package com.pphgzs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.service.jta.platform.internal.SynchronizationRegistryBasedSynchronizationStrategy;

import com.pphgzs.dao.QuestionDao;
import com.pphgzs.domain.DO.jwcpxt_question;
import com.pphgzs.domain.VO.QuestionVO;

@SuppressWarnings("unchecked")
public class QuestionDaoImpl implements QuestionDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	/**
	 * 获取questionVO
	 */
	@Override
	public List<jwcpxt_question> list_question_byQuestionVO(QuestionVO questionVO) {
		/**
		 * 定义对象
		 */
		List<jwcpxt_question> listQuestion = new ArrayList<>();
		/**
		 * end
		 */
		Session session = getSession();
		String hql = "from jwcpxt_question" + " where question_service_definition = :definitionId and"
				+ " question_type like :screenType and question_describe like :screenSearch order by question_sort";
		Query query = session.createQuery(hql);
		query.setParameter("definitionId", "%"
				+ questionVO.getServiceDefinitionDTO().getServiceDefinition().getJwcpxt_service_definition_id() + "%");
		// 类型筛选
		if (questionVO.getScreenType().equals("")) {
			query.setParameter("screenType", "%%");
		} else {
			query.setParameter("screenType", "%" + questionVO.getScreenType() + "%");
		}
		// 搜索筛选
		if (questionVO.getScreenSearch().equals("")) {
			query.setParameter("screenSearch", "%%");
		} else {
			query.setParameter("screenSearch", "%" + questionVO.getScreenSearch() + "%");
		}
		query.setFirstResult((questionVO.getCurrPage() - 1) * questionVO.getPageSize());
		query.setMaxResults(questionVO.getPageSize());
		listQuestion = query.list();
		//
		session.clear();
		return listQuestion;
	}

	/**
	 * 获取问题的数量
	 */
	@Override
	public int get_questionTotalCount_byQuestionVO(QuestionVO questionVO) {
		int count = 0;
		Session session = getSession();
		String hql = "select count(*) from jwcpxt_question"
				+ " where question_service_definition = :jwcpxt_service_definition_id and"
				+ " question_type like :screenType and question_describe like :screenSearch";
		Query query = session.createQuery(hql);
		query.setParameter("jwcpxt_service_definition_id", "%"
				+ questionVO.getServiceDefinitionDTO().getServiceDefinition().getJwcpxt_service_definition_id() + "%");
		// 类型筛选
		if (questionVO.getScreenType().equals("")) {
			query.setParameter("screenType", "%%");
		} else {
			query.setParameter("screenType", "%" + questionVO.getScreenType() + "%");
		}
		// 搜索筛选
		if (questionVO.getScreenSearch().equals("")) {
			query.setParameter("screenSearch", "%%");
		} else {
			query.setParameter("screenSearch", "%" + questionVO.getScreenSearch() + "%");
		}
		//
		count = ((Number) query.uniqueResult()).intValue();
		//
		session.clear();
		return count;
	}

}
