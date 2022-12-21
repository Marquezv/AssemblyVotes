package com.vmarquezv.dev.userApi.domain.response;

import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Relation(collectionRelation = "users")
@Accessors(chain = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO extends RepresentationModel<UserResponseDTO>{
	
	private Long user_id;
	
	private String username;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private LocalDateTime created_on;
	
	
}
