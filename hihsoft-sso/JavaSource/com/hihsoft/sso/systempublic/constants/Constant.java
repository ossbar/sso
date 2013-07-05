package com.hihsoft.sso.systempublic.constants;

import java.util.List;
import java.util.Map;

public class Constant {

	public final static String USER = "user";
	
	public static final String PASSWORD = "123478";

	//不可以外部实例化该类
	private Constant() {
	}

	/*-----------------------------------------系统管理信息-------------------------------------------------------------------*/
	/**
	 * 系统参数类型，参数级别
	 */
	public static final String SYS_PARAM_PARACLASS = "paraClass";

	/**
	 * 系统参数类型，是否默认
	 */
	public static final String SYS_PARAM_ISDEFAULT = "isdefault";

	/**
	 * 省份名称
	 */
	public static String SYS_AREA_PROVINCE = "province";

	/**
	 * 在线人数总计
	 */
	public static int onlineUserTotal = 0;

	/**
	 * 登录用户人数总计
	 */
	public static int loginUserTotal = 0;

	public static String SYS_PARAM_INITPAGESIZE = "initPageSize";
	//行业
	public static String SYS_PARAM_TRADE="TRADE";
	//职业
	public static String SYS_PARAM_CERTIFIED="CERTIFIED";

	public static String SYS_CONFIGlOCATION_XML_NAME = "hihSoftConfigLocation";

	/*---------------------------------------模块管理---------------------------------------------------------------------*/

	/**
	 * 模块图标保存路径
	 */
	public static final String SYS_PARAM_MODULEIMAGEPATH = "moduleImagePath";

	/*---------------------------------------------------用户信息---------------------------------------------------------*/
	/**
	 * 用户类型，1为系统级用户，2为收费用户，3为普通用户
	 */
	public static final String SYS_PARAM_USERTYPE = "userType";

	/**
	 * 用户性别，1为男，2为女
	 */
	public static final String SYS_PARAM_SEX = "sex";

	/**
	 * 用户在线状态，1为在线，2为不在线
	 */
	public static final String SYS_PARAM_ISONLINE = "isonline";

	/**
	 * 用户状态
	 */
	public static final String SYS_PARAM_USERSTATE = "userState";

	/**
	 * 用户头像保存路径
	 */
	public static final String SYS_PARAM_USERIMAGEPATH = "userImagePath";

	/**
	 * 用户历史记录中的request参数Map对象
	 */
	public static final String SYS_USERHISTORY_NOTE_PARAMMAP = "userHistoryNoteParamMap";
	
	public static final String SYS_USERHISTORY_QUERY = "userQuery";

	/**
	 * 登录用户信息
	 */
	public static final String SYS_LOGIN_USERINFO = "loginUser";
	
	public static final String SYS_LOGIN_FLAT = "loginFlat";

	/**
	 * 登录用户权限信息
	 */
	public static final String SYS_USER_MOPEDOM_MAP = "userMopedomMap";

	public static Map paramMap = null;

	public static List getTsysParameterByparaType(String paraType) {
		return (List) paramMap.get(paraType);
	}

	public static Map areaMap = null;

	public static List getTSysAreaByAreaType(String type) {
		return (List) areaMap.get(type);
	}
	/**
	 * 停用状态
	 */
	public static final String ORG_STATUS_STOPED = "00";
	/**
	 * 正常状态
	 */
	public static final String ORG_STATUS_NORMAL = "01";
	
}
