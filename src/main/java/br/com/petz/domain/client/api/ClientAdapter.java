package br.com.petz.domain.client.api;

import br.com.petz.domain.client.api.dto.ClientDTO;
import br.com.petz.domain.client.api.form.ClientForm;
import br.com.petz.exception.ExistingEntityException;
import br.com.petz.exception.NotFoundException;

public interface ClientAdapter {
	
	ClientDTO getById(Long id) throws NotFoundException;
	ClientDTO update(Long id, ClientForm clientForm);
	ClientDTO create(ClientForm clientForm) throws ExistingEntityException;
	void delete(Long id);
	
}
