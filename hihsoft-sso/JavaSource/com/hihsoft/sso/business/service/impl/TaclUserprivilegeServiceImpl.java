/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.business.service.impl;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;


import com.hihframework.exception.ServiceException;
import com.hihsoft.baseclass.service.impl.BaseServiceImpl;
import com.hihsoft.sso.business.model.*;
import com.hihsoft.sso.business.service.*;

@Service(value ="taclUserprivilegeService")
public class TaclUserprivilegeServiceImpl extends BaseServiceImpl implements TaclUserprivilegeService{
	/************查询所有的TACLUSERPRIVILEGE******************/
	private static final String ALLTACLUSERPRIVILEGE_HQL=" from TaclUserprivilege";
	
	/************通过主键查询TACLUSERPRIVILEGE******************/
	private static final String TACLUSERPRIVILEGEById_HQL=" from TaclUserprivilege taclUserprivilege where taclUserprivilege.userprviid=?";
	
	/************通过不同的条件组合，利用Hibernate HQL查询TACLUSERPRIVILEGE******************/
	private static final StringBuffer QUERY_TACLUSERPRIVILEGE_HQL = new StringBuffer(" from TaclUserprivilege taclUserprivilege where 1=1");
	
	/************通过不同的条件组合，利用JDBC SQL查询TACLUSERPRIVILEGE******************/
	private static final StringBuffer QUERY_TACLUSERPRIVILEGE_SQL = new StringBuffer("select * from T_ACL_USERPRIVILEGE t where 1=1");

	/**
	 * 新增、修改TaclUserprivilege信息
	 * 
	 * @param taclUserprivilege
	 * @throws DataAccessException
	 */
	public void saveOrUpdateTaclUserprivilege(TaclUserprivilege taclUserprivilege) throws ServiceException {
		baseDAO.saveOrUpdateVO(taclUserprivilege);
	}

	/**
	 * 删除TaclUserprivilege信息
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	  public  void deleteTaclUserprivilege(String id) throws ServiceException{
		  TaclUserprivilege taclUserprivilege=this.getTaclUserprivilegeById(id);
		  baseDAO.deleteVO(taclUserprivilege);
	  }


	 /**
		 * 通过HQL构造查询条件来查询符合条件的TaclUserprivilege信息
		 * 
		 * @param hql
		 * @return List
		 * @throws DataAccessException
		 */
	  public List<?> getTaclUserprivilegeByHQL(String hql) throws ServiceException{
		  return baseDAO.getValueObjectsByHQL(hql);
		  
	  }
		 /**
		 * 查询所有的TaclUserprivilege信息
		 * 
		 * @param hql
		 * @return List
		 * @throws DataAccessException
		 */
	  public List<?> getAllTaclUserprivilege() throws ServiceException{
		  return  baseDAO.getValueObjectsByHQL(ALLTACLUSERPRIVILEGE_HQL);
	  }

	 /**
		 * 根据主键查询TaclUserprivilege信息明细
		 * 
		 * @param id
		 * @throws DataAccessException
		 */
	  public TaclUserprivilege getTaclUserprivilegeById(String id) throws ServiceException{
		  TaclUserprivilege taclUserprivilege=null;
		  List<?> list=baseDAO.getValueObjectsByHQL(TACLUSERPRIVILEGEById_HQL,new Object[]{id});
		  if(!list.isEmpty()&&list.size()>0){
			  taclUserprivilege=(TaclUserprivilege)list.get(0);
		  }
		 return taclUserprivilege;
	  }
	 /**
		 * 把查询条件构造成数组来查询TaclUserprivilege信息
		 * 
		 * @param Object[]
		 *            object
		 * @return List
		 * @throws DataAccessException
		 */
	  public List<?> getTaclUserprivilegeByArray(Object[] filter) throws ServiceException{
		  return baseDAO.getValueObjectsByHQL(QUERY_TACLUSERPRIVILEGE_HQL.toString(),filter);
	  }
	  /**
		 * 取得分页总数
		 * 
		 * @param hql
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */

	    public int getTaclUserprivilegeDataTotalNum(Object[] filter)
	            throws ServiceException{
	    	return baseDAO.getDataTotalNum(QUERY_TACLUSERPRIVILEGE_HQL.toString(),filter);
	    }
	    
	    /**
		 * 把查询条件构造成数组来查询TaclUserprivilege信息
		 * 
		 * @param Map
		 *            filter
		 * @return List
		 * @throws DataAccessException
		 */
	  public List<?> getTaclUserprivilegeByMap(Map<String, Object> filter) throws ServiceException{
		  return baseDAO.getPageDataByHQL(QUERY_TACLUSERPRIVILEGE_HQL.toString(),filter);
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

     public List<?> getTaclUserprivilegePageDataByArray(Object[] filter, int page_size,
	                                 int pageNo) throws ServiceException{
	  return baseDAO.getPageDataByHQL(QUERY_TACLUSERPRIVILEGE_HQL.toString(),filter,page_size,pageNo);
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
	    public List<?> getTaclUserprivilegePageDataByMap(Map<String, Object> filter, int page_size, int pageNo)
	            throws ServiceException{
	    	return baseDAO.getPageDataByHQL(QUERY_TACLUSERPRIVILEGE_HQL.toString(),filter,page_size,pageNo);
	    }

	    /**
		 * 利用SQL数组条件来查询记录
		 * 
		 * @param sql
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */
	    public List<?> getTaclUserprivilegeValueObjectWithSQLByArray(Object[] filter) throws ServiceException{
	    	return baseDAO.getValueObjectBySQL(QUERY_TACLUSERPRIVILEGE_SQL.toString(),filter);
	    }

	    
		/**
		 * 通过配置SQL来执行查询带多个参数的情况 包括SQL语句、存储过程等
		 * 
		 * @param queryName
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */
		public List<?> getTaclUserprivilegeValueObjectByNameQuery(String queryName,Object[] filter)throws ServiceException{
			return baseDAO.getValueObjectByNameQuery(queryName,filter);
		}
		/**
		 * 动态构造HQL参数
		 * @param detachedCriteria
		 * @return
		 * @throws ServiceException
		 */
		public List<?> getTaclUserprivilegeValueObjectByDetachedCriteria(
				DetachedCriteria detachedCriteria) throws ServiceException{
			return baseDAO.getValueObjectByDetachedCriteria(detachedCriteria);
		}

		/**
		 * 动态构造HQL参数
		 * @param detachedCriteria
		 * @return
		 * @throws ServiceException
		 */
		public List<?> getTaclUserprivilegeValueObjectByDetachedCriterias(
				DetachedCriteria detachedCriteria, int arg1, int arg2)
				throws ServiceException{
			return baseDAO.getValueObjectByDetachedCriterias(detachedCriteria, arg1, arg2);
		}


}
