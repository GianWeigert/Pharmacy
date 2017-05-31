package controller;

import util.FacesUtil;
import model.Position;
import dao.PositionDAO;
import javax.faces.bean.ViewScoped;

import java.util.List;

import javax.faces.bean.ManagedBean;

@ManagedBean
@ViewScoped
public class PositionBean {
	Position position = new Position();
	PositionDAO positionDAO = new PositionDAO();
	List<Position> positions = null;
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	
	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}

	public void save(){
		try {
			this.positionDAO.insert(position);
			
			FacesUtil.addMessageInfo("Cargo salvo com sucesso");
		} catch (Exception e) {
			FacesUtil.addMessageInfo("Não foi possível salvar");
		}
	}
	
	public void fetchAll(){
		try {
			this.positions = positionDAO.fetchAll();
		} catch (Exception e) {
			FacesUtil.addMessageInfo("Não foi possível listar os fabricantes");
		}
	}
}
