package com.vmarquezv.dev.assemblyVotes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.vmarquezv.dev.assemblyVotes.commons.status.AccessStatus;
import com.vmarquezv.dev.assemblyVotes.commons.status.SessionStatus;
import com.vmarquezv.dev.assemblyVotes.commons.util.CheckService;
import com.vmarquezv.dev.assemblyVotes.domain.entity.AllowedUserSession;
import com.vmarquezv.dev.assemblyVotes.domain.entity.Session;
import com.vmarquezv.dev.assemblyVotes.domain.entity.Survey;
import com.vmarquezv.dev.assemblyVotes.domain.entity.User;
import com.vmarquezv.dev.assemblyVotes.domain.request.SessionRequestDTO;
import com.vmarquezv.dev.assemblyVotes.domain.response.AllowedUserSessionResponseDTO;
import com.vmarquezv.dev.assemblyVotes.domain.response.SessionResponseDTO;
import com.vmarquezv.dev.assemblyVotes.domain.response.SurveyResponseDTO;
import com.vmarquezv.dev.assemblyVotes.domain.response.UserResponseDTO;
import com.vmarquezv.dev.assemblyVotes.repository.AllowedUserSessionRepository;
import com.vmarquezv.dev.assemblyVotes.repository.SessionRepository;

@SpringBootTest
public class SessionServiceTest {
	
	private static final long ID = (long) 1;
	private static final String USERNAME = "Vinicius";
	private static final LocalDateTime CREATED_ON = LocalDateTime.of(2022, 12, 10, 18, 22, 30);
	private static final LocalDateTime DATESTART = LocalDateTime.of(2022, 12, 10, 19, 22, 30);
	private static final LocalDateTime DATECLOSE = LocalDateTime.of(2022, 12, 10, 22, 22, 30);

	private static final String CPF = "490.605.290-81";
	private static final String DESCRIPTION = "Shall we change the gutters ?";
	
	
	@InjectMocks
	private SessionService sessionService;

	@InjectMocks
	private UserService userService;
	
	@InjectMocks
	private SurveyService surveyService;
	
	@InjectMocks
	private AllowedUserSessionService allowedUserSessionService;
	
	@InjectMocks
	private CheckService checkService;

	@Mock
	private SessionRepository repository;
	
	@Mock
	private AllowedUserSessionRepository allowedUSRepository;
	
	private User user;
	private UserResponseDTO userRes;
	private Survey survey;
	private SurveyResponseDTO surveyRes;
	private Session session;
	private Optional<Session> optionalSession;
	private SessionRequestDTO sessionReq;
	private SessionResponseDTO sessionRes;
	private AllowedUserSession allowedUS;
	private AllowedUserSessionResponseDTO allowedUSRes;
	
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startSession();
	}
	

	
	
	private void startSession() {
		user = new User(ID, USERNAME, CPF, CREATED_ON);
		survey = new Survey(ID, DESCRIPTION, CREATED_ON, user);
		session = new Session(ID, survey, user,DATESTART, DATECLOSE, CREATED_ON,0, 0, 0, AccessStatus.PUBLIC, SessionStatus.NONE);
		sessionRes = session.toResponse();
		sessionReq = new SessionRequestDTO(ID, survey, survey.getId(), user, user.getId(), DATESTART, DATECLOSE, CREATED_ON, 0, 0, 0, AccessStatus.PUBLIC, SessionStatus.NONE, List.of());
		
		allowedUS = new AllowedUserSession(ID, session, user);
		allowedUSRes = new AllowedUserSessionResponseDTO(session.getId(), user.getId());
		
		optionalSession = Optional.of(session);
	}
	
	
	
}
