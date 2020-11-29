package com.bautistaj.survey.api.controller;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bautistaj.survey.api.model.PcBrand;
import com.bautistaj.survey.api.service.IPcBrandService;

@RestController
@RequestMapping("/api/v1")
public class PcBrandController {
	@Autowired
	private IPcBrandService pcBrandService;
	@Value("${default.page-size}")
	private Integer DEFAULT_PAGE_SIZE;
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	
	@GetMapping("/pcbrands/page/{page}")
	public Page<PcBrand> index(@PathVariable Integer page) {
		return this.pcBrandService.findAll(PageRequest.of(page, DEFAULT_PAGE_SIZE));
	}
	
	@GetMapping("/pcbrands")
	public List<PcBrand> findAll() {
		return this.pcBrandService.findAll();
	}
	
	@DeleteMapping("/pcbrands/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, String> response = new HashMap<>();
		
		if(this.pcBrandService.findById(id).isPresent()) {
			
			LOGGER.debug(MessageFormat.format("Deleting brand with id {0}", id));
			this.pcBrandService.deleteById(id);
			
		} else {
			
			LOGGER.debug(MessageFormat.format("Brand with id:{0} no found", id));
			response.put("message", String.format("Brand with id:{0} no found", id));
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
			
		}
		
		response.put("message", MessageFormat.format("Brand with id:{0} was deleted", id));
		return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
	}
	
	@PostMapping("/pcbrands")
	public ResponseEntity<PcBrand> create(@RequestBody PcBrand pcBrand) {
		PcBrand newPcBrand = this.pcBrandService.create(pcBrand);
		return new ResponseEntity<PcBrand>(newPcBrand, HttpStatus.CREATED);
	}
	
	@GetMapping("/pcbrands/{id}")
	public ResponseEntity<?> getBayId(@PathVariable Long id) {
		Map<String, String> response = new HashMap<>();
		Optional<PcBrand> pcBrand = this.pcBrandService.findById(id);
		
		if(!pcBrand.isPresent()) {
			LOGGER.debug(MessageFormat.format("Brand with id:{0} no found", id));
			response.put("message", String.format("Brand with id:{0} no found", id));
			return new ResponseEntity<Map<String, String>>(response, HttpStatus.OK);
		}
		
		return new ResponseEntity<PcBrand>(pcBrand.orElse(null), HttpStatus.CREATED);
	}
}
