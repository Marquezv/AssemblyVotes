package com.vmarquezv.dev.userApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmarquezv.dev.userApi.domain.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByCpf(String cpf);	
	
}

