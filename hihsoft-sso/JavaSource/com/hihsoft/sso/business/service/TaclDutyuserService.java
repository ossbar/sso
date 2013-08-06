/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.business.service;

import com.hihframework.exception.ServiceException;
import com.hihsoft.baseclass.service.BaseService;
import com.hihsoft.sso.business.model.TaclDutyuser;
/**
 * Title:岗位分配用户的服务
 * Description:
 * Copyright: Copyright (c) 2013
 * Company:hihsoft.co.,ltd
 * @author hihsoft.co.,ltd
 * @version 1.0
 */

public interface  TaclDutyuserService extends BaseService{
	
	/**
	 * 新增、修改TaclDutyuser信息
	 * @param taclDutyuser
	 * @throws ServiceException
	 */
	public void saveOrUpdateTaclDutyuser(TaclDutyuser taclDutyuser)
			throws ServiceException; 
	
	/**
	 * 删除 TaclDutyuser 信息
	 * @param id
	 * @throws ServiceException
	 */
	public void deleteTaclDutyuser(String id)
			throws ServiceException; 
	
	/**
	 * 根据主键查询TaclDutyuser
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public TaclDutyuser getTaclDutyuserById(String id)
			throws ServiceException;
}
