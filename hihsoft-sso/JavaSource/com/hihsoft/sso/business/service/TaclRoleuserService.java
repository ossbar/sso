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
 * Title:角色分配用户的服务
 * Description:
 * Copyright: Copyright (c) 2013
 * Company:hihsoft.co.,ltd
 * @author hihsoft.co.,ltd
 * @version 1.0
 */

public interface TaclRoleuserService extends BaseService {

	/**
	 * 新增、修改TaclRoleuser信息
	 * 
	 * @param taclRoleuser
	 * @throws DataAccessException
	 */
	public void saveOrUpdateTaclRoleuser(TaclRoleuser taclRoleuser)
			throws ServiceException;

	/**
	 * 删除TaclRoleuser信息
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public void deleteTaclRoleuser(String id) throws ServiceException;

	/**
	 * 通过HQL构造查询条件来查询符合条件的TaclRoleuser信息
	 * 
	 * @param hql
	 * @return List
	 * @throws DataAccessException
	 */
	public List<?> getTaclRoleuserByHQL(String hql) throws ServiceException;

	/**
	 * 查询所有的TaclRoleuser信息
	 * 
	 * @param hql
	 * @return List
	 * @throws DataAccessException
	 */
	public List<?> getAllTaclRoleuser() throws ServiceException;

	/**
	 * 根据主键查询TaclRoleuser信息明细
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public TaclRoleuser getTaclRoleuserById(String id) throws ServiceException;

	/**
	 * 把查询条件构造成数组来查询TaclRoleuser信息
	 * 
	 * @param Object
	 *            [] object
	 * @return List
	 * @throws DataAccessException
	 */
	public List<?> getTaclRoleuserByHQL(String hql, Object[] object)
			throws ServiceException;

	/**
	 * 取得分页总数
	 * 
	 * @param hql
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */

	public int getTaclRoleuserDataTotalNum(String hql, Object[] object)
			throws ServiceException;

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

	public List<?> getTaclRoleuserPageDataByHQL(String hql, Object[] object,
			int page_size, int pageNo) throws ServiceException;

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
	public List<?> getTaclRoleuserPageDataByHQL(String hql, Map<String, Object> obj,
			int page_size, int pageNo) throws ServiceException;

	/**
	 * 利用SQL数组条件来查询记录
	 * 
	 * @param sql
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public List<?> getTaclRoleuserValueObjectBySQL(String sql, Object[] object)
			throws ServiceException;

	/**
	 * 通过配置SQL来执行查询带多个参数的情况 包括SQL语句、存储过程等
	 * 
	 * @param queryName
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public List<?> getTaclRoleuserValueObjectByNameQuery(String queryName,
			Object[] object) throws ServiceException;

	/**
	 * 动态构造HQL参数
	 * 
	 * @param detachedCriteria
	 * @return
	 * @throws ServiceException
	 */
	public List<?> getTaclRoleuserValueObjectByDetachedCriteria(
			DetachedCriteria detachedCriteria) throws ServiceException;

	/**
	 * 动态构造HQL参数
	 * 
	 * @param detachedCriteria
	 * @return
	 * @throws ServiceException
	 */
	public List<?> getTaclRoleuserValueObjectByDetachedCriterias(
			DetachedCriteria detachedCriteria, int arg1, int arg2)
			throws ServiceException;
	/**
	 * 获取用户已经定义的角色
	 * @param userId
	 * @return
	 * @author Xiaojf
	 * @since 2011-6-3
	 */
	public List<TaclRole> getDefinedRole(String userId);
}
