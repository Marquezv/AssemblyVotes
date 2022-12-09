package com.vmarquezv.dev.assemblyVotes.commons.status;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum SurveyStatus {
	
	NONE("NONE"),
	OPPEND("OPPEND"),
//	IN_PROGRESS("IN_PROGRESS"),
	CLOSED("CLOSED");
//	FINALIZED("FINALIZED");
	
	private String description;

	public String getDescription() {
	        return description;
	    }
}

