<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="ui_widget/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">

div {	/*为每个示例加个边框*/
 border: 1px solid gray;
 }

h5{	/*示例编号段落设置*/
 margin-bottom: 1px;
 margin-top: 1px;
}

font{	/*示例代码的字体样式*/
 font-size: small;
}

.impo{	/*示例代码中的关键字样式*/
 color:red;
}

.attr{	/*示例代码中的属性样式*/
 color:blue;
}

.rema{	/*每个示例中的注释内容样式*/
 font-size:small;
}
#download a:hover{
 color:red;
}
</style>
</head>
<body>
	<h1 align="center">My97DatePicker</h1>
	<span  id="download"><a href="http://www.my97.net/dp/index.asp" target="_blank">官方网站下载</a></span>
	<br /><br />
	<span style="font-size: large;">
		各目录及文件的用途: <br />
		WdatePicker.js 配置文件,在调用的地方仅需使用该文件,可多个共存,以xx_WdatePicker.js方式命名<br />
		config.js 语言和皮肤配置文件,无需引入(4.8以后合并入WdatePicker.js)<br />
		calendar.js 日期库主文件,无需引入<br />
		My97DatePicker.htm 临时页面文件,不可删除(4.8以后不存在此文件)<br />
		目录lang 存放语言文件,你可以根据需要清理或添加语言文件<br />
		目录skin 存放皮肤的相关文件,你可以根据需要清理或添加皮肤文件包<br />
		当WdatePicker.js里的属性:$wdate=true时,在input里加上class="Wdate"就会在选择框右边出现日期图标,如果您不喜欢这个样式,可以把class="Wdate"去掉,另外也可以通过修改skin目录下的WdatePicker.css文件来修改样式
	</span>
	<br /><br />
	<div style="width: 50%">
		<h5>示例1： 常规调用</h5>
		<p>
			<input type="text" id="date1" class="Wdate" onClick="WdatePicker()"/>
			<br />
			<font>
				&lt;input type=&quot;text&quot; id=&quot;date1&quot; class=&quot;Wdate&quot; 
				<span class="impo">onClick=&quot;WdatePicker()&quot;</span>/&gt;
			</font>
		<p>
		<span class="rema">注释：在标签中使用WdatePicker()方法，需在页面引入WdatePicker.js文件</span>
	</div>
	<br />
	<div style="width: 85%">
		<h5>示例2： 自定义日期格式</h5>
		<p>
			<input type="text" id="date2" class="Wdate" style="width:250px" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH时mm分ss秒 星期D 第WW周'})"/>
			<br />
			<font>
				&lt;input type=&quot;text&quot; id=&quot;date2&quot; class=&quot;Wdate&quot; style=&quot;width:250px&quot; 
				onClick=&quot;WdatePicker({<span class="impo">dateFmt:'yyyy-MM-dd HH时mm分ss秒 星期 D 第WW周'</span>})&quot;</span>/&gt;
			</font>
		<p>
		<span class="rema">注释：dateFmt属性是设置日期格式(注意大小写区分) y-->年, M-->月, d-->日, H-->时, m-->分, s-->秒, D-->星期, W-->周</span>
	</div>
	<br />
	<div style="width: 70%">
		<h5>示例3： 支持周显示</h5>
		<p>
			<input type="text" id="date3" class="Wdate" onClick="WdatePicker({isShowWeek:true,dateFmt:'yyyy-MM-dd 第WW周'})"/>
			<br />
			<font>
				&lt;input type=&quot;text&quot; id=&quot;date3&quot; class=&quot;Wdate&quot; 
				onClick=&quot;WdatePicker({<span class="impo">isShowWeek:true,dateFmt:'yyyy-MM-dd 第WW周'</span>})&quot;</span>/&gt;
			</font>
		<p>
		<span class="rema">注释：isShowWeek属性是控制是否显示周，默认是false不显示，想在文本框中显示周，仍然需再设置dateFmt属性</span>
	</div>
	<br />
	<div style="width: 70%">
		<h5>示例4： 限制日期的范围是 2013年10月5日 到 2013年11月05日</h5>
		<p>
			<input type="text" id="date4" class="Wdate" onClick="WdatePicker({minDate:'2013-10-05',maxDate:'2013-11-05'})"/>
			<br />
			<font>
				&lt;input type=&quot;text&quot; id=&quot;date4&quot; class=&quot;Wdate&quot; 
				onClick=&quot;WdatePicker({<span class="attr">minDate:</span><span class="impo">'2013-10-05'</span>,
				<span class="attr">maxDate:</span><span class="impo">'2013-11-05'</span>})&quot;</span>/&gt;
			</font>
		<p>
		<span class="rema">注释：minDate设置限制范围中的最小值，maxDate设置限制范围中的最大值 </span>
	</div>
	<br />
	<div style="width: 70%">
		<h5>示例5：起始日期不能大于终止日期</h5>
		<p>
			起始日期:<input type="text" id="bdate" class="Wdate" onClick="WdatePicker({maxDate:'#F{$dp.$D(\'edate\')}'})"/>
			终止日期:<input type="text" id="edate" class="Wdate" onClick="WdatePicker({minDate:'#F{$dp.$D(\'bdate\')}'})"/>
			<br />
			<font>起始日期:
				&lt;input type=&quot;text&quot; id=&quot;<span style="color:green">bdate</span>&quot; class=&quot;Wdate&quot; 
				onClick=&quot;WdatePicker({<span class="attr">maxDate:</span><span class="impo">
				'#F{$dp.$D(\'<span style="color:purple">edate</span>\')}'</span>})&quot;</span>/&gt;
			</font>
			<br />
			<font>终止日期:
				&lt;input type=&quot;text&quot; id=&quot;<span style="color:purple">edate</span>&quot; class=&quot;Wdate&quot; 
				onClick=&quot;WdatePicker({<span class="attr">minDate:</span><span class="impo">
				'#F{$dp.$D(\'<span style="color:green">bdate</span>\')}'</span>})&quot;</span>/&gt;
			</font>
		<p>
		<span class="rema">注释：两个日期的日期格式必须相同，  #F{} 中填入你自定义的脚，  $dp.$ 相当于 document.getElementById 函数</span>
	</div>
</body>
</html>