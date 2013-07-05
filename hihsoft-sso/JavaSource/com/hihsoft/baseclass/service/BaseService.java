package com.hihsoft.baseclass.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.hihframework.exception.ServiceException;
import com.hihsoft.baseclass.model.BaseEntity;
import com.hihsoft.sso.business.model.TaclUserinfo;

/**
 * <p> Title:框架业务逻辑层SERVICE接口实现类基类</p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 *
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public interface BaseService
{
	/**
	 * 新增、修改对象
	 *
	 * @param vo：VO
	 * @throws ServiceException
	 */
	public void saveOrUpdateVO(BaseEntity vo) throws ServiceException;

	public void executeHQL(String hql, Object... params) throws ServiceException;

	/**
	 * 批量新增、修改集合对象
	 *
	 * @param dataList：LIST对象
	 * @throws ServiceException
	 */
	public void saveOrUpdateBatchVO(List dataList) throws ServiceException;

	/**
	 * 删除对象
	 *
	 * @param vo：VO对象
	 * @throws ServiceException
	 */
	public void deleteVO(BaseEntity vo, String id) throws ServiceException;

	/**
	 * 批量删除集合对象
	 *
	 * @param dataList
	 * @throws ServiceException
	 */
	public void deleteBatchVO(List dataList) throws ServiceException;

	/**
	 * 通过字符串型主键来加载子类对象
	 *
	 * @param id:主键
	 * @param vo：VO对象
	 * @return
	 * @throws ServiceException
	 */
	public BaseEntity getVO(BaseEntity vo, String id) throws ServiceException;

	public BaseEntity getVO(BaseEntity vo) throws ServiceException;

	/**
	 * 通用的通过ＨＱＬ来查询列表
	 *
	 * @param hql
	 * @return List
	 * @throws ServiceException
	 */
	public List getValueObjectsByHQL(String hql) throws ServiceException;

	/**
	 * 通过数组来构造查询条件，解决复杂查询
	 *
	 * @param hql：构造ＨＱＬ语句
	 * @param object：数组
	 * @return list
	 * @throws ServiceException
	 */
	public List getValueObjectsByHQL(String hql, Object... filter) throws ServiceException;

	/**
	 * 取得页总数
	 *
	 * @param hql
	 * @return
	 * @throws ServiceException
	 */
	public int getDataTotalNum(String hql) throws ServiceException;

	/**
	 * @param hql
	 * @param arg1
	 * @return
	 * @throws ServiceException
	 */
	public int getDataTotalNum(String hql, String arg1) throws ServiceException;

	/**
	 * @param hql
	 * @param obj
	 * @return
	 * @throws ServiceException
	 */
	public int getDataTotalNum(String hql, Object obj) throws ServiceException;

	/**
	 * @param hql
	 * @param object
	 * @return
	 * @throws ServiceException
	 */

	public int getDataTotalNum(String hql, Object... filter) throws ServiceException;

	/**
	 * 通过子类的hql查询子类对象集合
	 *
	 * @param hql
	 * @return
	 * @throws ServiceException
	 */
	public int getDataTotalNum(BaseEntity vo, String whereClause) throws ServiceException;
	
	/**
	 * 得到SQL查询的记录总数
	 * @param sql
	 * @return
	 * @throws ServiceException
	 */
	public int getTotalNumBySQL(String sql, Object... args) throws ServiceException;

	public List getPageDataByHQL(String hql, int page_size, int pageNo, Object... object) throws ServiceException;

	/**
	 * @param hql：HQL
	 * @param obj：MAP条件
	 * @return
	 * @throws ServiceException
	 */

	public List getPageDataByHQL(String hql, Map<String, Object> filter) throws ServiceException;

	/**
	 * 根据HQL得出分页列表
	 *
	 * @param hql
	 * @param page_size
	 * @param pageNo
	 * @return
	 * @throws ServiceException
	 */
	public List getPageDataByHQL(String hql, int page_size, int pageNo) throws ServiceException;

	/**
	 * @param hql
	 * @param arg1
	 * @param page_size
	 * @param pageNo
	 * @return
	 * @throws ServiceException
	 */
	public List getPageDataByHQL(String hql, String arg1, int page_size, int pageNo) throws ServiceException;

	/**
	 * @param hql
	 * @param obj
	 * @param page_size
	 * @param pageNo
	 * @return
	 * @throws ServiceException
	 */
	public List getPageDataByHQL(String hql, Object obj, int page_size, int pageNo) throws ServiceException;

	/**
	 * @param hql
	 * @param obj
	 * @param page_size
	 * @param pageNo
	 * @return
	 * @throws ServiceException
	 */
	public List getPageDataByHQL(String hql, Map<String, Object> filter, int page_size, int pageNo) throws ServiceException;

	/**
	 * @param hql
	 * @param page_size
	 * @param pageNo
	 * @param total_num
	 * @return
	 * @throws ServiceException
	 */
	public List getPageDataByHQL(String hql, int page_size, int pageNo, int total_num) throws ServiceException;
	/**
	 * 按SQL分页
	 * @param sql
	 * @param pageSize
	 * @param pageNo
	 * @param args
	 * @return
	 * @throws ServiceException
	 */
	public <T> List<T> getPageDataBySQL(Class<T> entity, String sql, int pageSize, int pageNo, Object... args) throws ServiceException;

	/**
	 * 通过SQL来查询记录
	 *
	 * @param sql
	 * @return
	 * @throws ServiceException
	 */
	public List getValueObjectBySQL(String sql) throws ServiceException;

	/**
	 * 利用SQL数组条件来查询记录
	 *
	 * @param sql
	 * @param object
	 * @return
	 * @throws ServiceException
	 */
	public List getValueObjectBySQL(String sql, Object... filter) throws ServiceException;

	/**
	 * 通过存储过程来查询
	 *
	 * @param queryName:定义查询名称
	 * @return
	 * @throws ServiceException
	 */
	public List getValueObjectByNameQuery(String queryName) throws ServiceException;

	/**
	 * 通过存储过程来查询带单个参数
	 *
	 * @param queryName
	 * @param object
	 * @return
	 * @throws ServiceException
	 */
	public List getValueObjectByNameQuery(String queryName, Object object) throws ServiceException;

	/**
	 * 通过存储过程来查询带多个参数的情况
	 *
	 * @param queryName
	 * @param object
	 * @return
	 * @throws ServiceException
	 */
	public List getValueObjectByNameQuery(String queryName, Object... filter) throws ServiceException;

	/**
	 * Gets the now time stamp.
	 *
	 * @return the now time stamp
	 * @throws ServiceException the data access exception
	 */
	public String getNowDateTime() throws ServiceException;

	/**
	 * Gets the now date.
	 *
	 * @return the now date
	 * @throws ServiceException the data access exception
	 */
	public String getNowDate() throws ServiceException;
	/**
	 * 以传入格式获取当前时间(数据库)
	 * @param parttern
	 * @return
	 * @throws ServiceException
	 * @author xjf721
	 * @since 2012-3-9
	 */
	public String getNow(String parttern) throws ServiceException;

	/**
	 * 获取模块树用以前台显示
	 * @return
	 * @author Xiaojf
	 * @since 2011-5-30
	 */
	public List<Map<String, Object>> getModuleTree(String flatId, boolean listoper, String... roleId) throws ServiceException;
	/**
	 * 获取机构树用以前台显示
	 * @param roleId
	 * @param flatId
	 * @return
	 * @author Xiaojf
	 * @since 2011-5-22
	 */
	public List<Map<String, Object>> getOrgTree(String userId, String orgId, String orgClass) throws ServiceException;
	/**
	 * 得到该机构及其下所有子机构的ID
	 * @param orgId
	 * @return
	 * @throws ServiceException
	 * @author Xiaojf
	 * @since 2011-6-10
	 */
	public String getSubOrgIds(final String orgId) throws ServiceException;
	public String getSubOrgIds(final String orgId, final String orgClass) throws ServiceException;
	
	/**
	 * 得到该部门及其下所有子部门的ID
	 * @param deptId
	 * @return
	 * @throws ServiceException
	 * @author Xiaojf
	 * @since 2011-6-20
	 */
	public String getSubDeptIds(final String orgId) throws ServiceException;
	public String getSubDeptIds(final String orgId, final String orgClass) throws ServiceException;
	/**
	 * 执行SQL语句查询指定的对象集合
	 * @param <T>
	 * @param entity
	 * @param sql
	 * @param args
	 * @return
	 * @throws ServiceException
	 * @author Xiaojf
	 * @since 2011-6-11
	 */
	public <T> List<T> getBeanBySQL(String sql, Class<T> entity, Object... args) throws ServiceException;
	/**
	 * 按ID获取对象
	 * @param sql
	 * @param entity
	 * @param args
	 * @return
	 * @throws ServiceException
	 * @author Xiaojf
	 * @since 2011-7-4
	 */
	public <T extends BaseEntity> T getBean(Class<T> entity, Serializable id) throws ServiceException;
	/**
	 * 获取用户的已授权菜单
	 * @param user
	 * @return
	 * @throws ServiceException
	 * @author Xiaojf
	 * @since 2011-6-13
	 */
	public String getMenus(TaclUserinfo user, String flatid) throws ServiceException;
	/**
	 * 获取数据权限
	 * @param userId
	 * @return
	 * @throws ServiceException
	 * @author Xiaojf
	 * @since 2011-9-6
	 */
	public String getPrivilege(String userId) throws ServiceException;
	
	public String getPrivilege(String userId, String orgId) throws ServiceException;
	
	/**
	 * 执行SQL查询，结果集中的每条记录都封装成一个Map
	 * @param sql
	 * @param args
	 * @return
	 * @throws ServiceException
	 * @author Xiaojf
	 * @since 2011-9-2
	 */
	public List<Map<String, Object>> queryForListBySQL(String sql, Object... args) throws ServiceException;
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
	public List<Map<String, Object>> queryForPagedListBySQL(String sql, int page, int rows, Object... args) throws ServiceException;
	/**
	 * 执行HQL查询，结果集中的每条记录都封装成一个Map
	 * @param hql
	 * @param args
	 * @return
	 * @throws ServiceException
	 * @author xjf721
	 * @since 2011-11-4
	 */
	public List<Map<String, Object>>  queryForListByHQL(String hql, Object... args) throws ServiceException;
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
	public List<Map<String, Object>> queryForPagedListByHQL(String hql, int page, int rows, Object... args) throws ServiceException;

	/**
	 * 执行一条SQL语句
	 * @param sql
	 * @param args
	 * @return
	 * @throws DataAccessException
	 * @author xjf721
	 * @since 2011-11-28
	 */
	public Integer executeSQL(final String sql, final Object... args) throws ServiceException;
	/**
	 * 获取岗位树
	 * @param dutyId 如果该参数为空，则取所有岗位，否则取其子岗位和其本身
	 * @return
	 * @throws ServiceException
	 * @author xjf721
	 * @since 2011-11-29
	 */
	public List<Map<String, Object>> getDutyTree(String dutyId) throws ServiceException;
	/**
	 * 按照授权显示机构树
	 * @param userId
	 * @return
	 * @throws ServiceException
	 * @author xjf721
	 * @since 2012-1-2
	 */
	public List<Map<String, Object>> getAssignedOrgTree(String userId, String orgClass) throws ServiceException;
	/**
	 * 获取某个用户某一级别的直属机构
	 * @param userId
	 * @param orgClass
	 * @return
	 * @throws ServiceException
	 * @author xjf721
	 * @since 2012-1-2
	 */
	public String getDirectOrgId(String userId, String orgClass) throws ServiceException;
	
	public String getDirectOrgId(String userId) throws ServiceException;
	/**
	 * 把结果集作为一个 map返回
	 * @param sql
	 * @param args
	 * @return
	 * @throws DataAccessException
	 * @author xjf721
	 * @since 2012-1-9
	 */
	public Map<String, Object> queryAsMapBySQL(final String sql, final Object... args) throws ServiceException;
}
