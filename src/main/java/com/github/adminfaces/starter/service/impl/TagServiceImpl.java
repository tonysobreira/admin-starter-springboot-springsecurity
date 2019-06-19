package com.github.adminfaces.starter.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.adminfaces.starter.dao.TagDao;
import com.github.adminfaces.starter.model.Tag;
import com.github.adminfaces.starter.service.TagService;

@Service("tagService")
public class TagServiceImpl implements TagService {

	private TagDao tagDao;

	@Autowired
	public TagServiceImpl(TagDao tagDao) {
		this.tagDao = tagDao;
	}

	@Override
	public Tag save(Tag tag) {
		return tagDao.save(tag);
	}

	@Override
	public List<Tag> findAll() {
		return tagDao.findAll();
	}

	@Override
	public Tag findById(Long id) {
		Tag tag = null;
		
		Optional<Tag> optionalTag = tagDao.findById(id);
		
		if (optionalTag.isPresent()) {
			tag = optionalTag.get();
		}
		
		return tag;
	}

}
