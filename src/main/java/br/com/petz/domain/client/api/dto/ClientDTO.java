package br.com.petz.domain.client.api.dto;

import java.time.LocalDate;

import br.com.petz.domain.client.model.Client;
import br.com.petz.domain.client.model.Gender;
import br.com.petz.domain.client.model.Phone;

public class ClientDTO {
	
	private Long id;
	private String name;
	private String email;
	private String document;
	private Phone cellPhone;
	private Phone phone;
	private Gender gender;
	private LocalDate birthDate;
	
	private ClientDTO() {}
	
	public static ClientDTO fromClient(Client client) {
		ClientDTO clientDTO = new ClientDTO();
		clientDTO.id = client.getId();
		clientDTO.name = client.getName();
		clientDTO.email = client.getEmail();
		clientDTO.document = client.getDocument();
		clientDTO.cellPhone = client.getCellPhone();
		clientDTO.phone = client.getPhone();
		clientDTO.gender = client.getGender();
		clientDTO.birthDate = client.getBirthDate();
		return clientDTO;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getDocument() {
		return document;
	}

	public Phone getCellPhone() {
		return cellPhone;
	}

	public Phone getPhone() {
		return phone;
	}

	public Gender getGender() {
		return gender;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}
	
}
