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
import com.hihsoft.sso.business.model.TaclRoleprivilege;
import com.hihsoft.sso.business.service.TaclRoleprivilegeService;

@Service(value ="taclRoleprivilegeService")
public class TaclRoleprivilegeServiceImpl extends BaseServiceImpl implements
		TaclRoleprivilegeService {
	/************ 查询所有的TACLROLEPRIVILEGE ******************/
	private static final String ALLTACLROLEPRIVILEGE_HQL = " from TaclRoleprivilege";

	/************ 通过主键查询TACLROLEPRIVILEGE ******************/
	private static final String TACLROLEPRIVILEGEById_HQL = " from TaclRoleprivilege taclRoleprivilege where taclRoleprivilege.rid=?";

	/**
	 * 新增、修改TaclRoleprivilege信息
	 * 
	 * @param taclRoleprivilege
	 * @throws DataAccessException
	 */
	public void saveOrUpdateTaclRoleprivilege(
			TaclRoleprivilege taclRoleprivilege) throws ServiceException {
		baseDAO.saveOrUpdateVO(taclRoleprivilege);
	}

	/**
	 * 删除TaclRoleprivilege信息
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public void deleteTaclRoleprivilege(String id) throws ServiceException {
		TaclRoleprivilege taclRoleprivilege = this.getTaclRoleprivilegeById(id);
		baseDAO.deleteVO(taclRoleprivilege);
	}

	/**
	 * 通过HQL构造查询条件来查询符合条件的TaclRoleprivilege信息
	 * 
	 * @param hql
	 * @return List
	 * @throws DataAccessException
	 */
	public List<?> getTaclRoleprivilegeByHQL(String hql) throws ServiceException {
		return baseDAO.getValueObjectsByHQL(hql);

	}

	/**
	 * 查询所有的TaclRoleprivilege信息
	 * 
	 * @param hql
	 * @return List
	 * @throws DataAccessException
	 */
	public List<?> getAllTaclRoleprivilege() throws ServiceException {
		return baseDAO.getValueObjectsByHQL(ALLTACLROLEPRIVILEGE_HQL);
	}

	/**
	 * 根据主键查询TaclRoleprivilege信息明细
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public TaclRoleprivilege getTaclRoleprivilegeById(String id)
			throws ServiceException {
		TaclRoleprivilege taclRoleprivilege = null;
		List<?> list = baseDAO.getValueObjectsByHQL(TACLROLEPRIVILEGEById_HQL,
				new Object[] { id });
		if (!list.isEmpty() && list.size() > 0) {
			taclRoleprivilege = (TaclRoleprivilege) list.get(0);
		}
		return taclRoleprivilege;
	}

	/**
	 * 把查询条件构造成数组来查询TaclRoleprivilege信息
	 * 
	 * @param Object
	 *            [] object
	 * @return List
	 * @throws DataAccessException
	 */
	public List<?> getTaclRoleprivilegeByHQL(String hql, Object[] object)
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

	public int getTaclRoleprivilegeDataTotalNum(String hql, Object[] object)
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

	public List<?> getTaclRoleprivilegePageDataByHQL(String hql, Object[] object,
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
	public List<?> getTaclRoleprivilegePageDataByHQL(String hql, Map<String, Object> obj,
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
	public List<?> getTaclRoleprivilegeValueObjectBySQL(String sql, Object[] object)
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
	public List<?> getTaclRoleprivilegeValueObjectByNameQuery(String queryName,
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
	public List<?> getTaclRoleprivilegeValueObjectByDetachedCriteria(
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
	public List<?> getTaclRoleprivilegeValueObjectByDetachedCriterias(
			DetachedCriteria detachedCriteria, int arg1, int arg2)
			throws ServiceException {
		return baseDAO.getValueObjectByDetachedCriterias(detachedCriteria,
				arg1, arg2);
	}
	
	@Override
	public boolean saveRoleSet(String roleId, String moduleSet) throws ServiceException {
		String[] moduleSets = moduleSet.split(";");
		String hql = "delete from TaclRoleprivilege where roleid='"+roleId+"'";
		executeHQL(hql);
		for (String set : moduleSets) {
			if ("".equals(set)) continue;
			String[] str = set.split(",");
			TaclRoleprivilege tp = new TaclRoleprivilege();
			tp.setOperateid(str[0]);
			tp.setModuleid(str[1]);
			tp.setRoleid(roleId);
			saveOrUpdateTaclRoleprivilege(tp);
		}
//		String[] dataSets = dataSet.split(",");
//		hql = "delete from TsysDataprivilege where roleid='"+roleId+"'";
//		executeHQL(hql);
//		for (String set : dataSets) {
//			if ("".equals(set)) continue;
//			TsysDataprivilege td = new TsysDataprivilege();
//			td.setOrgid(set);
//			td.setRoleid(roleId);
//			baseDAO.saveOrUpdateVO(td);
//		}
		return true;
	}
}
