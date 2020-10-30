package br.com.petz.domain.client.api.form;

import java.time.LocalDate;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import br.com.petz.domain.client.model.Client;
import br.com.petz.domain.client.model.Gender;
import br.com.petz.domain.client.model.Phone;

public class ClientForm {
	
	@NotBlank
	private String name;
	
	@NotNull @Email
	private String email;
	
	@NotNull @CPF
	private String document;
	
	@NotNull
	private Gender gender;
	
	@NotNull @Valid
	private Phone cellPhone;
	
	@Valid
	private Phone phone;
	
	@NotNull
	private LocalDate birthDate;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}

	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Phone getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(Phone cellPhone) {
		this.cellPhone = cellPhone;
	}

	public Phone getPhone() {
		return phone;
	}
	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	
	public Client toClient() {
		return new Client(this);
	}
	
	@Override
	public String toString() {
		return "ClientForm [name=" + name + ", email=" + email + ", document=" + document + ", gender=" + gender + "]";
	}
	
}
