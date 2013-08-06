/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.business.service.impl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;


import com.hihframework.exception.ServiceException;
import com.hihsoft.baseclass.service.impl.BaseServiceImpl;
import com.hihsoft.sso.business.model.*;
import com.hihsoft.sso.business.service.*;

@Service(value ="tsysDutyService")
public class TsysDutyServiceImpl extends BaseServiceImpl implements TsysDutyService{
	/************查询所有的TSYSDUTY******************/
	private static final String ALLTSYSDUTY_HQL=" from TsysDuty";
	
	/************通过主键查询TSYSDUTY******************/
	private static final String TSYSDUTYById_HQL=" from TsysDuty tsysDuty where tsysDuty.dutyid=?";
	
	/************通过不同的条件组合，利用Hibernate HQL查询TSYSDUTY******************/
	private static final StringBuffer QUERY_TSYSDUTY_HQL = new StringBuffer(" from TsysDuty tsysDuty where 1=1");
	
	/************通过不同的条件组合，利用JDBC SQL查询TSYSDUTY******************/
	private static final StringBuffer QUERY_TSYSDUTY_SQL = new StringBuffer("select * from T_SYS_DUTY t where 1=1");

	/**
	 * 新增、修改TsysDuty信息
	 * 
	 * @param tsysDuty
	 * @throws DataAccessException
	 */
	public void saveOrUpdateTsysDuty(TsysDuty tsysDuty) throws ServiceException {
		baseDAO.saveOrUpdateVO(tsysDuty);
	}

	/**
	 * 删除TsysDuty信息
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	  public  void deleteTsysDuty(String id) throws ServiceException{
		  TsysDuty tsysDuty=this.getTsysDutyById(id);
		  baseDAO.deleteVO(tsysDuty);
	  }


	 /**
		 * 通过HQL构造查询条件来查询符合条件的TsysDuty信息
		 * 
		 * @param hql
		 * @return List
		 * @throws DataAccessException
		 */
	  public List<?> getTsysDutyByHQL(String hql) throws ServiceException{
		  return baseDAO.getValueObjectsByHQL(hql);
		  
	  }
		 /**
		 * 查询所有的TsysDuty信息
		 * 
		 * @param hql
		 * @return List
		 * @throws DataAccessException
		 */
	  public List<?> getAllTsysDuty() throws ServiceException{
		  return  baseDAO.getValueObjectsByHQL(ALLTSYSDUTY_HQL);
	  }

	 /**
		 * 根据主键查询TsysDuty信息明细
		 * 
		 * @param id
		 * @throws DataAccessException
		 */
	  public TsysDuty getTsysDutyById(String id) throws ServiceException{
		  TsysDuty tsysDuty=null;
		  List<?> list=baseDAO.getValueObjectsByHQL(TSYSDUTYById_HQL,new Object[]{id});
		  if(!list.isEmpty()&&list.size()>0){
			  tsysDuty=(TsysDuty)list.get(0);
		  }
		 return tsysDuty;
	  }
	 /**
		 * 把查询条件构造成数组来查询TsysDuty信息
		 * 
		 * @param Object[]
		 *            object
		 * @return List
		 * @throws DataAccessException
		 */
	  public List<?> getTsysDutyByArray(Object[] filter) throws ServiceException{
		  return baseDAO.getValueObjectsByHQL(QUERY_TSYSDUTY_HQL.toString(),filter);
	  }
	  /**
		 * 取得分页总数
		 * 
		 * @param hql
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */

	    public int getTsysDutyDataTotalNum(Object[] filter)
	            throws ServiceException{
	    	return baseDAO.getDataTotalNum(QUERY_TSYSDUTY_HQL.toString(),filter);
	    }
	    
	    /**
		 * 把查询条件构造成数组来查询TsysDuty信息
		 * 
		 * @param Map
		 *            filter
		 * @return List
		 * @throws DataAccessException
		 */
	  public List<?> getTsysDutyByMap(Map<String, Object> filter) throws ServiceException{
		  return baseDAO.getPageDataByHQL(QUERY_TSYSDUTY_HQL.toString(),filter);
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

     public List<?> getTsysDutyPageDataByArray(Object[] filter, int page_size,
	                                 int pageNo) throws ServiceException{
	  return baseDAO.getPageDataByHQL(QUERY_TSYSDUTY_HQL.toString(),filter,page_size,pageNo);
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
	    public List<?> getTsysDutyPageDataByMap(Map<String, Object> filter, int page_size, int pageNo)
	            throws ServiceException{
	    	return baseDAO.getPageDataByHQL(QUERY_TSYSDUTY_HQL.toString(),filter,page_size,pageNo);
	    }

	    /**
		 * 利用SQL数组条件来查询记录
		 * 
		 * @param sql
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */
	    public List<?> getTsysDutyValueObjectWithSQLByArray(Object[] filter) throws ServiceException{
	    	return baseDAO.getValueObjectBySQL(QUERY_TSYSDUTY_SQL.toString(),filter);
	    }

	    
		/**
		 * 通过配置SQL来执行查询带多个参数的情况 包括SQL语句、存储过程等
		 * 
		 * @param queryName
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */
		public List<?> getTsysDutyValueObjectByNameQuery(String queryName,Object[] filter)throws ServiceException{
			return baseDAO.getValueObjectByNameQuery(queryName,filter);
		}
		/**
		 * 动态构造HQL参数
		 * @param detachedCriteria
		 * @return
		 * @throws ServiceException
		 */
		public List<?> getTsysDutyValueObjectByDetachedCriteria(
				DetachedCriteria detachedCriteria) throws ServiceException{
			return baseDAO.getValueObjectByDetachedCriteria(detachedCriteria);
		}

		/**
		 * 动态构造HQL参数
		 * @param detachedCriteria
		 * @return
		 * @throws ServiceException
		 */
		public List<?> getTsysDutyValueObjectByDetachedCriterias(
				DetachedCriteria detachedCriteria, int arg1, int arg2)
				throws ServiceException{
			return baseDAO.getValueObjectByDetachedCriterias(detachedCriteria, arg1, arg2);
		}

		@Override
		public List<TaclDutyuser> getUserAllByDutyId(String dutyid)
				throws ServiceException {
			String hql = "from TaclDutyuser d where d.userid in " +
					"(select du.userid from TaclDutyuser du where du.dutyid = ?)";
			@SuppressWarnings("unchecked")
			List<TaclDutyuser> userSet = (List<TaclDutyuser>) getValueObjectsByHQL(hql, dutyid);
			return userSet; 
		}

		@Override
		public List<TaclDutyuser> getTaclDutyuserByDutyid(final String dutyid)
				throws ServiceException {
			return baseDAO.doInHibernate(new HibernateCallback<List<TaclDutyuser>>() {
				@Override
				public List<TaclDutyuser> doInHibernate(Session session)
						throws HibernateException, SQLException {
					TsysDuty tsysDuty = null;
					Query query = session.createQuery(TSYSDUTYById_HQL);
					query.setString(0, dutyid);
					List<?> list = query.list();
					if (!list.isEmpty() && list.size() > 0) {
						tsysDuty = (TsysDuty) list.get(0);
						Hibernate.initialize(tsysDuty.getTaclDutyusers());
						list.clear();
						return new ArrayList<TaclDutyuser>(tsysDuty.getTaclDutyusers());
					}
					return new ArrayList<TaclDutyuser>();
				}
			});
		}


}
