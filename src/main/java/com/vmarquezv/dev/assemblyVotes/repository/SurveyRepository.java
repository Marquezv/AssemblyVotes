package com.vmarquezv.dev.assemblyVotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vmarquezv.dev.assemblyVotes.domain.entity.Survey;


public interface SurveyRepository extends JpaRepository<Survey, Long> {

}

