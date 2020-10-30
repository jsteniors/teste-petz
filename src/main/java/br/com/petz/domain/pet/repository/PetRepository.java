package br.com.petz.domain.pet.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.petz.domain.pet.model.Pet;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
	
	Page<Pet> findByOwner_Id(Long clientId, Pageable pageable);
	
}
