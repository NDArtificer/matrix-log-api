package com.artificer.matrixlog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.artificer.matrixlog.api.converter.ClientUserConverter;
import com.artificer.matrixlog.api.model.input.ClientUserInputModel;
import com.artificer.matrixlog.api.model.output.ClientUserModelOutput;
import com.artificer.matrixlog.domain.model.ClientUser;
import com.artificer.matrixlog.domain.repository.ClientUserRepository;
import com.artificer.matrixlog.domain.service.ClientUserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/client-users")
@AllArgsConstructor
public class ClientUserController {
	
	private ClientUserConverter converter;
	
	private ClientUserRepository clientUserRepository;
	
	private ClientUserService clientUserService;
	
	@GetMapping
	public List<ClientUserModelOutput> list(){
		return converter.toCollectionModel(clientUserRepository.findAll());
	}

	
	@GetMapping("/{clientUserId}")
	public ClientUserModelOutput find(@PathVariable Long clientUserId){
		return converter.toModel(clientUserService.find(clientUserId));
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ClientUserModelOutput register(@RequestBody @Valid ClientUserInputModel ClientUserInput) {
		ClientUser clienntUser = converter.toEntity(ClientUserInput);
		return converter.toModel(clientUserService.save(clienntUser));
	}
	
}
