package com.pphgzs.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pphgzs.dao.ServiceDao;
import com.pphgzs.domain.DO.jwcpxt_grab_journal;
import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_service_grab;
import com.pphgzs.domain.DO.jwcpxt_service_instance;
import com.pphgzs.domain.DTO.ServiceDefinitionDTO;
import com.pphgzs.domain.VO.ServiceDefinitionVO;
import com.pphgzs.domain.VO.ServiceInstanceVO;
import com.pphgzs.util.TimeUtil;

public class ServiceDaoImpl implements ServiceDao {
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
	 * 业务定义列表
	 */
	@Override
	public List<jwcpxt_service_definition> list_serviceDefinitionDO_all() {
		Session session = getSession();
		String hql = "from jwcpxt_service_definition ";
		Query query = session.createQuery(hql);
		//
		List<jwcpxt_service_definition> list = query.list();
		session.clear();
		System.out.println(list);
		return list;
	}

	@Override
	public int get_serviceInstanceTotalCount_byServiceInstanceVO(ServiceInstanceVO serviceInstanceVO) {
		Session session = getSession();
		String hql = "select count(*) from jwcpxt_service_instance "
				+ " where service_instance_judge like :screenServiceInstanceJudge "
				+ " and service_instance_service_definition like :screenServiceDefinition"
				+ " and service_instance_date >= :screenServiceInstanceStartDate "
				+ " and service_instance_date <= :screenServiceInstanceStopDate ";
		Query query = session.createQuery(hql);
		//
		if (serviceInstanceVO.getScreenServiceInstanceJudge().equals("")) {
			query.setParameter("screenServiceInstanceJudge", "%%");
		} else {
			query.setParameter("screenServiceInstanceJudge",
					"%" + serviceInstanceVO.getScreenServiceInstanceJudge() + "%");
		}
		if (serviceInstanceVO.getScreenServiceDefinition().equals("")) {
			query.setParameter("screenServiceDefinition", "%%");
		} else {
			query.setParameter("screenServiceDefinition", "%" + serviceInstanceVO.getScreenServiceDefinition() + "%");
		}
		if (serviceInstanceVO.getScreenServiceInstanceStartDate().equals("")) {
			query.setParameter("screenServiceInstanceStartDate", "0000-00-00");
		} else {
			query.setParameter("screenServiceInstanceStartDate", serviceInstanceVO.getScreenServiceInstanceStartDate());
		}
		if (serviceInstanceVO.getScreenServiceInstanceStopDate().equals("")) {
			query.setParameter("screenServiceInstanceStopDate", "9999-99-99");
		} else {
			query.setParameter("screenServiceInstanceStopDate", serviceInstanceVO.getScreenServiceInstanceStopDate());
		}
		//
		int count = ((Number) query.uniqueResult()).intValue();
		//
		session.clear();
		return count;
	}

	@Override
	public int get_serviceInstanceDistributionCount_byDateAndServiceDefinitionID(String date,
			String serviceDefinitionID) {
		Session session = getSession();
		String hql = "select count(*) from jwcpxt_service_instance "
				+ " where service_instance_service_definition = :serviceDefinitionID "
				+ " and service_instance_gmt_create >= :date " + " and service_instance_judge != 'none' ";
		Query query = session.createQuery(hql);
		//
		query.setParameter("serviceDefinitionID", serviceDefinitionID);
		query.setParameter("date", date);
		//
		int count = ((Number) query.uniqueResult()).intValue();
		//
		session.clear();
		return count;
	}

	@Override
	public int get_serviceInstanceTotalCount_byDateAndServiceDefinitionID(String date, String serviceDefinitionID) {
		Session session = getSession();
		String hql = "select count(*) from jwcpxt_service_instance "
				+ " where service_instance_service_definition = :serviceDefinitionID "
				+ " and service_instance_gmt_create >= :date ";
		Query query = session.createQuery(hql);
		//
		query.setParameter("serviceDefinitionID", serviceDefinitionID);
		query.setParameter("date", date);
		//
		int count = ((Number) query.uniqueResult()).intValue();
		//
		session.clear();
		return count;
	}

