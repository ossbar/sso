
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
		win.window('open');
		var div = $(this);
		div.show();
	};
	win = $("#win").window({
		title : "操作窗口",
		modal : true
	});
		$("#btn_remove").click(
				function() {
					if (!confirm("确定删除选中的记录吗？"))
						return;
					form.attr("action",
							"${ctx}/tlogLoginlogController.do?method=delete&ids="
									+ getSelected());
					form.submit();
				});
		$("#btn_search").click(function() {
				form.submit();
		});
		$(".table-container").layout();
		$(window).resize(function() {
			$("#container").layout();
		});
	});

</script>
</head>
<body>
	<form method="post" target="_self"
		action="
		${ctx}/tlogLoginlogController.do?method=list "
		name="listForm" style="width: 100%;height: 90%;">
		<input type="hidden" name="error" value='' />
		<table class="FormView" border="0" cellspacing="1" cellpadding="0">
			<col class="Label" />
			<col class="Data" />
			<col class="Label" />
			<col class="Data" />
			<col class="Label" />
			<col class="Data" />
			<col class="Label" />
			<col class="Data" />
			<col class="Label" />
			<col class="Data" />
			<tr>
				<td align="right"><fmt:message key="tlogloginlog.userid" />
				</td>
				<td><input type="text" class="text" name="srh_userid"
					value="${userid==null?"":userid}">
				</td>
				<td align="right"><fmt:message key="tlogloginlog.orgid" />
				</td>
				<td><input type="text" class="text" name="srh_orgid"
					value="${orgid==null?"":orgid}"/>
				</td>
			</tr>
		 	<tr>
				<td align="right"><fmt:message key="tlogloginlog.starttime" />
				</td>
				<td><input type="text" name="srh_beginDate"
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"
					value="${beginDate==null?"":beginDate}"/>
				至
				<input type="text" name="srh_endDate"
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"
					value="${endDate==null?"":endDate}"/></td>
				<td align="right"><fmt:message key="tlogloginlog.ipaddr" />
				</td>
				<td><input type="text" class="text" name="srh_ipaddr" value="${ipaddr==null?"":ipaddr}"/></td>
			</tr>
		</table>
		<div class="easyui-layout" style="border: none;" fit="true" id="container">
			<div class="datagrid-toolbar" region="north" style="height: 31px;"
				border="false">
				<hih:auth operate="DELETE" module="LOG_LOGIN" value="button.remove"
					id="btn_remove" iconCls="icon-remove" />
				<hih:auth operate="QUERY" module="LOG_LOGIN" value="button.query"
					id="btn_search" iconCls="icon-search" />
			</div>
			<div id="grid-body" region="center">
			<hih:table items="${list}" var="tlogloginlog">
				<hih:column field="logid" checkbox="true"/>
				<hih:column field="orgid" header="tlogloginlog.orgid" width="200"/>
				<hih:column field="ipaddr" header="tlogloginlog.ipaddr" width="120"/>
				<hih:column field="userid" header="tlogloginlog.userid" width="120"/>
				<hih:column field="logintime" header="tlogloginlog.logintime" width="200"/>
			    <hih:column field="logouttime" header="tlogloginlog.logouttime" width="400"/>
			</hih:table>
			</div>
		<jsp:include page="/WEB-INF/jsp/common/page.jsp" />
		</div>
	</form>
	<div id="win" closed="true" style="width: 650px; height: 500px;">
		<div id="msg"></div>
	</div>
</body>
</html>
