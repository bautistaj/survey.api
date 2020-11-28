package com.bautistaj.survey.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bautistaj.survey.api.model.User;
import com.bautistaj.survey.api.repository.IUserRepository;
import com.bautistaj.survey.api.service.IUserService;

@Service
public class UserService implements IUserService {
	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public Page<User> findAll(Pageable pageable) {
		return this.userRepository.findAll(pageable);
	}

	@Override
	public Optional<User> findById(Long id) {
		return this.userRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		this.userRepository.deleteById(id);
	}

	@Override
	public User update(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public User create(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		return this.userRepository.findByUsername(username);
	}
}
