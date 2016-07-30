package cn.cdahua.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
	private static Properties authProp;
	private static Properties mailProp;

	public static Properties getAuthProp() {
		if (authProp == null)
			authProp = getProp("auth.properties");
		return authProp;
	}

	public static Properties getMailProp() {
		if (mailProp == null)
			mailProp = getProp("mail.properties");
		return mailProp;
	}

	private static Properties getProp(String properties){
		Properties prop = new Properties();
		try {
			prop.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(properties));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
}
