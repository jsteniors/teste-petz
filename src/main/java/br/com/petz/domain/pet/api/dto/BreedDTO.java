package br.com.petz.domain.pet.api.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.petz.domain.pet.model.Breed;

public class BreedDTO {
	
	@NotBlank
	private String code;
	
	public BreedDTO() {}
	
	public BreedDTO(String code) {
		this.code = code;
	}
	
	@JsonIgnore
	public static BreedDTO fromBreed(Breed breed) {
		return new BreedDTO(breed.getCode());
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	
	@Override
	public String toString() {
		return "BreedDTO [code=" + code + "]";
	}
	
}
