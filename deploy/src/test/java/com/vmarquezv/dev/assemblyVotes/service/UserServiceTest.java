package com.vmarquezv.dev.assemblyVotes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.vmarquezv.dev.assemblyVotes.domain.entity.User;
import com.vmarquezv.dev.assemblyVotes.domain.request.UserRequestDTO;
import com.vmarquezv.dev.assemblyVotes.domain.response.UserResponseDTO;
import com.vmarquezv.dev.assemblyVotes.exceptions.DataIntegratyViolationException;
import com.vmarquezv.dev.assemblyVotes.exceptions.ObjectNotFoundException;
import com.vmarquezv.dev.assemblyVotes.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	LocalDateTime date = LocalDateTime.now();

	private static final long ID = (long) 1;
	private static final String USERNAME = "Vinicius";
	private static final LocalDateTime CREATED_ON = LocalDateTime.of(2022, 12, 10, 18, 22, 30);
	private static final String CPF = "490.605.290-81";
	private static final String CPF_USER = "345.422.390-39";
	private static final String PASSWORD = "password";

	@InjectMocks
	private UserService service;

	@Mock
	private UserRepository repository;
	
	@Mock
	private  PasswordEncoder passwordEncoder;
	
	private User user;

	private UserRequestDTO userReq;
	
	private Optional<User> optionalUser;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	@Test
	void whenFindByIdThenReturnAnUser() {
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optionalUser);

		User res = service.findById(ID);

		assertNotNull(res);
		assertEquals(User.class, res.getClass());
		assertEquals(ID, res.getId());
		assertEquals(USERNAME, res.getUsername());
		assertEquals(CPF, res.getCpf());
		assertEquals(CREATED_ON, res.getCreated_on());
	}

	@Test
	void whenFindByIdThenReturnAnUserResponse() {
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(optionalUser);

		UserResponseDTO res = service.findById(ID).toResponse();

		assertNotNull(res);
		assertEquals(UserResponseDTO.class, res.getClass());
		assertEquals(ID, res.getUser_id());
		assertEquals(USERNAME, res.getUsername());
		assertEquals(CREATED_ON, res.getCreated_on());
	}

	@Test
	void whenFindByIdThenReturnAnObjectNotFoundException() {
		try {
			service.findById(ID);
		} catch (Exception err) {
			assertEquals(ObjectNotFoundException.class, err.getClass());
			assertEquals("USER_ID - NOT_FOUND", err.getMessage());
		}

	}

	@Test
	void whenCreateThenReturnSuccess() throws Exception {
		Mockito.when(repository.save(any())).thenReturn(user);

		UserResponseDTO res = service.insert(userReq);

		assertNotNull(res);
		assertEquals(UserResponseDTO.class, res.getClass());
		assertEquals(ID, res.getUser_id());
		assertEquals(USERNAME, res.getUsername());
		assertEquals(CREATED_ON, res.getCreated_on());

	}
	
	@Test
	void whenCreateThenReturnDataIntegratyViolationException() throws Exception {
		Mockito.when(repository.findByCpf(anyString())).thenReturn(optionalUser);
		
		try {
			service.insert(userReq);
		}catch (Exception err) {
			assertEquals(DataIntegratyViolationException.class, err.getClass());
			assertEquals("CPF - IN USE", err.getMessage());
		}
		
	}
	
	
	private void startUser() {
		
		service = new UserService(repository, passwordEncoder);
		
		user = new User(ID, USERNAME, PASSWORD, CPF_USER, CREATED_ON);
		userReq = new UserRequestDTO(ID, USERNAME,PASSWORD, CPF, CREATED_ON);

		optionalUser = Optional.of(new User(ID, USERNAME, PASSWORD, CPF, CREATED_ON));
	}
}
