<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<html>
<head>
<script type="text/javascript">
	$(function() {
		var website = '网站';
		var prop = "网点性质";
		var comp = "单位";
		orgform = $("form[name='orgform']");
		$("#mainregion").find("tr>td:even").attr("align", "right");
		var select = $("#orgClass");
		var oldvalue = $("option:selected", select).text().replace(/级别/g, "");
		orgform.find("td:even").each(function() {
			var txt = $(this).text();
			if (!txt)
				return true;
			if (txt != website && (oldvalue == "站点" || oldvalue == "专线"))
				$(this).attr("innerText", txt.replace(/单位/g, oldvalue));
		});
		var fn = function() {
			$("input[name='orgname']").focus();
		};
		fn.defer(500);
		var initText = function() {
			var text = $("option:selected", this).text().replace(/级别/g, "");
			orgform.find("td:even").each(function() {
				var txt = $(this).text().trim();
				if (!txt || txt.indexOf("上级") != -1)
					return true;
				if ((text == "站点" || text == "专线")) {
					if (txt == prop) {
						$(this).parent().show();
					}
					if (txt.indexOf(website) != -1 || txt.indexOf("单位类型") != -1) {
						$(this).parent().hide();
					}
					if (txt.indexOf(comp) != -1) {
						txt = txt.replace(comp, text);
					} else if (txt.indexOf(oldvalue) != -1) {
						txt = txt.replace(oldvalue, text);
					}
				} else {
					if (txt == prop) {
						$(this).parent().hide();
					}
					if (txt.indexOf(website) != -1) {
						$(this).parent().show();
					}
					txt = txt.replace(oldvalue, comp);
				}
				$(this).text(txt);
			});
			oldvalue = text.replace(/级/g, "");
		}
		select.change(initText);
		initText.call(select);
		$("#formID").validationEngine({
			showOnMouseOver : true
		});
		$("#parentorgid").combotree({
			width : 165,
			url : "${ctx}/tsysOrgController.do?method=getAssignedOrgTree"
		});
	});
</script>
</head>
<body>
	<div>
		<hih:form bean="org" scope="request">
			<form action="/tsysOrgController.do" method="post" name="orgform"
				id="formID">
				<c:if test="${org!=null}">
					<input type="hidden" name='orgid' value='${org.orgid}' />
				</c:if>
				<div>
					<table class="FormView" border="0" cellspacing="1" cellpadding="0"
						id="mainregion">
						<col class="Label" />
						<col class="Data" />
						<tr>
							<td><fmt:message key="tsysorg.orgname"></fmt:message></td>
							<td><input type="text" name="orgname" id="orgname"
								class="validate[required,maxSize[15]] text" /><font color="red">**</font>
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tsysorg.orgno"></fmt:message>
							</td>
							<td><input type="text" name="orgno" id="orgno"
								class="validate[maxSize[12],custom[onlyNumberSp]] text" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tsysorg.orgshortname"></fmt:message>
							</td>
							<td><input type="text" name="orgShortname" id="orgshortname"
								class="validate[maxSize[20]] text" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tsysorg.orgclass"></fmt:message>
							</td>
							<td><hih:parameter name="roleType" id="orgClass" cssClass="select"/>
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tsysorg.orgsort"></fmt:message>
							</td>
							<td>
								<hih:parameter name="ORG_SORT" id="orgSort" style="width:165px;" defValue="1" type="radio"/>
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tsysorg.orgtype"></fmt:message>
							</td>
							<td>
								<hih:parameter name="RUN_MODE" id="orgtype" style="width:165px;"/>
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tsysorg.parentorgid"></fmt:message>
							</td>
							<td><input id="parentorgid" name="parentorgid"
								value="${parentorgid==null?(org==null?"
								":org.parentorgid):parentorgid}" readOnly />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tsysorg.orgdesc"></fmt:message>
							</td>
							<td><input type="text" name="orgdesc" id="orgdesc"
								class="validate[maxSize[30]] text" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tsysorg.addr"></fmt:message>
							</td>
							<td><textarea type="textarea" name="addr" id="addr"
									class="validate[maxSize[50]] textarea" cols="28" rows="3"></textarea>
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tsysorg.telephone"></fmt:message>
							</td>
							<td><input type="text" name="telephone" id="telephone"
								class="validate[maxSize[20],custom[phone]] text" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tsysorg.mobile"></fmt:message>
							</td>
							<td><input type="text" name="mobile" id="mobile"
								class="validate[maxSize[15],custom[phone]] text" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tsysorg.fax"></fmt:message>
							</td>
							<td><input type="text" name="fax" id="fax"
								class="validate[maxSize[20],custom[fax]] text" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tsysorg.corpman"></fmt:message>
							</td>
							<td><input type="text" name="corpMan" id="corpMan"
								class="validate[maxSize[15]] text" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tsysorg.email"></fmt:message>
							</td>
							<td><input type="text" name="email" id="email"
								class="validate[maxSize[30],custom[email]] text" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tsysorg.website"></fmt:message>
							</td>
							<td><input type="text" name="website" id="website"
								class="validate[maxSize[30],custom[url]] text" />
							</td>
						</tr>
						<tr>
							<td><fmt:message key="tsysorg.orgkind"></fmt:message>
							</td>
							<td><input type="text" name="orgKind" id="orgKind"
								class="text" />
							</td>
						</tr>
					</table>
				</div>
			</form>
		</hih:form>
	</div>
</body>
</html>
