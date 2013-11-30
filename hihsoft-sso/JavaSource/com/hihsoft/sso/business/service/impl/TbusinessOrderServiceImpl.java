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

import com.hihsoft.baseclass.service.impl.BaseServiceImpl;
import com.hihframework.exception.ServiceException;
import com.hihsoft.sso.business.service.*;
import com.hihsoft.sso.business.model.*;
/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft
 * @version 1.0
 */

@Service(value ="tbusinessOrderService")
public class TbusinessOrderServiceImpl extends BaseServiceImpl implements TbusinessOrderService{
	/************查询所有的TBUSINESSORDER******************/
	private static final String ALLTBUSINESSORDER_HQL=" from TbusinessOrder";
	
	/************通过主键查询TBUSINESSORDER******************/
	private static final String TBUSINESSORDERById_HQL=" from TbusinessOrder tbusinessOrder where tbusinessOrder.id=?";
	
	/************通过不同的条件组合，利用Hibernate HQL查询TBUSINESSORDER******************/
	private static final StringBuffer QUERY_TBUSINESSORDER_HQL = new StringBuffer(" from TbusinessOrder tbusinessOrder where 1=1");
	
	/************通过不同的条件组合，利用JDBC SQL查询TBUSINESSORDER******************/
	private static final StringBuffer QUERY_TBUSINESSORDER_SQL = new StringBuffer("select * from t_business_order t where 1=1");

	/**
	 * 新增、修改TbusinessOrder信息
	 * 
	 * @param tbusinessOrder
	 * @throws DataAccessException
	 */
	public void saveOrUpdateTbusinessOrder(TbusinessOrder tbusinessOrder) throws ServiceException {
		baseDAO.saveOrUpdateVO(tbusinessOrder);
	}
	
	
	/**
	 * 删除TbusinessOrder信息
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	  public  void deleteTbusinessOrder(String id) throws ServiceException{
		  TbusinessOrder tbusinessOrder=this.getTbusinessOrderById(id);
		  baseDAO.deleteVO(tbusinessOrder);
	  }


	 /**
		 * 通过HQL构造查询条件来查询符合条件的TbusinessOrder信息
		 * 
		 * @param hql
		 * @return List
		 * @throws DataAccessException
		 */
	  public List getTbusinessOrderByHQL(String hql) throws ServiceException{
		  return baseDAO.getValueObjectsByHQL(hql);
		  
	  }
		 /**
		 * 查询所有的TbusinessOrder信息
		 * 
		 * @param hql
		 * @return List
		 * @throws DataAccessException
		 */
	  public List getAllTbusinessOrder() throws ServiceException{
		  return  baseDAO.getValueObjectsByHQL(ALLTBUSINESSORDER_HQL);
	  }

	 /**
		 * 根据主键查询TbusinessOrder信息明细
		 * 
		 * @param id
		 * @throws DataAccessException
		 */
	  public TbusinessOrder getTbusinessOrderById(String id) throws ServiceException{
		  TbusinessOrder tbusinessOrder=null;
		  List list=baseDAO.getValueObjectsByHQL(TBUSINESSORDERById_HQL,new Object[]{id});
		  if(!list.isEmpty()&&list.size()>0){
			  tbusinessOrder=(TbusinessOrder)list.get(0);
		  }
		 return tbusinessOrder;
	  }
	 /**
		 * 把查询条件构造成数组来查询TbusinessOrder信息
		 * 
		 * @param Object[]
		 *            object
		 * @return List
		 * @throws DataAccessException
		 */
	  public List getTbusinessOrderByArray(Object[] filter) throws ServiceException{
		  return baseDAO.getValueObjectsByHQL(QUERY_TBUSINESSORDER_HQL.toString(),filter);
	  }
	  /**
		 * 取得分页总数
		 * 
		 * @param hql
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */

	    public int getTbusinessOrderDataTotalNum(Object[] filter)
	            throws ServiceException{
	    	return baseDAO.getDataTotalNum(QUERY_TBUSINESSORDER_HQL.toString(),filter);
	    }
	    
	    /**
		 * 把查询条件构造成数组来查询TbusinessOrder信息
		 * 
		 * @param Map
		 *            filter
		 * @return List
		 * @throws DataAccessException
		 */
	  public List getTbusinessOrderByMap(Map filter) throws ServiceException{
		  return baseDAO.getPageDataByHQL(QUERY_TBUSINESSORDER_HQL.toString(),filter);
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

     public List getTbusinessOrderPageDataByArray(Object[] filter, int page_size,
	                                 int pageNo) throws ServiceException{
	  return baseDAO.getPageDataByHQL(QUERY_TBUSINESSORDER_HQL.toString(),filter,page_size,pageNo);
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
	    public List getTbusinessOrderPageDataByMap(Map filter, int page_size, int pageNo)
	            throws ServiceException{
	    	 String id = (String) filter.get("id");
	    	return baseDAO.getPageDataByHQL(QUERY_TBUSINESSORDER_HQL.toString(),filter,page_size,pageNo);
	    }

	    /**
		 * 利用SQL数组条件来查询记录
		 * 
		 * @param sql
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */
	    public List getTbusinessOrderValueObjectWithSQLByArray(Object[] filter) throws ServiceException{
	    	return baseDAO.getValueObjectBySQL(QUERY_TBUSINESSORDER_SQL.toString(),filter);
	    }

	    
		/**
		 * 通过配置SQL来执行查询带多个参数的情况 包括SQL语句、存储过程等
		 * 
		 * @param queryName
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */
		public List getTbusinessOrderValueObjectByNameQuery(String queryName,Object[] filter)throws ServiceException{
			return baseDAO.getValueObjectByNameQuery(queryName,filter);
		}
		/**
		 * 动态构造HQL参数
		 * @param detachedCriteria
		 * @return
		 * @throws ServiceException
		 */
		public List getTbusinessOrderValueObjectByDetachedCriteria(
				DetachedCriteria detachedCriteria) throws ServiceException{
			return baseDAO.getValueObjectByDetachedCriteria(detachedCriteria);
		}

		/**
		 * 动态构造HQL参数
		 * @param detachedCriteria
		 * @return
		 * @throws ServiceException
		 */
		public List getTbusinessOrderValueObjectByDetachedCriterias(
				DetachedCriteria detachedCriteria, int arg1, int arg2)
				throws ServiceException{
			return baseDAO.getValueObjectByDetachedCriterias(detachedCriteria, arg1, arg2);
		}


		@Override
		public void mergeOrUpdateTbusinessOrder(TbusinessOrder tbusinessOrder)
				throws ServiceException {
			
		}


}
