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
import com.hihsoft.sso.business.model.TaclRole;
import com.hihsoft.sso.business.model.TaclRoleuser;
import com.hihsoft.sso.business.service.TaclRoleuserService;


@Service(value ="taclRoleuserService")
public class TaclRoleuserServiceImpl extends BaseServiceImpl implements
		TaclRoleuserService {
	/************ 查询所有的TACLROLEUSER ******************/
	private static final String ALLTACLROLEUSER_HQL = " from TaclRoleuser";

	/************ 通过主键查询TACLROLEUSER ******************/
	private static final String TACLROLEUSERById_HQL = " from TaclRoleuser taclRoleuser where taclRoleuser.roleuserid=?";

	/**
	 * 新增、修改TaclRoleuser信息
	 * 
	 * @param taclRoleuser
	 * @throws DataAccessException
	 */
	public void saveOrUpdateTaclRoleuser(TaclRoleuser taclRoleuser)
			throws ServiceException {
		baseDAO.saveOrUpdateVO(taclRoleuser);
	}

	/**
	 * 删除TaclRoleuser信息
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public void deleteTaclRoleuser(String id) throws ServiceException {
		TaclRoleuser taclRoleuser = this.getTaclRoleuserById(id);
		baseDAO.deleteVO(taclRoleuser);
	}

	/**
	 * 通过HQL构造查询条件来查询符合条件的TaclRoleuser信息
	 * 
	 * @param hql
	 * @return List
	 * @throws DataAccessException
	 */
	public List getTaclRoleuserByHQL(String hql) throws ServiceException {
		return baseDAO.getValueObjectsByHQL(hql);
	}
	
	/**
	 * 通过HQL构造查询条件来查询符合条件的TaclRoleuser信息
	 * 
	 * @param hql
	 * @return Object
	 * @throwa ServiceException
	 * */
	public TaclRoleuser getTaclRoleuserObjectByHQL(String hql) throws ServiceException{
	//	return baseDAO.getValueObjectsByHQL(hql);
		return null;
	}

	/**
	 * 查询所有的TaclRoleuser信息
	 * 
	 * @param hql
	 * @return List
	 * @throws DataAccessException
	 */
	public List getAllTaclRoleuser() throws ServiceException {
		return baseDAO.getValueObjectsByHQL(ALLTACLROLEUSER_HQL);
	}

	/**
	 * 根据主键查询TaclRoleuser信息明细
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public TaclRoleuser getTaclRoleuserById(String id) throws ServiceException {
		TaclRoleuser taclRoleuser = null;
		List list = baseDAO.getValueObjectsByHQL(TACLROLEUSERById_HQL,
				new Object[] { id });
		if (!list.isEmpty() && list.size() > 0) {
			taclRoleuser = (TaclRoleuser) list.get(0);
		}
		return taclRoleuser;
	}

	/**
	 * 把查询条件构造成数组来查询TaclRoleuser信息
	 * 
	 * @param Object
	 *            [] object
	 * @return List
	 * @throws DataAccessException
	 */
	public List getTaclRoleuserByHQL(String hql, Object[] object)
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

	public int getTaclRoleuserDataTotalNum(String hql, Object[] object)
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

	public List getTaclRoleuserPageDataByHQL(String hql, Object[] object,
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
	public List getTaclRoleuserPageDataByHQL(String hql, Map obj,
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
	public List getTaclRoleuserValueObjectBySQL(String sql, Object[] object)
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
	public List getTaclRoleuserValueObjectByNameQuery(String queryName,
			Object[] object) throws ServiceException {
		return baseDAO.getValueObjectByNameQuery(queryName, object);
	}

	/**
	 * 动态构造HQL参数
	 * 
	 * @param detachedCriteria
	 * @return
	 * @throws ServiceException
	 */
	public List getTaclRoleuserValueObjectByDetachedCriteria(
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
	public List getTaclRoleuserValueObjectByDetachedCriterias(
			DetachedCriteria detachedCriteria, int arg1, int arg2)
			throws ServiceException {
		return baseDAO.getValueObjectByDetachedCriterias(detachedCriteria,
				arg1, arg2);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TaclRole> getDefinedRole(final String userId) {
		String hql = "from TaclRole t left join fetch t.taclRoleusers r where r.userid=? and t.roleid=r.roleid)";
		return baseDAO.getValueObjectsByHQL(hql, userId);
	} 
}
