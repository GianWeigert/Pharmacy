package controller;

import java.util.List;
import util.FacesUtil;
import model.Manufacturer;
import dao.ManufacturerDAO;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ViewScoped
public class ManufacturerBean {
	Manufacturer manufacturer = new Manufacturer();
	ManufacturerDAO manufacturerDAO = new ManufacturerDAO();
	List<Manufacturer> manufacturers = null;
	
	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public ManufacturerDAO getManufacturerDAO() {
		return manufacturerDAO;
	}

	public void setManufacturerDAO(ManufacturerDAO manufacturerDAO) {
		this.manufacturerDAO = manufacturerDAO;
	}

	public List<Manufacturer> getManufacturers() {
		return manufacturers;
	}

	public void setManufacturers(List<Manufacturer> manufacturers) {
		this.manufacturers = manufacturers;
	}

	public void save(){
		try {
			this.manufacturerDAO.insert(manufacturer);
			
			FacesUtil.addMessageInfo("Cargo salvo com sucesso");
		} catch (Exception e) {
			FacesUtil.addMessageInfo("Não foi possível salvar");
		}
	}
	
	public void fetchAll(){
		try {
			this.manufacturers = manufacturerDAO.fetchAll();
		} catch (Exception e) {
			FacesUtil.addMessageInfo("Não foi possível listar os fabricantes");
		}
	}
}
