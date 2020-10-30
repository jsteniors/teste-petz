package br.com.petz.domain.pet.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.petz.domain.pet.api.form.PetForm;
import br.com.petz.domain.pet.model.Pet;
import br.com.petz.exception.BusinessException;
import br.com.petz.exception.NotFoundException;

public interface PetService {
	
	Page<Pet> listByClienteId(Long clientId, Pageable pageable);
	Pet getById(Long petId) throws NotFoundException;
	Pet create(PetForm petForm) throws BusinessException;
	Pet update(Long petId, PetForm petForm) throws BusinessException;
	void delete(Long petId);
	
}
