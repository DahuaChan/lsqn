package cn.cdahua.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

public class ImageUtil {

	public static List<String> list() {

		String imagePath = ServletActionContext.getRequest().getScheme() + "://"
				+ ServletActionContext.getRequest().getServerName() + ":"
				+ ServletActionContext.getRequest().getServerPort() + ServletActionContext.getRequest().getContextPath()
				+ "/upload/image";
		File[] files = new File(imagePath).listFiles();
		List<String> fileNameList = new ArrayList<String>();
		for(File file:files){
			fileNameList.add(imagePath+"/"+file.getName());
		}
		return fileNameList;
	}
}
