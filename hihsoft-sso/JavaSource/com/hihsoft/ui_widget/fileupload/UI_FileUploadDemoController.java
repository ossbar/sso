/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.ui_widget.fileupload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.hihframework.exception.ControllerException;
import com.hihsoft.baseclass.web.controller.javahihBaseController;

/**
 * <p> Title:使用优秀的文件上传组件fileupload演示 </p>
 * <p> Description:主要文件上传功能演示</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
@Controller
@RequestMapping("/uiFileUploadDemoController.do")
public class UI_FileUploadDemoController extends javahihBaseController {

	/**
	* 访问列表
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(params="method=getTree")
	public ModelAndView getTree(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
	    return new ModelAndView("/ui_widget/ui_fileupload_demo");
	}
	/**
	* 访问列表
	* @param request
	* @param response
	* @return
	* @throws Exception
	*/
	@RequestMapping(params="method=details")
	public ModelAndView details(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
	    return new ModelAndView("/ui_widget/ui_fileupload_demo_describe");
	}

}

