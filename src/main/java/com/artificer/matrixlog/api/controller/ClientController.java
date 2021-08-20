package com.artificer.matrixlog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.artificer.matrixlog.api.converter.ClientConverter;
import com.artificer.matrixlog.api.model.input.ClientInputModel;
import com.artificer.matrixlog.api.model.output.ClientModelOutput;
import com.artificer.matrixlog.domain.Exception.BusinessException;
import com.artificer.matrixlog.domain.model.Client;
import com.artificer.matrixlog.domain.repository.ClientRepository;
import com.artificer.matrixlog.domain.service.ClientRegistrationService;

@RestController
@RequestMapping("/clients")
@CrossOrigin("http://localhost:4200")
public class ClientController {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private ClientRegistrationService clientRegistrationService;
	@Autowired
	private ClientConverter clientConverter;

	@GetMapping
	public List<ClientModelOutput> listar() {
		return clientConverter.toCollectionModel(clientRepository.findAll());
	}

	@GetMapping("/{clientId}")
	public ClientModelOutput buscar(@PathVariable Long clientId) {
		return clientConverter.toModel(clientRegistrationService.find(clientId));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClientModelOutput adicionar(@Valid @RequestBody ClientInputModel clientInput) {
		Client client = clientConverter.toEntity(clientInput);
		return clientConverter.toModel(clientRegistrationService.save(client));
	}

	@PutMapping("/{clientId}")
	public ClientModelOutput atualizar(@PathVariable Long clientId, @Valid  @RequestBody ClientInputModel clientInput) {
		try {
			Client clientAtual = clientRegistrationService.find(clientId);
			clientConverter.copyToDaminObject(clientInput, clientAtual);
			return clientConverter.toModel(clientRegistrationService.save(clientAtual));
		} catch (Exception e) {
			throw new BusinessException("Failed updating client!");
		}

	}

	@DeleteMapping("/{clientId}")
	public ResponseEntity<Void> remover(@PathVariable Long clientId) {
		if (!clientRepository.existsById(clientId)) {
			return ResponseEntity.notFound().build();
		}

		clientRegistrationService.delete(clientId);

		return ResponseEntity.noContent().build();
	}
}
