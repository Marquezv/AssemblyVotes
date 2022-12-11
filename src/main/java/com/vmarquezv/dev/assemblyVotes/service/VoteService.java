package com.vmarquezv.dev.assemblyVotes.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmarquezv.dev.assemblyVotes.domain.entity.Vote;
import com.vmarquezv.dev.assemblyVotes.domain.entity.commons.VoteId;
import com.vmarquezv.dev.assemblyVotes.domain.request.VoteRequestDTO;
import com.vmarquezv.dev.assemblyVotes.domain.response.SessionResponseDTO;
import com.vmarquezv.dev.assemblyVotes.domain.response.VoteResponseDTO;
import com.vmarquezv.dev.assemblyVotes.exceptions.DataIntegratyViolationException;
import com.vmarquezv.dev.assemblyVotes.repository.VoteRepository;

@Service
public class VoteService {
	
	@Autowired
	UserService userService;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	AllowedUserSessionService allowedUserSessionService;
	
	@Autowired
	VoteRepository repository;

	public VoteResponseDTO insert(VoteRequestDTO voteReq) throws Exception {
		LocalDateTime date = LocalDateTime.now();
		SessionResponseDTO session = sessionService.findById(voteReq.getSession_id());
		findByUserSession(voteReq.getUser_id(), voteReq.getSession_id());
		if(!allowedUserSessionService.userCanVote(session.getSession_id(), voteReq.getUser_id(), session.getAccess_status())) {
			throw new DataIntegratyViolationException("USER_ID - NOT_PERMITED");
		}
		voteReq.setVoted_in(date);
		voteReq.setVoteId(createVoteId(voteReq.getUser_id(), voteReq.getSession_id()));
		sessionService.votingSession(voteReq.getVote_status(), voteReq.getSession_id());
		return repository.save(voteReq.build()).toResponse();
		
	}
	
	public VoteId createVoteId(Long user_id, Long session_id) {
		userService.findById(user_id);
		sessionService.findById(session_id);
		VoteId voteId = new VoteId(user_id, session_id);
		
		return voteId;
	}
	
	private void findByUserSession(Long user_id, Long session_id) {
		Optional<Vote> voteOptional = repository.findByUserSession(user_id, session_id);
		if(voteOptional.isPresent()) {
			System.out.println(voteOptional);
			throw new DataIntegratyViolationException("USER_ID - " + user_id + " has voted in that SESSION_ID - " + session_id);
		}
	}
}

