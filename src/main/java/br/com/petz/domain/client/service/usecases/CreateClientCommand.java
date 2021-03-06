package br.com.petz.domain.client.service.usecases;

import br.com.petz.domain.client.api.form.ClientForm;
import br.com.petz.domain.client.model.Client;
import br.com.petz.exception.ExistingEntityException;

public interface CreateClientCommand {
	
	Client create(ClientForm clientForm) throws ExistingEntityException;
	
}
