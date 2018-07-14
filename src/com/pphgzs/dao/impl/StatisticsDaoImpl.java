package com.pphgzs.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pphgzs.dao.StatisticsDao;
import com.pphgzs.domain.DTO.ServiceGradeDTO;

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

	@Override
	public int geteStatisticsGrade_byFatherUnit(ServiceGradeDTO serviceGradeDTO, String fatherUnitId,
			String searchTimeStart, String searchTimeEnd) {
		Session session = getSession();
		String hql = " select "//
				+ " ((((count(distinct serviceClient)*100)  -  sum(_option.option_grade))  /  count(distinct serviceClient)) / 100) * :grade "//
				+ " from "//
				+ " jwcpxt_unit unit , "//
				+ " jwcpxt_unit fatherUnit , "//
				+ " jwcpxt_service_instance serviceInstance , "//
				+ " jwcpxt_service_client serviceClient , "//
				+ " jwcpxt_answer_choice answerChoice , "//
				+ " jwcpxt_option _option "//
				//
				+ " where "
				+ " serviceClient.service_client_service_instance=serviceInstance.jwcpxt_service_instance_id"//
				+ " and answerChoice.answer_choice_client=serviceClient.jwcpxt_service_client_id"//
				+ " and answerChoice.answer_choice_option=_option.jwcpxt_option_id"//
				+ " and unit.unit_father=fatherUnit.jwcpxt_unit_id"// 连接二三级单位
				+ " and serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id "// 业务实例关联到三级单位
				//
				+ " and fatherUnit.jwcpxt_unit_id = :fatherUnitId "// 二级单位ID等于传过来的单位ID
				+ " and serviceInstance.service_instance_service_definition = :serviceDefinitionID "//
				//
				+ " and serviceInstance.service_instance_date >= :searchTimeStart "//
				+ " and serviceInstance.service_instance_date <= :searchTimeEnd "//
		;
		System.out.println(hql);
		Query query = session.createQuery(hql);
		query.setParameter("fatherUnitId", fatherUnitId);
		query.setParameter("serviceDefinitionID", serviceGradeDTO.getService_id());
		//
		query.setParameter("searchTimeStart", searchTimeStart);
		query.setParameter("searchTimeEnd", searchTimeEnd);
		//
		query.setParameter("grade", serviceGradeDTO.getGrade());
		//
		try {
			int count = ((Number) query.uniqueResult()).intValue();
			return count;
		} catch (ClassCastException e) {
			System.err.println(e);
			return 0;
		} finally {
			session.clear();
		}
	}

	@Override
	public int geteStatisticsGrade(ServiceGradeDTO serviceGradeDTO, String unitId, String searchTimeStart,
			String searchTimeEnd) {
		Session session = getSession();
		String hql = " select "//
				+ " ((((count(distinct serviceClient)*100)  -  sum(_option.option_grade))  /  count(distinct serviceClient)) / 100) * :grade "//
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
		System.out.println(hql);
		Query query = session.createQuery(hql);
		query.setParameter("unitId", unitId);
		query.setParameter("serviceDefinitionID", serviceGradeDTO.getService_id());
		//
		query.setParameter("searchTimeStart", searchTimeStart);
		query.setParameter("searchTimeEnd", searchTimeEnd);
		//
		query.setParameter("grade", serviceGradeDTO.getGrade());
		//
		try {
			int count = ((Number) query.uniqueResult()).intValue();
			return count;
		} catch (ClassCastException e) {
			System.err.println(e);
			return 0;
		} finally {
			session.clear();
		}

		//

	}
}