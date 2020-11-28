package com.bautistaj.survey.api.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bautistaj.survey.api.model.User;

public interface IUserService {
	public Page<User> findAll(Pageable pageable);
	public Optional<User> findById(Long id);
	public void deleteById(Long id);
	public User update(User user);
	public User create(User user);
	public User findByUsername(String username);
}
