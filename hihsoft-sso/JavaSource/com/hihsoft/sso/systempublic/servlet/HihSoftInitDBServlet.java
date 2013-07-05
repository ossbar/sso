package com.hihsoft.sso.systempublic.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hihframework.osplugins.dom4j.XmlParseUtil;
import com.hihsoft.baseclass.service.InitFrameworkService;
import com.hihsoft.sso.business.service.TsysAreacodingService;
import com.hihsoft.sso.business.service.TsysParameterService;
import com.hihsoft.sso.systempublic.constants.Constant;

/**
 * Title:原始框架时，需要系统初始化原始数据 Description:系统启动后需要初始化的数据都在此进行：用户只需要按格式定义xml文件即可
 * 原则上先启动Spring上下文、再调用此初始化类，然后执行spring mvc上下文 
 * Copyright:Copyright (c) 2013
 * Company:hihsoft.co.,ltd
 * 
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public class HihSoftInitDBServlet extends javax.servlet.http.HttpServlet
		implements javax.servlet.Servlet {
	private static Logger log = Logger.getLogger(HihSoftInitDBServlet.class
			.getName());

	private static ApplicationContext ctx = null;
	private static ServletContext context = null;
	private InitFrameworkService initFrameworkService;
	private TsysAreacodingService tsysAreacodingService;
	private TsysParameterService tsysParameterService;

	public HihSoftInitDBServlet() {
		super();

	}

	/**
	 * 目前通过XML文件保存初始化数据，然后随着后台启动时，初始化数据库基础数据
	 */
	public void init() throws ServletException {
		super.init();
		// 取得javahihConfigLocation上下文
		context = getServletContext();
		if (ctx == null) {
			ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext(context);
		}

		initFrameworkService = (InitFrameworkService) ctx
				.getBean("initFrameworkService");
		tsysParameterService = (TsysParameterService) ctx
				.getBean("tsysParameterService");
		tsysAreacodingService = (TsysAreacodingService) ctx
				.getBean("tsysAreacodingService");
		// 检察系统是不是第一次启动，如果是把初始化数据加载至数据库
		// util.getIsFrameworkInitialized(); 2011-03-08 by
		// zhujw:需求变更如果系统已经运行，要初始化其他数据时，不适合，所以去掉此验证
		try {
			// 在web.xml文件中读取配置文件路径，可以是xml文件，可以是文件夹，多个文件之间用‘,’分隔
			String[] folderPaths = HihSoftInitDBServlet.getParamValue(
					Constant.SYS_CONFIGlOCATION_XML_NAME).split(",");
			String path = null;
			// 循环读取文件
			for (int j = 0; j < folderPaths.length; j++) {
				path = HihSoftInitDBServlet.getAbsolutePath(folderPaths[j]);
				File folder = new File(path);
				readFile(folder);
			}
		} catch (Exception io) {
			io.printStackTrace();
			ServletException ex = new ServletException(io.getMessage());
			ex.setStackTrace(io.getStackTrace());
			throw ex;

		}
		// log.info("加载了属性文件没有？" + I18n.getMessage("javahih.about"));
		initSysParam();// modify by zhujw
		// 初始化完成后，不做缓存，交由InitPara插件机制来完成所有的参数加载，由spring来管理
	}

	/**
	 * 判断是否为文件，如果是javahih开头的xml文件,加载xml,如果是目录，递归到目录是找文件
	 * 
	 * @param file
	 */
	private void readFile(File file) throws Exception {
		String name = file.getName();
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				readFile(files[i]);
			}
		} else if (name != null
				&& name.indexOf("hihsoft") == 0
				&& name.substring(name.lastIndexOf(".") + 1, name.length())
						.toUpperCase().equals("XML")) {
			loadFile(file);
		} else {
			log.error("请检查xml文件是否以hihsoft开头或文件是否存在？");
		}

	}

	/**
	 * 加载xml,如果有"import"节点，读取此节点引入的xml文件或文件夹
	 * 
	 * @param file
	 */
	@SuppressWarnings("deprecation")
	private void loadFile(File file) throws Exception {
		XmlParseUtil xmlUtil = new XmlParseUtil();
		Document doc = xmlUtil.readXml(file);
		Element root = doc.getRootElement();
		Iterator it = root.elementIterator();
		String path = file.getPath();
		String dir = path.substring(0, path.lastIndexOf("\\"));
		Element el = null;
		String isLoad = null;
		String type = null;
		String resource = null;
		File file1 = null;
		Document doc1 = null;
		while (it.hasNext()) {
			el = (Element) it.next();
			isLoad = el.attributeValue("isLoad");
			type = el.attributeValue("type");
			resource = el.attributeValue("resource");
			// 如果此节点为"import"节点，当引入的为文件夹时,递归找到xml文件，否则加载文件
			if (el.getName().equals("import") && isLoad != null
					&& isLoad.equals("true")) {
				file1 = new FileSystemResource(dir + "\\" + resource).getFile();
				if (file1.isDirectory()) {
					readFile(file1);
				} else {
					doc1 = xmlUtil.readXml(file1);
					// 如果是初始化框架
					if (el.getName().equals("import") && isLoad != null
							&& isLoad.equals("true") && type != null
							&& type.equals("framework")) {
						initDB(doc1);
						el.setAttributeValue("isLoad", "false");
						doc1.getRootElement().element("models")
								.setAttributeValue("isLoad", "false");
						xmlUtil.writeXml(doc1, file1);

					}
				}
			} else if (el.getName().equals("models")) {
				initDB(doc);
				doc.getRootElement().element("models")
						.setAttributeValue("isLoad", "false");
				// xmlUtil.writeXml(doc1, file1);
			}
			xmlUtil.writeXml(doc, file);
		}
		initFrameworkService.saveOrUpdateInitFramework();
	}

	/**
	 * 初始化框架
	 * 
	 * @param doc
	 */
	private void initDB(Document doc) throws Exception {
		XmlParseUtil xmlUtil = new XmlParseUtil();
		initFrameworkService.saveSysInitializeFactory(doc);
	}

	/**
	 * 初始化加载参数，并缓存
	 * 
	 * @throws ServletException
	 */
	private void initSysParam() throws ServletException {
		// 缓存系统参数
		Map map = new HashMap();
		try {
			tsysParameterService.saveFillParamMap(null);
		} catch (Exception e) {
			ServletException ex = new ServletException();
			ex.setStackTrace(e.getStackTrace());
			throw ex;
		}
		// 地区信息
		try {
			tsysAreacodingService.saveFillParamMap(null);
		} catch (Exception e) {
			ServletException ex = new ServletException();
			ex.setStackTrace(e.getStackTrace());
			throw ex;
		}
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
	}

	/**
	 * 根据初始化参数名返回参数值
	 * 
	 * @param initParamName
	 * @return
	 * @throws Exception
	 */
	public static String getParamValue(String paramName) throws IOException {
		String ParamValue = context.getInitParameter(paramName);
		return ParamValue;
	}

	/**
	 * 返回绝对路径
	 * 
	 * @param RelativePath
	 * @return
	 * @throws Exception
	 */
	public static String getAbsolutePath(String RelativePath)
			throws IOException {
		return context.getRealPath(RelativePath);
	}


		public boolean accept(File dir, String name) {
			boolean a = false;
			if (name != null
					&& name.indexOf("hihsoft") == 0
					&& name.substring(name.lastIndexOf(".") + 1, name.length())
							.toUpperCase().equals("XML")) {
				a = true;
				// 检查下程序目录下是否有此.xml文件，
			}
			return a;
		}
}