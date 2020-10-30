package br.com.petz.domain.pet.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import br.com.petz.domain.client.model.Client;
import br.com.petz.domain.pet.api.form.PetForm;

@Entity
public class Pet {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private LocalDate birthDate;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "breed.id", column = @Column(name = "breed_id")),
		@AttributeOverride(name = "type.id", column = @Column(name = "type_id"))
	})
	private Category category;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Client owner;
	
	@Enumerated
	private Gender gender;

	@Enumerated
	private SizeType sizeType;
	
	private BigDecimal weight;
	
	private String color;
	
	public Pet() {}
	
	public Pet(PetForm petForm, Client owner, Category category) {
		this.update(petForm, category);
		this.owner = owner;
	}
	
	public void update(PetForm petForm, Category category) {
		this.name = petForm.getName();
		this.birthDate = petForm.getBirthDate();
		this.gender = petForm.getGender();
		this.weight = petForm.getWeight();
		this.sizeType = petForm.getSizeType();
		this.color = petForm.getColor();
		
		this.category = category;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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

	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	public Client getOwner() {
		return owner;
	}
	public void setOwner(Client owner) {
		this.owner = owner;
	}

	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public SizeType getSizeType() {
		return sizeType;
	}
	public void setSizeType(SizeType sizeType) {
		this.sizeType = sizeType;
	}

	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	@Transient
	public PetType getPetType() {
		return Optional.ofNullable(this.category)
						.map(Category::getType)
						.orElse(null);
	}
	
	@Transient
	public Breed getBreed() {
		return Optional.ofNullable(this.category)
				.map(Category::getBreed)
				.orElse(null);
	}

	@Override
	public String toString() {
		return "Pet [id=" + id + ", name=" + name + "]";
	}
	
}
