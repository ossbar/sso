<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
$(function(){
	form = $("form[name=listForm]");
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
	<form method="post" target="_self" action="${ctx}/tsysModuleoperateController.do?method=list" name="listForm" style="width: 100%;height: 100%;">
		<input type="hidden" name="moduleid" value="${moduleid}">
		<div id="grid-container" fit="true" border="false">
			<div id="grid-body" class="grid-body" region="center" border="false">
				<table class="data-grid" cellspacing="0" cellpadding="0">
					<thead>
						<tr class="data-grid-header">
							<th><input type="checkbox" id="selectAll" />
							</th>
							<th width="200" ><fmt:message key="tsysmoduleoperate.operatename" /></th>
							<th width="200" ><fmt:message key="tsysmoduleoperate.operateno" /></th>
							<th width="200" ><fmt:message key="tsysmoduleoperate.operateonlycode" /></th>
					</tr>
				</thead>
				<c:forEach items="${list}" var="oper" varStatus="index">
					<tr>
						<td width="100" class="checkbox">
						<input type="checkbox" name="chk" value="${oper.operateid}" />
						</td>
						<td width="200">${oper.operatename }</td>
						<td width="200">${oper.operateno }</td>
						<td width="200">${oper.operateonlycode }</td>
					</tr>
				</c:forEach>
				</table>
			</div>
			<jsp:include page="/WEB-INF/jsp/common/page.jsp" />
		</div>
	</form>
</body>
</html>