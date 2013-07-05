package com.hihframework.exception;

/**
 * <p> Title:控制层异常类的统一处理 </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public class ControllerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ControllerException(String msg) {
		super(msg);
		// 日志
	}

	public ControllerException(String code, Throwable cause) {
		super(code, cause);
	}
}