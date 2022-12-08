package com.vmarquezv.dev.assemblyVotes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vmarquezv.dev.assemblyVotes.domain.entity.User;


public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByCpf(String cpf);	
	
}

