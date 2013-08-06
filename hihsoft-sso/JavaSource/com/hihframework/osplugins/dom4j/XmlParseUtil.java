/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihframework.osplugins.dom4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import jxl.common.Logger;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * <p> Title:</p>
 * <p> Description:用于解析XML文件的统一封装方法</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *  @author hihsoft.co.,ltd
 * @version 1.0
 */
public class XmlParseUtil {
	
	private Logger log = Logger.getLogger(XmlParseUtil.class);
	//---------------------------以下为创建文件方法---------------------------------------
	/**
	 * 创建一个文档实例，此操作是整个创建修改XML文件的基础。
	 *
	 * @return
	 */
	public Document CreateDocument() {
		Document document = DocumentHelper.createDocument();
		return document;
	}

	/**
	 * 创建XML文档根元素（节点）
	 *
	 * @param document
	 *            XML文档实例
	 * @param rootName要创建的元素名称
	 * @return 返回此根元素的element类型，对此节点的操作都调用此返回值
	 */
	public Element createRootElement(Document document, String rootName) {
		Element element = document.addElement(rootName);
		return element;

	}

	/**
	 * 添加文档类型说明, 如果文档要使用文档类型定义（DTD）文档验证则必须有 Doctype。
	 *
	 * @param document
	 *            XML文档实例
	 * @param rootName
	 *            根节点名称
	 * @param docType
	 *            类型说明字符串
	 */
	public void addDocType(Document document, String rootName, String docType) {
		document.addDocType(rootName, null, docType);
		//<!DOCTYPE catalog SYSTEM "file://c:/Dtds/catalog.dtd">
	}

	/**
	 * 对某一元素添加注释
	 *
	 * @param element
	 *            要添加注释的元素
	 * @param comment
	 *            注释字符串
	 */
	public void addComment(Element element, String comment) {
		element.addComment(comment);
	}

	/**
	 * 在父元素中增加子元素
	 *
	 * @param parentelement要增加子元素的元素名称
	 * @param elementName要增加的子元素名称
	 * @return Element元素类型
	 */
	public Element addElement(Element parentelement, String elementName) {
		Element element = parentelement.addElement(elementName);
		return element;
	}

	/**
	 * 在某个元素中添加属性和属性值
	 *
	 * @param element某个元素节点变量
	 * @param attributeName要添加的属性名
	 * @param attributeValue要添加的属性值
	 */
	public void addAttribute(Element element, String attributeName,
			String attributeValue) {
		element.addAttribute(attributeName, attributeValue);
	}

	/**
	 * 为某一元素节点设置文本值
	 *
	 * @param element
	 *            元素节点变量
	 * @param text
	 *            要设置的文本值
	 */
	public void setText(Element element, String text) {
		element.setText(text);
	}

	/**
	 * 执行XML操作，将内容写入文件，并且格式化此文件，解决中文问题。 所有的创建XML文件操作最后都要调用此方法。
	 *
	 * @param document
	 *            操作的文档对象
	 * @param file
	 *            最终生成的XML文件
	 */
	public void writeXml(Document document, File file) {
		try {
			//此方法可解决中文问题
			OutputFormat format = OutputFormat.createPrettyPrint();
			XMLWriter output = new XMLWriter(new OutputStreamWriter(
					new FileOutputStream(file), "UTF-8"), format);
			output.write(document);
			output.flush();
			output.close();
		} catch (IOException e) {
			log.info(e.getMessage());
		}

	}

	/**
	 * 执行XML操作，并且格式化内容，解决中文问题。 输出到控制台
	 *
	 * @param document
	 *            操作的文档对象
	 * @param file
	 *            最终生成的XML文件
	 */
	public void writeXml(Document document) {
		try {
			//此方法可解决中文问题
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			XMLWriter output = new XMLWriter(System.out, format);
			//XMLWriter output = new XMLWriter(new FileWriter(file));
			output.write(document);
			output.flush();
			output.close();
		} catch (IOException e) {
			log.info(e.getMessage());
		}

	}

	//------------------------------以下为读取文件方法-----------------------------------

	//从文件读取XML，输入文件名，返回XML文档
	public Document readXml(File file) throws MalformedURLException,
			DocumentException {
		SAXReader reader = new SAXReader();
		Document document = reader.read(file);
		//Document document=reader.read(InputStream);
		//Document document=reader.read(url);
		return document;
	}

