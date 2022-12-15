package com.vmarquezv.dev.assemblyVotes.commons.util;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.vmarquezv.dev.assemblyVotes.exceptions.StatusArgumentExceptionException;

@Service
public class CheckService {

	
	public boolean hourState(LocalDateTime started_on) {
		LocalDateTime date = LocalDateTime.now();

	  //After
	    if(date.isAfter(started_on)) {
	    		return true;
	    //Before
	      } else if(date.isBefore(started_on)) {
	    	 	return false;
	   //Equals
	      } else if(date.equals(started_on)) {
		    	return true;
	      }
		return false;
	}
	
	
	public boolean accessStatus(int accessStatus) {
		switch (accessStatus){
		case 1: {
			return false;
		}
		case 2: {
			return true;
		}
		default:
			throw new StatusArgumentExceptionException("Unexpected value: " + accessStatus);
		}
	}
	
}
