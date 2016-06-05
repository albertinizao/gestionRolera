package com.gestion.rel.binding.serializer.helper;

import javax.servlet.http.HttpServletRequest;

import com.gestion.rel.utils.UrlPathConstants;

public class GameHelper extends ObjectHelper {

	private static GameHelper instance = null;

	public GameHelper(HttpServletRequest request) {
		this.request = request;
	}

	public static GameHelper getInstance(HttpServletRequest request) {
		if (instance == null) {
			instance = new GameHelper(request);
		}
		return instance;
	}

	@Override
	protected String[] getLinkTokkens() {
		return new String[] { UrlPathConstants.GAME };
	}
}
