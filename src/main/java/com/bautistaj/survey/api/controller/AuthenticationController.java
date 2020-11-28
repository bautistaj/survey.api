package com.bautistaj.survey.api.controller;

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
import com.bautistaj.survey.api.security.Jwt;
import com.bautistaj.survey.api.security.service.UserDetailService;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailService userDetailService;
	@Autowired
	private Jwt jwtUtil;
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> generateJWT(@RequestBody AuthenticationRequest authenticationRequest){
		try {
			
			LOGGER.debug("Start authentication");
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
