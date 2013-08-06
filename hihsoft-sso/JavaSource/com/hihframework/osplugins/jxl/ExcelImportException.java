
/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihframework.osplugins.jxl;

/**
 * <p> Title:导入EXCEL异常处理</p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *  @author hihsoft.co.,ltd
 * @version 1.0
 */
public class ExcelImportException extends Exception {
	private static final long serialVersionUID = 6179538138036322023L;

	public ExcelImportException() {

		super();
	}

	public ExcelImportException(String msg) {

		super(msg);
	}

	public ExcelImportException(Throwable s) {

		super(s);
	}
}
