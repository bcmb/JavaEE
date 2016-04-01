package com.insart.dao;

import javax.persistence.Id;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.insart.model.User;
import com.insart.utils.HibernateUtil;

public class UserManagementHibernateImpl implements UserManagement {
	private final static Logger logger = Logger.getLogger(UserManagementHibernateImpl.class);

	@Override
	public User getUser(String userName) {
		User user = new User();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			user = (User) session.get(User.class, userName);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e.getMessage(), e);
		} finally {
			session.close();
		}
		return user;
	}

	@Override
	public boolean addUser(String username, String password) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.save(new User(username, password));
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			logger.error(e.getMessage(), e);
		} finally {
			session.close();
		}
		return tx.wasCommitted();
	}

	@Override
	public boolean createSession(String userName, String sessionId) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(new com.insart.model.Session(userName, sessionId));
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			logger.error(e.getMessage(), e);
		} finally {
			session.close();
		}
		return tx.wasCommitted();
	}

	@Override
	public com.insart.model.Session getSession(com.insart.model.Session s) {
		com.insart.model.Session userSession = new com.insart.model.Session();
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			userSession = (com.insart.model.Session) session.get(com.insart.model.Session.class, s.getSessionId());
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e.getMessage(), e);
		} finally {
			session.close();
		}
		return userSession;
	}

	@Override
	public boolean dropSession(com.insart.model.Session s) {
		Session session = null;
		Transaction tx = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.delete(s);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			logger.error(e.getMessage(), e);
		} finally {
			session.close();
		}
		return tx.wasCommitted();
	}

}
