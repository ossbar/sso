
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
		$("#container").layout();
	});
</script>
</head>
<body>
	<form method="post" target="_self"
		action="
		${ctx}/tlogLoginlogController.do?method=list "
		name="listForm">
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
		<div
			style="width: 100%; height: 400px; margin: auto; position: relative;"
			id="container">
			<div class="datagrid-toolbar" region="north" style="height: 31px;"
				border="false">
				<hih:auth operate="DELETE" module="LOG_LOGIN" value="button.remove"
					id="btn_remove" iconCls="icon-remove" />
				<hih:auth operate="QUERY" module="LOG_LOGIN" value="button.query"
					id="btn_search" iconCls="icon-search" />
			</div>
			<div id="grid-body" class="grid-body" region="center">
				<table class="data-grid" cellspacing="0" cellpadding="0">
					<thead>
						<tr class="data-grid-header">
							<th><input type="checkbox" id="selectAll" />
							</th>
							<th width="120"><fmt:message key="tlogloginlog.ipaddr" />
							</th>
							<th width="120"><fmt:message key="tlogloginlog.logintime" />
							</th>
							<th width="120"><fmt:message key="tlogloginlog.logouttime" />
							</th>
							<th width="120"><fmt:message key="tlogloginlog.onlinetime" />
							</th>
							<th width="120"><fmt:message key="tlogloginlog.userid" />
							</th>
							<th width="120"><fmt:message key="tlogloginlog.logout" />
							</th>
							<th width="120"><fmt:message key="tlogloginlog.orgid" />
							</th>
						</tr>
					</thead>
					<c:forEach items="${list}" var="tlogloginlog" varStatus="paStatus">
						<tr>
							<td width="100" class="checkbox"><input type="checkbox"
								name="ckh" value="${tlogloginlog.logid}" />
							</td>
							<td width="120">${tlogloginlog.ipaddr}</td>
							<td width="120">${tlogloginlog.logintime}</td>
							<td width="120">${tlogloginlog.logouttime}</td>
							<td width="120">${tlogloginlog.onlinetime}</td>
							<td width="120">${tlogloginlog.userid}</td>
							<td width="120">${tlogloginlog.logout}</td>
							<td width="120">${tlogloginlog.orgid}</td>
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
