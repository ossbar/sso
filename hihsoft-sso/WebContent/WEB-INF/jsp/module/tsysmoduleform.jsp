<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
</head>
<script type="text/javascript">
	function save_click() {
		if(!$("#formID").validationEngine('validate'))
			return;	
		var frm = $("form[name='moduleinfoform']");
		frm.attr("action", "${ctx}/tsysModuleinfoController.do?method=saveOrUpdate");
		frm.submit();
	}
	
	function back_click() {
		document.location = "${ctx}/tsysModuleinfoController.do?method=list"
	}
	$(function() {
		var flat = $("select[name=flatid]").val();
		var comboUrl = "${ctx}/tsysModuleinfoController.do?method=getModuleTree";
		var combo = $("#parentmoduleid");
		combo.combotree({
			url : flat ? (comboUrl + "&flatid=" + flat) : "",
			onClick : function(n) {
				var attr = decode(n.attributes);
				if (!attr || !attr.moduleid) {
					combo.combotree("setValue", "");
					return false;
				}
			}
		});
		$("select[name='flatid']").change(function() {
			var flatid = $("option:selected", this).val();
			if (!flatid) return;
			combo.combotree("reload", comboUrl + "&flatid=" + flatid);
		});
		(function(){
			$("input[name='modulename']").focus();
		}).defer(500);
		$("#formID").validationEngine({
			showOnMouseOver : true
		});
		$("table.FormView").find("td:even").css("text-align", "right");
	});
</script>
<body>
	<hih:form bean="moduleInfo" scope="request">
		<div style="position: relative; width: 100%; height: 100%;">
			<form action="/tsysModuleinfoController" method="post"
				name="moduleinfoform" id="formID">
				<table class="FormView" border="0" cellspacing="1" cellpadding="0">
					<col class="Label" />
					<col class="Data" />
					<tr>
						<td><c:if test="${moduleInfo!=null}">
								<input type='hidden' name='moduleid'
									value='${moduleInfo.moduleid}' />
							</c:if> <fmt:message key="tsysmoduleinfo.modulename"></fmt:message></td>
						<td><input type="text" name="modulename" id="modulename" class="validate[required,maxSize[80]] text" /><font
							color="red">**</font>
						</td>
					</tr>
					<tr>
						<td><fmt:message key="tsysmoduleinfo.flatid"></fmt:message>
						</td>
						<td><select id="flatid" name="flatid" style="width: 165px;" class="validate[required]">
								<option value="">---</option>
								<c:forEach items="${flats}" var="flat">
									<option value="${flat.flatid}">${flat.flatname}</option>
								</c:forEach>
						</select>
						</td>
					</tr>
					<tr>
						<td><fmt:message key="tsysmoduleinfo.parentmoduleid"></fmt:message>
						</td>
						<td><input type="text" id="parentmoduleid" name="parentmoduleid"
							style="width: 165px;" />
						</td>
					</tr>
					<tr>
						<td><fmt:message key="tsysmoduleinfo.moduleclass"></fmt:message>
						</td>
						<td><input type="text" name="moduleclass" id="moduleclass" class="validate[required,maxSize[2]] text" /><font
							color="red">**</font>
						</td>
					</tr>
					<tr>
						<td><fmt:message key="tsysmoduleinfo.moduleno"></fmt:message>
						</td>
						<td><input type="text" name="moduleno" id="moduleno" class="validate[required,maxSize[50]] text" /><font color="red">**</font>
						</td>
					</tr>
					<tr>
						<td><fmt:message key="tsysmoduleinfo.linkedaddr"></fmt:message>
						</td>
						<td><input type="text" name="linkedaddr" class="text" />
						</td>
					</tr>
					<tr>
						<td><fmt:message key="tsysmoduleinfo.moduleicon"></fmt:message>
						</td>
						<td><input type="text" name="moduleicon" class="text" />
						</td>
					</tr>
					<tr>
						<td><fmt:message key="tsysmoduleinfo.moduledesc"></fmt:message>
						</td>
						<td><input type="text" name="moduledesc" class="text" />
						</td>
					</tr>
					<tr>
						<td><fmt:message key="tsysmoduleinfo.sortnum"/></td>
						<td><input type="text" name="sortnum" id="sortnum" class="validate[custom[onlyNumberSp],maxSize[4]] text" /></td>
					</tr>
				</table>
			</form>
		</div>
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
