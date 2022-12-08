package com.vmarquezv.dev.assemblyVotes.service;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmarquezv.dev.assemblyVotes.domain.entity.User;
import com.vmarquezv.dev.assemblyVotes.domain.request.UserRequestDTO;
import com.vmarquezv.dev.assemblyVotes.domain.response.UserResponseDTO;
import com.vmarquezv.dev.assemblyVotes.exceptions.DataIntegratyViolationException;
import com.vmarquezv.dev.assemblyVotes.exceptions.ObjectNotFoundException;
import com.vmarquezv.dev.assemblyVotes.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	public UserResponseDTO insert(UserRequestDTO userReq) throws Exception {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		userReq.setCreated_on(timestamp);
		findByCpf(userReq);
		return repository.save(userReq.build()).toResponse();
	}
	
	
	public User findById(Long id) {
		return repository.findById(id)
				.orElseThrow(
						() -> new ObjectNotFoundException("USER_ID - NOT_FOUND"));
	}

	private void findByCpf(UserRequestDTO userReq) {
		Optional<User> user = repository.findByCpf(userReq.getCpf());
		if(user.isPresent()) {
			System.out.println(user);
			throw new DataIntegratyViolationException("CPF - IN USE");
		}
		
	}
}
