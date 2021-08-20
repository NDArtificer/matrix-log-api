package com.artificer.matrixlog.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.artificer.matrixlog.domain.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	Optional<Client> findByEmail(String email);
}
