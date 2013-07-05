/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.business.service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hihframework.exception.ServiceException;
import com.hihsoft.baseclass.service.impl.BaseServiceImpl;
import com.hihsoft.sso.business.model.TsysAreacoding;
import com.hihsoft.sso.business.service.TsysAreacodingService;
import com.hihsoft.sso.systempublic.constants.Constant;

@Service(value ="tsysAreacodingService")
public class TsysAreacodingServiceImpl extends BaseServiceImpl implements
		TsysAreacodingService {
	/************查询所有的TSYSAREACODING******************/
	private static final String ALLTSYSAREACODING_HQL = " from TsysAreacoding";

	/************通过主键查询TSYSAREACODING******************/
	private static final String TSYSAREACODINGById_HQL = " from TsysAreacoding tsysAreacoding where tsysAreacoding.areacodingid=?";

	/**
	 * 新增、修改TsysAreacoding信息
	 * 
	 * @param tsysAreacoding
	 * @throws DataAccessException
	 */
	public void saveOrUpdateTsysAreacoding(TsysAreacoding tsysAreacoding)
			throws ServiceException {
		baseDAO.saveOrUpdateVO(tsysAreacoding);
	}

	/**
	 * 删除TsysAreacoding信息
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public void deleteTsysAreacoding(String id) throws ServiceException {
		TsysAreacoding tsysAreacoding = this.getTsysAreacodingById(id);
		baseDAO.deleteVO(tsysAreacoding);
	}

	/**
	 * 通过HQL构造查询条件来查询符合条件的TsysAreacoding信息
	 * 
	 * @param hql
	 * @return List
	 * @throws DataAccessException
	 */
	public List getTsysAreacodingByHQL(String hql) throws ServiceException {
		return baseDAO.getValueObjectsByHQL(hql);

	}

	/**
	* 查询所有的TsysAreacoding信息
	* 
	* @param hql
	* @return List
	* @throws DataAccessException
	*/
	public List getAllTsysAreacoding() throws ServiceException {
		return baseDAO.getValueObjectsByHQL(ALLTSYSAREACODING_HQL);
	}

	/**
	 * 根据主键查询TsysAreacoding信息明细
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public TsysAreacoding getTsysAreacodingById(String id)
			throws ServiceException {
		TsysAreacoding tsysAreacoding = null;
		List list = baseDAO.getValueObjectsByHQL(TSYSAREACODINGById_HQL,
				new Object[] { id });
		if (!list.isEmpty() && list.size() > 0) {
			tsysAreacoding = (TsysAreacoding) list.get(0);
		}
		return tsysAreacoding;
	}

	/**
	 * 把查询条件构造成数组来查询TsysAreacoding信息
	 * 
	 * @param Object[]
	 *            object
	 * @return List
	 * @throws DataAccessException
	 */
	public List getTsysAreacodingByHQL(String hql, Object[] object)
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

	public int getTsysAreacodingDataTotalNum(String hql, Object[] object)
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

	public List getTsysAreacodingPageDataByHQL(String hql, Object[] object,
			int page_size, int pageNo) throws ServiceException {
		return baseDAO.getPageDataByHQL(hql, object, page_size, pageNo);
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
	public List getTsysAreacodingPageDataByHQL(String hql, Map obj,
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
	public List getTsysAreacodingValueObjectBySQL(String sql, Object[] object)
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
	public List getTsysAreacodingValueObjectByNameQuery(String queryName,
			Object[] object) throws ServiceException {
		return baseDAO.getValueObjectByNameQuery(queryName, object);
	}

	/**
	 * 动态构造HQL参数
	 * @param detachedCriteria
	 * @return
	 * @throws ServiceException
	 */
	public List getTsysAreacodingValueObjectByDetachedCriteria(
			DetachedCriteria detachedCriteria) throws ServiceException {
		return baseDAO.getValueObjectByDetachedCriteria(detachedCriteria);
	}

	/**
	 * 动态构造HQL参数
	 * @param detachedCriteria
	 * @return
	 * @throws ServiceException
	 */
	public List getTsysAreacodingValueObjectByDetachedCriterias(
			DetachedCriteria detachedCriteria, int arg1, int arg2)
			throws ServiceException {
		return baseDAO.getValueObjectByDetachedCriterias(detachedCriteria,
				arg1, arg2);
	}

	/*
	 * (non-Javadoc) 填充地区信息
	 * 
	 * @see
	 * com.hihsoft.sso.business.service.TsysAreacodingService#saveFillParamMap(
	 * java.lang.String)
	 */
	@Override
	public Map saveFillParamMap(String type) throws Exception {
		List list = null;
		Map priviceMap = new HashMap();
		TsysAreacoding area = null;
		String hql = "from TsysAreacoding area";
		list = baseDAO.getValueObjectsByHQL(hql);
		if (type != null && type.equalsIgnoreCase(Constant.SYS_AREA_PROVINCE)) {
			Iterator it = list.iterator();
			while (it.hasNext()) {
				area = (TsysAreacoding) it.next();
				String parent = area.getParentarea();
				if (parent == null) {
					// 如果父地区为空，表示是省份
					priviceMap.put(area.getAreacodingid(), area.getAreaname());
				}
			}
			return priviceMap;
		} else {
			hql += " where area.parentarea='" + type + "'";
			list = baseDAO.getValueObjectsByHQL(hql);
			Iterator it = list.iterator();
			while (it.hasNext()) {
				area = (TsysAreacoding) it.next();
				priviceMap.put(area.getAreacodingid(), area.getAreaname());

			}
			return priviceMap;
		}
	}

}
