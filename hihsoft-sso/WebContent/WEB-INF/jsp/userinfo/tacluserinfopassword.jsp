<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
function checkpwd() {
	var old = $("input[name=newpassword]").val();
	var newpwd = $("input[name=newagainpassword]").val();
	var o = $("input[name=oldpassword]").val();
	if (!o) {
		alert("旧密码不能为空！");
		return false;
	}
	if (!newpwd || !old) {
		alert("新密码不能为空");
		return false;
	}
	if (old != newpwd) {
		alert("两次输入的密码不一致,请重新输入");
		return false;
	}
	return true;
}
function back_click() {
	if (win) win.window("close");
}
function save_click() {
	if (checkpwd()) {
		var param = $("form[name='pwdform']").getFormParams();
		$.request("${ctx}/tsysLoginController.do?method=changePwd", param, function(data, status) {
			if (!data.success) {
				alert(data.message, "error");
			} else {
				alert("修改密码成功！", "info", function() {
					win.window("close");
				});
			}
		}, function(data,status) {
			alert("无法访问服务器！" + status);
		});
	}
}
$(function() {
	$("#formID").validationEngine({
		showOnMouseOver : true
	});
	var newpwd = $("input[name=newagainpassword]");
	newpwd.blur(function() {
		checkpwd();
	});
});
</script>
</head>
<body>
	<div style="width: 100%;">
		<form name="pwdform" action="/TaclUserinfoController.do" method="post"
			id="formID">
			<div>
				<table class="FormView" border="0" cellspacing="1" cellpadding="0" width="100%" height="100%">
					<col class="Label" />
					<col class="Data" />
					<tr>
						<td><fmt:message key="tsysuserpassword.old"></fmt:message>
						</td>
						<td><input type="password" name="oldpassword"
							class="validate[required,custom[onlyLetterNumber],lenth[0,32]] text"
							id="oldpassword" /><font color="red">**</font>
						</td>
					</tr>
					<tr>
						<td><fmt:message key="tsysuserpassword.new"></fmt:message>
						</td>
						<td><input type="password" name="newpassword"
							class="text required,custom[noSpecialCaracters],length[0,32]"
							id="newpassword" /><font color="red">**</font>
						</td>
					</tr>
					<tr>
						<td><fmt:message key="tsysuserpassword.newagain"></fmt:message>
						</td>
						<td><input type="password" name="newagainpassword"
							class="text required,custom[noSpecialCaracters],length[0,32]"
							id="newagainpassword" /><font color="red">**</font>
						</td>
					</tr>
					<tr>
						<td>&nbsp;&nbsp;</td>
						<td><input type="button" value="保存" onclick="save_click()">
							<input type="button" value="取消" onclick="back_click()">
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
</body>
</html>
