package com.hihsoft.sso.sysmonitor.syslogs.service;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;

import com.hihframework.exception.ServiceException;
import com.hihsoft.baseclass.service.BaseService;
import com.hihsoft.sso.sysmonitor.syslogs.model.TlogSysexception;

/**
 * <p> Title: </p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft.co.,ltd
 * @version 1.0
 */

public interface TlogSysexceptionService extends BaseService{
	
	/**
	 * 新增、修改TlogSysexception信息
	 * 
	 * @param tlogSysexception
	 * @throws DataAccessException
	 */
	 public  void saveOrUpdateTlogSysexception(TlogSysexception tlogSysexception) throws ServiceException ;
	   /**
		 * 删除TlogSysexception信息
		 * 
		 * @param id
		 * @throws DataAccessException
		 */
	  public  void deleteTlogSysexception(String id) throws ServiceException;

	 /**
		 * 通过HQL构造查询条件来查询符合条件的TlogSysexception信息
		 * 
		 * @param hql
		 * @return List
		 * @throws DataAccessException
		 */
	  public List getTlogSysexceptionByHQL(String hql) throws ServiceException;

	 /**
		 * 根据主键查询TlogSysexception信息明细
		 * 
		 * @param id
		 * @throws DataAccessException
		 */
	  public TlogSysexception getTlogSysexceptionById(String id) throws ServiceException;
	 /**
		 * 把查询条件构造成数组来查询TlogSysexception信息
		 * 
		 * @param Object[]
		 *            object
		 * @return List
		 * @throws DataAccessException
		 */
	  public List getTlogSysexceptionByHQL(String hql,Object[] object) throws ServiceException;
	  /**
		 * 取得分页总数
		 * 
		 * @param hql
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */

	    public int getTlogSysexceptionDataTotalNum(String hql, Object[] object)
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

	    public List getTlogSysexceptionPageDataByHQL(String hql, Object[] object, int page_size,
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
	    public List getTlogSysexceptionPageDataByHQL(String hql, Map obj, int page_size, int pageNo)
	            throws ServiceException;

	    /**
		 * 利用SQL数组条件来查询记录
		 * 
		 * @param sql
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */
	    public List getTlogSysexceptionValueObjectBySQL(String sql, Object[] object) throws ServiceException;

	    
		/**
		 * 通过配置SQL来执行查询带多个参数的情况 包括SQL语句、存储过程等
		 * 
		 * @param queryName
		 * @param object
		 * @return
		 * @throws DataAccessException
		 */
		public List getTlogSysexceptionValueObjectByNameQuery(String queryName,Object[] object)throws ServiceException;
		
		/**
		 * 动态构造HQL参数
		 * @param detachedCriteria
		 * @return
		 * @throws ServiceException
		 */
		public List getTlogSysexceptionValueObjectByDetachedCriteria(
				DetachedCriteria detachedCriteria) throws ServiceException;

		/**
		 * 动态构造HQL参数
		 * @param detachedCriteria
		 * @return
		 * @throws ServiceException
		 */
		public List getTlogSysexceptionValueObjectByDetachedCriterias(
				DetachedCriteria detachedCriteria, int arg1, int arg2)
				throws ServiceException;

	  


}
