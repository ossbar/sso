package com.hihsoft.sso.business.service;

import java.util.*;

import org.hibernate.criterion.DetachedCriteria;

import com.hihframework.exception.ServiceException;
import com.hihsoft.baseclass.service.BaseService;
import com.hihsoft.sso.business.model.*;

/**
 * Title:平台(子系统)服务
 * Description:
 * Copyright: Copyright (c) 2013
 * Company:hihsoft.co.,ltd
 * @author hihsoft.co.,ltd
 * @version 1.0
 */

public interface TsysFlatService extends BaseService {

	/**
	 * 新增、修改TsysFlat信息
	 * 
	 * @param tsysFlat
	 * @throws DataAccessException
	 */
	public void saveOrUpdateTsysFlat(TsysFlat tsysFlat) throws ServiceException;

	/**
	 * 删除TsysFlat信息
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public void deleteTsysFlat(String id) throws ServiceException;

	/**
	 * 通过HQL构造查询条件来查询符合条件的TsysFlat信息
	 * 
	 * @param hql
	 * @return List
	 * @throws DataAccessException
	 */
	public List getTsysFlatByHQL(String hql) throws ServiceException;

	/**
	 * 查询所有的TsysFlat信息
	 * 
	 * @param hql
	 * @return List
	 * @throws DataAccessException
	 */
	public List getAllTsysFlat() throws ServiceException;

	/**
	 * 根据主键查询TsysFlat信息明细
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public TsysFlat getTsysFlatById(String id) throws ServiceException;

	/**
	 * 把查询条件构造成数组来查询TsysFlat信息
	 * 
	 * @param Object
	 *            [] filter
	 * @return List
	 * @throws DataAccessException
	 */
	public List getTsysFlatByArray(Object[] filter) throws ServiceException;

	/**
	 * 取得分页总数
	 * 
	 * @param hql
	 * @param filter
	 * @return
	 * @throws DataAccessException
	 */

	public int getTsysFlatDataTotalNum(Object[] filter) throws ServiceException;

	/**
	 * 把查询条件构造成MAP来查询TsysFlat信息
	 * 
	 * @param Map
	 *            filter
	 * @return List
	 * @throws DataAccessException
	 */
	public List getTsysFlatByMap(Map filter) throws ServiceException;

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

	public List getTsysFlatPageDataByArray(Object[] filter, int page_size,
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
	public List getTsysFlatPageDataByMap(Map filter, int page_size, int pageNo)
			throws ServiceException;

	/**
	 * 利用SQL数组条件来查询记录
	 * 
	 * @param sql
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public List getTsysFlatValueObjectWithSQLByArray(Object[] filter)
			throws ServiceException;

	/**
	 * 通过配置SQL来执行查询带多个参数的情况 包括SQL语句、存储过程等
	 * 
	 * @param queryName
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public List getTsysFlatValueObjectByNameQuery(String queryName,
			Object[] filter) throws ServiceException;

	/**
	 * 动态构造HQL参数
	 * 
	 * @param detachedCriteria
	 * @return
	 * @throws ServiceException
	 */
	public List getTsysFlatValueObjectByDetachedCriteria(
			DetachedCriteria detachedCriteria) throws ServiceException;

	/**
	 * 动态构造HQL参数
	 * 
	 * @param detachedCriteria
	 * @return
	 * @throws ServiceException
	 */
	public List getTsysFlatValueObjectByDetachedCriterias(
			DetachedCriteria detachedCriteria, int arg1, int arg2)
			throws ServiceException;
	/**
	 * 按用户查询已授权子系统
	 * @param userId
	 * @return
	 * @throws ServiceException
	 * @author Xiaojf
	 * @since 2011-6-14
	 */
	public List<TsysFlat> getFlatsByUser(String userId) throws ServiceException;
}
