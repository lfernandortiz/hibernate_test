import org.hibernate.Session;
import org.hibernate.Transaction;
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

	public static void test1() {
		Session session = null;
		try {
			session = HibernateSessionUtil.getInstance().getSession();
			FirstMappingTest fmt = new FirstMappingTest();
			fmt.setTest("insertion 2");
			Transaction transaction = session.beginTransaction();
			session.save(fmt);
			transaction.commit();
			System.out.println(fmt.getId());

		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

}
