import java.util.List;
import java.util.stream.Stream;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.test.dao.CategoryDAO;
import org.hibernate.test.entity.Category;
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
		Session session1 = null;
		try {
			session1 = HibernateSessionUtil.getInstance().getSession();
			CategoryDAO categoryDAO = new CategoryDAO(session1);
			Category[] category = {new Category("Technology"), new Category("Music"), new Category("Religion")};
			Stream.of(category).forEach(categoryDAO::insert);
			
		}finally {
			if(session1!=null) {
				session1.close();
			}
			
		}

		//use of try with resource statement in order to close the resource automatically
		try (Session session = HibernateSessionUtil.getInstance().getSession()){
			CategoryDAO categoryDAO = new CategoryDAO(session);
			List<Category> categoryList = categoryDAO.findAll(); 
			categoryList.stream().forEach(c->System.out.println(c));
		}
		
	}

}
