package com.hihframework.osplugins.freemarker;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import org.apache.log4j.Logger;

/**
 * <p> Title:国际化处理类：包括所有的Freemarker页面上语言参数的提取</p>
 * <p> Description:解决新闻模板中涉及的属性文件的读值</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public class I18n {
	private static final Logger logger = Logger.getLogger(I18n.class);

	private static Map messagesMap = new HashMap();

	private static Properties localeNames = new Properties();

	private I18n() {
	}

	/**
	 * 加载属性配置文件
	 *
	 */
	private static void loadLocales() {
		InputStream fis = null;
		try {
			fis = I18n.class.getClassLoader().getResourceAsStream(
					"resources/FreeMarker.properties");
			//所有 的地址配置来源于jdbc.properties可以根据具体情况自定义
			localeNames.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
				}
			}
		}
	}

	/**
	 * @see 把属性文件中的值互换到Map中
	 * @param messageName String
	 * @param params Object
	 * @return String
	 */

	public static Map getMessage() {
		loadLocales();
		Set keySet = localeNames.keySet();
		for (Object key : keySet) {
			messagesMap
					.put((String) key, localeNames.getProperty((String) key));
		}
		return messagesMap;

	}

	public static void main(String[] args) {
		//		Map map = I18n.getMessage();
		//		System.out.print(map.get("news.freemarker.path"));
	}

}