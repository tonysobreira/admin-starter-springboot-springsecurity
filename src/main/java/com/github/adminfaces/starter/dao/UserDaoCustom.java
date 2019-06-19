package com.github.adminfaces.starter.dao;

import java.sql.SQLException;
import java.util.List;

import com.github.adminfaces.starter.model.User;

public interface UserDaoCustom {
	public List<User> findAllUserNative();

	public List<User> findAllUserHQL();

	public List<User> findAllUserDataSource() throws SQLException;
}
