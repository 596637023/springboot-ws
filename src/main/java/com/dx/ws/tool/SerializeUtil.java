package com.dx.ws.tool;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SerializeUtil {
	private static ObjectMapper objectMapper;

	/**
	 * json字符串转JavaBean
	 * 
	 * @param jsonStr
	 * @param valueType
	 * @return
	 */
	public static <T> T toObject(String jsonStr, Class<T> valueType) {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		try {
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return objectMapper.readValue(jsonStr, valueType);
		} catch (Exception e) {
			log.error("反序列化异常", e);
		}
		return null;
	}

	/**
	 * json字符串转JavaBean
	 * 
	 * @param jsonStr
	 * @param valueTypeRef
	 * @return
	 */
	public static <T> T toObject(String jsonStr, TypeReference<T> valueTypeRef) {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		try {
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return objectMapper.readValue(jsonStr, valueTypeRef);
		} catch (Exception e) {
			log.error("反序列化异常", e);
		}
		return null;
	}

	/**
	 * 把JavaBean转换为json字符串
	 * 
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		try {
			return objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			log.error("序列化异常", e);
		}
		return null;
	}
}
