package com.bautistaj.survey.api.security.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bautistaj.survey.api.repository.IUserRepository;

@Service
public class UserDetailService implements UserDetailsService{
	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.bautistaj.survey.api.model.User user = this.userRepository.findByUsername(username);
		return new User(user.getUsername(), user.getPassword(), new ArrayList<>());
	}

}
