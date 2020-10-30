package br.com.petz.domain.pet.model;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class Category {
	
	@ManyToOne
	private Breed breed;
	@ManyToOne
	private PetType type;
	
	public Category() {}
	
	public Category(Breed breed, PetType type) {
		this.breed = breed;
		this.type = type;
	}

	public Breed getBreed() {
		return breed;
	}
	public void setBreed(Breed breed) {
		this.breed = breed;
	}

	public PetType getType() {
		return type;
	}
	public void setType(PetType type) {
		this.type = type;
	}
	
}
