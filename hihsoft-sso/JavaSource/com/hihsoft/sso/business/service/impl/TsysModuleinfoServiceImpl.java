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

@Service(value ="tsysModuleinfoService")
public class TsysModuleinfoServiceImpl extends BaseServiceImpl implements TsysModuleinfoService{
	/************查询所有的TSYSMODULEINFO******************/
	private static final String ALLTSYSMODULEINFO_HQL=" from TsysModuleinfo";
	
	/************通过主键查询TSYSMODULEINFO******************/
	private static final String TSYSMODULEINFOById_HQL=" from TsysModuleinfo tsysModuleinfo where tsysModuleinfo.moduleid=?";

	/**
	 * 新增、修改TsysModuleinfo信息
	 * 
	 * @param tsysModuleinfo
	 * @throws DataAccessException
	 */
	public void saveOrUpdateTsysModuleinfo(TsysModuleinfo tsysModuleinfo) throws ServiceException {
		baseDAO.saveOrUpdateVO(tsysModuleinfo);
	}

	/**
	 * 删除TsysModuleinfo信息
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	  public  void deleteTsysModuleinfo(String id) throws ServiceException{
		  TsysModuleinfo tsysModuleinfo=this.getTsysModuleinfoById(id);
		  baseDAO.deleteVO(tsysModuleinfo);
	  }


	 /**
		 * 通过HQL构造查询条件来查询符合条件的TsysModuleinfo信息
		 * 
		 * @param hql
		 * @return List
		 * @throws DataAccessException
		 */
	  public List<?> getTsysModuleinfoByHQL(String hql) throws ServiceException{
		  return baseDAO.getValueObjectsByHQL(hql);
		  
	  }
		 /**
		 * 查询所有的TsysModuleinfo信息
		 * 
		 * @param hql
		 * @return List
		 * @throws DataAccessException
		 */
	  public List<?> getAllTsysModuleinfo() throws ServiceException{
		  return  baseDAO.getValueObjectsByHQL(ALLTSYSMODULEINFO_HQL);
	  }

	 /**
		 * 根据主键查询TsysModuleinfo信息明细
		 * 
		 * @param id
		 * @throws DataAccessException
		 */
	  public TsysModuleinfo getTsysModuleinfoById(String id) throws ServiceException{
		  TsysModuleinfo tsysModuleinfo=null;
		  List<?> list=baseDAO.getValueObjectsByHQL(TSYSMODULEINFOById_HQL,new Object[]{id});
		  if(!list.isEmpty()&&list.size()>0){
			  tsysModuleinfo=(TsysModuleinfo)list.get(0);
		  }
		 return tsysModuleinfo;
	  }
	 /**
		 * 把查询条件构造成数组来查询TsysModuleinfo信息
		 * 
		 * @param Object[]
		 *            object
		 * @return List
		 * @throws DataAccessException
		 */
	  public List<?> getTsysModuleinfoByHQL(String hql,Object[] object) throws ServiceException{
		  return baseDAO.getValueObjectsByHQL(hql,object);
	  }
	  /**
		 * 取得分页总数
		 * 
		 * @param hql
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */

	    public int getTsysModuleinfoDataTotalNum(String hql, Object[] object)
	            throws ServiceException{
	    	return baseDAO.getDataTotalNum(hql,object);
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

     public List<?> getTsysModuleinfoPageDataByHQL(String hql, Object[] object, int page_size,
	                                 int pageNo) throws ServiceException{
	  return baseDAO.getPageDataByHQL(hql,object,page_size,pageNo);
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
	    public List<?> getTsysModuleinfoPageDataByHQL(String hql, Map<String, Object> obj, int page_size, int pageNo)
	            throws ServiceException{
	    	return baseDAO.getPageDataByHQL(hql,obj,page_size,pageNo);
	    }

	    /**
		 * 利用SQL数组条件来查询记录
		 * 
		 * @param sql
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */
	    public List<?> getTsysModuleinfoValueObjectBySQL(String sql, Object[] object) throws ServiceException{
	    	return baseDAO.getValueObjectBySQL(sql,object);
	    }

	    
		/**
		 * 通过配置SQL来执行查询带多个参数的情况 包括SQL语句、存储过程等
		 * 
		 * @param queryName
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */
		public List<?> getTsysModuleinfoValueObjectByNameQuery(String queryName,Object[] object)throws ServiceException{
			return baseDAO.getValueObjectByNameQuery(queryName,object);
		}
		/**
		 * 动态构造HQL参数
		 * @param detachedCriteria
		 * @return
		 * @throws ServiceException
		 */
		public List<?> getTsysModuleinfoValueObjectByDetachedCriteria(
				DetachedCriteria detachedCriteria) throws ServiceException{
			return baseDAO.getValueObjectByDetachedCriteria(detachedCriteria);
		}

		/**
		 * 动态构造HQL参数
		 * @param detachedCriteria
		 * @return
		 * @throws ServiceException
		 */
		public List<?> getTsysModuleinfoValueObjectByDetachedCriterias(
				DetachedCriteria detachedCriteria, int arg1, int arg2)
				throws ServiceException{
			return baseDAO.getValueObjectByDetachedCriterias(detachedCriteria, arg1, arg2);
		}


}
