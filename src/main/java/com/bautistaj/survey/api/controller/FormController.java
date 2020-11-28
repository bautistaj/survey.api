package com.bautistaj.survey.api.controller;

import java.text.MessageFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bautistaj.survey.api.model.Form;
import com.bautistaj.survey.api.service.IFormService;

@RestController
@RequestMapping("/api/v1")
public class FormController {
	@Autowired
	private IFormService formService;
	@Value("${default.page-size}")
	private Integer DEFAULT_PAGE_SIZE;
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/forms/page/{page}")
	public Page<Form> index(@PathVariable Integer page) {
		return this.formService.findAll(PageRequest.of(page, DEFAULT_PAGE_SIZE));
	}
	
	@PostMapping("/forms")
	public ResponseEntity<Form> create(@RequestBody Form form) {
		form.setCreatedAt(new Date());
		Form newForm = this.formService.create(form);
		return new ResponseEntity<Form>(newForm, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/forms/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, String> response = new HashMap<>();
		
		if(this.formService.findById(id).isPresent()) {
			
			LOGGER.debug(MessageFormat.format("Deleting form with id {0}", id));
			this.formService.deleteById(id);
			
		} else {
			
			LOGGER.debug(String.format("Form with id:{0} no found", id));
			response.put("message", MessageFormat.format("Form with id:{0} no found", id));
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
			
		}
		
		response.put("message", MessageFormat.format("Form with id:{0} was deleted", id));
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
	}
}
