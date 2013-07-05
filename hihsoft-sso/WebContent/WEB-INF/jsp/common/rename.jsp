 <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<html>
<head>
</head>
<script type="text/javascript">
function save_click() {
	var frm = $("form[name='tsysUploadForm']");
	frm.attr("action", "${ctx}/fileUploadController.do?method=save");
	frm.submit();
}
function back_click(){
}
</script>
<body>
	<form action="/fileUploadController" method="post" name="tsysUploadForm">
		<input type="hidden" name="count" value="${count}"/>
		<div style="height: 314px;">
			<table class="FormView" border="0" cellspacing="1" cellpadding="0">
				<col class="Label" />
				<col class="Data" />
				<c:forEach var="file" items="${files}" varStatus="stat">
				<tr>
					<td align="right"><fmt:message key="tsysupload.secondName"/><input type="hidden" name="id${stat.count}" value="${file.uploadid}"/></td>
					<td><input type="text" name="secondName${stat.count}" class="text" value="${file.fileName}"/><font color="red">**</font></td> 
				</tr>
				<tr>
					<td align="right"><fmt:message key="tsysupload.flatid"/></td>
					<td><select name="flatid${stat.count}" class="select">
							<c:forEach var="flat" items="${flats}">
							<option value="${flat.flatid}">${flat.flatname}</option>
							</c:forEach>
						</select> </td> 
				</tr>
				<tr>
					<td align="right"><fmt:message key="tsysupload.remark"/></td>
					<td><textarea cols="5" rows="6" class="textarea" name="remark${stat.count}">${file.remark}</textarea></td> 
				</tr>
				</c:forEach>
			</table>
		</div>
	</form>
	<div align="center" class="foot-buttons">
		<input type="button" class="button SaveRecord" value="<fmt:message key="button.save"/>" onclick="save_click()">
		<input type="button" class="button BackRecord" value="<fmt:message key="button.back"/>" onclick="back_click()">
	</div>
</body>
</html>
