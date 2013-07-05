<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
</head>
<script type="text/javascript">
	var roleId = "";
	var moduleId = "";
	var win;
	var moduleTree;
	var dataTree;
	function init() {
		var div = $(this);
		initGrid(div.find("form"));
		div.find("#btn_add").click(btn_add_click);
		div.find("#btn_modify").click(btn_modify_click);
		div.find("#btn_remove").click(btn_remove_click);
		div.find("#btn_save").click(btn_save_click);
	}
	function save_click() {
		if (!moduleTree || !dataTree) return;
		var modules = moduleTree.tree("getChecked");
		var moduleSet = "";
		for (var i = 0; i < modules.length; i++) {
			var attr = modules[i].attributes;
			if (attr) {
				var obj = decode(attr);
				moduleSet += (";"+obj.operid+","+obj.moduleid);
			}
		}
		moduleSet = moduleSet.substring(1);
		var datas = dataTree.tree("getChecked");
		var dataSet = "";
		for (var i = 0; i < datas.length; i++) {
			dataSet +=  "," + datas[i].id;
		}
		dataSet = dataSet.substring(1);
		//$("#msg").attr("innerText", dataSet + "\n" + moduleSet);
		//win.window("open");return;
		$.ajax({
			url : "${ctx}/taclRoleprivilegeController.do?method=saveRoleSet",
			data : "roleId=" + roleId + "&moduleSet=" + moduleSet + "&dataSet=" + dataSet,
			type : 'POST',
			success : function(data) {
				var d = decode(data);
				if (d && d.success) {
					alert("保存权限设置成功！");
				} else {
					alert("保存权限设置失败！");
				}
			}
		});
	}
	function btn_add_click() {
		win.window('open');
		$("#msg").load("${ctx}/tsysModuleoperateController.do?method=add&moduleid="+moduleId);
	}
	function btn_modify_click() {
		
	}
	function btn_remove_click() {
		var ids = getSelected();
		if (!confirm("确定删除选中的记录吗？"))
			return;
		$.ajax({
			url : "${ctx}/tsysModuleoperateController.do",
			data : "method=delete&ids=" + ids,
			success : function(data) {
				alert("删除成功！");
				loadTree(moduleId,roleId);
			}
		});
	}
	function queryRole(v) {
		roleId = v;
		var tree = $("#moduleTree");
		var opt = tree.tree('options');
		opt.url = "${ctx}/taclRoleprivilegeController.do?method=getModuleTree&roleId=" + v;
		tree.tree('reload');
		var tree = $("#dataTree");
		var opt = tree.tree('options');
		opt.url = "${ctx}/taclRoleprivilegeController.do?method=getOrgTree&roleId="	+ v;
		tree.tree('reload');
	}

	$(function() {
		moduleTree = $("#moduleTree").tree({
			checkbox : true,
			url : "${ctx}/taclRoleprivilegeController.do?method=getModuleTree&roleId=",
			onClick : function(n) {
				moduleId = n.id;
				//loadTree(moduleId,roleId);
			},
			onCheck : function(n, checked) {
				if (checked) {
					var attr = n.attributes;
					if (!event.ctrlKey && !attr) {
						n.checked = false;
						moduleTree.tree("uncheck", n.target);
						return false;
					}
				}
			}
		});
		dataTree = $("#dataTree").tree({
			checkbox : true,
			url : "${ctx}/taclRoleprivilegeController.do?method=getOrgTree",
			onClick : function(n) {
				//moduleId = n.id;
				//loadTree(moduleId,roleId);
			}
		});
		win = $("#win").window({
			title : "新增模块操作",
			modal : true
		});
		$("#container").layout();
	});
	
</script>

<body class="easyui-layout">
<div style="width: 100%; margin: auto" region="center">
<hih:form bean="request">
	<form action="/taclRoleprivilegeController" method="post">
	<input type="hidden" name="error" value='' />
	<div>
	<div>
	<table class="FormView" border="0" cellspacing="1" cellpadding="0">
		<col class="Label" />
		<col class="Data" />
		<col class="Label" />
		<col class="Data" />
		<tr>
			<td><fmt:message key="taclrolelist.title"/></td>
			<td><select name="roleid" class="select" onchange="queryRole(this.value)">
				<option value="">------</option>
				<c:forEach items="${rolelist}" var="role">
					<option value="${role.roleid}">${role.rolename}</option>
				</c:forEach>
			</select></td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
	</table>
	</div>
	</div>
<div>
	<div style="position: relative;height: 600px;" id="container">
		<div style="height: 30px;"  region="north">
			<div class="tool-btn" id="btn_save" onclick="save_click()">
				<span class="icon-save">&nbsp;</span><fmt:message key="button.save" />
			</div>
		</div>
		<div region="west" style="width: 320px;height: 400px;" split="true" title="模块列表">
			<ul id="moduleTree" ></ul>
		</div>
		<div id="datas" region="center" title="数据权限">
			<ul id="dataTree"></ul>
		</div>
	</div>
</div>
</form>
</hih:form>
</div>
<div id="win" closed="true" style="width: 450px; height: 350px;">
	<div id="msg"></div>
</div>
</body>
</html>
