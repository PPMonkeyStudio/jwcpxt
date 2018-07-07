package com.pphgzs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pphgzs.dao.QuestionDao;
import com.pphgzs.domain.DO.jwcpxt_answer_choice;
import com.pphgzs.domain.DO.jwcpxt_option;
import com.pphgzs.domain.DO.jwcpxt_option_inquiries;
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
		String hql = "from jwcpxt_question" + " where question_service_definition like :definitionId and"
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
				+ " where question_service_definition like :jwcpxt_service_definition_id and"
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

	@Override
	public List<jwcpxt_option> get_option_byQuestionId(String questionId) {
		List<jwcpxt_option> listOption = new ArrayList<>();
		Session session = getSession();
		String hql = "from jwcpxt_option where option_question = :questionId order by option_sort";
		Query query = session.createQuery(hql);
		query.setParameter("questionId", questionId);
		listOption = query.list();
		return listOption;
	}

	@Override
	public List<jwcpxt_option_inquiries> get_optionInquireies_byOptionId(String optionId) {
		List<jwcpxt_option_inquiries> listOptionInquiries = new ArrayList<>();
		Session session = getSession();
		String hql = "from jwcpxt_option_inquiries where option_inquiries_option = :optionId order by option_inquiries_gmt_create";
		Query query = session.createQuery(hql);
		query.setParameter("optionId", optionId);
		listOptionInquiries = query.list();
		return listOptionInquiries;
	}

	@Override
	public int get_option_max_sort(String option_question) {
		Session session = getSession();
		String hql = "select Max(option_sort) from jwcpxt_option where option_question = :questionId";
		Query query = session.createQuery(hql);
		query.setParameter("questionId", option_question);
		if (query.uniqueResult() == null) {
			return 0;
		}
		int num = ((Number) query.uniqueResult()).intValue();
		return num;
	}

	@Override
	public jwcpxt_option get_option_byOptionId(String optionId) {
		jwcpxt_option optionInfo = new jwcpxt_option();
		Session session = getSession();
		String hql = "from jwcpxt_option where jwcpxt_option_id = :optionId";
		Query query = session.createQuery(hql);
		query.setParameter("optionId", optionId);
		optionInfo = (jwcpxt_option) query.uniqueResult();
		session.clear();
		return optionInfo;
	}

	@Override
	public int get_option_min_sort(String questionId) {
		Session session = getSession();
		String hql = "select Min(option_sort) from jwcpxt_option where " + " option_question = :questionId ";
		Query query = session.createQuery(hql);
		query.setParameter("questionId", questionId);
		int num = (int) query.uniqueResult();
		return num;
	}

	@Override
	public jwcpxt_option get_option_moveUpPosition_sort(jwcpxt_option option) {
		List<jwcpxt_option> listOption = new ArrayList<>();
		Session session = getSession();
		String hql = "from jwcpxt_option where " + " option_question = :questionId and"
				+ " option_sort < :currentPosition order by option_sort desc";
		Query query = session.createQuery(hql);
		query.setMaxResults(1);
		query.setParameter("questionId", option.getOption_question());
		query.setParameter("currentPosition", option.getOption_sort());
		listOption = query.list();
		if (listOption.size() > 0) {
			return listOption.get(0);
		} else {
			return null;
		}
	}

	@Override
	public jwcpxt_option get_option_moveDownPosition_sort(jwcpxt_option option) {
		List<jwcpxt_option> listOption = new ArrayList<>();
		Session session = getSession();
		String hql = "from jwcpxt_option where " + " option_question = :questionId and"
				+ " option_sort > :currentPosition order by option_sort";
		Query query = session.createQuery(hql);
		query.setMaxResults(1);
		query.setParameter("questionId", option.getOption_question());
		query.setParameter("currentPosition", option.getOption_sort());
		listOption = query.list();
		if (listOption.size() > 0) {
			return listOption.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 获取某个问题的所有选择题回答对象
	 */
	@Override
	public List<jwcpxt_answer_choice> list_choiceAnswer_byQuestionId(String questionId) {
		List<jwcpxt_answer_choice> listChoiceAnswer = new ArrayList<>();
		Session session = getSession();
		String hql = "from jwcpxt_answer_choice where answer_choice_question = :questionId";
		Query query = session.createQuery(hql);
		listChoiceAnswer = query.list();
		return listChoiceAnswer;
	}

	@Override
	public boolean delete_question_byOptionId(String optionId) {
		Session session = getSession();
		String hql = "delete from jwcpxt_question where question_service_definition = :optionId";
		Query query = session.createQuery(hql);
		//
		query.setParameter("optionId", optionId);
		//
		query.executeUpdate();
		session.flush();
		return true;
	}

	@Override
	public boolean delete_option_byOptionId(String optionId) {
		Session session = getSession();
		String hql = "delete from jwcpxt_option where jwcpxt_option_id = :optionId";
		Query query = session.createQuery(hql);
		//
		query.setParameter("optionId", optionId);
		//
		query.executeUpdate();
		session.flush();
		return true;
	}

}
