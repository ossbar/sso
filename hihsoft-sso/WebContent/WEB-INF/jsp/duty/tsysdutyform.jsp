<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
</head>
<script type="text/javascript">
$(function(){
	var fn = function(){
		$("input[name='dutyname']").focus();
	};
	fn.defer(500);
	$("#parentDutyid").combotree({
		url : "${ctx}/tsysDutyController.do?method=getDutyTree"
	});
	$("#formID").validationEngine({
		showOnMouseOver : true
	});
})
</script>
<body>
<hih:form bean="tsysDuty" scope="request">
		<form action="/tsysDutyController" method="post" name="tsysDutyForm" id="formID">
			<c:if test="${tsysDuty!=null}">
				<input type="hidden" name="dutyid" value="${tsysDuty.dutyid}" />
			</c:if>
			<div>
				<table class="FormView" border="0" cellspacing="1" cellpadding="0">
					<col class="Label"/>
					<col class="Data" />
					<tr>
						<td><fmt:message key="tsysduty.dutyname" /></td>
						<td><input type="text" name="dutyname" id="dutyname" class="validate[required,maxSize[40]] text" /><font
							color="red">**</font></td>
					</tr>
					<tr>
						<td><fmt:message key="tsysduty.parentdutyid" /></td>
						<td><input type="text" name="parentDutyid" id="parentDutyid" style="width: 165px;"/><font color="red">**</font></td>
					</tr>
					<tr>
						<td><fmt:message key="tsysduty.remark" /></td>
						<td><input type="text" name="remark" id="remark" class="validate[required,maxSize[100]] text" /><font
							color="red">**</font></td>
					</tr>
					
					<tr>
						<td><fmt:message key="tsysduty.dutytype" /></td>
						<td><select name="dutytype" class="select" id="dutySort" style="width: 165px">
							<c:forEach items="${dutytype}" var="selectRole">
								<option value="${selectRole.parano}">${selectRole.paraKey}</option>
							</c:forEach>
				  		 </select><font
							color="red">**</font></td>
					</tr>
					
					<tr>
						<td><fmt:message key="tsysduty.dutysort" /></td>
						<td>
						<select name="dutySort" class="select" id="dutySort" style="width: 165px">
							<c:forEach items="${selectRole}" var="selectRole">
								<option value="${selectRole.parano}">${selectRole.paraKey}</option>
							</c:forEach>
				  		 </select>
						<!-- 根据当前用户级别进行控制 -->
						<%-- <c:choose>
							<c:when test="${isGroup}">
						 	<hih:parameter name="roleType" id="dutysort" cssClass="select"/>
							</c:when>
							<c:otherwise>
							<select name="dutysort" class="select" id="dutysort" style="width: 165px">
							<c:forEach items="${selectRole}" var="selectRole">
								<option value="${selectRole.parano}">${selectRole.paraKey}</option>
							</c:forEach>
				  		 </select>
						</c:otherwise>
						</c:choose> --%>
						</td>
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
