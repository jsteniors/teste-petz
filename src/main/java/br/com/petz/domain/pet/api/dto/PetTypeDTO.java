package br.com.petz.domain.pet.api.dto;

import javax.validation.constraints.NotBlank;

import br.com.petz.domain.pet.model.PetType;

public class PetTypeDTO {
	
	@NotBlank
	private String code;

	public PetTypeDTO() {}
	
	public PetTypeDTO(String code) {
		this.code = code;
	}
	
	public static PetTypeDTO fromPetType(PetType petType) {
		return new PetTypeDTO(petType.getCode());
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return "PetTypeDTO [code=" + code + "]";
	}
	
}
