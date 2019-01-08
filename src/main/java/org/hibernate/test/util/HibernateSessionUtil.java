package org.hibernate.test.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Hibernate session util to encapsulate the session creation
 * 
 * @author Sergio Rojas
 *
 */
public class HibernateSessionUtil {

	private static HibernateSessionUtil hsu = new HibernateSessionUtil();

	private SessionFactory sf;

	/**
	 * Singleton class to create Hibernate Sessions
	 */
	private HibernateSessionUtil() {
		// A SessionFactory is set up once for an application!
		// configure() method call configures settings from hibernate.cfg.xml
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
			sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			// The registry would be destroyed by the SessionFactory, but if we had trouble
			// building the SessionFactory so destroy it manually.
			StandardServiceRegistryBuilder.destroy(registry);
			e.printStackTrace();
			throw new ExceptionInInitializerError("impossible to build HibernateSessionUtil class");
		}
	}

	/**
	 * Retrieves the singleton instance
	 * 
	 * @return
	 */
	public static HibernateSessionUtil getInstance() {
		return hsu;
	}

	/**
	 * Get Hibernate Session
	 * 
	 * @return
	 */
	public Session getSession() {
		return sf.openSession();
	}

}
