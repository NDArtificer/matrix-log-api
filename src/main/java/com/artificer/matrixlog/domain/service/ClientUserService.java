package com.artificer.matrixlog.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.artificer.matrixlog.domain.Exception.BusinessException;
import com.artificer.matrixlog.domain.Exception.EntityNotFoundException;
import com.artificer.matrixlog.domain.model.ClientUser;
import com.artificer.matrixlog.domain.repository.ClientUserRepository;

@Service
public class ClientUserService {

	@Autowired
	private ClientUserRepository clientUserRepository;

	public ClientUser find(Long clientUserId) {
		// TODO Auto-generated method stub
		return clientUserRepository.findById(clientUserId)
				.orElseThrow(() -> new EntityNotFoundException("Entity not found!"));
	}

	public ClientUser save(ClientUser clientUser) {
		boolean clientUserExists = clientUserRepository.findByUsername(clientUser.getUsername()).stream()
				.anyMatch(clientExists -> !clientExists.equals(clientUser));
		if (clientUserExists) {
			throw new BusinessException("There is already a client with the username entered, inform another one!");
		}
		return clientUserRepository.save(clientUser);
	}

}
