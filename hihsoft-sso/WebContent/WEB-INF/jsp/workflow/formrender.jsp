<%@page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<c:if test="${fields.size() == 0}">
	<jsp:forward page="${ctx}/ActReProcdefController.do?method=start&id=${id}"/>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/title.jsp"%>
<script type="text/javascript">
$(function() {
	$("#btn_save").click(function(){
		var params = $("form[name=processForm]").getFormParams();
		$.post("${ctx}/workFlowController.do", params, function(data) {
			if (data && data.success) {
				win.window("close");
				alert("操作成功");
			} else {
				alert("操作失败");
			}
		});
	});
	$("#btn_cancel").click(function(){
		win.window("close");
	});
});

</script>
</head>
<body>
	<form action="${ctx}/workFlowController.do" method="post" name="processForm" id="formID">
		<input type="hidden" name="method" value="${type}">
		<input type="hidden" name="id" value="${id}">
		<div>
			<table class="FormView" border="0" cellspacing="1" cellpadding="0">
				<col class="Label" />
				<col class="Data" />
				<col class="Label" />
				<col class="Data" />
				<c:forEach items="${fields}" var="field">
				<tr>
					<td>${field.name}：</td>
					<td>
					<c:choose>
						<c:when test='${field.type.name==null || field.type.name=="string" || field.type.name=="long"}'>
							<input type="text" name="flow_${field.id}" ${field.writable ? "" : "readonly"} class="text" value="${field.value}"/>
						</c:when>
						<c:when test='${field.type.name == "date"}'>
							<input type="text" name="flow_${field.id}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly  value="${field.value}"/>
						</c:when>
						<c:when test='${field.type.name == "enum"}'>
							<select name="flow_${field.id}">
								<c:forEach items='${field.type.getInformation("values")}' var="opt">
								<option value="${opt.key}">${opt.value}</option>	
								</c:forEach>
							</select>
						</c:when>
					</c:choose>
					<c:if test="${field.required}"><font color="red">*</font></c:if>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</form>
	<div align="center" class="foot-buttons">
		<div class="tool-btn" id="btn_save">
			<span class="icon-save">&nbsp;</span>
			<fmt:message key="button.save" />
		</div>
		<div class="tool-btn" id="btn_cancel">
			<span class="icon-back">&nbsp;</span>
			<fmt:message key="button.back" />
		</div>
	</div>
</body>
</html>