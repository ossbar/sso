<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
$(function() {
	form = $("form");
	module = $("input[name='moduleid']");
	initGrid(form, {
		onpagefirst : detail,
		onpagelast : detail,
		onpageprev : detail,
		onpagenext : detail,
		onpagerefresh : detail,
		onpagesize : detail
	});

}); 
</script>
</head>

<body>
<form method="post" target="_self" action="${ctx}/tsysModuleinfoController.do?method=getTree" name="listForm" style="width: 100%;height: 100%;">
		<input type="hidden" name="moduleid" value="${moduleid}">
		<div id="grid-container" fit="true" border="false">
			<div id="grid-body" class="grid-body" region="center" border="false">
			 <hih:table items="${list}" var="item">
				<hih:column field="moduleid" checkbox="true"/>
				<hih:column field="modulename" header="tsysmoduleinfo.modulename" width="120"/>
				<hih:column field="moduleno" header="tsysmoduleinfo.moduleno" width="120"/>
				<hih:column field="moduleclass" header="tsysmoduleinfo.moduleclass" width="120"/>
				<hih:column field="linkedaddr" header="tsysmoduleinfo.linkedaddr" width="120"/>
				<hih:column field="moduledesc" header="tsysmoduleinfo.moduledesc" width="120"/>
			</hih:table>
			</div>
			<jsp:include page="/WEB-INF/jsp/common/page.jsp" />
		</div>
	</form>
</body>
</html>
