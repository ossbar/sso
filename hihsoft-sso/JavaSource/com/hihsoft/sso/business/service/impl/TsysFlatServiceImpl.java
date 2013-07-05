package com.hihsoft.sso.business.service.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;


import com.hihframework.exception.ServiceException;
import com.hihsoft.baseclass.service.impl.BaseServiceImpl;
import com.hihsoft.sso.business.model.*;
import com.hihsoft.sso.business.service.*;
@Service(value ="tsysFlatService")
public class TsysFlatServiceImpl extends BaseServiceImpl implements
		TsysFlatService {
	/************ 查询所有的TSYSFLAT ******************/
	private static final String ALLTSYSFLAT_HQL = " from TsysFlat order by flatdesc";

	/************ 通过主键查询TSYSFLAT ******************/
	private static final String TSYSFLATById_HQL = " from TsysFlat tsysFlat where tsysFlat.flatid=?";

	/************ 通过不同的条件组合，利用Hibernate HQL查询TSYSFLAT ******************/
	private static final StringBuffer QUERY_TSYSFLAT_HQL = new StringBuffer(
			" from TsysFlat tsysFlat where 1=1");

	/************ 通过不同的条件组合，利用JDBC SQL查询TSYSFLAT ******************/
	private static final StringBuffer QUERY_TSYSFLAT_SQL = new StringBuffer(
			"select * from T_SYS_FLAT t where 1=1");

	/**
	 * 新增、修改TsysFlat信息
	 * 
	 * @param tsysFlat
	 * @throws DataAccessException
	 */
	public void saveOrUpdateTsysFlat(TsysFlat tsysFlat) throws ServiceException {
		 baseDAO.saveOrUpdateVO(tsysFlat);
		//baseDAO.saveOrMergeVO(tsysFlat);
	}

	/**
	 * 删除TsysFlat信息
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public void deleteTsysFlat(String id) throws ServiceException {
		TsysFlat tsysFlat = this.getTsysFlatById(id);
		baseDAO.deleteVO(tsysFlat);
	}

	/**
	 * 通过HQL构造查询条件来查询符合条件的TsysFlat信息
	 * 
	 * @param hql
	 * @return List
	 * @throws DataAccessException
	 */
	public List getTsysFlatByHQL(String hql) throws ServiceException {
		return baseDAO.getValueObjectsByHQL(hql);

	}

	/**
	 * 查询所有的TsysFlat信息
	 * 
	 * @param hql
	 * @return List
	 * @throws DataAccessException
	 */
	public List getAllTsysFlat() throws ServiceException {
		return baseDAO.getValueObjectsByHQL(ALLTSYSFLAT_HQL);
	}

	/**
	 * 根据主键查询TsysFlat信息明细
	 * 
	 * @param id
	 * @throws DataAccessException
	 */
	public TsysFlat getTsysFlatById(String id) throws ServiceException {
		TsysFlat tsysFlat = null;
		List list = baseDAO.getValueObjectsByHQL(TSYSFLATById_HQL,
				new Object[] { id });
		if (!list.isEmpty() && list.size() > 0) {
			tsysFlat = (TsysFlat) list.get(0);
		}
		return tsysFlat;
	}

	/**
	 * 把查询条件构造成数组来查询TsysFlat信息
	 * 
	 * @param Object
	 *            [] object
	 * @return List
	 * @throws DataAccessException
	 */
	public List getTsysFlatByArray(Object[] filter) throws ServiceException {
		return baseDAO.getValueObjectsByHQL(QUERY_TSYSFLAT_HQL.toString(),
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

	public int getTsysFlatDataTotalNum(Object[] filter) throws ServiceException {
		return baseDAO.getDataTotalNum(QUERY_TSYSFLAT_HQL.toString(), filter);
	}

	/**
	 * 把查询条件构造成数组来查询TsysFlat信息
	 * 
	 * @param Map
	 *            filter
	 * @return List
	 * @throws DataAccessException
	 */
	public List getTsysFlatByMap(Map filter) throws ServiceException {
		return baseDAO.getPageDataByHQL(QUERY_TSYSFLAT_HQL.toString(), filter);
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

	public List getTsysFlatPageDataByArray(Object[] filter, int page_size,
			int pageNo) throws ServiceException {
		return baseDAO.getPageDataByHQL(QUERY_TSYSFLAT_HQL.toString(), filter,
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
	public List getTsysFlatPageDataByMap(Map filter, int page_size, int pageNo)
			throws ServiceException {
		String id = (String) filter.get("id");
		return baseDAO.getPageDataByHQL(QUERY_TSYSFLAT_HQL.toString(), filter,
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
	public List getTsysFlatValueObjectWithSQLByArray(Object[] filter)
			throws ServiceException {
		return baseDAO.getValueObjectBySQL(QUERY_TSYSFLAT_SQL.toString(),
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
	public List getTsysFlatValueObjectByNameQuery(String queryName,
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
	public List getTsysFlatValueObjectByDetachedCriteria(
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
	public List getTsysFlatValueObjectByDetachedCriterias(
			DetachedCriteria detachedCriteria, int arg1, int arg2)
			throws ServiceException {
		return baseDAO.getValueObjectByDetachedCriterias(detachedCriteria,
				arg1, arg2);
	}

	public List<TsysFlat> getFlatsByUser(String userId) {
		String sql = "select * from t_sys_flat where flatid in (select flatid from t_sys_moduleinfo where moduleid in (select moduleid from t_acl_roleprivilege where roleid in(select roleid from t_acl_roleuser where userid=?)) or moduleid in (select moduleid from t_acl_userprivilege where userid=?)) order by flatdesc";
		return getBeanBySQL(sql, TsysFlat.class, userId, userId);
	}
}
