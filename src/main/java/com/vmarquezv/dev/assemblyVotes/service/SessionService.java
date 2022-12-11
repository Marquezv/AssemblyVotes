package com.vmarquezv.dev.assemblyVotes.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmarquezv.dev.assemblyVotes.commons.status.AccessStatus;
import com.vmarquezv.dev.assemblyVotes.commons.status.SessionStatus;
import com.vmarquezv.dev.assemblyVotes.commons.status.VoteStatus;
import com.vmarquezv.dev.assemblyVotes.commons.util.CheckService;
import com.vmarquezv.dev.assemblyVotes.domain.entity.Session;
import com.vmarquezv.dev.assemblyVotes.domain.entity.User;
import com.vmarquezv.dev.assemblyVotes.domain.request.SessionRequestDTO;
import com.vmarquezv.dev.assemblyVotes.domain.response.SessionResponseDTO;
import com.vmarquezv.dev.assemblyVotes.exceptions.DataIntegratyViolationException;
import com.vmarquezv.dev.assemblyVotes.exceptions.ObjectNotFoundException;
import com.vmarquezv.dev.assemblyVotes.exceptions.StatusArgumentExceptionException;
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
	CheckService checkService;
	
	@Autowired
	SessionRepository repository;
	
	public SessionResponseDTO  insert(SessionRequestDTO sessionReq) {
		LocalDateTime date = LocalDateTime.now();
		if(sessionReq.getAccess_status() == null || sessionReq.getAccess_status() == AccessStatus.NONE ) {
			sessionReq.setAccess_status(AccessStatus.PUBLIC);
		}
		else if(sessionReq.getStarted_on().isAfter(sessionReq.getClosed_on()) ||
				sessionReq.getStarted_on().isBefore(date) ||
				sessionReq.getClosed_on().isBefore(sessionReq.getStarted_on()) ||
				sessionReq.getClosed_on().isBefore(date)) {
			throw new DataIntegratyViolationException("SESSION - INVALID DATE");
		}
	
		sessionReq.setUser(userService.findById(sessionReq.getUser_id()));
		sessionReq.setSurvey(surveyService.findById(sessionReq.getSurvey_id()));
		sessionReq.setAmount_votes(0);
		sessionReq.setUp_votes(0);
		sessionReq.setDown_votes(0);
		sessionReq.setSession_status(SessionStatus.NONE);
		sessionReq.setCreated_on(date);
		return repository.save(sessionReq.build()).toResponse();
	}
	
	public SessionResponseDTO addUserSession(SessionRequestDTO sessionReq) {
		
		Session session = repository.findById(sessionReq.getSession_id())
				.orElseThrow(
						() -> new ObjectNotFoundException("SESSION_ID - NOT_FOUND"));
		User user = userService.findById(sessionReq.getUser_id());
		
		if(!checkService.accessStatus(session.getAccess_status().ordinal())) {
			throw new DataIntegratyViolationException("SESSION_ID - NOT_PERMITED_ADD_USER");
		}
		else if(checkService.hourState(session.getStarted_on())) {
			throw new DataIntegratyViolationException("SESSION_ID - HAS_BEEN_STARTED");
		}
		allowedUserSessionService.userRegisterCheck(session.getId(), user.getId());
		allowedUserSessionService.addUserSession(session, user);

		return findById(sessionReq.getSession_id());
	}
	
	public List<SessionResponseDTO> findAll() {
		return 	repository.findAll().stream()
				.map(session -> session.toResponse())
				.map(session -> findById(session.getSession_id()))
				.collect(Collectors.toList());
	}
	
	
	public SessionResponseDTO findById(Long id) {
		SessionResponseDTO res = repository.findById(id)
				.orElseThrow(
						() -> new ObjectNotFoundException("SESSION_ID - NOT_FOUND")).toResponse();
		res.setAllowedUserSession(allowedUserSessionService.findAllUserSession(id));
		res.setUserResponse(userService.findById(res.getUser_id()).toResponse());
		res.setSurveyResponse(surveyService.findById(res.getSurvey_id()).toResponse());
		
		return res;
	}
	
	public void votingSession(VoteStatus voteStatus, Long session_id) {
		Session session = repository.findById(session_id).orElseThrow(
					() -> new ObjectNotFoundException("SESSION_ID - NOT_FOUND"));
		
		if(checkService.hourState(session.getClosed_on()) || session.getSession_status().equals(SessionStatus.NONE)) {
			throw new DataIntegratyViolationException("SESSION_ID - NOT_IN_PROGRESS");
		}
		
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
			throw new StatusArgumentExceptionException("Unexpected value: " + voteStatus);
		}
	}
	
}