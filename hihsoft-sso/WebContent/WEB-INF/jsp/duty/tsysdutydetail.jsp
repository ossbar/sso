 <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
$(function(){
	form = $("form[name=detailbox]");
	dutyid = form.find("input[name='dutyid']");
	dutyname = form.find("input[name='dutyname']");
	initGrid(form, {
		onpagefirst : detail, 
		onpagelast : detail, 
		onpageprev : detail, 
		onpagenext : detail, 
		onpagerefresh : detail,
		onpagesize : detail
	});
	$("#btnAdd").click(function() {
		detailWin.window("setTitle", dutyname.val()+"-用户添加");
		detailWin.window('open');
		detailUserList({dutyid:dutyid.val()});
		//$("#detailMsg").load("${ctx}/tsysDutyController.do?method=showUserInfo4Duty&dutyid="+dutyid.val(), detailHandler);
	});
	$("#btnRemove").click(function() {
		var ids = getSelected(false,form);
		if (!ids || ids.length == 0) return;
		if (!confirm("确定删除选中的记录吗？"))
			return;
		/* form.attr("action", baseUrl + "delete&ids=" + getSelected(false,form));
		submit(form, function(res, status){
			if (isSuccess(res, status)) {
				alert("删除成功！");
				detail({orgid : org.val()});
			}
		}); */
	});
	var detailHandler = function() {
		/* $(this).find("#btn_save").click(function() {
			if(!$("#formID").validationEngine('validate'))
				return;		
			var frm = $("form[name='tsysDutyForm']");
			frm.attr("action", "${ctx}/tsysDutyController.do?method=save");
			frm.submit();
		});
		$(this).find("#btn_cancel").click(function() {
			win.window("close");
		}); */
		//$("#userselect_container",this).layout();
	};
});
</script>
</head>
<body>
<form method="post" target="_self" action="${ctx}/tsysDutyController.do?method=showDutyInfo&dutyid=${tsysDuty.dutyid}" name="detailbox"  style="width: 100%;height: 100%;">
	<div id="detail-container" fit="true" align="left" border="false">
		<div region="north" title="岗位信息"  style="height:150px;" align="left">
			<input type="hidden" name='dutyid' value='${tsysDuty.dutyid}' />
			<input type="hidden" name='dutyname' value='${tsysDuty.dutyname}' />
			<table class="FormView" border="0" cellspacing="1" cellpadding="0" style="width: 300px;"
						id="mainregion">
						<col class="Label" />
						<col class="Data" />
						<tr>
							<td><fmt:message key="tsysduty.dutyname"></fmt:message></td>
							<td>${tsysDuty.dutyname}
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tsysduty.remark"></fmt:message></td>
							<td>${tsysDuty.remark}
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tsysduty.dutytype"></fmt:message></td>
							<td>${tsysDuty.dutyType }
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tsysduty.dutysort"></fmt:message></td>
							<td>${tsysDuty.dutySort }
							</td>
						</tr>
					</table>
		</div>
		<div id="grid-body" class="grid-body" region="center" border="false" align="left" title="岗位人员信息">
		<%-- 	<div class="datagrid-toolbar" region="north" style="height: auto;"
					border="false" align="left">
					<div class="tool-btn" id="btnRemove">
						<span class="icon-remove">&nbsp;</span>
						<fmt:message key="button.cancel.assign" />
					</div>
				</div> --%>
			<table class="data-grid" cellspacing="0" cellpadding="0">
				<thead>
					<tr class="data-grid-header">
						<th width="35"><input type="checkbox" id="selectAll" /></th>
						<th width="120"><fmt:message key="tacluserinfo.loginname" /></th>
						<th width="120"><fmt:message key="tacluserinfo.username" /></th>
						<th width="60"><fmt:message key="tacluserinfo.usertype" /></th>
<%-- 						<th width="90"><fmt:message key="tacluserinfo.dutyid" /></th>
 --%>						<th width="80"><fmt:message key="tacluserinfo.mobile" /></th>
						<th width="100"><fmt:message key="tacluserinfo.idcard" /></th>
						<th width="60"><fmt:message key="tacluserinfo.sex" /></th>
						<th width="60"><fmt:message key="tacluserinfo.userstate" /></th>
						<th width="80"><fmt:message key="tacluserinfo.birthday" /></th>
						<th width=""><fmt:message key="tacluserinfo.userEmail" /></th>
						</tr>
				</thead>
				<c:forEach items="${list}" var="tacluserinfo" varStatus="paStatus">
					<tr style="text-align: center;">
						<td class="checkbox"><input type="checkbox"
							name="ckh" value="${tacluserinfo.userid}" /></td>
						<td width="120">${tacluserinfo.loginname}</td>
						<td>${tacluserinfo.username}</td>
						<td>${tacluserinfo.usertype}</td>
<%-- 						<td>${tacluserinfo.tsysDuty.dutyname}</td>
 --%>						<td>${tacluserinfo.mobile}</td>
						<td>${tacluserinfo.idcard}</td>
						<td>${tacluserinfo.sex}</td>
						<td>${tacluserinfo.userstate}</td>
						<td>${tacluserinfo.birthday}</td>
						<td>${tacluserinfo.userEmail}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<jsp:include page="/WEB-INF/jsp/common/page.jsp" />
	</div>
</form>
</body>
</html>
