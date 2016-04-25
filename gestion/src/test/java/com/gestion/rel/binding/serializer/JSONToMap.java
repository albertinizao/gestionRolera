package com.gestion.rel.binding.serializer;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JSONToMap {

	public static Map<String, Object> jsonToMap(String jsonString){
		Gson gson = new Gson();
		return gson.fromJson(jsonString, (new HashMap<String, Object>()).getClass());
	}
}
