package com.github.adminfaces.starter.bean;

import java.io.Serializable;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import com.github.adminfaces.starter.model.User;

@Named
@SessionScoped
public class AdminBean extends BaseBean implements Serializable {

	@RolesAllowed("ADMIN")
	public void deleteUser(User user) {
		getFacade().deleteUser(user);
	}

}
