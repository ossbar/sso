<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
</head>
<script type="text/javascript">
	var baseUrl = "${ctx}/taclRoleController.do?method=list";
	function save_click() {
		var valid = $("form[name=taclRoleForm]").validationEngine('validate');
	  	if (!moduleTree || !valid) return;  
		var resualt=false;
		for(var i=0;i<document.taclRoleForm.roleSort.length;i++){
			if(document.taclRoleForm.roleSort[i].checked){
				resualt=true;
			}
		}
		if(!resualt){
			alert("请选择角色类型");
			return;
		}
		var modules = moduleTree.tree("getChecked");
		var moduleSet = [];
		for ( var i = 0; i < modules.length; i++) {
			var attr = modules[i].attributes || {};
			var obj = decode(attr);
			if (!obj.moduleid) continue; 
			moduleSet.push((obj.operid || "") + "," + (obj.moduleid || ""));
		}
		var orgid = $("input[name='orgid']").val();
		moduleSet = moduleSet.join(";");
		var params = $("form[name=taclRoleForm]").getFormParams();
		params["moduleSet"] = moduleSet;
		$.post("${ctx}/taclRoleController.do?method=save", params, function(data, status) {
				win.window("close");
				if (status == "success") {
					alert("保存成功", "info", function() {
						document.location = "${ctx}/taclRoleController.do?method=list";
					});
				} else {
					alert("保存失败");
				}
			}, "json");
	}
	function back_click() {
		document.location = "${ctx}/taclRoleController.do?method=list";
	}
	
	var moduleTree;
	$(function() {
		var roleId = $("input[name='roleid']").val();
		moduleTree = $("#moduleTree").tree({
			checkbox : true,
			url : "${ctx}/taclRoleController.do?method=getModuleTree&roleId=${taclRole.roleid}",
			onLoadSuccess : function(node, data) {
				var tree = $(this);
				if (${taclRole == null}) {
					var roots = $(this).tree("getRoots");
					for (var i = 0; i < roots.length; i++) {
						tree.tree("check", roots[i].target);
					}
				}
			}
		});
		(function(){
			$("input[name='rolename']").focus();
		}).defer(500);
		$("#treecontainer").layout();
		$("form[name=taclRoleForm]").validationEngine({
			showOnMouseOver : true
		});
	});
</script>
<body>
	<hih:form bean="taclRole" scope="request">
	<form action="/taclRoleController" method="post" name="taclRoleForm" style="width: 100%;height: 100%">
		<div id="treecontainer" fit="true" border="false">
			<div region="north" style="height: 100px;" border="false"
				title="角色信息" collapsible="false">
				<table class="FormView" border="0" cellspacing="1" cellpadding="0">
					<col class="Label" />
					<col class="Data" />
					<col class="Label" />
					<col class="Data" />
					<tr>
						<c:if test="${taclRole!=null}">
							<input type="hidden" name="roleid" value="${taclRole.roleid}"/>
							<input type="hidden" name="orgid" value="${taclRole.orgid}"/>
						</c:if>
					</tr>
					<tr>
						<td><fmt:message key="taclrole.rolename">
							</fmt:message>
						</td>
						<td><input type="text"  id="rolename" name="rolename" class="validate[required,maxSize[20]] text"
							value="${taclRole==null?"":taclRole.rolename}"/><font
							color="red">**</font></td>
							<td><fmt:message key="taclrole.rolesort">
						</fmt:message></td>
						<td>
						<c:choose>
							<c:when test="${isGroup}">
								<input type="radio" name="roleSort" value="1" />公共
								<input type="radio" name="roleSort" value="2" />私有
							</c:when>
							<c:otherwise>
								<input type="radio" name="roleSort" value="2" checked="checked"/>私有
								<input type="hidden" name="roleSort" value="" />
							</c:otherwise>
						</c:choose>
						</td>
					</tr>
					<tr>
						<td><fmt:message key="taclrole.remark">
							</fmt:message>
						</td>
						<td><textarea id="remark" name="remark" class="validate[maxSize[50]] textarea" cols="40"
								rows="2" style="height: 15px;width: 162px;"></textarea>
						</td>
						<td><fmt:message key="taclrole.roletype">
							</fmt:message>
						</td>
						<td>
						<c:choose>
							<c:when test="${isGroup}">
							<aft:parameter name="roleType" id="roletype" cssClass="select"/>
							</c:when>
							<c:otherwise>
							<select name="roletype" class="select" id="roletype" style="width: 165px">
							<c:forEach items="${selectRole}" var="selectRole">
								<option value="${selectRole.parano}">${selectRole.paraKey}</option>
							</c:forEach>
				  		 </select>
						</c:otherwise>
						</c:choose>
					</td>
					</tr>
				<%-- 	选择所属单位	<c:if test="${!isGroup}">
						<tr>
						<td><fmt:message key="taclrole.orgid"></fmt:message></td>
						<td>	
						<input id="orgid" name="orgid"
							style="width: 165px;" value="${taclRole==null?"":taclRole.orgid}"/> 
						</td>
					</tr>
					</c:if> --%>
				</table>
			</div>
			<div region="center" style="width: 200px;" split="true" title="操作权限">
				<ul id="moduleTree"></ul>
			</div>
		<div align="center" region="south" style="height: 40px;" border="false">
			<div class="tool-btn" id="btn_save" onclick="save_click()">
				<span class="icon-save">&nbsp;</span>
				<fmt:message key="button.save" />
			</div>
			<div class="tool-btn" id="btn_save" onclick="back_click()">
				<span class="icon-back">&nbsp;</span>
				<fmt:message key="button.back" />
			</div>
		</div>
		</div>
	</form>
	</hih:form>
</body>
</html>