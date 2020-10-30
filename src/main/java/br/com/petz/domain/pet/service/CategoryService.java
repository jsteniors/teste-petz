package br.com.petz.domain.pet.service;

import br.com.petz.domain.pet.api.form.Categoryable;
import br.com.petz.domain.pet.model.Category;
import br.com.petz.exception.NotFoundException;

public interface CategoryService {
	Category findCategory(Categoryable categoryable) throws NotFoundException;
}
