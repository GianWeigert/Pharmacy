package dao;

import model.Manufacturer;
import util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ManufacturerDAO {
	public void insert(Manufacturer manufacturer) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(manufacturer);
			transaction.commit();
		} catch (RuntimeException ex) {
			if (transaction != null) {
				transaction.rollback();	
			}
			
		} finally {
			session.close();
		}
	}
}
