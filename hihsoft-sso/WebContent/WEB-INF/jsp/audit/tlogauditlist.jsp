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
			$("#msg").load("${ctx}/tlogAuditController.do?method=add", "", handler);
		});
		$("#btn_modify").click(function() {
			var ids = getSelected(true);
			if (ids) {
				win.window('open');
				$("#msg").load("${ctx}/tlogAuditController.do?method=modify",{id : ids}, handler);
			}
		});
		$("#btn_remove").click(function() {
			if (!confirm("确定删除选中的记录吗？"))
				return;
			form.attr("action", "${ctx}/tlogAuditController.do?method=delete&ids=" + getSelected());
			form.submit();
		});
		$("#btn_query").click(function() {
			form.submit();
		});
		$("#container").layout();
	});
</script>
</head>
<body>
	<form method="post" target="_self" action="
		${ctx}/tlogAuditController.do?method=list " name="listForm">
		<input type="hidden" name="error" value='' />
		<div
			style="width: 100%; height: 400px; margin: auto; position: relative;"
			id="container">
			<div class="datagrid-toolbar" region="north" style="height: 31px;"
				border="false">
				<div class="tool-btn" id="btn_add">
					<span class="icon-add">&nbsp;</span>
					<fmt:message key="button.add" />
				</div>
				<div class="tool-btn" id="btn_modify">
					<span class="icon-edit">&nbsp;</span>
					<fmt:message key="button.edit" />
				</div>
				<div class="tool-btn" id="btn_remove">
					<span class="icon-remove">&nbsp;</span>
					<fmt:message key="button.remove" />
				</div>
				<div class="tool-btn" id="btn_search">
					<span class="icon-search">&nbsp;</span>
					<fmt:message key="button.query" />
				</div>
			</div>
			<div id="grid-body" class="grid-body" region="center">
				<table class="data-grid" cellspacing="0" cellpadding="0">
					<thead>
						<tr class="data-grid-header">
							<th><input type="checkbox" id="selectAll" />
							</th>                  
						</tr>
					</thead>
					<c:forEach items=" ${list}" var="tlogaudit"
						varStatus="paStatus">
						<tr>
							<td width="100" class="checkbox"><input type="checkbox"
								name="ckh" value=" ${tlogaudit.aduitid}" />
							</td>                 
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
