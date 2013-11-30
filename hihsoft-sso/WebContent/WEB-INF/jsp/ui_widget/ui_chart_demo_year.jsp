
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>

<!--[if IE]><script language="javascript" type="text/javascript" src="${ctx}/ui_widget/jqplot/excanvas.js"></script><![endif]-->
<link rel="stylesheet" type="text/css"
	href="${ctx}/ui_widget/jqplot/jquery.jqplot.css" />
<script type="text/javascript"
	src="${ctx}/ui_widget/jqplot/jquery.jqplot.min.js"></script>

<!-- plugins -->
<script type="text/javascript"
	src="${ctx}/ui_widget/jqplot/plugins/jqplot.pieRenderer.js"></script>
<script type="text/javascript"
	src="${ctx}/ui_widget/jqplot/plugins/jqplot.barRenderer.js"></script>
<script type="text/javascript"
	src="${ctx}/ui_widget/jqplot/plugins/jqplot.categoryAxisRenderer.js"></script>
<script type="text/javascript"
	src="${ctx}/ui_widget/jqplot/plugins/jqplot.pointLabels.min.js"></script>
	<!-- 鼠标效果 -->
<script type="text/javascript" src="${ctx}/ui_widget/jqplot/plugins/jqplot.cursor.js"></script>

<script type="text/javascript">
	$(document).ready(
			function() {

				/********饼图********/
				
				var line1 = [];//统计数据
				<c:forEach items="${report}" var="r">
						 line1.push(['${r[0]}',eval("${(r[2]=='01'?r[1]:0)+(r[2]=='02'?r[1]:0)+(r[2]=='03'?r[1]:0)+(r[2]=='04'?r[1]:0)+(r[2]=='05'?r[1]:0)+(r[2]=='06'?r[1]:0)+(r[2]=='07'?r[1]:0)+(r[2]=='08'?r[1]:0)+(r[2]=='09'?r[1]:0)+(r[2]=='10'?r[1]:0)+(r[2]=='11'?r[1]:0)+(r[2]=='12'?r[1]:0)}")]);
				</c:forEach>
				
				/* line1 = [ [ '冰箱', 3 ], [ '洗衣机', 7 ], [ '空调', 2.5 ],
						[ '电风扇', 6 ], [ '电脑', 5 ], [ '抽油烟机', 4 ] ]; */
				plot1 = $.jqplot('chart', [ line1 ], {
					title : '人员派工情况年表 ',//设置饼状图的标题
					seriesDefaults : {
						fill : true,
						showMarker : true,
						shadow : true,
						renderer : $.jqplot.PieRenderer,

						rendererOptions : {
							showDataLabels : true,
							diameter : undefined, // 设置饼的直径
							padding : 20, // 饼距离其分类名称框或者图表边框的距离，变相该表饼的直径
							sliceMargin : 9, // 饼的每个部分之间的距离
							fill : true, // 设置饼的每部分被填充的状态
							shadow : true, //为饼的每个部分的边框设置阴影，以突出其立体效果
							shadowOffset : 2, //设置阴影区域偏移出饼的每部分边框的距离
							shadowDepth : 5, // 设置阴影区域的深度
							shadowAlpha : 0.07
						// 设置阴影区域的透明度
						}
					},
					legend : {
						show : true,//设置是否出现分类名称框（即所有分类的名称出现在图的某个位置）
						location : 'ne', // 分类名称框出现位置, nw, n, ne, e, se, s, sw, w.
						xoffset : 12, // 分类名称框距图表区域上边框的距离（单位px）
						yoffset : 12, // 分类名称框距图表区域左边框的距离(单位px)
					}

				}); 
				
				
				/****** 柱状图*********/
				
				var lines = [];//统计数据
				var series =[];//提示工具栏
				<c:forEach items="${report}" var="r">
					     series1 = {label: '${r[0]}'};
						 line1 = [['一月份',eval("${r[2]=='01'?r[1]:0}")], ['二月份',eval("${r[2]=='02'?r[1]:0}")], ['三月份',eval("${r[2]=='03'?r[1]:0}")], ['四月份',eval("${r[2]=='04'?r[1]:0}")],['五月份',eval("${r[2]=='05'?r[1]:0}")], ['六月份',eval("${r[2]=='06'?r[1]:0}")], ['七月份',eval("${r[2]=='07'?r[1]:0}")], ['八月份',eval("${r[2]=='08'?r[1]:0}")],['九月份',eval("${r[2]=='09'?r[1]:0}")], ['十月份',eval("${r[2]=='10'?r[1]:0}")], ['十一月份',eval("${r[2]=='11'?r[1]:0}")], ['十二月份',eval("${r[2]=='12'?r[1]:0}")]];    
						 
						 series.push(series1);
						 lines.push(line1);				
				</c:forEach>
				
			    
			     plot1 = $.jqplot('chart1', lines, {
			     title: '人员派工情况年表',
			     legend: {show: true,  placement: 'outsideGrid'}, //提示工具栏--show：是否显示,location: 显示位置 (e:东,w:西,s:南,n:北,nw:西北,ne:东北,sw:西南,se:东南)
			         series: series, //提示工具栏
			         showMarker : true,
			         seriesDefaults: {
				         renderer: $.jqplot.BarRenderer, //使用柱状图表示
				         pointLabels: { show: true },  
				         rendererOptions: {
				              barMargin: 2   //柱状体组之间间隔
				         }
			         },
			         axes: {
			             xaxis: {
			                 renderer: $.jqplot.CategoryAxisRenderer //x轴绘制方式
			             },
			             yaxis: {
			             min: 0,           //y轴最小值
			             tickInterval: 10        //网格线间隔大小
			            }
			         }
			     });

			});
