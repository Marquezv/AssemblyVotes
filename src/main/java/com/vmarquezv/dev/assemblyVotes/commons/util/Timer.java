package com.vmarquezv.dev.assemblyVotes.commons.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	CheckService checkService;
	
	@Scheduled(cron = "* * * ? * *")
	public void log() {
		sessionRepository.findAll().forEach(session -> log.info("[SESSION_ID : "+ session.getId() + " ] -" + "- [STATUS : " + session.getSession_status()  +" ]"));
	}

	@Scheduled(cron = "* * * ? * *")
    public void setSessionStarted(){
		List<Session> sessionList = sessionRepository.findAll();
		for(Session session : sessionList) {
			if(checkService.hourState(session.getStarted_on())){
				if(!checkService.hourState(session.getClosed_on())) {
					session.setSession_status(SessionStatus.IN_PROGRESS);
				}
			}
			sessionRepository.save(session);
		}
		
	}
	
	@Scheduled(cron = "* * * ? * *")
    public void setSessionClosed(){
		List<Session> sessionList = sessionRepository.findAll();
		for(Session session : sessionList) {
			if(checkService.hourState(session.getClosed_on())){
				session.setSession_status(SessionStatus.FINALIZED);
			}
			sessionRepository.save(session);
		}
		
	}
	
	
}
