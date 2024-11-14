package com.workintech.s20challenge;

import com.workintech.s20challenge.controller.UserController;
import com.workintech.s20challenge.entity.User;
import com.workintech.s20challenge.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class S20challengeApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private UserRepository userRepository;

	@Test
	void testFindByUsername() {
		Optional<User> user = userRepository.findByUsername("tayfunsenturk");
		assertTrue(user.isPresent());
		assertEquals("tayfunsenturk", user.get().getUsername());
	}


	@Test
	void testUserService() {

		User user = new User();
		user.setUsername("testUser");
		user.setEmail("test@user.com");
		userRepository.save(user);

		User savedUser = userRepository.findByUsername("testUser").orElseThrow();
		assertEquals("testUser", savedUser.getUsername());
		assertEquals("test@user.com", savedUser.getEmail());

		userRepository.delete(user);
	}


	@Autowired
	private UserController userController;

	@Test
	void testGetUser() {
		Long userId = 1L;
		ResponseEntity<User> response = userController.getUserById(userId);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.hasBody());
		assertEquals(userId, response.getBody().getId());
	}


}
