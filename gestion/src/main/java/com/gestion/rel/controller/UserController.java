package com.gestion.rel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.rel.domain.User;
import com.gestion.rel.service.UserService;
import com.gestion.rel.utils.UrlPathConstants;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/" + UrlPathConstants.USER, method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public User getOwn() {
		return userService.getOwnUser();
	}

	@RequestMapping(value = "/" + UrlPathConstants.USER + "/{id}", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	public User get(@PathVariable("id") String id) {
		return userService.getUserById(id);
	}
}
