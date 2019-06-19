package com.github.adminfaces.starter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.adminfaces.starter.model.Tag;

@Repository("tagDao")
public interface TagDao extends JpaRepository<Tag, Long> {

}
