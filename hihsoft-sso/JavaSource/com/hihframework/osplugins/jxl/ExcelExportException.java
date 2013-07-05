/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihframework.osplugins.jxl;

/**
 * <p> Title:excel数据导出异常类处理</p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *  @author hihsoft.co.,ltd
 * @version 1.0
 */
public class ExcelExportException extends Exception {

	private static final long serialVersionUID = 1L;

	public ExcelExportException() {

		super();
	}

	public ExcelExportException(String msg) {

		super(msg);
	}

	public ExcelExportException(Throwable s) {

		super(s);
	}
}
