/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.business.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hihframework.exception.ServiceException;
import com.hihsoft.baseclass.service.impl.BaseServiceImpl;
import com.hihsoft.sso.business.model.TaclDutyuser;
import com.hihsoft.sso.business.service.TaclDutyuserService;

@Service(value="taclDutyuserService")
public class TaclDutyuserServiceImpl extends BaseServiceImpl implements
		TaclDutyuserService {
	
	/************ 查询所有的TaclDutyuser ******************/
	public static final String ALLTACLDUTYUSER_HQL = " from TaclDutyuser";

	/************ 通过dutyid查询TaclDutyuser ******************/
	public static final String TACLDUTYUSERBYDUTYID_HQL = " from TaclDutyuser taclDutyuser where tsysDuty.dutyid=?";

	/************ 通过主键查询TACLROLEUSER ******************/
	public static final String TACLDUTYUSERById_HQL = " from TaclDutyuser taclDutyuser where taclDutyuser.dutyuserid=?";

	/* (non-Javadoc)
	 * @see com.hihsoft.sso.business.service.TaclDutyuserService#saveOrUpdateTaclDutyuser(com.hihsoft.sso.business.model.TaclDutyuser)
	 */
	@Override
	public void saveOrUpdateTaclDutyuser(TaclDutyuser taclDutyuser)
			throws ServiceException {
		baseDAO.saveOrUpdateVO(taclDutyuser);

	}

	/* (non-Javadoc)
	 * @see com.hihsoft.sso.business.service.TaclDutyuserService#deleteTaclDutyuser(java.lang.String)
	 */
	@Override
	public void deleteTaclDutyuser(String id) throws ServiceException {
		TaclDutyuser taclDutyuser = this.getTaclDutyuserById(id);
		baseDAO.deleteVO(taclDutyuser);

	}

	/*
	 * (non-Javadoc)
	 * @see com.hihsoft.sso.business.service.TaclDutyuserService#getTaclDutyuserById(java.lang.String)
	 */
	@Override
	public TaclDutyuser getTaclDutyuserById(String id) throws ServiceException {
		TaclDutyuser taclDutyuser = null;
		List<?> list = baseDAO.getValueObjectsByHQL(TACLDUTYUSERById_HQL,
				new Object[] { id });
		if (!list.isEmpty() && list.size() > 0) {
			taclDutyuser = (TaclDutyuser) list.get(0);
		}
		return taclDutyuser;
	}

}
