/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.business.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hihsoft.sso.business.model.TsysModuleinfo;
import com.hihsoft.sso.business.service.TsysModuleinfoService;
import com.hihsoft.sso.util.BaseTestCase;
/**
 * <p> Title:单元测试 </p>
 * <p> Description:角色管理业务逻辑层单元测试案例</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:javahih.co.,ltd </p>
 *
 * @author zhujw
 * @version 1.0
 */
public class TaclModuleinfoServiceTestCase extends BaseTestCase {

	/** The tacl role service. */
	@Autowired
	public static TsysModuleinfoService tsysModuleinfoService;



	/**批量执行入库操作:直接利用hibernate超过5000条记录就报内存不足，利用spring封装可以一次性插入<50000的数据
	 * 分4000一批量执行入库，可以实现，但占用CPU过高
	 * Testsave or update tacl role.
	 */
	@Test
	public void testsaveOrUpdateTaclModuleinfo() {
		TsysModuleinfo moduleinfo = null;
		// 打开Session
		Configuration cfg = new Configuration();
		SessionFactory sf = cfg.configure().buildSessionFactory();
		Session session = sf.openSession();
		// 开始事务
		Transaction tx = session.beginTransaction();
		// 循环100 000次，插入100 000条记录
		for (int i = 1000000; i < 2000000; i++) {
			// 创建Moduleinfo实例
			moduleinfo = new TsysModuleinfo();
			moduleinfo.setModulename("moduleinfo" + i);
			moduleinfo.setModulename("00000001" + i);
			moduleinfo.setFlatid("8a9e848a2ed74230012ed7424737000a");
			session.save(moduleinfo);
			// 每当累加器是20的倍数时，将Session中的数据刷入数据库，并清空Session缓存
			if (i % 5000 == 0) {
				session.flush();
				session.clear();
				tx.commit();
				tx = session.beginTransaction();
			}
		}
		// 提交事务
		tx.commit();
		// 关闭事务
		session.close();

	}

	/**
	 * Test query moduleinfo.
	 */
	@Test
	public void testQueryModuleinfo() {
		log.info(tsysModuleinfoService.getAllTsysModuleinfo());

	}
}
