
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
	$(function() {
		var mtree = $("#moduleTree"),mtreeInit = false, dtree = $("#dataTree"),dtreeInit = false;
		var check = function(node, checked) {
			 if (node.target.disabled) {
				 return false;
			} 
		}
		$("#detail-container").tabs({
			border : false,
			onSelect : function(title) {
				if (title == "操作权限" && !mtreeInit) {
					mtree.html("加载中，请稍候...");
					mtree.tree({
						checkbox : true,
						url : "${ctx}/taclUserinfoController.do?method=getModuleTree&userId=${taclUserinfo.userid}",
						onLoadSuccess : function(node, data) {
							if (data && data.length > 0) {
								//disable(mtree, data);
							}
						},
						onCheck : check
					});
					mtreeInit = true;
				} else if (title == "数据权限" && !dtreeInit) {
					dtree.html("加载中，请稍候...");
					dtree.tree({
						checkbox : true,
						url : "${ctx}/taclUserinfoController.do?method=getOrgTree&userId=${taclUserinfo.userid}",
						onLoadSuccess : function(node, data) {
							if (data && data.length > 0) {
								disable(dtree, data);
							}
						},
						onCheck : check
					});
					dtreeInit = true;
				}
			}
		});
		var fn = function(tree, n) {
			var find = tree.tree("find",n.id);
			find.target.disabled = true;
			$.each(n.children || [], function(i, node) {
				fn(tree, node);
			});
		};
		var disable = function(tree, data) {
			if (tree == mtree) {
				var checked = tree.tree("getChecked");
				$.each(checked, function(i,n) {
					tree.tree("check", n.target);
				});
			}
			$.each(data, function(i, n){
				fn(tree, n);
			}); 
		}
	});
</script>
</head>
<body>
	<div id="detail-container">
		<div region="center" title="用户基本信息" selected="true">
			<table class="FormView" border="0" cellspacing="1" cellpadding="0">
				<col class="Label" />
				<col class="Data" />
				<col class="Label" />
				<col class="Data" />
				<tr>
				</tr>
				<tr>
					<td><fmt:message key="tacluserinfo.loginname" />
					</td>
					<td>${taclUserinfo.loginname}</td>
					<td><fmt:message key="tacluserinfo.userpw" />
					</td>
					<td>******</td>
				</tr>
				<tr>
					<td><fmt:message key="tacluserinfo.username" />
					</td>
					<td>${taclUserinfo.username}</td>
					<td><fmt:message key="tacluserinfo.usertype" />
					</td>
					<td>${taclUserinfo.usertype}</td>
				</tr>
				<tr>
					<td><fmt:message key="tacluserinfo.sex" />
					</td>
					<td>${taclUserinfo.sex}</td>
					<td><fmt:message key="tacluserinfo.orgid" />
					</td>
					<td>${taclUserinfo.tsysOrg.orgname}</td>
				</tr>
				<tr>
					<td><fmt:message key="tacluserinfo.telephone" />
					</td>
					<td>${taclUserinfo.telephone}</td>
					<td><fmt:message key="tacluserinfo.mobile" />
					</td>
					<td>${taclUserinfo.mobile}</td>
				</tr>
				<tr>
					<td><fmt:message key="tacluserinfo.idcard" />
					</td>
					<td>${taclUserinfo.idcard}</td>
					<td><fmt:message key="tacluserinfo.userEmail" />
					</td>
					<td>${taclUserinfo.userEmail}</td>
				</tr>
				<tr>
					<td><fmt:message key="tacluserinfo.userstate" />
					</td>
					<td>${taclUserinfo.userstate}</td>
					<td><fmt:message key="tacluserinfo.dutyid" />
					</td>
					<td>
					<%-- ${taclUserinfo.tsysDuty.dutyname} --%>
					${dutySetName}
					</td>
					
				</tr>
				<tr>
					<td><fmt:message key="tacluserinfo.certified" />
					</td>
					<td>${taclUserinfo.certified}</td>
					<td><fmt:message key="tacluserinfo.birthday" />
					</td>
					<td>${taclUserinfo.birthday}</td>
				</tr>
				<tr>
					<td><fmt:message key="tacluserinfo.city" />
					</td>
					<td>${taclUserinfo.city}</td>
					<td><fmt:message key="tacluserinfo.imagepath" />
					</td>
					<td>${taclUserinfo.imagepath}</td>
				</tr>
				<tr>
					<td><fmt:message key="tacluserinfo.qq" />
					</td>
					<td>${taclUserinfo.qq}</td>
					<td><fmt:message key="tacluserinfo.msn" />
					</td>
					<td>${taclUserinfo.msn}</td>
				</tr>
			</table>
		</div>
		<div title="所属角色">
			<table class="data-grid" width="100%">
				<c:forEach items="${roles}" var="role">
					<tr>
						<td>${role.rolename}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div title="操作权限">
			<ul id="moduleTree"></ul>
		</div>
		<div title="数据权限">
			<ul id="dataTree"></ul>
		</div>
	</div>
</body>
</html>
