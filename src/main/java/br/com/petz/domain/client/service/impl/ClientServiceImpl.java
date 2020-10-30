package br.com.petz.domain.client.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import br.com.petz.domain.client.api.form.ClientForm;
import br.com.petz.domain.client.model.Client;
import br.com.petz.domain.client.service.ClientService;
import br.com.petz.domain.client.service.usecases.CreateClientCommand;
import br.com.petz.domain.client.service.usecases.DeleteClientCommand;
import br.com.petz.domain.client.service.usecases.FindClientsCommand;
import br.com.petz.domain.client.service.usecases.UpdateClientCommand;
import br.com.petz.exception.NotFoundException;

@Service
public class ClientServiceImpl implements ClientService {
	
	private FindClientsCommand findClientsCommand;
	private CreateClientCommand createClientCommand;
	private UpdateClientCommand updateClientCommand;
	private DeleteClientCommand deleteClientCommand;
	
	public ClientServiceImpl(FindClientsCommand findClientsCommand, CreateClientCommand createClientCommand,
			UpdateClientCommand updateClientCommand, DeleteClientCommand deleteClientCommand) {
		this.findClientsCommand = findClientsCommand;
		this.createClientCommand = createClientCommand;
		this.updateClientCommand = updateClientCommand;
		this.deleteClientCommand = deleteClientCommand;
	}

	@Override
	public Client getById(Long clientId) throws NotFoundException {
		Client client = this.findClientsCommand.findClient(clientId);
		return client;
	}

	@Override
	public Client create(ClientForm clientForm) {
		logger.info("Iniciando processo de cadastro de Cliente({})", clientForm);
		Client client = this.createClientCommand.create(clientForm);
		return client;
	}

	@Override
	public Client update(Long clientId, ClientForm clientForm) throws NotFoundException {
		logger.info("Iniciando processo de atualização de Cliente({})", clientForm);
		Client client = this.updateClientCommand.update(clientId, clientForm);
		return client;
	}

	@Override
	public void delete(Long clientId) {
		this.deleteClientCommand.delete(clientId);
	}
	
	private static final Logger logger = LogManager.getLogger(ClientServiceImpl.class);
}