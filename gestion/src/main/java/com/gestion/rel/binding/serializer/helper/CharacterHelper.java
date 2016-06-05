package com.gestion.rel.binding.serializer.helper;

import javax.servlet.http.HttpServletRequest;

import com.gestion.rel.utils.UrlPathConstants;

public class CharacterHelper extends ObjectHelper {

	private static CharacterHelper instance = null;

	public CharacterHelper(HttpServletRequest request) {
		this.request = request;
	}

	public static CharacterHelper getInstance(HttpServletRequest request) {
		if (instance == null) {
			instance = new CharacterHelper(request);
		}
		return instance;
	}

	@Override
	protected String[] getLinkTokkens() {
		return new String[] { UrlPathConstants.GAME, UrlPathConstants.CHARACTER };
	}
}
