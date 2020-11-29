package com.bautistaj.survey.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bautistaj.survey.api.model.User;

public interface IUserRepository  extends PagingAndSortingRepository<User, Long>{
	public User findByUsernameAndActive(String username, Boolean active);
	public User findByUsername(String username);
}
