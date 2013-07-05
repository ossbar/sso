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
var baseUrl = "${ctx}/tsysModuleoperateController.do?method=";
var form;
var moduleid;
var win;
$(function() {
	win = $("#win").window({
		title : "新增",
		modal : true
	});
	var handler = function() {
		var hid = function() {
			win.window("close");
		};
		$("input[type='button']").click(hid);
	};
	$("#btn_add").click(function() {
		if (!moduleid) {
			alert("请选择一个模块");
			return;
		}
		win.window('open');
		$("#msg").load(baseUrl + "add&moduleid="+moduleid, "", handler);
	});
	$("#btn_edit").click(function() {
		var ids = getSelected(true, form);
		if (ids) {
			win.window('open');
			$("#msg").load(baseUrl + "modify",{id : ids}, handler);
		}
	});
	$("#btn_remove").click(function() {
		var ids = getSelected(false, form);
		if (!ids || !confirm("确定删除选中的记录吗？"))
			return;
		form.attr("action", baseUrl + "delete&ids=" + ids);
		submit(form, function(res, status){
			if (isSuccess(res, status)) {
				alert("删除成功！");
				detail();
			}
		});
	});
	$("#tree").tree({
		url : baseUrl + "getModuleTree",
		onClick : function(n) {
			moduleid = n.id;
			if (isNull(n.id)) return; 
			detail({moduleid : n.id});
		}
	});
	$("#container").layout();
	$(window).resize(function() {
		$("#container").layout();
		$("#grid-container").layout();
	});
});
function detail(config) {
	config = config || {};
	var curPage = config.curPage, 
		pageSize = config.pageSize;
	if (isNull(moduleid)) return;
	var url = baseUrl + "list&moduleid="+moduleid;
	if (curPage) url += "&page="+curPage;
	if (pageSize) url += "&rows="+pageSize;
	$("#detail").load(url, function() {
		$("#grid-container").layout();
	});
}
function save_click() {
	if(!$("#formID").validationEngine('validate'))
		return;	
	var form = $("form[name='tsysModuleoperateForm']");
	form.attr("action", baseUrl + "save");
	submit(form, function(res,status) {
		win.window("close");
		if(isSuccess(res, status)) {
			alert("保存成功!");
			detail();
		}
	});
}
</script>
<body>
<div style="position: relative;" id="container" fit="true">
	<div region="north" border="false" class="datagrid-toolbar">	
		<hih:auth operate="ADD" module="SYS_OPERATEINFO" value="button.add"
					id="btn_add" iconCls="icon-add" />
		<hih:auth operate="EDIT" module="SYS_OPERATEINFO" value="button.edit"
					id="btn_edit" iconCls="icon-edit" />
		<hih:auth operate="DELETE" module="SYS_OPERATEINFO"
					value="button.remove" id="btn_remove" iconCls="icon-remove" />
		</div>
		<div region="west" title="模块树" split="true" style="width:220px;">
			<ul id="tree"></ul>
		</div>
		<div region="center" id="detail" title="操作列表">
	</div>
</div>
<div id="win" closed="true" style="width: 600px; height: 500px;">
	<div id="msg"></div>
</div>
</body>
</html>