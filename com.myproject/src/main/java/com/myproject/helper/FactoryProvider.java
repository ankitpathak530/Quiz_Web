package com.myproject.helper;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryProvider {
   
	public static SessionFactory sessionfactory;
	
	public static SessionFactory getFactory() {
		if(sessionfactory==null) {
			 sessionfactory = new Configuration().configure("Hibernate.config.xml").buildSessionFactory();
			 return sessionfactory;
		}
		else
			return sessionfactory;
	}
	public static void closeFactory() {
		if(sessionfactory.isOpen())
			sessionfactory.close();
	}
}
