package com.hihsoft.sso.business.service.impl;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;


import com.hihframework.exception.ServiceException;
import com.hihsoft.baseclass.service.impl.BaseServiceImpl;
import com.hihsoft.sso.business.model.*;
import com.hihsoft.sso.business.service.*;

@Service(value ="tsysDataprivilegeService")
public class TsysDataprivilegeServiceImpl extends BaseServiceImpl implements TsysDataprivilegeService{
	/************查询所有的TSYSDATAPRIVILEGE******************/
	private static final String ALLTSYSDATAPRIVILEGE_HQL=" from TsysDataprivilege";
	
	/************通过主键查询TSYSDATAPRIVILEGE******************/
	private static final String TSYSDATAPRIVILEGEById_HQL=" from TsysDataprivilege tsysDataprivilege where tsysDataprivilege.dataid=?";
	
	/************通过不同的条件组合，利用Hibernate HQL查询TSYSDATAPRIVILEGE******************/
	private static final StringBuffer QUERY_TSYSDATAPRIVILEGE_HQL = new StringBuffer(" from TsysDataprivilege tsysDataprivilege where 1=1");
	
	/************通过不同的条件组合，利用JDBC SQL查询TSYSDATAPRIVILEGE******************/
	private static final StringBuffer QUERY_TSYSDATAPRIVILEGE_SQL = new StringBuffer("select * from T_SYS_DATAPRIVILEGE t where 1=1");

	/**
	 * 新增、修改TsysDataprivilege信息
	 * 
	 * @param tsysDataprivilege
	 * @throws DataAccessException
	 */
	public void saveOrUpdateTsysDataprivilege(TsysDataprivilege tsysDataprivilege) throws ServiceException {
		baseDAO.saveOrUpdateVO(tsysDataprivilege);
	}

	/**
	 * 删除TsysDataprivilege信息
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	  public  void deleteTsysDataprivilege(String id) throws ServiceException{
		  TsysDataprivilege tsysDataprivilege=this.getTsysDataprivilegeById(id);
		  baseDAO.deleteVO(tsysDataprivilege);
	  }


	 /**
		 * 通过HQL构造查询条件来查询符合条件的TsysDataprivilege信息
		 * 
		 * @param hql
		 * @return List
		 * @throws DataAccessException
		 */
	  public List getTsysDataprivilegeByHQL(String hql) throws ServiceException{
		  return baseDAO.getValueObjectsByHQL(hql);
		  
	  }
		 /**
		 * 查询所有的TsysDataprivilege信息
		 * 
		 * @param hql
		 * @return List
		 * @throws DataAccessException
		 */
	  public List getAllTsysDataprivilege() throws ServiceException{
		  return  baseDAO.getValueObjectsByHQL(ALLTSYSDATAPRIVILEGE_HQL);
	  }

	 /**
		 * 根据主键查询TsysDataprivilege信息明细
		 * 
		 * @param id
		 * @throws DataAccessException
		 */
	  public TsysDataprivilege getTsysDataprivilegeById(String id) throws ServiceException{
		  TsysDataprivilege tsysDataprivilege=null;
		  List list=baseDAO.getValueObjectsByHQL(TSYSDATAPRIVILEGEById_HQL,new Object[]{id});
		  if(!list.isEmpty()&&list.size()>0){
			  tsysDataprivilege=(TsysDataprivilege)list.get(0);
		  }
		 return tsysDataprivilege;
	  }
	 /**
		 * 把查询条件构造成数组来查询TsysDataprivilege信息
		 * 
		 * @param Object[]
		 *            object
		 * @return List
		 * @throws DataAccessException
		 */
	  public List getTsysDataprivilegeByArray(Object[] filter) throws ServiceException{
		  return baseDAO.getValueObjectsByHQL(QUERY_TSYSDATAPRIVILEGE_HQL.toString(),filter);
	  }
	  /**
		 * 取得分页总数
		 * 
		 * @param hql
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */

	    public int getTsysDataprivilegeDataTotalNum(Object[] filter)
	            throws ServiceException{
	    	return baseDAO.getDataTotalNum(QUERY_TSYSDATAPRIVILEGE_HQL.toString(),filter);
	    }
	    
	    /**
		 * 把查询条件构造成数组来查询TsysDataprivilege信息
		 * 
		 * @param Map
		 *            filter
		 * @return List
		 * @throws DataAccessException
		 */
	  public List getTsysDataprivilegeByMap(Map filter) throws ServiceException{
		  return baseDAO.getPageDataByHQL(QUERY_TSYSDATAPRIVILEGE_HQL.toString(),filter);
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

     public List getTsysDataprivilegePageDataByArray(Object[] filter, int page_size,
	                                 int pageNo) throws ServiceException{
	  return baseDAO.getPageDataByHQL(QUERY_TSYSDATAPRIVILEGE_HQL.toString(),filter,page_size,pageNo);
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
	    public List getTsysDataprivilegePageDataByMap(Map filter, int page_size, int pageNo)
	            throws ServiceException{
	    	 String id = (String) filter.get("id");
	    	return baseDAO.getPageDataByHQL(QUERY_TSYSDATAPRIVILEGE_HQL.toString(),filter,page_size,pageNo);
	    }

	    /**
		 * 利用SQL数组条件来查询记录
		 * 
		 * @param sql
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */
	    public List getTsysDataprivilegeValueObjectWithSQLByArray(Object[] filter) throws ServiceException{
	    	return baseDAO.getValueObjectBySQL(QUERY_TSYSDATAPRIVILEGE_SQL.toString(),filter);
	    }

	    
		/**
		 * 通过配置SQL来执行查询带多个参数的情况 包括SQL语句、存储过程等
		 * 
		 * @param queryName
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */
		public List getTsysDataprivilegeValueObjectByNameQuery(String queryName,Object[] filter)throws ServiceException{
			return baseDAO.getValueObjectByNameQuery(queryName,filter);
		}
		/**
		 * 动态构造HQL参数
		 * @param detachedCriteria
		 * @return
		 * @throws ServiceException
		 */
		public List getTsysDataprivilegeValueObjectByDetachedCriteria(
				DetachedCriteria detachedCriteria) throws ServiceException{
			return baseDAO.getValueObjectByDetachedCriteria(detachedCriteria);
		}

		/**
		 * 动态构造HQL参数
		 * @param detachedCriteria
		 * @return
		 * @throws ServiceException
		 */
		public List getTsysDataprivilegeValueObjectByDetachedCriterias(
				DetachedCriteria detachedCriteria, int arg1, int arg2)
				throws ServiceException{
			return baseDAO.getValueObjectByDetachedCriterias(detachedCriteria, arg1, arg2);
		}


}
