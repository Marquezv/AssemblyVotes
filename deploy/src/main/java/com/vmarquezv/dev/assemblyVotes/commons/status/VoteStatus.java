package com.vmarquezv.dev.assemblyVotes.commons.status;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum VoteStatus {
	
	NONE("NONE"),
	POSITIVE("OPPEND"),
	NEGATIVE("NEGATIVE");
	
	private String description;

	public String getDescription() {
	        return description;
	}
}

