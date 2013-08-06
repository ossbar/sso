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
 * Title:区域信息的服务
 * Description:
 * Copyright: Copyright (c) 2013
 * Company:hihsoft.co.,ltd
 * @author hihsoft.co.,ltd
 * @version 1.0
 */

public interface TsysAreacodingService extends BaseService{
	
	/**
	 * 新增、修改TsysAreacoding信息
	 * 
	 * @param tsysAreacoding
	 * @throws DataAccessException
	 */
	 public  void saveOrUpdateTsysAreacoding(TsysAreacoding tsysAreacoding) throws ServiceException ;
	   /**
		 * 删除TsysAreacoding信息
		 * 
		 * @param id
		 * @throws DataAccessException
		 */
	  public  void deleteTsysAreacoding(String id) throws ServiceException;

	 /**
		 * 通过HQL构造查询条件来查询符合条件的TsysAreacoding信息
		 * 
		 * @param hql
		 * @return List
		 * @throws DataAccessException
		 */
	  public List<?> getTsysAreacodingByHQL(String hql) throws ServiceException;
	  
		 /**
		 * 查询所有的TsysAreacoding信息
		 * 
		 * @param hql
		 * @return List
		 * @throws DataAccessException
		 */
	  public List<?> getAllTsysAreacoding() throws ServiceException;

	 /**
		 * 根据主键查询TsysAreacoding信息明细
		 * 
		 * @param id
		 * @throws DataAccessException
		 */
	  public TsysAreacoding getTsysAreacodingById(String id) throws ServiceException;
	 /**
		 * 把查询条件构造成数组来查询TsysAreacoding信息
		 * 
		 * @param Object[]
		 *            object
		 * @return List
		 * @throws DataAccessException
		 */
	  public List<?> getTsysAreacodingByHQL(String hql,Object[] object) throws ServiceException;
	  /**
		 * 取得分页总数
		 * 
		 * @param hql
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */

	    public int getTsysAreacodingDataTotalNum(String hql, Object[] object)
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

	    public List<?> getTsysAreacodingPageDataByHQL(String hql, Object[] object, int page_size,
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
	    public List<?> getTsysAreacodingPageDataByHQL(String hql, Map<String, Object> obj, int page_size, int pageNo)
	            throws ServiceException;

	    /**
		 * 利用SQL数组条件来查询记录
		 * 
		 * @param sql
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */
	    public List<?> getTsysAreacodingValueObjectBySQL(String sql, Object[] object) throws ServiceException;

	    
		/**
		 * 通过配置SQL来执行查询带多个参数的情况 包括SQL语句、存储过程等
		 * 
		 * @param queryName
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */
		public List<?> getTsysAreacodingValueObjectByNameQuery(String queryName,Object[] object)throws ServiceException;
		
		/**
		 * 动态构造HQL参数
		 * @param detachedCriteria
		 * @return
		 * @throws ServiceException
		 */
		public List<?> getTsysAreacodingValueObjectByDetachedCriteria(
				DetachedCriteria detachedCriteria) throws ServiceException;

		/**
		 * 动态构造HQL参数
		 * @param detachedCriteria
		 * @return
		 * @throws ServiceException
		 */
		public List<?> getTsysAreacodingValueObjectByDetachedCriterias(
				DetachedCriteria detachedCriteria, int arg1, int arg2)
				throws ServiceException;
		
		public Map<String, Object> saveFillParamMap(String type) throws Exception;


}
