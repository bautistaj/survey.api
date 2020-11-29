package com.bautistaj.survey.api.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bautistaj.survey.api.model.Survey;

public interface IFormService {
	public Page<Survey> findAll(Pageable pageable);
	public Optional<Survey> findById(Long id);
	public void deleteById(Long id);
	public Survey update(Survey form);
	public Survey create(Survey form);
}
