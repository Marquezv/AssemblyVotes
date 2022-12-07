package com.vmarquezv.dev.assemblyVotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vmarquezv.dev.assemblyVotes.domain.entity.Session;


public interface SessionRepository extends JpaRepository<Session, Long> {

}

