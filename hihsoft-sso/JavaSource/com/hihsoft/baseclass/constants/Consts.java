/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.baseclass.constants;

import java.util.Map;

import javax.servlet.ServletContext;

/**
 * <p> Title:框架常量定义类</p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 * @author hihsoft.co.,ltd
 * @version 1.0
 */

public class Consts {

	public static String SYS_PAGE_SIZE = "pageSize";

	public static String SYS_DEFAULT_PAGE_SIZE = "15";

	public static String SYS_PAGE_NO = "pageNo";

	public static String SYS_PAGE_ITMECOUNT = "itemCount";
	// 监控属性文件是否修改的延时
	public static final String FILECHANGES_DELAY = "file.changes.delay";
	public static final String SPRING_PROPERTIES = "properties.path";

	// public static int PAGESIZE = 10;

	// 不可以外部实例化该类
	private Consts() {
	}

	public static final String USER_INFO = "userinfo"; // 登录的用户信息

	public static final String ADMIN_INIT_PWD = ""; // 系统初始化时管理员登录的初始密码

	public static final String USER_ID = "userId"; // 登录用户Id

	public static final String USER_NAME = "userName";// 登录用户姓名

	public static final String USER_DEFAULT_ORG = "defaultOrg"; // 登录用户的默认单位

	public static final String USER_DEFAULT_DEPT = "defaultDept"; // 登录用户的默认部门

	public static final String USER_DEFAULT_ROLE = "defaultRole"; // 登录用户的默认角色

	public static final String USER_DEFAULT_STYLE = "defaultStyle"; // 用户默认的界面风格

	public static final String USER_PRIVILEGES_DATA = "userPrivilegesData";// 用户的所有权限：角色权限＋用户特权

	public static final String MODULE_NO = "moduleNo"; // 模块定义

	public static final String OPERATE_NO = "operateNo";// 操作定义

	public static final String PAGE_OPERATE_MAP = "pageOperateMap";// 用户一个页面中所包含的所有有权限的操作定义

	public static final String SYS_LOG = "sysLog";// 日志定义

	public static final String SYS_BASEINFO = "sysbaseinfo";// 基本参数定义

	public static final String PAGE_NO = "pageNo";

	public static final String YES = "Y";

	public static final String NO = "N";

	// 读取XML配置文件
	public static final String XML_PATH_SOA = "SOAConfigLoction";

	public static ServletContext context;

	public static Map<String, String> pathMap;

	// 框架初始化信息定义
	// 4.默认管理员信息定义
	public static final String ADMIN_USER_LOGINID = "devadmin"; //
	
	public static final String ADMIN_USER_PASSWD="123478";

	public static final String LOGINACTION = "UserverifyAction";

	public static final String METHODLOGIN = "login";

	public static final String METHODLOGINOFF = "logOff";
	
	public static final String CUR_ORGID = "curOrgId";
	
	public static final String CUR_DEPTID = "curDeptId";
	
	public static final String ALL_ORGS = "allOrgs";

}
