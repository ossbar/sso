<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="listdefine.js"></script>
<style type="text/css">
body{font-size: 12px;TEXT-ALIGN: center;}
#multiselect{width: 550px;height:300px;position: relative; margin-left: auto;margin-right: auto;}
#leftlist{width: 180px;height: 294px;left: 0;top: 0;position: absolute;margin: 2px;overflow: auto;border: 2px dotted #3315a9;,padding:1px;}
#rightlist{width: 247px;height: 294px;top: 0;right: 65;position: absolute;margin: 2px;overflow: auto;border: 2px dotted #3315a9;padding:1px;}
#toright,#toleft,#allright,#allleft{width: 35px;}
#bar{width: 40px;left: 188px;top: 25%;position: absolute;margin: 2px;}
#bar2{width: 40px;left: 485px;top: 25%;position: absolute;margin: 2px;}
.lbl{width: 120px;}
td{padding-left: 2px;}
.leftpad{padding-left: 2px;}
table{border-collapse: collapse;border: 1px solid gray;}
td,th {border-bottom: 1px solid gray;border-right: 1px solid gray;}
</style>
<title>列表自定义</title>
</head>
<body>
<input type="hidden" id="userid" value="">
<input type="hidden" id="moduleid" value="">
<div id="multiselect">
<div id="leftlist">
<table id="lefttable" cellpadding="0" cellspacing="0">
	<tr><th width="15" class="leftpad"><input type="checkbox" id="chkallleft"></th><th width="150">字段名</th></tr>
	<tr>
		<td><input type="checkbox" value="moduleid" name="chkleft"></td>	
		<td valign="middle"><label class="lbl">模块号</label></td>
	</tr>
	<tr>
		<td><input type="checkbox" value="modulename" name="chkleft"></td>	
		<td valign="middle"><label class="lbl">模块名称</label></td>
	</tr>
	<tr>
		<td><input type="checkbox" value="moduleid" name="chkleft"></td>	
		<td valign="middle"><label class="lbl">模块号</label></td>
	</tr>
	<tr>
		<td><input type="checkbox" value="modulename" name="chkleft"></td>	
		<td valign="middle"><label class="lbl">模块名称</label></td>
	</tr>
	<tr>
		<td><input type="checkbox" value="moduleid" name="chkleft"></td>	
		<td valign="middle"><label class="lbl">模块号</label></td>
	</tr>
	<tr>
		<td><input type="checkbox" value="modulename" name="chkleft"></td>	
		<td valign="middle"><label class="lbl">模块名称</label></td>
	</tr>
</table>
</div>
<div id="bar">
	<input type="button" value="&gt;" id="toright"><br/>
	<input type="button" value="&lt;" id="toleft"><br/>
	<input type="button" value="&gt;&gt;" id="allright"><br/>
	<input type="button" value="&lt;&lt;" id="allleft">
</div>
<div id="rightlist">
<table id="righttable" cellpadding="0" cellspacing="0">
	<tr><th width="15" class="leftpad"><input type="checkbox" id="chkallright"></th><th width="175">字段名</th><th width="40">宽度</th></tr>
</table>
</div>
<div id="bar2">
	<input type="button" value="置顶" id="first"><br/>
	<input type="button" value="向上" id="up"><br/>
	<input type="button" value="向下" id="down"><br/>
	<input type="button" value="置底" id="last">
</div>
</div>

</body>
</html>