 <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<% String noTree =request.getParameter("noTree");
     request.setAttribute("noTree",noTree);
 %>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.fieldSet {list-style: none;width: 100%;margin: 0;padding: 0;}
.fieldSet li {float: left;text-align: right;vertical-align: middle;margin-left: 5px;}
li.west {float: right;margin-right: 8px;}
#selectedUsers {height: 30px;margin-left: 4px;clear: both;}
</style>
</head>
<script type="text/javascript">
$(function() {
});
</script>
<body>

<div class="easyui-layout" border="false" fit="true">

<c:if test="${noTree==null}">
<div region="north" style="height: 50px;" border="false">
</c:if>
<c:if test="${noTree!=null}">
<div region="north" style="height: 70px;" border="false">
</c:if>
	
		<form name="queryForm">
		<ul class="fieldSet">
			<li>
				员工姓名：<input name="filter_username" type="text" class="text" style="width: 100px;"/>
			</li>
			<li>
				登陆帐号：<input name="filter_loginname" type="text" class="text" style="width: 100px;"/>
			</li>
			<li>范围：<hih:parameter name="userState" id="userstate" type="checkbox" defValue="正常,调岗"/>
			</li>
			<li class="west">
				<a class="easyui-linkbutton" iconCls="icon-search" id="btn-query">查询</a>
				<a class="easyui-linkbutton" iconCls="icon-reload" id="btn-reset">重置</a>
			</li>
		</ul>
		</form>
		<div id="selectedUsers" style="height: 25px;"></div>
	</div>
<c:if test="${noTree==null}">
<div region="west" split="true" style="width:200px;" border="false">
	<div class="easyui-accordion" fit="true" border="false">
		<div title="按机构" iconCls="icon-set">
			<ul id="orgTree" style="padding-top: 0px;padding-left: 0px;"></ul>
		</div>
		<div title="按岗位" iconCls="icon-role">
			<ul id="dutyTree" style="padding-top: 0px;padding-left: 0px;"></ul>
		</div>
	</div>
</div>
</c:if>
	<div region="center" title="用户列表">
		<table id="userGrid" border="false"></table>
	</div>
</div>
</body>
</html>