package com.github.adminfaces.starter.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import com.github.adminfaces.starter.model.Authority;

@Named
@ApplicationScoped
public class ApplicationBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 3068945670084089653L;

	private static Map<String, Object> appMap = new HashMap<String, Object>();

//	@PostConstruct
//	public void init() {
//		List<Authority> authList = getFacade().findAllAuthority();
//		Authority auth;
//
//		if (authList.isEmpty()) {
//			auth = new Authority();
//			auth.setAuthority("ROLE_ADMIN");
//
//			getFacade().save(auth);
//
//			auth = new Authority();
//			auth.setAuthority("ROLE_USER");
//
//			getFacade().save(auth);
//		}
//
//	}

	public ApplicationBean() {
		
	}

	public static Map<String, Object> getAppMap() {
		return appMap;
	}

	public static void setAppMap(Map<String, Object> appMap) {
		ApplicationBean.appMap = appMap;
	}

}
