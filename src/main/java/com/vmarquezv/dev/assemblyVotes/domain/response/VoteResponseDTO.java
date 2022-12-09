package com.vmarquezv.dev.assemblyVotes.domain.response;

import java.sql.Timestamp;

import com.vmarquezv.dev.assemblyVotes.domain.entity.commons.VoteId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@NoArgsConstructor
@Data
@AllArgsConstructor
public class VoteResponseDTO {
	
	private VoteId vote_id;
	
	private Timestamp voted_in;
}
