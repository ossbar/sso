
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	$(function() {
		var form = $("form[name=authListForm]");
		var handler = function(config) {
			var url = "${ctx}/taclRoleController.do?method=listRole";
			if (config.curPage) url += "&page=" + config.curPage;
			if (config.pageSize) url += "&rows=" + config.pageSize;
			if (config.rolename) url += "&rolename=" + encodeURI(encodeURI(config.rolename));
			$("#msg").load(url);
		};
		initGrid(form, {
			onpagefirst : handler, 
			onpagelast : handler, 
			onpageprev : handler, 
			onpagenext : handler, 
			onpagerefresh : handler,
			onpagesize : handler
		});
		$("#btn_query").click(function() {
			handler({rolename : $("input[name='rolename']").val()});
		});
		$(".table-container").layout();
		$("#btn_auth").click(function() {
			var userIds = form.find("input[name='userIds']").val();
			if (!userIds) {
				alert("请选择用户!");
				return;
			}
			var roleIds = getSelected(false, form);
			if (!roleIds) {
				alert("请选择角色!");
				return;
			}
			$.ajax({
				url : "${ctx}/taclUserinfoController.do?method=saveAuth",
				data : "userIds=" + userIds + "&roleIds=" + roleIds,
				type : "POST",
				success : function(json) {
					if (json) {
						var obj = decode(json);
						if (obj.success) {
							alert("角色定义成功！");
						} else {
							alert("角色定义失败！");
						}
					}
				},
				error : function(json) {
					alert(json);
				}
			});
		});
	});
</script>
</head>
<body >
<form method="post" target="_self" action="${ctx}/taclRoleController.do?method=listRole" name="authListForm">
	<input type="hidden" name="userIds" value="${userIds}"/>
		<div class="table-container" region="center" style="border: none;height: 400px;">
			<div class="datagrid-toolbar" region="north" style="height: 31px;" border="false">
				<label><fmt:message key="taclrole.rolename" />：</label>
				<input type="text" name="rolename" size="10"/>
				<div class="tool-btn" id="btn_query">
					<span class="icon-search">&nbsp;</span>查询
				</div>
			</div>
			<div id="grid-body" class="grid-body" region="center">
				<table class="data-grid" cellspacing="0" cellpadding="0">
					<thead>
						<tr class="data-grid-header">
							<th><input type="checkbox" id="selectAll" /></th>
							<th width="120"><fmt:message key="taclrole.rolename" /></th>
							<th width="120"><fmt:message key="taclrole.remark" /></th>
							<th width="120"><fmt:message key="taclrole.roletype" /></th>
						</tr>
					</thead>
					<c:forEach items="${list}" var="taclrole" varStatus="paStatus">
						<tr>
							<td width="100" class="checkbox"><input type="checkbox"
								name="ckh" value="${taclrole.roleid}" /></td>
							<td width="120">${taclrole.rolename}</td>
							<td width="120">${taclrole.remark}</td>
							<td width="120">${taclrole.roletype}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<jsp:include page="/WEB-INF/jsp/common/page.jsp" />
		</div>
		<div align="center" class="foot-buttons">
		<div class="tool-btn" id="btn_save" onclick="save_click()">
				<span class="icon-save">&nbsp;</span>
				<fmt:message key="button.save" />
			</div>
			<div class="tool-btn" id="btn_save" onclick="back_click()">
				<span class="icon-back">&nbsp;</span>
				<fmt:message key="button.back" />
			</div>
		</div>
</form>
</body>
</html>
