package com.github.adminfaces.starter.service;

import java.util.List;

import com.github.adminfaces.starter.model.Book;

public interface BookService {

	public Book save(Book book);

	public void delete(Book book);

	public Book findById(Integer id);

	public List<Book> findAll();

}
