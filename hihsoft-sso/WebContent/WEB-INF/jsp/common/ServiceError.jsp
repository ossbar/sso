<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<title>出错页面</title>
</head>

<body>
	<div id="content">
		<h3>业务错误:</h3>

		<p>${errorMsg}</p>
		<button onclick="history.back();">返回</button>
	</div>
</body>
</html>