package com.hihsoft.sso.sysmonitor.syslogs.service.impl;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hihframework.exception.ServiceException;
import com.hihsoft.baseclass.service.impl.BaseServiceImpl;
import com.hihsoft.sso.sysmonitor.syslogs.model.TlogDbsql;
import com.hihsoft.sso.sysmonitor.syslogs.service.TlogDbsqlService;
/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2011 </p>
 * <p> Company:javahih.co.,ltd </p>
 *
 * @author zhujw
 * @version 1.0
 */
@Service(value ="tlogDbsqlService")
public class TlogDbsqlServiceImpl extends BaseServiceImpl implements TlogDbsqlService{

	/**
	 * 新增、修改TlogDbsql信息
	 * 
	 * @param tlogDbsql
	 * @throws DataAccessException
	 */
	public void saveOrUpdateTlogDbsql(TlogDbsql tlogDbsql) throws ServiceException {
		baseDAO.saveOrUpdateVO(tlogDbsql);
	}

	/**
	 * 删除TlogDbsql信息
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	  public  void deleteTlogDbsql(String id) throws ServiceException{
		  TlogDbsql tlogDbsql=this.getTlogDbsqlById(id);
		  baseDAO.deleteVO(tlogDbsql);
	  }


	 /**
		 * 通过HQL构造查询条件来查询符合条件的TlogDbsql信息
		 * 
		 * @param hql
		 * @return List
		 * @throws DataAccessException
		 */
	  public List getTlogDbsqlByHQL(String hql) throws ServiceException{
		  return baseDAO.getValueObjectsByHQL(hql);
		  
	  }

	 /**
		 * 根据主键查询TlogDbsql信息明细
		 * 
		 * @param id
		 * @throws DataAccessException
		 */
	  public TlogDbsql getTlogDbsqlById(String id) throws ServiceException{
		  TlogDbsql tlogDbsql=null;
		   String hql=" from TlogDbsql tlogDbsql where tlogDbsql.database_oid='"+id+"'";
		  List list=baseDAO.getValueObjectsByHQL(hql);
		  if(!list.isEmpty()&&list.size()>0){
			  tlogDbsql=(TlogDbsql)list.get(0);
		  }
		 return tlogDbsql;
	  }
	 /**
		 * 把查询条件构造成数组来查询TlogDbsql信息
		 * 
		 * @param Object[]
		 *            object
		 * @return List
		 * @throws DataAccessException
		 */
	  public List getTlogDbsqlByHQL(String hql,Object[] object) throws ServiceException{
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

	    public int getTlogDbsqlDataTotalNum(String hql, Object[] object)
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

     public List getTlogDbsqlPageDataByHQL(String hql, Object[] object, int page_size,
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
	    public List getTlogDbsqlPageDataByHQL(String hql, Map obj, int page_size, int pageNo)
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
	    public List getTlogDbsqlValueObjectBySQL(String sql, Object[] object) throws ServiceException{
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
		public List getTlogDbsqlValueObjectByNameQuery(String queryName,Object[] object)throws ServiceException{
			return baseDAO.getValueObjectByNameQuery(queryName,object);
		}
		/**
		 * 动态构造HQL参数
		 * @param detachedCriteria
		 * @return
		 * @throws ServiceException
		 */
		public List getTlogDbsqlValueObjectByDetachedCriteria(
				DetachedCriteria detachedCriteria) throws ServiceException{
			return baseDAO.getValueObjectByDetachedCriteria(detachedCriteria);
		}

		/**
		 * 动态构造HQL参数
		 * @param detachedCriteria
		 * @return
		 * @throws ServiceException
		 */
		public List getTlogDbsqlValueObjectByDetachedCriterias(
				DetachedCriteria detachedCriteria, int arg1, int arg2)
				throws ServiceException{
			return baseDAO.getValueObjectByDetachedCriterias(detachedCriteria, arg1, arg2);
		}


}
