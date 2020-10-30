package br.com.petz.domain.pet.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.domain.Page;

import br.com.petz.domain.pet.model.Gender;
import br.com.petz.domain.pet.model.Pet;
import br.com.petz.domain.pet.model.SizeType;

public class PetDTO {
	
	private Long id;
	private String name;
	private PetTypeDTO type;
	private BreedDTO breed;
	private ClientDTO owner;
	private SizeType sizeType;
	private Gender gender;
	private String color;
	private LocalDate birthDate;
	private BigDecimal weight;
	
	private PetDTO() {}
	
	public static Page<PetDTO> fromPage(Page<Pet> pagePet){
		return pagePet.map(PetDTO::fromPet);
	}
	
	public static PetDTO fromPet(Pet pet) {
		PetDTO petDTO = new PetDTO();
		petDTO.id = pet.getId();
		petDTO.name = pet.getName();
		petDTO.type = PetTypeDTO.fromPetType(pet.getPetType());
		petDTO.breed = BreedDTO.fromBreed(pet.getBreed());
		petDTO.owner = ClientDTO.fromClient(pet.getOwner());
		petDTO.sizeType = pet.getSizeType();
		petDTO.gender = pet.getGender();
		petDTO.color = pet.getColor();
		petDTO.birthDate = pet.getBirthDate();
		petDTO.weight = pet.getWeight();
		return petDTO;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public PetTypeDTO getType() {
		return type;
	}

	public BreedDTO getBreed() {
		return breed;
	}
	
	public ClientDTO getOwner() {
		return owner;
	}

	public Gender getGender() {
		return gender;
	}
	
	public SizeType getSizeType() {
		return sizeType;
	}

	public String getColor() {
		return color;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public BigDecimal getWeight() {
		return weight;
	}
	
	
}
