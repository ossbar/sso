<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
<head>
<script type="text/javascript">
var moduleTree;
		$(function(){
			$("#detail-container").tabs();
			var roleId = $("input[name='roleid']").val();
			moduleTree = $("#moduleTree").tree({
				checkbox : true,
				url : "${ctx}/taclRoleController.do?method=getModuleTree&view=1&roleId=" + (roleId || ""),
				onLoadSuccess : function(node, data) {
					if (data && data.length > 0) {
						var checked = moduleTree.tree("getChecked");
						$.each(checked, function(i, n){
							if (moduleTree.tree("isLeaf", n.target)) {
								moduleTree.tree("check", n.target);
							}
							n.target.disabled = true;
						});
						checked = moduleTree.tree("getChecked");
						$.each(checked, function(i, n){
							n.target.disabled = true;
						});
					}
				},
				onCheck : function(node, checked) {
					 if (node.target.disabled) {
						 return false;
					} 
				}
			});
		});
</script>
</head>
<body>
<div id="detail-container" region="center" style="border: none; height: 450px;">
	<div id="tab1" region="center" title="角色信息" border="false" style="overflow:auto;">
		<table class="FormView" border="0" cellspacing="1" cellpadding="0" > 
			<col class="Label" />
			<col class="Data" />
			<col class="Label" />
			<col class="Data" />
			<tr>
				<td><fmt:message key="taclrole.rolename"/></td>
				<td>${taclRole.rolename}</td> 
				<td><fmt:message key="taclrole.roletype"/></td>
				<td>${taclRole.roletype}</td> 
			</tr>
			<tr>
				<td><fmt:message key="taclrole.remark"/></td>
				<td>${taclRole.remark}</td> 
				<td><fmt:message key="taclrole.roleid"/></td>
				<td>****</td>
			</tr>
		</table>
	</div>
	<div id="tab2" region="south" style="height: 390px;" title="已分配的用户" border="false" >
		<table class="data-grid" width="100%">
			<tr class="data-grid-header">
				<th></th>
				<th><fmt:message key="tacluserinfo.loginname"/></th>
				<th><fmt:message key="tacluserinfo.username"/></th>
				<th><fmt:message key="tacluserinfo.orgid"/></th>
				<%-- <th><fmt:message key="tacluserinfo.deptid"/></th> --%>
				<th><fmt:message key="tacluserinfo.dutyid"/></th>
			</tr>
 			<c:forEach items="${userList}" var="userlist" varStatus="status">
				<tr class="data-grid-header">
					<td>${status.index+1}</td> 
					<td>${userlist.loginname}</td>
					<td>${userlist.username}</td>
					<td>${userlist.tsysOrg.orgname}</td>
					<%-- <td>${userlist.tsysDept.deptname}</td> --%>
					<td>${userlist.tsysDuty.dutyname}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div id="tab3" title="操作权限">
		<div region="center" style="width: 200px;" split="true" title="操作权限">
			<input type="hidden" name="roleid" value="${roleId}"/>
				<ul id="moduleTree"></ul>
		</div>
	</div>
</div>
</body>
</html>