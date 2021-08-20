package com.artificer.matrixlog.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artificer.matrixlog.domain.Exception.BusinessException;
import com.artificer.matrixlog.domain.Exception.EntityNotFoundException;
import com.artificer.matrixlog.domain.model.Client;
import com.artificer.matrixlog.domain.repository.ClientRepository;

@Service
public class ClientRegistrationService {

	@Autowired
	private ClientRepository clientRepository;

	public Client find(Long clientId) {
		return clientRepository.findById(clientId).orElseThrow(() -> new EntityNotFoundException("Client not found!"));
	}

	@Transactional
	public Client save(Client client) {
		boolean emailExists = clientRepository.findByEmail(client.getEmail()).stream()
				.anyMatch(clientExists -> !clientExists.equals(client));
		if (emailExists) {
			throw new BusinessException("There is already a client with the email entered, inform another one!");
		}
		return clientRepository.save(client);
	}

	@Transactional
	public void delete(Long clientId) {
		clientRepository.deleteById(clientId);
	}

}
