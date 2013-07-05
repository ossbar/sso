 <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
var baseUrl = "${ctx}/taclUserinfoController.do?method=";
var form;
var org;
var win;
var dlg;
$(function() {
	win = $("#win").window({
		title : "新增",
		modal : true
	});
	var tree = $("#tree").tree({
		url : baseUrl + "getAssignedOrgTree",
		onClick : function(n) {
			var attr = decode(n.attributes);
			dlg.find("form")[0].reset();
			detail({orgid : n.id});
		}
	});
	function check() {
		var root = tree.tree("getRoot");
		var pass =  (root.id != org.val());
		if (!pass) {
			alert("集团节点下不允许操作");
		}
		return pass;
	}
	$("#btn_add").click(function() {
		if (!org || !org.val()) {
			alert("请选择一个单位");
			return;
		}
		if (!check()) return;
		win.window('open');
		$("#msg").load(baseUrl + "add&orgid="+org.val());
	});
	$("#btn_edit").click(function() {
		var ids = getSelected(true, form);
		if (!ids || ids.length == 0) return;
		if (!check()) return;
		win.window("setTitle", "修改");
		win.window('open');
		$("#msg").load(baseUrl + "modify",{id : ids});
	});
	$("#btn_remove").click(function() {
		var ids = getSelected(false,form);
		if (!ids || ids.length == 0) return;
		if (!check()) return;
		if (!confirm("确定删除选中的记录吗？"))
			return;
		form.attr("action", baseUrl + "delete&ids=" + getSelected(false,form));
		submit(form, function(res, status){
			if (isSuccess(res, status)) {
				alert("删除成功！");
				detail({orgid : org.val()});
			}
		});
	});
	$("#btn_defRole").click(function() {
		if (!form) {
			alert("请选择一个单位");
			return;
		}
		var ids = getSelected(false, form);
		if (!ids || ids.length == 0) return;
		if (!check()) return;
		win.window("setTitle", "分配角色");
		win.window("open");
		$("#msg").load(baseUrl + "listRole", {userId : ids});
	});
	$("#btn_resetRole").click(function() {
		if (!form) {
			alert("请选择一个单位");
			return;
		}
		var id = getSelected(true, form);
		if (!id || id.length == 0) return;
		if (!check()) return;
		if (!confirm("确定要执行此操作吗？")) return;
		$.ajax({
			url : baseUrl + "clearRole",
			data : "id="+id,
			success : function(data, type) {
				if (isSuccess(data, type)) {
					alert("操作成功！");
				}
			},
			error : function(data, type) {
				alert("操作失败！请联系管理员");
			},
			cache: false,
			dataType : "json",
			type : "POST"
		});
	});
	$("#btn_privilege").click(function() {
		var ids = getSelected(true, form);
		if (!ids) return;
		win.window("open");
		$("#msg").load(baseUrl + "privilege", {userId : ids});
	});
	dlg = $("#queryLayer").dialog();
	$("#btn_query").click(function(e) {
		if (!org || !org.val()) {
			alert("请选择一个单位");
			return;
		}
		$("#queryLayer .dialog-content").show();
		dlg.dialog("open");
	});
	$("#btnQuery").click(function() {
		var params = dlg.find("form").serialize();
		if (params) {
			detail({}, params);
		}
	});
	$("#btnReset").click(function() {
		dlg.find("form")[0].reset();
	});
	$("input[name=srh_dutyid]").combotree({
		url : "${ctx}/taclUserinfoController.do?method=getDutyTree"
	});
	$(window).resize(function() {
		$("#container").layout();
		$("#grid-container").layout();
	})
});
function detail(config, params) {
	var curPage = config.curPage, 
		pageSize = config.pageSize, 
		orgid = config.orgid;
	if (orgid == undefined) orgid = org.val();
	if (!params) params = dlg.find("form").getFormParams();
	var url = baseUrl + "list&orgid="+orgid;
	if (notNull(curPage)) url += "&page="+curPage;
	if (notNull(pageSize)) url += "&rows="+pageSize;
	$("#detail").load(url, params, function(){
		$("#grid-container", this).layout();
		var page = $(this);
		form = $("form", page);
		org = page.find("input[name='orgid']");
		org.val(orgid);
	});
}

