<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
<script type="text/javascript">
var orgform;
var baseUrl = "${ctx}/tsysOrgController.do?method=";
var exclude = "<fmt:message key="button.back"/>";
var formEnabled = false;
function detail(id) {
	$("#detail").load(baseUrl + "view&orgid="+id, function() {
		orgfrom = $("form[name='orgform']");
		disableForm();
		$("#footBar").remove(".tool-btn");
	});
}
function layout() {
	var footBar = $("#footBar");
	var toolbar = footBar.children(".tool-btn");
	if (toolbar.length == 0) {
		var text = "<fmt:message key="button.save"/>";
		footBar.append('<div class="tool-btn" id="btn_save" onclick="save_click()"><span class="icon-save">&nbsp;</span><fmt:message key="button.save" /></div>');
		footBar.append('<div class="tool-btn" id="btn_cancel" onclick="back_click()"><span class="icon-back">&nbsp;</span><fmt:message key="button.back" /></div>');
	}
}
function add_click() {
	var div = $("#detail");
	var id = $("input[name='orgid']").val();
	var url = baseUrl + "add";
	if (id) {
		url += "&orgid="+id;
	}
	if (div.size() != 0) {
		div.load(url,function(){
			layout();
		});
	} else {
		orgform.attr("action", url);
		orgform.submit();
	}
	enableForm.defer(500);
}
function save_click() {
	if (!formEnabled) return;
	var valid = $("#formID").validationEngine('validate');
	if(!valid)
		return;
	orgform.attr("action", baseUrl + "save");
	orgform[0].submit();
}
function back_click(){
	orgform.attr("action", baseUrl + "list");
	orgform.submit();
}
function delete_click() {
	var id = $("input[name='orgid']").val();
	if (id) {
		if (!confirm("确定要删除这个单位吗？")) return;
		$.ajax({
			url : baseUrl + "delete&orgid=" + id,
			success : function(data, status) {
				if (data && data.hasOrg) {
					alert("该机构下有子机构，不能删除！");
				} else if (data && data.hasDept) {
					alert("该机构下有子部门，不能删除！");
				} else if (data && data.hasUser) {
					alert("该机构下有用户，不能删除！");
				} else if (isSuccess(data, status)) {
					alert("删除成功！");
					$("#tree").tree("reload");
				} else {
					alert("删除失败!");
				}
			},
			error : function() {
				alert("删除失败，请联系管理员!");
			}
		});
	} else {
		alert("请选择一个单位");
	}
}
function enableForm() {
	switchFrom($("#container"), false , ["orgno"]);
	formEnabled = true;
}
function disableForm() {
	switchFrom($("#container"), true);
	formEnabled = false;
}
function modify_click() {
	$("#orgno").attr("class","");
	enableForm();
	layout();
}
$(function() {
	$("#tree").tree({
		url : baseUrl + "getAssignedOrgTree",
		onClick : function(n) {
			detail(n.id);
		}
	});
	$("#detail").load(baseUrl + "add", disableForm);
	$("#btn_add").click(add_click);
	$("#btn_edit").click(modify_click);
	$("#btn_remove").click(delete_click);
	$(window).resize(function() {
		$("#container").layout();
	});
});
</script>
</head>
<body>
	<div class="easyui-layout" fit="true" id="container" border="false">
		<div class="datagrid-toolbar" region="north" border="false">
			<hih:auth operate="ADD" module="SYS_ORG" value="button.add" id="btn_add" iconCls="icon-add" />
			<hih:auth operate="EDIT" module="SYS_ORG" value="button.edit" id="btn_edit" iconCls="icon-edit" />
			<hih:auth operate="DELETE" module="SYS_ORG" value="button.remove" id="btn_remove" iconCls="icon-remove" />
		</div>
		<div region="west" title="组织机构树" split="true" style="width:250px;">
			<ul id="tree" style=""></ul>
		</div>
		<div id="detail" region="center" title="单位信息" style="width: 500px;overflow-x:auto;white-space:nowrap;"></div>
	</div>
</body>
</html>