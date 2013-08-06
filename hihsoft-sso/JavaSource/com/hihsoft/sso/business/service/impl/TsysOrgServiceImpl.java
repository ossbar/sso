/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.business.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;

import com.hihframework.core.utils.BeanUtils;
import com.hihframework.core.utils.StringHelpers;
import com.hihframework.exception.ServiceException;
import com.hihsoft.baseclass.service.impl.BaseServiceImpl;
import com.hihsoft.sso.business.model.TsysOrg;
import com.hihsoft.sso.business.service.TsysOrgService;
import com.hihsoft.sso.systempublic.constants.Constant;

@Service(value ="tsysOrgService")
public class TsysOrgServiceImpl extends BaseServiceImpl implements
		TsysOrgService {
	/************ 查询所有的TSYSORG ******************/
	private static final String ALLTSYSORG_HQL = " from TsysOrg";

	/************ 通过主键查询TSYSORG ******************/
	private static final String TSYSORGById_HQL = " from TsysOrg tsysOrg where tsysOrg.orgid=?";

	/************ 通过不同的条件组合，利用Hibernate HQL查询TSYSORG ******************/
	private static final StringBuffer QUERY_TSYSORG_HQL = new StringBuffer(
			" from TsysOrg tsysOrg where 1=1");

	/************ 通过不同的条件组合，利用JDBC SQL查询TSYSORG ******************/
	private static final StringBuffer QUERY_TSYSORG_SQL = new StringBuffer(
			"select * from T_SYS_ORG t where 1=1");

	/**
	 * 新增、修改TsysOrg信息
	 * 
	 * @param tsysOrg
	 * @throws DataAccessException
	 */
	public void saveOrUpdateTsysOrg(final TsysOrg tsysOrg) throws ServiceException {
		baseDAO.doInHibernate(new HibernateCallback<Void>() {
			public Void doInHibernate(Session session) throws HibernateException,
					SQLException {
				Transaction tx = session.beginTransaction();
				if (StringHelpers.notNull(tsysOrg.getOrgid())) {
					TsysOrg old = (TsysOrg) session.get(TsysOrg.class, tsysOrg.getOrgid());
					TsysOrg parent = (TsysOrg) session.get(TsysOrg.class, tsysOrg.getParentorgid());
					BeanUtils.bean2Bean(tsysOrg, old);
					old.getTsysOrg().getTsysOrgs().remove(old);
					parent.getTsysOrgs().add(old);
					old.setTsysOrg(parent);
					session.saveOrUpdate(parent);
					session.saveOrUpdate(old);
					String regionId = null;
					if (Integer.valueOf(old.getOrgClass()) <= 2 && old.getOrgSort().equals("1")) {
						regionId = old.getOrgid();
					} else while (parent != null) {
						if (Integer.valueOf(parent.getOrgClass()) <= 2 && parent.getOrgSort().equals("1")) {
							regionId = parent.getOrgid();
							break;
						}
						parent = parent.getTsysOrg();
					}
					if (StringHelpers.notNull(regionId)) {
						old.setOrgRegion(regionId);
					}
					session.saveOrUpdate(old);
				} else {
					session.saveOrUpdate(tsysOrg);
				}
				tx.commit();
				return null;
			}
				
		});
	}

	/**
	 * 删除TsysOrg信息
	 * 
	 * @param id
	 * @throws DataAccessException
	 * @author Xiaojf
	 * @since 20110624
	 */
	public String deleteTsysOrg(final String id) throws ServiceException {
		return baseDAO.doInHibernate(new HibernateCallback<String>() {
			public String doInHibernate(Session session) throws HibernateException,
					SQLException {
				TsysOrg org = (TsysOrg) session.get(TsysOrg.class, id);
				Set<TsysOrg> subOrgs = org.getTsysOrgs();
				if (subOrgs.size() > 0) {
					for (TsysOrg o : subOrgs) {
//						if ("2".equals(o.getOrgSort())) return "hasDept";
						if("01".equals(o.getOrgstate())) return "hasOrg";
					}
				}
				int num = getDataTotalNum("from TaclUserinfo where orgid=?", id);
				if (num > 0) return "hasUser";
				String hql = "delete from TsysDataprivilege where orgid in (";
				StringBuffer sb = new StringBuffer();
				eachOrg(org, sb);
				hql += sb.substring(1) + ")";
				session.createQuery(hql).executeUpdate();
				org.setOrgstate(Constant.ORG_STATUS_STOPED);
				session.saveOrUpdate(org);
				hql = "delete from TsysTreeprivilege where orgid in (" + sb.substring(1) + ")";
				session.createQuery(hql).executeUpdate();
				
				return "success";
			}
		});
	}

	/**
	 * 通过HQL构造查询条件来查询符合条件的TsysOrg信息
	 * 
	 * @param hql
	 * @return List
	 * @throws DataAccessException
	 */
	public List<?> getTsysOrgByHQL(String hql) throws ServiceException {
		return baseDAO.getValueObjectsByHQL(hql);

	}

	/**
	 * 查询所有的TsysOrg信息
	 * 
	 * @param hql
	 * @return List
	 * @throws DataAccessException
	 */
	public List<?> getAllTsysOrg() throws ServiceException {
		return baseDAO.getValueObjectsByHQL(ALLTSYSORG_HQL);
	}

	/**
	 * 根据主键查询TsysOrg信息明细
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public TsysOrg getTsysOrgById(String id) throws ServiceException {
		TsysOrg tsysOrg = null;
		List<?> list = baseDAO.getValueObjectsByHQL(TSYSORGById_HQL,
				new Object[] { id });
		if (!list.isEmpty() && list.size() > 0) {
			tsysOrg = (TsysOrg) list.get(0);
		}
		return tsysOrg;
	}

	/**
	 * 把查询条件构造成数组来查询TsysOrg信息
	 * 
	 * @param Object
	 *            [] object
	 * @return List
	 * @throws DataAccessException
	 */
	public List<?> getTsysOrgByArray(Object[] filter) throws ServiceException {
		return baseDAO.getValueObjectsByHQL(QUERY_TSYSORG_HQL.toString(),
				filter);
	}

	/**
	 * 取得分页总数
	 * 
	 * @param hql
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */

	public int getTsysOrgDataTotalNum(Object[] filter) throws ServiceException {
		return baseDAO.getDataTotalNum(QUERY_TSYSORG_HQL.toString(), filter);
	}

	/**
	 * 把查询条件构造成数组来查询TsysOrg信息
	 * 
	 * @param Map
	 *            filter
	 * @return List
	 * @throws DataAccessException
	 */
	public List<?> getTsysOrgByMap(Map<String, Object> filter) throws ServiceException {
		return baseDAO.getPageDataByHQL(QUERY_TSYSORG_HQL.toString(), filter);
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

	public List<?> getTsysOrgPageDataByArray(Object[] filter, int page_size,
			int pageNo) throws ServiceException {
		return baseDAO.getPageDataByHQL(QUERY_TSYSORG_HQL.toString(), filter,
				page_size, pageNo);
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
	public List<?> getTsysOrgPageDataByMap(Map<String, Object> filter, int page_size, int pageNo)
			throws ServiceException {
		return baseDAO.getPageDataByHQL(QUERY_TSYSORG_HQL.toString(), filter,
				page_size, pageNo);
	}

	/**
	 * 利用SQL数组条件来查询记录
	 * 
	 * @param sql
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public List<?> getTsysOrgValueObjectWithSQLByArray(Object[] filter)
			throws ServiceException {
		return baseDAO
				.getValueObjectBySQL(QUERY_TSYSORG_SQL.toString(), filter);
	}

	/**
	 * 通过配置SQL来执行查询带多个参数的情况 包括SQL语句、存储过程等
	 * 
	 * @param queryName
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public List<?> getTsysOrgValueObjectByNameQuery(String queryName,
			Object[] filter) throws ServiceException {
		return baseDAO.getValueObjectByNameQuery(queryName, filter);
	}

	/**
	 * 动态构造HQL参数
	 * 
	 * @param detachedCriteria
	 * @return
	 * @throws ServiceException
	 */
	public List<?> getTsysOrgValueObjectByDetachedCriteria(
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
	public List<?> getTsysOrgValueObjectByDetachedCriterias(
			DetachedCriteria detachedCriteria, int arg1, int arg2)
			throws ServiceException {
		return baseDAO.getValueObjectByDetachedCriterias(detachedCriteria,
				arg1, arg2);
	}
}
