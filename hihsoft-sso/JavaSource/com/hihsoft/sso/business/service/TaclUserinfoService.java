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
 * Title:用户基础信息的相关服务
 * Description:
 * Copyright: Copyright (c) 2013
 * Company:hihsoft.co.,ltd
 * @author hihsoft.co.,ltd
 * @version 1.0
 */

public interface TaclUserinfoService extends BaseService {

	/**
	 * 新增、修改TaclUserinfo信息
	 * 
	 * @param taclUserinfo
	 * @throws DataAccessException
	 */
	public void saveOrUpdateTaclUserinfo(TaclUserinfo taclUserinfo)
			throws ServiceException;
	/**
	 * 新增或修改用户岗位
	 * @param taclDutyuser
	 * @throws ServiceException
	 */
	public boolean saveOrUpdateTaclDutyUser(String userId, String duty)
			throws ServiceException;

	/**
	 * 删除TaclUserinfo信息
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public void deleteTaclUserinfo(String id) throws ServiceException;

	/**
	 * 通过HQL构造查询条件来查询符合条件的TaclUserinfo信息
	 * 
	 * @param hql
	 * @return List
	 * @throws DataAccessException
	 */
	public List getTaclUserinfoByHQL(String hql) throws ServiceException;

	/**
	 * 查询所有的TaclUserinfo信息
	 * 
	 * @param hql
	 * @return List
	 * @throws DataAccessException
	 */
	public List getAllTaclUserinfo() throws ServiceException;

	/**
	 * 根据主键查询TaclUserinfo信息明细
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public TaclUserinfo getTaclUserinfoById(String id) throws ServiceException;

	/**
	 * 把查询条件构造成数组来查询TaclUserinfo信息
	 * 
	 * @param Object
	 *            [] filter
	 * @return List
	 * @throws DataAccessException
	 */
	public List getTaclUserinfoByArray(Object[] filter) throws ServiceException;

	/**
	 * 取得分页总数
	 * 
	 * @param hql
	 * @param filter
	 * @return
	 * @throws DataAccessException
	 */

	public int getTaclUserinfoDataTotalNum(Object[] filter)
			throws ServiceException;

	/**
	 * 把查询条件构造成MAP来查询TaclUserinfo信息
	 * 
	 * @param Map
	 *            filter
	 * @return List
	 * @throws DataAccessException
	 */
	public List getTaclUserinfoByMap(Map filter) throws ServiceException;

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

	public List getTaclUserinfoPageDataByArray(Object[] filter, int page_size,
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
	public List getTaclUserinfoPageDataByMap(Map filter, int page_size,
			int pageNo) throws ServiceException;

	/**
	 * 利用SQL数组条件来查询记录
	 * 
	 * @param sql
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public List getTaclUserinfoValueObjectWithSQLByArray(Object[] filter)
			throws ServiceException;

	/**
	 * 通过配置SQL来执行查询带多个参数的情况 包括SQL语句、存储过程等
	 * 
	 * @param queryName
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public List getTaclUserinfoValueObjectByNameQuery(String queryName,
			Object[] filter) throws ServiceException;

	/**
	 * 动态构造HQL参数
	 * 
	 * @param detachedCriteria
	 * @return
	 * @throws ServiceException
	 */
	public List getTaclUserinfoValueObjectByDetachedCriteria(
			DetachedCriteria detachedCriteria) throws ServiceException;

	/**
	 * 动态构造HQL参数
	 * 
	 * @param detachedCriteria
	 * @return
	 * @throws ServiceException
	 */
	public List getTaclUserinfoValueObjectByDetachedCriterias(
			DetachedCriteria detachedCriteria, int arg1, int arg2)
			throws ServiceException;
	/**
	 * 按用户名查找用户
	 * @param loginName
	 * @return
	 * @throws ServiceException
	 * @author Xiaojf
	 * @since 2011-6-14
	 */
	public TaclUserinfo getUserByLoginName(String loginName) throws ServiceException;
	/**
	 * 获取用户的权限
	 * @param user
	 * @return
	 * @throws ServiceException
	 * @author Xiaojf
	 * @since 2011-6-15
	 */
	public Map<String, Map<String, String>> getUserRoles(TaclUserinfo user) throws ServiceException;
	/**
	 * 保存用户的数据权限设置
	 * @param userId
	 * @param modules
	 * @return
	 * @throws ServiceException
	 * @author Xiaojf
	 * @since 2011-6-20
	 */
	public boolean saveDataSet(String userId, String modules) throws ServiceException;
	/**
	 * 保存用户的机构树权限设置
	 * @param userId
	 * @param treeNodes
	 * @return
	 * @throws ServiceException
	 * @author xjf721
	 * @since 2012-1-4
	 */
	public boolean saveTreeSet(String userId, String treeNodes) throws ServiceException;
	/**
	 * 收回用户所有的权限
	 * @param userId
	 * @throws ServiceException
	 * @author Xiaojf
	 * @since 2011-6-25
	 */
	public void clearRole(String userId) throws ServiceException;
	/**
	 * 用户授予特权时显示模块与数据权限树
	 * @param type
	 * @param userid
	 * @return
	 * @throws ServiceException
	 * @author Xiaojf
	 * @since 2011-6-27
	 */
	public String getPrivilegeTree(String userId, String curUserId) throws ServiceException;
	/**
	 * 保存特权设置
	 * @param moduleSet
	 * @param dataSet
	 * @throws ServiceException
	 * @author Xiaojf
	 * @since 2011-6-28
	 */
	public void savePrivilege(String userId, String moduleSet) throws ServiceException;
	
	public List<Map<String, Object>> getModuleTree(String userId) throws ServiceException;
}
