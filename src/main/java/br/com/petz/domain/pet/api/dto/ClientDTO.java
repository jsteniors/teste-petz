package br.com.petz.domain.pet.api.dto;

import com.sun.istack.NotNull;

import br.com.petz.domain.client.model.Client;

public class ClientDTO {
	
	@NotNull
	private Long id;
	
	public ClientDTO() {}
	
	public ClientDTO(Long id) {
		this.id = id;
	}
	
	public static ClientDTO fromClient(Client client) {
		return new ClientDTO(client.getId());
	}

	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "ClientDTO [id=" + id + "]";
	}
	
}
