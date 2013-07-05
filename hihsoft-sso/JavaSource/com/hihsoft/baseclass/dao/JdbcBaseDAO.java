/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.baseclass.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
/**
 * JDBC基础操作类
 * @author hihsoft.co.,ltd
 * @since 2013-10-26
 * @param <T> 
 */
public interface JdbcBaseDAO<T> extends JdbcOperations{

	public List<T> queryList(String sql, Object... params) throws DataAccessException;
	
	public Map<String, Object> queryMap(String sql, Object... params) throws DataAccessException;
	
	public Integer getTotalNum(String sql, Object... params) throws DataAccessException;
	
	public void setMapper(RowMapper<T> m);
	
	public List<T> queryPage(String sql, int pageNum, int pageSize, Object... params) throws DataAccessException;
}
