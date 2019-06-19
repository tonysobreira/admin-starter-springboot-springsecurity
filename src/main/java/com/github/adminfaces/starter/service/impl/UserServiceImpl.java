package com.github.adminfaces.starter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.adminfaces.starter.dao.UserDao;
import com.github.adminfaces.starter.model.User;
import com.github.adminfaces.starter.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	public User findUserByEmail(String email) {
		return userDao.findByEmail(email);
	}

	public User saveUser(User user) {
		return userDao.save(user);
	}

	@Override
	public void deleteUser(User user) {
		userDao.delete(user);
	}

	@Override
	public List<User> findAllUser() {
		return userDao.findAll();
	}

	@Override
	public List<User> findAllUserHQL() {
		return userDao.findAllUserHQL();
	}

	@Override
	public List<User> findAllUserNative() {
		return userDao.findAllUserNative();
	}

}