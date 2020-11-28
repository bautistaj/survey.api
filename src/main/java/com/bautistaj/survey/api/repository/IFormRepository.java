package com.bautistaj.survey.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bautistaj.survey.api.model.Form;

public interface IFormRepository extends PagingAndSortingRepository<Form, Long> {

}
