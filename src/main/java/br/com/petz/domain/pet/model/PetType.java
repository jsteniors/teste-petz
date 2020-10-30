package br.com.petz.domain.pet.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class PetType {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Integer id;
	@Column(nullable = false, unique = true)
	private String code;
	@Column(nullable = false)
	private String description;
	@OneToMany(mappedBy = "type", fetch = FetchType.LAZY)
	private List<Breed> breeds;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public List<Breed> getBreeds() {
		return breeds;
	}
	public void setBreeds(List<Breed> breeds) {
		this.breeds = breeds;
	}
	
}
