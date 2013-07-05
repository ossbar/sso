
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
			title : "子系统基本信息",
			modal : true
		});
		var handler = function() {
			var hid = function() {
				//win.window("close");
			};
			$("input[type='button']").click(hid);
		};
		$("#btn_add").click(
				function() {
					win.window('open');
					$("#msg").load("${ctx}/tsysFlatController.do?method=add",
							"", handler);
				});
		$("#btn_edit").click(function() {
			var ids = getSelected(true);
			if (ids) {
				win.window('open');
				$("#msg").load("${ctx}/tsysFlatController.do?method=modify", {
					id : ids
				}, handler);
			}
		});
		$("#btn_remove").click(
				function() {
					if (!confirm("确定删除选中的记录吗？"))
						return;
					form.attr("action",
							"${ctx}/tsysFlatController.do?method=delete&ids="
									+ getSelected());
					form.submit();
				});
		$("#btn_query").click(function() {
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
		action="${ctx}/tsysFlatController.do?method=list" name="listForm" style="width: 100%;height: 100%;">
		<div style="position: relative;" id="container" fit="true">
			<div class="datagrid-toolbar" region="north" border="false">
				<hih:auth operate="ADD" module="SYS_FLATINFO" value="button.add"
					id="btn_add" iconCls="icon-add" />
				<hih:auth operate="EDIT" module="SYS_FLATINFO" value="button.edit"
					id="btn_edit" iconCls="icon-edit" />
				<hih:auth operate="DELETE" module="SYS_FLATINFO"
					value="button.remove" id="btn_remove" iconCls="icon-remove" />
				<hih:auth operate="QUERY" module="SYS_FLATINFO"
					value="button.query" id="btn_search" iconCls="icon-search" />
			</div>
			<div id="grid-body" region="center">
			<hih:table items="${list}" var="item">
				<hih:column field="flatid" checkbox="true"/>
				<hih:column field="flatcode" header="tsysflat.flatcode" width="120"/>
				<hih:column field="flatname" header="tsysflat.flatname" width="120"/>
				<hih:column field="shortname" header="tsysflat.shortname" width="120"/>
				<hih:column field="flatdesc" header="tsysflat.flatdesc" width="120"/>
				<hih:column field="remark" header="tsysflat.remark" width="120"/>
				<hih:column field="flaturl" header="tsysflat.flaturl" width="120"/>
			</hih:table>
		</div>
			<jsp:include page="/WEB-INF/jsp/common/page.jsp" />
		</div>
	</form>
	<div id="win" closed="true" style="width: 450px; height: 350px;">
		<div id="msg"></div>
	</div>
</body>
</html>
