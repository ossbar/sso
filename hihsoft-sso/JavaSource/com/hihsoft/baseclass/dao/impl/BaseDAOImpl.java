package com.hihsoft.baseclass.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.hql.ast.QueryTranslatorImpl;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.hihframework.core.customtaglibs.pagetag.Page;
import com.hihframework.core.utils.BeanUtils;
import com.hihframework.core.utils.StringHelpers;
import com.hihsoft.baseclass.dao.BaseDAO;
import com.hihsoft.baseclass.model.BaseEntity;
import com.hihsoft.sso.sysmonitor.sysaudit.model.BaseAuditEntity;

/**
 * <p>
 * Title:框架持久化层DAO接口实现类基类
 * Description:支持各种过滤条件：数组、Map
 * Copyright: Copyright (c) 2013
 * Company:hihsoft.co.,ltd
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
@Repository
public class BaseDAOImpl extends HibernateDaoSupport implements BaseDAO {
	protected final static Logger log = Logger.getLogger(BaseDAOImpl.class);
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public void deleteBatchVO(final List dataList) throws DataAccessException {
		getHibernateTemplate().deleteAll(dataList);
	}

	@Override
	public void executeQueryByHQL(String hql, Object... params)
			throws DataAccessException {
		Session session = getSession();
		Query query = session.createQuery(hql);
		for (int i = 0; i < params.length; i++) {
			query = query.setParameter(i, params[i]);

		}
		query.executeUpdate();
		releaseSession(session);
	}

	@Override
	public void deleteVO(final BaseEntity vo, final String id)
			throws DataAccessException {
		getHibernateTemplate().delete(getVO(vo, id));
	}

	/**
	 * 保存对象，返回主键ID
	 * 
	 */
	@Override
	public String saveOrUpdateVOAndReturnId(final BaseEntity vo)
			throws DataAccessException {
		final Serializable result = getHibernateTemplate().save(vo);
		return (String) result;
	}

	@Override
	public void deleteVO(final BaseEntity vo) throws DataAccessException {
		getHibernateTemplate().delete(vo);
	}

	/**
	 * 带审计对象
	 */
	public void deleteVO(final BaseAuditEntity vo) throws DataAccessException {
		getHibernateTemplate().delete(vo);
	}

	@Override
	public int getDataTotalNum(final String hql) throws DataAccessException {
		int amount = 0;
		final Session session = getSession(); // 取得session
		Query query = null;
		final int start = hql.toLowerCase().indexOf("from");
		query = session.createQuery("select count(*) from "
				+ hql.substring(start + 4)); // 查询符合条件数据
		final List list = query.list();
		if (!list.isEmpty()) {
			amount = Integer.parseInt(list.get(0).toString());

		}
		releaseSession(session); // 关闭session

		return amount;
	}

	@Override
	public int getDataTotalNum(final String hql, final String arg1)
			throws DataAccessException {

		int amount = 0;
		final Session session = getSession(); // 取得session
		Query query = null;
		query = session.createQuery(hql); // 查询符合条件数据
		query.setString(0, arg1);
		final List list = query.list();
		if (!list.isEmpty()) {
			amount = list.size();

		}
		releaseSession(session);
		return amount;
	}

	public int getTotalNumBySQL(String sql, Object... args)
			throws DataAccessException {
		int amount = 0;
		final Session session = getSession(); // 取得session
		Query query = session.createSQLQuery("select count(*)  from (" + sql
				+ ") count_table"); // 查询符合条件数据
		for (int i = 0; i < args.length; i++) {
			query = query.setParameter(i, args[i]);
		}
		Object result = query.uniqueResult();
		releaseSession(session);
		amount = Integer.parseInt(result.toString());
		return amount;
	}

	@Override
	public int getDataTotalNum(final String hql, final Object obj)
			throws DataAccessException {

		int amount = 0;
		final Session session = getSession(); // 取得session
		Query query = null;
		final int start = hql.toLowerCase().indexOf("from");
		query = session.createQuery(
				"select count(*) from " + hql.substring(start + 4))
				.setProperties(obj); // 查询符合条件数据
		final List list = query.list();
		if (!list.isEmpty()) {
			amount = Integer.parseInt(list.get(0).toString());

		}
		releaseSession(session); // 关闭session

		return amount;
	}

	@Override
	public int getDataTotalNum(String hql, Object... filter)
			throws DataAccessException {
		QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(hql, hql,
				Collections.EMPTY_MAP,
				(SessionFactoryImplementor) this.getSessionFactory());
		queryTranslator.compile(Collections.EMPTY_MAP, false);
		String tempSQL = queryTranslator.getSQLString();
		String countSQL = "select count(*) from (" + tempSQL + ") tmp_count_t";
		final Session session = getSession(); // 取得session
		Query query = session.createSQLQuery(countSQL);
		int amount = 0;
		for (int i = 0; i < filter.length; i++) {
			query = query.setParameter(i, filter[i]);
		}
		final List list = query.list();
		if (!list.isEmpty()) {
			amount = Integer.parseInt(list.get(0).toString());
		}
		releaseSession(session); // 关闭session
		return amount;
	}

	@Override
	public int getDataTotalNum(final BaseEntity vo, final String whereClause)
			throws DataAccessException {

		String className = vo.getClass().getName();
		className = className.substring(className.lastIndexOf(".") + 1);
		String hql = "select count(*) from " + className + " ";

		if (whereClause != null && !whereClause.trim().equals("")) {
			final int wherePos = whereClause.trim().toUpperCase()
					.indexOf("where");

			if (wherePos >= 0)
				hql += whereClause.trim();
			else
				hql += " where " + whereClause.trim();
		}

		final Session session = getSession();
		final Query query = session.createQuery(hql);
		final List list = query.list();
		releaseSession(session); // 关闭session
		if (list != null && !list.isEmpty()) {
			return Integer.parseInt(list.get(0).toString());
		} else
			return 0;
	}

	@Override
	public List getPageDataByHQL(String hql, int page_size, int pageNo,
			Object... args) throws DataAccessException {
		List list = new ArrayList();
		int total_page = 0; // 总页数

		final int total_num = getDataTotalNum(hql, args); // 总记录数
		if (total_num % page_size == 0) { // 取得总页数
			total_page = total_num / page_size;
		} else {
			total_page = total_num / page_size + 1;
		}
		if (pageNo > total_page)
			pageNo = total_page;
		final int start = (pageNo - 1) * page_size; // 开始数据的位置
		final int rowNum = page_size; // 每页显示数据
		// System.out.println("传递的参数为 "+obj);

		final Session session = getSession(); // 取得session
		Query query = null;
		query = session.createQuery(hql); // 查询符合条件的数据
		for (int i = 0; i < args.length; i++) {
			query = query.setParameter(i, args[i]);

		}
		query.setReadOnly(true); // 设置此连接为只读属性
		query.setFirstResult(start);
		query.setMaxResults(rowNum);

		list = query.list();
		releaseSession(session); // 关闭session

		return list;
	}

	@Override
	public List getPageDataByHQL(final String hql,
			final Map<String, Object> filter) throws DataAccessException {

		List list = new ArrayList();
		final Session session = getSession(); // 取得session
		Query query = null;
		query = session.createQuery(hql); // 查询符合条件的数据
		final Iterator it = filter.keySet().iterator();
		while (it.hasNext()) {
			final String key = (String) it.next();
			query = query.setParameter(key, filter.get(key));
			log.info(key + ":xxxxxxxxxxxxxxxxxxxxxxxxxxxxx:" + filter.get(key));

		}
		list = query.list();
		releaseSession(session); // 关闭session

		return list;
	}

	@Override
	public List getPageDataByHQL(final String hql, final int page_size,
			int pageNo) throws DataAccessException {
		List list = new ArrayList();
		int total_page = 0; // 总页数
		final int total_num = getHibernateTemplate().find(hql).size(); // 总记录数
		if (total_num < 1) {
			return list;
		}
		if (total_num % page_size == 0) { // 取得总页数
			total_page = total_num / page_size;
		} else {
			total_page = total_num / page_size + 1;
		}
		if (pageNo > total_page)
			pageNo = total_page;
		final int start = (pageNo - 1) * page_size; // 开始数据的位置
		final int rowNum = page_size; // 每页显示数据

		final Session session = getSession(); // 取得session
		Query query = null;
		query = session.createQuery(hql); // 查询符合条件的数据
		query.setFirstResult(start);
		query.setMaxResults(rowNum);
		list = query.list();
		releaseSession(session); // 关闭session

		return list;
	}

	@Override
	public List getPageDataByHQL(final String hql, final String arg1,
			final int page_size, int pageNo) throws DataAccessException {

		List list = new ArrayList();
		int total_page = 0; // 总页数

		final int total_num = getDataTotalNum(hql, arg1); // 总记录数
		if (total_num % page_size == 0) { // 取得总页数
			total_page = total_num / page_size;
		} else {
			total_page = total_num / page_size + 1;
		}
		if (pageNo > total_page)
			pageNo = total_page;
		final int start = (pageNo - 1) * page_size; // 开始数据的位置
		final int rowNum = page_size; // 每页显示数据
		final Session session = getSession(); // 取得session
		Query query = null;
		query = session.createQuery(hql); // 查询符合条件的数据
		query.setString(0, "%" + arg1 + "%");
		query.setFirstResult(start);
		query.setMaxResults(rowNum);

		list = query.list();
		releaseSession(session); // 关闭session

		return list;
	}

	@Override
	public List getPageDataByHQL(final String hql, final Object obj,
			final int page_size, int pageNo) throws DataAccessException {

		List list = new ArrayList();
		int total_page = 0; // 总页数

		final int total_num = getDataTotalNum(hql, obj); // 总记录数
		if (total_num % page_size == 0) { // 取得总页数
			total_page = total_num / page_size;
		} else {
			total_page = total_num / page_size + 1;
		}
		if (pageNo > total_page)
			pageNo = total_page;
		final int start = (pageNo - 1) * page_size; // 开始数据的位置
		final int rowNum = page_size; // 每页显示数据
		// System.out.println("传递的参数为 "+obj);

		final Session session = getSession(); // 取得session
		Query query = null;
		query = session.createQuery(hql).setProperties(obj); // 查询符合条件的数据
		query.setFirstResult(start);
		query.setMaxResults(rowNum);

		list = query.list();
		releaseSession(session); // 关闭session

		return list;
	}

	@Override
	public List getPageDataByHQL(final String hql,
			final Map<String, Object> filter, final int page_size, int pageNo)
			throws DataAccessException {

		List list = new ArrayList();
		int total_page = 0; // 总页数

		final int total_num = getDataTotalNum(hql, filter); // 总记录数
		if (total_num % page_size == 0) { // 取得总页数
			total_page = total_num / page_size;
		} else {
			total_page = total_num / page_size + 1;
		}
		if (pageNo > total_page)
			pageNo = total_page;
		final int start = (pageNo - 1) * page_size; // 开始数据的位置
		final int rowNum = page_size; // 每页显示数据
		// System.out.println("传递的参数为 "+obj);

		final Session session = getSession(); // 取得session
		Query query = null;
		query = session.createQuery(hql); // 查询符合条件的数据
		final Iterator it = filter.keySet().iterator();
		while (it.hasNext()) {
			final String key = (String) it.next();
			query = query.setParameter(key, filter.get(key));

		}
		query.setFirstResult(start);
		query.setMaxResults(rowNum);

		list = query.list();
		releaseSession(session); // 关闭session

		return list;
	}

	@Override
	public List getPageDataByHQL(final String hql, final int page_size,
			int pageNo, final int total_num) throws DataAccessException {

		log.info("查询数据 " + hql);
		List list = new ArrayList();
		int total_page = 0; // 总页数
		if (total_num % page_size == 0) { // 取得总页数
			total_page = total_num / page_size;
		} else {
			total_page = total_num / page_size + 1;
		}
		if (pageNo > total_page)
			pageNo = total_page;
		final int start = (pageNo - 1) * page_size; // 开始数据的位置
		final int rowNum = page_size; // 每页显示数据

		final Session session = getSession(); // 取得session
		final Query query = session.createQuery(hql.trim()); // 查询符合条件的数据
		query.setFirstResult(start);
		query.setMaxResults(rowNum);
		list = query.list();
		releaseSession(session); // 关闭session

		return list;
	}

	@Override
	public BaseEntity getVO(final BaseEntity vo, final String id)
			throws DataAccessException {
		final BaseEntity BaseEntity = getHibernateTemplate().get(vo.getClass(),
				id);

		if (BaseEntity == null) {
			throw new ObjectRetrievalFailureException(vo.getClass(), id);
		}

		return BaseEntity;

	}

	@Override
	public BaseEntity getVO(final BaseEntity vo) throws DataAccessException {
		return (BaseEntity) getHibernateTemplate().loadAll(vo.getClass());
	}

	@Override
	public List getValueObjectBySQL(final String sql)
			throws DataAccessException {
		final Session session = getSession(); // 取得session
		Query query = null;
		query = session.createSQLQuery(sql); // 查询符合条件数据
		final List list = query.list();// 返回数组对象,给实体赋值时循环数组对象就行了.
		releaseSession(session); // 关闭session

		return list;
	}

	@Override
	public List getValueObjectBySQL(final String sql, Object... args)
			throws DataAccessException {
		Session session = getSession();
		Query query = null;
		query = session.createSQLQuery(sql);
		for (int i = 0; i < args.length; i++) {
			query = query.setParameter(i, args[i]);

		}
		List list = query.list();
		releaseSession(session);
		return list;
	}

	@Override
	public List getValueObjectsByHQL(final String hql)
			throws DataAccessException {

		return getHibernateTemplate().find(hql);
	}

	@Override
	public List getValueObjectsByHQL(final String hql, Object... object)
			throws DataAccessException {

		return getHibernateTemplate().find(hql, object);
	}

	@Override
	public void saveOrUpdateBatchVO(final List dataList)
			throws DataAccessException {
		getHibernateTemplate().saveOrUpdateAll(dataList);
	}

	@Override
	public void saveOrUpdateVO(final BaseEntity vo) throws DataAccessException {
		getHibernateTemplate().saveOrUpdate(vo);

	}

	/**
	 * 带审计对象
	 */
	public void saveOrMergeVO(final BaseAuditEntity vo)
			throws DataAccessException {
		getHibernateTemplate().merge(vo);

	}

	/*
	 * 通过 queryName 查询数据库中的持久对象,而queyName是定义在此对象对应的hibernate的映射文件中的! 定义语法如下:
	 * <query name="queryUser"> <![CDATA[ select user.name from TAclUser as user
	 * where user.userid=3 ]]> </query>
	 */
	@Override
	public List getValueObjectByNameQuery(final String queryName)
			throws DataAccessException {
		return getHibernateTemplate().findByNamedQuery(queryName);
	}
	@Override
	public List getValueObjectByNameQuery(final String queryName,
			final Object object) throws DataAccessException {
		return getHibernateTemplate().findByNamedQuery(queryName, object);
	}

	@Override
	public List getValueObjectByNameQuery(final String queryName,
			final Object... filter) throws DataAccessException {
		return getHibernateTemplate().findByNamedQuery(queryName, filter);
	}

	@Override
	public List getValueObjectByDetachedCriteria(
			final DetachedCriteria detachedCriteria) throws DataAccessException {
		return getHibernateTemplate().findByCriteria(detachedCriteria);
	}

	@Override
	public List getValueObjectByDetachedCriterias(
			final DetachedCriteria detachedCriteria, final int arg1,
			final int arg2) throws DataAccessException {
		return getHibernateTemplate().findByCriteria(detachedCriteria, arg1,
				arg2);
	}

	@Override
	public List getPageDataBySQL(String sql, int page_size, int pageNo,
			Object... args) throws DataAccessException {
		List list = new ArrayList();
		int total_page = 0; // 总页数

		final int total_num = getDataTotalNum(sql, args); // 总记录数
		if (total_num % page_size == 0) { // 取得总页数
			total_page = total_num / page_size;
		} else {
			total_page = total_num / page_size + 1;
		}
		if (pageNo > total_page)
			pageNo = total_page;
		final int start = (pageNo - 1) * page_size; // 开始数据的位置
		final int rowNum = page_size; // 每页显示数据
		final Session session = getSession(); // 取得session
		Query query = null;
		query = session.createSQLQuery(sql).setProperties(args); // 查询符合条件的数据
		query.setFirstResult(start);
		query.setMaxResults(rowNum);
		list = query.list();
		releaseSession(session); // 关闭session
		return list;
	}

	@Override
	public List getPageDataBySQL(final String sql, final int page_size,
			int pageNo) throws DataAccessException {

		List list = new ArrayList();
		int total_page = 0; // 总页数
		final int total_num = getHibernateTemplate().find(sql).size(); // 总记录数
		if (total_num < 1) {
			return list;
		}
		if (total_num % page_size == 0) { // 取得总页数
			total_page = total_num / page_size;
		} else {
			total_page = total_num / page_size + 1;
		}
		if (pageNo > total_page)
			pageNo = total_page;
		final int start = (pageNo - 1) * page_size; // 开始数据的位置
		final int rowNum = page_size; // 每页显示数据

		final Session session = getSession(); // 取得session
		Query query = null;
		query = session.createSQLQuery(sql); // 查询符合条件的数据
		query.setFirstResult(start);
		query.setMaxResults(rowNum);
		list = query.list();
		releaseSession(session); // 关闭session

		return list;
	}

	/**
	 * 模拟前端新闻及文档的分页
	 * 
	 * @param page
	 * @return
	 * @throws DataAccessException
	 */
	@Override
	public List getFrontPage(final Page page) throws DataAccessException {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Object doInHibernate(final Session s)
					throws HibernateException, SQLException {
				final Query query = s.createQuery(page.getHql());
				query.setFirstResult(page.getStartRs());
				query.setMaxResults(page.getPerPage());
				final List list = query.list();
				return list;

			}
		});
	}

	@Override
	public <T> T doInHibernate(HibernateCallback<T> callback) {
		return getHibernateTemplate().execute(callback);
	}

	@Override
	public <T extends BaseEntity> T getBean(Class<T> type, Serializable id)
			throws DataAccessException {
		return getHibernateTemplate().get(type, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> loadByField(Class<T> type, String field, Object value)
			throws DataAccessException {
		String hql = "from "
				+ type.getName().substring(type.getName().lastIndexOf(".") + 1)
				+ " where " + field + "=?";
		return getHibernateTemplate().find(hql, value);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getPageDateBySQL(Class<T> entity, String sql,
			int pageSize, int pageNo, Object... args)
			throws DataAccessException {
		List<T> list = new ArrayList<T>();
		int total_page = 0; // 总页数

		final int total_num = getTotalNumBySQL(sql); // 总记录数
		if (total_num % pageSize == 0) { // 取得总页数
			total_page = total_num / pageSize;
		} else {
			total_page = total_num / pageSize + 1;
		}
		if (pageNo > total_page)
			pageNo = total_page;
		final int start = (pageNo - 1) * pageSize; // 开始数据的位置
		final int rowNum = pageSize; // 每页显示数据

		final Session session = getSession(); // 取得session
		Query query = null;
		query = session.createSQLQuery(sql).addEntity(entity); // 查询符合条件的数据
		for (int i = 0; i < args.length; i++) {
			query = query.setParameter(i, args[i]);
		}
		query.setReadOnly(true); // 设置此连接为只读属性
		query.setFirstResult(start);
		query.setMaxResults(rowNum);

		list = query.list();
		releaseSession(session); // 关闭session

		return list;
	}

	public List<Map<String, Object>> queryForListBySQL(String sql,
			Object... args) throws DataAccessException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = SessionFactoryUtils.getDataSource(
					getSessionFactory()).getConnection();
			pstmt = conn.prepareStatement(sql);
			int index = 0;
			for (Object o : args) {
				pstmt.setObject(++index, o);
			}
			rs = pstmt.executeQuery();
			ResultSetMetaData meta = rs.getMetaData();
			int cols = meta.getColumnCount();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= cols; i++) {
					String key = meta.getColumnName(i);
					Object value = rs.getObject(i);
					map.put(key, value);
				}
				list.add(map);
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			log.error(e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null && !conn.isClosed()) conn.close();
			} catch (SQLException e) {
				log.error(e);
			}
		}
		return list;
	}

	public List<Map<String, Object>> queryForPagedListBySQL(String sql,
			int page, int rows, Object... args) throws DataAccessException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = SessionFactoryUtils.getDataSource(
					getSessionFactory()).getConnection();
			pstmt = conn.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			int index = 0;
			for (Object o : args) {
				pstmt.setObject(++index, o);
			}
			pstmt.setMaxRows(page * rows);
			rs = pstmt.executeQuery();
			ResultSetMetaData meta = rs.getMetaData();
			int cols = meta.getColumnCount();
			int first = (page - 1) * rows;
			if (first == 0) {
				rs.beforeFirst();
			} else {
				rs.absolute(first);
			}
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= cols; i++) {
					String key = meta.getColumnName(i);
					Object value = rs.getObject(i);
					if (value == null)
						value = "";
					map.put(key, value);
				}
				list.add(map);
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			log.error(e);
		} finally {
			try {
				if (rs != null) rs.close();
				if (pstmt != null) pstmt.close();
				if (conn != null && !conn.isClosed()) conn.close();
			} catch (SQLException e) {
				log.error(e);
			}
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> queryForListByHQL(final String hql,
			final Object... args) throws DataAccessException {
		return getHibernateTemplate().execute(
				new HibernateCallback<List<Map<String, Object>>>() {
					public List<Map<String, Object>> doInHibernate(
							Session session) throws HibernateException,
							SQLException {
						Query query = session.createQuery(hql);
						for (int i = 0; i < args.length; i++) {
							query.setParameter(i, args[i]);
						}
						String[] keys = query.getReturnAliases();
						List<?> datas = query.list();
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						for (Object row : datas) {
							Map<String, Object> map = new HashMap<String, Object>();
							if (row.getClass().isArray()) {
								int length = Array.getLength(row);
								for (int i = 0; i < length
										&& keys.length == length; i++) {
									Object value = Array.get(row, i);
									if (value == null)
										continue;
									map.put(keys[i], value);
								}
							} else {
								map.putAll(BeanUtils.bean2Map(row));
							}
							list.add(map);
						}
						return list;
					}
				});
	}

	@Override
	public List<Map<String, Object>> queryForPagedListByHQL(final String hql,
			final int page, final int rows, final Object... args)
			throws DataAccessException {
		return getHibernateTemplate().execute(
				new HibernateCallback<List<Map<String, Object>>>() {
					public List<Map<String, Object>> doInHibernate(
							Session session) throws HibernateException,
							SQLException {
						Query query = session.createQuery(hql);
						query.setMaxResults(page * rows);
						for (int i = 0; i < args.length; i++) {
							query.setParameter(i, args[i]);
						}
						String[] keys = query.getReturnAliases();
						List<?> datas = query.setMaxResults(rows)
								.setFirstResult((page - 1) * rows).list();
						List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
						for (Object row : datas) {
							Map<String, Object> map = new HashMap<String, Object>();
							if (row.getClass().isArray()) {
								int length = Array.getLength(row);
								for (int i = 0; i < length
										&& keys.length == length; i++) {
									Object value = Array.get(row, i);
									if (value == null)
										continue;
									map.put(keys[i], value);
								}
							} else {
								map.putAll(BeanUtils.bean2Map(row));
							}
							list.add(map);
						}
						return list;
					}
				});
	}

	

	@Override
	public Integer executeSQL(final String sql, final Object... args)
			throws DataAccessException {
		return getHibernateTemplate().execute(new HibernateCallback<Integer>() {
			public Integer doInHibernate(Session session)
					throws HibernateException, SQLException {
				SQLQuery sqlQuery = session.createSQLQuery(sql);
				for (int i = 0; i < args.length; i++) {
					sqlQuery.setParameter(i, args[i]);
				}
				return sqlQuery.executeUpdate();
			}
		});
	}

	public Map<String, Object> queryAsMapBySQL(final String sql,
			final Object... args) throws DataAccessException {
		return getHibernateTemplate().execute(
				new HibernateCallback<Map<String, Object>>() {
					public Map<String, Object> doInHibernate(Session session)
							throws HibernateException, SQLException {
						Map<String, Object> map = new HashMap<String, Object>();
						Connection conn = null;
						PreparedStatement pstmt = null;
						ResultSet rs = null;
						try {
							conn = SessionFactoryUtils
									.getDataSource(getSessionFactory())
									.getConnection();
							pstmt = conn
									.prepareStatement(sql);
							int index = 0;
							for (Object o : args) {
								pstmt.setObject(++index, o);
							}
							rs = pstmt.executeQuery();
							ResultSetMetaData meta = rs.getMetaData();
							int cols = meta.getColumnCount();
							while (rs.next()) {
								String key = rs.getString(1);
								if (key == null)
									key = "";
								if (cols >= 2) {
									map.put(key, rs.getObject(2));
								} else if (cols == 1) {
									map.put(key, key);
								}
							}
							pstmt.close();
							conn.close();
						} catch (SQLException e) {
							log.error(e);
						} finally {
							try {
								if (rs != null) rs.close();
								if (pstmt != null) pstmt.close();
								if (conn != null && !conn.isClosed()) conn.close();
							} catch (SQLException e) {
								log.error(e);
							}
						}
						return map;
					}
				});
	}
}
