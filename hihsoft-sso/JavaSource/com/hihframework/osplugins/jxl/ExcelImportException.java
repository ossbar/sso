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
