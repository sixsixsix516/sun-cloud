package com.sixsixsix516.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author SUN
 * @date 2021/11/5
 */
@Configuration
public class EnvUtil implements ApplicationContextAware {

	/**
	 * 当前程序是否是线上环境 true是
	 */
	private static boolean isProd;

	/**
	 * 当前程序是否是测试环境 true是
	 */
	private static boolean isTest;
	/**
	 * 当前程序是否是开发环境 true是
	 */
	private static boolean isDev;

	/**
	 * 当前环境
	 */
	private static String env;

	public static boolean isProd() {
		return isProd;
	}

	public static boolean isTest() {
		return isTest;
	}

	public static boolean isDev() {
		return isDev;
	}

	public static String getEnv() {
		return env;
	}

	@PostConstruct
	public void init() {
		String profile = "default";
		String[] defaultProfiles = applicationContext.getEnvironment().getActiveProfiles();
		if (null != defaultProfiles && defaultProfiles.length > 0) {
			profile = defaultProfiles[0];
		}

		env = profile;

		switch (profile) {
			case "prod":
				isProd = true;
				break;
			case "test":
				isTest = true;
				break;
			case "dev":
				isDev = true;
				break;
			default:
		}
	}

	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		EnvUtil.applicationContext = applicationContext;
	}
}
