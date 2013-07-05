 <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<html>
<head>
</head>
<script type="text/javascript">
	function save_click() {
		var frm = $("form[name='tlogAuditForm']");
		frm.attr("action", "${ctx}/tlogAuditController.do?method=save");
		frm.submit();
	}
	function back_click(){
		var frm = $("form[name='tlogAuditForm']");
		frm.attr("action", "${ctx}/tlogAuditController.do?method=list");
		frm.submit();
		}
</script>
<body>
<hih:form bean="tlogAudit" scope="request">
	<form action="/tlogAuditController" method="post" name="tlogAuditForm">
		<c:if test="${tlogAudit!=null}">
			<input type="hidden" name="aduitid" value="${tlogAudit.aduitid}"/>
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
				</tr>
				<tr>
				</tr>
				<tr>
				</tr>
				<tr>
				</tr>
				<tr>
				</tr>
				<tr>
				</tr>
				<tr>
				</tr>
			</table>
		</div>
	</form>
	<div align="center" class="foot-buttons">
		<input type="button" class="button SaveRecord" value="<fmt:message key="button.save"/>" onclick="save_click()">
		<input type="button" class="button BackRecord" value="<fmt:message key="button.back"/>" onclick="back_click()">
	</div>
</hih:form>
</body>
</html>
