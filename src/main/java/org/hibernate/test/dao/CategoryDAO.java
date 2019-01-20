package org.hibernate.test.dao;

import org.hibernate.Session;
import org.hibernate.test.entity.Category;

public class CategoryDAO extends AbstractGenericDAO<Category> {
	
	/**
	 * 	
	 * @param session
	 */
	public CategoryDAO(Session session) {
		super(session);
	}

	/**
	 * 
	 */
	@Override
	protected Class<Category> getEntityClass() {
		return Category.class;
	}
	

}
