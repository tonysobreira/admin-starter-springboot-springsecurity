package com.github.adminfaces.starter.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.adminfaces.starter.model.User;

@Repository("userDao")
public interface UserDao extends JpaRepository<User, Long>, UserDaoCustom {

	User findByEmail(String email);

}