</script>
<style type="text/css">
table.gridtable {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}

table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #E0ECFF;
	text-align: center;
}

table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
	text-align: center;
}

</style>
</head>
<body>
	<table>
		<tr>
			<td>
				  <div id="chart"
					style="margin-top: 20px; margin-left: 10px; width: 360px; height: 300px;"></div> 
			</td>
			<td>
			<div id="chart1"
					style="margin-top: 20px; margin-left: 10px; width: 660px; height: 300px;"></div>
			</td>
		</tr>
		<tr>
			<td>
			</td>
			<td>
				
			
			</td>
		</tr>
		<tr align="center">
			<td colspan="2">
				<table class="gridtable" style="margin-left: 40px; ">
					<tr>
						<th>姓名/月份</th>
						<th>一月份</th>
						<th>二月份</th>
						<th>三月份</th>
						<th>四月份</th>
						<th>五月份</th>
						<th>六月份</th>
						<th>七月份</th>
						<th>八月份</th>
						<th>九月份</th>
						<th>十月份</th>
						<th>十一月份</th>
						<th>十二月份</th>
						<th>总计</th>
					</tr>
					<c:forEach items="${report}" var="r">
						<tr>
							<td>${r[0] }</td>
							<td>${r[2]=='01'?r[1]:0}</td>
							<td>${r[2]=='02'?r[1]:0}</td>
							<td>${r[2]=='03'?r[1]:0}</td>
							<td>${r[2]=='04'?r[1]:0}</td>
							<td>${r[2]=='05'?r[1]:0}</td>
							<td>${r[2]=='06'?r[1]:0}</td>
							<td>${r[2]=='07'?r[1]:0}</td>
							<td>${r[2]=='08'?r[1]:0}</td>
							<td>${r[2]=='09'?r[1]:0}</td>
							<td>${r[2]=='10'?r[1]:0}</td>
							<td>${r[2]=='11'?r[1]:0}</td>
							<td>${r[2]=='12'?r[1]:0}</td>
							<td><b>${(r[2]=='01'?r[1]:0)+(r[2]=='02'?r[1]:0)+(r[2]=='03'?r[1]:0)+(r[2]=='04'?r[1]:0)+(r[2]=='05'?r[1]:0)+(r[2]=='06'?r[1]:0)+(r[2]=='07'?r[1]:0)+(r[2]=='08'?r[1]:0)+(r[2]=='09'?r[1]:0)+(r[2]=='10'?r[1]:0)+(r[2]=='11'?r[1]:0)+(r[2]=='12'?r[1]:0)}</b></td>
						</tr>
					</c:forEach>
				</table>
			</td>
		</tr>
	</table>





</body>
</html>
