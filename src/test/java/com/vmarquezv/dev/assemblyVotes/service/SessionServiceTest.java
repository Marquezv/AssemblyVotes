package com.vmarquezv.dev.assemblyVotes.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vmarquezv.dev.assemblyVotes.commons.status.AccessStatus;
import com.vmarquezv.dev.assemblyVotes.domain.entity.Session;
import com.vmarquezv.dev.assemblyVotes.domain.entity.Survey;
import com.vmarquezv.dev.assemblyVotes.domain.entity.User;
import com.vmarquezv.dev.assemblyVotes.domain.request.SessionRequestDTO;
import com.vmarquezv.dev.assemblyVotes.domain.response.SessionResponseDTO;
import com.vmarquezv.dev.assemblyVotes.exceptions.DataIntegratyViolationException;
import com.vmarquezv.dev.assemblyVotes.repository.SessionRepository;

@ExtendWith(MockitoExtension.class)
public class SessionServiceTest {
	
	@InjectMocks
	private SessionService service;
	
	private static final long SESSION_ID = (long) 1;
	private static final long USER_ID = (long) 1;
	private static final long SURVEY_ID = (long) 1;
	private static final LocalDateTime STARTED_ON = LocalDateTime.of(2022,12,15,18,22,30);
	private static final LocalDateTime ERROR_STARTED_ON= LocalDateTime.of(2022,12,9,18,22,30);
	private static final LocalDateTime CLOSED_ON = LocalDateTime.of(2022,12,16,18,22,30);
	
	private User user;
	
	private Survey survey;
	
	private Session session;
	
	private SessionRequestDTO sessionReq;

	private SessionResponseDTO sessionRes;
	
	private SessionRequestDTO sessionReqEr;
	
	@Mock
	private AllowedUserSessionService allowedUSService;
	
	@Mock
	private UserService userService;
	
	@Mock
	private SurveyService surveyService;
	
	@Mock
	private SessionRepository repository;
	

	@BeforeEach
	void initUseCase() {
		startUser();
	}
	
	@Test
	void whenCreateThenReturnSuccess() {
		when(repository.save(any(Session.class))).thenReturn(session);
		sessionRes = service.insert(sessionReq);
		assertNotNull(sessionRes);
		assertEquals(sessionRes.getSession_id(), sessionReq.getSession_id());
		assertEquals(sessionRes.getUser_id(), sessionReq.getUser_id());
		assertEquals(sessionRes.getSurvey_id(), sessionReq.getSurvey_id());
		assertEquals(sessionRes.getStarted_on(), sessionReq.getStarted_on());
		assertEquals(sessionRes.getClosed_on(), sessionReq.getClosed_on());
		assertEquals(sessionRes.getAccess_status(), sessionReq.getAccess_status());
	}
	
	@Test
	void whenCreateThenReturnDataIntegratyViolationException() {
		try {
			service.insert(sessionReqEr);
		} catch(Exception err) {
			assertEquals(err.getClass(), DataIntegratyViolationException.class);
			assertEquals(err.getMessage(), "SESSION - INVALID DATE");
		}
	}
	
	private void startUser() {
		service = new SessionService(surveyService, allowedUSService, userService, repository);
		
		user = new User()
				.setId(USER_ID);
		
		survey = new Survey()
				.setId(SURVEY_ID);
		sessionReq = new SessionRequestDTO()
				.setUser_id(USER_ID)
				.setSurvey_id(SESSION_ID)
				.setStarted_on(STARTED_ON)
				.setClosed_on(CLOSED_ON)
				.setAccess_status(AccessStatus.PUBLIC);
		
		sessionReqEr = new SessionRequestDTO()
				.setUser_id(USER_ID)
				.setSurvey_id(SESSION_ID)
				.setStarted_on(ERROR_STARTED_ON)
				.setClosed_on(CLOSED_ON)
				.setAccess_status(AccessStatus.PUBLIC);
		
		session = new Session()
				.setUser(user)
				.setSurvey(survey)
				.setStarted_on(STARTED_ON)
				.setClosed_on(CLOSED_ON)
				.setAccess_status(AccessStatus.PUBLIC);
		
	}
	
}


