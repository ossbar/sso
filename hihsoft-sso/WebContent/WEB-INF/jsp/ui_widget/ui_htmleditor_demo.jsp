<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="ui_widget/easyui/js/jquery-1.6.min.js"></script>
<script src="ui_widget/ckeditor/ckeditor.js"></script>
<script src="ui_widget/ckeditor/adapters/jquery.js"></script>
<style type="text/css">
pre.samples
{
	background-color: #F7F7F7;
	border: 1px solid #D7D7D7;
	overflow: auto;
	padding: 0.25em;
	white-space: pre-wrap; /* CSS 2.1 */
	word-wrap: break-word; /* IE7 */
	-moz-tab-size: 4;
	-o-tab-size: 4;
	-webkit-tab-size: 4;
	tab-size: 4;
}
</style>
</head>
<body>
	<h1 align="center">CKEditor</h1>
	<span ><a href="http://ckeditor.com/download" target="_blank">官方网站下载</a></span>
	<br /><br />
	<span style="font-size: large;">
		CKeditor的用法很简单: <br />
		需要导入的文件：<br />
		<ul>
			<li>jquery.js</li>
			<li>ckeditor.js(必须)</li>
			<li>adapters/jquery.js(如果需要使用jquery方式初始化)</li>
		</ul>
		目录lang 存放语言文件,你可以根据需要清理或添加语言文件<br />
		目录skins 存放皮肤的相关文件,你可以根据需要清理或添加皮肤文件包<br />
		目录plugins 存放相关的扩展插件，可以根据需要添加或者移除
	</span>
	<br /><br />
	<div style="width: 85%">
		<h3>示例1： 按样式名初始化</h3>
		<pre class="samples">&lt;textarea class="ckeditor" name="editor1"&gt;&lt;/textarea&gt;</pre>
		<p>
			<textarea class="ckeditor" name="editor1"></textarea>
		<p>
	</div>
	<br />
	<div style="width: 85%">
		<h3>示例2： 使用js代码初始化</h3>
		<pre class="samples">CKEDITOR.replace( 'editor2' )</pre>
		<p>
			<textarea id="editor2"></textarea>
		<p>
		<script type="text/javascript">
			CKEDITOR.replace( 'editor2' );
		</script>
	</div>
	<br />
	<div style="width: 85%">
		<h3>示例3： 使用jquery初始化</h3>
		<pre class="samples">
		$( document ).ready( function() {
			$( 'textarea#editor3' ).ckeditor();
		} );</pre>
		<p>
			<textarea id="editor3"></textarea>
		<p>
		<script type="text/javascript">
		$( document ).ready( function() {
			$( 'textarea#editor3' ).ckeditor();
		} );
		</script>
	</div>
	<br />
	
</body>
</html>