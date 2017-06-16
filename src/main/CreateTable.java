package main;
import util.HibernateUtil;

public class CreateTable {
	public static void main(String[] args) {
		HibernateUtil.getSessionFactory();
		
		HibernateUtil.getSessionFactory().close();
	}
}
