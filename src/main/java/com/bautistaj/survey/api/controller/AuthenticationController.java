package com.bautistaj.survey.api.controller;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bautistaj.survey.api.dto.AuthenticationRequest;
import com.bautistaj.survey.api.dto.AuthenticationResponse;
import com.bautistaj.survey.api.model.User;
import com.bautistaj.survey.api.security.Jwt;
import com.bautistaj.survey.api.security.service.UserDetailService;
import com.bautistaj.survey.api.service.impl.UserService;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailService userDetailService;
	@Autowired
	private UserService userService;
	@Autowired
	private Jwt jwtUtil;
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> generateJWT(@RequestBody AuthenticationRequest authenticationRequest){
		try {
			
			LOGGER.debug("Start authentication");
			User user = this.userService.findByUsername(authenticationRequest.getUsername());
			
			if(user == null) {
				Map<String, String> response = new HashMap<>();
				LOGGER.debug("User doesn't exist");	
				response.put("message", MessageFormat.format("User: {0} doesn't exist", authenticationRequest.getUsername()));
				return new ResponseEntity<Map<String, String>>(response, HttpStatus.NOT_ACCEPTABLE);
			}

			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
			UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUsername());
			String  jwt = jwtUtil.generateToken(userDetails);

			LOGGER.debug("Authentication successfully");
			return new ResponseEntity<AuthenticationResponse>(new AuthenticationResponse(jwt), HttpStatus.OK);
			
		} catch (BadCredentialsException exception) {
			LOGGER.debug("Authentication failed");
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
	}
}
