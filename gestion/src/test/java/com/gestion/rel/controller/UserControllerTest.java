package com.gestion.rel.controller;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.gestion.rel.domain.User;
import com.gestion.rel.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@InjectMocks
	private UserController userController;

	@Mock
	private UserService userService;
	
	@Test
	public void getOwnUser(){
		User user = new User();
		user.setId("own");
		Mockito.when(userService.getOwnUser()).thenReturn(user);
		User actual = userController.getOwn();
		assertEquals(user, actual);
	}
	
	@Test
	public void getUserById(){
		String id = "ownId";
		User user = new User();
		user.setId(id);
		Mockito.when(userService.getUserById(id)).thenReturn(user);
		User actual = userController.get(id);
		assertEquals(user, actual);
	}

}
