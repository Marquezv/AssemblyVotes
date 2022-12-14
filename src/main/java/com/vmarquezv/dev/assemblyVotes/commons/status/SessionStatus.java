package com.vmarquezv.dev.assemblyVotes.commons.status;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum SessionStatus {
	
	NONE("NONE"),
	IN_PROGRESS("IN_PROGRESS"),
	IN_WAITING("IN_WAITING"),
	FINALIZED("FINALIZED");

	private String description;

	public String getDescription() {
	        return description;
	}
}