	@Override
	public int get_serviceInstanceTotalCount_byToday() {
		Session session = getSession();
		String hql = "select count(*) from jwcpxt_service_instance  " + " where  service_instance_gmt_create >= :date";
		Query query = session.createQuery(hql);
		//
		query.setParameter("date", TimeUtil.getStringDay());
		//
		int count = ((Number) query.uniqueResult()).intValue();
		//
		session.clear();
		return count;
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
	public List<jwcpxt_service_client> list_client_byServiceInstanceID(String serviceInstanceID) {

		Session session = getSession();
		String hql = "from jwcpxt_service_client serviceClient where serviceClient.service_client_service_instance=:serviceInstanceID";
		Query query = session.createQuery(hql);
		//
		query.setParameter("serviceInstanceID", serviceInstanceID);
		//
		List<jwcpxt_service_client> list = query.list();
		session.clear();
		return list;
	}

	@Override
	public List<jwcpxt_service_instance> list_serviceInstance_byNoJudgeAndRandomAndNumAndServiceDefinitionIDAndDate(
			int num, String serviceDefinitionID, String date) {
		Session session = getSession();
		String hql = " from jwcpxt_service_instance "//
				+ " where service_instance_judge = 'none' "//
				+ " and service_instance_service_definition = :serviceInstanceID "//
				+ " and service_instance_gmt_create >= :date "//
				+ " order by rand() ";
		Query query = session.createQuery(hql);
		//
		query.setParameter("serviceInstanceID", serviceDefinitionID);
		query.setParameter("date", date);
		query.setMaxResults(num);
		//
		List<jwcpxt_service_instance> list = query.list();
		session.clear();
		return list;
	}

	@Override
	public List<jwcpxt_service_client> list_serviceClient_byServiceInstanceID(String serviceInstanceID) {
		Session session = getSession();
		String hql = "from jwcpxt_service_client serviceClient where serviceClient.service_client_service_instance = :serviceInstanceID";
		Query query = session.createQuery(hql);
		//
		query.setParameter("serviceInstanceID", serviceInstanceID);
		//
		List<jwcpxt_service_client> list = query.list();
		session.clear();
		return list;
	}

	@Override
	public List<jwcpxt_service_instance> list_serviceInstance_byServiceDefinitionID(String serviceDefinitionID) {
		Session session = getSession();
		String hql = "from jwcpxt_service_instance serviceInstance where serviceInstance.service_instance_service_definition=:serviceDefinitionID";
		Query query = session.createQuery(hql);
		//
		query.setParameter("serviceDefinitionID", serviceDefinitionID);
		//
		List<jwcpxt_service_instance> list = query.list();
		session.clear();
		return list;
	}

	@Override
	public ServiceDefinitionDTO get_serviceDefinitionDTO_byServiceDefinitionID(String serviceDefinitionID) {
		Session session = getSession();
		String hql = "select new com.pphgzs.domain.DTO.ServiceDefinitionDTO(serviceDefinition) from jwcpxt_service_definition serviceDefinition"
				+ " where serviceDefinition.jwcpxt_service_definition_id = :serviceDefinitionID  ";
		Query query = session.createQuery(hql);
		//
		query.setParameter("serviceDefinitionID", serviceDefinitionID);
		//
		ServiceDefinitionDTO serviceDefinitionDTO = (ServiceDefinitionDTO) query.uniqueResult();
		session.clear();
		return serviceDefinitionDTO;
	}

	@Override
	public List<jwcpxt_service_instance> list_serviceInstance_byServiceInstanceVO(ServiceInstanceVO serviceInstanceVO) {
		Session session = getSession();
		String hql = "from jwcpxt_service_instance " + " where service_instance_judge like :screenServiceInstanceJudge "
				+ " and service_instance_service_definition like :screenServiceDefinition"
				+ " and service_instance_date >= :screenServiceInstanceStartDate "
				+ " and service_instance_date <= :screenServiceInstanceStopDate "
				+ " order by service_instance_gmt_create";
		Query query = session.createQuery(hql);
		//
		if (serviceInstanceVO.getScreenServiceInstanceJudge().equals("")) {
			query.setParameter("screenServiceInstanceJudge", "%%");
		} else {
			query.setParameter("screenServiceInstanceJudge",
					"%" + serviceInstanceVO.getScreenServiceInstanceJudge() + "%");
		}
		if (serviceInstanceVO.getScreenServiceDefinition().equals("")) {
			query.setParameter("screenServiceDefinition", "%%");
		} else {
			query.setParameter("screenServiceDefinition", "%" + serviceInstanceVO.getScreenServiceDefinition() + "%");
		}
		if (serviceInstanceVO.getScreenServiceInstanceStartDate().equals("")) {
			query.setParameter("screenServiceInstanceStartDate", "0000-00-00");
		} else {
			query.setParameter("screenServiceInstanceStartDate", serviceInstanceVO.getScreenServiceInstanceStartDate());
		}
		if (serviceInstanceVO.getScreenServiceInstanceStopDate().equals("")) {
			query.setParameter("screenServiceInstanceStopDate", "9999-99-99");
		} else {
			query.setParameter("screenServiceInstanceStopDate", serviceInstanceVO.getScreenServiceInstanceStopDate());
		}
		query.setFirstResult((serviceInstanceVO.getCurrPage() - 1) * serviceInstanceVO.getPageSize());
		query.setMaxResults(serviceInstanceVO.getPageSize());
		//
		List<jwcpxt_service_instance> list = query.list();
		session.clear();
		return list;
	}

	@Override
	public List<ServiceDefinitionDTO> list_serviceDefinitionDTO_byServiceDefinitionVO(
			ServiceDefinitionVO serviceDefinitionVO) {
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
		if (serviceDefinitionVO.getScreenUnit().equals("")) {
			query.setParameter("screenUnit", "%%");
		} else {
			query.setParameter("screenUnit", "%" + serviceDefinitionVO.getScreenUnit() + "%");
		}
		query.setFirstResult((serviceDefinitionVO.getCurrPage() - 1) * serviceDefinitionVO.getPageSize());
		query.setMaxResults(serviceDefinitionVO.getPageSize());
		//
		List<ServiceDefinitionDTO> list = null;
		list = query.list();
		session.clear();
		return list;
	}

	@Override
	public boolean ifExist_serviceDefinition_byServiceDefinitionDescribe(String service_definition_describe) {
		Session session = getSession();

		String hql = "from jwcpxt_service_definition where service_definition_describe=:definitionDescribe";
		Query query = session.createQuery(hql);
		//
		query.setParameter("definitionDescribe", service_definition_describe);
		//
		jwcpxt_service_definition serviceDefinition = (jwcpxt_service_definition) query.uniqueResult();
		session.clear();
		if (serviceDefinition != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public jwcpxt_grab_journal get_grabJournal_byServiceDefinitionIDAndDate(String serviceDefinitionID, String date) {
		Session session = getSession();

		String hql = "from jwcpxt_grab_journal"//
				+ " where grab_journal_service_definition=:serviceDefinitionID "//
				+ " and grab_journal_gmt_create >= :date";
		Query query = session.createQuery(hql);
		//
		query.setParameter("serviceDefinitionID", serviceDefinitionID);
		query.setParameter("date", date);
		//
		jwcpxt_grab_journal grabJournal = (jwcpxt_grab_journal) query.uniqueResult();
		session.clear();
		return grabJournal;
	}

	@Override
	public jwcpxt_service_definition get_serviceDefinition_byServiceDefinitionID(String serviceDefinitionID) {
		Session session = getSession();
		String hql = "from jwcpxt_service_definition where jwcpxt_service_definition_id=:serviceDefinitionID ";
		//
		Query query = session.createQuery(hql);
		//
		query.setParameter("serviceDefinitionID", serviceDefinitionID);
		//
		jwcpxt_service_definition serviceDefinition = (jwcpxt_service_definition) query.uniqueResult();
		session.clear();
		return serviceDefinition;
	}

	@Override
	public jwcpxt_service_grab get_serviceGrabDO_byServiceDefinitionID(String serviceDefinitionID) {
		Session session = getSession();
		String hql = "from jwcpxt_service_grab where service_grab_service_definition=:serviceDefinitionID ";
		//
		Query query = session.createQuery(hql);
		//
		query.setParameter("serviceDefinitionID", serviceDefinitionID);
		//
		jwcpxt_service_grab service_grab = (jwcpxt_service_grab) query.uniqueResult();
		session.clear();
		return service_grab;
	}

	@Override
	public jwcpxt_service_grab get_serviceGrab_byServiceGrabID(String serviceGrabId) {
		Session session = getSession();
		String hql = "from jwcpxt_service_grab where jwcpxt_service_grab_id=:serviceGrabId ";
		//
		Query query = session.createQuery(hql);
		//
		query.setParameter("serviceGrabId", serviceGrabId);
		//
		jwcpxt_service_grab serviceInstance = (jwcpxt_service_grab) query.uniqueResult();
		session.clear();
		return serviceInstance;
	}

	@Override
	public jwcpxt_service_instance get_serviceInstance_byServiceInstanceID(String serviceInstanceID) {
		Session session = getSession();
		String hql = "from jwcpxt_service_instance where jwcpxt_service_instance_id=:serviceInstanceID ";
		//
		Query query = session.createQuery(hql);
		//
		query.setParameter("serviceInstanceID", serviceInstanceID);
		//
		jwcpxt_service_instance serviceInstance = (jwcpxt_service_instance) query.uniqueResult();
		session.clear();
		return serviceInstance;
	}

	@Override
	public boolean update_serviceInstance(jwcpxt_service_instance serviceInstance) {
		Session session = getSession();
		session.update(serviceInstance);
		session.flush();
		return true;
	}

	@Override
	public boolean update_serviceDefinition(jwcpxt_service_definition serviceDefinitionOld) {
		Session session = getSession();
		session.update(serviceDefinitionOld);
		session.flush();
		return true;
	}

	@Override
	public boolean update_serviceGrab(jwcpxt_service_grab serviceGrabOld) {
		Session session = getSession();
		session.update(serviceGrabOld);
		session.flush();
		return true;
	}

	@Override
	public boolean save_grabJournal(jwcpxt_grab_journal grabJournal) {
		Session session = getSession();
		session.save(grabJournal);
		session.flush();
		return true;
	}

	@Override
	public boolean save_serviceGrab(jwcpxt_service_grab serviceGrab) {
		Session session = getSession();
		session.save(serviceGrab);
		session.flush();
		return true;

	}

	@Override
	public boolean save_serviceDefinition(jwcpxt_service_definition serviceDefinition) {
		Session session = getSession();
		session.save(serviceDefinition);
		session.flush();
		return true;
	}
	/*
	 * 
	 */

}
