package br.com.petz.domain.client.service;

import br.com.petz.domain.client.api.form.ClientForm;
import br.com.petz.domain.client.model.Client;
import br.com.petz.exception.NotFoundException;

public interface ClientService {
	Client getById(Long clientId) throws NotFoundException;
	Client create(ClientForm clientForm);
	Client update(Long clientId, ClientForm clientForm) throws NotFoundException;
	void delete(Long clientId);
}
