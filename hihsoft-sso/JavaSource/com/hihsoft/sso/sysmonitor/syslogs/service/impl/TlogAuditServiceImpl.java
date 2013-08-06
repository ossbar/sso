/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.sysmonitor.syslogs.service.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hihframework.exception.ServiceException;
import com.hihsoft.baseclass.service.impl.BaseServiceImpl;
import com.hihsoft.sso.sysmonitor.syslogs.model.TlogAudit;
import com.hihsoft.sso.sysmonitor.syslogs.service.TlogAuditService;

/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2011 </p>
 * <p> Company:javahih.co.,ltd </p>
 *
 * @author zhujw
 * @version 1.0
 */
@Service(value="tlogAuditService")
public class TlogAuditServiceImpl extends BaseServiceImpl implements
		TlogAuditService {
	/************查询所有的TLOGAUDIT******************/
	private static final String ALLTLOGAUDIT_HQL = " from TlogAudit";

	/************通过主键查询TLOGAUDIT******************/
	private static final String TLOGAUDITById_HQL = " from TlogAudit tlogAudit where tlogAudit.aduitid=?";

	/************通过不同的条件组合，利用Hibernate HQL查询TLOGAUDIT******************/
	private static final StringBuffer QUERY_TLOGAUDIT_HQL = new StringBuffer(
			" from TlogAudit tlogAudit where 1=1");

	/************通过不同的条件组合，利用JDBC SQL查询TLOGAUDIT******************/
	private static final StringBuffer QUERY_TLOGAUDIT_SQL = new StringBuffer(
			"select * from T_LOG_AUDIT t where 1=1");

	/**
	 * 新增、修改TlogAudit信息
	 * 
	 * @param tlogAudit
	 * @throws DataAccessException
	 */
	public void saveOrUpdateTlogAudit(TlogAudit tlogAudit)
			throws ServiceException {
		baseDAO.saveOrUpdateVO(tlogAudit);
	}

	/**
	 * 删除TlogAudit信息
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public void deleteTlogAudit(String id) throws ServiceException {
		TlogAudit tlogAudit = this.getTlogAuditById(id);
		baseDAO.deleteVO(tlogAudit);
	}

	/**
	 * 通过HQL构造查询条件来查询符合条件的TlogAudit信息
	 * 
	 * @param hql
	 * @return List
	 * @throws DataAccessException
	 */
	public List<?> getTlogAuditByHQL(String hql) throws ServiceException {
		return baseDAO.getValueObjectsByHQL(hql);

	}

	/**
	* 查询所有的TlogAudit信息
	* 
	* @param hql
	* @return List
	* @throws DataAccessException
	*/
	public List<?> getAllTlogAudit() throws ServiceException {
		return baseDAO.getValueObjectsByHQL(ALLTLOGAUDIT_HQL);
	}

	/**
	 * 根据主键查询TlogAudit信息明细
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public TlogAudit getTlogAuditById(String id) throws ServiceException {
		TlogAudit tlogAudit = null;
		List<?> list = baseDAO.getValueObjectsByHQL(TLOGAUDITById_HQL,
				new Object[] { id });
		if (!list.isEmpty() && list.size() > 0) {
			tlogAudit = (TlogAudit) list.get(0);
		}
		return tlogAudit;
	}

	/**
	 * 把查询条件构造成数组来查询TlogAudit信息
	 * 
	 * @param Object[]
	 *            object
	 * @return List
	 * @throws DataAccessException
	 */
	public List<?> getTlogAuditByArray(Object[] filter) throws ServiceException {
		return baseDAO.getValueObjectsByHQL(QUERY_TLOGAUDIT_HQL.toString(),
				filter);
	}

	/**
	 * 取得分页总数
	 * 
	 * @param hql
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */

	public int getTlogAuditDataTotalNum(Object[] filter)
			throws ServiceException {
		return baseDAO.getDataTotalNum(QUERY_TLOGAUDIT_HQL.toString(), filter);
	}

	/**
	 * 把查询条件构造成数组来查询TlogAudit信息
	 * 
	 * @param Map
	 *            filter
	 * @return List
	 * @throws DataAccessException
	 */
	public List<?> getTlogAuditByMap(Map<String, Object> filter) throws ServiceException {
		return baseDAO.getPageDataByHQL(QUERY_TLOGAUDIT_HQL.toString(), filter);
	}

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
			int pageNo) throws ServiceException {
		return baseDAO.getPageDataByHQL(QUERY_TLOGAUDIT_HQL.toString(), filter,
				page_size, pageNo);
	}

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
			throws ServiceException {
		return baseDAO.getPageDataByHQL(QUERY_TLOGAUDIT_HQL.toString(), filter,
				page_size, pageNo);
	}

	/**
	 * 利用SQL数组条件来查询记录
	 * 
	 * @param sql
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public List<?> getTlogAuditValueObjectWithSQLByArray(Object[] filter)
			throws ServiceException {
		return baseDAO.getValueObjectBySQL(QUERY_TLOGAUDIT_SQL.toString(),
				filter);
	}

	/**
	 * 通过配置SQL来执行查询带多个参数的情况 包括SQL语句、存储过程等
	 * 
	 * @param queryName
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public List<?> getTlogAuditValueObjectByNameQuery(String queryName,
			Object[] filter) throws ServiceException {
		return baseDAO.getValueObjectByNameQuery(queryName, filter);
	}

	/**
	 * 动态构造HQL参数
	 * @param detachedCriteria
	 * @return
	 * @throws ServiceException
	 */
	public List<?> getTlogAuditValueObjectByDetachedCriteria(
			DetachedCriteria detachedCriteria) throws ServiceException {
		return baseDAO.getValueObjectByDetachedCriteria(detachedCriteria);
	}

	/**
	 * 动态构造HQL参数
	 * @param detachedCriteria
	 * @return
	 * @throws ServiceException
	 */
	public List<?> getTlogAuditValueObjectByDetachedCriterias(
			DetachedCriteria detachedCriteria, int arg1, int arg2)
			throws ServiceException {
		return baseDAO.getValueObjectByDetachedCriterias(detachedCriteria,
				arg1, arg2);
	}

}
