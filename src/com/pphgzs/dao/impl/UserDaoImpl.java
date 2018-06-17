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
		List<jwcpxt_user> user_List = new ArrayList<jwcpxt_user>();

		Session session = getSession();
		String hql = "from jwcpxt_user";
		Query query = session.createQuery(hql);
		user_List = query.list();
		session.clear();

		return user_List;
	}

	@Override
	public int get_userTotalRecords_all() {
		Session session = getSession();
		String hql = "select count(*) from jwcpxt_user";
		Query query = session.createQuery(hql);
		int count = ((Number) query.uniqueResult()).intValue();
		session.clear();
		return count;
	}

	@Override
	public boolean save_user(jwcpxt_user user) {
		Session session = getSession();
		session.save(user);
		session.flush();
		return true;
	}

	@Override
	public boolean delete_user(jwcpxt_user user) {
		Session session = getSession();
		String hql = "delete from jwcpxt_user where jwcpxt_user_id='" + user.getJwcpxt_user_id() + "'";
		Query query = session.createQuery(hql);
		query.executeUpdate();
		session.flush();
		return true;
	}

	@Override
	public jwcpxt_user get_user_byUserID(String userID) {
		Session session = getSession();
		String hql = "from jwcpxt_user where jwcpxt_user_id='" + userID + "'";
		Query query = session.createQuery(hql);
		jwcpxt_user newUser = (jwcpxt_user) query.uniqueResult();
		session.clear();
		return newUser;
	}

	@Override
	public boolean ifExist_user_byUserAccount(String account) {
		Session session = getSession();

		String hql = "from jwcpxt_user where user_account='" + account + "'";
		Query query = session.createQuery(hql);
		jwcpxt_user newUser = (jwcpxt_user) query.uniqueResult();
		session.clear();
		if (newUser != null) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean update_user(jwcpxt_user user) {
		Session session = getSession();
		session.update(user);
		session.flush();
		return true;

	}

}
