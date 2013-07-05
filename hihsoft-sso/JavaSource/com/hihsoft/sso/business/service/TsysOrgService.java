/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.business.service;

import java.util.*;

import org.hibernate.criterion.DetachedCriteria;

import com.hihframework.exception.ServiceException;
import com.hihsoft.baseclass.service.BaseService;
import com.hihsoft.sso.business.model.*;

/**
 * Title:机构定义的服务
 * Description:
 * Copyright: Copyright (c) 2013
 * Company:hihsoft.co.,ltd
 * @author hihsoft.co.,ltd
 * @version 1.0
 */

public interface TsysOrgService extends BaseService {

	/**
	 * 新增、修改TsysOrg信息
	 * 
	 * @param tsysOrg
	 * @throws DataAccessException
	 */
	public void saveOrUpdateTsysOrg(TsysOrg tsysOrg) throws ServiceException;

	/**
	 * 删除TsysOrg信息
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public String deleteTsysOrg(String id) throws ServiceException;

	/**
	 * 通过HQL构造查询条件来查询符合条件的TsysOrg信息
	 * 
	 * @param hql
	 * @return List
	 * @throws DataAccessException
	 */
	public List getTsysOrgByHQL(String hql) throws ServiceException;

	/**
	 * 查询所有的TsysOrg信息
	 * 
	 * @param hql
	 * @return List
	 * @throws DataAccessException
	 */
	public List getAllTsysOrg() throws ServiceException;

	/**
	 * 根据主键查询TsysOrg信息明细
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public TsysOrg getTsysOrgById(String id) throws ServiceException;

	/**
	 * 把查询条件构造成数组来查询TsysOrg信息
	 * 
	 * @param Object
	 *            [] filter
	 * @return List
	 * @throws DataAccessException
	 */
	public List getTsysOrgByArray(Object[] filter) throws ServiceException;

	/**
	 * 取得分页总数
	 * 
	 * @param hql
	 * @param filter
	 * @return
	 * @throws DataAccessException
	 */

	public int getTsysOrgDataTotalNum(Object[] filter) throws ServiceException;

	/**
	 * 把查询条件构造成MAP来查询TsysOrg信息
	 * 
	 * @param Map
	 *            filter
	 * @return List
	 * @throws DataAccessException
	 */
	public List getTsysOrgByMap(Map filter) throws ServiceException;

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

	public List getTsysOrgPageDataByArray(Object[] filter, int page_size,
			int pageNo) throws ServiceException;

	/**
	 * 分页查询。
	 * 
	 * @param hql
	 * @param obj
	 *            ：MAP条件构造
	 * @param page_size
	 * @param pageNo
	 * @return
	 * @throws DataAccessException
	 */
	public List getTsysOrgPageDataByMap(Map filter, int page_size, int pageNo)
			throws ServiceException;

	/**
	 * 利用SQL数组条件来查询记录
	 * 
	 * @param sql
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public List getTsysOrgValueObjectWithSQLByArray(Object[] filter)
			throws ServiceException;

	/**
	 * 通过配置SQL来执行查询带多个参数的情况 包括SQL语句、存储过程等
	 * 
	 * @param queryName
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public List getTsysOrgValueObjectByNameQuery(String queryName,
			Object[] filter) throws ServiceException;

	/**
	 * 动态构造HQL参数
	 * 
	 * @param detachedCriteria
	 * @return
	 * @throws ServiceException
	 */
	public List getTsysOrgValueObjectByDetachedCriteria(
			DetachedCriteria detachedCriteria) throws ServiceException;

	/**
	 * 动态构造HQL参数
	 * 
	 * @param detachedCriteria
	 * @return
	 * @throws ServiceException
	 */
	public List getTsysOrgValueObjectByDetachedCriterias(
			DetachedCriteria detachedCriteria, int arg1, int arg2)
			throws ServiceException;

}
