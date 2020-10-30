package br.com.petz.domain.pet.service.usecases;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.petz.domain.pet.model.Pet;
import br.com.petz.exception.NotFoundException;

public interface FindPetsCommand {
	
	Page<Pet> listPetsByClient(Long clientId, Pageable pageable);
	Pet findById(Long id) throws NotFoundException;
	
}
