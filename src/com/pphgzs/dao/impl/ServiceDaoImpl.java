package com.pphgzs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pphgzs.dao.ServiceDao;
import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_service_distribution;
import com.pphgzs.domain.DO.jwcpxt_service_instance;

public class ServiceDaoImpl implements ServiceDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public List<jwcpxt_service_definition> list_serviceDefinition_all() {
		List<jwcpxt_service_definition> serviceDefinitionList = new ArrayList<jwcpxt_service_definition>();

		Session session = getSession();
		String hql = "from jwcpxt_service_definition";
		Query query = session.createQuery(hql);
		serviceDefinitionList = query.list();
		session.clear();

		return serviceDefinitionList;
	}

	@Override
	public int get_serviceDefinitionTotalRecords() {
		Session session = getSession();
		String hql = "select count(*) from jwcpxt_service_definition";
		Query query = session.createQuery(hql);
		int count = ((Number) query.uniqueResult()).intValue();
		session.clear();
		return count;
	}

	@Override
	public int get_serviceInstanceTotalRecords() {
		Session session = getSession();
		String hql = "select count(*) from jwcpxt_service_instance";
		Query query = session.createQuery(hql);
		int count = ((Number) query.uniqueResult()).intValue();
		session.clear();
		return count;
	}

	@Override
	public int get_serviceDistributionTotalRecords() {
		Session session = getSession();
		String hql = "select count(*) from jwcpxt_service_distribution";
		Query query = session.createQuery(hql);
		int count = ((Number) query.uniqueResult()).intValue();
		session.clear();
		return count;
	}

	@Override
	public List<jwcpxt_service_instance> list_serviceInstance_all() {
		List<jwcpxt_service_instance> serviceInstanceList = new ArrayList<jwcpxt_service_instance>();

		Session session = getSession();
		String hql = "from jwcpxt_service_instance";
		Query query = session.createQuery(hql);
		serviceInstanceList = query.list();
		session.clear();

		return serviceInstanceList;
	}

	@Override
	public jwcpxt_service_definition get_serviceDefinition_byID(String serviceDefinitionID) {
		Session session = getSession();
		String hql = "from jwcpxt_service_definition where jwcpxt_service_definition_id='" + serviceDefinitionID + "'";
		Query query = session.createQuery(hql);
		jwcpxt_service_definition serviceDefinition = (jwcpxt_service_definition) query.uniqueResult();
		session.clear();
		return serviceDefinition;
	}

	@Override
	public jwcpxt_service_instance get_serviceInstance_byID(String serviceInstanceID) {
		Session session = getSession();
		String hql = "from jwcpxt_service_instance where jwcpxt_service_instance_id='" + serviceInstanceID + "'";
		Query query = session.createQuery(hql);
		jwcpxt_service_instance serviceInstance = (jwcpxt_service_instance) query.uniqueResult();
		session.clear();
		return serviceInstance;
	}

	@Override
	public jwcpxt_service_distribution get_serviceDistribution_byID(String serviceDistributionID) {
		Session session = getSession();
		String hql = "from jwcpxt_service_distribution where jwcpxt_service_distribution_id='" + serviceDistributionID
				+ "'";
		Query query = session.createQuery(hql);
		jwcpxt_service_distribution serviceDistribution = (jwcpxt_service_distribution) query.uniqueResult();
		session.clear();
		return serviceDistribution;
	}

	@Override
	public List<jwcpxt_service_client> list_serviceClient_byInstance(String instanceID) {
		List<jwcpxt_service_client> serviceClientList = new ArrayList<jwcpxt_service_client>();

		Session session = getSession();
		String hql = "from jwcpxt_service_client where service_client_service_instance='" + instanceID + "'";
		Query query = session.createQuery(hql);
		serviceClientList = query.list();
		session.clear();
		return serviceClientList;
	}

	@Override
	public List<jwcpxt_service_distribution> list_serviceDistribution_all() {
		List<jwcpxt_service_distribution> serviceDistributionList = new ArrayList<jwcpxt_service_distribution>();
		Session session = getSession();
		String hql = "from jwcpxt_service_distribution ";
		Query query = session.createQuery(hql);
		serviceDistributionList = query.list();
		session.clear();
		return serviceDistributionList;
	}

	@Override
	public boolean add_serviceDefinition(jwcpxt_service_definition serviceDefinition) {
		Session session = getSession();
		session.save(serviceDefinition);
		session.flush();
		return true;
	}

}
