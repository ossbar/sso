<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!-- 文件上传组件 uploadify -->
<link rel="stylesheet" type="text/css" href="${ctx}/upload_plugins/uploadify.css"/>
<script type="text/javascript" src="${ctx}/upload_plugins/jquery.uploadify.js"></script>
<script type="text/javascript">
var win;
$(document).ready(function() {
	var form = $("form[name=listForm]");
	initGrid(form);
	win = $("#win").window({
		title : "启动流程",
		modal : true
	});
	$("#btn_start").click(function(){
		var ids = getSelected(true, form);
		if (ids) {
			win.window("open");
			$("#msg").load("${ctx}/workFlowController.do?method=hasForm&type=startProcess&id="+ids,function(txt, status, res) {
				if (txt != null) return; 
				$.get("${ctx}/workFlowController.do?method=startProcess", "id="+ids, function(data) {
					if (data && data.success) {
						win.window("close");
						alert("流程启动成功");
					} else {
						alert("流程启动失败");
					}
				});
			});
		}
	});
	$("#container").layout();
});
</script>
</head>
<body>
<form method="post" target="_self" action="${ctx}/workFlowController.do?method=listProcess" name="listForm">
	<div style="width: 100%; height: 500px; margin: auto; position: relative;" id="container">
		<div class="datagrid-toolbar" region="north" style="height: 31px;"
			border="false">
			<div class="tool-btn" id="btn_start">
				<span class="icon-search">&nbsp;</span>
				<fmt:message key="button.start" />
			</div>
		</div>
		<div id="grid-body" class="grid-body" region="center">
			<table class="data-grid" cellspacing="0" cellpadding="0">
				<thead>
					<tr class="data-grid-header">
						<th><input type="checkbox" id="selectAll" /></th>
						<th width="120">流程名称</th>  
						<th width="120">流程编号</th>
						<th width="120">流程版本</th>   							
						<th width="120">部署编号</th>
					</tr>
				</thead>
				<c:forEach items="${list}" var="rec" varStatus="paStatus">
					<tr>
						<td width="100" class="checkbox">
							<input type="checkbox" name="ckh" value="${rec.id}" />
						</td>    
						<td width="120">${rec.name}</td>
						<td width="120">${rec.key}</td>
						<td width="120">${rec.version}</td>
						<td width="120">${rec.deploymentId}</td>
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
