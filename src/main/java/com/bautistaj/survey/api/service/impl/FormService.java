package com.bautistaj.survey.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bautistaj.survey.api.model.Form;
import com.bautistaj.survey.api.repository.IFormRepository;
import com.bautistaj.survey.api.service.IFormService;

@Service
public class FormService implements IFormService {
	@Autowired
	private IFormRepository formRepository;
	
	@Override
	public Page<Form> findAll(Pageable pageable) {
		return this.formRepository.findAll(pageable);
	}

	@Override
	public Optional<Form> findById(Long id) {
		return this.formRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		this.formRepository.deleteById(id);
	}

	@Override
	public Form update(Form form) {
		return this.formRepository.save(form);
	}

	@Override
	public Form create(Form form) {
		return this.formRepository.save(form);
	}

}
