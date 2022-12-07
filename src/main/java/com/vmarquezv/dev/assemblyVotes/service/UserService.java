package com.vmarquezv.dev.assemblyVotes.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmarquezv.dev.assemblyVotes.domain.request.UserRequestDTO;
import com.vmarquezv.dev.assemblyVotes.domain.response.UserResponseDTO;
import com.vmarquezv.dev.assemblyVotes.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	public UserResponseDTO insert(UserRequestDTO userReq) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		userReq.setCreated_on(timestamp);
		return repository.save(userReq.build()).toResponse();
	}

}
