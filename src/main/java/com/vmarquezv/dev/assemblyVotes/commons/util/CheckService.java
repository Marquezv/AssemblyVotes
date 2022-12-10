package com.vmarquezv.dev.assemblyVotes.commons.util;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.vmarquezv.dev.assemblyVotes.exceptions.StatusArgumentExceptionException;

@Service
public class CheckService {

	
	@SuppressWarnings("deprecation")
	public boolean startHour(Date started_on) {
		Date actualDate = new Date(System.currentTimeMillis());
		started_on.setHours(started_on.getHours() + 1);

		if(started_on.equals(actualDate) || started_on.after(actualDate)) {
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public boolean closedHour(Date closed_on) {
		Date actualDate = new Date(System.currentTimeMillis());
		closed_on.setHours(closed_on.getHours() + 1);
		if(closed_on.equals(actualDate) || actualDate.after(closed_on)) {
			return true;
		}
		return false;
	}
	
	@SuppressWarnings("unused")
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
