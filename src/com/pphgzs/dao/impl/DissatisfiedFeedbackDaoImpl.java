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
	 * 
	 */
	@Override
	public String getServiceDefinitionByFeedbackId(String feedbackRectificationId) {
		Session session = getSession();
		jwcpxt_service_definition serviceDefinition = new jwcpxt_service_definition();
		String hql = "select"//
				+ " servicedefinition.jwcpxt_service_definition_id"//
				+ " from"//
				+ " jwcpxt_feedback_rectification feedbackrectification,"//
				+ " jwcpxt_dissatisfied_feedback dissatisfiedfeedback,"//
				+ " jwcpxt_answer_choice answerchoice,"//
				+ " jwcpxt_question question,"//
				+ " jwcpxt_service_definition servicedefinition"//
				+ " where"//
				+ " feedbackrectification.feedback_rectification_dissatisfied_feedback = dissatisfiedfeedback.jwcpxt_dissatisfied_feedback_id"//
				+ " and dissatisfiedfeedback.dissatisfied_feedback_answer_choice = answerchoice.jwcpxt_answer_choice_id"//
				+ " and answerchoice.answer_choice_question = question.jwcpxt_question_id"//
				+ " and question.question_service_definition = servicedefinition.jwcpxt_service_definition_id"//
				+ " and feedbackrectification.jwcpxt_feedback_rectification_id = :feedbackRectificationId";
		Query query = session.createQuery(hql);
		query.setParameter("feedbackRectificationId", feedbackRectificationId);
		return (String) query.uniqueResult();
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
				+ " and serviceDefinition.jwcpxt_service_definition_id = 'revisit'"//
				+ " and question.question_describe like '%整改结果%满意%'"//
				+ " and _option.option_describe like '不满意'"//
				+ " and ("//
				+ " unit.unit_name like :search"//
				+ " or question.question_describe like :search"//
				+ " or unit.unit_contacts_name like :search"//
				+ " )" + " and serviceDefinition.jwcpxt_service_definition_id like :searchService"//
				+ " and serviceClient.service_client_gmt_modified >= :searchTimeStart"//
				+ " and serviceClient.service_client_gmt_modified <= :searchTimeEnd"//
				+ " order by "//
				+ " answerChoice.answer_choice_gmt_create desc";//

		//
		Query query = session.createQuery(hql);
		if ("".equals(secondDistatisVO.getSearch())) {
			query.setParameter("search", "%");
		} else {
			query.setParameter("search", "%" + secondDistatisVO.getSearch() + "%");
		}
		if ("".equals(secondDistatisVO.getSearchService())) {
			query.setParameter("searchService", "%");
		} else {
			query.setParameter("searchService", "%" + secondDistatisVO.getSearchService() + "%");
		}
		if ("".equals(secondDistatisVO.getSearchTimeStart())) {
			query.setParameter("searchTimeStart", "0000-00-00 00:00:00");
		} else {
			query.setParameter("searchTimeStart", secondDistatisVO.getSearchTimeStart() + " 00:00:00");
		}
		if ("".equals(secondDistatisVO.getSearchTimeEnd())) {
			query.setParameter("searchTimeEnd", "9999-99-99 23:59:59");
		} else {
			query.setParameter("searchTimeEnd", secondDistatisVO.getSearchTimeEnd() + " 23:59:59");
		}
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
	public int get_secondDisStatisCountExceedTime(SecondDistatisVO secondDistatisVO) {
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
				+ " and question.question_describe like '%整改结果%满意%'"//
				+ " and _option.option_describe like '不满意'"//
				+ " and ("//
				+ " unit.unit_name like :search"//
				+ " or question.question_describe like :search"//
				+ " or unit.unit_contacts_name like :search"//
				+ " )" + " and serviceDefinition.jwcpxt_service_definition_id like :searchService"//
				+ " and serviceClient.service_client_gmt_modified >= :searchTimeStart"//
				+ " and serviceClient.service_client_gmt_modified <= :searchTimeEnd";//
		/*
		 * System.out.println("hql:" + hql); System.out.println("search:" +
		 * secondDistatisVO.getSearch()); System.out.println("searchService:" +
		 * secondDistatisVO.getSearchService()); System.out.println("searchTimeStart:" +
		 * secondDistatisVO.getSearchTimeStart()); System.out.println("searchTimeEnd:" +
		 * secondDistatisVO.getSearchTimeEnd());
		 */
		//
		Query query = session.createSQLQuery(hql);
		if ("".equals(secondDistatisVO.getSearch())) {
			query.setParameter("search", "%");
			// System.out.println("1");
		} else {
			query.setParameter("search", "%" + secondDistatisVO.getSearch() + "%");
		}
		if ("".equals(secondDistatisVO.getSearchService())) {
			// System.out.println("2");
			query.setParameter("searchService", "%");
		} else {
			query.setParameter("searchService", "%" + secondDistatisVO.getSearchService() + "%");
		}
		if ("".equals(secondDistatisVO.getSearchTimeStart())) {
			// System.out.println("3");
			query.setParameter("searchTimeStart", "0000-00-00 00:00:00");
		} else {
			query.setParameter("searchTimeStart", secondDistatisVO.getSearchTimeStart() + " 00:00:00");
		}
		if ("".equals(secondDistatisVO.getSearchTimeEnd())) {
			// System.out.println("4");
			query.setParameter("searchTimeEnd", "9999-99-99 23:59:59");
		} else {
			query.setParameter("searchTimeEnd", secondDistatisVO.getSearchTimeEnd() + " 23:59:59");
		}
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
				+ " and feedbackRectification.feedback_rectification_audit_state = 1"//
				+ " and feedbackRectification.feedback_rectification_gmt_create < :beforeDate"//
				+ " and ("//
				+ " unit.unit_name like :search "// 单位名称
				+ " or feedbackRectification.feedback_rectification_no like :search"// 编号
				+ " or feedbackRectification.feedback_rectification_title like :search"// 问题标题
				+ " or unit.unit_contacts_name like :search"// 联系人姓名
				+ " )"//
				+ " and serviceDefinition.jwcpxt_service_definition_id like :searchService"// 业务名称
				+ " and feedbackRectification.feedback_rectification_gmt_create >= :searchTimeStart"// 整改反馈创建时间
				+ " and feedbackRectification.feedback_rectification_gmt_create <= :searchTimeEnd"
				+ " order by feedback_rectification_gmt_create asc";//
		Query query = session.createQuery(hql);
		// 获取五天前
		query.setParameter("beforeDate", TimeUtil.getDateBefore(new Date(), 5));
		if ("".equals(feedbackRectificationExceedTimeVO.getSearch())) {
			query.setParameter("search", "%");
		} else {
			query.setParameter("search", "%" + feedbackRectificationExceedTimeVO.getSearch() + "%");
		}
		if ("".equals(feedbackRectificationExceedTimeVO.getSearchService())) {
			query.setParameter("searchService", "%");
		} else {
			query.setParameter("searchService", "%" + feedbackRectificationExceedTimeVO.getSearchService() + "%");
		}
		if ("".equals(feedbackRectificationExceedTimeVO.getSearchTimeStart())) {
			query.setParameter("searchTimeStart", "0000-00-00 00:00:00");
		} else {
			query.setParameter("searchTimeStart", feedbackRectificationExceedTimeVO.getSearchTimeStart() + " 00:00:00");
		}
		if ("".equals(feedbackRectificationExceedTimeVO.getSearchTimeEnd())) {
			query.setParameter("searchTimeEnd", "9999-99-99 23:59:59");
		} else {
			query.setParameter("searchTimeEnd", feedbackRectificationExceedTimeVO.getSearchTimeEnd() + " 23:59:59");
		}
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
	public int get_countExceedTimeFive(FeedbackRectificationExceedTimeVO feedbackRectificationExceedTimeVO) {
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
				+ " and feedbackRectification.feedback_rectification_audit_state = 1"//
				+ " and feedbackRectification.feedback_rectification_gmt_create < :beforeDate"//
				+ " and ("//
				+ " unit.unit_name like :search "// 单位名称
				+ " or feedbackRectification.feedback_rectification_no like :search"// 编号
				+ " or feedbackRectification.feedback_rectification_title like :search"// 问题标题
				+ " or unit.unit_contacts_name like :search"// 联系人姓名
				+ " )"//
				+ " and serviceDefinition.jwcpxt_service_definition_id like :searchService"// 业务名称
				+ " and feedbackRectification.feedback_rectification_gmt_create >= :searchTimeStart"// 整改反馈创建时间
				+ " and feedbackRectification.feedback_rectification_gmt_create <= :searchTimeEnd";//
		//
		Query query = session.createSQLQuery(hql);
		query.setParameter("beforeDate", TimeUtil.getDateBefore(new Date(), 5));
		if ("".equals(feedbackRectificationExceedTimeVO.getSearch())) {
			query.setParameter("search", "%");
		} else {
			query.setParameter("search", "%" + feedbackRectificationExceedTimeVO.getSearch() + "%");
		}
		if ("".equals(feedbackRectificationExceedTimeVO.getSearchService())) {
			query.setParameter("searchService", "%");
		} else {
			query.setParameter("searchService", "%" + feedbackRectificationExceedTimeVO.getSearchService() + "%");
		}
		if ("".equals(feedbackRectificationExceedTimeVO.getSearchTimeStart())) {
			query.setParameter("searchTimeStart", "0000-00-00 00:00:00");
		} else {
			query.setParameter("searchTimeStart", feedbackRectificationExceedTimeVO.getSearchTimeStart() + " 00:00:00");
		}
		if ("".equals(feedbackRectificationExceedTimeVO.getSearchTimeEnd())) {
			query.setParameter("searchTimeEnd", "9999-99-99 23:59:59");
		} else {
			query.setParameter("searchTimeEnd", feedbackRectificationExceedTimeVO.getSearchTimeEnd() + " 23:59:59");
		}
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
		/*
		 * String hql = "select count(*) "// + " from "// +
		 * " jwcpxt_dissatisfied_feedback "// + " where "// +
		 * " dissatisfied_feedback_state like :screenState "// +
		 * " and dissatisfied_feedback_gmt_create >= :screenStartTime "// +
		 * " and dissatisfied_feedback_gmt_create <= :screenEndTime ";
		 */
		String hql = "select "//
				+ " count(*) "//
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
				+ " and ( " + " question.question_describe like :searchTitle " + " or unit.unit_name like :searchTitle "
				+ " or serviceClient.service_client_name like :searchTitle "
				+ " or serviceClient.service_client_phone like :searchTitle " + " )"
				+ " and choice.answer_choice_question = question.jwcpxt_question_id "//
				+ " and choice.answer_choice_client = serviceClient.jwcpxt_service_client_id "//
				+ " and serviceClient.service_client_service_instance = serviceInstance.jwcpxt_service_instance_id "//
				+ " and serviceInstance.service_instance_service_definition = serviceDefinition.jwcpxt_service_definition_id "//
				+ " and serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id "//
				+ " and serviceInstance.service_instance_judge = _user.jwcpxt_user_id "//

				+ " and serviceDefinition.jwcpxt_service_definition_id like :searchService "//

				+ " and dessatisfiedFeedback.dissatisfied_feedback_state  like :screenState " //
				+ " and dessatisfiedFeedback.dissatisfied_feedback_gmt_create >= :screenStartTime "//
				+ " and dessatisfiedFeedback.dissatisfied_feedback_gmt_create <= :screenEndTime ";

		Query query = session.createQuery(hql);
		//
		if (dissatisfiedQuestionVO.getSearchService() == null || "".equals(dissatisfiedQuestionVO.getSearchService())) {
			query.setParameter("searchService", "%%");
		} else {
			query.setParameter("searchService", "%" + dissatisfiedQuestionVO.getSearchService() + "%");
		}
		//
		if (dissatisfiedQuestionVO.getSearchTitle() == null || "".equals(dissatisfiedQuestionVO.getSearchTitle())) {
			query.setParameter("searchTitle", "%%");
		} else {
			query.setParameter("searchTitle", "%" + dissatisfiedQuestionVO.getSearchTitle() + "%");
		}
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
				+ " and ( " + " question.question_describe like :searchTitle " + " or unit.unit_name like :searchTitle "
				+ " or serviceClient.service_client_name like :searchTitle "
				+ " or serviceClient.service_client_phone like :searchTitle " + " )"
				+ " and choice.answer_choice_question = question.jwcpxt_question_id "//
				+ " and choice.answer_choice_client = serviceClient.jwcpxt_service_client_id "//
				+ " and serviceClient.service_client_service_instance = serviceInstance.jwcpxt_service_instance_id "//
				+ " and serviceInstance.service_instance_service_definition = serviceDefinition.jwcpxt_service_definition_id "//
				+ " and serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id "//
				+ " and serviceInstance.service_instance_judge = _user.jwcpxt_user_id "//

				+ " and serviceDefinition.jwcpxt_service_definition_id like :searchService "//

				+ " and dessatisfiedFeedback.dissatisfied_feedback_state  like :screenState " //
				+ " and dessatisfiedFeedback.dissatisfied_feedback_gmt_create >= :screenStartTime "//
				+ " and dessatisfiedFeedback.dissatisfied_feedback_gmt_create <= :screenEndTime "//
				+ " order by "//
				+ " dessatisfiedFeedback.dissatisfied_feedback_gmt_create "//
				+ " desc ";
		Query query = session.createQuery(hql);
		//
		if (dissatisfiedQuestionVO.getSearchService() == null || "".equals(dissatisfiedQuestionVO.getSearchService())) {
			query.setParameter("searchService", "%%");
		} else {
			query.setParameter("searchService", "%" + dissatisfiedQuestionVO.getSearchService() + "%");
		}
		//
		if (dissatisfiedQuestionVO.getSearchTitle() == null || "".equals(dissatisfiedQuestionVO.getSearchTitle())) {
			query.setParameter("searchTitle", "%%");
		} else {
			query.setParameter("searchTitle", "%" + dissatisfiedQuestionVO.getSearchTitle() + "%");
		}
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
				+ " ( " + " feedbackRectification.feedback_rectification_title like :screenSearch "//
				+ " or feedbackRectification.feedback_rectification_client_name like :screenSearch "//

				+ " or feedbackRectification.feedback_rectification_no like :screenSearch "//
				+ " or question.question_describe like :screenSearch "//
				+ " or serviceClient.service_client_phone like :screenSearch "//

				+ " or feedbackRectification.feedback_rectification_unit_name like :screenSearch " + " ) "//
				//
				+ " and unit.unit_father like :unitID "// 上级单位是传过来的单位
				//
				+ " and feedbackRectification.feedback_rectification_handle_state like :searchHandleState "// 办结状态

				+ " and feedbackRectification.feedback_rectification_dissatisfied_feedback = dissatisfiedFeedback.jwcpxt_dissatisfied_feedback_id "//
				+ " and dissatisfiedFeedback.dissatisfied_feedback_answer_choice=answerChoice.jwcpxt_answer_choice_id "//
				+ " and answerChoice.answer_choice_client=serviceClient.jwcpxt_service_client_id "//
				+ " and answerChoice.answer_choice_question = question.jwcpxt_question_id"//
				+ " and serviceClient.service_client_service_instance=serviceInstance.jwcpxt_service_instance_id "//
				+ " and serviceInstance.service_instance_belong_unit=unit.jwcpxt_unit_id "//
				+ " and serviceInstance.service_instance_service_definition = serviceDefinition.jwcpxt_service_definition_id"//
				//
				+ " and serviceDefinition.jwcpxt_service_definition_id like :searchService "//
				//
				+ " and feedbackRectification.feedback_rectification_gmt_create >= :screenStartTime "//
				+ " and feedbackRectification.feedback_rectification_gmt_create <= :screenEndTime "//
				+ " order by "//
				+ " feedbackRectification.feedback_rectification_gmt_create "//
				+ " desc ";
		Query query = session.createQuery(hql);
		// 业务
		if (checkFeedbackRectificationVO.getSearchService() == null
				|| "".equals(checkFeedbackRectificationVO.getSearchService())) {
			query.setParameter("searchService", "%%");
		} else {
			query.setParameter("searchService", "%" + checkFeedbackRectificationVO.getSearchService() + "%");
		}
		// 办理情况
		if ("".equals(checkFeedbackRectificationVO.getSearchHandleState())
				|| checkFeedbackRectificationVO.getSearchHandleState() == null) {
			query.setParameter("searchHandleState", "%%");
		} else {
			query.setParameter("searchHandleState", checkFeedbackRectificationVO.getSearchHandleState());
		}
		// 审核情况
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
				+ " feedbackRectification.feedback_rectification_audit_state like :screenCheckState "//
				//
				+ " and "//
				+ " ( " + " feedbackRectification.feedback_rectification_title like :screenSearch "//
				+ " or feedbackRectification.feedback_rectification_client_name like :screenSearch "//

				+ " or feedbackRectification.feedback_rectification_no like :screenSearch "//
				+ " or question.question_describe like :screenSearch "//
				+ " or serviceClient.service_client_phone like :screenSearch "//

				+ " or feedbackRectification.feedback_rectification_unit_name like :screenSearch " + " ) "//
				//
				+ " and unit.unit_father like :unitID "// 上级单位是传过来的单位
				//
				+ " and feedbackRectification.feedback_rectification_handle_state like :searchHandleState "// 办结状态

				+ " and feedbackRectification.feedback_rectification_dissatisfied_feedback = dissatisfiedFeedback.jwcpxt_dissatisfied_feedback_id "//
				+ " and dissatisfiedFeedback.dissatisfied_feedback_answer_choice=answerChoice.jwcpxt_answer_choice_id "//
				+ " and answerChoice.answer_choice_client=serviceClient.jwcpxt_service_client_id "//
				+ " and answerChoice.answer_choice_question = question.jwcpxt_question_id"//
				+ " and serviceClient.service_client_service_instance=serviceInstance.jwcpxt_service_instance_id "//
				+ " and serviceInstance.service_instance_belong_unit=unit.jwcpxt_unit_id "//
				+ " and serviceInstance.service_instance_service_definition = serviceDefinition.jwcpxt_service_definition_id"//
				//
				+ " and serviceDefinition.jwcpxt_service_definition_id like :searchService "//
				//
				+ " and feedbackRectification.feedback_rectification_gmt_create >= :screenStartTime "//
				+ " and feedbackRectification.feedback_rectification_gmt_create <= :screenEndTime "//
				+ " order by "//
				+ " feedbackRectification.feedback_rectification_gmt_create "//
				+ " desc ";
		Query query = session.createQuery(hql);
		// 业务
		if (checkFeedbackRectificationVO.getSearchService() == null
				|| "".equals(checkFeedbackRectificationVO.getSearchService())) {
			query.setParameter("searchService", "%%");
		} else {
			query.setParameter("searchService", "%" + checkFeedbackRectificationVO.getSearchService() + "%");
		}
		// 办理情况
		if ("".equals(checkFeedbackRectificationVO.getSearchHandleState())
				|| checkFeedbackRectificationVO.getSearchHandleState() == null) {
			query.setParameter("searchHandleState", "%%");
		} else {
			query.setParameter("searchHandleState", checkFeedbackRectificationVO.getSearchHandleState());
		}
		// 审核情况
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

	@Override
	public void updateDissatisfiedClient(String jwcpxt_service_client_id, String jwcpxt_unit_id) {
		Session session = getSession();
		String hql = "UPDATE"//
				+ " jwcpxt_dissatisfied_feedback dissatisfiedFeedback,"//
				+ " jwcpxt_answer_choice answerAnswer,"//
				+ " jwcpxt_service_client serviceClient,"//
				+ " jwcpxt_service_instance serviceInstance"//
				+ " SET dissatisfiedFeedback.dissatisfied_feedback_state = '3',"//
				+ " dissatisfiedFeedback.dissatisfied_feedback_audit_opinion = '同一当事人，与上条合并推送'"//
				+ " WHERE"//
				+ " dissatisfiedFeedback.dissatisfied_feedback_answer_choice = answerAnswer.JWCPXT_ANSWER_CHOICE_ID"//
				+ " AND answerAnswer.ANSWER_CHOICE_CLIENT = serviceClient.JWCPXT_SERVICE_CLIENT_ID"//
				+ " AND serviceClient.SERVICE_CLIENT_SERVICE_INSTANCE = serviceInstance.JWCPXT_SERVICE_INSTANCE_ID"//
				+ " AND dissatisfiedFeedback.dissatisfied_feedback_state = '1'"//
				+ " AND serviceClient.JWCPXT_SERVICE_CLIENT_ID = :clientId"//
				+ " AND serviceInstance.SERVICE_INSTANCE_BELONG_UNIT = :unitId";
		Query query = session.createSQLQuery(hql);
		query.setParameter("clientId", jwcpxt_service_client_id);
		query.setParameter("unitId", jwcpxt_unit_id);
		query.executeUpdate();
		session.clear();
	}

}
