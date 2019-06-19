package com.github.adminfaces.starter.service;

import java.util.List;

import com.github.adminfaces.starter.model.Post;

public interface PostService {

	public Post save(Post post);

	public List<Post> findAll();

	public Post findById(Long id);

}
