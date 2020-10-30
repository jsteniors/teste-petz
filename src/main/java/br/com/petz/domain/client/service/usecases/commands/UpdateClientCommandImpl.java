package br.com.petz.domain.client.service.usecases.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import br.com.petz.domain.client.api.form.ClientForm;
import br.com.petz.domain.client.model.Client;
import br.com.petz.domain.client.repository.ClientRepository;
import br.com.petz.domain.client.service.usecases.FindClientsCommand;
import br.com.petz.domain.client.service.usecases.UpdateClientCommand;
import br.com.petz.exception.BusinessException;
import io.vavr.control.Try;

@Service
public class UpdateClientCommandImpl implements UpdateClientCommand {
	
	private ClientRepository clientRepository;
	private FindClientsCommand findClientsCommand;

	public UpdateClientCommandImpl(ClientRepository clientRepository, FindClientsCommand findClientsCommand) {
		this.clientRepository = clientRepository;
		this.findClientsCommand = findClientsCommand;
	}

	@Override
	public Client update(Long clientId, ClientForm clientForm) throws BusinessException {
		Client client = this.findPet(clientId);
		logger.info("Atualizando Cliente({})", clientForm);
		client.update(clientForm);
		this.clientRepository.save(client);
		logger.info("Cliente atualizado com sucesso");
		return client;
	}
	
	private Client findPet(Long clientId) {
		return Try.of(() -> this.findClientsCommand.findClient(clientId))
				.onFailure(e -> logger.error("Erro ao atualizar Cliente | Motivo: {}", e.getMessage()))
				.getOrElseThrow(e -> new BusinessException("Erro ao atualizar Cliente", e));
	}
	
	private static final Logger logger = LogManager.getLogger(UpdateClientCommandImpl.class);
}
