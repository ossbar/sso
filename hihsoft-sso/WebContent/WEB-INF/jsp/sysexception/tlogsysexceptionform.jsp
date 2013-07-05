 <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<html>
<head>
</head>
<script type="text/javascript">
	function save_click() {
		var frm = $("form[name='tlogSysexceptionForm']");
		frm.attr("action", "${ctx}/tlogSysexceptionController.do?method=save");
		frm.submit();
	}
	function back_click(){
		var frm = $("form[name='tlogSysexceptionForm']");
		frm.attr("action", "${ctx}/tlogSysexceptionController.do?method=list");
		frm.submit();
		}
</script>
<body>
	<hih:form bean="tlogSysexception" scope="request">
		<form action="/tlogSysexceptionController" method="post"
			name="tlogSysexceptionForm">
			<c:if test=" ${tlogSysexception!=null}">
				<input type="hidden" name="exception_oid" value=" ${tlogSysexception.exception_oid}"/>
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
						 						<td><fmt:message
								key="tlogsysexception.exceptionTimes" />
						</td>
						<td><input type="text" name="exceptionTimes"
							id="exceptionTimes" class="text" /><font color="red">**</font>
						</td>   
					</tr>
					<tr>
						 						<td><fmt:message
								key="tlogsysexception.exceptionLasttime" />
						</td>
						<td><input type="text" name="exceptionLasttime"
							id="exceptionLasttime" class="text" /><font color="red">**</font>
						</td>   
					</tr>
					<tr>
						 						<td><fmt:message
								key="tlogsysexception.exceptionName" />
						</td>
						<td><input type="text" name="exceptionName"
							id="exceptionName" class="text" /><font color="red">**</font>
						</td>   
					</tr>
					<tr>
						 						<td><fmt:message
								key="tlogsysexception.exceptionMsg" />
						</td>
						<td><input type="text" name="exceptionMsg"
							id="exceptionMsg" class="text" /><font color="red">**</font>
						</td>   
					</tr>
					<tr>
						 						<td><fmt:message
								key="tlogsysexception.exceptionClass" />
						</td>
						<td><input type="text" name="exceptionClass"
							id="exceptionClass" class="text" /><font color="red">**</font>
						</td>   
					</tr>
					<tr>
						 						<td><fmt:message
								key="tlogsysexception.userid" />
						</td>
						<td><input type="text" name="userid"
							id="userid" class="text" /><font color="red">**</font>
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
