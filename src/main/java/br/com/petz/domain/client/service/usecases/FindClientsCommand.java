package br.com.petz.domain.client.service.usecases;

import br.com.petz.domain.client.model.Client;
import br.com.petz.exception.NotFoundException;

public interface FindClientsCommand {
	
	Client findClient(Long clientId) throws NotFoundException;
	
}
