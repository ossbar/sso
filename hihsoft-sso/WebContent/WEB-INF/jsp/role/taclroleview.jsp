<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<html>
<head>
<script type="text/javascript">
	function back_click() {
		document.location = "${ctx}/taclRoleController.do?method=list";
	}

	function save_click() {
		var frm = $("form[name=viewForm]");
	 	frm.attr("action","${ctx}/taclRoleController.do?method=cancel&ids=" + getSelected(false, $("form[name=viewForm]")));
		//frm.submit();
		submit(frm,function(res,status){
			win.window("close");
			if(isSuccess(res, status)){
				alert("已撤销当前角色下分配的用户", "info", function(){
					frm.attr("action", "${ctx}/taclRoleController.do?method=list");
					frm.submit();
				});
			}
		}); 
	}

	$(function() {
		var form = $("form[name=viewForm]");
		initGrid(form, {
			onpagefirst : detail,
			onpagelast : detail,
			onpageprev : detail,
			onpagenext : detail,
			onpagerefresh : detail,
			onpagesize : detail
		});
	});
</script>
</head>
<body>
	<hih:form bean="request">
		<form method="post" target="_self"
			action="${ctx}/taclRoleController.do?method=userRole" name="viewForm">
			<div id="detail-container" region="center" style="border: none;height: 100%;">
			<input type="hidden" name="roleId" value='${roleId}' id="roleId" />
				 <div align="center">
				<div class="tool-btn" id="btn_save" onclick="save_click()">
					<span class="icon-save">&nbsp;</span>
					<fmt:message key="button.save" />
				</div>
				<div class="tool-btn" id="btn_save" onclick="back_click()">
					<span class="icon-back">&nbsp;</span>
					<fmt:message key="button.back" />
				</div>
				</div>
				<hr>
			
			 <div id="grid-body" class="grid-body"> 
			<table class="data-grid" cellspacing="0" cellpadding="0"> 
					<thead>
						<tr class="data-grid-header">
							<th><input type="checkbox" id="selectAll" />
							</th>
						<th width="120"><fmt:message key="tacluserinfo.loginname"/></th>
						<th width="120"><fmt:message key="tacluserinfo.username"/></th>
						<th width="120"><fmt:message key="tacluserinfo.orgid"/></th>
						<%-- <th width="120"><fmt:message key="tacluserinfo.deptid"/></th> --%>
						<th width="120"><fmt:message key="tacluserinfo.dutyid"/></th>
						</tr>
					</thead>
					<c:forEach items="${userList}" var="userlist" varStatus="paStatus">
						<tr>
							<td width="100" class="checkbox"><input type="checkbox"
								name="ckh" value="${userlist.userid}" />
							</td>
							<td>${userlist.loginname}</td>
							<td>${userlist.username}</td>
							<td>${userlist.tsysOrg.orgname}</td>
							<%-- <td>${userlist.tsysDept.deptname}</td> --%>
							<td>${userlist.tsysDuty.dutyname}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			
			</div>
		</form>
	</hih:form>
</body>
</html>