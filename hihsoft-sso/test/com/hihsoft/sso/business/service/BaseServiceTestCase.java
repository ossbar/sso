package com.hihsoft.sso.business.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hihsoft.baseclass.service.BaseService;
import com.hihsoft.sso.util.BaseTestCase;
public class BaseServiceTestCase extends BaseTestCase {
	@Autowired
	BaseService svc;
	@Test
	public void testQeuryForList() {
		long time = System.currentTimeMillis();
//		String sql = "select t.modulename,t.moduleno,o.operatename from T_SYS_MODULEINFO t, t_sys_moduleoperate o where t.moduleid = o.moduleid";
//		List<Map<String,Object>> list = svc.queryForPagedListBySQL(sql, 2, 20);
//		final String pwd = Eryptogram.getUserPasswd("admin");
//		svc.executeHQL("update TaclUserinfo u set u.userpw=? where u.userid='4028828530f7dfd30130f7dff01a014c'", pwd);
//		String privilege = svc.getPrivilege("4028828530f7dfd30130f7dff01a014c");
//		System.out.println(privilege);
//		String directOrgId = svc.getDirectOrgId("4028818a33f3334e0133f38226f70270", "3");
//		String orgid = svc.getSubOrgIds("4028828530f8df160130f8e4b7be0000");
//		System.out.println(orgid);
//		String[] names = { "分流部长", "储运部长", "仓管", "装卸工", "车管", "零担部长", "市场部长",
//				"软件开发", "广告设计", "软件测试", "需求分析", "杂志主编", "公司行政人事", "股份总务主管",
//				"股份业务主管", "站点会计", "业务部长", "信息部长", "副省经理", "组长", "后勤", "数据库维护",
//				"站点出纳","软件开发","广告设计","软件测试","需求分析","杂志主编" };
//		
//		svc.executeSQL("delete from t_acl_roleprivilege where roleid in(select roleid from t_acl_role where rolename in ("+StringHelpers.join(names)+"))");
//		String roleId = "4028828c355ba19701355ba6d2220001";
//		List<TaclRoleprivilege> pri = svc.getValueObjectsByHQL("from TaclRoleprivilege where roleid=?", roleId);
//		List<TaclRole> roles = svc.getValueObjectsByHQL("from TaclRole where rolename in ("+StringHelpers.join(names)+")");
//		for (TaclRole role : roles) {
//			List<TaclRoleprivilege> roleSet = new ArrayList<TaclRoleprivilege>();
//			for (TaclRoleprivilege p : pri) {
//				TaclRoleprivilege copy = new TaclRoleprivilege();
//				copy.setRoleid(role.getRoleid());
//				copy.setModuleid(p.getModuleid());
//				copy.setOperateid(p.getOperateid());
//				roleSet.add(copy);
//			}
//			svc.saveOrUpdateBatchVO(roleSet);
//		}
//		int index = 0;
//		for (String name : names) {
//			TaclRole r = new TaclRole();
//			r.setRolename(name);
//			r.setRemark(name);
//			r.setOrgid("4028828530f7dfd30130f7dfef9d002b");
//			r.setRoletype(index >= 23 ? "1" : "2");
//			r.setRoleSort("1");
//			svc.saveOrUpdateVO(r);
//			List<TaclRoleprivilege> roleSet = new ArrayList<TaclRoleprivilege>();
//			for (TaclRoleprivilege p : pri) {
//				TaclRoleprivilege copy = new TaclRoleprivilege();
//				copy.setRoleid(r.getRoleid());
//				copy.setModuleid(p.getModuleid());
//				copy.setOperateid(p.getOperateid());
//				roleSet.add(copy);
//			}
//			svc.saveOrUpdateBatchVO(roleSet);
//			index++;
//		}

	}
	

}
