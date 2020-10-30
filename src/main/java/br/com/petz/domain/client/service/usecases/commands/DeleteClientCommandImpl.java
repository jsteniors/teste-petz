package br.com.petz.domain.client.service.usecases.commands;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import br.com.petz.domain.client.repository.ClientRepository;
import br.com.petz.domain.client.service.usecases.DeleteClientCommand;

@Service
public class DeleteClientCommandImpl implements DeleteClientCommand {
	
	private ClientRepository clientRepository;

	public DeleteClientCommandImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public void delete(Long clientId) {
		logger.info("Deletando cliente[id={}]", clientId);
		this.clientRepository.deleteById(clientId);
	}
	
	private static final Logger logger = LogManager.getLogger(DeleteClientCommandImpl.class);
}