	/**
	 * 取得ROOT根节点
	 *
	 * @param doc
	 *            读取的XML文档对象
	 * @return ROOT根节点信息
	 */
	public Element getRootElement(Document doc) {
		Element rootElement = doc.getRootElement();
		return rootElement;

	}

	/**
	 * 取得根节点的名字
	 *
	 * @param rootElement
	 *            根节点的元素值
	 * @return 根节点的名字
	 */
	public String getRootName(Element rootElement) {
		String rootName = rootElement.getName();
		return rootName;
	}

	/**
	 * 取得某一节点元素信息。 比如要取得根节点下name元素那么parentelement就是根节点的element,
	 * elementName就是name的element。如果取超过两层下的元素信息， 那就多次调用次方法，一层层往下传。
	 *
	 * @param parentelement
	 *            上一节点元素信息。
	 * @param elementName
	 *            要取的本节点元素信息
	 * @return 本节点元素信息。
	 */
	public Element getElement(Element parentelement, String elementName) {
		Element element = null;
		for (Iterator<?> i = parentelement.elementIterator(elementName); i
				.hasNext();) {
			element = (Element) i.next();
		}
		return element;
	}

	/**
	 * 取得某一节点元素值
	 *
	 * @param element
	 *            节点元素
	 * @return 字符串文本值
	 */
	public String getElementText(Element element) {
		String elementText = element.getText();
		return elementText;
	}

	/**
	 * 获取某一节点某一属性值
	 *
	 * @param element
	 *            节点元素实例
	 * @param attributeName
	 *            元素名
	 * @return 属性值
	 */
	public String getAttributeValue(Element element, String attributeName) {
		Attribute Attribute = element.attribute(attributeName);
		String attributeValue = Attribute.getValue();
		return attributeValue;
	}

	/**
	 * 在文件中查找某一个节点
	 *
	 * @param document
	 *            文档实例对象
	 * @param elementName
	 *            要查找的实例
	 * @return 查找出来的节点对象
	 */
	public Element FindElement(Document document, String elementName) {
		Element root = document.getRootElement();
		Element element = null;
		for (Iterator<?> i = root.elementIterator(elementName); i.hasNext();) {
			element = (Element) i.next();
		}
		return element;
	}

	/**
	 * 获取某一节点的属性和属性值列表
	 *
	 * @param element
	 *            节点对象
	 * @return 属性和属性值列表
	 */
	public List<Object> attributeList(Element element) {
		@SuppressWarnings("unchecked")
		List<Object> list = element.attributes();
		return list;
	}

	public int getElementCount(Element element) {
		int i = 0;
		Iterator<?> iterator = element.elementIterator();
		while (iterator.hasNext()) {
			i++;
			iterator.next();
		}
		return i;
	}

	/**
	 * 取得某个节点的属性数量
	 *
	 * @param element
	 * @return
	 */
	public int getAttributeCount(Element element) {
		int attributeCount = element.attributeCount();
		return attributeCount;
	}

	/**
	 * 获取某一节点的属性和属性值迭代列表
	 *
	 * @param element
	 *            节点对象
	 * @return 属性和属性值列表
	 */
	public Iterator<Object> attributeIterator(Element element) {
		@SuppressWarnings("unchecked")
		Iterator<Object> attrIterator = element.attributeIterator();
		return attrIterator;
	}

	/**
	 * 获取某一节点的所有属性和属性值，返回列表
	 *
	 * @param element
	 *            节点元素
	 * @return 列表值
	 */
	public HashMap<String, String> attributeMap(Element element) {
		HashMap<String, String> attributeHah = new HashMap<String, String>();
		Iterator<?> elemIterator = element.attributeIterator();
		while (elemIterator.hasNext()) {
			Attribute attribute = (Attribute) elemIterator.next();
			String attributeName = attribute.getName();
			String attributeValue = attribute.getValue();
			attributeHah.put(attributeName, attributeValue);
		}
		return attributeHah;
	}

	/**
	 * 取得某一节点所有子节点的迭代
	 *
	 * @param element
	 *            节点元素
	 * @return 子节点的迭代
	 */
	public Iterator<Object> elementIterator(Element element) {
		@SuppressWarnings("unchecked")
		Iterator<Object> elemIterator = element.elementIterator();
		return elemIterator;
	}

	/**
	 * 取得某一节点所有子节点的列表
	 * @param element
	 * @return
	 */
	public List<Object> elementList(Element element) {
		@SuppressWarnings("unchecked")
		List<Object> list = element.elements();
		return list;
	}

