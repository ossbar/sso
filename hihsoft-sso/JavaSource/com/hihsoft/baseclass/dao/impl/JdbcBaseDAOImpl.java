package com.hihsoft.baseclass.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.hihsoft.baseclass.dao.JdbcBaseDAO;
/**
 * <p>
 * Title:基于JDBC框架持久化层DAO接口实现类基类
 * Description:支持各种过滤条件：数组、Map
 * Copyright: Copyright (c) 2013
 * Company:hihsoft.co.,ltd
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public abstract class JdbcBaseDAOImpl<T extends RowMapper<T>> extends JdbcTemplate implements JdbcBaseDAO<T> {

	protected RowMapper<T> mapper = null;
	
	public void setMapper(RowMapper<T> m) {
		this.mapper = m;
	}
	@SuppressWarnings("unchecked")
	public JdbcBaseDAOImpl() {
		try {
			Class<T> t = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
			setMapper(t.newInstance());
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	@Override
	public List<T> queryList(String sql, Object... params)
			throws DataAccessException {
		return query(sql, mapper, params);
	}

	@Override
	public Map<String, Object> queryMap(String sql,	Object... params) throws DataAccessException {
		return queryForMap(sql, mapper, params); 
	}

	@Override
	public Integer getTotalNum(String sql, Object... params)
			throws DataAccessException {
		sql = "select count(*) from ("+sql+")";
		return query(sql, new ResultSetExtractor<Integer>(){
			public Integer extractData(ResultSet rs)
					throws SQLException, DataAccessException {
				if (rs.next()) {
					String num = rs.getString(1);
					if (num != null) return new Integer(num);
					rs.afterLast();
				}
				return null;
			}}, params);
	}
	
	public List<T> queryPage(String sql, int pageNum, int pageSize, Object... params) throws DataAccessException {
		final int begin = (pageNum - 1) * pageSize;
		final int end = begin + pageSize;
		final List<T> list = new ArrayList<T>();
		query(sql, new RowMapper<T>() {
			public T mapRow(ResultSet rs, int i)
					throws SQLException {
				if (i >= begin && i < end) {
					list.add(mapper.mapRow(rs, i));
				}
				return null;
			}
		});
		return list;
	}
}
