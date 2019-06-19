package com.github.adminfaces.starter.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.adminfaces.starter.dao.BookDao;
import com.github.adminfaces.starter.model.Book;
import com.github.adminfaces.starter.service.BookService;

@Service("bookService")
public class BookServiceImpl implements BookService {

	private BookDao bookDao;

	@Autowired
	public BookServiceImpl(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	public Book save(Book book) {
		return this.bookDao.save(book);
	}

	@Override
	public void delete(Book book) {
		this.bookDao.delete(book);
	}

	@Override
	public Book findById(Integer id) {
		Optional<Book> book = this.bookDao.findById(id);
		return book.get();
	}

	@Override
	public List<Book> findAll() {
		return this.bookDao.findAll();
	}

}
