package com.hihsoft.sso.business.service;

import java.util.*;

import org.hibernate.criterion.DetachedCriteria;

import com.hihframework.exception.ServiceException;
import com.hihsoft.baseclass.service.BaseService;
import com.hihsoft.sso.business.model.*;

/**
 * Title:角色定义服务
 * Description:
 * Copyright: Copyright (c) 2013
 * Company:hihsoft.co.,ltd
 * @author hihsoft.co.,ltd
 * @version 1.0
 */

public interface TaclRoleService extends BaseService {

	/**
	 * 新增、修改TaclRole信息
	 * 
	 * @param taclRole
	 * @throws DataAccessException
	 */
	public void saveOrUpdateTaclRole(TaclRole taclRole) throws ServiceException;

	/**
	 * 删除TaclRole信息
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public void deleteTaclRole(String id) throws ServiceException;

	/**
	 * 通过HQL构造查询条件来查询符合条件的TaclRole信息
	 * 
	 * @param hql
	 * @return List
	 * @throws DataAccessException
	 */
	public List getTaclRoleByHQL(String hql) throws ServiceException;

	/**
	 * 查询所有的TaclRole信息
	 * 
	 * @param hql
	 * @return List
	 * @throws DataAccessException
	 */
	public List getAllTaclRole() throws ServiceException;

	/**
	 * 根据主键查询TaclRole信息明细
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public TaclRole getTaclRoleById(String id) throws ServiceException;

	/**
	 * 根据角色ID获得TaclRoleuser的所有User信息
	 * 
	 * @param roleId
	 * 
	 * */
	public List<TaclRoleuser> getTaclRoleUserByRoleId(final String id)
			throws ServiceException;

	/**
	 * 把查询条件构造成数组来查询TaclRole信息
	 * 
	 * @param Object
	 *            [] filter
	 * @return List
	 * @throws DataAccessException
	 */
	public List getTaclRoleByArray(Object[] filter) throws ServiceException;

	/**
	 * 取得分页总数
	 * 
	 * @param hql
	 * @param filter
	 * @return
	 * @throws DataAccessException
	 */

	public int getTaclRoleDataTotalNum(Object[] filter) throws ServiceException;

	/**
	 * 把查询条件构造成MAP来查询TaclRole信息
	 * 
	 * @param Map
	 *            filter
	 * @return List
	 * @throws DataAccessException
	 */
	public List getTaclRoleByMap(Map filter) throws ServiceException;

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

	public List getTaclRolePageDataByArray(Object[] filter, int page_size,
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
	public List getTaclRolePageDataByMap(Map filter, int page_size, int pageNo)
			throws ServiceException;

	/**
	 * 利用SQL数组条件来查询记录
	 * 
	 * @param sql
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public List getTaclRoleValueObjectWithSQLByArray(Object[] filter)
			throws ServiceException;

	/**
	 * 通过配置SQL来执行查询带多个参数的情况 包括SQL语句、存储过程等
	 * 
	 * @param queryName
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public List getTaclRoleValueObjectByNameQuery(String queryName,
			Object[] filter) throws ServiceException;

	/**
	 * 动态构造HQL参数
	 * 
	 * @param detachedCriteria
	 * @return
	 * @throws ServiceException
	 */
	public List getTaclRoleValueObjectByDetachedCriteria(
			DetachedCriteria detachedCriteria) throws ServiceException;

	/**
	 * 动态构造HQL参数
	 * 
	 * @param detachedCriteria
	 * @return
	 * @throws ServiceException
	 */
	public List getTaclRoleValueObjectByDetachedCriterias(
			DetachedCriteria detachedCriteria, int arg1, int arg2)
			throws ServiceException;
	
	public List<Map<String, Object>> getModuleTree(String roleId, String curUserId, String curOrgId) throws ServiceException;
}
