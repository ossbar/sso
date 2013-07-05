 <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
</head>
<script type="text/javascript">
	function save_click() {
		var frm = $("form[name='tlogLoginlogForm']");
		frm.attr("action", "${ctx}/tlogLoginlogController.do?method=save");
		frm.submit();
	}
	function back_click(){
		var frm = $("form[name='tlogLoginlogForm']");
		frm.attr("action", "${ctx}/tlogLoginlogController.do?method=list");
		frm.submit();
		}
</script>
<body>
<hih:form bean="tlogLoginlog" scope="request">
	<div class="form-body">
		<form action="/tlogLoginlogController" method="post" name="tlogLoginlogForm">
			<c:if test="${tlogLoginlog!=null}">
				<input type="hidden" name="logid" value="${tlogLoginlog.logid}"/>
			</c:if>
			<div>
				<table class="FormView" border="0" cellspacing="1" cellpadding="0">
					<col class="Label" />
					<col class="Data" />
					<col class="Label" />
					<col class="Data" />
					<tr>
					</tr>
					<tr>
						<td><fmt:message key="tlogloginlog.ipaddr"/></td>
						<td><input type="text" name="ipaddr" class="text" /><font color="red">**</font></td> 
					</tr>
					<tr>
						<td><fmt:message key="tlogloginlog.logintime"/></td>
						<td><input type="text" name="logintime" class="text" /><font color="red">**</font></td> 
					</tr>
					<tr>
						<td><fmt:message key="tlogloginlog.logouttime"/></td>
						<td><input type="text" name="logouttime" class="text" /><font color="red">**</font></td> 
					</tr>
					<tr>
						<td><fmt:message key="tlogloginlog.onlinetime"/></td>
						<td><input type="text" name="onlinetime" class="text" /><font color="red">**</font></td> 
					</tr>
					<tr>
						<td><fmt:message key="tlogloginlog.userid"/></td>
						<td><input type="text" name="userid" class="text" /><font color="red">**</font></td> 
					</tr>
					<tr>
						<td><fmt:message key="tlogloginlog.logout"/></td>
						<td><input type="text" name="logout" class="text" /><font color="red">**</font></td> 
					</tr>
					<tr>
						<td><fmt:message key="tlogloginlog.orgid"/></td>
						<td><input type="text" name="orgid" class="text" /><font color="red">**</font></td> 
					</tr>
				</table>
			</div>
		</form>
	</div>
	<div align="center" class="foot-buttons">
		<input type="button" class="button SaveRecord" value="<fmt:message key="button.save"/>" onclick="save_click()">
		<input type="button" class="button BackRecord" value="<fmt:message key="button.back"/>" onclick="back_click()">
	</div>
</hih:form>
</body>
</html>
