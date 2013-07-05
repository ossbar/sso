/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihframework.exception;

import java.text.MessageFormat;
import java.util.MissingResourceException;

import com.hihframework.osplugins.properties.PropertiesUtil;
/**
 * <p> Title:封装业务逻辑层统一异常类 </p>
 * <p> Description:实现异常编码机制</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft.co.,ltd
 * @version 1.0
 */

/**
 * 业务异常基类. 带有错误代码与错误信息. 用户在生成异常时既可以直接赋予错误代码与错误信息. 也可以只赋予错误代码与错误信息参数.
 * 如ErrorCode=ORDER.LACK_INVENTORY ,errorArg=without EJB
 * javahih-SSO-000001={0} 系统出错，请与系统管理员联系。
 * 系统会从errors.properties中查出 javahih-000001 
 * 
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * 错误代码,默认为未知错误
	 */
	private String errorCode = "UNKNOW_ERROR";

	/**
	 * 错误信息中的参数
	 */
	protected String[] errorArgs = null;

	/**
	 * 兼容纯错误信息，不含error code,errorArgs的情况
	 */
	private String errorMessage = null;

	public ServiceException() {
		super();
	}

	public ServiceException(String errorCode, String[] errorArgs) {
		this.errorCode = errorCode;
		this.errorArgs = errorArgs;
	}

	public ServiceException(String errorCode, String errorArg) {
		this.errorCode = errorCode;
		this.errorArgs = new String[] { errorArg };
	}

	public ServiceException(String errorCode, String[] errorArgs,
			Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
		this.errorArgs = errorArgs;
	}

	public ServiceException(String errorCode, String errorArg, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
		this.errorArgs = new String[] { errorArg };
	}

	public ServiceException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * 获得出错信息. 读取i18N properties文件中Error Code对应的message,并组合参数获得i18n的出错信息.
	 */
	public String getMessage() {
		// 如果errorMessage不为空,直接返回出错信息.
		if (errorMessage != null) {
			return errorMessage;
		}
		// 否则用errorCode查询Properties文件获得出错信息
		String message;
		try {
			message = PropertiesUtil.getValue(errorCode);
		} catch (MissingResourceException mse) {
			message = "ErrorCode is: " + errorCode
					+ ", but can't get the message of the Error Code";
		}

		// 将出错信息中的参数代入到出错信息中
		if (errorArgs != null)
			message = MessageFormat.format(message, (Object[]) errorArgs);

		return message + ", Error Code is: " + errorCode;

	}

	public String getErrorCode() {
		return errorCode;
	}
}