package com.vmarquezv.dev.assemblyVotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmarquezv.dev.assemblyVotes.domain.entity.Session;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

}

