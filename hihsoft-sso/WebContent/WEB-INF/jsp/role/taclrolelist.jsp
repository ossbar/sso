<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
<script type="text/javascript">
var ids;
var win;
	$(function() {
		var form = $("form[name=listForm]");
		initGrid(form);
		var handler = function() {
			win.window('open');
			var div = $(this);
			div.show();
		};
		win = $("#win").window({
			title : "操作窗口",
			modal : true
		});
		
		$("#btn_add").click(
				function() {
					win.window('open');
					$("#msg").load(
							"${ctx}/taclRoleController.do?method=add",
							"", handler);
				});
		
		$("#btn_edit").click(
					function() {
						var isGroup = $("#isGroup").val();
						var text = form.find(":checked").parent().parent().find("td:eq(5)").text();
						if (isGroup!="true" && text=="公共") {
							alert("非集团用户不能修改公共角色");
							return;
						}
						var ids = getSelected(true);
						if (ids){
							win.window('open');
							$("#msg").load("${ctx}/taclRoleController.do?method=modifyInit",
										{id : ids}, handler);
						}
					});
		
		$("#btn_remove")
				.click(
						function() {
							var isGroup = $("#isGroup").val();
							var text = form.find(":checked").parent().parent().find("td:eq(5)").text();
							var str = "公共";
							if(isGroup!="true"&&text.indexOf(str)!=-1){
								alert("非集团用户不能删除公共角色");
								return;
							}
							if (!confirm("确定删除选中的记录吗？"))
								return;
							form.attr("action", "${ctx}/taclRoleController.do?method=delete&ids="
									+ getSelected());
							submit(form,function(res,status){
								if(isSuccess(res, status)){
									alert("删除成功", "info", function(){
										form.attr("action", "${ctx}/taclRoleController.do?method=list");
										form.submit();
									});
								}else{
									alert("您所选的角色当中存在已分配用户","info",function(){
										form.attr("action", "${ctx}/taclRoleController.do?method=list");
										form.submit();
									});
								}
							});
						});
		$("#btn_authorization").click(function() {
					ids = getSelected(true);
					if (ids) {
						detail({roleId: ids});
					}
				});
		
		$("#btn_cancel").click(
				function(){
					var isGroup = $("#isGroup").val();
					var text = form.find(":checked").parent().parent().find("td:eq(5)").text();
					if (isGroup!="true" && text=="公共") {
						alert("非集团用户不能修改取消分配公共角色");
						return;
					}
					ids = getSelected(true);
					if(ids){
						win.window('open');
						$("#msg").load("${ctx}/taclRoleController.do?method=userView",
						{
							id : ids
						}, handler);
					}
				}	
			);
		
		$("#btn_search").click(function() {
			form.submit();
		});

		$(".table-container").layout();
		$(window).resize(function() {
			$("#container").layout();
		});
	});
				
	function detail(config) {
		var curPage = config.curPage;
		var pageSize = config.pageSize;
		win.window('open');
		var url = "${ctx}/taclRoleController.do?method=userRole";
		if (curPage) url += "&page="+curPage;
		if (pageSize) url += "&rows="+pageSize;
		var loginName = $("input[name='srh_loginname']").val();
		if (loginName) url += "&srh_loginname=" + loginName; 
		var mobile = $("input[name='srh_mobile']").val();
		if (mobile) url += "&srh_mobile=" + mobile; 
		var usertype = $("select[name='usertype']").val();
		if (usertype) url += "&usertype=" + usertype; 
		if (config.roleId) url += "&roleId=" + config.roleId;
		$("#msg").load(url);
	}		
	
	function showRole(id){
		if(!id) return;
		$("#msg").load("${ctx}/taclRoleController.do?method=roleView&id=" + id, function(){
		win.window("open");
		});
	}
	
</script>
</head>
<body>
<form method="post" target="_self" action="${ctx}/taclRoleController.do?method=list" name="listForm" style="width: 100%;height: 100%;">
	<div class="easyui-layout" style="border: none;" fit="true" id="container">
		<input type="hidden" id="isGroup" value="${isGroup}" />
		<div class="datagrid-toolbar" region="north" border="false" style="height: 70px;">
			<hih:auth operate="ADD" module="ACL_ROLE" value="button.add"
				id="btn_add" iconCls="icon-add" />
			<hih:auth operate="EDIT" module="ACL_ROLE" value="button.edit"
				id="btn_edit" iconCls="icon-edit" />
			<hih:auth operate="DELETE" module="ACL_ROLE" value="button.remove"
				id="btn_remove" iconCls="icon-remove" />
			<hih:auth operate="QUERY" module="ACL_ROLE" value="button.query"
				id="btn_search" iconCls="icon-search" />
			<hih:auth operate="AUTHORIZATION" module="ACL_ROLE" value="button.assign.user"
				id="btn_authorization" iconCls="icon-ok" style="width: 70px;"/>
			<hih:auth operate="CANCEL" module="ACL_ROLE" value="button.cancel.assign"
				id="btn_cancel" iconCls="icon-remove" style="width: 70px;"/>
			<table class="FormView" border="0" cellspacing="1" cellpadding="0" style="margin-top: 4px;">
				<tr height="30px">
					<td align="left" bgcolor="#E0EEEE">
						<fmt:message key="taclrole.rolename" />
						<input type="text" class="text" name="srh_rolename" value="${rolename}">
					</td>
				</tr>
			</table>
		</div>
		<div id="grid-body" class="grid-body" region="center">
			<table class="data-grid" cellspacing="0" cellpadding="0">
				<thead>
				<tr class="data-grid-header">
				<th><input type="checkbox" id="selectAll" /></th>
				<th width="120"><fmt:message key="taclrole.rolename" /></th>
				<th width="120"><fmt:message key="taclrole.remark" /></th>						
				<th width="120"><fmt:message key="taclrole.roletype" /></th>
				<th width="120"><fmt:message key="taclrole.orgid" /></th>
				<th width="120"><fmt:message key="taclrole.rolesort" /></th>
				</tr>
				</thead>
				<c:forEach items="${list}" var="taclrole"
					varStatus="paStatus">
					<tr>
					<td width="100" class="checkbox"><input type="checkbox"
							name="ckh" value="${taclrole.roleid}" /></td>
						<td width="120">
						<a href="#" onclick="showRole('${taclrole.roleid}')">${taclrole.rolename}</a></td>
						<td width="120">${taclrole.remark}</td>								
						<td width="120">${taclrole.roletype}</td>
						<td width="120">${taclrole.orgid}</td>
						<td width="120">${taclrole.roleSort}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<jsp:include page="/WEB-INF/jsp/common/page.jsp" />
	</div>
</form>
	<div id="win" closed="true" style="width: 650px; height: 500px;">
		<div id="msg"></div>
	</div>
</body>
</html>