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
$(document).ready(function() {
	var form = $("form[name=listForm]");
	initGrid(form);
	var uploader = $("#uploader").dialog();
	$("#btn_upload").click(function() {
		uploader.dialog('open');
	});
	$("#btn_remove").click(function() {
		var ids = getSelected(false, form);
		if (!ids) return;
		if (!confirm("确定删除选中的记录吗？"))
			return;
		var cascad = confirm("是否要级联删除相关的文件？");
		var url = "${ctx}/workFlowController.do?method=delete&ids=" + ids;
		url += cascad ? "&cascad=true" : "";
		form.attr("action", url);
		form.submit();
	});
	$("#btn_search").click(function() {
		document.location= "${ctx}/workFlowController.do?method=queryTaskList";
		//form.submit();
	});
	$("#container").layout();
	var up = $('#file_upload');
	var uploaded = [];
	up.uploadify({
	  'swf'  : '${ctx}/upload_plugins/uploadify.swf',
	  'uploader'    : '${ctx}/workFlowController.do?method=upload',
	  'cancelImage' : '${ctx}/upload_plugins/uploadify-cancel.png',
	  'folder'    : '/uploads',
	  'buttonText': '选择文件',
	  'queueID'   : 'fileDiv', 
	  'auto'      : false,
	  'queueSizeLimit' : 20, 
      'fileDataName'   : 'file', 
      'multi'          : true, 
      'simUploadLimit' : 20,
      'onUploadSuccess': function(file, data, response) {
    	  var f = decode(data);
    	  if (f[0]) uploaded.push(f[0]);
      },
      'onQueueComplete' : function(state) {
    	  if (state.upload_errors == 0 && uploaded.length > 0) {
    		  alert("上传成功！", "info", function() {
    			  uploader.dialog('close');
    			  form.submit();
    		  });
    	  }
      },
      'fileTypeExts'     : '*.bar',
      'fileTypeDesc'    : '流程部署文件'
	});
	$("#btnUpload").click(function(){
		up.uploadifyUpload('*');
	});
	$("#btnCancel").click(function(){
		up.uploadifyCancel('*');
	});
});
</script>
</head>
<body>
<form method="post" target="_self" action="${ctx}/workFlowController.do?method=listDeployment" name="listForm">
	<div style="width: 100%; height: 500px; margin: auto; position: relative;" id="container">
		<div class="datagrid-toolbar" region="north" style="height: 31px;"
			border="false">
			<div class="tool-btn" id="btn_upload">
				<span class="icon-add">&nbsp;</span>
				<fmt:message key="button.upload" />
			</div>
			<div class="tool-btn" id="btn_search">
				<span class="icon-search">&nbsp;</span>
				<fmt:message key="button.query" />
			</div>
			<div class="tool-btn" id="btn_remove">
				<span class="icon-remove">&nbsp;</span>
				<fmt:message key="button.remove" />
			</div>
		</div>
		<div id="grid-body" class="grid-body" region="center">
			<table class="data-grid" cellspacing="0" cellpadding="0">
				<thead>
					<tr class="data-grid-header">
						<th><input type="checkbox" id="selectAll" /></th>
						<th width="">资源文件名称</th>   							
						<th width="140">上传时间</th>
					</tr>
				</thead>
				<c:forEach items="${list}" var="rec" varStatus="paStatus">
					<tr>
						<td width="100" class="checkbox">
							<input type="checkbox" name="ckh" value="${rec.id}" />
						</td>    
						<td style="padding: 2px 3px;">${rec.name}</td>
						<td style="padding: 2px 3px;"><fmt:formatDate value="${rec.deploymentTime}" pattern="yyyy-MM-dd hh:mm:ss"/></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<jsp:include page="/WEB-INF/jsp/common/page.jsp" />
	</div>
</form>
<div id="uploader" closed="true" title="上传文件" width="400" height="350" style="padding: 5px;">
	<div style="width: 380px;position: relative;padding-left: 3px;">
		<input type="file" id="file_upload" name="file_upload">
		<div style="float: right;position: absolute;right: 5px;top: 10px;">
			<input type="button" id="btnUpload" value="开始上传"> | <input type="button" id="btnCancel" value="清除队列"> 
		</div>
	</div>
	<div id="fileDiv" style="width: 380px;height: 320px;border: 4px solid #ECF4FA;padding-left: 5px;margin-top: 8px;overflow: auto;">
	</div>
</div>
</body>
</html>
