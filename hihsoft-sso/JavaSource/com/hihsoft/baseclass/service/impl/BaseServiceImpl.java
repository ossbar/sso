/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.baseclass.service.impl;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Service;

import com.hihframework.core.utils.ParamUtil;
import com.hihframework.core.utils.StringHelpers;
import com.hihframework.exception.ServiceException;
import com.hihframework.osplugins.json.JsonUtil;
import com.hihsoft.baseclass.dao.BaseDAO;
import com.hihsoft.baseclass.model.BaseEntity;
import com.hihsoft.baseclass.service.BaseService;
import com.hihsoft.sso.business.model.TaclRoleprivilege;
import com.hihsoft.sso.business.model.TaclUserinfo;
import com.hihsoft.sso.business.model.TsysDataprivilege;
import com.hihsoft.sso.business.model.TsysDuty;
import com.hihsoft.sso.business.model.TsysFlat;
import com.hihsoft.sso.business.model.TsysModuleinfo;
import com.hihsoft.sso.business.model.TsysModuleoperate;
import com.hihsoft.sso.business.model.TsysOrg;
import com.hihsoft.sso.business.model.TsysTreeprivilege;
import com.hihsoft.sso.systempublic.constants.Constant;
import com.mysql.jdbc.StringUtils;

@Service(value="baseService")
public class BaseServiceImpl implements BaseService {
	protected Logger log = Logger.getLogger(this.getClass());
	@Autowired
	protected BaseDAO baseDAO;
	@Override
	public void deleteBatchVO(final List dataList) throws ServiceException {
		baseDAO.deleteBatchVO(dataList);
	}

	@Override
	public void deleteVO(final BaseEntity vo, final String id)
			throws ServiceException {
		baseDAO.deleteVO(vo, id);

	}

	@Override
	public void executeHQL(String hql, Object... params)
			throws ServiceException {
		baseDAO.executeQueryByHQL(hql, params);
	}

	@Override
	public int getDataTotalNum(final String hql) throws ServiceException {

		return baseDAO.getDataTotalNum(hql);
	}

	@Override
	public int getDataTotalNum(final String hql, final String arg1)
			throws ServiceException {

		return baseDAO.getDataTotalNum(hql, arg1);
	}

	@Override
	public int getDataTotalNum(final String hql, final Object obj)
			throws ServiceException {

		return baseDAO.getDataTotalNum(hql, obj);
	}

	@Override
	public int getDataTotalNum(final String hql, final Object... filter)
			throws ServiceException {

		return baseDAO.getDataTotalNum(hql, filter);
	}

	@Override
	public int getDataTotalNum(final BaseEntity vo, final String whereClause)
			throws ServiceException {

		return baseDAO.getDataTotalNum(vo, whereClause);
	}

	public int getTotalNumBySQL(String sql, Object... args)
			throws ServiceException {
		return baseDAO.getTotalNumBySQL(sql, args);
	}

	@Override
	public List getPageDataByHQL(final String hql, final int page_size,
			final int pageNo, final Object... filter) throws ServiceException {

		return baseDAO.getPageDataByHQL(hql, page_size, pageNo, filter);
	}

	@Override
	public List getPageDataByHQL(final String hql,
			final Map<String, Object> filter) throws ServiceException {
		return baseDAO.getPageDataByHQL(hql, filter);
	}

	@Override
	public List getPageDataByHQL(final String hql, final int page_size,
			final int pageNo) throws ServiceException {
		return baseDAO.getPageDataByHQL(hql, page_size, pageNo);
	}

	@Override
	public List getPageDataByHQL(final String hql, final String arg1,
			final int page_size, final int pageNo) throws ServiceException {
		return baseDAO.getPageDataByHQL(hql, arg1, page_size, pageNo);
	}

	@Override
	public List getPageDataByHQL(final String hql, final Object obj,
			final int page_size, final int pageNo) throws ServiceException {
		return baseDAO.getPageDataByHQL(hql, obj, page_size, pageNo);
	}

