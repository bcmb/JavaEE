package com.insart.utils;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.insart.dao.UserManagementHibernateImpl;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

public class HibernateUtil {
	private final static Logger logger = Logger.getLogger(HibernateUtil.class);
	private static final SessionFactory sessionFactory;
    
	static {
      try { 
    	  sessionFactory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
      } catch (Throwable ex) {
    	  logger.error("Initial SessionFactory creation failed.", ex);
    	  throw new ExceptionInInitializerError(ex);
      }
    }

    public static SessionFactory getSessionFactory() {
      return sessionFactory;
    }
}
