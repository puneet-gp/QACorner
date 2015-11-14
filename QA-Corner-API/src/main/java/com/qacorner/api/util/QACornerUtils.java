package com.qacorner.api.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class QACornerUtils {

	private static final Gson GSON = new GsonBuilder().setPrettyPrinting()
			.serializeNulls().create();
	
	public static<T> String toJsonString(T t) {
		return GSON.toJson(t);
	}

}
