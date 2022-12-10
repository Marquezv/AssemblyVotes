package com.vmarquezv.dev.assemblyVotes.config;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.vmarquezv.dev.assemblyVotes.commons.status.AccessStatus;
import com.vmarquezv.dev.assemblyVotes.commons.status.SessionStatus;
import com.vmarquezv.dev.assemblyVotes.commons.status.SurveyStatus;
import com.vmarquezv.dev.assemblyVotes.domain.entity.AllowedUserSession;
import com.vmarquezv.dev.assemblyVotes.domain.entity.Session;
import com.vmarquezv.dev.assemblyVotes.domain.entity.Survey;
import com.vmarquezv.dev.assemblyVotes.domain.entity.User;
import com.vmarquezv.dev.assemblyVotes.repository.AllowedUserSessionRepository;
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
	
	@Autowired
	private AllowedUserSessionRepository allowedUserSessionRepository;
	
	@Bean 
	public void startDB() throws ParseException {
		LocalDateTime date = LocalDateTime.now();
		LocalDateTime dateStart = LocalDateTime.of(2022,12,10,18,22,30);
		LocalDateTime dateClose = LocalDateTime.of(2022,12,10,18,40,30);

		
		
		User user1 = new User((long)1, "Vini", "109.691.830-90", date);
		User user2 = new User((long)2, "Lua", "309.691.830-90", date);
		userRepository.saveAll(List.of(user1,user2));
		
		Survey survey1 = new Survey((long)1, "Should we renovate the playground?", date, user1, SurveyStatus.OPPEND);
		Survey survey2 = new Survey((long)2, "Shall we change the gutters?", date, user2, SurveyStatus.OPPEND);
		surveyRepository.saveAll(List.of(survey1, survey2));
		
		Collection<User> allowed_users = new LinkedHashSet<User>();
		allowed_users.add(user2);
		
		Session session1 = new Session((long) 1, survey2, user1, dateStart, dateClose, date, 0, 0, 0, AccessStatus.PRIVATE, SessionStatus.NONE);
		
		Session session2 = new Session((long) 2, survey2, user1, dateStart, dateClose, date, 0, 0, 0, AccessStatus.PUBLIC, SessionStatus.NONE);

		sessionRepository.saveAll(List.of(session1, session2));
		
		AllowedUserSession allowedUserSession1 = new AllowedUserSession((long)1, session2, user1);
		allowedUserSessionRepository.save(allowedUserSession1);
	}
	
}
