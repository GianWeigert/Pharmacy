package model;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "position")  
public class Position {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;
	
	@NotEmpty(message = "O campo descrição não pode ser nulo")
	@Size(min = 3, max = 100, message = "O campo descrição deve estar entre 3 e 100 caracteres")
	@Column(name = "description",length = 255, nullable = false)
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Position [id=" + this.id + ", description=" + this.description + "]";
	}
}
