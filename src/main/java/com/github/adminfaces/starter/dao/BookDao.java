package com.github.adminfaces.starter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.adminfaces.starter.model.Book;

@Repository("bookDao")
public interface BookDao extends JpaRepository<Book, Integer> {

}
