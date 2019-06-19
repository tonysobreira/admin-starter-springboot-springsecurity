package com.github.adminfaces.starter.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.adminfaces.starter.dao.AuthorityDao;
import com.github.adminfaces.starter.model.Authority;
import com.github.adminfaces.starter.service.AuthorityService;


@Service("authorityService")
public class AuthorityServiceImpl implements AuthorityService {

	private AuthorityDao authorityDao;

	@Autowired
	public AuthorityServiceImpl(AuthorityDao authorityDao) {
		this.authorityDao = authorityDao;
	}

	@Override
	public List<Authority> findAllAuthority() {
		return authorityDao.findAll();
	}

	public Authority findByAuthority(String authority) {
		return authorityDao.findByAuthority(authority);
	}

	@Override
	public Authority findById(Integer id) {
		Authority authority = null;
		
		Optional<Authority> optionalRole = authorityDao.findById(id);
		
		if (optionalRole.isPresent()) {
			authority = optionalRole.get();
		}
		
		return authority;
	}

}
