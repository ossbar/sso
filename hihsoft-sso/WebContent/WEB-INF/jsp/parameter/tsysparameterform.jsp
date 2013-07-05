
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
</head>
<script type="text/javascript">
$(function(){
	var fn = function(){
		$("input[name='paraname']").focus();
	};
	fn.defer(500);
	$("#formID").validationEngine({
		showOnMouseOver : true
	});
})
</script>
<body>
	<hih:form bean="tsysParameter" scope="request">
		<form action="${ctx}/tsysParameterController.do?method=save" method="post"
			name="tsysParameterForm" id="formID">
			<div>
				<table class="FormView" border="0" cellspacing="1" cellpadding="0">
					<col class="Label" />
					<col class="Data" />
						<tr>
						<td><fmt:message key="tsysparameter.paraname">
							</fmt:message> <c:if test="${tsysParameter!=null}">
								<input type="hidden" name="paraid" />
							</c:if>
						</td>
						<td><input type="text" name="paraname" id="paraname" class="validate[required,maxSize[10]] text" /><font
							color="red">**</font></td>
					</tr>
					<tr>
						<td><fmt:message key="tsysparameter.parano">
							</fmt:message>
						</td>
						<td><input type="text" name="parano" id="parano" class="validate[required,maxSize[100]] text" /><font
							color="red">**</font></td>
					</tr>
					<tr>
						<td><fmt:message key="tsysparameter.paraclass">
							</fmt:message>
						</td>
						<td><select name="paraClass">
								<option value="0">系统级</option>
								<option value="1">自定义级</option>
						</select>
						</td>
					</tr>
					<tr>
						<td><fmt:message key="tsysparameter.parakey">
							</fmt:message>
						</td>
						<td><input type="text" name="paraKey" id="paraKey" class="validate[required,maxSize[30]] text" /><font
							color="red">**</font></td>
					</tr>
					<tr>
						<td><fmt:message key="tsysparameter.paratype">
							</fmt:message>
						</td>
						<td><input type="text" name="paraType" id="paraType" class="validate[required,maxSize[20]] text" /><font
							color="red">**</font></td>
					</tr>
					<tr>
						<td><fmt:message key="tsysparameter.paraorder">
							</fmt:message>
						</td>
						<td><input type="text" name="paraOrder" class="text" />**</td>
					</tr>
					<tr>
						<td><fmt:message key="tsysparameter.isdefault">
							</fmt:message>
						</td>
						<td><input type="checkbox" name="isdefault" value="1" /></td>
					</tr>
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
	</hih:form>
</body>
</html>