	@Override
	public List getPageDataByHQL(final String hql,
			final Map<String, Object> filter, final int page_size,
			final int pageNo) throws ServiceException {
		return baseDAO.getPageDataByHQL(hql, filter, page_size, pageNo);
	}

	@Override
	public List getPageDataByHQL(final String hql, final int page_size,
			final int pageNo, final int total_num) throws ServiceException {
		return baseDAO.getPageDataByHQL(hql, page_size, pageNo, total_num);
	}

	@Override
	public BaseEntity getVO(final BaseEntity vo, final String id)
			throws ServiceException {
		return baseDAO.getVO(vo, id);
	}

	@Override
	public BaseEntity getVO(final BaseEntity vo) throws ServiceException {
		return baseDAO.getVO(vo);
	}

	@Override
	public List getValueObjectByNameQuery(final String queryName)
			throws ServiceException {
		return baseDAO.getValueObjectByNameQuery(queryName);
	}

	@Override
	public List getValueObjectByNameQuery(final String queryName,
			final Object object) throws ServiceException {
		return baseDAO.getValueObjectByNameQuery(queryName, object);
	}

	@Override
	public List getValueObjectByNameQuery(final String queryName,
			final Object... filter) throws ServiceException {
		return baseDAO.getValueObjectByNameQuery(queryName, filter);
	}

	@Override
	public List getValueObjectBySQL(final String sql) throws ServiceException {
		return baseDAO.getValueObjectBySQL(sql);
	}

	@Override
	public List getValueObjectBySQL(final String sql, final Object... filter)
			throws ServiceException {
		return baseDAO.getValueObjectBySQL(sql, filter);
	}

	@Override
	public List getValueObjectsByHQL(final String hql) throws ServiceException {
		return baseDAO.getValueObjectsByHQL(hql);
	}

	@Override
	public List getValueObjectsByHQL(final String hql, final Object... filter)
			throws ServiceException {
		return baseDAO.getValueObjectsByHQL(hql, filter);
	}

	@Override
	public void saveOrUpdateBatchVO(final List dataList)
			throws ServiceException {
		baseDAO.saveOrUpdateBatchVO(dataList);
	}

	@Override
	public void saveOrUpdateVO(final BaseEntity vo) throws ServiceException {
		baseDAO.saveOrUpdateVO(vo);
	}

	@Override
	public String getNowDateTime() throws ServiceException {
		return getNow("yyyy-MM-dd HH:mm:ss");
	}

	@Override
	public String getNowDate() throws ServiceException {
		return getNow("yyyy-MM-dd");
	}

