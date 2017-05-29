package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Position;
import util.HibernateUtil;

public class PositionDAO {
	public void insert(Position position) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(position);
			transaction.commit();
		} catch (RuntimeException ex) {
			if (transaction != null) {
				transaction.rollback();	
			}
			
		} finally {
			session.close();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Position> fetchAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Position> positions = null;

		try {
			Query query = session.createQuery("from position");
			positions = query.list();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			session.close();
		}

		return positions;
	}
}
