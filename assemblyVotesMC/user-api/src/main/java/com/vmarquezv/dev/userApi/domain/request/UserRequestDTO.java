package com.vmarquezv.dev.userApi.domain.request;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vmarquezv.dev.userApi.commons.annotation.Cpf;
import com.vmarquezv.dev.userApi.domain.entity.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
	
	private Long user_id;
	
	private String username;
	
	private String password;
	
	@Cpf
	private String cpf;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm:ss")
	private LocalDateTime created_on;
	
	public User build() {
		User user = new User()
				.setUsername(this.username)
				.setPassword(password)
				.setCpf(this.cpf)
				.setCreated_on(this.created_on);
			return user;
	}
	
}
