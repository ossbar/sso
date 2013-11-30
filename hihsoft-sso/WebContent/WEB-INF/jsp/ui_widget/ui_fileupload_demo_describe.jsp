<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style type="text/css">
#download a:hover{
 color:red;
}
</style>
</head>
<body>
	<h1 align="center" style="font-size: medium;">Uploadify</h1>
	<span  id="download"><a href="http://www.uploadify.com" target="_blank" style="font-size: medium;">官方网站下载</a></span>
	<br /><br />
	<span style="font-size: medium;">
		第一步：下载好压缩包之后，解压里面的文件：<br />
		jquery.uploadify.js （主要插件）<br />
		jquery-1.7.2.min.js （jquery主件）<br />
		uploadify.swf （flash上传插件）<br />
		uploadify.css （上传样式表）<br />
		uploadify-cancel.png （flash上传按钮图标）<br />
		uploadify.php （上传处理数据）<br />
		uploads文件夹 （默认保存上传文件目录）<br /><br />
		第二步：写上传接口<br /><br />
		在你需要上传的页面写下如下js：<br />
		首先引入js/css<br />
		然后定义一些初始化变量，后面都写有注释<br />
		下面这个是ajax 接受后台数据处理返回来的值，然后追加到前台上传页面显示上传的文件名和地址<br />
		最后是在body里面添加调用标签<br />
	</span>
</body>
</html>