package com.github.adminfaces.starter.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.adminfaces.starter.dao.BitcoinDao;
import com.github.adminfaces.starter.model.Bitcoin;
import com.github.adminfaces.starter.service.BitcoinService;

@Service("bitcoinService")
public class BitcoinServiceImpl implements BitcoinService {

	private BitcoinDao bitcoinDao;

	@Autowired
	public BitcoinServiceImpl(BitcoinDao bitcoinDao) {
		this.bitcoinDao = bitcoinDao;
	}

	@Override
	public Bitcoin save(Bitcoin bitcoin) {
		return bitcoinDao.save(bitcoin);
	}

	@Override
	public void delete(Bitcoin bitcoin) {
		bitcoinDao.delete(bitcoin);
	}

	@Override
	public Bitcoin findById(Integer id) {
		Optional<Bitcoin> bitcoin = this.bitcoinDao.findById(id);
		return bitcoin.get();
	}

	@Override
	public List<Bitcoin> findAll() {
		return bitcoinDao.findAll();
	}

}
