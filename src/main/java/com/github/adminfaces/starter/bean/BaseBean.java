package com.github.adminfaces.starter.bean;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.adminfaces.starter.facade.Facade;

public abstract class BaseBean {

	@Autowired
	private Facade facade;

	public Facade getFacade() {
		return facade;
	}

}
