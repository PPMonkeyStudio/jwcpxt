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

	/**
	 * 获取最大问题序号
	 */
	@Override
	public int get_question_max_sort(String question_service_definition) {
		Session session = getSession();
		// String hql = "select question_sort from jwcpxt_question where"
		// + " question_service_definition = :questionServiceDefinition order by
		// --question_sort desc limit 1";
		// Query query = session.createSQLQuery(hql);
		String hql = "select Max(question_sort) from jwcpxt_question where "
				+ " question_service_definition = :questionServiceDefinition ";
		Query query = session.createQuery(hql);
		query.setParameter("questionServiceDefinition", question_service_definition);
		if (query.uniqueResult() == null) {
			return 0;
		}
		int num = ((Number) query.uniqueResult()).intValue();
		return num;
	}

	/**
	 * 根据问题id获取问题对象
	 */
	@Override
	public jwcpxt_question get_question_byQuestionId(String trim) {
		jwcpxt_question questionInfo = new jwcpxt_question();
		Session session = getSession();
		String hql = "from jwcpxt_question where jwcpxt_question_id = :questionId";
		Query query = session.createQuery(hql);
		query.setParameter("questionId", trim);
		questionInfo = (jwcpxt_question) query.uniqueResult();
		session.clear();
		return questionInfo;
	}

	@Override
	public int get_question_min_sort(String question_service_definition) {
		Session session = getSession();
		// String hql = "select question_sort from jwcpxt_question where"
		// + " question_service_definition = :questionServiceDefinition order by
		// --question_sort desc limit 1";
		// Query query = session.createSQLQuery(hql);
		String hql = "select Min(question_sort) from jwcpxt_question where "
				+ " question_service_definition = :questionServiceDefinition ";
		Query query = session.createQuery(hql);
		query.setParameter("questionServiceDefinition", question_service_definition);
		int num = (int) query.uniqueResult();
		return num;
	}

	@Override
	public jwcpxt_question get_question_moveUpPosition_sort(jwcpxt_question question) {
		List<jwcpxt_question> listQuestion = new ArrayList<>();
		Session session = getSession();
		String hql = "from jwcpxt_question where " + " question_service_definition = :questionServiceDefinition and "
				+ "question_sort < :currentPosition order by question_sort desc";
		Query query = session.createQuery(hql);
		query.setMaxResults(1);
		query.setParameter("questionServiceDefinition", question.getQuestion_service_definition());
		query.setParameter("currentPosition", question.getQuestion_sort());
		listQuestion = query.list();
		if (listQuestion.size() > 0) {
			return listQuestion.get(0);
		} else {
			return null;
		}
	}

	@Override
	public jwcpxt_question get_question_moveDownPosition_sort(jwcpxt_question question) {
		List<jwcpxt_question> listQuestion = new ArrayList<>();
		Session session = getSession();
		String hql = "from jwcpxt_question where " + " question_service_definition = :questionServiceDefinition and"
				+ " question_sort > :currentPosition order by question_sort";
		Query query = session.createQuery(hql);
		query.setMaxResults(1);
		query.setParameter("questionServiceDefinition", question.getQuestion_service_definition());
		query.setParameter("currentPosition", question.getQuestion_sort());
		listQuestion = query.list();
		if (listQuestion.size() > 0) {
			return listQuestion.get(0);
		} else {
			return null;
		}
	}

	@Override
	public boolean delete_question(String question_id) {
		Session session = getSession();
		String hql = "delete from jwcpxt_question where jwcpxt_question_id=:questionId";
		Query query = session.createQuery(hql);
		//
		query.setParameter("questionId", question_id);
		//
		query.executeUpdate();
		session.flush();
		return true;
	}

}
