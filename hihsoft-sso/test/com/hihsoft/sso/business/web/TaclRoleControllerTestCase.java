/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.business.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.hihsoft.sso.business.model.TaclRole;
import com.hihsoft.sso.business.web.controller.TaclRoleController;
import com.hihsoft.sso.util.BaseTestCase;

/**
 * <p> Title:单元测试 </p>
 * <p> Description:角色管理WEB控制层单元测试案例</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author zhujw
 * @version 1.0
 */

public class TaclRoleControllerTestCase extends BaseTestCase{

	/** The tacl role controller. */
	@Autowired
	public TaclRoleController taclRoleController;



	/**
	 * 测试控制层的save方法
	 * Test save.
	 *
	 * @throws Exception the exception
	 */
	@Test
	public void testSave() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		request.setParameter("rolename", "开发管理员1");
		request.setParameter("remark", "开发人员专用的角色1");
		TaclRole taclRole=new TaclRole();

		taclRoleController.save(request, response);

	}

	/**
	 * Test list.
	 * 测试控制层的list方法
	 * @throws Exception the exception
	 */
	@Test
	public void testList() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		// request.addParameter("rolename", "系统管理员");//设定查询条件
		taclRoleController.list(request, response);
	}



}
