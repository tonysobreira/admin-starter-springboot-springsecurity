package com.github.adminfaces.starter.service;

import java.util.List;

import com.github.adminfaces.starter.model.User;

public interface UserService {

	public User findUserByEmail(String email);

	public User saveUser(User user);

	public void deleteUser(User user);

	public List<User> findAllUser();
	
	public List<User> findAllUserHQL();
	
	public List<User> findAllUserNative();

}