<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!-- 文件上传组件 uploadify -->
<link rel="stylesheet" type="text/css" href="${ctx}/js_plugins/uploadify/uploadify.css"/>
<script type="text/javascript" src="${ctx}/js_plugins/uploadify/jquery.uploadify.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var form = $("form[name=listForm]");
	initGrid(form);
	var win = $("#win").window({
		title : "更改文件名",
		modal : true,
		onClose : function() {
			form.submit();
		}
	});
	var uploader = $("#uploader").dialog();
	$("#btn_upload").click(function() {
		uploader.dialog('open');
	});
	$("#btn_remove").click(function() {
		var ids = getSelected(false, form);
		if (!ids) return;
		if (!confirm("确定删除选中的记录吗？"))
			return;
		form.attr("action", "${ctx}/fileUploadController.do?method=delete&ids=" + ids);
		form.submit();
	});
	$("#btn_search").click(function() {
		form.submit();
	});
	$("#container").layout();
	var up = $('#file_upload');
	var uploaded = [];
	up.uploadify({
	  'swf'  : '${ctx}/js_plugins/uploadify/uploadify.swf',
	  'uploader'    : '${ctx}/fileUploadController.do?method=upload',
	  'cancelImage' : '${ctx}/js_plugins/uploadify/uploadify-cancel.png',
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
    	  if (f[0].uploadid) uploaded.push(f[0].uploadid);
      },
      'onQueueComplete' : function(state) {
    	  if (state.upload_errors == 0 && uploaded.length > 0) {
    		  alert("上传成功！", "info", function() {
    			  uploader.dialog('close');
        		  win.window("open");
        		  $("#msg").load("${ctx}/fileUploadController.do?method=rename&ids="+uploaded.join(","));
    		  });
    	  }
      }
	});
	$("#btnUpload").click(function(){
		up.uploadifyUpload('*');
	});
	$("#btnCancel").click(function(){
		up.uploadifyCancel('*');
	});
	$(window).resize(function() {
		$("#container").layout();
	});
});
</script>
</head>
<body>
<form method="post" target="_self" action="${ctx}/fileUploadController.do?method=forward " name="listForm" style="width: 100%;height: 100%;">
	<div fit="true" id="container">
		<div class="datagrid-toolbar" region="north" border="false">
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" id="btn_upload"><fmt:message key="button.upload" /></a>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="btn_remove"><fmt:message key="button.remove" /></a>
			<a class="easyui-linkbutton" iconCls="icon-search" plain="true" id="btn_search"><fmt:message key="button.query" /></a>
		</div>
		<div id="grid-body" class="grid-body" region="center">
			<table class="data-grid" cellspacing="0" cellpadding="0">
				<thead>
					<tr class="data-grid-header">
						<th><input type="checkbox" id="selectAll" /></th>
						<th width="120"><fmt:message key="tsysupload.secondName" /></th>  
						<th width="120"><fmt:message key="tsysupload.fileName" /></th>
						<th width="120"><fmt:message key="tsysupload.flatid" /></th>   							
						<th width="120"><fmt:message key="tsysupload.fileUrl" /></th>
						<th width="120"><fmt:message key="tsysupload.createMan" /></th>   							
						<th width="120"><fmt:message key="tsysupload.createTime" /></th>
						<th width="120"><fmt:message key="tsysupload.remark" /></th>
					</tr>
				</thead>
				<c:forEach items="${list}" var="tsysupload" varStatus="paStatus">
					<tr>
						<td width="100" class="checkbox">
							<input type="checkbox" name="ckh" value="${tsysupload.uploadid}" />
						</td>    
						<td width="120">${tsysupload.secondName}</td>
						<td width="120">${tsysupload.fileName}</td>
						<td width="120">${tsysupload.flatid}</td>
						<td width="120">${tsysupload.fileUrl}</td>
						<td width="120">${tsysupload.createMan}</td>
						<td width="120">${tsysupload.createTime}</td>
						<td width="120">${tsysupload.remark}</td>
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
<div id="uploader" closed="true" title="上传文件" style="padding: 5px;width: 420px;height: 410px;">
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
