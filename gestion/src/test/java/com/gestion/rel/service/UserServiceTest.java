package com.gestion.rel.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.gestion.rel.domain.User;
import com.gestion.rel.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	@Test
	public void getUser() {
		String id = "AAS";
		User expected = new User();
		expected.setId(id);
		Mockito.when(userRepository.getUserById(id)).thenReturn(expected);
		User actual = userService.getUserById(id);
		assertNotNull(actual);
		assertEquals(expected, actual);
	}

}
