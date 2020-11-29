package com.bautistaj.survey.api.security.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bautistaj.survey.api.repository.IUserRepository;

@Service
public class UserDetailService implements UserDetailsService{
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.bautistaj.survey.api.model.User user = this.userRepository.findByUsernameAndActive(username, true);
		LOGGER.debug("Username "+ user.getUsername());
		LOGGER.debug("Password "+ user.getPassword());
		return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}

}
