package com.github.adminfaces.starter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.adminfaces.starter.model.Bitcoin;

@Repository("bitcoinDao")
public interface BitcoinDao extends JpaRepository<Bitcoin, Integer> {

}
