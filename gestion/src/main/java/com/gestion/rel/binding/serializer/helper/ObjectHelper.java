package com.gestion.rel.binding.serializer.helper;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public abstract class ObjectHelper {

	protected HttpServletRequest request;

	public Map<String, Object> getMap(Object... var) {
		final Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("id", var[var.length - 1]);
		userMap.put("link", getLink(var));
		return userMap;
	}

	protected abstract String[] getLinkTokkens();

	public String getLink(Object... var) {
		return getLinkWithTokken(getLinkTokkens(), var);
	}
	
	protected String getLinkWithTokken(String[] tokkens, Object... var){
		StringBuilder sb = new StringBuilder(request.getContextPath());
		for (int i = 0; i < tokkens.length; i++) {
			sb.append("/").append(tokkens[i]);
			if (var != null && i<var.length) {
				sb.append("/").append(var[i]);
			}
		}
		return sb.toString();
		
	}
}
