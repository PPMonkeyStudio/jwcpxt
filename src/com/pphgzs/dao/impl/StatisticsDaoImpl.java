package com.pphgzs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pphgzs.dao.StatisticsDao;
import com.pphgzs.domain.DTO.ServiceGradeDTO;
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
				+ "dissatisfiedFeedback.dissatisfied_feedback_answer_choice = answerChoice.answer_choice_option "//
				+ "and answerChoice.answer_choice_option = _option.jwcpxt_option_id "//
				+ "and answerChoice.answer_choice_client = serviceClient.jwcpxt_service_client_id "//
				+ "and serviceClient.service_client_service_instance = serviceInstance.jwcpxt_service_instance_id "//
				+ "and serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id "//
				+ ""//
				+ "and unit.jwcpxt_unit_id like :unitId "//
				+ "and serviceInstance.service_instance_date >= :startTime "//
				+ "and serviceInstance.service_instance_date < :endTime "//
				+ "and _option.option_describe like :option";
		System.out.println("hql:" + hql);
		Query query = session.createQuery(hql);
		if (statisDissatiDateVO.getScreenUnit().equals("")) {
			query.setParameter("unitId", "%%");
		} else {
			query.setParameter("unitId", statisDissatiDateVO.getScreenUnit());
		}
		query.setParameter("startTime", startTime);
		query.setParameter("endTime", endTime);
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
	public List<String> get_pushOption_byTime(StatisDissatiDateVO statisDissatiDateVO, String startTime,
			String endTime) {
		Session session = getSession();
		String hql = "select distinct(_option.option_describe) from "//
				+ "jwcpxt_dissatisfied_feedback dissatisfiedFeedback,"//
				+ "jwcpxt_answer_choice answerChoice,"//
				+ "jwcpxt_option _option,"//
				+ "jwcpxt_service_client serviceClient,"//
				+ "jwcpxt_service_instance serviceInstance,"//
				+ "jwcpxt_unit unit "//
				+ "where "//
				+ "dissatisfiedFeedback.dissatisfied_feedback_answer_choice = answerChoice.answer_choice_option "//
				+ "and answerChoice.answer_choice_option = _option.jwcpxt_option_id "//
				+ "and answerChoice.answer_choice_client = serviceClient.jwcpxt_service_client_id "//
				+ "and serviceClient.service_client_service_instance = serviceInstance.jwcpxt_service_instance_id "//
				+ "and serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id "//
				+ ""//
				+ "and unit.jwcpxt_unit_id like :unitId "//
				+ "and serviceInstance.service_instance_date >= :startTime "//
				+ "and serviceInstance.service_instance_date < :endTime ";
		System.out.println("hql:" + hql);
		Query query = session.createQuery(hql);
		if (statisDissatiDateVO.getScreenUnit().equals("")) {
			query.setParameter("unitId", "%%");
		} else {
			query.setParameter("unitId", statisDissatiDateVO.getScreenUnit());
		}
		query.setParameter("startTime", startTime);
		query.setParameter("endTime", endTime);
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
		query.setParameter("startTime", startTime);
		query.setParameter("endTime", endTime);
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
		query.setParameter("startTime", startTime);
		query.setParameter("endTime", endTime);
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
		query.setParameter("searchTimeStart", searchTimeStart);
		query.setParameter("searchTimeEnd", searchTimeEnd);
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
		query.setParameter("searchTimeStart", searchTimeStart);
		query.setParameter("searchTimeEnd", searchTimeEnd);
		//
		//

		try {
			// System.out.println("shuli:" + (query.uniqueResult()));
			double count = ((Number) query.uniqueResult()).intValue();
			// System.out.println("count:" + count);
			// System.out.println("unitId:" + unitId + ".grade:" + count *
			// serviceGradeDTO.getGrade());
			return (count / 100) * serviceGradeDTO.getGrade();
		} catch (ClassCastException e) {
			// System.out.println("Class");
			return serviceGradeDTO.getGrade();
		} catch (NullPointerException e) {
			// System.out.println("Null");
			return serviceGradeDTO.getGrade();
		} finally {
			session.clear();
		}

		//

	}

}