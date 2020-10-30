package br.com.petz.domain.pet.service.usecases.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import br.com.petz.domain.pet.repository.PetRepository;
import br.com.petz.domain.pet.service.usecases.DeletePetCommand;

@Service
public class DeletePetCommandImpl implements DeletePetCommand {
	
	private PetRepository petRepository;
	
	public DeletePetCommandImpl(PetRepository petRepository) {
		this.petRepository = petRepository;
	}

	@Override
	public void delete(Long petId) {
		logger.info("Excluindo Pet[id={}]", petId);
		this.petRepository.deleteById(petId);
	}
	
	private static final Logger logger = LogManager.getLogger(DeletePetCommandImpl.class);
}
