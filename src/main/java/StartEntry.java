import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
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
		test1();
	}

	/**
	 * First test to know the application communicates with the database correctly
	 */
	public static void test1() {
		Session session = null;
		try {
			session = HibernateSessionUtil.getInstance().getSession();
			FirstMappingTest fmt = new FirstMappingTest();
			fmt.setTest("insertion 3");
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

}
