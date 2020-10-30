package br.com.petz.domain.pet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.petz.domain.pet.model.PetType;

@Repository
public interface PetTypeRepository extends JpaRepository<PetType, Long> {
	
	Optional<PetType> findByCode(String code);
	
}
