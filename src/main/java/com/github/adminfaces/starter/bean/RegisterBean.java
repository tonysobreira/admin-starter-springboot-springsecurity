package com.github.adminfaces.starter.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.github.adminfaces.starter.model.Authority;
import com.github.adminfaces.starter.model.User;

@Named
@ViewScoped
public class RegisterBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 630918717499272527L;
	
	private User user;
	private String email;
	private String password1;
	private String password2;

	private String name;
	private String lastName;
	private List<Authority> authorityList;
	private List<Authority> selectedAuthorities;

	@PostConstruct
	public void init() {
		user = new User();
	}

	public String register() {

		user.setEmail(this.email);

		String encryptedPassword = getFacade().encodePassword(this.password1);
		user.setPassword(encryptedPassword);

		user.setAccountNonExpired(Boolean.FALSE);
		user.setAccountNonLocked(Boolean.FALSE);
		user.setCredentialsNonExpired(Boolean.FALSE);
		user.setEnabled(Boolean.TRUE);

		user.setName(this.name);
		user.setLastName(this.lastName);

//		String role = "ROLE_ADMIN";
//		Role userRole = getFacade().findByRole(role);
//		user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));

		Set<Authority> authorities = new HashSet<Authority>();
		Authority authority = null;

		for (Authority a : this.selectedAuthorities) {
			authority = getFacade().findAuthorityById(a.getId());
			authorities.add(authority);
		}

		user.setAuths(authorities);

		getFacade().saveUser(user);

		return "startpage?faces-redirect=true";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAuthorityList(List<Authority> authorityList) {
		this.authorityList = authorityList;
	}

	public List<Authority> getAuthorityList() {

		if (authorityList == null) {
			authorityList = getFacade().findAllAuthority();
		}

		return authorityList;
	}

	public List<Authority> getSelectedAuthorities() {
		return selectedAuthorities;
	}

	public void setSelectedAuthorities(List<Authority> selectedAuthorities) {
		this.selectedAuthorities = selectedAuthorities;
	}

}
