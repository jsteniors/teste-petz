package br.com.petz.domain.pet.service.usecases;

public interface DeletePetCommand {
	
	void delete(Long petId);
	
}
