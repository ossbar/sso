<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.net.URLDecoder"%>
<% 
String u = request.getParameter("u");
String p = request.getParameter("p");
String url = request.getParameter("url");
if (url != null)
request.setAttribute("url", url);
String msg = request.getParameter("msg");
if (msg == null) msg = (String)request.getAttribute("msg");
if (msg == null) msg = "";
if (!"".equals(msg))
request.setAttribute("msg", URLDecoder.decode(msg, "UTF-8"));
if ((u != null && !"".equals(u) && p != null && !"".equals(p) && "".equals(msg)) 
		|| session.getAttribute("userinfo") != null) {
	request.getRequestDispatcher("/tsysLoginController.do").forward(request, response);
}
%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link rel="shortcut icon" href="${ctx}/themes/shareResources/images/favicon.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${ctx}/js_plugins/jquery/css/default.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js_plugins/jquery/js/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js_plugins/jquery/js/themes/icon.css" />
<script type="text/javascript" src="${ctx}/js_plugins/jquery/js/jquery-1.6.4.js"></script>
<script type="text/javascript" src="${ctx}/js_plugins/jquery/js/jquery.easyui-1.2.6.js"></script>
<script type="text/javascript" src="${ctx}/js_plugins/custom/common.js"></script>
<script type="text/javascript" >
function onBodyLoad() {
	var curflat = "";
	var curUrl = "";
	var serverMsg = "${msg}";
	if (serverMsg)  {
		alert(serverMsg);
	}
	document.body.onkeyup = function() {
		if (event.keyCode == 13 && event.srcElement.type == "password") {
			$("input[name=submit]").click();
		}
	}
	$("#submit").click(function() {
		if (!$("input[name=u]").val()) {
			alert('用户名不能为空！');
			return false;
		} else if (!$("input[name=p]").val()) {
			alert('密码不能为空！');
			return false;
		}
// 		var url = $("#flat").combobox("getValue");
// 		url += "/?method=index";
		$("form").attr("action", curUrl + "/?method=index");
	});
	$("#reset").click(function() {
		document.forms[0].reset();
	});
	var imgPath = "js_plugins/jquery/images/";
	$("input[type=image]").hover(function(){
		this.src = imgPath + this.name + "L.gif";
	},function(){
		this.src = imgPath + this.name + ".gif";
	});
	$("#flat").combobox({
		url : "${ctx}/tsysLoginController.do?method=getFlats",
		textField : "shortname",
		valueField : "flatid",
		onLoadSuccess : function() {
			var c = $(this);
			var datas = c.combobox('getData');
			if (datas.length > 0) {
				c.combobox("select", datas[0].flatid);
			}
		},
		onSelect: function(rec) {
			curUrl = rec.flaturl;
		}
	});
	
}
</script>
</head>
<body style="background-color: #CFE4F9; margin: 0px auto;" onload="onBodyLoad();">
	<form action="${ctx}/tsysLoginController.do" method="post">
		<input type="hidden" name="method" value="index">
		<input type="hidden" name="url" value="${url}">
		<div id="loginWindow"
			style="margin-left:auto;margin-right:auto;position:relative;top:180px;background-image:url(${ctx}/js_plugins/jquery/images/loginWindow.png); width:600px; height:310px; font-size:12px; font:bold;">
			<div
				style="position: absolute; top: 100px; left: 180px; top: 100px; height: 26px; width: 300px;">
				<fmt:bundle basename="resources/propertiescfg/ApplicationResources">
				 <fmt:requestEncoding value="UTF-8"/>  
				<table>
						<td style="height: 18px; width: 60px;"><font color="#FFFFFF" style="font-weight: bolder"><fmt:message key="sso.login.loginname" /></font></td>
						<td><input type="text" name="u" style="width: 140px;" class="text"  value="admin" onfocus="this.select();"/>
						</td>
						
					</tr>
					<tr>
						<td style="height: 18px"><font color="#FFFFFF" style="font-weight: bolder"><fmt:message key="sso.login.password"/></font></td>
						<td><input type="password" name="p" style="width: 140px;" value="admin" class="text" onfocus="this.select();"/>
						</td>
					</tr>
					<tr>
						<td style="height: 18px"><font color="#FFFFFF" style="font-weight: bolder">子系统</font></td>
						<td style="padding-left: 3px;"><select id="flat" name="f" style="width: 142px;"></select>
						</td>
					</tr>
					<tr>
						<td style="height: 35px"></td>
						<td><input
							src="js_plugins/jquery/images/submit.gif" name="submit" id="submit"
							type="image" style="float: left;">
							<input
								src="js_plugins/jquery/images/reset.gif" name="reset" id="reset"
								type="image" style="float: right;">
						</td>
					</tr>
				</table>
				</fmt:bundle>
			</div>
		</div>
	</form>
</body>
</html>

