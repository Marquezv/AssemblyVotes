package com.vmarquezv.dev.assemblyVotes.service;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vmarquezv.dev.assemblyVotes.domain.entity.commons.VoteId;
import com.vmarquezv.dev.assemblyVotes.domain.request.VoteRequestDTO;
import com.vmarquezv.dev.assemblyVotes.domain.response.VoteResponseDTO;
import com.vmarquezv.dev.assemblyVotes.repository.VoteRepository;

@Service
public class VoteService {
	
	@Autowired
	UserService userService;
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	VoteRepository repository;

	public VoteResponseDTO insert(VoteRequestDTO voteReq) throws Exception {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		voteReq.setVoted_in(timestamp);
		voteReq.setVoteId(createVoteId(voteReq.getUser_id(), voteReq.getSession_id()));
		System.out.println(voteReq);
		sessionService.votingSession(voteReq.getVote_status(), voteReq.getSession_id());
		return repository.save(voteReq.build()).toResponse();
		
	}
	
	public VoteId createVoteId(Long user_id, Long session_id) {
		userService.findById(user_id);
		sessionService.findById(session_id);
		VoteId voteId = new VoteId(user_id, session_id);
		
		return voteId;
	}
	
	
}

