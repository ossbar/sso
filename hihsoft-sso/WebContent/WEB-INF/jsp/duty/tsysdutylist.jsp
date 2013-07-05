
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
<script type="text/javascript">
$(function() {
	var form = $("form[name=listForm]");
	initGrid(form);
	var win = $("#win").window({
		title : "岗位管理",
		modal : true
	});
	var handler = function() {
		$(this).find("#btn_save").click(function() {
			if(!$("#formID").validationEngine('validate'))
				return;		
			var frm = $("form[name='tsysDutyForm']");
			frm.attr("action", "${ctx}/tsysDutyController.do?method=save");
			frm.submit();
		});
		$(this).find("#btn_cancel").click(function() {
			win.window("close");
		});
	};
	$("#btn_add").click(function() {
		win.window('open');
		$("#msg").load("${ctx}/tsysDutyController.do?method=add", handler);
	});
	$("#btn_edit").click(function() {
		var ids = getSelected(true);
		if (ids) {
			win.window('open');
			$("#msg").load("${ctx}/tsysDutyController.do?method=modify", {
				id : ids
			}, handler);
		}
	});
	$("#btn_remove").click(function() {
		if (!confirm("确定删除选中的记录吗？"))
			return;
		form.attr("action", "${ctx}/tsysDutyController.do?method=delete&ids=" + getSelected());
		submit(form,function(res,status){
			if(isSuccess(res, status)){
				alert("删除成功", "info", function(){
					form.attr("action", "${ctx}/tsysDutyController.do?method=list");
					form.submit();
				});
			}else{
				alert("您所选岗位当中存在已分配用户，无法删除。","info",function(){
					win.window("close");
				});
			}
		});
	});
	$("#btn_search").click(function() {
		form.submit();
	});
	$("#container").layout();
	$(window).resize(function() {
		$("#container").layout();
	});
});
</script>
</head>
<body>
	<form method="post" target="_self"
		action="${ctx}/tsysDutyController.do?method=list" name="listForm" style="height: 100%;width: 100%;">
		<input type="hidden" name="error" value='' />
		<div style="margin: auto; position: relative;" fit="true" id="container">
			<div class="datagrid-toolbar" region="north" style="height: 69px;" border="false">
				<hih:auth operate="ADD" module="SYS_DUTY" value="button.add"
					id="btn_add" iconCls="icon-add" />
				<hih:auth operate="EDIT" module="SYS_DUTY" value="button.edit"
					id="btn_edit" iconCls="icon-edit" />
				<hih:auth operate="DELETE" module="SYS_DUTY" value="button.remove"
					id="btn_remove" iconCls="icon-remove" />
				<hih:auth operate="QUERY" module="SYS_DUTY" value="button.query"
					id="btn_search" iconCls="icon-search" />
				<table class="FormView" border="0" cellspacing="1" cellpadding="0" style="margin-top: 5px;">
					<col class="Label" />
					<col class="Data" />
					<tr height="30px">
						<td align="left">
						<fmt:message key="tsysduty.dutyname" />
						<input type="text" class="text" name="srh_dutyname"
							value="${dutyname==null?"":dutyname}"></td>
					</tr>
				</table>
			</div>
			<div id="grid-body" region="center">
			<hih:table items="${list}" var="item">
				<hih:column field="dutyid" checkbox="true"/>
				<hih:column field="dutyname" header="tsysduty.dutyname" width="120"/>
				<hih:column field="remark" header="tsysduty.remark" width="120"/>
				<hih:column field="dutyType" header="tsysduty.dutytype" width="120"/>
				<hih:column field="dutySort" header="tsysduty.dutysort" width="120"/>
			</hih:table>
		</div>
		<jsp:include page="/WEB-INF/jsp/common/page.jsp" />
		</div>
	</form>
	<div id="win" closed="true" style="width: 450px; height: 250px;">
		<div id="msg"></div>
	</div>
</body>
</html>