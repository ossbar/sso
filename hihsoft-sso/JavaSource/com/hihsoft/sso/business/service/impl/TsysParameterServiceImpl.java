/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.business.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.hihframework.exception.ServiceException;
import com.hihsoft.baseclass.service.impl.BaseServiceImpl;
import com.hihsoft.sso.business.model.TsysParameter;
import com.hihsoft.sso.business.service.TsysParameterService;
import com.hihsoft.sso.systempublic.constants.Constant;

@Service(value ="tsysParameterService")
public class TsysParameterServiceImpl extends BaseServiceImpl implements
		TsysParameterService {
	/************查询所有的TSYSPARAMETER******************/
	private static final String ALLTSYSPARAMETER_HQL = " from TsysParameter";

	/************通过主键查询TSYSPARAMETER******************/
	private static final String TSYSPARAMETERById_HQL = " from TsysParameter tsysParameter where tsysParameter.paraid=?";

	/**
	 * 新增、修改TsysParameter信息
	 * 
	 * @param tsysParameter
	 * @throws DataAccessException
	 */
	public void saveOrUpdateTsysParameter(TsysParameter tsysParameter)
			throws ServiceException {
		baseDAO.saveOrUpdateVO(tsysParameter);
	}

	/**
	 * 删除TsysParameter信息
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public void deleteTsysParameter(String id) throws ServiceException {
		TsysParameter tsysParameter = this.getTsysParameterById(id);
		baseDAO.deleteVO(tsysParameter);
	}

	/**
	 * 通过HQL构造查询条件来查询符合条件的TsysParameter信息
	 * 
	 * @param hql
	 * @return List
	 * @throws DataAccessException
	 */
	public List getTsysParameterByHQL(String hql) throws ServiceException {
		return baseDAO.getValueObjectsByHQL(hql);

	}

	/**
	* 查询所有的TsysParameter信息
	* 
	* @param hql
	* @return List
	* @throws DataAccessException
	*/
	public List getAllTsysParameter() throws ServiceException {
		return baseDAO.getValueObjectsByHQL(ALLTSYSPARAMETER_HQL);
	}

	/**
	 * 根据主键查询TsysParameter信息明细
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public TsysParameter getTsysParameterById(String id)
			throws ServiceException {
		TsysParameter tsysParameter = null;
		List list = baseDAO.getValueObjectsByHQL(TSYSPARAMETERById_HQL,
				new Object[] { id });
		if (!list.isEmpty() && list.size() > 0) {
			tsysParameter = (TsysParameter) list.get(0);
		}
		return tsysParameter;
	}

	/**
	 * 把查询条件构造成数组来查询TsysParameter信息
	 * 
	 * @param Object[]
	 *            object
	 * @return List
	 * @throws DataAccessException
	 */
	public List getTsysParameterByHQL(String hql, Object[] object)
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

	public int getTsysParameterDataTotalNum(String hql, Object[] object)
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

	public List getTsysParameterPageDataByHQL(String hql, Object[] object,
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
	public List getTsysParameterPageDataByHQL(String hql, Map obj,
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
	public List getTsysParameterValueObjectBySQL(String sql, Object[] object)
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
	public List getTsysParameterValueObjectByNameQuery(String queryName,
			Object[] object) throws ServiceException {
		return baseDAO.getValueObjectByNameQuery(queryName, object);
	}

	/**
	 * 动态构造HQL参数
	 * @param detachedCriteria
	 * @return
	 * @throws ServiceException
	 */
	public List getTsysParameterValueObjectByDetachedCriteria(
			DetachedCriteria detachedCriteria) throws ServiceException {
		return baseDAO.getValueObjectByDetachedCriteria(detachedCriteria);
	}

	/**
	 * 动态构造HQL参数
	 * @param detachedCriteria
	 * @return
	 * @throws ServiceException
	 */
	public List getTsysParameterValueObjectByDetachedCriterias(
			DetachedCriteria detachedCriteria, int arg1, int arg2)
			throws ServiceException {
		return baseDAO.getValueObjectByDetachedCriterias(detachedCriteria,
				arg1, arg2);
	}

	@Override
	public void saveFillParamMap(String paraType) throws ServiceException {
		List list = null;
		List paramList = null;
		String type = null;
		Object[] obj = getTsysParameterHql(new Object[] { "", "", "", "" });
		StringBuffer paramHql = (StringBuffer) obj[0];
		if (Constant.paramMap == null) {
			Constant.paramMap = new HashMap();
		}
		if (paraType != null && !paraType.trim().equals("")) {
			paramHql.append(" and param.paraType = ?");

			list = baseDAO.getValueObjectsByHQL(paramHql.toString(),
					new Object[] { paraType });
			if (list.size() > 0) {
				Constant.paramMap.put(paraType, list);
			}
		} else {
			list = baseDAO.getValueObjectsByHQL(paramHql.toString());
			Iterator it = list.iterator();
			while (it.hasNext()) {
				TsysParameter param = (TsysParameter) it.next();
				type = param.getParaType();
				if (Constant.paramMap.containsKey(type)) {
					paramList = (List) Constant.paramMap.get(type);
				} else {
					paramList = new ArrayList<TsysParameter>();
					Constant.paramMap.put(type, paramList);
				}
				paramList.add(param);
			}
		}
	}

	private Object[] getTsysParameterHql(Object[] object) {
		String paraname = (String) object[0];
		log.info("9999999999999999" + paraname);
		String paraType = (String) object[1];
		String isdefault = (String) object[2];
		String paraClass = (String) object[3];
		StringBuffer paramHql = new StringBuffer(
				"from TsysParameter param where 1=1");
		List paramList = new ArrayList();
		if (paraname != null && !paraname.trim().equals("")) {
			paramHql.append(" and param.paraname like ?");
			paramList.add("%" + paraname + "%");
		}
		if (paraType != null && !paraType.trim().equals("")) {
			paramHql.append(" and param.paraType like ?");
			paramList.add("%" + paraType + "%");
		}
		if (isdefault != null && !isdefault.trim().equals("")) {
			paramHql.append(" and param.isdefault = ?");
			paramList.add(isdefault);
		}
		if (paraClass != null && !paraClass.trim().equals("")) {
			paramHql.append(" and param.paraClass = ?");
			paramList.add(paraClass);
		}

		return new Object[] { paramHql, paramList };
	}

}
