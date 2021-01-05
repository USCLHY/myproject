package com.yc.web.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yc.util.StringUtil;

public class HttpServletRequest implements ServletRequest{
	private String method;
	private String url;
	private InputStream is;
	private BufferedReader reader;
	private Map<String, String> parameter = new HashMap<String, String>();
	private String protocolVersion;
	
	public HttpServletRequest(InputStream is) {
		this.is = is;
		parse();
	}

	@Override
	public void parse() {
		try {
			reader = new BufferedReader(new InputStreamReader(is));
			List<String> headers = new ArrayList<String>();
			String line = null;
			while((line = reader.readLine()) != null && !"".equals(line)) {
				headers.add(line);
			}
			//headers.forEach(System.out::println);
			parseFirstLine(headers.get(0));//解析起始行
			
			parseParameter(headers);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 解析参数
	 * @param headers
	 */
	private void parseParameter(List<String> headers) {
		//如果是GET请求，那么参数只会在起始行中有
		
		//如果是POST请求，那么要获取头部字段中的Content-Length  Content-Type
	}

	/**
	 * 解析起始行
	 * @param str
	 */
	private void parseFirstLine(String str) {
		if(StringUtil.checkNull(str)) {
			return;
		}
		
		String[] arrs = str.split(" ");
		this.method = arrs[0];//请求方式
		if(arrs[1].contains("?")) { //说明有请求参数
			this.url = arrs[1].substring(0, arrs[1].indexOf("?"));
		}else {
			this.url = arrs[1];
		}
		this.protocolVersion = arrs[2];
	}

	@Override
	public String getParameter(String key) {
		return this.parameter.getOrDefault(key, null);
	}

	@Override
	public String getMethod() {
		return this.method;
	}

	@Override
	public String getUrl() {
		return this.url;
	}
	
	public String getProtocalVersion() {
		return protocolVersion;
	}
}
