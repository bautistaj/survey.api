package com.bautistaj.survey.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bautistaj.survey.api.model.PcBrand;

public interface IPcBrandService {
	public Page<PcBrand> findAll(Pageable pageable);
	public Optional<PcBrand> findById(Long id);
	public void deleteById(Long id);
	public PcBrand update(PcBrand pcBrand);
	public PcBrand create(PcBrand pcBrand);
	public List<PcBrand> findAll();
}
