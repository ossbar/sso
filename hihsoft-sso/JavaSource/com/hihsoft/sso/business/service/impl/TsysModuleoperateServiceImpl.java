/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.business.service.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hihframework.exception.ServiceException;
import com.hihsoft.baseclass.service.impl.BaseServiceImpl;
import com.hihsoft.sso.business.model.TsysModuleoperate;
import com.hihsoft.sso.business.service.TsysModuleoperateService;

@Service(value ="tsysModuleoperateService")
public class TsysModuleoperateServiceImpl extends BaseServiceImpl implements
		TsysModuleoperateService {
	/************ 查询所有的TSYSMODULEOPERATE ******************/
	private static final String ALLTSYSMODULEOPERATE_HQL = " from TsysModuleoperate";

	/************ 通过主键查询TSYSMODULEOPERATE ******************/
	private static final String TSYSMODULEOPERATEById_HQL = " from TsysModuleoperate tsysModuleoperate where tsysModuleoperate.operateid=?";

	/**
	 * 新增、修改TsysModuleoperate信息
	 * 
	 * @param tsysModuleoperate
	 * @throws DataAccessException
	 */
	public void saveOrUpdateTsysModuleoperate(
			TsysModuleoperate tsysModuleoperate) throws ServiceException {
		baseDAO.saveOrUpdateVO(tsysModuleoperate);
	}

	/**
	 * 删除TsysModuleoperate信息
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public void deleteTsysModuleoperate(String id) throws ServiceException {
		TsysModuleoperate tsysModuleoperate = this.getTsysModuleoperateById(id);
		baseDAO.deleteVO(tsysModuleoperate);
	}

	/**
	 * 通过HQL构造查询条件来查询符合条件的TsysModuleoperate信息
	 * 
	 * @param hql
	 * @return List
	 * @throws DataAccessException
	 */
	public List<?> getTsysModuleoperateByHQL(String hql) throws ServiceException {
		return baseDAO.getValueObjectsByHQL(hql);

	}

	/**
	 * 查询所有的TsysModuleoperate信息
	 * 
	 * @param hql
	 * @return List
	 * @throws DataAccessException
	 */
	public List<?> getAllTsysModuleoperate() throws ServiceException {
		return baseDAO.getValueObjectsByHQL(ALLTSYSMODULEOPERATE_HQL);
	}

	/**
	 * 根据主键查询TsysModuleoperate信息明细
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public TsysModuleoperate getTsysModuleoperateById(String id)
			throws ServiceException {
		TsysModuleoperate tsysModuleoperate = null;
		List<?> list = baseDAO.getValueObjectsByHQL(TSYSMODULEOPERATEById_HQL,
				new Object[] { id });
		if (!list.isEmpty() && list.size() > 0) {
			tsysModuleoperate = (TsysModuleoperate) list.get(0);
		}
		return tsysModuleoperate;
	}

	/**
	 * 把查询条件构造成数组来查询TsysModuleoperate信息
	 * 
	 * @param Object
	 *            [] object
	 * @return List
	 * @throws DataAccessException
	 */
	public List<?> getTsysModuleoperateByHQL(String hql, Object... object)
			throws ServiceException {
		return baseDAO.getValueObjectsByHQL(hql, object);
	}

	/**
	 * 取得分页总数
	 * 
	 * @param hql
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */

	public int getTsysModuleoperateDataTotalNum(String hql, Object... object)
			throws ServiceException {
		return baseDAO.getDataTotalNum(hql, object);
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
	public List<?> getTsysModuleoperatePageDataByHQL(String hql, Object object,
			int page_size, int pageNo) throws ServiceException {
		return baseDAO.getPageDataByHQL(hql, object, page_size, pageNo);
	}

	/**
	 * 分页查询。
	 * 
	 * @param hql
	 * @param obj
	 *            ：MAP条件构造
	 * @param page_size
	 * @param pageNo
	 * @return
	 * @throws DataAccessException
	 */
	public List<?> getTsysModuleoperatePageDataByHQL(String hql, Map<String, Object> obj,
			int page_size, int pageNo) throws ServiceException {
		return baseDAO.getPageDataByHQL(hql, obj, page_size, pageNo);
	}

	/**
	 * 利用SQL数组条件来查询记录
	 * 
	 * @param sql
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public List<?> getTsysModuleoperateValueObjectBySQL(String sql, Object... object)
			throws ServiceException {
		return baseDAO.getValueObjectBySQL(sql, object);
	}

	/**
	 * 通过配置SQL来执行查询带多个参数的情况 包括SQL语句、存储过程等
	 * 
	 * @param queryName
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public List<?> getTsysModuleoperateValueObjectByNameQuery(String queryName,
			Object... object) throws ServiceException {
		return baseDAO.getValueObjectByNameQuery(queryName, object);
	}

	/**
	 * 动态构造HQL参数
	 * 
	 * @param detachedCriteria
	 * @return
	 * @throws ServiceException
	 */
	public List<?> getTsysModuleoperateValueObjectByDetachedCriteria(
			DetachedCriteria detachedCriteria) throws ServiceException {
		return baseDAO.getValueObjectByDetachedCriteria(detachedCriteria);
	}

	/**
	 * 动态构造HQL参数
	 * 
	 * @param detachedCriteria
	 * @return
	 * @throws ServiceException
	 */
	public List<?> getTsysModuleoperateValueObjectByDetachedCriterias(
			DetachedCriteria detachedCriteria, int arg1, int arg2)
			throws ServiceException {
		return baseDAO.getValueObjectByDetachedCriterias(detachedCriteria,
				arg1, arg2);
	}
}
