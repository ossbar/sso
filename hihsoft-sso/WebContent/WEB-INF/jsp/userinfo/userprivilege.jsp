<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
var moduleTree;
var dataTree;
$(function() {
	var userId = $("#userId").val();
	if (!userId) return;
	var baseUrl = "${ctx}/taclUserinfoController.do?method=";
	moduleTree = $("#moduleTree").tree({
		url : baseUrl + "getPrivilegeTree&userId=" + userId,
		checkbox : true,
		onLoadSuccess : function(node, data) {
			if (data && data.length > 0) {
				var checked = $(this).tree("getChecked");
				$.each(checked, function(i, n){
					if (moduleTree.tree("isLeaf", n.target)) {
						moduleTree.tree("check", n.target);
					}
					n.target.disabled = n.attributes.disabled || false;
				});
			}
		},
		onCheck : function(node, checked) {
			if (node.target.disabled) {
				return false;
			}
		}
	});
	dataTree = $("#dataTree").tree({
		url : baseUrl + "getOrgTree&userId=" + userId,
		checkbox : true,
		includeHalf : false,
		onLoadSuccess : function(node, data) {
			if (data && data.length > 0) {
				var checked = dataTree.tree("getChecked");
				$.each(checked, function(i, n){
					if (dataTree.tree("isLeaf", n.target)) {
						dataTree.tree("check", n.target);
					}
				});	
			}
		}
	});
	win.window("setTitle", "分配特权");
	$("#treecontainer").layout();
});
function save_privilege_click() {
	var modules = moduleTree.tree("getChecked");
	var moduleSet = [];
	$(modules).each(function(i, n) {
		if (n.target.disabled) return true;
		var obj = n.attributes || {};
		if (!obj.moduleid) return true; 
		moduleSet.push((obj.operid || "") + "," + (obj.moduleid || ""));
	});
	moduleSet = moduleSet.join(";");
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
	param["moduleSet"] = moduleSet;
	param["dataSet"] = dataSet;
	param["treeSet"] = treeSet;
	param["userId"] = $("#userId").val();
	var userId = $("#userId").val();
	if (!userId) return;
	$.ajax({
		url : baseUrl + "savePrivilege",
		data : param,
		success : function(data, status) {
			if (isSuccess(data, status)) {
				alert("保存特权成功！");
				win.window("close");
			} else {
				alert("保存特权失败！");
			}
		},
		error : function(data, status) {
			alert("保存特权失败！请联系管理员");
		},
		dataType : "json",
		type : "POST"
	});
}
function back_click() {
	win.window("close");
}
</script>
</head>

<body>
<input id="userId" value="${userId}" type="hidden">
<div id="treecontainer" style="width: 100%; height: 400px;" region="center">
	<div region="west" style="width: 300px;" title="操作权限" split="true">
		<ul id="moduleTree"></ul>
	</div>
	<div region="center" title="数据权限">
		<ul id="dataTree"></ul>
	</div>
</div>
<div align="center" class="foot-buttons">
	<div class="tool-btn" id="btn_save" onclick="save_privilege_click()">
		<span class="icon-save">&nbsp;</span>
		<fmt:message key="button.save" />
	</div>
	<div class="tool-btn" id="btn_save" onclick="back_click()">
		<span class="icon-back">&nbsp;</span>
		<fmt:message key="button.back" />
	</div>
</div>
</body>
</html>