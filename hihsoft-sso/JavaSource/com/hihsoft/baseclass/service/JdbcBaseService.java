package com.hihsoft.baseclass.service;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.hihframework.exception.ServiceException;
/**
 * 
 * @author hihsoft.co.,ltd
 * @since 2013-10-26
 */
public interface JdbcBaseService<T extends RowMapper<T>> {

	public List<T> queryList(String sql, Object... params) throws ServiceException;
	
	public Map<String, Object> queryMap(String sql, Object... params) throws ServiceException;
	
	public Integer getTotalNum(String sql, Object... params) throws ServiceException;
	
	public void setMapper(RowMapper<T> m);
	
	public List<T> queryPage(String sql, int pageNum, int pageSize, Object... params) throws ServiceException;
}
