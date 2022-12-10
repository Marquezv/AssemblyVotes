package com.vmarquezv.dev.assemblyVotes.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmarquezv.dev.assemblyVotes.domain.entity.AllowedUserSession;
import com.vmarquezv.dev.assemblyVotes.domain.entity.Session;
import com.vmarquezv.dev.assemblyVotes.domain.entity.User;
import com.vmarquezv.dev.assemblyVotes.domain.response.AllowedUserSessionResponseDTO;
import com.vmarquezv.dev.assemblyVotes.exceptions.ObjectNotFoundException;
import com.vmarquezv.dev.assemblyVotes.repository.AllowedUserSessionRepository;

@Service
public class AllowedUserSessionService {
	
	@Autowired
	AllowedUserSessionRepository repository;
	
	public List<AllowedUserSessionResponseDTO> findAllUserSession(Long session_id) {
		return repository.findBySession(session_id).stream()
				.map(allowedUS -> allowedUS.toResponse()).collect(Collectors.toList());
	}
	
	public AllowedUserSessionResponseDTO findBySessionUser(Long session_id, Long user_id) {
		return repository.findBySessionUser(session_id, user_id)
				.orElseThrow(
						() -> new ObjectNotFoundException("NOT_FOUND")).toResponse();
	}
	
	public void addUserSession(Session session, User user) {
		System.out.println(session);
		System.out.println(user);

		AllowedUserSession allowedUserSession = new AllowedUserSession()
				.setSession(session)
				.setUser(user);
		repository.save(allowedUserSession);
	}
}
