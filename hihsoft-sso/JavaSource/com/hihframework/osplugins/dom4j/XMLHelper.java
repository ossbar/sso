/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihframework.osplugins.dom4j;

import java.io.File;

import org.dom4j.Document;
import org.springframework.core.io.FileSystemResource;

/**
 * <p> Title:读取XML的辅助类</p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public class XMLHelper {
	private XmlParseUtil xmlUtil;

	private static String soaPath;

	public static String getSoaPath() {
		return soaPath;
	}

	/**
	 * 
	 * @param soaPath
	 */
	public static void setSoaPath(String soaPath) {
		XMLHelper.soaPath = soaPath;
	}

	// 得到文档对象模型
	public Document getDoc() throws Exception {

		File file = new FileSystemResource(soaPath).getFile();
		Document doc = xmlUtil.readXml(file);
		return doc;
	}

	public static void main(String[] args) throws Exception {
		System.out.println("ttttttttttttttt" + XMLHelper.getSoaPath());
	}
}
