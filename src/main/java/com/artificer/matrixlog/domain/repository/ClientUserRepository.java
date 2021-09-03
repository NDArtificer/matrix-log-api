package com.artificer.matrixlog.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artificer.matrixlog.domain.model.ClientUser;

@Repository
public interface ClientUserRepository extends JpaRepository<ClientUser, Long> {
	
	Optional<ClientUser> findByUsername(String username);

}
