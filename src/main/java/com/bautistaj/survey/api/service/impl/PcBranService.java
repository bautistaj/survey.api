package com.bautistaj.survey.api.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bautistaj.survey.api.model.PcBrand;
import com.bautistaj.survey.api.repository.IPcBrandRepository;
import com.bautistaj.survey.api.service.IPcBrandService;

@Service
public class PcBranService implements IPcBrandService{
	@Autowired
	private IPcBrandRepository pcBrandRepository;
	
	@Override
	public Page<PcBrand> findAll(Pageable pageable) {
		return this.pcBrandRepository.findAll(pageable);
	}

	@Override
	public Optional<PcBrand> findById(Long id) {
		return this.pcBrandRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		this.pcBrandRepository.deleteById(id);		
	}

	@Override
	public PcBrand update(PcBrand pcBrand) {
		return this.pcBrandRepository.save(pcBrand);
	}

	@Override
	public PcBrand create(PcBrand pcBrand) {
		return this.pcBrandRepository.save(pcBrand);
	}

}
