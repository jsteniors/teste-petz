package br.com.petz.domain.pet.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.petz.domain.pet.api.dto.PetDTO;
import br.com.petz.domain.pet.api.form.PetForm;

public interface PetAdapter {
	
	Page<PetDTO> listByClienteId(Long clientId, Pageable pageable);
	PetDTO getById(Long id);
	PetDTO update(Long id, PetForm petForm);
	PetDTO create(PetForm petForm);
	void delete(Long petId);
	
}
