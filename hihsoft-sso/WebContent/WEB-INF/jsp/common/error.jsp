<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<html>
<head>
<title>出错页面</title>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<style type="text/css">
.hih-error-img {width: 500px;height: 600px;}
.hih-error-msg {text-align: left; padding-top: 150px;}
td {vertical-align: top;}
.hih-error{font-size: 14px;display: block;margin-left: 20px;}
td ul li a {font-size: 12px;}
h3 {font: 2em "黑体",Georgia,Serif;}
</style>
</head>

<body>
	<div id="content">
		<table style="border: 0;width: 100%;height: 100%;">
			<tr>
				<td class="hih-error-img">
					<img alt="" src="${ctx}/css/images/error.png">
				</td>
				<td class="hih-error-msg">
					<h3>您访问的页面出错拉！</h3>
					<c:if test="${exception.message != null}">
					<span class="hih-error">${exception.message}</span>
					</c:if>
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