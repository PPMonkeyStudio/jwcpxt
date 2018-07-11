package com.pphgzs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pphgzs.dao.UnitDao;
import com.pphgzs.domain.DO.jwcpxt_unit;

public class UnitDaoImpl implements UnitDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public List<jwcpxt_unit> list_unitDO_byOneAndTwo() {
		List<jwcpxt_unit> unit_List = new ArrayList<jwcpxt_unit>();

		Session session = getSession();
		String hql = "from jwcpxt_unit where unit_grade = 1 or unit_grade = 2 order by unit_grade asc";
		Query query = session.createQuery(hql);
		unit_List = query.list();
		session.clear();

		return unit_List;
	}

	@Override
	public List<jwcpxt_unit> list_unitDO_all() {

		List<jwcpxt_unit> unit_List = new ArrayList<jwcpxt_unit>();

		Session session = getSession();
		String hql = "from jwcpxt_unit";
		Query query = session.createQuery(hql);
		unit_List = query.list();
		session.clear();

		return unit_List;
	}

	@Override
	public int get_unitTotalRecords() {
		Session session = getSession();
		String hql = "select count(*) from jwcpxt_unit";
		Query query = session.createQuery(hql);
		int count = ((Number) query.uniqueResult()).intValue();
		session.clear();
		return count;
	}

	@Override
	public boolean save_unit(jwcpxt_unit unit) {
		Session session = getSession();
		session.save(unit);
		session.flush();
		return true;
	}

	@Override
	public boolean delete_unit(jwcpxt_unit unit) {
		Session session = getSession();
		String hql = "delete from jwcpxt_unit where jwcpxt_unit_id='" + unit.getJwcpxt_unit_id() + "'";
		Query query = session.createQuery(hql);
		query.executeUpdate();
		session.flush();
		return true;
	}

	@Override
	public jwcpxt_unit get_unitDO_byID(String unitID) {
		Session session = getSession();
		String hql = "from jwcpxt_unit where jwcpxt_unit_id='" + unitID + "'";
		Query query = session.createQuery(hql);
		jwcpxt_unit newUnit = (jwcpxt_unit) query.uniqueResult();
		session.clear();
		return newUnit;
	}

	@Override
	public jwcpxt_unit get_unit_byNameOrAccount(String unit_name, String unit_account) {
		Session session = getSession();
		String hql = "from jwcpxt_unit where unit_name=:unit_name or unit_account=:unit_account";
		//
		Query query = session.createQuery(hql);
		query.setParameter("unit_name", unit_name);
		query.setParameter("unit_account", unit_account);
		//
		jwcpxt_unit newUnit = (jwcpxt_unit) query.uniqueResult();
		session.clear();
		return newUnit;
	}

	@Override
	public boolean update_unit(jwcpxt_unit unit) {
		Session session = getSession();
		session.update(unit);
		session.flush();
		return true;
	}

}
