package com.github.adminfaces.starter.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.adminfaces.starter.facade.Facade;
import com.github.adminfaces.starter.model.Authority;
import com.github.adminfaces.starter.model.Bitcoin;
import com.github.adminfaces.starter.model.Book;
import com.github.adminfaces.starter.model.Post;
import com.github.adminfaces.starter.model.Tag;
import com.github.adminfaces.starter.model.User;
import com.github.adminfaces.starter.service.AuthorityService;
import com.github.adminfaces.starter.service.BitcoinService;
import com.github.adminfaces.starter.service.BookService;
import com.github.adminfaces.starter.service.PostService;
import com.github.adminfaces.starter.service.TagService;
import com.github.adminfaces.starter.service.UserService;

@Service("facade")
public class FacadeImpl implements Facade {

	private UserService userService;
	private AuthorityService authorityService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private TagService tagService;
	private PostService postService;
	
	private BookService bookService;
	
	private BitcoinService bitcoinService;

	@Autowired
	public FacadeImpl(@Qualifier("userService") final UserService userService,
			@Qualifier("authorityService") final AuthorityService authorityService,
			BCryptPasswordEncoder bCryptPasswordEncoder,
			@Qualifier("tagService") final TagService tagService,
			@Qualifier("postService") final PostService postService,
			@Qualifier("bookService") final BookService bookService,
			@Qualifier("bitcoinService") final BitcoinService bitcoinService) {
		this.userService = userService;
		this.authorityService = authorityService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		
		this.tagService = tagService;
		this.postService = postService;
		
		this.bookService = bookService;
		
		this.bitcoinService = bitcoinService;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public User findUserByEmail(String email) {
		return userService.findUserByEmail(email);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public User saveUser(User user) {
		return userService.saveUser(user);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public void deleteUser(User user) {
		userService.deleteUser(user);
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public List<Authority> findAllAuthority() {
		return authorityService.findAllAuthority();
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = Exception.class, isolation = Isolation.DEFAULT)
	public Authority findByAuthority(String authority) {
		return authorityService.findByAuthority(authority);
	}

	@Override
	public String encodePassword(CharSequence rawPassword) {
		return bCryptPasswordEncoder.encode(rawPassword);
	}

	@Override
	public List<User> findAllUser() {
		return userService.findAllUser();
	}

	@Override
	public Authority findAuthorityById(Integer id) {
		return authorityService.findById(id);
	}

	@Override
	public List<User> findAllUserHQL() {
		return userService.findAllUserHQL();
	}

	@Override
	public List<User> findAllUserNative() {
		return userService.findAllUserNative();
	}

	@Override
	public Tag saveTag(Tag tag) {
		return tagService.save(tag);
	}

	@Override
	public List<Tag> findAllTag() {
		return tagService.findAll();
	}

	@Override
	public Tag findTagById(Long id) {
		return tagService.findById(id);
	}

	@Override
	public Post savePost(Post post) {
		return postService.save(post);
	}

	@Override
	public List<Post> findAllPost() {
		return postService.findAll();
	}

	@Override
	public Post findById(Long id) {
		return postService.findById(id);
	}

	@Override
	public Book save(Book book) {
		return bookService.save(book);
	}

	@Override
	public void delete(Book book) {
		bookService.delete(book);
	}

	@Override
	public Book findById(Integer id) {
		return bookService.findById(id);
	}

	@Override
	public List<Book> findAll() {
		return bookService.findAll();
	}

	@Override
	public Bitcoin save(Bitcoin bitcoin) {
		return bitcoinService.save(bitcoin);
	}

	@Override
	public void delete(Bitcoin bitcoin) {
		bitcoinService.delete(bitcoin);
	}

	@Override
	public Bitcoin findBitcoinById(Integer id) {
		return bitcoinService.findById(id);
	}

	@Override
	public List<Bitcoin> findAllBitcoin() {
		return bitcoinService.findAll();
	}

	@Override
	public Authority save(Authority authority) {
		return authorityService.save(authority);
	}

}
