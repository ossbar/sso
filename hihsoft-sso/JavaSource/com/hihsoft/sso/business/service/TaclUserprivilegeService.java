package com.hihsoft.sso.business.service;
import java.util.*;

import org.hibernate.criterion.DetachedCriteria;

import com.hihframework.exception.ServiceException;
import com.hihsoft.baseclass.service.BaseService;
import com.hihsoft.sso.business.model.*;

/**
 * Title:用户分配权限的服务
 * Description:
 * Copyright: Copyright (c) 2013
 * Company:hihsoft.co.,ltd
 * @author hihsoft.co.,ltd
 * @version 1.0
 */

public interface TaclUserprivilegeService extends BaseService{
	
	/**
	 * 新增、修改TaclUserprivilege信息
	 * 
	 * @param taclUserprivilege
	 * @throws DataAccessException
	 */
	 public  void saveOrUpdateTaclUserprivilege(TaclUserprivilege taclUserprivilege) throws ServiceException ;
	   /**
		 * 删除TaclUserprivilege信息
		 * 
		 * @param id
		 * @throws DataAccessException
		 */
	  public  void deleteTaclUserprivilege(String id) throws ServiceException;

	 /**
		 * 通过HQL构造查询条件来查询符合条件的TaclUserprivilege信息
		 * 
		 * @param hql
		 * @return List
		 * @throws DataAccessException
		 */
	  public List getTaclUserprivilegeByHQL(String hql) throws ServiceException;
	  
		 /**
		 * 查询所有的TaclUserprivilege信息
		 * 
		 * @param hql
		 * @return List
		 * @throws DataAccessException
		 */
	  public List getAllTaclUserprivilege() throws ServiceException;

	 /**
		 * 根据主键查询TaclUserprivilege信息明细
		 * 
		 * @param id
		 * @throws DataAccessException
		 */
	  public TaclUserprivilege getTaclUserprivilegeById(String id) throws ServiceException;
	 /**
		 * 把查询条件构造成数组来查询TaclUserprivilege信息
		 * 
		 * @param Object[]
		 *            filter
		 * @return List
		 * @throws DataAccessException
		 */
	  public List getTaclUserprivilegeByArray(Object[] filter) throws ServiceException;
	  /**
		 * 取得分页总数
		 * 
		 * @param hql
		 * @param filter
		 * @return
		 * @throws DataAccessException
		 */

	    public int getTaclUserprivilegeDataTotalNum(Object[] filter)
	            throws ServiceException;
	    
	    
	    /**
		 * 把查询条件构造成MAP来查询TaclUserprivilege信息
		 * 
		 * @param Map
		 *            filter
		 * @return List
		 * @throws DataAccessException
		 */
	  public List getTaclUserprivilegeByMap(Map filter) throws ServiceException;

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

	    public List getTaclUserprivilegePageDataByArray(Object[] filter, int page_size,
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
	    public List getTaclUserprivilegePageDataByMap(Map filter, int page_size, int pageNo)
	            throws ServiceException;

	    /**
		 * 利用SQL数组条件来查询记录
		 * 
		 * @param sql
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */
	    public List getTaclUserprivilegeValueObjectWithSQLByArray(Object[] filter) throws ServiceException;

		/**
		 * 通过配置SQL来执行查询带多个参数的情况 包括SQL语句、存储过程等
		 * 
		 * @param queryName
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */
		public List getTaclUserprivilegeValueObjectByNameQuery(String queryName,Object[] filter)throws ServiceException;
		
		/**
		 * 动态构造HQL参数
		 * @param detachedCriteria
		 * @return
		 * @throws ServiceException
		 */
		public List getTaclUserprivilegeValueObjectByDetachedCriteria(
				DetachedCriteria detachedCriteria) throws ServiceException;

		/**
		 * 动态构造HQL参数
		 * @param detachedCriteria
		 * @return
		 * @throws ServiceException
		 */
		public List getTaclUserprivilegeValueObjectByDetachedCriterias(
				DetachedCriteria detachedCriteria, int arg1, int arg2)
				throws ServiceException;

	  


}
