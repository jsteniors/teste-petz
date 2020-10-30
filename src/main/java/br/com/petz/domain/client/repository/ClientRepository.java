package br.com.petz.domain.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.petz.domain.client.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	
	Client findByEmailOrDocument(String email, String document);
	
}
