package cn.cdahua.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringUtil {
	private static List<String[]> readFileSplit(String oldString) {
		List<String[]> list = new ArrayList<>();
		String[] str1 = oldString.split("\",\"");
		for (int i = 0; i < str1.length; i++) {
			if(i==(str1.length-1)){
				str1[i] = str1[i].substring(0,str1[i].length()-2);
			}
			String[] str2 = str1[i].split( "\":\"");
			String[] str3 = str2[1].split("\\?");
			list.add(str3);
		}
		return list;
	}
	
	public static Map<String, String> readFileURLSplit(String oldString){
		List<String[]> lists= readFileSplit(oldString);
		Map<String,String> map = new HashMap<String, String>();
		for(String[] str:lists){
			if(str[1].endsWith(".doc")){
				String fileName = str[1].substring(str[1].length()-24, str[1].length()-4);
				str[1] = str[1].replace(".doc", fileName+".html");
			}else if(str[1].endsWith(".docx")){
				String fileName = str[1].substring(str[1].length()-25, str[1].length()-5);
				str[1] = str[1].replace(".docx", fileName+".html");
			}
			map.put(str[0], str[1]);
		}
		return map;
	}
	public static Map<String, String> readFilePathSplit(String oldString){
		List<String[]> lists= readFileSplit(oldString);
		Map<String,String> map = new HashMap<String, String>();
		for(String[] str:lists){
			map.put(str[0], str[1]);
		}
		return map;
	}
}
