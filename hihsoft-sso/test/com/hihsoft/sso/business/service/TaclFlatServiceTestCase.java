/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.business.service;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hihsoft.sso.business.model.TaclUserinfo;
import com.hihsoft.sso.business.model.TsysFlat;
import com.hihsoft.sso.business.service.TaclUserinfoService;
import com.hihsoft.sso.business.service.TsysFlatService;
import com.hihsoft.sso.util.BaseTestCase;
public class TaclFlatServiceTestCase extends BaseTestCase  {
	@Autowired
	private  TsysFlatService tsysFlatService;

	@Autowired
	private  TaclUserinfoService taclUserinfoService;


	 @Test
	public void testGetUserRoles() {
		Map<String, Map<String, String>> roles = taclUserinfoService
				.getUserRoles(new TaclUserinfo(
						"402882ea3000f634013000f64ecf012f"));
		System.out.println(roles.size());
	}
	 @Test
	public void testSaveOrUpdateTsysFlat() {
		System.out.println("test");
		TsysFlat tSysFalt = new TsysFlat();
		tSysFalt.setFlatcode("tttt");
		tSysFalt.setFlatname("采购子系统");
		tSysFalt.setRemark("物流平台中的一个子系统");
		tsysFlatService.saveOrUpdateTsysFlat(tSysFalt);
	}

	
}
