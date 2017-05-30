package controller;

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
	
	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public void save(){
		try {
			manufacturerDAO.insert(manufacturer);
			
			FacesUtil.addMessageInfo("Cargo salvo com sucesso");
		} catch (Exception e) {
			FacesUtil.addMessageInfo("Não foi possível salvar");
		}
	}
}
