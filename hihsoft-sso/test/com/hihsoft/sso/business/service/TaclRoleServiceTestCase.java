package com.hihsoft.sso.business.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hihsoft.baseclass.service.BaseService;
import com.hihsoft.sso.business.model.TaclRole;
import com.hihsoft.sso.business.service.TaclRoleService;
import com.hihsoft.sso.util.BaseTestCase;

/**
 * <p> Title:单元测试 </p>
 * <p> Description:角色管理业务逻辑层单元测试案例</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:javahih.co.,ltd </p>
 *
 * @author zhujw
 * @version 1.0
 */
public class TaclRoleServiceTestCase extends BaseTestCase
{

	/** The tacl role service. */
	@Autowired
	public static TaclRoleService taclRoleService;
	@Autowired
	public static BaseService baseService;




	/**测试新增、修改角色信息#saveOrUpdateTaclRole方法
	 * Testsave or update tacl role.
	 */
	@Test
	public void testsaveOrUpdateTaclRole()
	{

		final TaclRole taclRole = new TaclRole();
		taclRole.setRemark("具有审核的相关权限888");
		taclRole.setRolename("审核员288882");
		
		taclRoleService.saveOrUpdateTaclRole(taclRole);
		log.info("新增成功返回成功信息" + taclRole.getRolename());

	}
	@Test
	public void testQueryRole()
	{
		log.info(taclRoleService.getDataTotalNum("from TaclRole"));

	}


}
