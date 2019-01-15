import java.util.List;
import java.util.stream.Stream;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.test.dao.CategoryDAO;
import org.hibernate.test.entity.Category;
import org.hibernate.test.entity.FirstMappingTest;
import org.hibernate.test.util.HibernateSessionUtil;

/**
 * This class is the application start point
 * 
 * @author Sergio Rojas
 *
 */
public class StartEntry {

	public static void main(String[] args) {
		test3();
	}

	/**
	 * First test to know the application communicates with the database correctly
	 */
	public static void test1() {
		Session session = null;
		try {
			session = HibernateSessionUtil.getInstance().getSession();
			FirstMappingTest fmt = new FirstMappingTest();
			fmt.setTest("insertion 1");
			Transaction transaction = session.beginTransaction();
			session.save(fmt);
			transaction.commit();
			System.out.println(fmt.getId());

		} finally {
			if (session != null) {
				session.close();
			}
		}

		try {
			// just to make sure hibernate does not get entities from cache
			session = HibernateSessionUtil.getInstance().getSession();
			Query<FirstMappingTest> query = session.createQuery("from FirstMappingTest", FirstMappingTest.class);
			List<FirstMappingTest> fmtList = query.list();

			for (FirstMappingTest fmt : fmtList) {
				System.out.println(fmt.getId());
			}

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}
	
	/**
	 * 
	 */
	public static void test2() {
		System.out.println(StartEntry.class.getName());
		System.out.println(StartEntry.class.getSimpleName());
		System.out.println(StartEntry.class.getCanonicalName());
		System.out.println(StartEntry.class.getTypeName());
	}
	
	/**
	 * 
	 */
	public static void test3() {
		Session session = null;
		try {
			session = HibernateSessionUtil.getInstance().getSession();
			CategoryDAO categoryDAO = new CategoryDAO(session);
			Category[] category = {new Category("Technology"), new Category("Music"), new Category("Religion")};
			Stream.of(category).forEach(categoryDAO::insert);
			
		}finally {
			if(session!=null) {
				session.close();
			}
			
		}
		
		try {
			session = HibernateSessionUtil.getInstance().getSession();
			CategoryDAO categoryDAO = new CategoryDAO(session);
			List<Category> categoryList = categoryDAO.findAll(); 
			categoryList.stream().forEach(c->System.out.println(c));
			
		}finally {
			if(session!=null) {
				session.close();
			}
			
		}
		
	}

}
