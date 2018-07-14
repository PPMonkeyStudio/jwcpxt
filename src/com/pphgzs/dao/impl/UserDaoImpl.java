package com.pphgzs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pphgzs.dao.UserDao;
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

}
