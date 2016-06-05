package com.gestion.rel.binding.serializer.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.gestion.rel.utils.UrlPathConstants;

public class RelationHelper extends ObjectHelper {

	private static RelationHelper instance = null;

	public RelationHelper(HttpServletRequest request) {
		this.request = request;
	}

	public static RelationHelper getInstance(HttpServletRequest request) {
		if (instance == null) {
			instance = new RelationHelper(request);
		}
		return instance;
	}

	@Override
	public Map<String, Object> getMap(Object... var) {
		final Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("id", var[var.length - 1]);
		userMap.put("link", getLink(removeFromArray(var,var.length - 1)));
		return userMap;
	}
	
	private Object[] removeFromArray(Object[] from, int indexToRemove){
		List<Object> fromList = new ArrayList<>(Arrays.asList(from));
		fromList.remove(indexToRemove);
		return fromList.toArray();
	}

	@Override
	public String getLink(Object... var) {
		String relationTokken = (String)var[0];
		var = removeFromArray(var, 0);
		String[] tokkensFixed = getLinkTokkens();
		List<String> tokkenList = new ArrayList<>(Arrays.asList(tokkensFixed));
		tokkenList.add(relationTokken);
		String[] tokkens = tokkenList.toArray(new String[]{});
		return getLinkWithTokken(tokkens, var);
	}

	@Override
	protected String[] getLinkTokkens() {
		return new String[] { UrlPathConstants.GAME, UrlPathConstants.CHARACTER };
	}
}
