package br.com.petz.domain.client.service.usecases.commands;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.petz.domain.client.api.form.ClientForm;
import br.com.petz.domain.client.model.Client;
import br.com.petz.domain.client.repository.ClientRepository;
import br.com.petz.exception.ExistingEntityException;

public class CreateClientCommandImplTest {

	@Test(expected = ExistingEntityException.class)
	public void mustNotRegisterIfExistsEmailOrDocument() {
		ClientForm clientForm = createForm();
		
		ClientRepository clientRepository = Mockito.mock(ClientRepository.class);
		when(clientRepository.findByEmailOrDocument(clientForm.getEmail(), clientForm.getDocument())).thenReturn(new Client());
		
		CreateClientCommandImpl clientCommand = new CreateClientCommandImpl(clientRepository);
		
		clientCommand.create(clientForm);
		
		Assert.fail();
	}

	private ClientForm createForm() {
		ClientForm clientForm = new ClientForm();
		clientForm.setEmail("teste@email.net");
		clientForm.setDocument("12345678900");
		return clientForm;
	}

}
