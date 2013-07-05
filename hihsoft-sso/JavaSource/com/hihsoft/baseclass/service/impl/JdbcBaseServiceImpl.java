package com.hihsoft.baseclass.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.hihframework.exception.ServiceException;
import com.hihsoft.baseclass.dao.JdbcBaseDAO;
import com.hihsoft.baseclass.service.JdbcBaseService;

public class JdbcBaseServiceImpl<T extends RowMapper<T>> implements JdbcBaseService<T> {
	private JdbcBaseDAO<T> dao;
	
	public void setDao(JdbcBaseDAO<T> dao) {
		this.dao = dao;
	}
	
	@Override
	public List<T> queryList(String sql, Object... params)
			throws ServiceException {
		return dao.queryList(sql, params);
	}

	@Override
	public Map<String, Object> queryMap(String sql, Object... params)
			throws ServiceException {
		return dao.queryMap(sql, params);
	}

	@Override
	public Integer getTotalNum(String sql, Object... params)
			throws ServiceException {
		return dao.getTotalNum(sql, params);
	}

	@Override
	public void setMapper(RowMapper<T> m) {
		dao.setMapper(m);
	}

	@Override
	public List<T> queryPage(String sql, int pageNum, int pageSize,
			Object... params) throws ServiceException {
		return dao.queryPage(sql, pageNum, pageSize, params);
	}

}
