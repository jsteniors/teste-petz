package br.com.petz.domain.pet.service.usecases;

import br.com.petz.domain.pet.api.form.PetForm;
import br.com.petz.domain.pet.model.Pet;
import br.com.petz.exception.BusinessException;

public interface UpdatePetCommand {
	
	Pet update(Long petId, PetForm petForm) throws BusinessException;
	
}
