 <%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<html>
<head>
</head>
<script type="text/javascript">
	function save_click() {
		var frm = $("form[name='tlogBusinesslogForm']");
		frm.attr("action", "${ctx}/tlogBusinesslogController.do?method=save");
		frm.submit();
	}
	function back_click(){
		var frm = $("form[name='tlogBusinesslogForm']");
		frm.attr("action", "${ctx}/tlogBusinesslogController.do?method=list");
		frm.submit();
		}
</script>
<body>
	<hih:form bean="tlogBusinesslog" scope="request">
		<form action="/tlogBusinesslogController" method="post"
			name="tlogBusinesslogForm">
			<c:if test=" ${tlogBusinesslog!=null}">
				<input type="hidden" name="logid" value=" ${tlogBusinesslog.logid}"/>
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
