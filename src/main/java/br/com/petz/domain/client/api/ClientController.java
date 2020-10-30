package br.com.petz.domain.client.api;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.petz.domain.client.api.dto.ClientDTO;
import br.com.petz.domain.client.api.form.ClientForm;
import br.com.petz.domain.client.model.Client;
import br.com.petz.domain.client.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController implements ClientAdapter {
	
	private ClientService clientService;
	
	public ClientController(ClientService clientService) {
		this.clientService = clientService;
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@Override
	public ClientDTO getById(@PathVariable Long id) {
		logger.info("Recebendo requisição de consulta de cliente[id={}]", id);
		Client client = this.clientService.getById(id);
		logger.info("Retornando cliente => {}", client);
		return ClientDTO.fromClient(client);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{id}")
	@Override
	public ClientDTO update(@PathVariable Long id, @RequestBody @Valid ClientForm clientForm) {
		logger.info("Recebendo requisição de atualização de cliente({})", clientForm);
		Client client = this.clientService.update(id, clientForm);
		logger.info("Requisição de atualização de cliente processada com sucesso");
		return ClientDTO.fromClient(client);
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	@Override
	public ClientDTO create(@RequestBody @Valid ClientForm clientForm) {
		logger.info("Recebendo requisição de cadastro de cliente({})", clientForm);
		Client client = this.clientService.create(clientForm);
		logger.info("Requisição de cadastro de cliente processada com sucesso");
		return ClientDTO.fromClient(client);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	@Override
	public void delete(@PathVariable Long id) {
		logger.info("Recebendo requisição de exclusão de cliente[id={}]", id);
		this.clientService.delete(id);
		logger.info("Requisição de exclusão de cliente processada com sucesso");
	}
	
	private static final Logger logger = LogManager.getLogger(ClientController.class);
}
