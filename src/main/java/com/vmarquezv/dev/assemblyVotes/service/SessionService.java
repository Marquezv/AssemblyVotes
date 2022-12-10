package com.vmarquezv.dev.assemblyVotes.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmarquezv.dev.assemblyVotes.commons.status.SessionStatus;
import com.vmarquezv.dev.assemblyVotes.commons.status.VoteStatus;
import com.vmarquezv.dev.assemblyVotes.domain.entity.Session;
import com.vmarquezv.dev.assemblyVotes.domain.entity.User;
import com.vmarquezv.dev.assemblyVotes.domain.request.SessionRequestDTO;
import com.vmarquezv.dev.assemblyVotes.domain.response.SessionResponseDTO;
import com.vmarquezv.dev.assemblyVotes.exceptions.ObjectNotFoundException;
import com.vmarquezv.dev.assemblyVotes.repository.SessionRepository;

@Service
public class SessionService {
	
	@Autowired
	UserService userService;
	
	@Autowired
	SurveyService surveyService;
	
	@Autowired
	AllowedUserSessionService allowedUserSessionService;
	
	@Autowired
	SessionRepository repository;
	
	@SuppressWarnings("deprecation")
	public SessionResponseDTO  insert(SessionRequestDTO sessionReq) {
		
		Date data = new Date(System.currentTimeMillis());
		data.setHours(data.getHours() -3);
		sessionReq.setCreated_on(data);
		sessionReq.setUser(userService.findById(sessionReq.getUser_id()));
		sessionReq.setSurvey(surveyService.findById(sessionReq.getSurvey_id()));
		sessionReq.setAmount_votes(0);
		sessionReq.setUp_votes(0);
		sessionReq.setDown_votes(0);
		sessionReq.setSession_status(SessionStatus.NONE);
		return repository.save(sessionReq.build()).toResponse();
	}
	
	public SessionResponseDTO addUserSession(SessionRequestDTO sessionReq) {
		
		Session session = repository.findById(sessionReq.getSession_id())
				.orElseThrow(
						() -> new ObjectNotFoundException("SESSION_ID - NOT_FOUND"));
		User user = userService.findById(sessionReq.getUser_id());
		
		allowedUserSessionService.addUserSession(session, user);
		
		
		return findById(sessionReq.getSession_id());
	}
	
	public List<SessionResponseDTO> findAll() {
		return repository.findAll().stream()
				.map(session -> session.toResponse())
				.map(session -> findById(session.getSession_id()))
				.collect(Collectors.toList());
	}
	
	public SessionResponseDTO findById(Long id) {
		SessionResponseDTO res = repository.findById(id)
				.orElseThrow(
						() -> new ObjectNotFoundException("SESSION_ID - NOT_FOUND")).toResponse();
		res.setAllowedUserSession(allowedUserSessionService.findAllUserSession(id));
		return res;
	}
	
	public void votingSession(VoteStatus voteStatus, Long session_id) {
		Session session = repository.findById(session_id).orElseThrow(
					() -> new ObjectNotFoundException("SESSION_ID - NOT_FOUND"));
		Integer upVotes = session.getUp_votes();
		Integer downVotes = session.getDown_votes();
		Integer amountVotes = session.getAmount_votes();
		switch (voteStatus.ordinal()){
		case 1: {
			session.setUp_votes(upVotes + 1);
			session.setAmount_votes(amountVotes + 1);
			repository.save(session);
			break;
		}
		case 2: {
			session.setDown_votes(downVotes + 1);
			session.setAmount_votes(amountVotes + 1);
			repository.save(session);
			break;
		}
		default:
			throw new IllegalArgumentException("Unexpected value: " + voteStatus);
		}
	}
}
