package br.com.petz.domain.client.service.usecases.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import br.com.petz.domain.client.model.Client;
import br.com.petz.domain.client.repository.ClientRepository;
import br.com.petz.domain.client.service.usecases.FindClientsCommand;
import br.com.petz.exception.NotFoundException;

@Service
public class FindClientsCommandImpl implements FindClientsCommand {
	
	private ClientRepository clientRepository;
	
	public FindClientsCommandImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public Client findClient(Long clientId) throws NotFoundException {
		logger.info("Consultando cliente[id={}]", clientId);
		return this.clientRepository
				.findById(clientId)
				.orElseThrow(() -> new NotFoundException("Client[id=" + clientId + "] não encontrado"));
	}
	private static final Logger logger = LogManager.getLogger(FindClientsCommandImpl.class);
}
