/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.sysmonitor.syslogs.service;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;

import com.hihframework.exception.ServiceException;
import com.hihsoft.baseclass.service.BaseService;
import com.hihsoft.sso.sysmonitor.syslogs.model.TlogAudit;

/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft.co.,ltd
 * @version 1.0
 */

public interface TlogAuditService extends BaseService {

	/**
	 * 新增、修改TlogAudit信息
	 * 
	 * @param tlogAudit
	 * @throws DataAccessException
	 */
	public void saveOrUpdateTlogAudit(TlogAudit tlogAudit)
			throws ServiceException;

	/**
	 * 删除TlogAudit信息
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public void deleteTlogAudit(String id) throws ServiceException;

	/**
	 * 通过HQL构造查询条件来查询符合条件的TlogAudit信息
	 * 
	 * @param hql
	 * @return List
	 * @throws DataAccessException
	 */
	public List<?> getTlogAuditByHQL(String hql) throws ServiceException;

	/**
	* 查询所有的TlogAudit信息
	* 
	* @param hql
	* @return List
	* @throws DataAccessException
	*/
	public List<?> getAllTlogAudit() throws ServiceException;

	/**
	 * 根据主键查询TlogAudit信息明细
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public TlogAudit getTlogAuditById(String id) throws ServiceException;

	/**
	 * 把查询条件构造成数组来查询TlogAudit信息
	 * 
	 * @param Object[]
	 *            filter
	 * @return List
	 * @throws DataAccessException
	 */
	public List<?> getTlogAuditByArray(Object[] filter) throws ServiceException;

	/**
	 * 取得分页总数
	 * 
	 * @param hql
	 * @param filter
	 * @return
	 * @throws DataAccessException
	 */

	public int getTlogAuditDataTotalNum(Object[] filter)
			throws ServiceException;

	/**
	 * 把查询条件构造成MAP来查询TlogAudit信息
	 * 
	 * @param Map
	 *            filter
	 * @return List
	 * @throws DataAccessException
	 */
	public List<?> getTlogAuditByMap(Map<String, Object> filter) throws ServiceException;

	/**
	 * 分页查询
	 * 
	 * @param hql
	 * @param object
	 * @param page_size
	 * @param pageNo
	 * @return
	 * @throws DataAccessException
	 */

	public List<?> getTlogAuditPageDataByArray(Object[] filter, int page_size,
			int pageNo) throws ServiceException;

	/**
	 * 分页查询。
	 * 
	 * @param hql
	 * @param obj：MAP条件构造
	 * @param page_size
	 * @param pageNo
	 * @return
	 * @throws DataAccessException
	 */
	public List<?> getTlogAuditPageDataByMap(Map<String, Object> filter, int page_size, int pageNo)
			throws ServiceException;

	/**
	 * 利用SQL数组条件来查询记录
	 * 
	 * @param sql
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public List<?> getTlogAuditValueObjectWithSQLByArray(Object[] filter)
			throws ServiceException;

	/**
	 * 通过配置SQL来执行查询带多个参数的情况 包括SQL语句、存储过程等
	 * 
	 * @param queryName
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public List<?> getTlogAuditValueObjectByNameQuery(String queryName,
			Object[] filter) throws ServiceException;

	/**
	 * 动态构造HQL参数
	 * @param detachedCriteria
	 * @return
	 * @throws ServiceException
	 */
	public List<?> getTlogAuditValueObjectByDetachedCriteria(
			DetachedCriteria detachedCriteria) throws ServiceException;

	/**
	 * 动态构造HQL参数
	 * @param detachedCriteria
	 * @return
	 * @throws ServiceException
	 */
	public List<?> getTlogAuditValueObjectByDetachedCriterias(
			DetachedCriteria detachedCriteria, int arg1, int arg2)
			throws ServiceException;

}
