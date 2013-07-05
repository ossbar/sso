/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.baseclass.service.impl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hihframework.core.utils.StringHelpers;
import com.hihframework.exception.ServiceException;
import com.hihframework.osplugins.dom4j.XmlObject;
import com.hihframework.osplugins.dom4j.XmlParseUtil;
import com.hihframework.osplugins.dom4j.XmlProperty;
import com.hihsoft.baseclass.constants.Consts;
import com.hihsoft.baseclass.model.BaseEntity;
import com.hihsoft.baseclass.service.InitFrameworkService;

@Service(value="initFrameworkService")
public class InitFrameworkServiceImpl extends BaseServiceImpl implements
		InitFrameworkService {

	private Map objMap;

	private XmlParseUtil xmlUtil;

	private Map voMap;

	// private Document doc;
	public InitFrameworkServiceImpl() {
		this.xmlUtil = new XmlParseUtil();
		this.objMap = new HashMap();
		this.voMap = new HashMap();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cn.rhui.framework.authority.service.FrameworkInitService#initFramework()
	 */
	public void saveOrUpdateInitFramework() throws ServiceException {
		try {
			// SysInitializeFactory(doc);

			Map voMap = initialize();
			Field[] fields = Consts.class.getDeclaredFields();
			// 设置全局属性
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				String name = field.getName();

				if (voMap.get(name) != null) {
					field.set(null, voMap.get(name));
				}
			}

		} catch (ServiceException e) {
			e.printStackTrace();
			throw e;
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 检查系统是否已经初始化数据
	 */
	public boolean getIsFrameworkInitialized() throws DataAccessException {
		String userHql = "from TaclUserinfo us";
		String moduleHql = "from TsysModuleinfo mo";
		String roleHql = "from TaclRole ro";
		if (baseDAO.getDataTotalNum(userHql) == 0
				|| baseDAO.getDataTotalNum(moduleHql) == 0
				|| baseDAO.getDataTotalNum(roleHql) == 0)
			return false;
		else
			return true;
	}

	/**
	 * @param args
	 */

	public void saveSysInitializeFactory(Document doc) throws ServiceException {
		// this.doc = doc;
		try {
			getObjMap(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 得到初始化时所有对象集合
	private void getObjMap(Document doc) throws Exception {
		// Document doc = getDoc();
		// 得到根结点
		Element rootElement = xmlUtil.getRootElement(doc).element("models");
		// 得到所有model集合
		List modelList = xmlUtil.elementList(rootElement);
		Iterator modelIt = modelList.iterator();
		// 得到每一个结点,构建XmlObject对象集合
		// objMap = new HashMap();
		while (modelIt.hasNext()) {
			Element model = (Element) modelIt.next();
			String modelClass = model.attribute("class").getValue();
			// 得到每一个对象
			List objList = xmlUtil.elementList(model);
			Iterator objIt = objList.iterator();
			while (objIt.hasNext()) {
				XmlObject xmlObj = new XmlObject();
				Element object = (Element) objIt.next();
				xmlObj.setObject(object.attribute("id").getValue());
				if (object.attribute("isload") != null) {
					xmlObj.setIsload(object.attribute("isload").getValue());
				}
				xmlObj.setModel(modelClass);
				// 得到对象的所有属性
				xmlObj.setPropertys(getPropertys(object));
				if (objMap.containsKey(xmlObj.getObject())) {
					throw new Exception("id:'" + xmlObj.getObject() + "'重复定义");
				}
				objMap.put(xmlObj.getObject(), xmlObj);
			}
		}
	}

	// 得到对象的所有属性
	public Map getPropertys(Element element) throws Exception {
		List proList = xmlUtil.elementList(element);
		Iterator proIt = proList.iterator();
		Map propertyMap = new HashMap();
		while (proIt.hasNext()) {
			Element proElen = (Element) proIt.next();
			XmlProperty pro = new XmlProperty();
			pro.setName(proElen.attribute("name").getValue());
			if (proElen.attribute("parent") != null) {
				pro.setParent(proElen.attribute("parent").getValue());
			}
			if (proElen.attribute("property") != null) {
				pro.setProperty(proElen.attribute("property").getValue());
			}
			if (proElen.attribute("value") != null) {
				pro.setValue(proElen.attribute("value").getValue());
			}
			propertyMap.put(pro.getName(), pro);
		}
		return propertyMap;
	}

	// //得到文档对象模型
	// public Document getDoc() throws Exception {
	// File file = new FileSystemResource(xmlPath).getFile();
	// Document doc = xmlUtil.readXml(file);
	// return doc;
	// }

	// 初始化
	public Map initialize() throws ServiceException {
		try {
			Iterator objIt = objMap.keySet().iterator();
			while (objIt.hasNext()) {
				// 对象标识
				String objId = (String) objIt.next();
				// 得到XML映射对象
				XmlObject xmlObj = (XmlObject) objMap.get(objId);
				// 如果此对象未实例
				if (xmlObj.isMapping() == false) {
					recursion(xmlObj);
				}
			}
		} catch (ServiceException e) {
			e.printStackTrace();
			throw e;
		}
		return voMap;
	}

	// 递归初始化对象
	private void recursion(XmlObject xmlObj) throws ServiceException {

		BeanWrapper bwTb = null;
		// 对象的全部属性
		Map proMap = xmlObj.getPropertys();
		// 动态加载model对象
		Class objClass = null;
		try {
			objClass = Class.forName(xmlObj.getModel());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BaseEntity vo = null;
		try {
			vo = (BaseEntity) objClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		// 得到model对象的方法
		Method[] methods = objClass.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			String name = method.getName();
			// String.valueOf(b)
			// 如果是set方法并且需要赋值
			if (name.indexOf("set") > -1) {
				String proName = name.substring(name.indexOf("set") + 3,
						name.length());
				String a = proName.substring(0, 1).toLowerCase();
				proName = a + proName.substring(1, proName.length());
				// 如果是需要赋值的属性
				if (proMap.get(proName) != null) {
					XmlProperty pro = (XmlProperty) proMap.get(proName);
					Object obj = new Object();
					// 如果此属性的值来自与其它的对象，就从其它对象中取
					if (pro.getParent() != null) {

						XmlObject xmlObj1 = (XmlObject) objMap.get(pro
								.getParent());
						if (xmlObj1.isMapping() == false) {
							recursion(xmlObj1);
						}
						String proName1 = pro.getProperty();
						// 如果指定了关联的字段，此对象的
						if (proName1 != null) {
							// a = proName1.substring(0, 1).toUpperCase();
							proName1 = "get"
									+ StringHelpers.fistCapital(proName1);
							Method meth = null;
							try {
								meth = voMap.get(xmlObj1.getObject())
										.getClass().getMethod(proName1);
							} catch (SecurityException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (NoSuchMethodException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							try {
								obj = meth
										.invoke(voMap.get(xmlObj1.getObject()));
							} catch (IllegalArgumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} else {
							obj = (BaseEntity) voMap.get(pro.getParent());
						}
					}
					// 从对象中取出
					else {

						obj = pro.getValue();
					}
					bwTb = new BeanWrapperImpl(vo);
					// System.out.println(pro.getName()+","+obj.getClass().getName());
					bwTb.setPropertyValue(pro.getName(), obj);

				}
			}
		}

		// 保存初始化数据 到数据库
		if (xmlObj.getIsload() == null || !xmlObj.getIsload().equals("false")) {
			log.info("vo对象＝＝＝＝＝＝＝＝" + vo.getClass());
			baseDAO.saveOrUpdateVO(vo);

		}
		voMap.put(xmlObj.getObject(), vo);
		xmlObj.setMapping(true);
		objMap.put(xmlObj.getObject(), xmlObj);
	}

}
