package com.github.adminfaces.starter.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.adminfaces.starter.dao.PostDao;
import com.github.adminfaces.starter.model.Post;
import com.github.adminfaces.starter.service.PostService;

@Service("postService")
public class PostServiceImpl implements PostService {

	private PostDao postDao;

	@Autowired
	public PostServiceImpl(PostDao postDao) {
		this.postDao = postDao;
	}

	@Override
	public Post save(Post post) {
		return postDao.save(post);
	}

	@Override
	public List<Post> findAll() {
		return postDao.findAll();
	}

	@Override
	public Post findById(Long id) {
		Post post = null;

		Optional<Post> optionalPost = postDao.findById(id);

		if (optionalPost.isPresent()) {
			post = optionalPost.get();
		}

		return post;
	}

}