	/**
	 * 取得某一节点元素子节点的迭代
	 *
	 * @param element
	 *            节点元素
	 * @param elementName子节点
	 * @return子节点的迭代
	 */
	public Iterator<Object> elementIterator(Element element, String elementName) {
		@SuppressWarnings("unchecked")
		Iterator<Object> elemIterator = element.elementIterator(elementName);
		return elemIterator;
	}

	/**
	 * 获取某个节点的所有子节点的名称列表
	 *
	 * @param element
	 *            查找的节点元素
	 * @return子节点的名称列表
	 */
	public ArrayList<String> getElementNameList(Element element) {
		Iterator<?> elemIterator = element.elementIterator();
		ArrayList<String> list = new ArrayList<String>();
		while (elemIterator.hasNext()) {
			Element childElement = (Element) elemIterator.next();
			list.add(childElement.getName());
		}
		return list;
	}

	/**
	 * 取得某一节点某一属性的属性值
	 *
	 * @param element
	 *            节点元素
	 * @param attributeName
	 *            属性名称
	 * @return 获取的属性值
	 */
	public void setAttributeValue(Element element, String attributeName,
			String attributeValue) {
		Attribute attribute = element.attribute(attributeName);
		attribute.setValue(attributeValue);
	}

	/**
	 * 修改某一节点的名字
	 *
	 * @param element
	 *            要修改的节点
	 * @param elementName
	 *            要设置的节点名称
	 */
	public void setElementName(Element element, String elementName) {
		element.setName(elementName);
	}

	/**
	 *
	 * @param element
	 * @param attributeName
	 */
	public boolean deleteAllAttribute(Element element, String attributeName) {
		Attribute attribute = (Attribute) element.attribute(attributeName);
		boolean deleteFlag = element.remove(attribute);
		return deleteFlag;
	}

	/**
	 * 删除某一节点下的所有属性和属性值
	 *
	 * @param element
	 *            要删除属性的节点
	 */
	public boolean deleteAttribute(Element element) {
		boolean deleteFlag = false;
		Iterator<?> iterator = element.attributeIterator();
		while (iterator.hasNext()) {
			Attribute attribute = (Attribute) iterator.next();
			deleteFlag = element.remove(attribute);
		}
		return deleteFlag;
	}

	//--------------------以下通过XPath方式读取--------------------
	/**
	 * 更改某个属性的值
	 *
	 * @param document
	 *            更改的文档对象
	 * @param attributeName
	 *            要更改的属性名，属性名格式为“//节点名称/@属性名称”
	 *            例如：//article/@level，表示article节点的level属性
	 * @param attributeValue
	 *            设置的属性值
	 */
	public void setAttrValues(Document document, String attributeName,
			String attributeValue) {
		List<?> list = document.selectNodes(attributeName);
		Iterator<?> iter = list.iterator();
		while (iter.hasNext()) {
			Attribute attribute = (Attribute) iter.next();
			attribute.setValue(attributeValue);
		}
	}

	/**
	 * 更改某个元素的值
	 *
	 * @param document更改的文档对象
	 * @param NodeName要更改的元素名的上级节点名，格式为“//父元素名”，
	 *            例如：//article，表示article元素
	 * @param element
	 *            要更改的元素名
	 */
	public void setElementValue(Document document, String NodeName,
			String elementName, String elementValue) {
		List<?> list = document.selectNodes(NodeName);
		Iterator<?> iter = list.iterator();
		while (iter.hasNext()) {
			Element element = (Element) iter.next();
			Iterator<?> iterator = element.elementIterator(elementName);
			while (iterator.hasNext()) {
				Element titleElement = (Element) iterator.next();
				titleElement.setText(elementValue);
			}
		}

	}

	/**
	 * 得到某一节点的所有节点元素。
	 *
	 * @param document
	 *            文档对象
	 * @param nodesName
	 *            节点名称， 格式："//节点名称"，如果是多个节点下的，就一直加“/节点名称",此节点名称是遍历拥有多个子节点的。
	 * @return 此节点下所有节点信息。
	 */
	public List<?> getNodes(Document document, String nodesName) {
		List<?> list = document.selectNodes(nodesName);
		//得到list后，可以通过如下方式来遍历节点
		/*
		 * for (Iterator iter = list.iterator(); iter.hasNext(); ) { Attribute
		 * attribute = (Attribute) iter.next(); //然后操作attribute String url =
		 * attribute.getValue(); }
		 */
		/*
		 * for (Iterator iter = list.iterator(); iter.hasNext(); ) { Element
		 * element = (Element) iter.next(); //然后操作element }
		 */
		return list;
	}

