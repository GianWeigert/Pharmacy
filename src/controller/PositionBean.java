package controller;

import util.FacesUtil;
import model.Position;
import dao.PositionDAO;
import javax.faces.bean.ViewScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ViewScoped
public class PositionBean {
	Position position = new Position();
	PositionDAO positionDAO = new PositionDAO();
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public void save(){
		try {
			positionDAO.insert(position);
			
			FacesUtil.addMessageInfo("Cargo salvo com sucesso");
		} catch (Exception e) {
			FacesUtil.addMessageInfo("Não foi possível salvar");
		}
	}
}
