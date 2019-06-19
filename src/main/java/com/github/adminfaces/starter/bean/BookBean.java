package com.github.adminfaces.starter.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;

import com.github.adminfaces.starter.model.Book;

@Named
@ViewScoped
public class BookBean extends BaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8212040652713752595L;

	private Book book;

	@PersistenceContext
	private EntityManager em;

	@PostConstruct
	public void init() {
		book = new Book();
	}

	public void save() {
		getFacade().save(book);
	}

	public void delete(Book book) {
		getFacade().delete(book);
	}

	public List<Book> list() {
		return getFacade().findAll();
	}

	public void prepareEdit(Book book) {
		this.book = book;
	}

	public void testBasicUsage() {
		AuditReader reader = AuditReaderFactory.get(em);
		Book firstRevision = reader.find(Book.class, 15, 16);
		Book secondRevision = reader.find(Book.class, 15, 17);
		List<Number> listRevision = reader.getRevisions(Book.class, 15);
		
		
	}
	
	public void revisions(Integer bookId) {
		AuditReader reader = AuditReaderFactory.get(em);
		List<Number> listRevision = reader.getRevisions(Book.class, bookId);
		Book revision = null;
		List<Book> bookRevisionList = new ArrayList<Book>();
		
		
		for (Number number : listRevision) {
			System.out.println(number);
			revision = reader.find(Book.class, bookId, number);
			System.out.println(revision);
			Date date = reader.getRevisionDate(number);
			System.out.println(date);
			
			
			bookRevisionList.add(book);
		}
		
		
		
		
		
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
