package com.bautistaj.survey.api.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bautistaj.survey.api.model.Survey;

public interface IFormRepository extends PagingAndSortingRepository<Survey, Long> {

}