function save_click() {
	var valid = $("#formID").validationEngine('validate');
	if(!valid)
		return;
	if (isNull($("#dutyId").combotree('getValue'))) {
		showTip("请选择岗位");
		return;
	}
	var datas = dataTree.tree("getChecked");
	var dataSet = [];
	for ( var i = 0; i < datas.length; i++) {
		dataSet.push(datas[i].id);
	}
	dataSet = dataSet.join(",");
	var treeSet = [];
	dataTree.tree("options").includeHalf = true;
	datas = dataTree.tree("getChecked");
	for ( var i = 0; i < datas.length; i++) {
		treeSet.push(datas[i].id);
	}
	treeSet = treeSet.join(",");
	var f = $("#formID");
	var param = f.getFormParams();
	param["dataSet"] = dataSet;
	param["treeSet"] = treeSet;
	$.request("${ctx}/taclUserinfoController.do?method=save", param, function(data, status) {
		if (data && data.success && status == "success") {
			win.window("close");
			alert("保存成功!");
			detail({orgid:org.val()});
		} else {
			alert("保存失败!");
		}
	},function(data, status) {
		alert("保存失败!");
	});
}
function showUser(id) {
	if (!id) return;
	win.window("setTitle", "查看用户详细信息");
	win.window("open");
	$("#msg").load(baseUrl + "view&id=" + id);
}
</script>
<body>
	<div class="easyui-layout" fit="true" id="container">
		<div region="north" border="false" class="datagrid-toolbar">
			<hih:auth operate="ADD" module="ACL_USER" value="button.add"
					id="btn_add" iconCls="icon-add" />
			<hih:auth operate="EDIT" module="ACL_USER" value="button.edit"
				id="btn_edit" iconCls="icon-edit" />
			<hih:auth operate="DELETE" module="ACL_USER" value="button.remove"
				id="btn_remove" iconCls="icon-remove" />
			<hih:auth operate="DEFROLE" module="ACL_USER" value="button.assign.role"
				id="btn_defRole" iconCls="icon-ok" />
			<hih:auth operate="PRIVILEGE" module="ACL_USER" value="button.role.privilege"
				id="btn_privilege" iconCls="icon-sum" />
			<hih:auth operate="RESET" module="ACL_USER" value="button.role.reset"
				id="btn_resetRole" iconCls="icon-reload"/>
			<hih:auth operate="QUERY" module="ACL_USER" value="button.query"
				id="btn_query" iconCls="icon-search"/>
		</div>
		<div region="west" title="组织机构树" split="true" style="width:220px;">
			<ul id="tree"></ul>
		</div>
		<div region="center" id="detail" title="用户列表">
		</div>
	</div>
<div id="win" closed="true" style="width: 650px; height: 535px;" resizable="false" collapsible="false" >
	<div id="msg"></div>
</div>
<hih:form bean="request">
<div id="queryLayer" closed="true" style="width: 450px; height: 350px;display: none;" title="查询" buttons="#dlg-buttons">
	<form>
	<table class="FormView" border="0" cellspacing="1" cellpadding="0">
		<col class="Label" />
		<col class="Data" />
		<tr>
			<td align="right"><fmt:message key="tacluserinfo.loginname" /></td>
			<td><input name="srh_loginname" class="text" type="text"></td>
		</tr>
		<tr>
			<td align="right"><fmt:message key="tacluserinfo.username"/></td>
			<td><input name="srh_username" class="text" type="text"></td>
		</tr>
		<tr>
			<td align="right"><fmt:message key="tacluserinfo.usertype"/></td>
			<td><hih:parameter name="userType" id="srh_usertype" withEmpty="true" cssClass="select"/></td>
		</tr>
		<tr>
			<td align="right"><fmt:message key="tacluserinfo.dutyid"/></td>
			<td>
				<input name="srh_dutyid" type="text" class="text" style="width: 165px;">
			</td>
		</tr>
		<tr>
			<td align="right"><fmt:message key="tacluserinfo.mobile"/></td>
			<td>
				<input name="srh_mobile" type="text" class="text">
			</td>
		</tr>
		<tr>
			<td align="right"><fmt:message key="tacluserinfo.idcard"/></td>
			<td>
				<input name="srh_idcard" type="text" class="text">
			</td>
		</tr>
		<tr>
			<td align="right"><fmt:message key="tacluserinfo.userstate"/></td>
			<td><hih:parameter name="userState" id="srh_userstate" cssClass="select"/></td>
		</tr>
	</table>
	<div id="dlg-buttons" style="text-align: center;">
		<input id="btnQuery" type="button" class="icon-search common-button" value="<fmt:message key="button.query"/>">
		<input id="btnReset" type="button" class="icon-cancel common-button" value="<fmt:message key="button.reset"/>">
	</div>
	</form>
</div>
</hih:form>
</body>
</html>