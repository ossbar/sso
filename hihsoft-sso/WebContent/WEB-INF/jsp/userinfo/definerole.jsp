<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
#multiselect{width: 550px;height:300px;position: relative;margin-left: auto;margin-right: auto;}
#leftlist{width: 220px;height: 294px;left: 0;top: 0;position: absolute;margin: 2px;overflow: auto;border: 1px dashed #3315a9;,padding:1px;}
#rightlist{width: 220px;height: 294px;top: 0;right: 0;position: absolute;margin: 2px;overflow: auto;border: 1px solid #3315a9;padding:1px;}
#toright,#toleft,#allright,#allleft{width: 35px;}
#bar1{width: 40px;left: 250px;top: 25%;position: absolute;margin: 2px;}
#bar2{width: 40px;left: 485px;top: 25%;position: absolute;margin: 2px;}
.lbl{width: 120px;}
.table td{padding-left: 2px;}
.leftpad{padding-left: 2px;}
.table{border-collapse: collapse;border: 1px solid gray;}
.table td,th {border-bottom: 1px solid gray;border-right: 1px solid gray;}
</style>
<script type="text/javascript">
$(function(){
	var move = function(src, dest, toLeft, order) {
		src.find(":checked").each(function(i) {
			var chk = $(this);
			var tr = chk.parent().parent();
			if (chk.parent().attr("tagName") == "TH") return;
			var name = (toLeft ? "chkleft" : "chkright");
			var td1 = "<td><input type='checkbox' name='"+name+"' value='"+chk.val()+"' checked/></td>";
			var label = tr.find(".lbl");
			var td2 = label.parent()[0].outerHTML;
			var html = (td1 + td2);
			dest.append("<TR>"+html+"</TR>");
			tr.remove();
		});
	};
	$("#toleft").click(function(){
		var tb = $("#righttable");
		var ltb = $("#lefttable tbody");
		move(tb, ltb, true);
	});
	$("#toright").click(function(){
		var tb = $("#lefttable");
		var rtb = $("#righttable tbody");
		var oldcount = rtb.find("tr").size();
		move(tb, rtb, false, oldcount);
	});
	//向右移动全部
	$("#allright").click(function(){
		var chkall = $("#chkallleft");
		chkall.attr("checked", true);
		chkall.trigger("click");
		$("#toright").click();
	});
	//向左移动全部
	$("#allleft").click(function(){
		var chkall = $("#chkallright");
		chkall.attr("checked", true);
		chkall.trigger("click");
		$("#toleft").click();
	});
	$("#chkallleft").click(function() {
		var chk = $(this).attr("checked");
		$(":checkbox[name=chkleft]").each(function() {
			$(this).attr("checked", chk);
		});
	});
	$("#chkallright").click(function() {
		var chk = $(this).attr("checked");
		$(":checkbox[name=chkright]").each(function() {
			$(this).attr("checked", chk);
		});
	});
	$("#btn_save").click(function() {
		var defineds = $("#righttable").find(":checkbox");
		var ids = [];
		defineds.each(function(){
			var val = $(this).val();
			if (val && val != "on") ids.push(val); 
		});
		ids = ids.join(",");
		$.ajax({
			url : "${ctx}/taclUserinfoController.do?method=saveRoleSet",
			data : "ids=" + ids + "&userId=" + $("#userId").val(),
			type : "POST",
			success : function(data) {
				if (data && data.success) {
					alert("定义用户角色成功！");
					win.window("close");
				} else {
					alert("定义用户角色失败！");
				}
			}
		});
	});
	$("#btn_cancel").click(function() {
		win.window("close");
	});
});
</script>
</head>
<body>
<input type="hidden" id="userId" value="${userId}">
<div id="multiselect">
	<div id="leftlist">
	<table id="lefttable" cellpadding="0" cellspacing="0" class="table">
		<tr><th width="15" class="leftpad"><input type="checkbox" id="chkallleft"></th><th width="195"><fmt:message key="taclrole.rolename" /></th></tr>
		<c:forEach items="${roles}" var="role">
		<tr>
			<td><input type="checkbox" value="${role.roleid}" name="chkleft"></td>	
			<td valign="middle"><label class="lbl">${role.rolename}</label></td>
		</tr>
		</c:forEach>
	</table>
	</div>
	<div id="bar1">
		<input type="button" value="&gt;" id="toright"><br/>
		<input type="button" value="&lt;" id="toleft"><br/>
		<input type="button" value="&gt;&gt;" id="allright"><br/>
		<input type="button" value="&lt;&lt;" id="allleft">
	</div>
	<div id="rightlist">
	<table id="righttable" cellpadding="0" cellspacing="0" class="table">
		<tr><th width="15" class="leftpad"><input type="checkbox" id="chkallright"></th><th width="195"><fmt:message key="taclrole.rolename" /></th></tr>
		<c:forEach items="${defiends}" var="role">
		<tr>
			<td><input type="checkbox" value="${role.roleid}" name="chkright"></td>	
			<td valign="middle"><label class="lbl">${role.rolename}</label></td>
		</tr>
		</c:forEach>
	</table>
	</div>
</div>
<div align="center" class="foot-buttons">
	<div class="tool-btn" id="btn_save">
		<span class="icon-save">&nbsp;</span>
		<fmt:message key="button.save" />
	</div>
	<div class="tool-btn" id="btn_cancel">
		<span class="icon-back">&nbsp;</span>
		<fmt:message key="button.back" />
	</div>
</div>
</body>
</html>