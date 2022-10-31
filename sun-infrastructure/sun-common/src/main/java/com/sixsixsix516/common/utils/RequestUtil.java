package com.sixsixsix516.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author SUN
 * @date 8/11/2022
 */
public class RequestUtil {

	public static HttpServletRequest getRequest() {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attributes != null) {
			return attributes.getRequest();
		}
		return null;
	}

	public static void appendAttribute(String key, String value, String separator) {
		HttpServletRequest request = getRequest();
		if (request != null) {
			Object attribute = request.getAttribute(key);
			if (attribute == null) {
				request.setAttribute(key, value);
			} else {
				request.setAttribute(key, attribute + separator + value);
			}
		}
	}
}
