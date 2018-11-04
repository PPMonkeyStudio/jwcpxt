package com.pphgzs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pphgzs.dao.UserDao;
import com.pphgzs.domain.DO.jwcpxt_entry_exit;
import com.pphgzs.domain.DO.jwcpxt_user;

public class UserDaoImpl implements UserDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public List<jwcpxt_user> list_user_all() {
		List<jwcpxt_user> userList = new ArrayList<jwcpxt_user>();

		Session session = getSession();
		String hql = "from jwcpxt_user";
		Query query = session.createQuery(hql);
		userList = query.list();
		session.clear();

		return userList;
	}

	@Override
	public jwcpxt_user get_user_byAccount(String user_account) {
		Session session = getSession();
		String hql = "from jwcpxt_user where user_account=:user_account";
		//
		Query query = session.createQuery(hql);
		query.setParameter("user_account", user_account);
		//
		jwcpxt_user user = (jwcpxt_user) query.uniqueResult();
		session.clear();
		return user;
	}

	@Override
	public jwcpxt_user get_userDO_byUserID(String userID) {
		Session session = getSession();
		String hql = "from jwcpxt_user where jwcpxt_user_id=:userID";
		Query query = session.createQuery(hql);
		query.setParameter("userID", userID);
		jwcpxt_user user = (jwcpxt_user) query.uniqueResult();
		session.clear();
		return user;
	}

	@Override
	public jwcpxt_user get_userDO_byRandomAndTypeCP() {
		Session session = getSession();
		String hql = "from "//
				+ " jwcpxt_user "//
				+ " where "//
				+ " user_state!=2 "//
				+ " and user_type=1"//
				+ " and user_account not in('1000009','1000008')"//
				+ " order by rand()";
		//
		Query query = session.createQuery(hql);
		query.setMaxResults(1);
		//
		List<jwcpxt_user> userList = query.list();
		session.clear();
		return userList.get(0);
	}

	@Override
	public boolean save_user(jwcpxt_user user) {
		Session session = getSession();
		session.save(user);
		session.flush();
		return true;
	}

	@Override
	public boolean update_user(jwcpxt_user user) {
		Session session = getSession();
		session.update(user);
		session.flush();
		return true;

	}

	@Override
	public String getUnitCode_ByUnitName(String stringCellValue) {
		Session session = getSession();
		String hql = " select unit.unit_num from "//
				+ " jwcpxt_unit unit"//
				+ " where "//
				+ " unit.unit_name = :name ";
		Query query = session.createQuery(hql);
		query.setParameter("name", stringCellValue);
		String num = (String) query.uniqueResult();
		session.clear();
		return num;
	}

	@Override
	public boolean saveObject(jwcpxt_entry_exit entry_exit) {
		try {
			Session session = getSession();
			session.save(entry_exit);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean verificationEntryExit(jwcpxt_entry_exit entry_exit) {
		boolean flag = true;
		Session session = getSession();
		String hql = " select entry_exit from "//
				+ " jwcpxt_entry_exit entry_exit "//
				+ " where "//
				+ " entry_exit.entry_exit_client_name = :name "//
				+ " and entry_exit.entry_exit_client_sex = :sex "//
				+ " and entry_exit.entry_exit_client_phone = :phone "//
				+ " and entry_exit.entry_exit_client_data = :data " //
				+ " and entry_exit.entry_exit_client_unit = :unit "//
				+ " and entry_exit.entry_exit_client_type = :type "//
				+ " and entry_exit.entry_exit_client_id_type = :id_type "//
				+ " and entry_exit.entry_exit_client_is_distribute = :distribute ";//
		Query query = session.createQuery(hql);
		query.setParameter("name", entry_exit.getEntry_exit_client_name());
		query.setParameter("sex", entry_exit.getEntry_exit_client_sex());
		query.setParameter("phone", entry_exit.getEntry_exit_client_phone());
		query.setParameter("data", entry_exit.getEntry_exit_client_data());
		query.setParameter("unit", entry_exit.getEntry_exit_client_unit());
		query.setParameter("type", entry_exit.getEntry_exit_client_type());
		query.setParameter("id_type", entry_exit.getEntry_exit_client_id_type());
		query.setParameter("distribute", entry_exit.getEntry_exit_client_is_distribute());
		List<jwcpxt_entry_exit> list = query.list();
		if (list.size() > 0) {
			flag = false;
		}
		session.clear();
		return flag;
	}

}
