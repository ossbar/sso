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

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;

import com.hihframework.core.utils.ParamUtil;
import com.hihframework.core.utils.StringHelpers;
import com.hihframework.exception.ServiceException;
import com.hihframework.osplugins.json.JsonUtil;
import com.hihsoft.baseclass.service.impl.BaseServiceImpl;
import com.hihsoft.sso.business.model.TaclDutyuser;
import com.hihsoft.sso.business.model.TaclRoleprivilege;
import com.hihsoft.sso.business.model.TaclUserinfo;
import com.hihsoft.sso.business.model.TaclUserprivilege;
import com.hihsoft.sso.business.model.TsysDataprivilege;
import com.hihsoft.sso.business.model.TsysDuty;
import com.hihsoft.sso.business.model.TsysFlat;
import com.hihsoft.sso.business.model.TsysModuleinfo;
import com.hihsoft.sso.business.model.TsysModuleoperate;
import com.hihsoft.sso.business.model.TsysTreeprivilege;
import com.hihsoft.sso.business.service.TaclUserinfoService;
import com.mysql.jdbc.StringUtils;

@Service(value ="taclUserinfoService")
public class TaclUserinfoServiceImpl extends BaseServiceImpl implements
		TaclUserinfoService {
	/************ 查询所有的TACLUSERINFO ******************/
	private static final String ALLTACLUSERINFO_HQL = " from TaclUserinfo";

	/************ 通过主键查询TACLUSERINFO ******************/
	private static final String TACLUSERINFOById_HQL = " from TaclUserinfo taclUserinfo where taclUserinfo.userid=?";

	/************ 通过不同的条件组合，利用Hibernate HQL查询TACLUSERINFO ******************/
	private static final StringBuffer QUERY_TACLUSERINFO_HQL = new StringBuffer(
			" from TaclUserinfo taclUserinfo where 1=1");

	/************ 通过不同的条件组合，利用JDBC SQL查询TACLUSERINFO ******************/
	private static final StringBuffer QUERY_TACLUSERINFO_SQL = new StringBuffer(
			"select * from T_ACL_USERINFO t where 1=1");

	/**
	 * 新增、修改TaclUserinfo信息
	 * 
	 * @param taclUserinfo
	 * @throws DataAccessException
	 */
	public void saveOrUpdateTaclUserinfo(TaclUserinfo taclUserinfo)
			throws ServiceException {
		baseDAO.saveOrUpdateVO(taclUserinfo);
	}

	/**
	 * 删除TaclUserinfo信息
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public void deleteTaclUserinfo(String ids) throws ServiceException {
		//List<TaclUserinfo> list = new ArrayList<TaclUserinfo>();
		if (ids != null && !"".equals(ids)) {
			String[] arr = ids.split(",");
			for (String id : arr) {
				TaclUserinfo obj = getTaclUserinfoById(id);
				if (obj != null) {
					obj.setUserstate(ParamUtil.getInstance().getValByKey("userState", "停用"));
					saveOrUpdateTaclUserinfo(obj);
				}
//				list.add(obj);
//				executeHQL("delete from TsysDataprivilege where userid=?", id);
//				executeHQL("delete from TaclRoleuser where userid=?", id);
			}
		}
		//deleteBatchVO(list);
	}

	/**
	 * 通过HQL构造查询条件来查询符合条件的TaclUserinfo信息
	 * 
	 * @param hql
	 * @return List
	 * @throws DataAccessException
	 */
	public List<?> getTaclUserinfoByHQL(String hql) throws ServiceException {
		return baseDAO.getValueObjectsByHQL(hql);

	}

	/**
	 * 查询所有的TaclUserinfo信息
	 * 
	 * @param hql
	 * @return List
	 * @throws DataAccessException
	 */
	public List<?> getAllTaclUserinfo() throws ServiceException {
		return baseDAO.getValueObjectsByHQL(ALLTACLUSERINFO_HQL);
	}

	/**
	 * 根据主键查询TaclUserinfo信息明细
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public TaclUserinfo getTaclUserinfoById(String id) throws ServiceException {
		TaclUserinfo taclUserinfo = null;
		List<?> list = baseDAO.getValueObjectsByHQL(TACLUSERINFOById_HQL,
				new Object[] { id });
		if (!list.isEmpty() && list.size() > 0) {
			taclUserinfo = (TaclUserinfo) list.get(0);
		}
		return taclUserinfo;
	}

	/**
	 * 把查询条件构造成数组来查询TaclUserinfo信息
	 * 
	 * @param Object
	 *            [] object
	 * @return List
	 * @throws DataAccessException
	 */
	public List<?> getTaclUserinfoByArray(Object[] filter) throws ServiceException {
		return baseDAO.getValueObjectsByHQL(QUERY_TACLUSERINFO_HQL.toString(),
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

	public int getTaclUserinfoDataTotalNum(Object[] filter)
			throws ServiceException {
		return baseDAO.getDataTotalNum(QUERY_TACLUSERINFO_HQL.toString(),
				filter);
	}

	/**
	 * 把查询条件构造成数组来查询TaclUserinfo信息
	 * 
	 * @param Map
	 *            filter
	 * @return List
	 * @throws DataAccessException
	 */
	public List<?> getTaclUserinfoByMap(Map<String, Object> filter) throws ServiceException {
		return baseDAO.getPageDataByHQL(QUERY_TACLUSERINFO_HQL.toString(),
				filter);
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

	public List<?> getTaclUserinfoPageDataByArray(Object[] filter, int page_size,
			int pageNo) throws ServiceException {
		return baseDAO.getPageDataByHQL(QUERY_TACLUSERINFO_HQL.toString(),
				filter, page_size, pageNo);
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
	public List<?> getTaclUserinfoPageDataByMap(Map<String, Object> filter, int page_size,
			int pageNo) throws ServiceException {
		return baseDAO.getPageDataByHQL(QUERY_TACLUSERINFO_HQL.toString(),
				filter, page_size, pageNo);
	}

	/**
	 * 利用SQL数组条件来查询记录
	 * 
	 * @param sql
	 * @param object
	 * @return
	 * @throws DataAccessException
	 */
	public List<?> getTaclUserinfoValueObjectWithSQLByArray(Object[] filter)
			throws ServiceException {
		return baseDAO.getValueObjectBySQL(QUERY_TACLUSERINFO_SQL.toString(),
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
	public List<?> getTaclUserinfoValueObjectByNameQuery(String queryName,
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
	public List<?> getTaclUserinfoValueObjectByDetachedCriteria(
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
	public List<?> getTaclUserinfoValueObjectByDetachedCriterias(
			DetachedCriteria detachedCriteria, int arg1, int arg2)
			throws ServiceException {
		return baseDAO.getValueObjectByDetachedCriterias(detachedCriteria,
				arg1, arg2);
	}
	
	public TaclUserinfo getUserByLoginName(String loginName) throws ServiceException {
		List<TaclUserinfo> list = baseDAO.loadByField(TaclUserinfo.class, TaclUserinfo.ALIAS_LOGINNAME, loginName);
		if (list != null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Map<String, String>> getUserRoles(TaclUserinfo user) throws ServiceException {
		String userId = user.getUserid();
		Map<String, Map<String, String>> roles = new HashMap<String, Map<String, String>>();
//		String sql = "select o.operateno,i.moduleno from t_sys_moduleoperate o,(select * from T_ACL_ROLEPRIVILEGE where roleid in (select roleid from t_acl_roleuser where userid=?)) t,t_sys_moduleinfo i where o.moduleid=t.moduleid and o.operateid=t.operateid and i.moduleid = t.moduleid";
		//String sql = "select o.operateno,i.moduleno from t_sys_moduleoperate o,(select distinct moduleid,operateid from (select moduleid,operateid from t_acl_roleprivilege where roleid in (select roleid from t_acl_roleuser where userid=?) union all select moduleid, operateid from t_acl_userprivilege where userid=?)) t,t_sys_moduleinfo i where o.moduleid=t.moduleid and o.operateid=t.operateid and i.moduleid = t.moduleid order by i.moduleno,o.operateno";
		//modify by zhujw兼容mysql 、oracle
		String sql = "SELECT O.OPERATENO,I.MODULENO FROM T_SYS_MODULEOPERATE O,(SELECT DISTINCT C.MODULEID,C.OPERATEID FROM (SELECT R.MODULEID,R.OPERATEID FROM T_ACL_ROLEPRIVILEGE  R WHERE R.ROLEID IN (SELECT US.ROLEID FROM T_ACL_ROLEUSER US WHERE US.USERID=?) UNION ALL SELECT AC.MODULEID, AC.OPERATEID FROM T_ACL_USERPRIVILEGE AC WHERE AC.USERID=?) C) T,T_SYS_MODULEINFO I WHERE O.MODULEID=T.MODULEID AND O.OPERATEID=T.OPERATEID AND I.MODULEID = T.MODULEID ORDER BY I.MODULENO,O.OPERATENO";
		List<Object[]> rolePrivileges = (List<Object[]>) baseDAO.getValueObjectBySQL(sql, userId, userId);
		String moduleno = "";
		for (Object[] os : rolePrivileges) {
			Map<String, String> map = null;
			if (os == null || os[1] == null) continue;
			if (!moduleno.equals(os[1])) {
				map = new HashMap<String, String>();
				moduleno = (String) os[1];
				map.put((String) os[0], moduleno);
				roles.put(moduleno, map);
			} else {
				map = roles.get(moduleno);
				map.put((String) os[0], moduleno);
			}
		}
		return roles;
	}
	@Override
	public boolean saveDataSet(String userId, String datas)  throws ServiceException {
		String[] dataSets = datas.split(",");
		String hql = "delete from TsysDataprivilege where userid='"+userId+"'";
		executeHQL(hql);
		for (String set : dataSets) {
			if ("".equals(set)) continue;
			TsysDataprivilege td = new TsysDataprivilege();
			td.setOrgid(set);
			td.setUserid(userId);
			baseDAO.saveOrUpdateVO(td);
		}
		return true;
	}
	
	@Override
	public void clearRole(String userId) throws ServiceException {
		executeHQL("delete from TaclRoleuser where userid=?", userId);
		executeHQL("delete from TaclUserprivilege where userid=?", userId);
		executeHQL("delete from TsysDataprivilege where userid=?", userId);
	}
	
	public String getPrivilegeTree(final String userId, final String curUserId) throws ServiceException {
		if (StringHelpers.isNull(userId)) return "[]";
		List<Map<String, Object>> map = baseDAO.doInHibernate(new HibernateCallback<List<Map<String, Object>>>() {
			@SuppressWarnings("unchecked")
			public List<Map<String, Object>> doInHibernate(Session session) throws HibernateException ,SQLException {
				List<Map<String, Object>> tree = new ArrayList<Map<String, Object>>();
				String sql = "SELECT DISTINCT F.* FROM T_SYS_FLAT F,(SELECT DISTINCT MODULEID FROM T_ACL_ROLEPRIVILEGE WHERE ROLEID IN (SELECT ROLEID FROM T_ACL_ROLEUSER WHERE USERID=:USERID) UNION ALL SELECT DISTINCT MODULEID FROM T_ACL_USERPRIVILEGE WHERE USERID=:USERID) MI, T_SYS_MODULEINFO M WHERE MI.MODULEID=M.MODULEID AND M.FLATID = F.FLATID";
				List<TsysFlat> flats = session.createSQLQuery(sql)
						.addEntity(TsysFlat.class)
						.setString("USERID", curUserId).list();
				sql = "SELECT DISTINCT CONCAT(MODULEID,OPERATEID) FROM T_ACL_ROLEPRIVILEGE WHERE ROLEID IN (SELECT ROLEID FROM T_ACL_ROLEUSER WHERE USERID=?) UNION ALL SELECT DISTINCT MODULEID FROM T_ACL_USERPRIVILEGE WHERE USERID=?";
				//当前用户可用模块
				Map<String, Object> modules = baseDAO.queryAsMapBySQL(sql, curUserId, curUserId);
				//已授权角色
				sql = "SELECT DISTINCT CONCAT(MODULEID,OPERATEID) FROM T_ACL_ROLEPRIVILEGE WHERE ROLEID IN (SELECT ROLEID FROM T_ACL_ROLEUSER WHERE USERID=?)";
				Map<String, Object> assigned = baseDAO.queryAsMapBySQL(sql, userId);
				//已授权特权
				sql = "SELECT DISTINCT CONCAT(MODULEID,OPERATEID) FROM T_ACL_USERPRIVILEGE WHERE USERID=?";
				Map<String, Object> privileges = baseDAO.queryAsMapBySQL(sql, userId);
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
							if (modules.get(info.getModuleid()) == null) continue;
							buildModuleTree(info, child, modules, assigned, privileges);
							if (!child.isEmpty()) children.add(child);
						}
						map.put("children", children);
						map.put("state", "closed");
					}
					tree.add(map);
				}
				return tree;
			}
		});
		return JsonUtil.toString(map);
	}
	
	protected void buildModuleTree(TsysModuleinfo info,
			Map<String, Object> child, Map<String, Object> modules, Map<String, Object> assinged, Map<String, Object> privileges) throws ServiceException {
		if (modules.get(info.getModuleid()) == null) return;
		child.put("id", info.getModuleid());
		child.put("text", info.getModulename());
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("moduleid", info.getModuleid());
		
		List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
		
		boolean disalbedAss = assinged.get(info.getModuleid()) != null;
		boolean disalbedPri = privileges.get(info.getModuleid()) != null;
		
		Set<TsysModuleoperate> opers = info.getTsysModuleoperates();
		for (TsysModuleoperate oper : opers) {
			if (modules.get(info.getModuleid() + oper.getOperateid()) == null) continue;
			Map<String, Object> operMap = new HashMap<String, Object>();
			operMap.put("id", oper.getOperateid());
			operMap.put("text", oper.getOperatename());
			Map<String, Object> attr = new HashMap<String, Object>();
			attr.put("operid", oper.getOperateid());
			attr.put("moduleid", info.getModuleid());
			operMap.put("attributes", attr);
			boolean found = false;
			if (assinged.get(info.getModuleid() + oper.getOperateid()) != null) {
				found = true;
				attr.put("disabled", true);
			} else if (privileges.get(info.getModuleid() + oper.getOperateid()) != null) {
				found = true;
			}
			if (found) operMap.put("checked", true);
			children.add(operMap);
		}
		if (disalbedAss || disalbedPri) {
			attributes.put("disabled", disalbedAss);
			child.put("checked", true);
		}
		child.put("attributes", attributes);
		Set<TsysModuleinfo> set = info.getTsysModuleinfos();
		for (TsysModuleinfo i : set) {
			Map<String, Object> chd = new HashMap<String, Object>();
			buildModuleTree(i, chd, modules, assinged, privileges);
			if (!chd.isEmpty()) children.add(chd);
		}
		if (children.size() > 0) child.put("children", children);
	}
	@Override
	public void savePrivilege(String userId, String moduleSet) throws ServiceException {
		String hql = "delete from TaclUserprivilege where userid=?";
		executeHQL(hql, userId);
		String[] modules = moduleSet.split(";");
		for (String module : modules) {
			if ("".equals(module)) continue;
			String[] m = module.split(",");
			TaclUserprivilege tu = new TaclUserprivilege();
			tu.setOperateid(m[0]);
			tu.setModuleid(m[1]);
			tu.setUserid(userId);
			saveOrUpdateVO(tu);
		}
	}

	@Override
	public boolean saveTreeSet(String userId, String treeNodes)
			throws ServiceException {
		String[] dataSets = treeNodes.split(",");
		String hql = "delete from TsysTreeprivilege where userid='"+userId+"'";
		executeHQL(hql);
		for (String set : dataSets) {
			if ("".equals(set)) continue;
			TsysTreeprivilege td = new TsysTreeprivilege();
			td.setOrgid(set);
			td.setUserid(userId);
			saveOrUpdateVO(td);
		}
		return true;
	}

	@Override
	public List<Map<String, Object>> getModuleTree(final String userId)
			throws ServiceException {
		final List<Map<String, Object>> tree = new ArrayList<Map<String, Object>>();
		baseDAO.doInHibernate(new HibernateCallback<Object>() {
			@SuppressWarnings("unchecked")
			public Object doInHibernate(Session arg0) throws HibernateException,
					SQLException {
				String hql = "from TaclRoleprivilege where roleid in (select roleid from TaclRoleuser where userid=?)";
				List<TaclRoleprivilege> assigned = (List<TaclRoleprivilege>) getValueObjectsByHQL(hql, userId);
				hql = "from TaclUserprivilege where userid=?";
				List<TaclUserprivilege> userpri = (List<TaclUserprivilege>) getValueObjectsByHQL(hql, userId);
				for (TaclUserprivilege pri : userpri) {
					TaclRoleprivilege r = new TaclRoleprivilege();
					r.setModuleid(pri.getModuleid());
					r.setOperateid(pri.getOperateid());
					assigned.add(r);
				}
				List<TsysFlat> flats = (List<TsysFlat>) getValueObjectsByHQL("from TsysFlat order by flatdesc");
				for (TsysFlat flat : flats) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("id", flat.getFlatid());
					map.put("text", flat.getFlatname());
					Map<String, Object> attr = new HashMap<String, Object>();
					attr.put("flatid", flat.getFlatid());
					map.put("attributes", JsonUtil.toString(attr));
					Set<TsysModuleinfo> moduleinfos = flat.getTsysModuleinfos();
					if (moduleinfos.size() > 0) {
						List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
						for (TsysModuleinfo info : moduleinfos) {
							if (!StringUtils.isNullOrEmpty(info
									.getParentmoduleid()))
								continue;
							Map<String, Object> child = new HashMap<String, Object>();
							buildModuleTree(info, child, assigned, true, false);
							children.add(child);
						}
						map.put("children", children);
						map.put("state", "closed");
					}
					tree.add(map);
				}
				return null;
			}
		});
		return tree;
	}

	@Override
	public boolean saveOrUpdateTaclDutyUser(String userId, String duty)
			throws ServiceException {
		String[] dataSets =  duty.split(",");
		String hql = "delete from TaclDutyuser where userid='"+userId+"'";
		executeHQL(hql);
		for (String set : dataSets) {
			if ("".equals(set)) continue;
			TaclDutyuser td = new TaclDutyuser();
			td.setDutyid(set);
			td.setUserid(userId);
			saveOrUpdateVO(td);
		}
		return true;
	}

	@Override
	public List<TsysDuty> getDutyAllByUserId(String userId)
			throws ServiceException {
		String hql = "from TsysDuty d where d.dutyid in " +
				"(select du.dutyid from TaclDutyuser du where du.userid = ?)";
		@SuppressWarnings("unchecked")
		List<TsysDuty> dutySet = (List<TsysDuty>) getValueObjectsByHQL(hql, userId);
		return dutySet; 
	}

	@Override
	public String getDutyAllNameByUserId(String userId) throws ServiceException {
		String result = "";
		List<TsysDuty> dutySet = getDutyAllByUserId(userId);
		for (TsysDuty tsysDuty : dutySet) {
			result += tsysDuty.getDutyname() + ",";
		}
		if(result.endsWith(",")){		result = result.substring(0, result.length() - 1); }
		return result;
	}
}
