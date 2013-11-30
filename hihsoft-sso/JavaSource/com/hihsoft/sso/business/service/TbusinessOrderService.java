/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.hihsoft.sso.business.service;
import java.util.*;

import org.hibernate.criterion.DetachedCriteria;
import com.hihsoft.sso.business.model.*;
import com.hihsoft.baseclass.service.BaseService;
import com.hihframework.exception.ServiceException;

/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft
 * @version 1.0
 */

public interface TbusinessOrderService extends BaseService{
	
	/**
	 * 新增、修改TbusinessOrder信息
	 * 
	 * @param tbusinessOrder
	 * @throws DataAccessException
	 */
	 public  void saveOrUpdateTbusinessOrder(TbusinessOrder tbusinessOrder) throws ServiceException ;
	 
	 public  void mergeOrUpdateTbusinessOrder(TbusinessOrder tbusinessOrder) throws ServiceException ;
	   /**
		 * 删除TbusinessOrder信息
		 * 
		 * @param id
		 * @throws DataAccessException
		 */
	  public  void deleteTbusinessOrder(String id) throws ServiceException;

	 /**
		 * 通过HQL构造查询条件来查询符合条件的TbusinessOrder信息
		 * 
		 * @param hql
		 * @return List
		 * @throws DataAccessException
		 */
	  public List getTbusinessOrderByHQL(String hql) throws ServiceException;
	  
		 /**
		 * 查询所有的TbusinessOrder信息
		 * 
		 * @param hql
		 * @return List
		 * @throws DataAccessException
		 */
	  public List getAllTbusinessOrder() throws ServiceException;

	 /**
		 * 根据主键查询TbusinessOrder信息明细
		 * 
		 * @param id
		 * @throws DataAccessException
		 */
	  public TbusinessOrder getTbusinessOrderById(String id) throws ServiceException;
	 /**
		 * 把查询条件构造成数组来查询TbusinessOrder信息
		 * 
		 * @param Object[]
		 *            filter
		 * @return List
		 * @throws DataAccessException
		 */
	  public List getTbusinessOrderByArray(Object[] filter) throws ServiceException;
	  /**
		 * 取得分页总数
		 * 
		 * @param hql
		 * @param filter
		 * @return
		 * @throws DataAccessException
		 */

	    public int getTbusinessOrderDataTotalNum(Object[] filter)
	            throws ServiceException;
	    
	    
	    /**
		 * 把查询条件构造成MAP来查询TbusinessOrder信息
		 * 
		 * @param Map
		 *            filter
		 * @return List
		 * @throws DataAccessException
		 */
	  public List getTbusinessOrderByMap(Map filter) throws ServiceException;

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

	    public List getTbusinessOrderPageDataByArray(Object[] filter, int page_size,
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
	    public List getTbusinessOrderPageDataByMap(Map filter, int page_size, int pageNo)
	            throws ServiceException;

	    /**
		 * 利用SQL数组条件来查询记录
		 * 
		 * @param sql
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */
	    public List getTbusinessOrderValueObjectWithSQLByArray(Object[] filter) throws ServiceException;

		/**
		 * 通过配置SQL来执行查询带多个参数的情况 包括SQL语句、存储过程等
		 * 
		 * @param queryName
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */
		public List getTbusinessOrderValueObjectByNameQuery(String queryName,Object[] filter)throws ServiceException;
		
		/**
		 * 动态构造HQL参数
		 * @param detachedCriteria
		 * @return
		 * @throws ServiceException
		 */
		public List getTbusinessOrderValueObjectByDetachedCriteria(
				DetachedCriteria detachedCriteria) throws ServiceException;

		/**
		 * 动态构造HQL参数
		 * @param detachedCriteria
		 * @return
		 * @throws ServiceException
		 */
		public List getTbusinessOrderValueObjectByDetachedCriterias(
				DetachedCriteria detachedCriteria, int arg1, int arg2)
				throws ServiceException;

	  


}
