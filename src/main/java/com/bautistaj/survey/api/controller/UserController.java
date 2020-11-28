package com.bautistaj.survey.api.controller;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bautistaj.survey.api.model.User;
import com.bautistaj.survey.api.service.IUserService;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	@Autowired
	private IUserService userService;
	@Value("${default.page-size}")
	private Integer DEFAULT_PAGE_SIZE;
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/users/page/{page}")
	public Page<User> index(@PathVariable Integer page) {
		return this.userService.findAll(PageRequest.of(page, DEFAULT_PAGE_SIZE));
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> create(@RequestBody User user) {
		user.setCreatedAt(new Date());
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		User newUser = this.userService.create(user);
		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
	}
	
	@PutMapping("/users")
	public ResponseEntity<?> update(@RequestBody User user) {
		Map<String, String> response = new HashMap<>();
		
		Optional<User> userToUpdate = this.userService.findById(user.getId());
		
		if(!userToUpdate.isPresent()) {
			LOGGER.debug(String.format("User with id:{0} no found", user.getId()));
			response.put("message", MessageFormat.format("User with id:{0} no found", user.getId()));
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
		}
		
		user.setUpdatedAt(new Date());
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		User userUpdated = this.userService.create(user);
		
		return new ResponseEntity<User>(userUpdated, HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, String> response = new HashMap<>();
		
		if(this.userService.findById(id).isPresent()) {
			
			LOGGER.debug(MessageFormat.format("Deleting user with id {0}", id));
			this.userService.deleteById(id);
			
		} else {
			
			LOGGER.debug(String.format("User with id:{0} no found", id));
			response.put("message", MessageFormat.format("User with id:{0} no found", id));
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
			
		}
		
		response.put("message", MessageFormat.format("User with id:{0} was deleted", id));
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
	}
}
