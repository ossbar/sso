
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
		var handler = function() {
			var div = $(this);
			div.show();
			$("#btn_save").click(function() {
				submit($("form[name='tsysParameterForm']", div), function(data, status) {
					if (isSuccess(data, status)) {
						showTip("保存成功！");
						(function() {
							form.submit();
						}).defer(700)
					}
				}, function(data, status) {
					alert("保存失败!");
				});
			});
			$("#btn_cancel").click(function() {
				win.window("close");
			});
		};
		var win = $("#win").window({
			title : "新增",
			modal : true
		});
		var query=$("#query_win").dialog();
		$("#btn_add").click(
				function() {
					win.window('open');
					$("#msg").load(
							"${ctx}/tsysParameterController.do?method=add", "",
							handler);
				});
		$("#btn_edit")
				.click(
						function() {
							var ids = getSelected(true, form);
							if (ids)
								win.window('open');
								$("#msg")
										.load(
												"${ctx}/tsysParameterController.do?method=modify",
												{
													id : ids
												}, handler);
						});
		$("#btn_remove").click(function() {
			if (!confirm("确定删除选中的记录吗？"))
				return;
			var old = form.attr("action");
			form.attr("action", "${ctx}/taclUserleaveController.do?method=delete&ids=" + ids);
			submit(form, function(data, status) {
				if (isSuccess(data, status)) {
					showTip("删除成功！");
					form.attr("action", old);
					var fn = function() {
						form.submit();
					}
					fn.defer(700);
				} else {
					alert("删除失败！");
				}
			}, function(data, status) {
				alert("删除失败！");
			});
		});
		form.submit(function() {
			var url = form.attr("action");
			form.appendParamsForm("#query_msg>form");
		});
		$("#btn_search").click(function() {
			query.dialog("open");
		});
		$("#btnQuery").click(function(){
			$("#query_msg form").submit();
		});
		$("#btnReset").click(function(){
			$("#query_msg form").find("input").val("");
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
<form method="post" target="_self" action="${ctx}/tsysParameterController.do?method=list" name="listForm" style="width: 100%;height: 100%;">
	<div id="container" fit="true" border="false">
		<div class="datagrid-toolbar" region="north" border="false">
			<hih:auth operate="ADD" module="SYS_PARAMTEINFO" value="button.add"
					id="btn_add" iconCls="icon-add" />
				<hih:auth operate="EDIT" module="SYS_PARAMTEINFO" value="button.edit"
					id="btn_edit" iconCls="icon-edit" />
				<hih:auth operate="DELETE" module="SYS_PARAMTEINFO"
					value="button.remove" id="btn_remove" iconCls="icon-remove" />
				<hih:auth operate="QUERY" module="SYS_PARAMTEINFO"
					value="button.query" id="btn_search" iconCls="icon-search" />
		</div>
		<div id="grid-body" region="center">
			<hih:table items="${list}" var="item">
				<hih:column field="paraid" checkbox="true"/>
				<hih:column field="isdefault" header="tsysparameter.isdefault" width="120">${item.isdefault eq "1" ? "是": item.isdefault}</hih:column>
				<hih:column field="paraname" header="tsysparameter.paraname" width="120"/>
				<hih:column field="parano" header="tsysparameter.parano" width="120"/>
				<hih:column field="paraClass" header="tsysparameter.paraclass" width="120">${item.paraClass eq "1" ? "自定义级":"系统级"}</hih:column>
				<hih:column field="paraKey" header="tsysparameter.parakey" width="120"/>
				<hih:column field="paraType" header="tsysparameter.paratype" width="120"/>
				<hih:column field="paraOrder" header="tsysparameter.paraorder" width="120"/>
			</hih:table>
		</div>
		<jsp:include page="/WEB-INF/jsp/common/page.jsp" />
	</div>
</form>
<div id="win" closed="true" style="width: 450px; height: 350px;">
	<div id="msg"></div>
</div>
<div id="query_win" style="width: 600px;height: 250px" closed="true" buttons="#dlg-buttons" title="高级查询">
	<div id="query_msg" style="position: relative;">
		<form action="${ctx}/tsysParameterController.do?method=list" method="post">
			<table class="FormView" border="0" cellspacing="1" cellpadding="0">
				<col class="Label" />
				<col class="Data" />
				<col class="Label" />
				<col class="Data" />
				<tr>
					<td><fmt:message key="tsysparameter.paraname"/></td>
					<td><input type="text" name="srh_paraname" class="text" /></td> 
					<td><fmt:message key="tsysparameter.parano"/></td>
					<td><input type="text" name="srh_parano" class="text"/></td> 
				</tr>
				<tr>
					<td><fmt:message key="tsysparameter.paraclass"/></td>
					<td><input type="text" name="srh_paraClass" class="text" /></td> 
					<td><fmt:message key="tsysparameter.parakey"/></td>
					<td><input type="text" name="srh_paraKey" class="text"/></td> 
				</tr>
				<tr>
					<td><fmt:message key="tsysparameter.paratype"/></td>
					<td><input type="text" name="srh_paraType" class="text"/></td> 
					<td><fmt:message key="tsysparameter.paraorder"/></td>
					<td><input type="text" name="srh_paraOrder" class="text"/></td> 
				</tr>
			</table>
			<div id="dlg-buttons" style="text-align: center;">
				<a href="#" id="btnQuery" class="easyui-linkbutton" iconCls="icon-search"><fmt:message key="button.query" /></a>
				<a href="#" id="btnReset" class="easyui-linkbutton" iconCls="icon-cancel"><fmt:message key="button.reset" /></a>
			</div>
		</form>
	</div>
</div>
</hih:form>
</body>
</html>
