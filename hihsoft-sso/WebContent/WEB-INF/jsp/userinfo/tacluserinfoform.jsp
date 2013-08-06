<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
</head>
<script type="text/javascript">
var dataTree;
$(function() {
	var fn = function(){
		$("input[name='loginname']").focus();
	};
	fn.defer(500);
	$("#formID").validationEngine({
		showOnMouseOver : true
	});
	
	var userId = "${taclUserinfo.userid}";
	var orgId = $("#orgid").val();
	dataTree = $("#dataTree").tree({
		checkbox : true,
		url: "${ctx}/taclUserinfoController.do?method=getOrgTree&userId=" + (userId || ""),
		includeHalf : false,
		onLoadSuccess : function(node, data) {
			console.log(data);
			if (data && data.length > 0) {
				$("#orgid").combotree({
					data : data,
					width : 165,
					disabled : userId
				});
				if (!userId && orgId) {
					var n = dataTree.tree("find", orgId);
					if (n) {
						dataTree.tree("check", n.target);
					}
				}
			}
		}
	});
	$("#treecontainer").layout();
	$("#userinfoDiv").layout();
	var orgname = "${org!=null?org.orgname:""}";
	orgname = orgname ?("<fmt:message key="tacluserinfo.orgid" />:"+orgname):"";
	var title = orgname;
	$("#win").window("setTitle", title);
	var op = $("#optional").find("td");
	$("#required").find("td").height(23).each(function(i,td) {
		$(op[i]).height($(td).height());
	});
	$("#dutyId").combotree({
		url : "${ctx}/taclUserinfoController.do?method=getDutyTree&userId=" + userId,
		cascadeCheck : false,
		multiple : true
	});
});
function back_click() {
	win.window("close");
}
</script>
<body>
<hih:form bean="taclUserinfo" scope="request">
	<form action="/taclUserinfoController" method="post" name="taclUserinfoForm" id="formID">
		<c:if test="${taclUserinfo!=null}">
			<input type="hidden" name="userid" value="${taclUserinfo.userid}" />
		</c:if>
		<div id="treecontainer" style="width: 100%; height: 465px;border: none;" region="center">
			<div region="north" style="height: 305px;border: none;" id="userinfoDiv">
				<div region="center" border="false" title="用户信息(必填)">
					<table class="FormView" border="0" cellspacing="1" cellpadding="0" id="required">
						<col class="Label" />
						<col class="Data" />
						<tr>
							<td><fmt:message key="tacluserinfo.loginname" /></td>
							<td><input type="text" name="loginname" id="loginname"
								class="validate[required,maxSize[10],ajax[ajaxNameCall]] text" old="${taclUserinfo.loginname}" ${taclUserinfo != null ? "readonly" : ""}/><font color="red">**</font><br/>
							</td>
						</tr>
						<tr style="display: none;">
							<td><fmt:message key="tacluserinfo.userpw" /></td>
							<td>****<input type="password" name="userpw" id="userpw"/></td>
						</tr>
						<tr>
							<td><fmt:message key="tacluserinfo.username" /></td>
							<td><input type="text" name="username" id="username"
								class="validate[required,maxSize[15]] text" /><font color="red">**</font>
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tacluserinfo.userNo" /></td>
							<td><input type="text" name="userNo" id="userNo"
								class="validate[required,maxSize[20]] text" /><font color="red">**</font>
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tacluserinfo.usertype" /></td>
							<td style="height: 23px;"><hih:parameter name="userType" id="usertype" cssClass="select"/></td>
						</tr>
						<tr>
							<td><fmt:message key="tacluserinfo.dutyid" /></td>
							<td style="height: 23px;">
								<input name="dutyid" id="dutyId" style="width: 165px;" type="text">
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tacluserinfo.mobile" /></td>
							<td><input type="text" name="mobile" id="mobile"
								class="validate[required,maxSize[12],custom[mobile]] text" /><font
								color="red">**</font></td>
						</tr>
						<tr>
							<td><fmt:message key="tacluserinfo.idcard" /></td>
							<td><input type="text" name="idcard" id="idcard"
								class="validate[required,maxSize[18],custom[idNumber]] text" /><font
								color="red">**</font></td>
						</tr>
						<tr>
							<td><fmt:message key="tacluserinfo.userEmail" /></td>
							<td><input type="text" name="userEmail" id="userEmail"
								class="text" /></td>
						</tr>
						<tr>
							<td><fmt:message key="tacluserinfo.orgid" /></td>
							<td><input name="orgid" id="orgid" value="${taclUserinfo != null ? taclUserinfo.orgid : org.orgid}"/></td>
						</tr>
					</table>
				</div>
				<div region="east" border="false" title="用户信息(选填)" style="width: 325px;">
					<table class="FormView" border="0" cellspacing="1" cellpadding="0" id="optional">
						<col class="Label" />
						<col class="Data" />
						<tr>
							<td><fmt:message key="tacluserinfo.sex" />
							</td>
							<td>
							<hih:parameter name="sex" id="sex" type="radio" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tacluserinfo.birthday" />
							</td>
							<td><input type="text" name="birthday" id="birthday" class="validate[maxSize[8]] text" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tacluserinfo.telephone" />
							</td>
							<td><input type="text" name="telephone" id="telephone" class="validate[maxSize[30],custom[phone]] text" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tacluserinfo.certified" />
							</td>
							<td><input type="text" name="certified" id="certified" class="validate[maxSize[10]] text" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tacluserinfo.city" />
							</td>
							<td><input type="text" name="city" id="city" class="validate[maxSize[16]] text" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tacluserinfo.imagepath" />
							</td>
							<td><input type="text" name="imagepath" id="imagepath" class="validate[maxSize[100],custom[onlyLetterNumber]] text" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tacluserinfo.qq" />
							</td>
							<td><input type="text" name="qq" id="qq" class="validate[maxSize[20],custom[onlyNumberSp]] text" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tacluserinfo.msn" />
							</td>
							<td><input type="text" name="msn" id="msn" class="validate[maxSize[100]] text" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tacluserinfo.userstate" />
							</td>
							<td><hih:parameter name="userState" id="userstate" type="radio" defValue="1"/></td>
						</tr>
					</table>
				</div>
			</div>
			<div region="center" title="数据权限" border="false">
				<ul id="dataTree"></ul>
			</div>
		</div>
	</form>
	<div align="center" class="foot-buttons">
		<div class="tool-btn" id="btn_save" onclick="save_click()">
			<span class="icon-save">&nbsp;</span>
			<fmt:message key="button.save" />
		</div>
		<div class="tool-btn" id="btn_cancel" onclick="back_click()">
			<span class="icon-back">&nbsp;</span>
			<fmt:message key="button.back" />
		</div>
	</div>
</hih:form>
</body>
</html>
