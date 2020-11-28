package com.bautistaj.survey.api.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bautistaj.survey.api.model.Form;

public interface IFormService {
	public Page<Form> findAll(Pageable pageable);
	public Optional<Form> findById(Long id);
	public void deleteById(Long id);
	public Form update(Form form);
	public Form create(Form form);
}
