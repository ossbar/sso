package com.hihframework.osplugins.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.hihframework.core.utils.DateUtils;

import freemarker.ext.jsp.TaglibFactory;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;

/**
 * <p> Title：利用freemarker创建HTML静态页面的辅助类</p>
 * <p> Description:进一步封装此类，成为javahih核心类成员</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *  @author hihsoft.co.,ltd
 * @version 1.0
 */
public class MakeTemplateFileByFM {
	private static final Logger log = Logger
			.getLogger(MakeTemplateFileByFM.class.getName());

	private static Configuration cfg = null;

	private static MakeTemplateFileByFM instance = null;

	private static String fileNameStr;

	private static Object lock = new Object();

	public MakeTemplateFileByFM(Object obj, FreeMarkerDTO dto,
			String templatePath, String savePath, HttpServletRequest request) {
		String realPath = dto.getRealPath();
		String templatepath = realPath + templatePath;
		/**************************构造文件的存放位置及生成各种类型的文件命名规则 ****************/
		String cDateStr = savePath + File.separator
				+ DateUtils.getDateFormatStr("yyyyMMdd");
		String filePostfix = dto.getFilePostfix();// ".html";
		String path = templatepath + "/" + cDateStr;
		String fileTimeName = DateUtils.getDateFormatStr("yyyyMMddhhmmss");
		String returnFileName = cDateStr + "/" + fileTimeName + filePostfix;
		String fileName = "";
		File newsDir = new File(path);
		if (newsDir.exists()) {// 判断文件夹是否存在
			fileName = path + "/" + fileTimeName + filePostfix;
		} else {
			newsDir.mkdirs();// 没有文件夹则先创建，支持级联创建文件夹
			fileName = path + "/" + fileTimeName + filePostfix;
		}
		try {
			cfg = new Configuration();// 存储全局配置信息并初步解析模板
			cfg.setObjectWrapper(new DefaultObjectWrapper());// 指定动态数据模型
			cfg.setDirectoryForTemplateLoading(new File(templatepath));// 指定加载的数据源
			cfg.setDefaultEncoding("UTF-8");
			cfg.setSharedVariable("contextPath", request.getContextPath());
			// 获取模板
			Template newsTemplate = cfg.getTemplate(dto.getTemplateName());
			newsTemplate.setEncoding("UTF-8");// 设定编码，避免乱码
			Map root = new HashMap();
			root.put(dto.getRootName(), obj);
			root.put("JspTaglibs", new TaglibFactory(request.getSession()   
	                .getServletContext()));   //加载struts标签库
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(fileName), "UTF-8"));// 设定编码，避免乱码
			try {
				newsTemplate.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateModelException e) {
			e.printStackTrace();
		}
		setFileNameStr(returnFileName);//设置返回的文件字符串
	}

	public static MakeTemplateFileByFM getInstance(Object obj, FreeMarkerDTO vo,
			String templatePath, String savePath, HttpServletRequest request) {
		synchronized (lock) {
			if (null == instance) {
				log.info("没有此实例，正在实例化一个freeMarker模板类");
				instance = new MakeTemplateFileByFM(obj, vo, templatePath,
						savePath, request);
			}
			//log.info("有此实例，调用先前的freeMarker模板类");
		}
		return instance;
	}

	public static void main(String[] args) {
		// MakeHtmlByFM makeNews = new MakeHtmlByFM();
	}

	public static String getFileNameStr() {
		return fileNameStr;
	}

	public static void setFileNameStr(String fileNameStr) {
		MakeTemplateFileByFM.fileNameStr = fileNameStr;
	}
}
