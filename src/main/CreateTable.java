package main;

import model.Position;
import model.Employee;
import util.HibernateUtil;

public class CreateTable {
	public static void main(String[] args) {
		HibernateUtil.getSessionFactory();
		
		Position position = new Position();
		position.setDescription("Administrador");
		
		Employee employee = new Employee();
		employee.setCpf("288.177.843-70");
		employee.setName("Administrador");
		employee.setPassword("12345678");
		employee.setPosition(position);
		
		new dao.PositionDAO().insert(position);
		new dao.EmployeeDAO().insert(employee);
		
		HibernateUtil.getSessionFactory().close();
	}
}
