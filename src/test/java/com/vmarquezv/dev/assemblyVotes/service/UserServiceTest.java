package com.vmarquezv.dev.assemblyVotes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.vmarquezv.dev.assemblyVotes.domain.entity.User;
import com.vmarquezv.dev.assemblyVotes.domain.request.UserRequestDTO;
import com.vmarquezv.dev.assemblyVotes.domain.response.UserResponseDTO;
import com.vmarquezv.dev.assemblyVotes.exceptions.DataIntegratyViolationException;
import com.vmarquezv.dev.assemblyVotes.exceptions.ObjectNotFoundException;
import com.vmarquezv.dev.assemblyVotes.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {

	LocalDateTime date = LocalDateTime.now();

	private static final long ID = (long) 1;
	private static final String USERNAME = "Vinicius";
	private static final LocalDateTime CREATED_ON = LocalDateTime.of(2022, 12, 10, 18, 22, 30);
	private static final String CPF = "490.605.290-81";

	@InjectMocks
	private UserService userService;

	@Mock
	private UserRepository repository;

	private User user;

	private UserResponseDTO userRes;

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

		User res = userService.findById(ID);

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

		UserResponseDTO res = userService.findById(ID).toResponse();

		assertNotNull(res);
		assertEquals(UserResponseDTO.class, res.getClass());
		assertEquals(ID, res.getUser_id());
		assertEquals(USERNAME, res.getUsername());
		assertEquals(CREATED_ON, res.getCreated_on());
	}

	@Test
	void whenFindByIdThenReturnAnObjectNotFoundException() {
		Mockito.when(repository.findById(Mockito.anyLong()))
				.thenThrow(new ObjectNotFoundException("USER_ID - NOT_FOUND"));

		try {
			userService.findById(ID);
		} catch (Exception err) {
			assertEquals(ObjectNotFoundException.class, err.getClass());
			assertEquals("USER_ID - NOT_FOUND", err.getMessage());
		}

	}

	@Test
	void whenFindAllThenReturnAnListOfUsers() {
		Mockito.when(repository.findAll().stream().map(user -> user.toResponse()).toList())
				.thenReturn(List.of(userRes));

		List<UserResponseDTO> res = List.of(userRes);
		assertNotNull(res);
		assertEquals(1, res.size());
		assertEquals(UserResponseDTO.class, res.get(0).getClass());
		assertEquals(ID, res.get(0).getUser_id());
		assertEquals(USERNAME, res.get(0).getUsername());
		assertEquals(CREATED_ON, res.get(0).getCreated_on());

	}

	@Test
	void whenCreateThenReturnSuccess() throws Exception {
		Mockito.when(repository.save(any())).thenReturn(user);

		UserResponseDTO res = userService.insert(userReq);

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
			userService.insert(userReq);
		}catch (Exception err) {
			assertEquals(DataIntegratyViolationException.class, err.getClass());
			assertEquals("CPF - IN USE", err.getMessage());
		}
		
	}
	
	
	private void startUser() {
		user = new User(ID, USERNAME, CPF, CREATED_ON);
		userRes = new UserResponseDTO(ID, USERNAME, CREATED_ON);
		userReq = new UserRequestDTO(ID, USERNAME, CPF, CREATED_ON, user);

		optionalUser = Optional.of(new User(ID, USERNAME, CPF, CREATED_ON));
	}
}
