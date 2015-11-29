package com.qacorner.api.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.core.util.UuidUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class QACornerUtils {

	private static final Gson GSON = new GsonBuilder().setPrettyPrinting()
			.serializeNulls().create();

	public static <T> String toJsonString(T t) {
		return GSON.toJson(t);
	}

	public static String getUID() {
		return StringUtils.remove(UuidUtil.getTimeBasedUuid().toString(), '-');
	}
	
}