package com.artificer.matrixlog.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.artificer.matrixlog.domain.Exception.BusinessException;
import com.artificer.matrixlog.domain.Exception.EntityNotFoundException;
import com.artificer.matrixlog.domain.model.ClientUser;
import com.artificer.matrixlog.domain.repository.ClientUserRepository;

@Service
public class ClientUserService implements UserDetailsService {

	@Autowired
	private ClientUserRepository clientUserRepository;

	public ClientUser find(Long clientUserId) {
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ClientUser clientUser = clientUserRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
		
		return User.builder()
				.username(clientUser.getUsername())
				.password(clientUser.getPassword())
				.roles("USER")
				.build();
	}

}
