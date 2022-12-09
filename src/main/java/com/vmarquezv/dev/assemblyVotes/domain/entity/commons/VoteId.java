package com.vmarquezv.dev.assemblyVotes.domain.entity.commons;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Embeddable
@Data
@Accessors(chain = true)
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class VoteId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long user_id;
	
	private Long session_id;
	
}
