<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
</head>
<script type="text/javascript">
function showuser(){
	showUserWindow(function(rows,win){
		if(rows.length > 0){
			win.window("close");
			var i=0;
			$("#username").val("");
			$("#userId").val("");
			for(i=0;i<rows.length;i++){
			$("#username").val(rows[i].username);
			$("#userId").val(rows[i].userid);
		}
	}},{excludes : "2"});
}
$(function(){
	var fn = function(){
		$("input[name='flatcode']").focus();
	};
	fn.defer(500);
	$("#formID").validationEngine({
		showOnMouseOver : true
	});
})
	function save_click() {
		if ($("#formID").validationEngine('validate')) {
			var frm = $("form[name='tsysFlatForm']");
			frm.attr("action", "${ctx}/tsysFlatController.do?method=save");
			frm.submit();
		}
	}
	function back_click() {
		document.location = "${ctx}/tsysFlatController.do?method=list";
	}
</script>
<script>
	$(document).ready(function() {
		$("#formID").validationEngine({
			showOnMouseOver : true
		});
	});
</script>
<body>
	<hih:form bean="tsysFlat" scope="request">
		<form action="/tsysFlatController" method="post" name="tsysFlatForm"
			id="formID">
			<c:if test="${tsysFlat!=null}">
				<input type="hidden" name="flatid" value="${tsysFlat.flatid}" />
			</c:if>
			<div>
				<table class="FormView" border="0" cellspacing="1" cellpadding="0">
					<col class="Label" />
					<col class="Data" />
					<tr>
						<td><fmt:message key="tsysflat.flatcode" /></td>
						<td><input type="text" id="flatcode" name="flatcode"
							class="validate[required,maxSize[10],custom[onlyLetterNumber]] text" /><font
							color="red">**</font></td>
					</tr>
					<tr>
						<td><fmt:message key="tsysflat.flatname" /></td>
						<td><input type="text" id="flatname" name="flatname"
							class="text validate[required,maxSize[25]]" /><font color="red">**</font>
						</td>
					</tr>
					<tr>
						<td><fmt:message key="tsysflat.shortname" /></td>
						<td><input type="text" id="shortname" name="shortname"
							class="text validate[required,maxSize[15]]" /><font color="red">**</font>
						</td>
					</tr>
					<tr>
						<td><fmt:message key="tsysflat.flaturl" /></td>
						<td><input type="text" id="flaturl" name="flaturl"
							class="validate[required,maxSize[100]] text" /><font
							color="red">**</font></td>
					</tr>
					<tr>
						<td><fmt:message key="tsysflat.flatdesc" /></td>
						<td><input type="text" name="flatdesc"
							class="text validate[required,maxSize[2]]"
							id="flatdesc" /><font color="red">**</font></td>
					</tr>
					<tr>
						<td><fmt:message key="tsysflat.remark" /></td>
						<td><textarea name="remark"
								class="validate[maxSize[50]] textarea" cols="30" id="remark"
								rows="4"></textarea></td>
					</tr>
				</table>
			</div>
		</form>
		<div align="center" class="foot-buttons">
			<div class="tool-btn" id="btn_save" onclick="save_click()">
				<span class="icon-save">&nbsp;</span>
				<fmt:message key="button.save" />
			</div>
			<input type="button" class="button BackRecord" value="选择用户" onclick="showuser()">
			<div class="tool-btn" id="btn_save" onclick="back_click()">
				<span class="icon-back">&nbsp;</span>
				<fmt:message key="button.back" />
			</div>
		</div>
	</hih:form>
</body>
</html>
