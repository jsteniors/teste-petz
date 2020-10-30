package br.com.petz.domain.client.service.usecases.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import br.com.petz.domain.client.api.form.ClientForm;
import br.com.petz.domain.client.model.Client;
import br.com.petz.domain.client.repository.ClientRepository;
import br.com.petz.domain.client.service.usecases.CreateClientCommand;
import br.com.petz.exception.ExistingEntityException;

@Service
public class CreateClientCommandImpl implements CreateClientCommand {
	
	private ClientRepository clientRepository;
	
	public CreateClientCommandImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public Client create(ClientForm clientForm) {
		Client client = clientForm.toClient();
		validateExistingClient(clientForm.getEmail(), clientForm.getDocument());
		logger.info("Cadastrando Cliente({})", clientForm);
		this.clientRepository.save(client);
		logger.info("Cliente[id={}] cadastrado com sucesso", client.getId());
		return client;
	}

	private void validateExistingClient(String email, String document) {
		logger.info("Validando cliente existente[email={}, document={}]", email, document);
		Client searchClient = this.clientRepository.findByEmailOrDocument(email, document);
		if(searchClient != null)
			 throw new ExistingEntityException(String.format("Cliente[email=%s, document=%s] já cadastrado", email, document));
	}
	
	private static final Logger logger = LogManager.getLogger(CreateClientCommandImpl.class);
}
