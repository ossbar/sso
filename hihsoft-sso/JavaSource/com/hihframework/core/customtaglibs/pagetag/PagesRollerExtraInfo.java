/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihframework.core.customtaglibs.pagetag;

import javax.servlet.jsp.tagext.*;

public class PagesRollerExtraInfo extends TagExtraInfo {
	public PagesRollerExtraInfo() {
	}

	public VariableInfo[] getVariableInfo(TagData data) {
		return (new VariableInfo[] {
				new VariableInfo("firsturl", "String", true, 0),
				new VariableInfo("lasturl", "String", true, 0),
				new VariableInfo("nexturl", "String", true, 0),
				new VariableInfo("hasprev", "String", true, 0),
				new VariableInfo("prevurl", "String", true, 0),
				new VariableInfo("hasnext", "String", true, 0),
				new VariableInfo("pageNo", "String", true, 0),
				new VariableInfo("sumpages", "String", true, 0),
				new VariableInfo("url","String",true,0)});
	}
}
