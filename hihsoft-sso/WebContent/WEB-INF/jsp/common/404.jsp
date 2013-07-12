<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<title>出错页面</title>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<style type="text/css">
.aft-error-img {width: 500px;height: 600px;}
.aft-error-msg {text-align: left; padding-top: 150px;}
td {vertical-align: top;}
td ul li a {font-size: 12px;}
h3 {font: 2em "黑体",Georgia,Serif;}
</style>
</head>

<body>
	<div id="content">
		<table style="border: 0;width: 100%;height: 100%;">
			<tr>
				<td class="aft-error-img">
					<img alt="" src="${ctx}/css/images/error.png">
				</td>
				<td class="aft-error-msg">
					<h3>您访问的页面不存在</h3>
					<ul>
						<li><a href="#">返回主页</a></li>
						<li><a href="#">联系管理员</a></li>
						<li><a href="#">返回上一页</a></li>
					</ul>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>