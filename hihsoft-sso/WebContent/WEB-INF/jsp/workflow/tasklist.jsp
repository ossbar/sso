<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<script type="text/javascript">
var win;
$(document).ready(function() {
	var form = $("form[name=listForm]");
	initGrid(form);
	win = $("#win").window({
		title : "处理任务",
		modal : true
	});
	$("#container").layout();
	$("#btn_process").click(function() {
		var ids = getSelected(true, form);
		if (ids) {
			win.window("open");
			$("#msg").load("${ctx}/workFlowController.do?method=hasForm&type=completeTask&id="+ids,function(txt, status, res) {
				if (txt != null) return; 
				$.get("${ctx}/actReDeploymentController.do?method=completeTask", "id="+ids, function(data) {
					if (data && data.success) {
						win.window("close");
						alert("操作成功");
						location.reload();
					} else {
						alert("操作失败");
					}
				});
			});
		}
	});
});
</script>
</head>
<body>
<form method="post" target="_self" action="${ctx}/workFlowController.do?method=listTask" name="listForm">
	<div style="width: 100%; height: 500px; margin: auto; position: relative;" id="container">
		<div class="datagrid-toolbar" region="north" style="height: 31px;"
			border="false">
			<div class="tool-btn" id="btn_process">
				<span class="icon-ok">&nbsp;</span>
				<fmt:message key="button.process" />
			</div>
		</div>
		<div id="grid-body" class="grid-body" region="center">
			<table class="data-grid" cellspacing="0" cellpadding="0">
				<thead>
					<tr class="data-grid-header">
						<th><input type="checkbox" id="selectAll" /></th>
						<th width="180">任务名称</th>  
						<th width="120">任务创建时间</th>
						<th>任务描述</th>							
					</tr>
				</thead>
				<c:forEach items="${list}" var="task" varStatus="paStatus">
					<tr>
						<td width="100" class="checkbox">
							<input type="checkbox" name="ckh" value="${task.id}" />
						</td>    
						<td width="180">${task.name}</td>
						<td width="120"><fmt:formatDate value="${task.createTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
						<td>${task.description}</td>
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
