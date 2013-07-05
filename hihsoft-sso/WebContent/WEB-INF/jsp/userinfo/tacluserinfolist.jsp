 <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
$(function(){
	form = $("form[name=listForm]");
	org = form.find("input[name='orgid']");
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
<form method="post" target="_self" action="${ctx}/taclUserinfoController.do?method=list" name="listForm"  style="width: 100%;height: 100%;">
	<input type="hidden" name="orgid" value="${orgid}">
	<div id="grid-container" fit="true" align="center" border="false">
		<div id="grid-body" class="grid-body" region="center" border="false" align="left">
			<table class="data-grid" cellspacing="0" cellpadding="0">
				<thead>
					<tr class="data-grid-header">
						<th width="35"><input type="checkbox" id="selectAll" /></th>
						<th width="120"><fmt:message key="tacluserinfo.loginname" /></th>
						<th width="120"><fmt:message key="tacluserinfo.username" /></th>
						<th width="60"><fmt:message key="tacluserinfo.usertype" /></th>
						<th width="90"><fmt:message key="tacluserinfo.dutyid" /></th>
						<th width="80"><fmt:message key="tacluserinfo.mobile" /></th>
						<th width="100"><fmt:message key="tacluserinfo.idcard" /></th>
						<th width="60"><fmt:message key="tacluserinfo.sex" /></th>
						<th width="60"><fmt:message key="tacluserinfo.userstate" /></th>
						<th width="80"><fmt:message key="tacluserinfo.birthday" /></th>
						<th width=""><fmt:message key="tacluserinfo.userEmail" /></th>
						</tr>
				</thead>
				<c:forEach items="${list}" var="tacluserinfo" varStatus="paStatus">
					<tr>
						<td class="checkbox"><input type="checkbox"
							name="ckh" value="${tacluserinfo.userid}" /></td>
						<td width="120"><a href="#" onclick="showUser('${tacluserinfo.userid}')">${tacluserinfo.loginname}</a></td>
						<td>${tacluserinfo.username}</td>
						<td>${tacluserinfo.usertype}</td>
						<td>${tacluserinfo.tsysDuty.dutyname}</td>
						<td>${tacluserinfo.mobile}</td>
						<td>${tacluserinfo.idcard}</td>
						<td>${tacluserinfo.sex}</td>
						<td>${tacluserinfo.userstate}</td>
						<td>${tacluserinfo.birthday}</td>
						<td>${tacluserinfo.userEmail}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<jsp:include page="/WEB-INF/jsp/common/page.jsp" />
	</div>
</form>
</body>
</html>
