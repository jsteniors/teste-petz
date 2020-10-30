package br.com.petz.domain.pet.api;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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

import br.com.petz.domain.pet.api.dto.PetDTO;
import br.com.petz.domain.pet.api.form.PetForm;
import br.com.petz.domain.pet.model.Pet;
import br.com.petz.domain.pet.service.PetService;

@RestController
@RequestMapping("/pet")
public class PetController implements PetAdapter {
	
	private PetService petService;
	
	public PetController(PetService petService) {
		this.petService = petService;
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/client/{clientId}")
	@Override
	public Page<PetDTO> listByClienteId(@PathVariable Long clientId, @PageableDefault(sort = "id", direction = Direction.DESC, page = 0, size = 10) Pageable pageable) {
		logger.info("Recebendo requisição de listagem de pet do cliente[clientId={}]", clientId);
		Page<Pet> pagePetOfClient = this.petService.listByClienteId(clientId, pageable);
		logger.info("Retornando Pets do cliente. Size: {}", pagePetOfClient.getSize());
		return PetDTO.fromPage(pagePetOfClient);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/{id}")
	@Override
	public PetDTO getById(@PathVariable Long id) {
		logger.info("Recebendo requisição de consulta de pet[id={}]", id);
		Pet pet = this.petService.getById(id);
		logger.info("Retornando Pet => {}", pet);
		return PetDTO.fromPet(pet);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{id}")
	@Override
	public PetDTO update(@PathVariable Long id, @RequestBody @Valid PetForm petForm) {
		logger.info("Recebendo requisição de atualização de pet({})", petForm);
		Pet pet = this.petService.update(id, petForm);
		logger.info("Requisição de atualização de pet processada com sucesso");
		return PetDTO.fromPet(pet);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	@Override
	public PetDTO create(@RequestBody @Valid PetForm petForm) {
		logger.info("Recebendo requisição de cadastro de pet({})", petForm);
		Pet pet = this.petService.create(petForm);
		logger.info("Requisição de cadastro de pet processada com sucesso");
		return PetDTO.fromPet(pet);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{petId}")
	@Override
	public void delete(@PathVariable Long petId) {
		logger.info("Recebendo requisição de exclusão de pet[id={}]", petId);
		this.petService.delete(petId);
		logger.info("Requisição de exclusão de pet processada com sucesso");
	}
	
	private static final Logger logger = LogManager.getLogger(PetController.class);
}
