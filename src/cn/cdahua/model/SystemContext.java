package cn.cdahua.model;

import java.util.Map;

public class SystemContext {
	
	private static ThreadLocal<Integer> pageOffSet = new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> pageSize = new ThreadLocal<Integer>();
	private static ThreadLocal<Map<Integer,String>> adminType = new ThreadLocal<Map<Integer,String>>();
	
	public static Map<Integer,String> getAdminType(){
		return adminType.get();
	}
	public static void setAdminType(Map<Integer,String> map){
		adminType.set(map);
	}
	
	public static void removeAdminType(){
		adminType.remove();
	}
	
	
	public static int getPageOffSet() {
		return pageOffSet.get();
	}
	public static void setPageOffSet(int _pageOffSet) {
		pageOffSet.set(_pageOffSet);
	}
	public static int getPageSize() {
		return pageSize.get();
	}
	public static void setPageSize(int _pageSize) {
		pageSize.set(_pageSize);
	}
	
	public static void removePageOffset() {
		pageOffSet.remove();
	}
	
	public static void removePageSize() {
		pageSize.remove();
	}
	
	
}
