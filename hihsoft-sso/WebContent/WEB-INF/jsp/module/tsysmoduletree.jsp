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
var baseUrl = "${ctx}/tsysModuleinfoController.do?method=";
var form;
var moduleid;
var win;

$(function() {
	 win = $("#win").window({
		title : "新增模块",
		modal : true
	});
	 
	 
	 
	var handler = function() {
		var hid = function() {
			win.window("close");
		};
		$("input[type='button']").click(hid);
	};
	$("#btn_add").click(function() {
		win.window('open');
		$("#msg").load("${ctx}/tsysModuleinfoController.do?method=add&moduleid="+moduleid,	"", handler);
	});
	$("#btn_edit").click(function() {
		var ids = getSelected(true, form);
		if (ids) {
			win.window('open');
			$("#msg").load("${ctx}/tsysModuleinfoController.do?method=modify", {id : ids}, handler);
		}
	});
	$("#btn_remove").click(function() {
		var ids = getSelected(false, form);
		if (!ids || !confirm("确定删除选中的记录吗？"))
			return;
		form.attr("action", "${ctx}/tsysModuleinfoController.do?method=delete&ids=" + ids);
		submit(form, function(res, status){
			if (isSuccess(res, status)) {
				alert("删除成功！");
				var node = $("#tree").tree("getSelected");
				$('#tree').tree('remove', node.target);
				detail();
			}
		});
	});
	$("#btn_query").click(function() {
		form.submit();
	});
/* 	$("#btn_list_define").click(function() {
		window.open("${ctx}/WEB-INF/jsp/common/listdefine.jsp");
	}); */

	$("#tree").tree({
			url : baseUrl + "getModuleTree",
			onClick : function(n) {
				moduleid = n.id;
				if (isNull(n.id))
					return;
				detail({
					moduleid : n.id
				});
			}
		});
	
		$("#container").layout();
		$(window).resize(function() {
			$("#container").layout();
			$("#grid-container").layout();
		});
});

/* function removeRole(){
	var node = $("#tree").tree("getSelected");
	$('#tree').tree('remove', node.target);
} */

function removeRole(moduleid){
	var node = $('#tree').tree('find', moduleid);
	if(notNull(node)){
	  $('#tree').tree('remove', node.target);
	}
}
	
function appendRole(modulename, moduleid, flatid) {
    removeRole(moduleid);//修改后移除原来的节点
	var root = $('#tree').tree('find', flatid);
    $("#tree").tree("append",{
        parent: root.target,
        data:     [{
        	"id" : moduleid,
            "text" : modulename,
            "checked" : true
            
        }]
	});
    $('#tree').tree('select',  $('#tree').tree('find', moduleid).target);
}

function updateRole(nodeText) {
	var node = $("#tree").tree("getSelected");
	if(node) {
		node.text = nodeText;
		$("#tree").tree("update", node);
	}
}	

function detail(config) {
	config = config || {};
	var curPage = config.curPage, pageSize = config.pageSize;
	if (isNull(moduleid))
		return;
	var url = baseUrl + "queryModule&moduleid=" + moduleid;
	if (curPage)
		url += "&page=" + curPage;
	if (pageSize)
		url += "&rows=" + pageSize;
	$("#detail").load(url, function() {
		$("#grid-container").layout();
	});
}


</script>
<body>
	<div style="position: relative;" id="container" fit="true">
		<div class="datagrid-toolbar" region="north" border="false">
			<hih:auth operate="ADD" module="SYS_MODULE" value="button.add" id="btn_add" iconCls="icon-add"/>
			<hih:auth operate="EDIT" module="SYS_MODULE" value="button.edit" id="btn_edit" iconCls="icon-edit"/>
			<hih:auth operate="DELETE" module="SYS_MODULE" value="button.remove" id="btn_remove" iconCls="icon-remove"/>
			<%-- <hih:auth operate="QUERY" module="SYS_MODULE" value="button.query" id="btn_query" iconCls="icon-search"/>
			<hih:auth operate="LISTDEFINE" module="SYS_MODULE" value="button.listdefine" id="btn_list_define" iconCls="icon-ok" style="width:70px;"/> --%>
		</div>
		<div region="west" title="模块树" split="true" style="width:220px;">
			<ul id="tree"></ul>
		</div>
		<div region="center" id="detail" title="模块定义列表">
	  </div>
</div>
<div id="win" closed="true" style="width: 600px; height: 500px;">
	<div id="msg"></div>
</div>
</body>
</html>