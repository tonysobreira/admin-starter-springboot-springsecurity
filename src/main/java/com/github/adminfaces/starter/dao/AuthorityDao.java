package com.github.adminfaces.starter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.adminfaces.starter.model.Authority;

@Repository("authorityDao")
public interface AuthorityDao extends JpaRepository<Authority, Integer> {

	Authority findByAuthority(String authority);

}
