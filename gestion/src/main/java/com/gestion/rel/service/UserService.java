package com.gestion.rel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.gestion.rel.domain.User;
import com.gestion.rel.repository.UserRepository;
import com.github.leleuj.ss.oauth.client.authentication.OAuthAuthenticationToken;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User getOwnUser() {
		return getUserById(((OAuthAuthenticationToken) SecurityContextHolder.getContext().getAuthentication())
				.getUserProfile().getId());
	}

	public User getUserById(String id) {
		return userRepository.getUserById(id);
	}

}
