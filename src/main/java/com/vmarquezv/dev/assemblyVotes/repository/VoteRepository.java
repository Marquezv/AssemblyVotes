package com.vmarquezv.dev.assemblyVotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vmarquezv.dev.assemblyVotes.domain.entity.Vote;


public interface VoteRepository extends JpaRepository<Vote, Long> {

}

