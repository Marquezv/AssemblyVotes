package com.vmarquezv.dev.assemblyVotes.config;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.vmarquezv.dev.assemblyVotes.commons.status.SurveyStatus;
import com.vmarquezv.dev.assemblyVotes.domain.entity.Survey;
import com.vmarquezv.dev.assemblyVotes.domain.entity.User;
import com.vmarquezv.dev.assemblyVotes.repository.SurveyRepository;
import com.vmarquezv.dev.assemblyVotes.repository.UserRepository;

@Configuration
@Profile("local")
public class LocalConfig {
		
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SurveyRepository surveyRepository;
	
	@Bean 
	public void startDB() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		
		User user1 = new User((long)1, "Vini", "109.691.830-90", timestamp);
		User user2 = new User((long)2, "Lua", "309.691.830-90", timestamp);
		userRepository.saveAll(List.of(user1,user2));
		
		Survey survey1 = new Survey((long)1, "Should we renovate the playground?", timestamp, user1, SurveyStatus.OPPEND);
		Survey survey2 = new Survey((long)2, "Shall we change the gutters?", timestamp, user2, SurveyStatus.OPPEND);
		surveyRepository.saveAll(List.of(survey1, survey2));
	}
	
}
