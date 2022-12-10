package com.vmarquezv.dev.assemblyVotes.commons.status;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum SurveyStatus {
	
	NONE("NONE"),
	OPPEND("OPPEND"),
	CLOSED("CLOSED");
	
	private String description;

	public String getDescription() {
	        return description;
	    }
}

