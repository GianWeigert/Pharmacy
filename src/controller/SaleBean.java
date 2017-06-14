package controller;

import model.Employee;
import model.Product;
import model.Sale;
import model.SaleItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.EmployeeDAO;
import dao.ProductDAO;
import util.FacesUtil;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ViewScoped
public class SaleBean {
	List<Product> products = null;
	ProductDAO productDAO  = new ProductDAO();
	List<SaleItem> items;

	Sale sale;
	
	public Sale getSale() {
		if (this.sale == null) {
			this.sale = new Sale();
			this.sale.setTotal(new BigDecimal("0.00"));
		}
		
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public void fetchAllProducts(){
		try {
			this.products = productDAO.fetchAll();
		} catch (Exception e) {
			FacesUtil.addMessageInfo("Não foi possível listar os produtos.");
		}
	}
	
	public List<SaleItem> getItems() {
		if (items == null) {
			items = new ArrayList<SaleItem>();
		}
		
		return items;
	}

	public void setItems(List<SaleItem> items) {
		this.items = items;
	}

	public void addProduct(Product product) {
		int foundPosition = - 1;
		
		for (int i = 0; i < items.size() && foundPosition < 0; i++) {
			SaleItem saleItemTemp = items.get(i);
			
			if (saleItemTemp.getProduct().equals(product)) {
				foundPosition = i;
			}
		}
		
		SaleItem item = new SaleItem();
		item.setProduct(product);
		
		if (foundPosition < 0 ) {
			item.setQuantity(1);
			item.setValue(product.getPrice());
			this.items.add(item);
		} else {
			SaleItem saleItemTemp = this.items.get(foundPosition);
			item.setQuantity(saleItemTemp.getQuantity() + 1);
			item.setValue(product.getPrice().multiply(new BigDecimal(item.getQuantity())));
			
			this.items.set(foundPosition, item);
		}
		
		this.sale.setTotal(sale.getTotal().add(product.getPrice()));
	}
	
	public void removeProduct(SaleItem item) {
		int foundPosition = - 1;
		
		for (int i = 0; i < this.items.size() && foundPosition < 0; i++) {
			SaleItem saleItemTemp = this.items.get(i);
			
			if (saleItemTemp.getProduct().equals(item.getProduct())) {
				foundPosition = i;
			}
		}
		if (foundPosition > -1 ) {
			this.items.remove(foundPosition);
			this.sale.setTotal(sale.getTotal().subtract(item.getValue()));
		} 
	}
	
	public void loadSaleData() {
		this.sale.setTime(new Date());
		
		EmployeeDAO employeeDAO = new EmployeeDAO();
		Employee employee = employeeDAO.find(1);
		
		this.sale.setEmployee(employee);
	}
}