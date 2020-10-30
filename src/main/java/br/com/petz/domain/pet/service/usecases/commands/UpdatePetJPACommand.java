package br.com.petz.domain.pet.service.usecases.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import br.com.petz.domain.pet.api.form.Categoryable;
import br.com.petz.domain.pet.api.form.PetForm;
import br.com.petz.domain.pet.model.Category;
import br.com.petz.domain.pet.model.Pet;
import br.com.petz.domain.pet.repository.PetRepository;
import br.com.petz.domain.pet.service.CategoryService;
import br.com.petz.domain.pet.service.usecases.FindPetsCommand;
import br.com.petz.domain.pet.service.usecases.UpdatePetCommand;
import br.com.petz.exception.BusinessException;
import io.vavr.control.Try;

@Service
public class UpdatePetJPACommand implements UpdatePetCommand {
	
	private PetRepository petRepository;
	private FindPetsCommand findPetsJPACommand;
	private CategoryService categoryService;

	public UpdatePetJPACommand(PetRepository petRepository, FindPetsCommand findPetsJPACommand,
			CategoryService categoryService) {
		this.petRepository = petRepository;
		this.findPetsJPACommand = findPetsJPACommand;
		this.categoryService = categoryService;
	}

	@Override
	public Pet update(Long petId, PetForm petForm) throws BusinessException {
		Pet pet = findPet(petId);
		
		Category category = findCategory(petForm);
		logger.info("Atualizando informações do pet");
		pet.update(petForm, category);
		this.petRepository.save(pet);
		logger.info("Informações do pet atualizadas com sucesso");
		return pet;
	}
	
	private Pet findPet(Long petId) {
		return Try.of(() -> this.findPetsJPACommand.findById(petId))
				.onFailure(e -> logger.error("Erro ao atualizar Pet | Motivo: {}", e.getMessage()))
				.getOrElseThrow(e -> new BusinessException("Erro ao atualizar pet", e));
	}

	private Category findCategory(Categoryable categoryable) {
		return Try.of(() -> this.categoryService.findCategory(categoryable))
				.onFailure(e -> logger.error("Erro ao atualizar pet | Motivo: {}", e.getMessage()))
				.getOrElseThrow(e -> new BusinessException("Erro ao atualizar Pet", e));
		
	}
	
	private static final Logger logger = LogManager.getLogger(UpdatePetJPACommand.class);
}
