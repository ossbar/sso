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
		<hih:table items="${list}" var="tacluserinfo">
				<hih:column field="userid" checkbox="true" />
				<hih:column field="loginname" header="tacluserinfo.loginname" width="120">
				<a href="#" onclick="showUser('${tacluserinfo.userid}')">${tacluserinfo.loginname}</a>
				</hih:column>
				<hih:column field="username" header="tacluserinfo.username" width="120"/>
				<hih:column field="usertype" header="tacluserinfo.usertype" width="120"/>
				<hih:column field="dutyid" header="tacluserinfo.dutyid" width="120"/>
				<hih:column field="mobile" header="tacluserinfo.mobile" width="120"/>
				<hih:column field="idcard" header="tacluserinfo.idcard" width="120"/>
				<hih:column field="sex" header="tacluserinfo.sex" width="120"/>
				<hih:column field="userstate" header="tacluserinfo.userstate" width="120"/>
				<hih:column field="birthday" header="tacluserinfo.birthday" width="120"/>
				<hih:column field="userEmail" header="tacluserinfo.userEmail" width="120"/>
			</hih:table>
		</div>
	<jsp:include page="/WEB-INF/jsp/common/page.jsp" />
	</div>
</form>
</body>
</html>