	/**
	 * 得到某一节点的单一节点元素。
	 *
	 * @param document
	 *            文档对象
	 * @param nodesName
	 *            节点名称， 格式："//节点名称"，如果是多个节点下的，就一直加“/节点名称"，
	 *            比如”//foo/bar“，bar节点下面必须只有一个子元素
	 * @return 此节点下单一节点信息。
	 */
	public Node getNode(Document document, String nodesName) {
		Node node = document.selectSingleNode(nodesName);
		return node;
	}

	/**
	 * 得到某一节点的文本值
	 *
	 * @param document
	 *            文档对象
	 * @param nodesName
	 *            节点名称， 格式："//节点名称"，如果是多个节点下的，就一直加“/节点名称"，
	 *            比如”//foo/bar“，bar节点下面必须只有一个子元素
	 * @return 此节点下单一节点信息。
	 */
	public String getNodeText(Node node, String nodeName) {
		String nodeText = node.valueOf(nodeName);
		return nodeText;
	}

	public static void main(String[] args) throws DocumentException,
			MalformedURLException {
		//----------------------解析读取修改XML文件-----------------------------
		/*
		 * XmlParseUtil xp = new XmlParseUtil(); Document doc =
		 * xp.CreateDocument();//创建文档对象 Element rootElement =
		 * xp.createRootElement(doc, "学生信息");//创建根节点 xp.addComment(rootElement,
		 * "学生信息管理");//创建根节点注释 //创建三好学生信息节点 Element goodstudElement =
		 * xp.addElement(rootElement, "三好学生信息"); //创建一般学生信息节点 Element
		 * plainElement = xp.addElement(rootElement, "一般学生信息");
		 * //为三好学生信息节点创建学生子节点 Element studentElement =
		 * xp.addElement(goodstudElement, "学生"); //为学生节点创建性别属性和年龄属性，分别对应男和23的值。
		 * xp.addAttribute(studentElement, "性别", "男");
		 * xp.addAttribute(studentElement, "年龄", "23"); //为学生节点创建姓名子节点 Element
		 * nameElement = xp.addElement(studentElement, "姓名"); //为姓名节点创建钟裔文本值
		 * xp.setText(nameElement, "钟裔"); //为学生节点创建期末考试总分子节点 Element
		 * scoreElement = xp.addElement(studentElement, "期末考试总分");
		 * //为期末考试总分节点创建600文本值 xp.setText(scoreElement, "600"); //为学生节点创建班主任子节点
		 * Element techerElement = xp.addElement(studentElement, "班主任");
		 * //为班主任节点创建王老五文本值 xp.setText(techerElement, "王老五"); //为学生节点创建平时表现子节点
		 * Element actElement = xp.addElement(studentElement, "平时表现");
		 * //为平时表现节点创建年度和班级属性，对应2004和计科2班的值。 xp.addAttribute(actElement, "生活表现",
		 * "优秀"); xp.addAttribute(actElement, "学习表现", "优秀");
		 * xp.addAttribute(actElement, "运动表现", "一般"); Element totalElement =
		 * xp.addElement(actElement, "综合表现"); //为综合表现节点创建良好文本值
		 * xp.setText(totalElement, "良好"); Element pjiaElement =
		 * xp.addElement(actElement, "综合评价"); //为综合评价节点创建良好文本值
		 * xp.setText(pjiaElement, "优秀"); //创建文件名为catalog.xml File file = new
		 * File("c:/catalog.xml"); //写入文件，并格式化，解决中文问题。 xp.writeXml(doc, file);
		 */

		//----------------------解析读取修改XML文件--------------------------
		/*File file = new File("c:/catalog.xml");
		 XmlParseUtil xp = new XmlParseUtil();
		 //创建文档读写对象
		 Document document = xp.readXml(file);
		 //取得ROOT根节点
		 Element rootElement = xp.getRootElement(document);
		 //取得根节点名称
		 String rootName = xp.getRootName(rootElement);
		 log.info(rootName);
		 //      用列表的方式获取根节点下所有子节点的名称列表
		 ArrayList elementNameList = xp.getElementNameList(rootElement);
		 if (!elementNameList.isEmpty()) {
		 for (int i = 0; i < elementNameList.size(); i++) { //输出根节点下一级子节点的名称
		 log.info((String) elementNameList.get(i));
		 }
		 }
		 //用迭代的方式获取根节点下子节点的名称
		 Iterator iterator = xp.elementIterator(rootElement);

		 while (iterator.hasNext()) {
		 //迭代下一级子节点
		 Element stuelement = (Element) iterator.next();
		 log.info(stuelement.getName());
		 //迭代stuelement的子节点
		 Iterator stuiterator = xp.elementIterator(stuelement);
		 while (stuiterator.hasNext()) {
		 Element studentelement = (Element) stuiterator.next();
		 //根据下级节点的属性名取得属性值
		 String saleAttrValue = xp.getAttributeValue(studentelement,
		 "性别");
		 log.info(saleAttrValue);
		 String ageAttrValue = xp
		 .getAttributeValue(studentelement, "年龄");
		 log.info(ageAttrValue);
		 //通过哈希表方式取出属性和属性值
		 HashMap attributeMap = (HashMap) xp
		 .attributeMap(studentelement);
		 //......取出HashMap中属性和属性值的操作

		 //取出学生节点下所有子节点的名称
		 ArrayList studentelementList = xp
		 .getElementNameList(studentelement);
		 if (!studentelementList.isEmpty()) {
		 for (int i = 0; i < studentelementList.size(); i++) {
		 log.info(studentelementList.get(i));
		 }
		 }
		 //输出学生节点的下级子节点数量
		 log.info(xp.getElementCount(studentelement));
		 //输出学生节点的属性数量
		 log.info(xp.getAttributeCount(studentelement));
		 //修改学生节点性别属性的值为女
		 xp.setAttributeValue(studentelement, "性别", "女");
		 //输出学生节点的性别属性值
		 log.info(xp.getAttributeValue(studentelement, "性别"));
		 xp.setElementName(studentelement, "学生");
		 //遍历学生节点所有子节点
		 Iterator elementiterator = xp.elementIterator(studentelement);
		 while (elementiterator.hasNext()) {
		 //取出每个子节点
		 Element element = (Element) elementiterator.next();
		 //判断子节点的节点数量
		 int count = xp.getElementCount(element);
		 //如果子节点是根节点，则输出节点文本值
		 if (count == 0) {
		 String elementText = xp.getElementText(element);
		 log.info(elementText);
		 } else {
		 //遍历学生节点属性值
		 Iterator attrIterator = xp.attributeIterator(element);
		 if (attrIterator.hasNext()) { //遍历属性，输出属性和属性值
		 Attribute attribute = (Attribute) attrIterator
		 .next();
		 log.info(attribute.getName());
		 log.info(attribute.getValue());
		 log.info(xp.getAttributeCount(element));
		 }
		 Iterator elemIterator = xp.elementIterator(element);
		 if (elemIterator.hasNext()) {
		 Element bxelement = (Element) elemIterator.next();
		 String elementText = xp.getElementText(bxelement);
		 log.info(elementText);
		 }
		 }
		 }

		 }
		 }

		 xp.writeXml(document, file);*/

		File file = new File("generator.xml");
		XmlParseUtil xp = new XmlParseUtil();
		//创建文档读写对象
		Document document = xp.readXml(file);
		//取得ROOT根节点
		Element rootElement = xp.getRootElement(document);
		//取得根节点名称
		String rootName = xp.getRootName(rootElement);
		System.out.println(rootName);//输出根节点名称
		//用迭代方式遍历根节点下的子节点
		Iterator<?> iterator = xp.elementIterator(rootElement);
		//用列表方式遍历根节点下的子节点
		List<?> list = xp.elementList(rootElement);
		System.out.println(list.size());//输出子节点数量
		System.out.println(xp.getElementCount(rootElement));//输出子节点数量
		//遍历节点
		while (iterator.hasNext()) { //取出子节点元素
			Element element = (Element) iterator.next();
			//取出子节点元素值
			String attributeValue = xp.getAttributeValue(element, "name");
			//判断子节点元素值，定位某一子节点
			if (attributeValue.equals("zhongyi")) { //输出定位到的子节点的名称
				System.out.println(element.getName());
				//遍历定位的子节点
				Iterator<?> it = xp.elementIterator(element);
				while (it.hasNext()) {
					Element childElement = (Element) it.next();
					String elementName = xp.getRootName(childElement);
					String elementText = xp.getElementText(childElement);
					System.out.println(elementName + ":=" + elementText);
					if (elementName.equals("address")) {
						xp.setText(childElement, "广东");
					}

				}
			}
		}
		//写入文件，并解决中文问题
		xp.writeXml(document, file);
	}
}
