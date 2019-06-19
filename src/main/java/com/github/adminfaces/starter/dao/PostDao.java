package com.github.adminfaces.starter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.adminfaces.starter.model.Post;

@Repository("postDao")
public interface PostDao extends JpaRepository<Post, Long> {

}
