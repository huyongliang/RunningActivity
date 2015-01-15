package firework.hyl.running.common.util;

import java.io.IOException;
import java.util.Properties;

public class GloobalProperties {

	private static Properties properties = new Properties();
	public static final String CURRENT_USER = "current_user";
	public static final String COOKIE_USER_NAME = "userName";
	public static final String COOKIE_USER_PASSWORD = "userPassword";

	public static final String USER_HEADER_DIR = "E:\\workspaces\\eclipse\\jee\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Running_001\\UserHeader\\20150113163353092.jpg";

	static {
		properties = new Properties();
		try {
			properties.load(GloobalProperties.class.getClassLoader()
					.getResourceAsStream("common-config.properties"));
			FileUtils.makeDir(properties.getProperty("user.header.dir"));
			System.out.println("makedir.....");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String get(String key){
		return properties.getProperty(key);
	}
}
