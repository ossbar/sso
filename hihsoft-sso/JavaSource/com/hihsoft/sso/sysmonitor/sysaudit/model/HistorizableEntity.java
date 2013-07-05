package com.hihsoft.sso.sysmonitor.sysaudit.model;


/**
 * 领域对象自动保存修改历史记录的接口.
 * Hibernate的Event Listener将对实现了此接口的领域对象自动保存修改记录
 * 
 *
 * 
 */
public interface HistorizableEntity extends AuditableEntity {
}
