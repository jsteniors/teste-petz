package br.com.petz.domain.pet.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.petz.domain.pet.api.form.PetForm;
import br.com.petz.domain.pet.model.Pet;
import br.com.petz.domain.pet.service.PetService;
import br.com.petz.domain.pet.service.usecases.CreatePetCommand;
import br.com.petz.domain.pet.service.usecases.DeletePetCommand;
import br.com.petz.domain.pet.service.usecases.FindPetsCommand;
import br.com.petz.domain.pet.service.usecases.UpdatePetCommand;

@Service
public class PetServiceImpl implements PetService {
	
	private FindPetsCommand findPetsCommand;
	private UpdatePetCommand updatePetCommand;
	private CreatePetCommand createPetCommand;
	private DeletePetCommand deletePetCommand;
	
	public PetServiceImpl(FindPetsCommand findPetsCommand, UpdatePetCommand updatePetCommand,
			CreatePetCommand createPetCommand, DeletePetCommand deletePetCommand) {
		this.findPetsCommand = findPetsCommand;
		this.updatePetCommand = updatePetCommand;
		this.createPetCommand = createPetCommand;
		this.deletePetCommand = deletePetCommand;
	}

	@Override
	public Page<Pet> listByClienteId(Long clientId, Pageable pageable) {
		Page<Pet> petsOfOwner = this.findPetsCommand.listPetsByClient(clientId, pageable);
		return petsOfOwner;
	}

	@Override
	public Pet getById(Long petId) {
		Pet pet = this.findPetsCommand.findById(petId);
		return pet;
	}

	@Override
	public Pet create(PetForm petForm) {
		logger.info("Iniciando processo de cadastro de Pet({})", petForm);
		Pet pet = this.createPetCommand.create(petForm);
		return pet;
	}

	@Override
	public Pet update(Long petId, PetForm petForm) {
		logger.info("Iniciando processo de atualização de Pet({})", petForm);
		Pet pet = this.updatePetCommand.update(petId, petForm);
		return pet;
	}

	@Override	
	public void delete(Long petId) {
		this.deletePetCommand.delete(petId);
	}
	
	private static final Logger logger = LogManager.getLogger(PetServiceImpl.class);
}
