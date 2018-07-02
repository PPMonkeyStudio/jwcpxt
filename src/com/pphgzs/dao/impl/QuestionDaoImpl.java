package com.pphgzs.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pphgzs.dao.QuestionDao;

@SuppressWarnings("unchecked")
public class QuestionDaoImpl implements QuestionDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

}
