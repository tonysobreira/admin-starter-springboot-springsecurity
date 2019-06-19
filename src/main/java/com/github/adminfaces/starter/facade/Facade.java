package com.github.adminfaces.starter.facade;

import java.util.List;

import com.github.adminfaces.starter.model.Authority;
import com.github.adminfaces.starter.model.Bitcoin;
import com.github.adminfaces.starter.model.Book;
import com.github.adminfaces.starter.model.Post;
import com.github.adminfaces.starter.model.Tag;
import com.github.adminfaces.starter.model.User;

public interface Facade {

	public User findUserByEmail(String email);

	public User saveUser(User user);

	public void deleteUser(User user);
	
	public Authority save(Authority authority);

	public List<Authority> findAllAuthority();

	public Authority findAuthorityById(Integer id);

	public Authority findByAuthority(String role);

	public String encodePassword(CharSequence rawPassword);

	public List<User> findAllUser();

	public List<User> findAllUserHQL();
	
	public List<User> findAllUserNative();
	
	//Tag
	
	public Tag saveTag(Tag tag);
	
	public List<Tag> findAllTag();
	
	public Tag findTagById(Long id);
	
	public Post savePost(Post post);
	
	//Post

	public List<Post> findAllPost();

	public Post findById(Long id);
	
	//Book
	
	public Book save(Book book);

	public void delete(Book book);

	public Book findById(Integer id);

	public List<Book> findAll();
	
	//Bitcoin
	
	public Bitcoin save(Bitcoin bitcoin);

	public void delete(Bitcoin bitcoin);

	public Bitcoin findBitcoinById(Integer id);

	public List<Bitcoin> findAllBitcoin();

}
