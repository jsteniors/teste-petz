package br.com.petz.domain.pet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.petz.domain.pet.model.Breed;

@Repository
public interface BreedRepository extends JpaRepository<Breed, Long> {
	
	Optional<Breed> findByCodeAndType_Code(String breedCode, String typeCode);
	
}
