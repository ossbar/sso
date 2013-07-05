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
 * Title:数据权限的服务
 * Description:
 * Copyright: Copyright (c) 2013
 * Company:hihsoft.co.,ltd
 * @author hihsoft.co.,ltd
 * @version 1.0
 */

public interface TsysDataprivilegeService extends BaseService{
	
	/**
	 * 新增、修改TsysDataprivilege信息
	 * 
	 * @param tsysDataprivilege
	 * @throws DataAccessException
	 */
	 public  void saveOrUpdateTsysDataprivilege(TsysDataprivilege tsysDataprivilege) throws ServiceException ;
	   /**
		 * 删除TsysDataprivilege信息
		 * 
		 * @param id
		 * @throws DataAccessException
		 */
	  public  void deleteTsysDataprivilege(String id) throws ServiceException;

	 /**
		 * 通过HQL构造查询条件来查询符合条件的TsysDataprivilege信息
		 * 
		 * @param hql
		 * @return List
		 * @throws DataAccessException
		 */
	  public List getTsysDataprivilegeByHQL(String hql) throws ServiceException;
	  
		 /**
		 * 查询所有的TsysDataprivilege信息
		 * 
		 * @param hql
		 * @return List
		 * @throws DataAccessException
		 */
	  public List getAllTsysDataprivilege() throws ServiceException;

	 /**
		 * 根据主键查询TsysDataprivilege信息明细
		 * 
		 * @param id
		 * @throws DataAccessException
		 */
	  public TsysDataprivilege getTsysDataprivilegeById(String id) throws ServiceException;
	 /**
		 * 把查询条件构造成数组来查询TsysDataprivilege信息
		 * 
		 * @param Object[]
		 *            filter
		 * @return List
		 * @throws DataAccessException
		 */
	  public List getTsysDataprivilegeByArray(Object[] filter) throws ServiceException;
	  /**
		 * 取得分页总数
		 * 
		 * @param hql
		 * @param filter
		 * @return
		 * @throws DataAccessException
		 */

	    public int getTsysDataprivilegeDataTotalNum(Object[] filter)
	            throws ServiceException;
	    
	    
	    /**
		 * 把查询条件构造成MAP来查询TsysDataprivilege信息
		 * 
		 * @param Map
		 *            filter
		 * @return List
		 * @throws DataAccessException
		 */
	  public List getTsysDataprivilegeByMap(Map filter) throws ServiceException;

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

	    public List getTsysDataprivilegePageDataByArray(Object[] filter, int page_size,
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
	    public List getTsysDataprivilegePageDataByMap(Map filter, int page_size, int pageNo)
	            throws ServiceException;

	    /**
		 * 利用SQL数组条件来查询记录
		 * 
		 * @param sql
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */
	    public List getTsysDataprivilegeValueObjectWithSQLByArray(Object[] filter) throws ServiceException;

		/**
		 * 通过配置SQL来执行查询带多个参数的情况 包括SQL语句、存储过程等
		 * 
		 * @param queryName
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */
		public List getTsysDataprivilegeValueObjectByNameQuery(String queryName,Object[] filter)throws ServiceException;
		
		/**
		 * 动态构造HQL参数
		 * @param detachedCriteria
		 * @return
		 * @throws ServiceException
		 */
		public List getTsysDataprivilegeValueObjectByDetachedCriteria(
				DetachedCriteria detachedCriteria) throws ServiceException;

		/**
		 * 动态构造HQL参数
		 * @param detachedCriteria
		 * @return
		 * @throws ServiceException
		 */
		public List getTsysDataprivilegeValueObjectByDetachedCriterias(
				DetachedCriteria detachedCriteria, int arg1, int arg2)
				throws ServiceException;

	  


}
