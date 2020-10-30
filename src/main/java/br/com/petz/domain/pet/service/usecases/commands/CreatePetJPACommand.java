package br.com.petz.domain.pet.service.usecases.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import br.com.petz.domain.client.model.Client;
import br.com.petz.domain.client.service.usecases.FindClientsCommand;
import br.com.petz.domain.pet.api.form.Categoryable;
import br.com.petz.domain.pet.api.form.PetForm;
import br.com.petz.domain.pet.model.Category;
import br.com.petz.domain.pet.model.Pet;
import br.com.petz.domain.pet.repository.PetRepository;
import br.com.petz.domain.pet.service.CategoryService;
import br.com.petz.domain.pet.service.usecases.CreatePetCommand;
import br.com.petz.exception.BusinessException;
import br.com.petz.exception.NotFoundException;
import io.vavr.control.Try;

@Service
public class CreatePetJPACommand implements CreatePetCommand {
	
	private PetRepository petRepository;
	private CategoryService categoryService;
	private FindClientsCommand clientsCommand;
	
	public CreatePetJPACommand(PetRepository petRepository, CategoryService categoryService, FindClientsCommand clientsCommand) {
		this.petRepository = petRepository;
		this.categoryService = categoryService;
		this.clientsCommand = clientsCommand;
	}

	@Override
	public Pet create(PetForm petForm) throws NotFoundException {
		Category category = findCategory(petForm);
		Client owner = findClient(petForm);
		logger.info("Cadastrando Pet({})", petForm);
		Pet pet = petForm.createPet(owner, category);
		this.petRepository.save(pet);
		logger.info("Pet[id={}] cadastrado com sucesso", pet.getId());
		return pet;
	}

	private Category findCategory(Categoryable categoryable) {
		return Try.of(() -> this.categoryService.findCategory(categoryable))
				.onFailure(e -> logger.error("Erro ao cadastrar pet | Motivo: {}", e.getMessage()))
				.getOrElseThrow(e -> new BusinessException("Erro ao cadastrar Pet", e));
		
	}

	private Client findClient(PetForm petForm) {
		return Try.of(() -> this.clientsCommand.findClient(petForm.getOwnerId()))
				.onFailure(e -> logger.error("Erro ao cadastrar pet | Motivo: {}", e.getMessage()))
				.getOrElseThrow(e -> new BusinessException("Erro ao cadastrar Pet", e));
	}
	
	private static final Logger logger = LogManager.getLogger(CreatePetJPACommand.class);
}
