package cn.cdahua.util;

import java.util.Properties;

import com.opensymphony.xwork2.ActionContext;

public class ActionUtils {
	public static final String REDIRECT = "redirect";
	public static final String SUCCESS = "success";
	public static final String INDEX = "index";
	public static final String INPUT = "input";
	public static final String LOGININPUT = "logininput";
	public static final String REGISTER = "register";
	
	public static boolean isEmpty(String str) {
		if(str==null||"".equals(str.trim())) {
			return true;
		}
		return false;
	}
	
	public static void setUrl(String url) {
		ActionContext.getContext().put("url", url);
	}
	
	/**
	 * 获取用户可以访问的功能
	 * @return
	 */
	public static String[] getUserAuth() {
		Properties prop = PropertiesUtil.getAuthProp();
		String user = prop.getProperty("author");
		return user.split(",");
	}
	
	/**
	 * 获取用户不能访问的功能
	 * @return
	 */
	public static String[] getUserNotAuth() {
		Properties prop = PropertiesUtil.getAuthProp();
		return (prop.getProperty("admin")).split(",");
	}
	
	public static boolean checkUrl(String action) {
		for(String url:getUserAuth()) {
			if(action.startsWith(url)) {
				return true;
			}
		}
		for(String url:getUserNotAuth()) {
			if(action.startsWith(url)) {
				return false;
			}
		}
		return true;
	}
}
