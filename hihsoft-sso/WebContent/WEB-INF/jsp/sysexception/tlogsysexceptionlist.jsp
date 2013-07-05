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
			win.window('open');
			$("#msg").load("${ctx}/tlogSysexceptionController.do?method=add", "", handler);
		});
		$("#btn_modify").click(function() {
			var ids = getSelected(true);
			if (ids) {
				win.window('open');
				$("#msg").load("${ctx}/tlogSysexceptionController.do?method=modify",{id : ids}, handler);
			}
		});
		$("#btn_remove").click(function() {
			if (!confirm("确定删除选中的记录吗？"))
				return;
			form.attr("action", "${ctx}/tlogSysexceptionController.do?method=delete&ids=" + getSelected());
			form.submit();
		});
		$("#btn_search").click(function() {
			form.submit();
		});
		$("#container").layout();
	});
</script>
</head>
<body>
	<form method="post" target="_self" action="
		${ctx}/tlogSysexceptionController.do?method=list " name="listForm">
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
				<td align="right"><fmt:message key="tlogsysexception.userid" />
				</td>
				<td><input type="text" class="text" name="srh_userid"
					value="${userid==null?"":userid}">
				</td>
				<td align="right"><fmt:message key="tlogsysexception.exceptionName" />
				</td>
				<td><input type="text" class="text" name="srh_exceptionName"
					value="${exceptionName==null?"":exceptionName}"/>
				</td>
			</tr>
		 	<tr>
				<td align="right"><fmt:message key="tlogsysexception.exceptionLasttime" />
				</td>
				<td><input type="text" name="srh_beginDate"
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"
					value="${beginDate==null?"":beginDate}"/>
				至
				<input type="text" name="srh_endDate"
					onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"
					value="${endDate==null?"":endDate}"/></td>
				<td align="right"><fmt:message key="tlogsysexception.exceptionClass" />
				</td>
				<td><input type="text" class="text" name="srh_exceptionClass" value="${exceptionClass==null?"":exceptionClass}"/></td>
			</tr>
		</table>
		<div
			style="width: 100%; height: 400px; margin: auto; position: relative;"
			id="container">
			<div class="datagrid-toolbar" region="north" style="height: 31px;"
				border="false">
			<hih:auth operate="DELETE" module="LOG_SYSEXCEPTION" value="button.remove"
					id="btn_remove" iconCls="icon-remove" />
			<hih:auth operate="QUERY" module="LOG_SYSEXCEPTION" value="button.query"
					id="btn_search" iconCls="icon-search" />
			</div>
			<div id="grid-body" class="grid-body" region="center">
				<table class="data-grid" cellspacing="0" cellpadding="0">
					<thead>
						<tr class="data-grid-header">
							<th><input type="checkbox" id="selectAll" />
							</th>     							<th width="120"><fmt:message
									key="tlogsysexception.exceptionTimes" />
							</th>   							<th width="120"><fmt:message
									key="tlogsysexception.exceptionLasttime" />
							</th>   							<th width="120"><fmt:message
									key="tlogsysexception.exceptionName" />
							</th>   							<th width="120"><fmt:message
									key="tlogsysexception.exceptionMsg" />
							</th>   							<th width="120"><fmt:message
									key="tlogsysexception.exceptionClass" />
							</th>   							<th width="120"><fmt:message
									key="tlogsysexception.userid" />
							</th>  
						</tr>
					</thead>
					<c:forEach items="${list}" var="tlogsysexception"
						varStatus="paStatus">
						<tr>
							<td width="100" class="checkbox"><input type="checkbox"
								name="ckh" value="${tlogsysexception.exceptionOid}" />
							</td>    
							<td width="120">${tlogsysexception.exceptionTimes}</td>
							<td width="120">${tlogsysexception.exceptionLasttime}</td>
							<td width="120">${tlogsysexception.exceptionName}</td>
							<td width="120">${tlogsysexception.exceptionMsg}</td>
							<td width="120">${tlogsysexception.exceptionClass}</td>
							<td width="120">${tlogsysexception.userid}</td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<jsp:include page="/WEB-INF/jsp/common/page.jsp" />
		</div>
	</form>
	<div id="win" closed="true" style="width: 450px; height: 350px;">
		<div id="msg"></div>
	</div>
</body>
</html>
