<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<script type="text/javascript">
	$(function() {
		var loaded=false;
		$("#detail-container").tabs({
			border : false,
			fit:true
		});
	});
</script>
</head>
<body>
	<div id="detail-container">
		<div title="组件介绍" id="details" selected="true">
			<iframe src="${ctx}/uiFileUploadDemoController.do?method=details" style="border: 0;width: 100%;height: 100%"></iframe>
		</div>
		<div title="组件演示" id="run">
			<iframe src="${ctx}/fileUploadController.do?method=forward" style="border: 0;width: 100%;height: 100%"></iframe>
		</div>
	</div>
</body>
</html>
