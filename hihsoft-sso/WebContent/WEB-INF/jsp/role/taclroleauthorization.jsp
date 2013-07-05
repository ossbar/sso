<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<html>
<head>
<script type="text/javascript">
	function back_click() {
		document.location = "${ctx}/taclRoleController.do?method=list";
	}

	function save_click() {
		var frm = $("form[name=userForm]");
		frm.attr("action",
				"${ctx}/taclRoleController.do?method=authorization&ids="
						+ getSelected(false, $("form[name=userForm]")));
	//	frm.submit();
		submit(frm,function(res,status){
			win.window("close");
			if(isSuccess(res, status)){
				alert("分配用户成功", "info", function(){
					frm.attr("action", "${ctx}/taclRoleController.do?method=list");
					frm.submit();
				});
			}
		});
	}

	$(function() {
		var comboUrl = "${ctx}/taclRoleController.do?method=getAssignedOrgTree";
		var combo = $("#orgid");
		combo.combotree({
			url : comboUrl,
			multiple:true,
			cascadeCheck:false,
			onLoadSuccess:function(){
				$("#orgid").combotree("setValues",[${orgid}]);
			}
		});
		
		$("#dutyid").combotree({
			url : "${ctx}/taclRoleController.do?method=getDutyTree",
			width : 165
		});
		
		var form = $("form[name=userForm]");
		initGrid(form, {
			onpagefirst : detail,
			onpagelast : detail,
			onpageprev : detail,
			onpagenext : detail,
			onpagerefresh : detail,
			onpagesize : detail
		});
		var fn = function(){
			$("input[name='srh_loginname']").focus();
		};
		fn.defer(500);
		$("#btn_search1").click(function() {
			var url = "${ctx}/taclRoleController.do?method=userRole&";
			url += form.serialize();
			$("#msg").load(url);
		});
	});
</script>
</head>
<body>
	<hih:form bean="request">
		<form method="post" target="_self"
			action="${ctx}/taclRoleController.do?method=userRole" name="userForm">
			<input type="hidden" name="roleId" value='${roleId}' id="roleId" />
			<div id="grid-body" class="grid-body">
				<table class="FormView" border="0" cellspacing="1" cellpadding="0">
					<col class="Label" />
					<col class="Data" />
					<col class="Label" />
					<col class="Data" />
					<tr>
						<td align="right"><fmt:message key="tacluserinfo.loginname" />
						</td>
						<td><input type="text" class="text" name="srh_loginname"
							style="width: 110px"></td>
						<td align="right"><fmt:message key="tacluserinfo.dutyid" /></td>
						<td>
						<%-- <hih:parameter name="userType" id="usertype" cssClass="select"/> --%>
							<%-- <select name="dutyid" class="select" style="width: 165px">
								<option value="">----请选择----</option>
								<c:forEach items="${dutys}" var="duty">
										<option value="${duty.dutyid}">${duty.dutyname}</option>
								</c:forEach>
							</select> --%>
							<input name="dutyid" id="dutyid" class="text" style="width: 162px;"/>
						</td>
					</tr>
					<tr>
						<td align="right"><fmt:message key="tacluserinfo.mobile" />
						</td>
						<td><input type="text" class="text" name="srh_mobile"
							style="width: 110px"></td>
						<td align="right"><fmt:message key="taclrole.orgid" /></td>
						<td>
							<input type="text" id="orgid" name="orgid" style="width :165px;"/>
						</td>
						
					<div align="center">	
				<div class="tool-btn" id="btn_save" onclick="save_click()">
					<span class="icon-save">&nbsp;</span>
					<fmt:message key="button.save" />
				</div>
				<div class="tool-btn" id="btn_save" onclick="back_click()">
					<span class="icon-back">&nbsp;</span>
					<fmt:message key="button.back" />
				</div>
				
				<div class="tool-btn" id="btn_search1">
							<span class="icon-search">&nbsp;</span>
							<fmt:message key="button.query" />
					</div>
				</div>
			
						
						
					</tr>
				</table>
				<table class="data-grid" cellspacing="0" cellpadding="0">
					<thead>
						<tr class="data-grid-header">
							<th><input type="checkbox" id="selectAll" />
							</th>
							<th width="120"><fmt:message key="tacluserinfo.username" />
							</th>
							<th width="120"><fmt:message key="tacluserinfo.loginname" />
							</th>
							<th width="120"><fmt:message key="tacluserinfo.dutyid" />
							</th>
							<th width="120"><fmt:message key="tacluserinfo.orgid" />
							</th>
							<th width="120"><fmt:message key="tacluserinfo.usertype" />
							</th>
							<th width="120"><fmt:message key="tacluserinfo.mobile" />
							</th>
						</tr>
					</thead>
					<c:forEach items="${list}" var="tacluserinfo" varStatus="paStatus">
						<tr>
							<td width="100" class="checkbox"><input type="checkbox"
								name="ckh" value="${tacluserinfo.userid}" />
							</td>
							<td width="120">${tacluserinfo.username}</td>
							<td width="120">${tacluserinfo.loginname}</td>
							<td width="120">${tacluserinfo.tsysDuty.dutyname}</td>
							<td width="120">${tacluserinfo.tsysOrg.orgname}</td>
							<td width="120">${tacluserinfo.usertype}</td>
							<td width="120">${tacluserinfo.mobile}</td>
						</tr>
					</c:forEach>
				</table>
			<%-- <jsp:include page="/common/page.jsp" /> --%>
			</div>
		</form>
	</hih:form>
</body>
</html>