<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
</head>
<script type="text/javascript">
	function save_click() {
		document.forms[0].action = "${ctx}/taclRoleController.do?method=save";
		document.forms[0].submit();
	}
	function back_click() {
		document.forms[0].action = "${ctx}/taclRoleController.do?method=list";
		document.forms[0].submit();
	}
</script>
<body>
	<div style="width: 98%; margin: auto">
		<form action="/taclRoleController" method="post">
			<div>
				<table class="workAreaTitleBg" border="0" cellpadding="0"
					cellspacing="0">
					<tr>
						<td class="cell01">&nbsp;</td>
						<td class="cell02"><b><fmt:message
									key="taclrole.form.remark" />
						</b>
						</td>
						<td class="cell03"></td>
						<td class="cell04"><input type="button"
							class="button SaveRecord"
							value="<fmt:message key="button.save"/>" onclick="save_click()">
							<input type="button" class="button BackRecord"
							value="<fmt:message key="button.back"/>" onclick="back_click()">
						</td>
						<td class="cell05"></td>
					</tr>
				</table>
			</div>
			<div>
				<table class="FormView" border="0" cellspacing="1" cellpadding="0">
					<col class="Label" />
					<col class="Data" />
					<col class="Label" />
					<col class="Data" />
					<tr>
						<td><fmt:message key="taclrole.rolename"></fmt:message>
						</td>
						<td><input type="text" name="rolename" class="text" /><font
							color="red">**</font>
						</td>
					</tr>
					<tr>
						<td><fmt:message key="taclrole.remark"></fmt:message>
						</td>
						<td><textarea name="remark" class="textarea" cols="20"
								rows="4"></textarea>
						</td>
					</tr>
				</table>
			</div>
	</div>
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
	</form>
</body>
</html>
