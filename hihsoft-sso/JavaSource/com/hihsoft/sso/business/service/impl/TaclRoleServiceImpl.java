/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.business.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;

import com.hihframework.exception.ServiceException;
import com.hihsoft.baseclass.service.impl.BaseServiceImpl;
import com.hihsoft.sso.business.model.TaclRole;
import com.hihsoft.sso.business.model.TaclRoleuser;
import com.hihsoft.sso.business.model.TsysFlat;
import com.hihsoft.sso.business.model.TsysModuleinfo;
import com.hihsoft.sso.business.model.TsysModuleoperate;
import com.hihsoft.sso.business.service.TaclRoleService;
import com.mysql.jdbc.StringUtils;
@Service(value ="taclRoleService")
public class TaclRoleServiceImpl extends BaseServiceImpl implements
		TaclRoleService {
	/************ 查询所有的TACLROLE ******************/
	private static final String ALLTACLROLE_HQL = " from TaclRole";

	/************ 通过主键查询TACLROLE ******************/
	private static final String TACLROLEById_HQL = " from TaclRole taclRole where taclRole.roleid=?";

	/************ 通过不同的条件组合，利用Hibernate HQL查询TACLROLE ******************/
	private static final StringBuffer QUERY_TACLROLE_HQL = new StringBuffer(
			" from TaclRole taclRole where 1=1");

	/************ 通过不同的条件组合，利用JDBC SQL查询TACLROLE ******************/
	private static final StringBuffer QUERY_TACLROLE_SQL = new StringBuffer(
			"select * from T_ACL_ROLE t where 1=1");

	/**
	 * 新增、修改TaclRole信息
	 * 
	 * @param taclRole
	 * @throws DataAccessException
	 */
	public void saveOrUpdateTaclRole(TaclRole taclRole) throws ServiceException {
		baseDAO.saveOrUpdateVO(taclRole);

	}

	/**
	 * 删除TaclRole信息
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public void deleteTaclRole(String id) throws ServiceException {
		TaclRole taclRole = this.getTaclRoleById(id);
		baseDAO.deleteVO(taclRole);
	}

	/**
	 * 通过HQL构造查询条件来查询符合条件的TaclRole信息
	 * 
	 * @param hql
	 * @return List
	 * @throws DataAccessException
	 */
	public List getTaclRoleByHQL(String hql) throws ServiceException {
		return baseDAO.getValueObjectsByHQL(hql);

	}

	/**
	 * 查询所有的TaclRole信息
	 * 
	 * @param hql
	 * @return List
	 * @throws DataAccessException
	 */
	public List getAllTaclRole() throws ServiceException {
		return baseDAO.getValueObjectsByHQL(ALLTACLROLE_HQL);
	}

	/**
	 * 根据主键查询TaclRole信息明细
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public TaclRole getTaclRoleById(final String id) throws ServiceException {
		return baseDAO.doInHibernate(new HibernateCallback<TaclRole>() {
			@Override
			public TaclRole doInHibernate(Session session)
					throws HibernateException, SQLException {
				TaclRole taclRole = null;
				Query query = session.createQuery(TACLROLEById_HQL);
				query.setString(0, id);
				List list = query.list();
				if (!list.isEmpty() && list.size() > 0) {
					taclRole = (TaclRole) list.get(0);
					Hibernate.initialize(taclRole.getTaclRoleusers());
				}
				return taclRole;
			}
		});
	}
	
	public List<TaclRoleuser> getTaclRoleUserByRoleId(final String id) throws ServiceException {
		return baseDAO.doInHibernate(new HibernateCallback<List<TaclRoleuser>>() {
			@Override
			public List<TaclRoleuser> doInHibernate(Session session)
					throws HibernateException, SQLException {
				TaclRole taclRole = null;
				Query query = session.createQuery(TACLROLEById_HQL);
				query.setString(0, id);
				List list = query.list();
				if (!list.isEmpty() && list.size() > 0) {
					taclRole = (TaclRole) list.get(0);
					Hibernate.initialize(taclRole.getTaclRoleusers());
					list.clear();
					list.addAll(taclRole.getTaclRoleusers());
				}
				return list;
			}
		});
	}

	/**
	 * 把查询条件构造成数组来查询TaclRole信息
	 * 
	 * @param Object
	 *            [] object
	 * @return List
	 * @throws DataAccessException
	 */
	public List getTaclRoleByArray(Object[] filter) throws ServiceException {
		return baseDAO.getValueObjectsByHQL(QUERY_TACLROLE_HQL.toString(),
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

	public int getTaclRoleDataTotalNum(Object[] filter) throws ServiceException {
		return baseDAO.getDataTotalNum(QUERY_TACLROLE_HQL.toString(), filter);
	}

	/**
	 * 把查询条件构造成数组来查询TaclRole信息
	 * 
	 * @param Map
	 *            filter
	 * @return List
	 * @throws DataAccessException
	 */
	public List getTaclRoleByMap(Map filter) throws ServiceException {
		return baseDAO.getPageDataByHQL(QUERY_TACLROLE_HQL.toString(), filter);
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

	public List getTaclRolePageDataByArray(Object[] filter, int page_size,
			int pageNo) throws ServiceException {
		return baseDAO.getPageDataByHQL(QUERY_TACLROLE_HQL.toString(), filter,
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
	public List getTaclRolePageDataByMap(Map filter, int page_size, int pageNo)
			throws ServiceException {
		String id = (String) filter.get("id");
		return baseDAO.getPageDataByHQL(QUERY_TACLROLE_HQL.toString(), filter,
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
	public List getTaclRoleValueObjectWithSQLByArray(Object[] filter)
			throws ServiceException {
		return baseDAO.getValueObjectBySQL(QUERY_TACLROLE_SQL.toString(),
				filter);
	}

	/**
	 * 通过配置SQL来执行查询带多个参数的情况 包括SQL语句、存储过程等
	 * 
	 * @param queryName
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public List getTaclRoleValueObjectByNameQuery(String queryName,
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
	public List getTaclRoleValueObjectByDetachedCriteria(
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
	public List getTaclRoleValueObjectByDetachedCriterias(
			DetachedCriteria detachedCriteria, int arg1, int arg2)
			throws ServiceException {
		return baseDAO.getValueObjectByDetachedCriterias(detachedCriteria,
				arg1, arg2);
	}

	@Override
	public List<Map<String, Object>> getModuleTree(final String roleId,
			final String curUserId, String curOrgId) throws ServiceException {
		return baseDAO.doInHibernate(new HibernateCallback<List<Map<String, Object>>>() {
			@SuppressWarnings("unchecked")
			public List<Map<String, Object>> doInHibernate(Session session) throws HibernateException,
					SQLException {
				List<Map<String, Object>> tree = new ArrayList<Map<String, Object>>();
				String sql = "select distinct f.* from t_sys_flat f,(select distinct moduleid from t_acl_roleprivilege where roleid in (select roleid from t_acl_roleuser where userid=:userId) union all select distinct moduleid from t_acl_userprivilege where userid=:userId) mi, T_sys_moduleinfo m where mi.moduleid=m.moduleid and m.flatid = f.flatid";
				List<TsysFlat> flats = session.createSQLQuery(sql)
						.addEntity(TsysFlat.class)
						.setString("userId", curUserId).list();
				//当前角色已授权信息
				sql = "select distinct concat(moduleid,operateid) from t_acl_roleprivilege where roleid=?";
				Map<String, Object> assigned = queryAsMapBySQL(sql, roleId);
				//当前用户可用模块
				sql = "select distinct concat(moduleid,operateid) from t_acl_roleprivilege where roleid in (select roleid from t_acl_roleuser where userid=?) union all select distinct moduleid from t_acl_userprivilege where userid=?";
				Map<String, Object> modules = baseDAO.queryAsMapBySQL(sql, curUserId, curUserId);
				
				for (TsysFlat flat : flats) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", flat.getFlatid());
					map.put("text", flat.getFlatname());
					Map<String, Object> attr = new HashMap<String, Object>();
					attr.put("flatid", flat.getFlatid());
					map.put("attributes", attr);
					Set<TsysModuleinfo> moduleinfos = flat.getTsysModuleinfos();
					if (moduleinfos.size() > 0) {
						List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
						for (TsysModuleinfo info : moduleinfos) {
							if (!StringUtils.isNullOrEmpty(info.getParentmoduleid()))
								continue;
							Map<String, Object> child = new HashMap<String, Object>();
							if (!isSuperUser(curUserId) && modules.get(info.getModuleid()) == null) continue;
							buildModuleTree(info, child, modules, assigned, curUserId);
							if (!child.isEmpty()) children.add(child);
						}
						map.put("children", children);
						map.put("state", "closed");
					}
					tree.add(map);
				}
				return tree;
			}
			
			@SuppressWarnings("unchecked")
			private void buildModuleTree(TsysModuleinfo info,
					Map<String, Object> child, Map<String, Object> modules,
					Map<String, Object> assinged, String userId) {
				if (!isSuperUser(curUserId) && modules.get(info.getModuleid()) == null) return;
				child.put("id", info.getModuleid());
				child.put("text", info.getModulename());
				Map<String, Object> attributes = new HashMap<String, Object>();
				attributes.put("moduleid", info.getModuleid());
				List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
				
				boolean isIn = assinged.get(info.getModuleid()) != null;
				Set<TsysModuleoperate> opers = info.getTsysModuleoperates();
				for (TsysModuleoperate oper : opers) {
					if (!isSuperUser(curUserId) && modules.get(info.getModuleid() + oper.getOperateid()) == null) continue;
					Map<String, Object> operMap = new HashMap<String, Object>();
					operMap.put("id", oper.getOperateid());
					operMap.put("text", oper.getOperatename());
					Map<String, Object> attr = new HashMap<String, Object>();
					attr.put("operid", oper.getOperateid());
					attr.put("moduleid", info.getModuleid());
					operMap.put("attributes", attr);
					if (assinged.get(info.getModuleid() + oper.getOperateid()) != null) {
						operMap.put("checked", true);
					}
					children.add(operMap);
				}
				if (isIn) {
					child.put("checked", true);
				}
				child.put("attributes", attributes);
				Set<TsysModuleinfo> set = info.getTsysModuleinfos();
				for (TsysModuleinfo i : set) {
					Map<String, Object> chd = new HashMap<String, Object>();
					buildModuleTree(i, chd, modules, assinged, userId);
					if (!chd.isEmpty()) children.add(chd);
				}
				if (children.size() > 0) child.put("children", children);
			}
		});
	}
}
