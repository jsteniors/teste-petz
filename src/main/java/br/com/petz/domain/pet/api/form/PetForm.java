package br.com.petz.domain.pet.api.form;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.petz.domain.client.model.Client;
import br.com.petz.domain.pet.api.dto.BreedDTO;
import br.com.petz.domain.pet.api.dto.ClientDTO;
import br.com.petz.domain.pet.api.dto.PetTypeDTO;
import br.com.petz.domain.pet.model.Category;
import br.com.petz.domain.pet.model.Gender;
import br.com.petz.domain.pet.model.Pet;
import br.com.petz.domain.pet.model.SizeType;

public class PetForm implements Categoryable {
	
	@NotBlank
	private String name;
	@NotNull
	private LocalDate birthDate;
	@Valid @NotNull
	private BreedDTO breed;
	@Valid @NotNull
	private PetTypeDTO type;
	@Valid @NotNull
	private ClientDTO owner;
	@NotNull
	private Gender gender;
	private SizeType sizeType;
	private BigDecimal weight;
	private String color;
	
	public PetForm() {
		this.sizeType = SizeType.SMALL;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	
	@JsonIgnore
	public String getBreedCode() {
		return Optional.ofNullable(this.breed).map(BreedDTO::getCode).orElse(null);
	}
	public void setBreed(BreedDTO breed) {
		this.breed = breed;
	}
	
	@JsonIgnore
	public String getTypeCode() {
		return Optional.ofNullable(this.type).map(PetTypeDTO::getCode).orElse(null);
	}
	public void setType(PetTypeDTO type) {
		this.type = type;
	}
	
	@JsonIgnore
	public Long getOwnerId() {
		return Optional.ofNullable(this.owner).map(ClientDTO::getId).orElse(null);
	}
	public void setOwner(ClientDTO owner) {
		this.owner = owner;
	}
	
	public Pet createPet(Client owner, Category category) {
		return new Pet(this, owner, category);
	}

	public SizeType getSizeType() {
		return sizeType;
	}
	public void setSizeType(SizeType sizeType) {
		this.sizeType = sizeType;
	}

	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "PetForm [name=" + name + ", breed=" + breed + ", type=" + type + ", owner=" + owner + ", color=" + color
				+ "]";
	}
	
}
