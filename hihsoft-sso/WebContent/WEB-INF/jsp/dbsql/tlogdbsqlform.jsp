 <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<html>
<head>
</head>
<script type="text/javascript">
	function save_click() {
		var frm = $("form[name='tlogDbsqlForm']");
		frm.attr("action", "${ctx}/tlogDbsqlController.do?method=save");
		frm.submit();
	}
	function back_click(){
		var frm = $("form[name='tlogDbsqlForm']");
		frm.attr("action", "${ctx}/tlogDbsqlController.do?method=list");
		frm.submit();
		}
</script>
<body>
	<hih:form bean="tlogDbsql" scope="request">
		<form action="/tlogDbsqlController" method="post"
			name="tlogDbsqlForm">
			<c:if test=" ${tlogDbsql!=null}">
				<input type="hidden" name="database_oid" value=" ${tlogDbsql.database_oid}"/>
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
								key="tlogdbsql.tableName" />
						</td>
						<td><input type="text" name="tableName"
							id="tableName" class="text" /><font color="red">**</font>
						</td>   
					</tr>
					<tr>
						 						<td><fmt:message
								key="tlogdbsql.tableOperate" />
						</td>
						<td><input type="text" name="tableOperate"
							id="tableOperate" class="text" /><font color="red">**</font>
						</td>   
					</tr>
					<tr>
						 						<td><fmt:message
								key="tlogdbsql.tableAccessTimes" />
						</td>
						<td><input type="text" name="tableAccessTimes"
							id="tableAccessTimes" class="text" /><font color="red">**</font>
						</td>   
					</tr>
					<tr>
						 						<td><fmt:message
								key="tlogdbsql.tableAccessLastTime" />
						</td>
						<td><input type="text" name="tableAccessLastTime"
							id="tableAccessLastTime" class="text" /><font color="red">**</font>
						</td>   
					</tr>
					<tr>
						 						<td><fmt:message
								key="tlogdbsql.columes" />
						</td>
						<td><input type="text" name="columes"
							id="columes" class="text" /><font color="red">**</font>
						</td>   
					</tr>
					<tr>
						 						<td><fmt:message
								key="tlogdbsql.userid" />
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
