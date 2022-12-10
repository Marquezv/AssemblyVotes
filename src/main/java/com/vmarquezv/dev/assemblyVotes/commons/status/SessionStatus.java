package com.vmarquezv.dev.assemblyVotes.commons.status;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum SessionStatus {
	
	NONE("NONE"),
	IN_PROGRESS("IN_PROGRESS"),
	FINALIZED("FINALIZED");

	private String description;

	public String getDescription() {
	        return description;
	}
}

