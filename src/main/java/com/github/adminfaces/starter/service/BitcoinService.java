package com.github.adminfaces.starter.service;

import java.util.List;

import com.github.adminfaces.starter.model.Bitcoin;

public interface BitcoinService {

	public Bitcoin save(Bitcoin bitcoin);

	public void delete(Bitcoin bitcoin);

	public Bitcoin findById(Integer id);

	public List<Bitcoin> findAll();
	
}
