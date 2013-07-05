package com.hihsoft.sso.business.service.impl;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;


import com.hihframework.exception.ServiceException;
import com.hihsoft.baseclass.service.impl.BaseServiceImpl;
import com.hihsoft.sso.business.model.*;
import com.hihsoft.sso.business.service.*;

@Service(value ="tsysCodebuilderService")
public class TsysCodebuilderServiceImpl extends BaseServiceImpl implements TsysCodebuilderService{
	/************查询所有的TSYSCODEBUILDER******************/
	private static final String ALLTSYSCODEBUILDER_HQL=" from TsysCodebuilder";
	
	/************通过主键查询TSYSCODEBUILDER******************/
	private static final String TSYSCODEBUILDERById_HQL=" from TsysCodebuilder tsysCodebuilder where tsysCodebuilder.codeid=?";

	/**
	 * 新增、修改TsysCodebuilder信息
	 * 
	 * @param tsysCodebuilder
	 * @throws DataAccessException
	 */
	public void saveOrUpdateTsysCodebuilder(TsysCodebuilder tsysCodebuilder) throws ServiceException {
		baseDAO.saveOrUpdateVO(tsysCodebuilder);
	}

	/**
	 * 删除TsysCodebuilder信息
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	  public  void deleteTsysCodebuilder(String id) throws ServiceException{
		  TsysCodebuilder tsysCodebuilder=this.getTsysCodebuilderById(id);
		  baseDAO.deleteVO(tsysCodebuilder);
	  }


	 /**
		 * 通过HQL构造查询条件来查询符合条件的TsysCodebuilder信息
		 * 
		 * @param hql
		 * @return List
		 * @throws DataAccessException
		 */
	  public List getTsysCodebuilderByHQL(String hql) throws ServiceException{
		  return baseDAO.getValueObjectsByHQL(hql);
		  
	  }
		 /**
		 * 查询所有的TsysCodebuilder信息
		 * 
		 * @param hql
		 * @return List
		 * @throws DataAccessException
		 */
	  public List getAllTsysCodebuilder() throws ServiceException{
		  return  baseDAO.getValueObjectsByHQL(ALLTSYSCODEBUILDER_HQL);
	  }

	 /**
		 * 根据主键查询TsysCodebuilder信息明细
		 * 
		 * @param id
		 * @throws DataAccessException
		 */
	  public TsysCodebuilder getTsysCodebuilderById(String id) throws ServiceException{
		  TsysCodebuilder tsysCodebuilder=null;
		  List list=baseDAO.getValueObjectsByHQL(TSYSCODEBUILDERById_HQL,new Object[]{id});
		  if(!list.isEmpty()&&list.size()>0){
			  tsysCodebuilder=(TsysCodebuilder)list.get(0);
		  }
		 return tsysCodebuilder;
	  }
	 /**
		 * 把查询条件构造成数组来查询TsysCodebuilder信息
		 * 
		 * @param Object[]
		 *            object
		 * @return List
		 * @throws DataAccessException
		 */
	  public List getTsysCodebuilderByHQL(String hql,Object[] object) throws ServiceException{
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

	    public int getTsysCodebuilderDataTotalNum(String hql, Object[] object)
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

     public List getTsysCodebuilderPageDataByHQL(String hql, Object[] object, int page_size,
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
	    public List getTsysCodebuilderPageDataByHQL(String hql, Map obj, int page_size, int pageNo)
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
	    public List getTsysCodebuilderValueObjectBySQL(String sql, Object[] object) throws ServiceException{
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
		public List getTsysCodebuilderValueObjectByNameQuery(String queryName,Object[] object)throws ServiceException{
			return baseDAO.getValueObjectByNameQuery(queryName,object);
		}
		/**
		 * 动态构造HQL参数
		 * @param detachedCriteria
		 * @return
		 * @throws ServiceException
		 */
		public List getTsysCodebuilderValueObjectByDetachedCriteria(
				DetachedCriteria detachedCriteria) throws ServiceException{
			return baseDAO.getValueObjectByDetachedCriteria(detachedCriteria);
		}

		/**
		 * 动态构造HQL参数
		 * @param detachedCriteria
		 * @return
		 * @throws ServiceException
		 */
		public List getTsysCodebuilderValueObjectByDetachedCriterias(
				DetachedCriteria detachedCriteria, int arg1, int arg2)
				throws ServiceException{
			return baseDAO.getValueObjectByDetachedCriterias(detachedCriteria, arg1, arg2);
		}


}
