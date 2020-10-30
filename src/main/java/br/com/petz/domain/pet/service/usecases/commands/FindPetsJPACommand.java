package br.com.petz.domain.pet.service.usecases.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.petz.domain.pet.model.Pet;
import br.com.petz.domain.pet.repository.PetRepository;
import br.com.petz.domain.pet.service.usecases.FindPetsCommand;
import br.com.petz.exception.NotFoundException;

@Service
public class FindPetsJPACommand implements FindPetsCommand {
	
	private PetRepository petRepository;
	
	public FindPetsJPACommand(PetRepository petRepository) {
		this.petRepository = petRepository;
	}

	@Override
	public Page<Pet> listPetsByClient(Long clientId, Pageable pageable) {
		logger.info("Buscando pets do dono[clientId={}]", clientId);
		return this.petRepository.findByOwner_Id(clientId, pageable);
	}

	@Override
	public Pet findById(Long id)  throws NotFoundException {
		logger.info("Buscando Pet[id={}]", id);
		return this.petRepository.findById(id)
								.orElseThrow(() -> new NotFoundException("Pet[id=" + id + "] não encontrado"));
	}
	
	private static final Logger logger = LogManager.getLogger(FindPetsJPACommand.class);
}
