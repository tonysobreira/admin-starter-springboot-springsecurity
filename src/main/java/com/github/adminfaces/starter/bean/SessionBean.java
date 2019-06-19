package com.github.adminfaces.starter.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class SessionBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = -6438905308250758643L;

	private static Map<String, Object> sessionMap = new HashMap<String, Object>();

	public SessionBean() {

	}

	public static Map<String, Object> getSessionMap() {
		return sessionMap;
	}

	public static void setSessionMap(Map<String, Object> sessionMap) {
		SessionBean.sessionMap = sessionMap;
	}

}
