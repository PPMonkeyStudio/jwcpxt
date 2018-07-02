package com.pphgzs.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pphgzs.dao.ServiceDao;
import com.pphgzs.domain.DTO.ServiceDefinitionDTO;
import com.pphgzs.domain.VO.ServiceDefinitionVO;

public class ServiceDaoImpl implements ServiceDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public int get_serviceDefinitionTotalCount_byServiceDefinitionVO(ServiceDefinitionVO serviceDefinitionVO) {
		Session session = getSession();
		String hql = "select count(*) from jwcpxt_service_definition serviceDefinition "
				+ " where serviceDefinition.service_definition_describe like :screenSearch and serviceDefinition.service_definition_unit like :screenUnit ";
		Query query = session.createQuery(hql);
		//
		if (serviceDefinitionVO.getScreenSearch().equals("")) {
			query.setParameter("screenSearch", "%%");
		} else {
			query.setParameter("screenSearch", "%" + serviceDefinitionVO.getScreenSearch() + "%");
		}
		if (serviceDefinitionVO.getScreenUnit().equals("")) {
			query.setParameter("screenUnit", "%%");
		} else {
			query.setParameter("screenUnit", "%" + serviceDefinitionVO.getScreenUnit() + "%");
		}
		//
		int count = ((Number) query.uniqueResult()).intValue();
		//
		session.clear();
		return count;
	}

	@Override
	public List<ServiceDefinitionDTO> list_serviceDefinitionDTO_byUserVO(ServiceDefinitionVO serviceDefinitionVO) {
		Session session = getSession();
		String hql = "select new com.pphgzs.domain.DTO.ServiceDefinitionDTO(serviceDefinition,unit)  from jwcpxt_service_definition serviceDefinition , jwcpxt_unit unit"
				+ " where serviceDefinition.service_definition_unit=unit.jwcpxt_unit_id and serviceDefinition.service_definition_describe like :screenSearch and serviceDefinition.service_definition_unit like :screenUnit "
				+ " order by service_definition_unit";
		Query query = session.createQuery(hql);
		//
		if (serviceDefinitionVO.getScreenSearch().equals("")) {
			query.setParameter("screenSearch", "%%");
		} else {
			query.setParameter("screenSearch", "%" + serviceDefinitionVO.getScreenSearch() + "%");
		}
		if (serviceDefinitionVO.getScreenUnit().endsWith("")) {
			query.setParameter("screenUnit", "%%");
		} else {
			query.setParameter("screenUnit", "%" + serviceDefinitionVO.getScreenUnit() + "%");
		}
		query.setFirstResult((serviceDefinitionVO.getCurrPage() - 1) * serviceDefinitionVO.getPageSize());
		query.setMaxResults(serviceDefinitionVO.getPageSize());
		//
		List<ServiceDefinitionDTO> list = null;
		list = query.list();
		//
		session.clear();
		return list;
	}
}
