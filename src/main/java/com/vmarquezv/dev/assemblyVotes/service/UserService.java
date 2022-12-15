package com.vmarquezv.dev.assemblyVotes.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vmarquezv.dev.assemblyVotes.domain.entity.User;
import com.vmarquezv.dev.assemblyVotes.domain.request.UserRequestDTO;
import com.vmarquezv.dev.assemblyVotes.domain.response.UserResponseDTO;
import com.vmarquezv.dev.assemblyVotes.exceptions.DataIntegratyViolationException;
import com.vmarquezv.dev.assemblyVotes.exceptions.ObjectNotFoundException;
import com.vmarquezv.dev.assemblyVotes.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
	

	private final UserRepository repository;
	
	
	private final PasswordEncoder passwordEncoder;
	
	public UserResponseDTO insert(UserRequestDTO userReq) throws Exception {
		try {
			String cpfNumbers = userReq.getCpf().replaceAll("\\D", "");
			System.out.println(userReq);
			userReq.setPassword(passwordEncoder.encode(userReq.getPassword()));
			
			userReq.setCpf(cpfNumbers);
			findByCpf(userReq);
			log.info("[ USER|SERVICE ] -" + "- [ FUNCTION : INSERT ]");
		}catch (Exception err) {
			throw new DataIntegratyViolationException("CPF - IN USE");
		}
		
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
	
	public void validateUser(Long user_id, String password) {
		String user_password = findById(user_id).getPassword();
		if(!passwordEncoder.matches(password, user_password)) {
			throw new DataIntegratyViolationException("USER_ID - NOT_PERMITED");
		}
	}
}
