package com.vmarquezv.dev.assemblyVotes.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.vmarquezv.dev.assemblyVotes.commons.status.SurveyStatus;
import com.vmarquezv.dev.assemblyVotes.domain.entity.Session;
import com.vmarquezv.dev.assemblyVotes.domain.entity.Survey;
import com.vmarquezv.dev.assemblyVotes.domain.entity.User;
import com.vmarquezv.dev.assemblyVotes.repository.SessionRepository;
import com.vmarquezv.dev.assemblyVotes.repository.SurveyRepository;
import com.vmarquezv.dev.assemblyVotes.repository.UserRepository;

@Configuration
@Profile("local")
public class LocalConfig {
		
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SurveyRepository surveyRepository;
	
	@Autowired
	private SessionRepository sessionRepository;
	
	@Bean 
	public void startDB() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date data = new Date();
		
		data = formatter.parse("09/12/2022 01:31:00");
		
		User user1 = new User((long)1, "Vini", "109.691.830-90", data);
		User user2 = new User((long)2, "Lua", "309.691.830-90", data);
		userRepository.saveAll(List.of(user1,user2));
		
		Survey survey1 = new Survey((long)1, "Should we renovate the playground?", data, user1, SurveyStatus.OPPEND);
		Survey survey2 = new Survey((long)2, "Shall we change the gutters?", data, user2, SurveyStatus.OPPEND);
		surveyRepository.saveAll(List.of(survey1, survey2));
		
		Session session1 = new Session((long) 1, survey1, user1, data, data, data, 0, 0, 0, 1, 1, null);
		Session session2 = new Session((long) 2, survey2, user1, data, data, data, 0, 0, 0, 1, 1, null);
		sessionRepository.saveAll(List.of(session1, session2));
		
	}
	
}
