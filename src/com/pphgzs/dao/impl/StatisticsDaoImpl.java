package com.pphgzs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pphgzs.dao.StatisticsDao;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DTO.QuestionOptionAnswerDTO;
import com.pphgzs.domain.DTO.ReturnVisitDTO;
import com.pphgzs.domain.DTO.ServiceGradeDTO;
import com.pphgzs.domain.VO.ReturnVisitVO;
import com.pphgzs.domain.VO.StatisDissaQuestionDateVO;
import com.pphgzs.domain.VO.StatisDissaServiceDateVO;
import com.pphgzs.domain.VO.StatisDissatiDateVO;

public class StatisticsDaoImpl implements StatisticsDao {
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 
	 */
	@Override
	public List<ReturnVisitDTO> getUserCountVO(ReturnVisitVO returnVisitVO) {
		Session session = getSession();
		String hql = "select new com.pphgzs.domain.DTO.ReturnVisitDTO(serviceClient.service_client_visit,count(*))"//
				+ " from"//
				+ " jwcpxt_service_client serviceClient,"//
				+ " jwcpxt_service_instance serviceInstance"//
				+ " where"//
				+ " serviceClient.service_client_service_instance = serviceInstance.jwcpxt_service_instance_id"//
				+ " and serviceClient.service_client_gmt_modified >= :beforeDate"//
				+ " and serviceClient.service_client_gmt_modified < :afterDate"//
				+ " and serviceInstance.service_instance_judge like :userId"//
				+ " group by serviceClient.service_client_visit";
		Query query = session.createQuery(hql);
		// 昨天和今天
		if ("".equals(returnVisitVO.getStartTime())) {
			query.setParameter("beforeDate", "0000-00-00");
		} else {
			query.setParameter("beforeDate", returnVisitVO.getStartTime() + " 00:00:00");
		}
		if ("".equals(returnVisitVO.getEndTime())) {
			query.setParameter("afterDate", "9999-99-99");
		} else {
			query.setParameter("afterDate", returnVisitVO.getEndTime() + " 23:59:59");
		}
		if ("".equals(returnVisitVO.getUserId())) {
			query.setParameter("userId", "%%");
		} else {
			query.setParameter("userId", "%" + returnVisitVO.getUserId() + "%");
		}
		List<ReturnVisitDTO> listReturnVisitDTO = new ArrayList<>();
		listReturnVisitDTO = query.list();
		return listReturnVisitDTO;
	}

