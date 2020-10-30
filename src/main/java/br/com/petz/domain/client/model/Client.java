package br.com.petz.domain.client.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.petz.domain.client.api.form.ClientForm;
import br.com.petz.domain.pet.model.Pet;

@Entity
public class Client {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(unique = true, nullable = false)
	private String document;
	
	@Enumerated
	private Gender gender;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "prefix", column = @Column(name = "cell_phone_prefix", nullable = false)),
		@AttributeOverride(name = "number", column = @Column(name = "cell_phone_number", nullable = false))
	})
	private Phone cellPhone;
	
	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "prefix", column = @Column(name = "phone_prefix")),
		@AttributeOverride(name = "number", column = @Column(name = "phone_number"))
	})
	private Phone phone;
	
	@Column(nullable = false)
	private LocalDate birthDate;
	
	@OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
	private List<Pet> pets;
	
	public Client() {}
	
	public Client(ClientForm clientForm) {
		this.update(clientForm);
		this.email = clientForm.getEmail();
		this.document = clientForm.getDocument();
	}
	
	public void update(ClientForm clientForm) {
		this.name = clientForm.getName();
		this.gender = clientForm.getGender();
		this.birthDate = clientForm.getBirthDate();
		this.cellPhone = clientForm.getCellPhone();
		this.phone = clientForm.getPhone();
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

	public Gender getGender() {
		return gender;
	}

	public Phone getCellPhone() {
		return cellPhone;
	}

	public Phone getPhone() {
		return phone;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", email=" + email + ", document=" + document + ", gender="
				+ gender + "]";
	}
	
}
