package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Product;
import util.HibernateUtil;

public class ProductDAO {
	public void insert(Product position) {
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
	public List<Product> fetchAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		List<Product> products = null;

		try {
			Query query = session.createQuery("from Product p");
			products = query.list();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			session.close();
		}

		return products;
	}
}