	/**
	 * 总数量
	 */
	@Override
	public int get_totalCountService_byTime(StatisDissaServiceDateVO statisDissaServiceDateVO, String startTime,
			String endTime, jwcpxt_service_definition serviceDefinition) {
		Session session = getSession();
		String hql = "select count(*)"//
				+ " from"//
				+ " jwcpxt_service_definition serviceDefinition,"//
				+ " jwcpxt_unit unit,"//
				+ " jwcpxt_answer_choice answerChoice,"//
				+ " jwcpxt_service_client serviceClient,"//
				+ " jwcpxt_service_instance serviceInstance"//
				+ " where"//
				+ " answerChoice.answer_choice_client = serviceClient.jwcpxt_service_client_id"//
				+ " and serviceClient.service_client_service_instance = serviceInstance.jwcpxt_service_instance_id"//
				+ " and serviceInstance.service_instance_service_definition = serviceDefinition.jwcpxt_service_definition_id"//
				+ " and serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id"//
				+ " and unit.jwcpxt_unit_id like :unitId"//
				+ "	and serviceDefinition.jwcpxt_service_definition_id like :serviceDefinitionId"//
				+ " and serviceInstance.service_instance_date >= :startTime "//
				+ " and serviceInstance.service_instance_date < :endTime ";
		Query query = session.createQuery(hql);
		if (statisDissaServiceDateVO.getScreenUnit().equals("")) {
			query.setParameter("unitId", "%%");
		} else {
			query.setParameter("unitId", statisDissaServiceDateVO.getScreenUnit());
		}
		query.setParameter("startTime", startTime + " 00:00:00");
		query.setParameter("endTime", endTime + " 23:59:59");
		query.setParameter("serviceDefinitionId", serviceDefinition.getJwcpxt_service_definition_id());
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
	 * 获取该单位所有的业务
	 */
	@Override
	public List<jwcpxt_service_definition> get_statisService_byTime(StatisDissaServiceDateVO statisDissaServiceDateVO) {
		Session session = getSession();
		String hql = "select distinct(serviceDefinition)"//
				+ " from"//
				+ " jwcpxt_service_definition serviceDefinition,"//
				+ " jwcpxt_unit unit,"//
				+ " jwcpxt_unit_service unitService"//
				+ " where unitService.unit_id = unit.jwcpxt_unit_id"//
				+ " and unitService.service_definition_id = serviceDefinition.jwcpxt_service_definition_id"
				+ " and unit.jwcpxt_unit_id like :unitId";//
		Query query = session.createQuery(hql);
		if (statisDissaServiceDateVO.getScreenUnit().equals("")) {
			query.setParameter("unitId", "%%");
		} else {
			query.setParameter("unitId", statisDissaServiceDateVO.getScreenUnit());
		}
		List<jwcpxt_service_definition> list = new ArrayList<>();
		list = query.list();
		session.clear();
		return list;
	}

	/**
	 * 根据VO获取改业务的所有不满意问题数量
	 */
	@Override
	public int get_countStatisDateDTO(StatisDissaQuestionDateVO statisDissaQuestionDateVO, String startTime,
			String endTime, QuestionOptionAnswerDTO questionOptionAnswerDTO) {
		Session session = getSession();
		String hql = "select count(*) from"//
				+ " jwcpxt_dissatisfied_feedback dissatisfiedFeedback,"//
				+ " jwcpxt_answer_choice answerChoice,"//
				+ "	jwcpxt_option _option,"//
				+ "	jwcpxt_question question,"//
				+ " jwcpxt_service_client serviceClient,"//
				+ " jwcpxt_service_instance serviceInstance,"//
				+ " jwcpxt_unit unit,"//
				+ " jwcpxt_service_definition serviceDefinition"//
				+ " where"//
				+ " "//
				+ " dissatisfiedFeedback.dissatisfied_feedback_answer_choice = answerChoice.jwcpxt_answer_choice_id"//
				+ " and answerChoice.answer_choice_option = _option.jwcpxt_option_id"//
				+ "	and answerChoice.answer_choice_question = question.jwcpxt_question_id"//
				+ " and answerChoice.answer_choice_client = serviceClient.jwcpxt_service_client_id"//
				+ " and serviceClient.service_client_service_instance = serviceInstance.jwcpxt_service_instance_id"//
				+ " and serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id"//
				+ "	and serviceInstance.service_instance_service_definition = serviceDefinition.jwcpxt_service_definition_id"//
				+ " "//
				+ " and unit.jwcpxt_unit_id like :unitId"//
				+ "	and serviceDefinition.jwcpxt_service_definition_id like :serviceDefinitionId"//
				+ " and serviceInstance.service_instance_date >= :startTime "//
				+ " and serviceInstance.service_instance_date < :endTime "
				+ " and question.jwcpxt_question_id like :questionId"//
				+ " and _option.jwcpxt_option_id like :optionId";//
		Query query = session.createQuery(hql);
		if (statisDissaQuestionDateVO.getScreenUnit().equals("")) {
			query.setParameter("unitId", "%%");
		} else {
			query.setParameter("unitId", statisDissaQuestionDateVO.getScreenUnit());
		}
		query.setParameter("startTime", startTime + " 00:00:00");
		query.setParameter("endTime", endTime + " 23:59:59");
		query.setParameter("serviceDefinitionId", statisDissaQuestionDateVO.getScreenService());
		query.setParameter("questionId", questionOptionAnswerDTO.getQuestion().getJwcpxt_question_id());
		query.setParameter("optionId", questionOptionAnswerDTO.getOption().getJwcpxt_option_id());
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
	 * 根据VO获取该业务的所有问题以及选项
	 */
	@Override
	public List<QuestionOptionAnswerDTO> get_pushQuestionOption(StatisDissaQuestionDateVO statisDissaQuestionDateVO) {
		Session session = getSession();
		String hql = "select new com.pphgzs.domain.DTO.QuestionOptionAnswerDTO(question,_option) from"//
				+ " jwcpxt_dissatisfied_feedback dissatisfiedFeedback,"//
				+ " jwcpxt_answer_choice answerChoice,"//
				+ "	jwcpxt_option _option,"//
				+ "	jwcpxt_question question,"//
				+ " jwcpxt_service_client serviceClient,"//
				+ " jwcpxt_service_instance serviceInstance,"//
				+ " jwcpxt_unit unit,"//
				+ " jwcpxt_service_definition serviceDefinition"//
				+ " where"//
				+ " "//
				+ " dissatisfiedFeedback.dissatisfied_feedback_answer_choice = answerChoice.jwcpxt_answer_choice_id"//
				+ " and answerChoice.answer_choice_option = _option.jwcpxt_option_id"//
				+ "	and answerChoice.answer_choice_question = question.jwcpxt_question_id"//
				+ " and answerChoice.answer_choice_client = serviceClient.jwcpxt_service_client_id"//
				+ " and serviceClient.service_client_service_instance = serviceInstance.jwcpxt_service_instance_id"//
				+ " and serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id"//
				+ "	and serviceInstance.service_instance_service_definition = serviceDefinition.jwcpxt_service_definition_id"//
				+ " "//
				+ " and unit.jwcpxt_unit_id like :unitId"//
				+ "	and serviceDefinition.jwcpxt_service_definition_id like :serviceDefinitionId";//
		Query query = session.createQuery(hql);
		if (statisDissaQuestionDateVO.getScreenUnit().equals("")) {
			query.setParameter("unitId", "%%");
		} else {
			query.setParameter("unitId", statisDissaQuestionDateVO.getScreenUnit());
		}
		if (statisDissaQuestionDateVO.getScreenService().equals("")) {
			query.setParameter("serviceDefinitionId", "%%");
		} else {
			query.setParameter("serviceDefinitionId", statisDissaQuestionDateVO.getScreenService());
		}
		List<QuestionOptionAnswerDTO> list = new ArrayList<>();
		list = query.list();
		session.clear();
		return list;
	}

	/**
	 * 
	 */
	@Override
	public int statisticsDaoget_countService_byTime(StatisDissaServiceDateVO statisDissaServiceDateVO, String startTime,
			String endTime, jwcpxt_service_definition serviceDefinition) {
		Session session = getSession();
		String hql = "select count(*)"//
				+ " from "//
				+ " jwcpxt_dissatisfied_feedback dissatisfiedFeedback,"//
				+ " jwcpxt_answer_choice answerChoice,"//
				+ " jwcpxt_service_client serviceClient,"//
				+ " jwcpxt_service_instance serviceInstance,"//
				+ " jwcpxt_service_definition serviceDefinition,"//
				+ " jwcpxt_unit unit"//
				+ " where"//
				+ ""//
				+ " dissatisfiedFeedback.dissatisfied_feedback_answer_choice = answerChoice.jwcpxt_answer_choice_id"//
				+ " and answerChoice.answer_choice_client = serviceClient.jwcpxt_service_client_id"//
				+ " and serviceClient.service_client_service_instance = serviceInstance.jwcpxt_service_instance_id"//
				+ " and serviceInstance.service_instance_service_definition = serviceDefinition.jwcpxt_service_definition_id"//
				+ " and serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id"//
				+ ""//
				+ " and unit.jwcpxt_unit_id like :unitId "//
				+ " and serviceInstance.service_instance_date >= :startTime "//
				+ " and serviceInstance.service_instance_date < :endTime "//
				+ " and serviceDefinition.jwcpxt_service_definition_id = :serviceDefinitionId";
		Query query = session.createQuery(hql);
		if (statisDissaServiceDateVO.getScreenUnit().equals("")) {
			query.setParameter("unitId", "%%");
		} else {
			query.setParameter("unitId", statisDissaServiceDateVO.getScreenUnit());
		}
		query.setParameter("startTime", startTime + " 00:00:00");
		query.setParameter("endTime", endTime + " 23:59:59");
		query.setParameter("serviceDefinitionId", serviceDefinition.getJwcpxt_service_definition_id());
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
	 * 获取当前时间段里面所有的业务
	 */
	@Override
	public List<jwcpxt_service_definition> get_pushService_byTime(StatisDissaServiceDateVO statisDissaServiceDateVO) {
		Session session = getSession();
		String hql = "select distinct(serviceDefinition)"//
				+ " from "//
				+ " jwcpxt_dissatisfied_feedback dissatisfiedFeedback,"//
				+ " jwcpxt_answer_choice answerChoice,"//
				+ " jwcpxt_service_client serviceClient,"//
				+ " jwcpxt_service_instance serviceInstance,"//
				+ " jwcpxt_service_definition serviceDefinition,"//
				+ " jwcpxt_unit unit"//
				+ " where"//
				+ ""//
				+ " dissatisfiedFeedback.dissatisfied_feedback_answer_choice = answerChoice.jwcpxt_answer_choice_id"//
				+ " and answerChoice.answer_choice_client = serviceClient.jwcpxt_service_client_id"//
				+ " and serviceClient.service_client_service_instance = serviceInstance.jwcpxt_service_instance_id"//
				+ " and serviceInstance.service_instance_service_definition = serviceDefinition.jwcpxt_service_definition_id"//
				+ " and serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id"//
				+ ""//
				+ " and unit.jwcpxt_unit_id like :unitId ";//
		Query query = session.createQuery(hql);
		if (statisDissaServiceDateVO.getScreenUnit().equals("")) {
			query.setParameter("unitId", "%%");
		} else {
			query.setParameter("unitId", statisDissaServiceDateVO.getScreenUnit());
		}
		List<jwcpxt_service_definition> list = new ArrayList<>();
		list = query.list();
		session.clear();
		return list;
	}

	/**
	 * 获取时间段的总数量
	 */
	@Override
	public int get_totaolCount(StatisDissatiDateVO statisDissatiDateVO, String startTime, String endTime) {
		Session session = getSession();
		String hql = "select count(*) "//
				+ " from"//
				+ " jwcpxt_answer_choice answerChoice,"//
				+ " jwcpxt_service_client serviceClient,"//
				+ " jwcpxt_service_instance serviceInstance,"//
				+ " jwcpxt_unit unit"//
				+ " where"//
				+ " answerChoice.answer_choice_client = serviceClient.jwcpxt_service_client_id"//
				+ " and serviceClient.service_client_service_instance = serviceInstance.jwcpxt_service_instance_id"//
				+ " and serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id"//
				+ ""//
				+ " and unit.jwcpxt_unit_id like :unitId"//
				+ " and serviceInstance.service_instance_date >= :startTime"//
				+ " and serviceInstance.service_instance_date < :endTime ";//
		Query query = session.createQuery(hql);
		if (statisDissatiDateVO.getScreenUnit().equals("")) {
			query.setParameter("unitId", "%%");
		} else {
			query.setParameter("unitId", statisDissatiDateVO.getScreenUnit());
		}
		query.setParameter("startTime", startTime + " 00:00:00");
		query.setParameter("endTime", endTime + " 23:59:59");
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
	 * 推送数量
	 */
	@Override
	public int get_countOption_byTime(StatisDissatiDateVO statisDissatiDateVO, String startTime, String endTime,
			String option) {
		Session session = getSession();
		String hql = "select count(*) from "//
				+ "jwcpxt_dissatisfied_feedback dissatisfiedFeedback,"//
				+ "jwcpxt_answer_choice answerChoice,"//
				+ "jwcpxt_option _option,"//
				+ "jwcpxt_service_client serviceClient,"//
				+ "jwcpxt_service_instance serviceInstance,"//
				+ "jwcpxt_unit unit "//
				+ "where "//
				+ "dissatisfiedFeedback.dissatisfied_feedback_answer_choice = answerChoice.jwcpxt_answer_choice_id "//
				+ "and answerChoice.answer_choice_option = _option.jwcpxt_option_id "//
				+ "and answerChoice.answer_choice_client = serviceClient.jwcpxt_service_client_id "//
				+ "and serviceClient.service_client_service_instance = serviceInstance.jwcpxt_service_instance_id "//
				+ "and serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id "//
				+ ""//
				+ "and unit.jwcpxt_unit_id like :unitId "//
				+ "and serviceInstance.service_instance_date >= :startTime "//
				+ "and serviceInstance.service_instance_date < :endTime "//
				+ "and _option.option_describe like :option";
		Query query = session.createQuery(hql);
		if (statisDissatiDateVO.getScreenUnit().equals("")) {
			query.setParameter("unitId", "%%");
		} else {
			query.setParameter("unitId", statisDissatiDateVO.getScreenUnit());
		}
		query.setParameter("startTime", startTime + " 00:00:00");
		query.setParameter("endTime", endTime + " 23:59:59");
		query.setParameter("option", option);
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
	 * 获取对应的所有推送选项
	 */
	@Override
	public List<String> get_pushOption_byTime(StatisDissatiDateVO statisDissatiDateVO) {
		Session session = getSession();
		String hql = "select distinct(_option.option_describe) from "//
				+ "jwcpxt_dissatisfied_feedback dissatisfiedFeedback,"//
				+ "jwcpxt_answer_choice answerChoice,"//
				+ "jwcpxt_option _option,"//
				+ "jwcpxt_service_client serviceClient,"//
				+ "jwcpxt_service_instance serviceInstance,"//
				+ "jwcpxt_unit unit "//
				+ "where "//
				+ "dissatisfiedFeedback.dissatisfied_feedback_answer_choice = answerChoice.jwcpxt_answer_choice_id "//
				+ "and answerChoice.answer_choice_option = _option.jwcpxt_option_id "//
				+ "and answerChoice.answer_choice_client = serviceClient.jwcpxt_service_client_id "//
				+ "and serviceClient.service_client_service_instance = serviceInstance.jwcpxt_service_instance_id "//
				+ "and serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id "//
				+ ""//
				+ "and unit.jwcpxt_unit_id like :unitId ";
		Query query = session.createQuery(hql);
		if (statisDissatiDateVO.getScreenUnit().equals("")) {
			query.setParameter("unitId", "%%");
		} else {
			query.setParameter("unitId", statisDissatiDateVO.getScreenUnit());
		}
		List<String> list = new ArrayList<>();
		list = query.list();
		session.clear();
		return list;
	}

	@Override
	public int get_StatisticsDissatisfiedDateCount_byFatherUnit(String jwcpxt_service_definition_id,
			String jwcpxt_unit_id, String startTime, String endTime) {
		Session session = getSession();
		String hql = "select "//
				+ " count(answerChoice) "//
				+ " from "//
				+ " jwcpxt_answer_choice answerChoice , "//
				+ " jwcpxt_service_client serviceClient , "//
				+ " jwcpxt_service_instance serviceInstance , "//
				+ " jwcpxt_option option , "//
				+ " jwcpxt_unit unit "//
				//
				+ " where "//
				+ " answerChoice.answer_choice_client=serviceClient.jwcpxt_service_client_id "//
				+ " and serviceClient.service_client_service_instance=serviceInstance.jwcpxt_service_instance_id "//
				+ " and answerChoice.answer_choice_option=option.jwcpxt_option_id "//
				//
				+ " and (unit.unit_father=:unitID or unit.jwcpxt_unit_id = :unitID) "//
				+ " and serviceInstance.service_instance_belong_unit=unit.jwcpxt_unit_id "//
				+ " and serviceInstance.service_instance_service_definition=:serviceDefinitionID "//
				+ " and serviceInstance.service_instance_date >= :startTime "//
				+ " and serviceInstance.service_instance_date <= :endTime "//
				//
				+ " and option.option_push='1' "//
		;
		Query query = session.createQuery(hql);
		//
		query.setParameter("unitID", jwcpxt_unit_id);
		query.setParameter("serviceDefinitionID", jwcpxt_service_definition_id);
		query.setParameter("startTime", startTime + " 00:00:00");
		query.setParameter("endTime", endTime + " 23:59:59");
		//
		try {
			int count = ((Number) query.uniqueResult()).intValue();
			return count;
		} catch (ClassCastException e) {
			return 0;
		} finally {
			session.clear();
		}
	}

	@Override
	public int get_StatisticsDissatisfiedDateCount(String jwcpxt_service_definition_id, String jwcpxt_unit_id,
			String startTime, String endTime) {
		Session session = getSession();
		String hql = "select "//
				+ " count(answerChoice) "//
				+ " from "//
				+ " jwcpxt_answer_choice answerChoice , "//
				+ " jwcpxt_service_client serviceClient , "//
				+ " jwcpxt_service_instance serviceInstance , "//
				+ " jwcpxt_option option "//
				//
				+ " where "//
				+ " answerChoice.answer_choice_client=serviceClient.jwcpxt_service_client_id "//
				+ " and serviceClient.service_client_service_instance=serviceInstance.jwcpxt_service_instance_id "//
				+ " and answerChoice.answer_choice_option=option.jwcpxt_option_id "//
				//
				+ " and serviceInstance.service_instance_belong_unit=:unitID "//
				+ " and serviceInstance.service_instance_service_definition=:serviceDefinitionID "//
				+ " and serviceInstance.service_instance_date >= :startTime "//
				+ " and serviceInstance.service_instance_date <= :endTime "//
				//
				+ " and option.option_push='1' "//
		;
		Query query = session.createQuery(hql);
		//
		query.setParameter("unitID", jwcpxt_unit_id);
		query.setParameter("serviceDefinitionID", jwcpxt_service_definition_id);
		query.setParameter("startTime", startTime + " 00:00:00");
		query.setParameter("endTime", endTime + " 23:59:59");
		//
		try {
			int count = ((Number) query.uniqueResult()).intValue();
			return count;
		} catch (ClassCastException e) {
			return 0;
		} finally {
			session.clear();
		}
	}

	@Override
	public int get_dayNum_byServiceDefinitionIDAndDate_byFatherUnit(String serviceDefinitionID, String unitID,
			String day) {
		Session session = getSession();
		String hql = "select "//
				+ " count(answerChoice) "//
				+ " from "//
				+ " jwcpxt_answer_choice answerChoice , "//
				+ " jwcpxt_service_client serviceClient , "//
				+ " jwcpxt_service_instance serviceInstance , "//
				+ " jwcpxt_option _option, "//
				+ " jwcpxt_unit unit "//
				//
				+ " where "//
				+ " answerChoice.answer_choice_client=serviceClient.jwcpxt_service_client_id "//
				+ " and serviceClient.service_client_service_instance=serviceInstance.jwcpxt_service_instance_id "//
				+ " and answerChoice.answer_choice_option=_option.jwcpxt_option_id "//
				+ " and serviceInstance.service_instance_belong_unit=unit.jwcpxt_unit_id "//
				//
				+ " and (unit.unit_father=:unitID or unit.jwcpxt_unit_id=:unitID) "//
				+ " and serviceInstance.service_instance_service_definition=:serviceDefinitionID "//
				+ " and serviceInstance.service_instance_date like :day "//
				//
				+ " and _option.option_push='1' "//
		;
		Query query = session.createQuery(hql);
		//
		query.setParameter("unitID", unitID);
		query.setParameter("serviceDefinitionID", serviceDefinitionID);
		query.setParameter("day", day + "%");

		//
		try {
			int count = ((Number) query.uniqueResult()).intValue();
			return count;
		} catch (ClassCastException e) {
			return 0;
		} finally {
			session.clear();
		}
	}

	@Override
	public int get_dayNum_byServiceDefinitionIDAndDate(String serviceDefinitionID, String unitID, String day) {

		Session session = getSession();
		String hql = "select "//
				+ " count(answerChoice) "//
				+ " from "//
				+ " jwcpxt_answer_choice answerChoice , "//
				+ " jwcpxt_service_client serviceClient , "//
				+ " jwcpxt_service_instance serviceInstance , "//
				+ " jwcpxt_option _option "//
				//
				+ " where "//
				+ " answerChoice.answer_choice_client=serviceClient.jwcpxt_service_client_id "//
				+ " and serviceClient.service_client_service_instance=serviceInstance.jwcpxt_service_instance_id "//
				+ " and answerChoice.answer_choice_option=_option.jwcpxt_option_id "//
				//
				+ " and serviceInstance.service_instance_belong_unit=:unitID "//
				+ " and serviceInstance.service_instance_service_definition=:serviceDefinitionID "//
				+ " and serviceInstance.service_instance_date like :day "//
				//
				+ " and _option.option_push='1' "//
		;
		Query query = session.createQuery(hql);
		//
		query.setParameter("unitID", unitID);
		query.setParameter("serviceDefinitionID", serviceDefinitionID);
		query.setParameter("day", day + "%");
		//
		try {
			int count = ((Number) query.uniqueResult()).intValue();
			return count;
		} catch (ClassCastException e) {
			return 0;
		} finally {
			session.clear();
		}
	}

	@Override
	public double geteStatisticsGrade_byFatherUnit(ServiceGradeDTO serviceGradeDTO, String fatherUnitId,
			String searchTimeStart, String searchTimeEnd) {
		Session session = getSession();
		String hql = " select "//
				+ " ((count(distinct serviceClient)*100)  -  sum(_option.option_grade))  /  count(distinct serviceClient)  "//
				+ " from "//
				+ " jwcpxt_unit unit , "//
				+ " jwcpxt_service_instance serviceInstance , "//
				+ " jwcpxt_service_client serviceClient , "//
				+ " jwcpxt_answer_choice answerChoice , "//
				+ " jwcpxt_option _option "//
				//
				+ " where "
				+ " serviceClient.service_client_service_instance=serviceInstance.jwcpxt_service_instance_id"//
				+ " and answerChoice.answer_choice_client=serviceClient.jwcpxt_service_client_id"//
				+ " and answerChoice.answer_choice_option=_option.jwcpxt_option_id"//
				+ " and serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id "// 业务实例关联到三级单位
				//
				+ " and (unit.unit_father = :fatherUnitId or unit.jwcpxt_unit_id= :fatherUnitId)" // 二级单位ID等于传过来的单位ID
				+ " and serviceInstance.service_instance_service_definition = :serviceDefinitionID "//
				//
				+ " and serviceInstance.service_instance_date >= :searchTimeStart "//
				+ " and serviceInstance.service_instance_date <= :searchTimeEnd "//
		;
		Query query = session.createQuery(hql);
		query.setParameter("fatherUnitId", fatherUnitId);
		query.setParameter("serviceDefinitionID", serviceGradeDTO.getService_id());
		//
		if (searchTimeStart.equals("")) {
			query.setParameter("searchTimeStart", "0000-00-00");
		} else {
			query.setParameter("searchTimeStart", searchTimeStart + " 00:00:00");
		}
		if (searchTimeEnd.equals("")) {
			query.setParameter("searchTimeEnd", "9999-99-99");
		} else {
			query.setParameter("searchTimeEnd", searchTimeEnd + " 23:59:59");
		}

		//
		//
		try {
			double count = ((Number) query.uniqueResult()).intValue();
			return (count / 100) * serviceGradeDTO.getGrade();
		} catch (ClassCastException e) {
			return serviceGradeDTO.getGrade();
		} catch (NullPointerException e) {
			return serviceGradeDTO.getGrade();
		} finally {
			session.clear();
		}
	}

	@Override
	public double geteStatisticsGrade(ServiceGradeDTO serviceGradeDTO, String unitId, String searchTimeStart,
			String searchTimeEnd) {
		Session session = getSession();
		String hql = " select "//
				+ " ((count(distinct serviceClient)*100)  -  sum(_option.option_grade))  /  count(distinct serviceClient) "//
				+ " from "//
				+ " jwcpxt_service_instance serviceInstance , "//
				+ " jwcpxt_service_client serviceClient , "//
				+ " jwcpxt_answer_choice answerChoice , "//
				+ " jwcpxt_option _option "//
				//
				+ " where "
				+ " serviceClient.service_client_service_instance=serviceInstance.jwcpxt_service_instance_id"//
				+ " and answerChoice.answer_choice_client=serviceClient.jwcpxt_service_client_id"//
				+ " and answerChoice.answer_choice_option=_option.jwcpxt_option_id"//
				//
				+ " and serviceInstance.service_instance_belong_unit = :unitId "//
				+ " and serviceInstance.service_instance_service_definition = :serviceDefinitionID "//
				//
				+ " and serviceInstance.service_instance_date >= :searchTimeStart "//
				+ " and serviceInstance.service_instance_date <= :searchTimeEnd "//
		;
		Query query = session.createQuery(hql);
		query.setParameter("unitId", unitId);
		query.setParameter("serviceDefinitionID", serviceGradeDTO.getService_id());
		//
		query.setParameter("searchTimeStart", searchTimeStart + " 00:00:00");
		query.setParameter("searchTimeEnd", searchTimeEnd + " 23:59:59");
		//
		//

		try {
			double count = ((Number) query.uniqueResult()).intValue();
			return (count / 100) * serviceGradeDTO.getGrade();
		} catch (ClassCastException e) {
			return serviceGradeDTO.getGrade();
		} catch (NullPointerException e) {
			return serviceGradeDTO.getGrade();
		} finally {
			session.clear();
		}

		//

	}

}