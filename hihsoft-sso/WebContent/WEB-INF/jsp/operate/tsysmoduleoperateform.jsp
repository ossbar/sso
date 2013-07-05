<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<html>
<head>
</head>
<script type="text/javascript">
$(function(){
	var fn = function(){
		$("input[name='operatename']").focus();
	};	
	fn.defer(500);
	
	$("#formID").validationEngine({
		showOnMouseOver : true
	});
});
function back_click() {
	 win.window("close");
}
</script>
<body>
	<hih:form bean="tsysModuleoperate" scope="request">
		<form name="tsysModuleoperateForm" method="post" id="formID">
			<c:if test="${tsysModuleoperate!=null}">
				<input type="hidden" name="operateid"
					value="${tsysModuleoperate.operateid}" />
			</c:if>
			<input type="hidden" name="moduleid" value="${moduleid}" />
			<div>
				<table class="FormView" border="0" cellspacing="1" cellpadding="0">
					<col class="Label" />
					<col class="Data" />
					<tr>
						<td><fmt:message key="tsysmoduleoperate.operatename" /></td>
						<td><input type="text" name="operatename" id="operatename" class="validate[required,maxSize[15]] text" /><font
							color="red">**</font></td>
					</tr>
					<tr>
						<td><fmt:message key="tsysmoduleoperate.operateno" /></td>
						<td><input type="text" name="operateno" id="operateno" class="validate[required,maxSize[50],custom[onlyLetterSp]] text" /><font
							color="red">**</font></td>
					</tr>
					<tr>
						<td><fmt:message key="tsysmoduleoperate.operateonlycode" />
						</td>
						<td><input type="text" name="operateonlycode" id="operateonlycode" class="validate[maxSize[25]] text" />
						</td>
					</tr>
				</table>
			</div>
		</form>
				<div align="center" class="foot-buttons">
			<div class="tool-btn" id="btn_save" onclick="save_click()">
				<span class="icon-save">&nbsp;</span>
				<fmt:message key="button.save" />
			</div>
			<div class="tool-btn" id="btn_save" onclick="back_click()">
				<span class="icon-back">&nbsp;</span>
				<fmt:message key="button.back" />
			</div>
		</div>
	</hih:form>
</body>
</html>
