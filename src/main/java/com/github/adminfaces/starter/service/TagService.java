package com.github.adminfaces.starter.service;

import java.util.List;

import com.github.adminfaces.starter.model.Tag;

public interface TagService {

	public Tag save(Tag tag);

	public List<Tag> findAll();
	
	public Tag findById(Long id);

}
