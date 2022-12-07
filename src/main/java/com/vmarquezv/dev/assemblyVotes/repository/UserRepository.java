package com.vmarquezv.dev.assemblyVotes.repository;

import com.vmarquezv.dev.assemblyVotes.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

}

