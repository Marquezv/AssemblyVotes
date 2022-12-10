package com.vmarquezv.dev.assemblyVotes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vmarquezv.dev.assemblyVotes.domain.entity.AllowedUserSession;

@Repository
public interface AllowedUserSessionRepository extends JpaRepository<AllowedUserSession, Long> {
	
	@Query(value = "SELECT * FROM ALLOWEDUSERSESSION WHERE SESSION_ID = ?1 AND USER_ID = ?2 ",
			nativeQuery = true)
	Optional<AllowedUserSession> findBySessionUser(Long session_id, Long user_id);
	
	@Query(value = "SELECT * FROM ALLOWEDUSERSESSION WHERE SESSION_ID = ?1",
			nativeQuery = true)
	List<AllowedUserSession> findBySession(Long session_id);
}

