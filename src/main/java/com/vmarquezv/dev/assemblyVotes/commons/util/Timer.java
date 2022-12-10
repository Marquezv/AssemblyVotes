package com.vmarquezv.dev.assemblyVotes.commons.util;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vmarquezv.dev.assemblyVotes.commons.status.SessionStatus;
import com.vmarquezv.dev.assemblyVotes.domain.entity.Session;
import com.vmarquezv.dev.assemblyVotes.repository.SessionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class Timer {
	
	private final SessionRepository sessionRepository;
	
	private final CheckService checkService;
	
	@Scheduled(cron = "* * * ? * *")
	public void log() {
		sessionRepository.findAll().forEach(session -> log.info("[SESSION_ID : "+ session.getId() + " ] -" + "- [STATUS : " + session.getSession_status()  +" ]"));
	}

	@Scheduled(cron = "* * * ? * *")
    public void setSession(){
		List<Session> sessionList = sessionRepository.findAll();
		for(Session session : sessionList) {
			session.setSession_status(statusSession(session));
			sessionRepository.save(session);
		}
		
	}
	
	private SessionStatus statusSession(Session session) {
		if(checkService.closedHour(session.getClosed_on())){
			return SessionStatus.FINALIZED;
		}
		
		else if(checkService.startHour(session.getStarted_on())){
			return SessionStatus.IN_PROGRESS;
		}
		return SessionStatus.NONE;
	}
	
}
