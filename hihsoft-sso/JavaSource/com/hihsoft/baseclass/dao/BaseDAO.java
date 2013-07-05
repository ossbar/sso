package com.hihsoft.baseclass.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.hihframework.core.customtaglibs.pagetag.Page;
import com.hihsoft.baseclass.model.BaseEntity;
import com.hihsoft.sso.sysmonitor.sysaudit.model.BaseAuditEntity;

/**
 * <p> Title:框架持久化层DAO接口类基类</p>
 * <p> Description:采用Hibernate：hql查询,及原生SQL查询相结合</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public interface BaseDAO {

	/**
	 * 新增、修改对象.
	 *
	 * @param vo the vo
	 * @throws DataAccessException the data access exception
	 */
	public void saveOrUpdateVO(BaseEntity vo) throws DataAccessException;

	/**
	 * hibernate中同一个session里面有了两个相同标识但是是不同实体.
	 *
	 * @param vo the vo
	 * @throws DataAccessException the data access exception
	 */
	public void saveOrMergeVO(BaseAuditEntity vo) throws DataAccessException;

	/**
	 * 新增、修改对象.
	 *
	 * @param vo the vo
	 * @return the string
	 * @throws DataAccessException the data access exception
	 */
	public String saveOrUpdateVOAndReturnId(BaseEntity vo)
			throws DataAccessException;

	// 执行普通hql（查询 删除）
	/**
	 * Execute query by hql.
	 *
	 * @param hql the hql
	 * @throws DataAccessException the data access exception
	 */
	public void executeQueryByHQL(String hql, Object... params)
			throws DataAccessException;

	/**
	 * 批量新增、修改集合对象.
	 *
	 * @param dataList the data list
	 * @throws DataAccessException the data access exception
	 */
	public void saveOrUpdateBatchVO(List dataList) throws DataAccessException;

	/**
	 * 删除对象.
	 *
	 * @param vo the vo
	 * @param id the id
	 * @throws DataAccessException the data access exception
	 */
	public void deleteVO(BaseEntity vo, String id) throws DataAccessException;

	/**
	 * 删除对象的方法：适用于先根据主键查询对象，后来执行删除对象.
	 *
	 * @param vo the vo
	 * @throws DataAccessException the data access exception
	 */
	public void deleteVO(BaseEntity vo) throws DataAccessException;

	public void deleteVO(BaseAuditEntity vo) throws DataAccessException;

	/**
	 * 批量删除集合对象.
	 *
	 * @param dataList the data list
	 * @throws DataAccessException the data access exception
	 */
	public void deleteBatchVO(List dataList) throws DataAccessException;

	/**
	 * 通过字符串型主键来加载子类对象.
	 *
	 * @param vo the vo
	 * @param id the id
	 * @return the vO
	 * @throws DataAccessException the data access exception
	 */
	public BaseEntity getVO(BaseEntity vo, String id)
			throws DataAccessException;

	/**
	 * Gets the vO.
	 *
	 * @param vo the vo
	 * @return the vO
	 * @throws DataAccessException the data access exception
	 */

	public BaseEntity getVO(BaseEntity vo) throws DataAccessException;

	/**
	 * 通用的通过hql来查询列表.
	 *
	 * @param hql the hql
	 * @return List
	 * @throws DataAccessException the data access exception
	 */
	public List getValueObjectsByHQL(String hql) throws DataAccessException;

	/**
	 * 通过数组来构造查询条件，解决复杂查询.
	 *
	 * @param hql the hql
	 * @param object the object
	 * @return list
	 * @throws DataAccessException the data access exception
	 */
	public List getValueObjectsByHQL(String hql, Object... args)
			throws DataAccessException;

	/**
	 * Gets the data total num.
	 *
	 * @param hql the hql
	 * @return the data total num
	 * @throws DataAccessException the data access exception
	 */
	public int getDataTotalNum(String hql) throws DataAccessException;

	/**
	 * Gets the data total num.
	 *
	 * @param hql the hql
	 * @param arg1 the arg1
	 * @return the data total num
	 * @throws DataAccessException the data access exception
	 */
	public int getDataTotalNum(String hql, String arg1)
			throws DataAccessException;

	/**
	 * Gets the data total num.
	 *
	 * @param hql the hql
	 * @param obj the obj
	 * @return the data total num
	 * @throws DataAccessException the data access exception
	 */
	public int getDataTotalNum(String hql, Object obj)
			throws DataAccessException;

	/**
	 * 得到SQL查询的记录总数
	 * @param sql
	 * @return
	 * @throws DataAccessException
	 */
	public int getTotalNumBySQL(String sql, Object... args) throws DataAccessException;

	/**
	 * 根据HQL得到分页数.
	 *
	 * @param hql the hql
	 * @param object the object
	 * @return the data total num
	 * @throws DataAccessException the data access exception
	 */

	public int getDataTotalNum(String hql, Object... args)
			throws DataAccessException;

	/**
	 * 通过子类的hql查询子类对象集合.
	 *
	 * @param vo the vo
	 * @param whereClause the where clause
	 * @return the data total num
	 * @throws DataAccessException the data access exception
	 */
	public int getDataTotalNum(BaseEntity vo, String whereClause)
			throws DataAccessException;

	/**
	 * 分页查询.
	 *
	 * @param hql the hql
	 * @param object the object
	 * @param page_size the page_size
	 * @param pageNo the page no
	 * @return the page data by hql
	 * @throws DataAccessException the data access exception
	 */

	public List getPageDataByHQL(String hql, int page_size, int pageNo,
			Object... args) throws DataAccessException;

	/**
	 * 分页查询.
	 *
	 * @param sql the sql
	 * @param object the object
	 * @param page_size the page_size
	 * @param pageNo the page no
	 * @return the page data by sql
	 * @throws DataAccessException the data access exception
	 */

	public List getPageDataBySQL(String sql, int page_size, int pageNo,
			Object... args) throws DataAccessException;

	/**
	 * Gets the page data by hql.
	 *
	 * @param hql the hql
	 * @param obj the obj
	 * @return List
	 * @throws DataAccessException the data access exception
	 */

	public List getPageDataByHQL(String hql, Map<String, Object> filter)
			throws DataAccessException;

	/**
	 * 根据HQL得出分页列表.
	 *
	 * @param hql the hql
	 * @param page_size the page_size
	 * @param pageNo the page no
	 * @return the page data by hql
	 * @throws DataAccessException the data access exception
	 */
	public List getPageDataByHQL(String hql, int page_size, int pageNo)
			throws DataAccessException;

	/**
	 * 根据SQL得出分页列表.
	 *
	 * @param sql the sql
	 * @param page_size the page_size
	 * @param pageNo the page no
	 * @return the page data by sql
	 * @throws DataAccessException the data access exception
	 */
	public List getPageDataBySQL(String sql, int page_size, int pageNo)
			throws DataAccessException;

	/**
	 * Gets the page data by hql.
	 *
	 * @param hql the hql
	 * @param arg1 the arg1
	 * @param page_size the page_size
	 * @param pageNo the page no
	 * @return the page data by hql
	 * @throws DataAccessException the data access exception
	 */
	public List getPageDataByHQL(String hql, String arg1, int page_size,
			int pageNo) throws DataAccessException;

	/**
	 * Gets the page data by hql.
	 *
	 * @param hql the hql
	 * @param obj the obj
	 * @param page_size the page_size
	 * @param pageNo the page no
	 * @return the page data by hql
	 * @throws DataAccessException the data access exception
	 */
	public List getPageDataByHQL(String hql, Object obj, int page_size,
			int pageNo) throws DataAccessException;

	/**
	 * 分页查询。.
	 *
	 * @param hql the hql
	 * @param obj the obj
	 * @param page_size the page_size
	 * @param pageNo the page no
	 * @return the page data by hql
	 * @throws DataAccessException the data access exception
	 */
	public List getPageDataByHQL(String hql, Map<String, Object> filter,
			int page_size, int pageNo) throws DataAccessException;

	/**
	 * 分页查询.
	 *
	 * @param hql the hql
	 * @param page_size the page_size
	 * @param pageNo the page no
	 * @param total_num the total_num
	 * @return the page data by hql
	 * @throws DataAccessException the data access exception
	 */
	public List getPageDataByHQL(String hql, int page_size, int pageNo,
			int total_num) throws DataAccessException;

	/**
	 * 按SQL执行分页查询
	 * @param <T>
	 * @param entity
	 * @param sql
	 * @param pageSize
	 * @param pageNo
	 * @param args
	 * @return
	 * @throws DataAccessException
	 * @author Xiaojf
	 * @since 2011-6-14
	 */
	public <T> List<T> getPageDateBySQL(Class<T> entity, String sql,
			int pageSize, int pageNo, Object... args)
			throws DataAccessException;

	/**
	 * 通过SQL来查询记录.
	 *
	 * @param sql the sql
	 * @return the value object by sql
	 * @throws DataAccessException the data access exception
	 */
	public List getValueObjectBySQL(String sql) throws DataAccessException;

	/**
	 * 利用SQL数组条件来查询记录.
	 *
	 * @param sql the sql
	 * @param object the object
	 * @return the value object by sql
	 * @throws DataAccessException the data access exception
	 */
	public List getValueObjectBySQL(String sql, Object... args)
			throws DataAccessException;

	/**
	 * 通过存储过程来查询.
	 *
	 * @param queryName the query name
	 * @return the value object by name query
	 * @throws DataAccessException the data access exception
	 */
	public List getValueObjectByNameQuery(String queryName)
			throws DataAccessException;

	/**
	 * 通过存储过程来查询带单个参数.
	 *
	 * @param queryName the query name
	 * @param object the object
	 * @return the value object by name query
	 * @throws DataAccessException the data access exception
	 */
	public List getValueObjectByNameQuery(String queryName, Object object)
			throws DataAccessException;

	/**
	 * 通过存储过程来查询带多个参数的情况.
	 *
	 * @param queryName the query name
	 * @param object the object
	 * @return the value object by name query
	 * @throws DataAccessException the data access exception
	 */
	public List getValueObjectByNameQuery(String queryName, Object... args)
			throws DataAccessException;

	/**
	 * 动态构造HQL参数.
	 *
	 * @param detachedCriteria the detached criteria
	 * @return the value object by detached criteria
	 * @throws DataAccessException the data access exception
	 */
	public List getValueObjectByDetachedCriteria(
			DetachedCriteria detachedCriteria) throws DataAccessException;

	/**
	 * 动态构造HQL参数.
	 *
	 * @param detachedCriteria the detached criteria
	 * @param arg1 the arg1
	 * @param arg2 the arg2
	 * @return the value object by detached criterias
	 * @throws DataAccessException the data access exception
	 */
	public List getValueObjectByDetachedCriterias(
			DetachedCriteria detachedCriteria, int arg1, int arg2)
			throws DataAccessException;

	/**
	 * 模拟前端新闻及文档的分页.
	 *
	 * @param page the page
	 * @return the front page
	 * @throws DataAccessException the data access exception
	 */
	public List getFrontPage(final Page page) throws DataAccessException;

	/**
	 * 在同一个Session中执行指定的代码
	 * @param callback
	 * @author xiaojf
	 * @return
	 */
	public <T> T doInHibernate(HibernateCallback<T> callback);

	/**
	 * 按ID获取对象
	 * @param <T>
	 * @param type
	 * @param id
	 * @return
	 * @throws DataAccessException
	 * @author Xiaojf
	 * @since 2011-6-10
	 */
	public <T extends BaseEntity> T getBean(Class<T> type, Serializable id)
			throws DataAccessException;

	/**
	 * 按对象的某个属性查询
	 * @param <T>
	 * @param type
	 * @param field
	 * @param value
	 * @return
	 * @throws DataAccessException
	 * @author Xiaojf
	 * @since 2011-6-10
	 */
	public <T> List<T> loadByField(Class<T> type, String field, Object value)
			throws DataAccessException;
	/**
	 * 执行SQL查询，结果集中的每条记录都封装成一个Map
	 * @param sql
	 * @param args
	 * @return
	 * @throws DataAccessException
	 * @author Xiaojf
	 * @since 2011-9-2
	 */
	public List<Map<String, Object>> queryForListBySQL(String sql, Object... args) throws DataAccessException;
	/**
	 * 执行分页SQL查询，结果集中的每条记录都封装成一个Map
	 * @param sql
	 * @param page
	 * @param rows
	 * @param args
	 * @return
	 * @throws DataAccessException
	 * @author xjf721
	 * @since 2011-11-28
	 */
	public List<Map<String, Object>> queryForPagedListBySQL(String sql, int page, int rows, Object... args) throws DataAccessException;
	/**
	 * 执行HQL查询，结果集中的每条记录都封装成一个Map
	 * @param hql
	 * @param args
	 * @return
	 * @throws DataAccessException
	 * @author xjf721
	 * @since 2011-11-4
	 */
	public List<Map<String, Object>>  queryForListByHQL(String hql, Object... args) throws DataAccessException;
	/**
	 * 执行分页HQL查询，结果集中的每条记录都封装成一个Map
	 * @param sql
	 * @param page
	 * @param rows
	 * @param args
	 * @return
	 * @throws DataAccessException
	 * @author xjf721
	 * @since 2011-11-28
	 */
	public List<Map<String, Object>> queryForPagedListByHQL(String hql, int page, int rows, Object... args) throws DataAccessException;
	
	/**
	 * 执行一条SQL语句
	 * @param sql
	 * @param args
	 * @return
	 * @throws DataAccessException
	 * @author xjf721
	 * @since 2011-11-28
	 */
	public Integer executeSQL(final String sql, final Object... args) throws DataAccessException;
	/**
	 * 把结果集作为一个 map返回
	 * @param sql
	 * @param args
	 * @return
	 * @throws DataAccessException
	 * @author xjf721
	 * @since 2012-1-9
	 */
	public Map<String, Object> queryAsMapBySQL(final String sql, final Object... args) throws DataAccessException;
	
}
