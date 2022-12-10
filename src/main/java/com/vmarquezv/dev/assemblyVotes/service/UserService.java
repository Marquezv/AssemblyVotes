package com.vmarquezv.dev.assemblyVotes.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
		LocalDateTime date = LocalDateTime.now();
		String cpfNumbers = userReq.getCpf().replaceAll("\\D", "");
		
		userReq.setCpf(cpfNumbers);
		userReq.setCreated_on(date);
		findByCpf(userReq);
		return repository.save(userReq.build()).toResponse();
	}
	
	
	public User findById(Long id) {
		return repository.findById(id)
				.orElseThrow(
						() -> new ObjectNotFoundException("USER_ID - NOT_FOUND"));
	}

	public List<UserResponseDTO> findAll() {
		return repository.findAll().stream().map(user -> user.toResponse())
			.collect(Collectors.toList());
	
	}
	
	private void findByCpf(UserRequestDTO userReq) {
		Optional<User> user = repository.findByCpf(userReq.getCpf());
		if(user.isPresent()) {
			throw new DataIntegratyViolationException("CPF - IN USE");
		}
		
	}
}
