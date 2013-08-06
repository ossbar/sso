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
@Service(value ="tsysUploadService")
public class TsysUploadServiceImpl extends BaseServiceImpl implements TsysUploadService{
	/************查询所有的TSYSUPLOAD******************/
	private static final String ALLTSYSUPLOAD_HQL=" from TsysUpload";
	
	/************通过主键查询TSYSUPLOAD******************/
	private static final String TSYSUPLOADById_HQL=" from TsysUpload tsysUpload where tsysUpload.uploadid=?";
	
	/************通过不同的条件组合，利用Hibernate HQL查询TSYSUPLOAD******************/
	private static final StringBuffer QUERY_TSYSUPLOAD_HQL = new StringBuffer(" from TsysUpload tsysUpload where 1=1");
	
	/************通过不同的条件组合，利用JDBC SQL查询TSYSUPLOAD******************/
	private static final StringBuffer QUERY_TSYSUPLOAD_SQL = new StringBuffer("select * from T_SYS_UPLOAD t where 1=1");

	/**
	 * 新增、修改TsysUpload信息
	 * 
	 * @param tsysUpload
	 * @throws DataAccessException
	 */
	public void saveOrUpdateTsysUpload(TsysUpload tsysUpload) throws ServiceException {
		baseDAO.saveOrUpdateVO(tsysUpload);
	}

	/**
	 * 删除TsysUpload信息
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	  public  void deleteTsysUpload(String id) throws ServiceException{
		  TsysUpload tsysUpload=this.getTsysUploadById(id);
		  baseDAO.deleteVO(tsysUpload);
	  }


	 /**
		 * 通过HQL构造查询条件来查询符合条件的TsysUpload信息
		 * 
		 * @param hql
		 * @return List
		 * @throws DataAccessException
		 */
	  public List<?> getTsysUploadByHQL(String hql) throws ServiceException{
		  return baseDAO.getValueObjectsByHQL(hql);
		  
	  }
		 /**
		 * 查询所有的TsysUpload信息
		 * 
		 * @param hql
		 * @return List
		 * @throws DataAccessException
		 */
	  public List<?> getAllTsysUpload() throws ServiceException{
		  return  baseDAO.getValueObjectsByHQL(ALLTSYSUPLOAD_HQL);
	  }

	 /**
		 * 根据主键查询TsysUpload信息明细
		 * 
		 * @param id
		 * @throws DataAccessException
		 */
	  public TsysUpload getTsysUploadById(String id) throws ServiceException{
		  TsysUpload tsysUpload=null;
		  List<?> list=baseDAO.getValueObjectsByHQL(TSYSUPLOADById_HQL,new Object[]{id});
		  if(!list.isEmpty()&&list.size()>0){
			  tsysUpload=(TsysUpload)list.get(0);
		  }
		 return tsysUpload;
	  }
	 /**
		 * 把查询条件构造成数组来查询TsysUpload信息
		 * 
		 * @param Object[]
		 *            object
		 * @return List
		 * @throws DataAccessException
		 */
	  public List<?> getTsysUploadByArray(Object[] filter) throws ServiceException{
		  return baseDAO.getValueObjectsByHQL(QUERY_TSYSUPLOAD_HQL.toString(),filter);
	  }
	  /**
		 * 取得分页总数
		 * 
		 * @param hql
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */

	    public int getTsysUploadDataTotalNum(Object[] filter)
	            throws ServiceException{
	    	return baseDAO.getDataTotalNum(QUERY_TSYSUPLOAD_HQL.toString(),filter);
	    }
	    
	    /**
		 * 把查询条件构造成数组来查询TsysUpload信息
		 * 
		 * @param Map
		 *            filter
		 * @return List
		 * @throws DataAccessException
		 */
	  public List<?> getTsysUploadByMap(Map<String, Object> filter) throws ServiceException{
		  return baseDAO.getPageDataByHQL(QUERY_TSYSUPLOAD_HQL.toString(),filter);
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

     public List<?> getTsysUploadPageDataByArray(Object[] filter, int page_size,
	                                 int pageNo) throws ServiceException{
	  return baseDAO.getPageDataByHQL(QUERY_TSYSUPLOAD_HQL.toString(),filter,page_size,pageNo);
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
	    public List<?> getTsysUploadPageDataByMap(Map<String, Object> filter, int page_size, int pageNo)
	            throws ServiceException{
	    	return baseDAO.getPageDataByHQL(QUERY_TSYSUPLOAD_HQL.toString(),filter,page_size,pageNo);
	    }

	    /**
		 * 利用SQL数组条件来查询记录
		 * 
		 * @param sql
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */
	    public List<?> getTsysUploadValueObjectWithSQLByArray(Object[] filter) throws ServiceException{
	    	return baseDAO.getValueObjectBySQL(QUERY_TSYSUPLOAD_SQL.toString(),filter);
	    }

	    
		/**
		 * 通过配置SQL来执行查询带多个参数的情况 包括SQL语句、存储过程等
		 * 
		 * @param queryName
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */
		public List<?> getTsysUploadValueObjectByNameQuery(String queryName,Object[] filter)throws ServiceException{
			return baseDAO.getValueObjectByNameQuery(queryName,filter);
		}
		/**
		 * 动态构造HQL参数
		 * @param detachedCriteria
		 * @return
		 * @throws ServiceException
		 */
		public List<?> getTsysUploadValueObjectByDetachedCriteria(
				DetachedCriteria detachedCriteria) throws ServiceException{
			return baseDAO.getValueObjectByDetachedCriteria(detachedCriteria);
		}

		/**
		 * 动态构造HQL参数
		 * @param detachedCriteria
		 * @return
		 * @throws ServiceException
		 */
		public List<?> getTsysUploadValueObjectByDetachedCriterias(
				DetachedCriteria detachedCriteria, int arg1, int arg2)
				throws ServiceException{
			return baseDAO.getValueObjectByDetachedCriterias(detachedCriteria, arg1, arg2);
		}


}
