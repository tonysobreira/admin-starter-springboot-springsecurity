package com.github.adminfaces.starter.audit;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.envers.RevisionListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class CustomRevisionListener implements RevisionListener {

	public void newRevision(Object revisionEntity) {
		CustomRevisionEntity revision = (CustomRevisionEntity) revisionEntity;
		
		// Username
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		// IP
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) fc.getExternalContext().getRequest();
		
		String ip = request.getHeader("X-FORWARDED-FOR");
		
		if (ip == null) {
			ip = request.getRemoteAddr();
		}
		
		revision.setUsername(username); // for testing
		
		revision.setIp(ip);
	}

}
