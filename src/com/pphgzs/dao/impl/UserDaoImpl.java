package com.pphgzs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pphgzs.dao.UserDao;
import com.pphgzs.domain.DO.jwcpxt_user;
import com.pphgzs.domain.VO.UserVO;

public class UserDaoImpl implements UserDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public int get_userTotalCount_byUserVO(UserVO userVO) {
		Session session = getSession();
		String hql = "select count(*) from jwcpxt_user "
				+ " where ( user_account like :screenSearch or user_name like :screenSearch ) "
				+ " and user_unit like :screenUnit ";
		Query query = session.createQuery(hql);
		//
		if (userVO.getScreenSearch().equals("")) {
			query.setParameter("screenSearch", "%%");
		} else {
			query.setParameter("screenSearch", "%" + userVO.getScreenSearch() + "%");
		}
		if (userVO.getScreenUnit().equals("")) {
			query.setParameter("screenUnit", "%%");
		} else {
			query.setParameter("screenUnit", "%" + userVO.getScreenUnit() + "%");
		}
		//
		int count = ((Number) query.uniqueResult()).intValue();
		//
		session.clear();
		return count;
	}

	@Override
	public List<jwcpxt_user> list_user_byUserVO(UserVO userVO) {

		Session session = getSession();
		String hql = "from jwcpxt_user " + " where ( user_account like :screenSearch or user_name like :screenSearch ) "
				+ " and user_unit like :screenUnit " + " order by user_gmt_create";
		Query query = session.createQuery(hql);
		//
		if (userVO.getScreenSearch().equals("")) {
			query.setParameter("screenSearch", "%%");
		} else {
			query.setParameter("screenSearch", "%" + userVO.getScreenSearch() + "%");
		}
		if (userVO.getScreenUnit().equals("")) {
			query.setParameter("screenUnit", "%%");
		} else {
			query.setParameter("screenUnit", "%" + userVO.getScreenUnit() + "%");
		}
		query.setFirstResult((userVO.getCurrPage() - 1) * userVO.getPageSize());
		query.setMaxResults(userVO.getPageSize());
		//
		List<jwcpxt_user> list = null;
		list = query.list();
		//
		session.clear();
		return list;
	}

	@Override
	public List<jwcpxt_user> list_user_byJurisdiction(String userJurisdictionEvaluate, String userJurisdictionReview,
			String userJurisdictionStatistics) {
		Session session = getSession();
		String hql = "from jwcpxt_user " + " where  user_Jurisdiction_evaluate like :userJurisdictionEvaluate "
				+ " and user_Jurisdiction_statistics like :userJurisdictionStatistics "
				+ " and user_Jurisdiction_review like :userJurisdictionReview " + " order by user_gmt_create";
		Query query = session.createQuery(hql);
		//
		if (userJurisdictionEvaluate.equals("")) {
			query.setParameter("userJurisdictionEvaluate", "%%");
		} else {
			query.setParameter("userJurisdictionEvaluate", "%" + userJurisdictionEvaluate + "%");
		}
		if (userJurisdictionReview.equals("")) {
			query.setParameter("userJurisdictionReview", "%%");
		} else {
			query.setParameter("userJurisdictionReview", "%" + userJurisdictionReview + "%");
		}
		if (userJurisdictionStatistics.equals("")) {
			query.setParameter("userJurisdictionStatistics", "%%");
		} else {
			query.setParameter("userJurisdictionStatistics", "%" + userJurisdictionStatistics + "%");
		}
		//
		List<jwcpxt_user> list = null;
		list = query.list();
		//
		session.clear();
		return list;
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
	public boolean delete_user(String userID) {
		Session session = getSession();
		String hql = "delete from jwcpxt_user where jwcpxt_user_id=:userID";
		Query query = session.createQuery(hql);
		//
		query.setParameter("userID", "userID");
		//
		query.executeUpdate();
		session.flush();
		return true;
	}

	@Override
	public jwcpxt_user get_user_byUserID(String userID) {
		Session session = getSession();
		String hql = "from jwcpxt_user where jwcpxt_user_id=:userID ";
		//
		Query query = session.createQuery(hql);
		//
		query.setParameter("userID", userID);
		//
		jwcpxt_user newUser = (jwcpxt_user) query.uniqueResult();
		session.clear();
		return newUser;
	}

	@Override
	public boolean ifExist_user_byUserAccount(String account) {
		Session session = getSession();

		String hql = "from jwcpxt_user where user_account=:account";
		Query query = session.createQuery(hql);
		//
		query.setParameter("account", account);
		//
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
