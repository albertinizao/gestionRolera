package com.gestion.rel.binding.serializer.helper;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.gestion.rel.utils.UrlPathConstants;

public class UserHelper extends ObjectHelper {

	private static UserHelper instance = null;

	protected UserHelper(HttpServletRequest request) {
		this.request = request;
	}

	public static UserHelper getInstance(HttpServletRequest request) {
		if (instance == null) {
			instance = new UserHelper(request);
		}
		return instance;
	}

	@Override
	protected String[] getLinkTokkens() {
		return new String[] { UrlPathConstants.USER };
	}
}
