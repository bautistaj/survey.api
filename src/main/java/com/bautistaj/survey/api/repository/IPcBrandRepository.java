package com.bautistaj.survey.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bautistaj.survey.api.model.PcBrand;

public interface IPcBrandRepository extends PagingAndSortingRepository<PcBrand, Long> {
}
