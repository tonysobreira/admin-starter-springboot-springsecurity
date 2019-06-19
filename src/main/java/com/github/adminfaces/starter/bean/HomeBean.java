package com.github.adminfaces.starter.bean;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.github.adminfaces.starter.model.Authority;
import com.github.adminfaces.starter.model.User;

@Named
@SessionScoped
public class HomeBean extends BaseBean {

	private String firstName = "";
	private String lastName = "";
	
	private List filteredUsers;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public List<User> getUserList() {
		List<User> userList = getFacade().findAllUser();
		
		User user = userList.get(0);
		
		for (GrantedAuthority authority : user.getAuthorities()) {
			System.out.println(authority.getAuthority());
		}
		
		return userList;
	}
	
	public List<String> getUserAuthorities(User user) {
		List<String> authoritiesString = new ArrayList<String>();
		
		for (GrantedAuthority authority : user.getAuthorities()) {
			authoritiesString.add(authority.getAuthority());
		}
		
		return authoritiesString;
	}

	public String showGreeting() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		authentication.getCredentials();
		return firstName + " " + lastName + "<br />" + "Hello " + authentication.getName() + "!";
	}
	
	public String back() {
		return "login";
	}
	
	public String getLoggedUser() {
		org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user.getUsername();
	}
	
	public List<User> testEntityManagerHQL() {
		List<User> list = getFacade().findAllUserHQL();
		
		for (User user : list) {
			System.out.println(user);
		}
		
		return list;
	}
	
	public List<User> testEntityManagerNative() {
		List<User> list = getFacade().findAllUserNative();
		
		for (User user : list) {
			System.out.println(user);
		}
		
		return list;
	}

	public List getFilteredUsers() {
		return filteredUsers;
	}

	public void setFilteredUsers(List filteredUsers) {
		this.filteredUsers = filteredUsers;
	}
	
}
