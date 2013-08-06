<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
<script type="text/javascript">
var form;
var dutyid;
var detailWin;
var win;
$(function() {
	form = $("form[name=listForm]");
	initGrid(form);
	$("#tree").tree({
		url : "${ctx}/tsysDutyController.do?method=getDutyTree",
		onClick : function(n) {
			detail({dutyid:n.id});
		}
	});
	detailWin = $("#detailWin").window({
		title : "岗位用户分配",
		modal : true
	});
	win = $("#win").window({
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
		if(!dutyid){
			alert("请选择要修改的岗位");
			return false;
		}
		var ids = dutyid.val();
		if (ids) {
			win.window('open');
			$("#msg").load("${ctx}/tsysDutyController.do?method=modify", {
				id : ids
			}, handler);
		}
	});
	$("#btn_remove").click(function() {
		if(!dutyid){
			alert("请选择要修改的岗位");
			return false;
		}
		if (!confirm("确定删除选中的记录吗？"))
			return;
		form.attr("action", "${ctx}/tsysDutyController.do?method=delete&ids=" + dutyid.val());
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
	
	$("#btn_authorization").click(function(){
		if(!dutyid){
			alert("请选择要分配人员的岗位");
			return false;
		}
		var ids = dutyid.val();
		detailUserList({dutyid:ids});
	});
	
	$("#btn_cancel").click(
			function(){
				var ids = getSelected();
				if(ids){
					form.attr("action",
							"${ctx}/tsysDutyController.do?method=cancel&ids="+ids);
					submit(form,function(res,status){
						detailWin.window("close");
						if(isSuccess(res, status)){
							alert("取消分配用户成功", "info", function(){
								detail({dutyid:res.dutyid});
							});
						}
					});
				}else{
					alert("请选择需要取消分配的用户！");
					return;
				}
				/* var duty = $("#isGroup").val();
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
				} */
			}	
		);
	
	$("#btn_search").click(function() {
		form.submit();
	});
	$(window).resize(function() {
		$("#container").layout();
		$("#detail-container").layout();
	});
});
function detail(config, params) {
	var curPage = config.curPage, 
	pageSize = config.pageSize, 
	 duty_id = config.dutyid;
	if (duty_id == undefined) duty_id = dutyid.val();
	var url = "${ctx}/tsysDutyController.do?method=showDutyInfo&dutyid="+duty_id;
	if (notNull(curPage)) url += "&page="+curPage;
	if (notNull(pageSize)) url += "&rows="+pageSize;
	$("#userInfoList").load(url,params,function(){
		$("#detail-container", this).layout();
	});
}
function detailUserList(config, params) {
	var curPage = config.curPage, 
	pageSize = config.pageSize, 
	 duty_id = config.dutyid;
	detailWin.window('open');
	if (duty_id == undefined) duty_id = dutyid.val();
	var url = "${ctx}/tsysDutyController.do?method=assinUser&dutyid="+duty_id;
	if (notNull(curPage)) url += "&page="+curPage;
	if (notNull(pageSize)) url += "&rows="+pageSize;
	var loginName = $("input[name='srh_loginname']").val();
	if (loginName) url += "&srh_loginname=" + loginName; 
	var mobile = $("input[name='srh_mobile']").val();
	if (mobile) url += "&srh_mobile=" + mobile; 
	var usertype = $("select[name='usertype']").val();
	if (usertype) url += "&usertype=" + usertype; 
	$("#detailMsg").load(url,params,function(){
		//$("#userselect_container",this).find("input").val("");
	});
}
</script>
</head>
<body>
	<form method="post" target="_self"
		action="${ctx}/tsysDutyController.do?method=list" name="listForm" style="height: 100%;width: 100%;">
		<input type="hidden" name="error" value='' />
		<div class="easyui-layout" fit="true" id="container">
			<div class="datagrid-toolbar" region="north" style="height: auto;" border="false">
				<hih:auth operate="ADD" module="SYS_DUTY" value="button.add"
					id="btn_add" iconCls="icon-add" />
				<hih:auth operate="EDIT" module="SYS_DUTY" value="button.edit"
					id="btn_edit" iconCls="icon-edit" />
				<hih:auth operate="DELETE" module="SYS_DUTY" value="button.remove"
					id="btn_remove" iconCls="icon-remove" />
				<hih:auth operate="ALLOCATION" module="SYS_DUTY" value="button.assign.user"
					id="btn_authorization" iconCls="icon-ok" />
				<hih:auth operate="CANCEL" module="SYS_DUTY" value="button.cancel.assign"
				id="btn_cancel" iconCls="icon-remove" style="width: 70px;"/>
			</div>
			<div region="west" title="岗位树" split="true" style="width:180px;">
			<ul id="tree" style=""></ul>
			</div>
			<div id="userInfoList" region="center" style="overflow-x:auto;white-space:nowrap;" >
			</div>
		</div>
	</form>
	<div id="win" closed="true" style="width: 450px; height: 250px;">
		<div id="msg"></div>
	</div>
	<div id="detailWin" closed="true" style="width: 1000px; height: 335px;" resizable="true" collapsible="false" >
		<div id="detailMsg"></div>
	</div>
</body>
</html>