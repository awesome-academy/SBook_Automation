package com.sun.edu.util;

import java.util.Properties;

public class Settings {
	private static Properties setting = FileUtil.loadProperties("src/main/resources/settings.properties");

	public static final String WEBDRIVER = "webdriver";
	public static final String WEBDRIVER_PATH = "webdriver-path";
	public static final String URL_LOGIN = "url-login";
	public static final String URL_ADMIN = "url-admin";
	public static final String USER_FILE = "user-file";
	public static final String WSM_LOGIN = "wsm-login";
	public static final String RESOURCE_ROOT = "resource-root";

	public static String getSetting(String key) {
		return setting.getProperty(key);
	}
}
