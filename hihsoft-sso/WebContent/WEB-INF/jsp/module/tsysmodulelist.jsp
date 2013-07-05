<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
<script type="text/javascript">
	$(function() {
		var form = $("form");
		initGrid(form);
		var win = $("#win").window({
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
			$("#msg").load("${ctx}/tsysModuleinfoController.do?method=add",	"", handler);
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
			form.submit();
		});
		$("#btn_query").click(function() {
			form.submit();
		});
		$("#btn_list_define").click(function() {
			window.open("${ctx}//WEB-INF/jsp/common/listdefine.jsp");
		});
		$("#container").layout();
		$(window).resize(function() {
			$("#container").layout();
		});
	});
</script>
</head>

<body>
<hih:form bean="request">
	<form method="post" target="_self" action="${ctx}/tsysModuleinfoController.do?method=list" style="width: 100%;height: 95%;">
	<table class="FormView" border="0" cellspacing="1" cellpadding="0">
		<tr height="30px">
			<td align="left" bgcolor="#E0EEEE">
				模块名称 <input type="text" name="srh_modulename" class="text"/>
			</td>
		</tr>		
	</table>
	<div style="position: relative;" id="container" border="false" fit="true">
		<div class="datagrid-toolbar" region="north" border="false">
			<hih:auth operate="ADD" module="SYS_MODULE" value="button.add" id="btn_add" iconCls="icon-add"/>
			<hih:auth operate="EDIT" module="SYS_MODULE" value="button.edit" id="btn_edit" iconCls="icon-edit"/>
			<hih:auth operate="DELETE" module="SYS_MODULE" value="button.remove" id="btn_remove" iconCls="icon-remove"/>
			<hih:auth operate="QUERY" module="SYS_MODULE" value="button.query" id="btn_query" iconCls="icon-search"/>
			<hih:auth operate="LISTDEFINE" module="SYS_MODULE" value="button.listdefine" id="btn_list_define" iconCls="icon-ok" style="width:70px;"/>
		</div>
		<div id="grid-body" region="center">
			<hih:table items="${list}" var="item">
				<hih:column field="moduleid" checkbox="true"/>
				<hih:column field="modulename" header="tsysmoduleinfo.modulename" width="120"/>
				<hih:column field="moduleno" header="tsysmoduleinfo.moduleno" width="120"/>
				<hih:column field="moduleclass" header="tsysmoduleinfo.moduleclass" width="120"/>
				<hih:column field="linkedaddr" header="tsysmoduleinfo.linkedaddr" width="120"/>
				<hih:column field="moduledesc" header="tsysmoduleinfo.moduledesc" width="120"/>
			</hih:table>
		</div>
		<jsp:include page="/WEB-INF/jsp/common/page.jsp" />
	</div>
	</form>
</hih:form>
<div id="win" closed="true" style="width: 450px; height: 350px;">
	<div id="msg"></div>
</div>
</body>
</html>