	public String getNow(String parttern) throws ServiceException {
		SimpleDateFormat format = new SimpleDateFormat(parttern);
		String sql = "select to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') from dual";
		List<?> list = baseDAO.getValueObjectBySQL(sql);
		if (list != null && !list.isEmpty()) {
			try {
				return format.format(format.parse((String) list.get(0)));
			} catch (ParseException e) {
				log.error(e);
			}
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getModuleTree(final String flatid,
			final boolean listoper, final String... roleId)
			throws ServiceException {
		final List<Map<String, Object>> tree = new ArrayList<Map<String, Object>>();
		baseDAO.doInHibernate(new HibernateCallback<Object>() {
			@SuppressWarnings("unchecked")
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "from TsysFlat";
				if (!StringUtils.isNullOrEmpty(flatid)) {
					hql += " where flatid='" + flatid + "'";
				}
				List<TsysFlat> flats = session.createQuery(hql).list();
				hql = "from TaclRoleprivilege where 1=1";
				if (roleId.length > 0) {
					hql += " and roleid in ("
							+ StringHelpers.join(roleId, ",", "'") + ")";
				}
				List<TaclRoleprivilege> assigned = session.createQuery(hql)
						.list();
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
							buildModuleTree(info, child, assigned, false,
									listoper, roleId);
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

	protected boolean containInObj(List<?> datas, String field, Object val) {
		Method getter = null;
		for (Object t : datas) {
			if (getter == null) {
				try {
					getter = t.getClass().getDeclaredMethod(
							"get" + StringHelpers.upperFirst(field));
				} catch (Exception e) {
				}
			}
			if (getter != null) {
				Object o = null;
				try {
					o = getter.invoke(t);
				} catch (Exception e) {
				}
				if (o == null)
					continue;
				if (o.equals(val))
					return true;
			}
		}
		return false;
	}

	/**
	 * 构造树
	 * 
	 * @param info
	 * @param child
	 * @author Xiaojf
	 * @since 2011-5-14
	 */
	@SuppressWarnings("unchecked")
	protected void buildModuleTree(TsysModuleinfo info,
			Map<String, Object> child, List<TaclRoleprivilege> assinged,
			boolean listoper, boolean hidden, String... roleId)
			throws ServiceException {
		child.put("id", info.getModuleid());
		child.put("text", info.getModulename());
		Map<String, Object> attributes = new HashMap<String, Object>();
		attributes.put("moduleid", info.getModuleid());
		child.put("attributes", JsonUtil.toString(attributes));
		List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
		// 子模块
		Set<TsysModuleinfo> set = info.getTsysModuleinfos();
		if (listoper) {// 模块下的操作
			Set<TsysModuleoperate> opers = info.getTsysModuleoperates();
			if (opers.size() == 0 && set.size() == 0) {
				for (TaclRoleprivilege tp : assinged) {
					if (tp.getModuleid().equals(info.getModuleid())) {
						boolean found = false;
						for (String s : roleId) {
							if (tp.getRoleid().equals(s)) {
								found = true;
								break;
							}
						}
						if ((roleId.length == 0) || found) {
							child.put("checked", true);
							break;
						}
					}
				}
			} else
				for (TsysModuleoperate oper : opers) {
					Map<String, Object> operMap = new HashMap<String, Object>();
					operMap.put("id", oper.getOperateid());
					operMap.put("text", oper.getOperatename());
					Map<String, Object> attr = new HashMap<String, Object>();
					attr.put("operid", oper.getOperateid());
					attr.put("moduleid", info.getModuleid());
					operMap.put("attributes", JsonUtil.toString(attr));
					boolean checked = false;
					for (TaclRoleprivilege tp : assinged) {
						if (oper.getOperateid().equals(tp.getOperateid())
								&& oper.getModuleid().equals(tp.getModuleid())) {
							boolean found = false;
							for (String s : roleId) {
								if (tp.getRoleid().equals(s)) {
									found = true;
									break;
								}
							}
							if (roleId.length == 0 || found) {
								operMap.put("checked", true);
								checked = true;
								break;
							}
						}
					}
					if (!hidden || checked)
						children.add(operMap);
				}
		}

		for (TsysModuleinfo i : set) {
			Map<String, Object> chd = new HashMap<String, Object>();
			String moduleid = i.getModuleid();
			boolean pass = containInObj(assinged, "moduleid", moduleid);
			if (!pass && hidden)
				continue;
			buildModuleTree(i, chd, assinged, listoper, hidden, roleId);
			children.add(chd);
		}
		child.put("children", children);
	}

	@Override
	public List<Map<String, Object>> getOrgTree(final String userId,
			final String orgId, final String orgClass) throws ServiceException {
		final List<Map<String, Object>> tree = new ArrayList<Map<String, Object>>();
		baseDAO.doInHibernate(new HibernateCallback<Void>() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Void doInHibernate(Session session)
					throws HibernateException, SQLException {
				String hql = "from TsysOrg where orgstate='"
						+ Constant.ORG_STATUS_NORMAL + "' ";
				String dataHql = "from TsysDataprivilege ";
				boolean p = false;
				List dataList = null;
				if (StringHelpers.notNull(userId)) {
					dataHql += "where userid=?";
					p = true;
					Query query = session.createQuery(dataHql);
					if (p)
						query.setParameter(0, userId);
					dataList = query.list();
				} else {
					dataList = new ArrayList();
				}
				if (StringHelpers.notNull(orgId)) {
					hql += " and orgid='" + orgId + "'";
				} else {
					hql += " and parentorgid is null";
				}
				Query q = session.createQuery(hql);
				List<TsysOrg> allOrgs = q.list();
				for (TsysOrg org : allOrgs) {
					if (!checkOrgClass(org, orgClass))
						continue;
					if (Constant.ORG_STATUS_STOPED.equals(org.getOrgstate())) continue;
					Map<String, Object> chd = new HashMap<String, Object>();
					buildOrgTree(org, chd, dataList, userId, orgClass);
					tree.add(chd);
				}
				return null;
			}
		});
		return tree;
	}

	/**
	 * 递归构建机构树
	 * 
	 * @param org
	 * @param treeData
	 * @param dataList
	 * @param roleId
	 * @author Xiaojf
	 * @since 2011-5-22
	 */
	@SuppressWarnings("unchecked")
	public void buildOrgTree(TsysOrg org, Map<String, Object> treeData,
			List<TsysDataprivilege> dataList, String userId, String orgClass)
			throws ServiceException {
		treeData.put("id", org.getOrgid());
		treeData.put("text", org.getOrgname());
		for (TsysDataprivilege td : dataList) {
			String uid = td.getUserid();
			if (StringHelpers.isNull(uid))
				continue;
			if (uid.equals(userId) && org.getOrgid().equals(td.getOrgid())) {
				treeData.put("checked", true);
				break;
			}
		}
		Set<TsysOrg> childOrgs = org.getTsysOrgs();
		if ("2".equals(org.getOrgClass()) && org.childrenSize() > 0) {
			treeData.put("state", "closed");
		}
		List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
		for (TsysOrg child : childOrgs) {
			if (!checkOrgClass(child, orgClass))
				continue;
			if (Constant.ORG_STATUS_STOPED.equals(child.getOrgstate())) continue;
			Map<String, Object> chd = new HashMap<String, Object>();
			buildOrgTree(child, chd, dataList, userId, orgClass);
			children.add(chd);
		}
		treeData.put("children", children);
	}

	protected boolean checkOrgClass(TsysOrg org, String orgClass) {
		if (StringHelpers.isNull(orgClass))
			return true;
		try {
			Integer class1 = Integer.valueOf(org.getOrgClass());
			Integer class2 = Integer.valueOf(orgClass);
			return class1 <= class2 && "1".equals(org.getOrgSort());
		} catch (Exception e) {
			return false;
		}
	}

	public String getSubOrgIds(final String orgId, final String orgClass)
			throws ServiceException {
		return baseDAO.doInHibernate(new HibernateCallback<String>() {
			@Override
			public String doInHibernate(Session session)
					throws HibernateException, SQLException {
				TsysOrg org = (TsysOrg) session.get(TsysOrg.class, orgId);
				StringBuffer buf = new StringBuffer();
				buf.append(",'" + orgId + "'");
				eachOrg(org, buf, "org", orgClass);
				return buf.substring(1);
			}
		});
	}

	@Override
	public String getSubOrgIds(String orgId) throws ServiceException {
		return getSubOrgIds(orgId, null);
	}

	public String getSubDeptIds(final String orgId, final String orgClass)
			throws ServiceException {
		return baseDAO.doInHibernate(new HibernateCallback<String>() {
			@Override
			public String doInHibernate(Session session)
					throws HibernateException, SQLException {
				TsysOrg dept = (TsysOrg) session.get(TsysOrg.class, orgId);
				StringBuffer buf = new StringBuffer();
				buf.append(",'" + orgId + "'");
				eachOrg(dept, buf, "dept", orgClass);
				return buf.substring(1);
			}
		});
	}

	@Override
	public String getSubDeptIds(String orgId) throws ServiceException {
		return getSubOrgIds(orgId, null);
	}

	@SuppressWarnings("unchecked")
	protected void eachOrg(TsysOrg org, StringBuffer buf, String type,
			String orgClass) {
		if (("dept".equals(type) && "2".equals(org.getOrgSort()))
				|| "org".equals(type)) {
			if ((orgClass != null && orgClass.equals(org.getOrgClass()))
					|| orgClass == null) {
				buf.append(",'" + org.getOrgid() + "'");
			}
		}
		Set<TsysOrg> set = org.getTsysOrgs();
		if (set.size() > 0) {
			for (TsysOrg o : set) {
				eachOrg(o, buf, type, orgClass);
			}
		}
	}

	protected void eachOrg(TsysOrg org, StringBuffer buf) {
		eachOrg(org, buf, "org", null);
	}

	@Override
	public <T> List<T> getBeanBySQL(final String sql, final Class<T> entity,
			final Object... args) throws ServiceException {
		return baseDAO.doInHibernate(new HibernateCallback<List<T>>() {
			@SuppressWarnings("unchecked")
			public List<T> doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createSQLQuery(sql).addEntity(entity);
				for (int i = 0; i < args.length; i++) {
					query = query.setParameter(i, args[i]);
				}
				return query.list();
			}
		});
	}

	/**
	 * 得到用户的已授权菜单
	 * 
	 * @param user
	 * @return
	 * @author Xiaojf
	 * @since 2011-6-13
	 */
	public String getMenus(final TaclUserinfo user, final String flatid) {
		if (StringHelpers.isNull(flatid))
			return "[]";
		return baseDAO.doInHibernate(new HibernateCallback<String>() {
			@SuppressWarnings("unchecked")
			public String doInHibernate(Session session)
					throws HibernateException, SQLException {
				TsysFlat flat = (TsysFlat) session.get(TsysFlat.class, flatid);
				Set<TsysModuleinfo> set = flat.getTsysModuleinfos();
				// Query query = session
				// .createSQLQuery("select distinct moduleid from (select
				// moduleid from t_acl_roleprivilege where roleid in (select
				// roleid from t_acl_roleuser where userid=?) union all select
				// moduleid from t_acl_userprivilege where userid=?)");
				Query query = session
						.createSQLQuery("select moduleid from t_sys_moduleinfo where moduleid in(select distinct t.moduleid from (select r.moduleid from t_acl_roleprivilege  r where r.roleid in (select us.roleid from t_acl_roleuser us where us.userid=?) union all select ac.moduleid from t_acl_userprivilege ac where ac.userid=?) t) order by sortnum");
				query.setString(0, user.getUserid());
				query.setString(1, user.getUserid());
				List<String> assinged = query.list();
				List<Map<String, Object>> menus = new ArrayList<Map<String, Object>>();
				for (TsysModuleinfo info : set) {
					if (!StringUtils.isNullOrEmpty(info.getParentmoduleid()))
						continue;
					if (!assinged.contains(info.getModuleid())) {
						continue;
					}
					Map<String, Object> menu = new LinkedHashMap<String, Object>();
					eachMenu(info, menu, assinged);
					if (!menu.isEmpty())
						menus.add(menu);
				}
				return JsonUtil.toString(menus);
			}
		});
	}

	protected void eachMenu(TsysModuleinfo info, Map<String, Object> menu,
			List<String> assinged) {
		menu.put("id", info.getModuleid());
		menu.put("text", info.getModulename());
		Map<String, String> attr = new HashMap<String, String>();
		String icon = info.getModuleicon();
		if (StringHelpers.notNull(icon)) {
			menu.put("iconCls", "icon " + icon);
		}
		String url = info.getLinkedaddr();
		if (StringHelpers.notNull(url)) {
			attr.put("url", url);
		}
		menu.put("attributes", attr);
		@SuppressWarnings("unchecked")
		Set<TsysModuleinfo> set = info.getTsysModuleinfos();
		List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
		for (TsysModuleinfo child : set) {
			if (!assinged.contains(child.getModuleid()))
				continue;
			Map<String, Object> childMenu = new LinkedHashMap<String, Object>();
			eachMenu(child, childMenu, assinged);
			children.add(childMenu);
		}
		menu.put("children", children);
	}

	@Override
	public <T> List<T> getPageDataBySQL(Class<T> entity, String sql,
			int pageSize, int pageNo, Object... args) throws ServiceException {
		return baseDAO.getPageDateBySQL(entity, sql, pageSize, pageNo, args);
	}

	@Override
	public <T extends BaseEntity> T getBean(Class<T> entity, Serializable id)
			throws ServiceException {
		return baseDAO.getBean(entity, id);
	}

	@SuppressWarnings("unchecked")
	public String getPrivilege(String userId) throws ServiceException {
		String hql = "from TsysDataprivilege where userid=?";
		if (isSuperUser(userId)) {
			List<TsysOrg> list = baseDAO.getValueObjectsByHQL("from TsysOrg");
			return StringHelpers.join(list, "orgid");
		}
		List<TsysDataprivilege> datas = baseDAO.getValueObjectsByHQL(hql,
				userId);
		return StringHelpers.join(datas, "orgid");
	}

	@Override
	public String getPrivilege(final String userId, final String orgId)
			throws ServiceException {
		if (StringHelpers.isNull(orgId))
			return "-1";
		return baseDAO.doInHibernate(new HibernateCallback<String>() {
			public String doInHibernate(Session session)
					throws HibernateException, SQLException {
				String ids = StringHelpers.join(orgId.split(","));
				@SuppressWarnings("unchecked")
				List<TsysOrg> orgs = getValueObjectsByHQL("from TsysOrg where orgid in ("
						+ ids + ")");
				String sql = "select orgid from t_sys_dataprivilege where userid=?";
				Map<String, Object> map = null;
				if (isSuperUser(userId)) {
					sql = "select orgid from t_sys_org";
					map = queryAsMapBySQL(sql);
				} else {
					map = queryAsMapBySQL(sql, userId);
				}
				List<String> appender = new ArrayList<String>();
				for (TsysOrg org : orgs) {
					buildPrivilege(org, map, appender);
				}
				if (appender.size() == 0)
					appender.add("-1");
				return StringHelpers.join(appender.toArray());
			}

			// @SuppressWarnings("unchecked")
			protected void buildPrivilege(TsysOrg org, Map<String, Object> map,
					List<String> appender) {
				if (map.get(org.getOrgid()) != null) {
					appender.add(org.getOrgid());
				}
				// Set<TsysOrg> orgs = org.getTsysOrgs();
				// for (TsysOrg child : orgs) {
				// buildPrivilege(child, map, appender);
				// }
			}
		});
	}

	@Override
	public List<Map<String, Object>> queryForListByHQL(String hql,
			Object... args) throws ServiceException {
		return baseDAO.queryForListByHQL(hql, args);
	}

	@Override
	public List<Map<String, Object>> queryForListBySQL(String sql,
			Object... args) throws ServiceException {
		return baseDAO.queryForListBySQL(sql, args);
	}

	@Override
	public List<Map<String, Object>> queryForPagedListBySQL(String sql,
			int page, int rows, Object... args) throws ServiceException {
		return baseDAO.queryForPagedListBySQL(sql, page, rows, args);
	}

	@Override
	public List<Map<String, Object>> queryForPagedListByHQL(String hql,
			int page, int rows, Object... args) throws ServiceException {
		return baseDAO.queryForPagedListByHQL(hql, page, rows, args);
	}

	@Override
	public Integer executeSQL(String sql, Object... args)
			throws ServiceException {
		return baseDAO.executeSQL(sql, args);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDutyTree(String dutyId)
			throws ServiceException {
		List<TsysDuty> dutys = new ArrayList<TsysDuty>();
		if (StringHelpers.notNull(dutyId)) {
			dutys.add(getBean(TsysDuty.class, dutyId));
		} else {
			String hql = "from TsysDuty where parentDutyid is null";
			dutys = getValueObjectsByHQL(hql);
		}
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		for (TsysDuty duty : dutys) {
			if (duty == null)
				continue;
			eachDuty(duty, datas);
		}
		return datas;
	}

	protected void eachDuty(TsysDuty duty, List<Map<String, Object>> datas) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", duty.getDutyid());
		map.put("text", duty.getDutyname());
		@SuppressWarnings("unchecked")
		Set<TsysDuty> dutys = duty.getTsysDutys();
		if (dutys.size() > 0) {
			List<Map<String, Object>> children = new ArrayList<Map<String, Object>>();
			for (TsysDuty child : dutys) {
				eachDuty(child, children);
			}
			map.put("children", children);
		}
		datas.add(map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAssignedOrgTree(String userId,
			String orgClass) throws ServiceException {
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		List<TsysTreeprivilege> assigned = baseDAO.getValueObjectsByHQL(
				"from TsysTreeprivilege where userid=?", userId);
		List<TsysOrg> orgs = baseDAO
				.getValueObjectsByHQL("from TsysOrg where orgstate=? and parentorgid is null", Constant.ORG_STATUS_NORMAL);
		for (TsysOrg org : orgs) {
			if (!checkOrgClass(org, orgClass))
				continue;
			boolean pass = isSuperUser(userId)
					|| containInObj(assigned, "orgid", org.getOrgid());
			if (!pass)
				continue;
			buildAssigned(org, datas, userId, assigned, orgClass);
		}
		return datas;
	}

	/**
	 * 是否超级用户，是则权限不受限制。
	 * 
	 * @param uid
	 * @return
	 * @author xjf721
	 * @since 2012-2-1
	 */
	protected boolean isSuperUser(String uid) {
		if (StringHelpers.isNull(uid))
			return false;
		TaclUserinfo user = getBean(TaclUserinfo.class, uid);
		if (user == null)
			return false;
		String superuser = ParamUtil.getInstance().getKeyByVal("SUPER_USER",
				user.getLoginname());
		return StringHelpers.notNull(superuser);
	}

	@SuppressWarnings("unchecked")
	public void buildAssigned(TsysOrg org, List<Map<String, Object>> datas,
			String userId, List<TsysTreeprivilege> assigned, String orgClass) {
		if (Constant.ORG_STATUS_STOPED.equals(org.getOrgstate())) return;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", org.getOrgid());
		map.put("text", org.getOrgname());
		Set<TsysOrg> children = org.getTsysOrgs();
		if ("2".equals(org.getOrgClass()) && org.childrenSize() > 0) {
			map.put("state", "closed");
		}
		if (children.size() > 0) {
			List<Map<String, Object>> childs = new ArrayList<Map<String, Object>>();
			for (TsysOrg child : children) {
				if (!checkOrgClass(child, orgClass))
					continue;
				boolean pass = isSuperUser(userId)
						|| containInObj(assigned, "orgid", child.getOrgid());
				if (!pass)
					continue;
				buildAssigned(child, childs, userId, assigned, orgClass);
			}
			map.put("children", childs);
		}
		datas.add(map);
	}

	@Override
	public String getDirectOrgId(String userId, String orgClass)
			throws ServiceException {
		TaclUserinfo user = getBean(TaclUserinfo.class, userId);
		if (user == null)
			return null;
		TsysOrg org = user.getTsysOrg();
		if (StringHelpers.isNull(orgClass)) {
			if (org != null)
				return org.getOrgid();
			return null;
		}
		int cls = -1;
		try {
			int ls = Integer.valueOf(orgClass);
			do {
				cls = Integer.valueOf(org.getOrgClass());
				if (cls <= ls && "1".equals(org.getOrgSort()))
					return org.getOrgid();
			} while ((org = org.getTsysOrg()) != null);
		} catch (Exception e) {
		}
		return null;
	}

	public String getDirectOrgId(String userId) throws ServiceException {
		return getDirectOrgId(userId, null);
	}

	@Override
	public Map<String, Object> queryAsMapBySQL(String sql, Object... args)
			throws ServiceException {
		return baseDAO.queryAsMapBySQL(sql, args);
	}
}
