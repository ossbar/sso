/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.sysmonitor.sysaudit.listener;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.collection.PersistentCollection;
import org.hibernate.event.PostUpdateEvent;
import org.hibernate.event.PostUpdateEventListener;
import org.hibernate.event.def.DefaultLoadEventListener;

import com.hihsoft.baseclass.model.IUser;
import com.hihsoft.sso.sysmonitor.sysaudit.model.AuditableEntity;
import com.hihsoft.sso.sysmonitor.sysaudit.model.HistorizableEntity;
import com.hihsoft.sso.sysmonitor.sysaudit.model.OperationType;
import com.hihsoft.sso.sysmonitor.syslogs.model.TlogAudit;

/**
 * 侦听领域对象的PostUpdate事件，如果此对象实现了HistorizableEntity接口，将记录其修改前后的数据.
 * 经调试发现：记录审计日志的必须调用spring封装的saveOrMergeVO方法才行.
 * 同时要注意配置文件中涉及到外键的情况lazy=false才行
 * 同时业务对象必须继承BaseAuditEntity
 * 奇怪在PostUpdateEventListener中save操作却不能成功？很难获取到hibernate的session，不能进行持久化操作.单独从会话工厂中获取session
 * @author hihsoft
 */
public class HistoryEventListener extends DefaultLoadEventListener implements
		PostUpdateEventListener {
	protected Logger log = Logger.getLogger(HistoryEventListener.class);

	public void onPostUpdate(PostUpdateEvent event) {
		log.info("开始监控修改的对象" + event.getEntity().getClass().getName()
				+ "----------------->>>>>>>" + event.getEntity().toString());
		if (event.getEntity() instanceof HistorizableEntity) {
			HistorizableEntity he = (HistorizableEntity) event.getEntity();
			IUser user = he.getModifyUser();
			String username = null;
			if (user != null)
				username = user.getUsername();
			for (int i = 0; i < event.getState().length; i++) {
				// 更新前的值
				Object oldValue = event.getOldState()[i];
				// 更新后的新值
				Object newValue = event.getState()[i];
				// 跳过集合属性
				// Check for changes
				if (oldValue == null && newValue == null) {
					continue;
				}
				if (newValue instanceof PersistentCollection) {
					PersistentCollection collection = (PersistentCollection) newValue;
					if (collection.isDirectlyAccessible() == false) {
						continue;
					}
				}
				if (oldValue != null && !oldValue.equals(newValue)) {
					TlogAudit entry = new TlogAudit();
					// 取得属性名称
					entry.setProperty(event.getPersister().getPropertyNames()[i]);
					entry.setOperationType(OperationType.UPDATE.toString());
					// 如果更改的属性是关联对象，则存储其id
					if (oldValue instanceof AuditableEntity) {
						entry.setFrontData(((AuditableEntity) oldValue).getId()
								.toString());
					} else {
						entry.setFrontData(oldValue != null ? oldValue
								.toString() : null);
					}
					if (newValue instanceof AuditableEntity) {
						entry.setBehindData(((AuditableEntity) newValue)
								.getId().toString());
					} else {
						entry.setBehindData(newValue != null ? newValue
								.toString() : null);
					}
					entry.setBehindData(newValue.toString());
					entry.setFrontData(oldValue.toString());
					entry.setOid(event.getId().toString());
					entry.setModifyMan(username);
					// final SimpleDateFormat format = new SimpleDateFormat(
					// "yyyy-MM-dd HH:mm:ss");
					// entry.setModifyTime(format.format(he.getModifyTime()));
					log.info("99999999999999999" + username);
					// entry.set
					recordHistory(event.getSession(), entry);
				}
			}
		}
	}

	private void recordHistory(Session session, TlogAudit entry) {
		Session tempSession = session.getSessionFactory().openSession();
		Transaction tx = tempSession.beginTransaction();
		try {
			tx.begin();
			tempSession.save(entry);
			tempSession.flush();
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
		}
		tempSession.close();
	}
}
