package br.com.petz.domain.pet.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import br.com.petz.domain.pet.api.form.Categoryable;
import br.com.petz.domain.pet.model.Breed;
import br.com.petz.domain.pet.model.Category;
import br.com.petz.domain.pet.model.PetType;
import br.com.petz.domain.pet.repository.BreedRepository;
import br.com.petz.domain.pet.repository.PetTypeRepository;
import br.com.petz.domain.pet.service.CategoryService;
import br.com.petz.exception.NotFoundException;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private BreedRepository breedRepository;
	private PetTypeRepository petTypeRepository;
	
	public CategoryServiceImpl(BreedRepository breedRepository, PetTypeRepository petTypeRepository) {
		this.breedRepository = breedRepository;
		this.petTypeRepository = petTypeRepository;
	}

	@Override
	public Category findCategory(Categoryable categoryable) throws NotFoundException {
		Breed breed = findBreed(categoryable.getBreedCode(), categoryable.getTypeCode());
		PetType petType = findPetType(categoryable.getTypeCode());
		return new Category(breed, petType);
	}
	
	private PetType findPetType(String typeCode) {
		logger.info("Consultando tipo do pet[code={}]", typeCode);
		return this.petTypeRepository
				.findByCode(typeCode)
				.orElseThrow(() -> createException(new NotFoundException("PetType[code=" + typeCode + "] não encontrado")));
	}

	private Breed findBreed(String breedCode, String typeCode) {
		logger.info("Consultando raça do pet[code={}]", breedCode);
		return this.breedRepository
				.findByCodeAndType_Code(breedCode, typeCode)
				.orElseThrow(() -> createException(new NotFoundException("Breed[code=" + breedCode + ", typeCode=" + typeCode + "] não encontrado")));
	}
	
	private RuntimeException createException(RuntimeException e) {
		logger.error("Erro ao atualizar Pet | Motivo: {}", e.getMessage());
		return e;
	}
	
	private static final Logger logger = LogManager.getLogger(CategoryServiceImpl.class);
}
