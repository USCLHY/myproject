package com.yc.tomcat.core;

public interface ConstantInfo {
	public static String BASE_PATH = ReadConfig.getInstance().getProperty("path");
	public static String DEFAULT_RESOURCE = ReadConfig.getInstance().getProperty("default"); //获取默认页面
	public static String REQUEST_METHOD_GET = "GET";
	public static String REQUEST_METHOD_POST = "POST";
}
