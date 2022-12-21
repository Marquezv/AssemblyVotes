package com.vmarquezv.dev.userApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vmarquezv.dev.userApi.domain.entity.Survey;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {

}

