
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
<script type="text/javascript"
	src="${ctx}/ui_widget/jqplot/plugins/jqplot.cursor.js"></script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						/*季度导航*/
						
						//默认季度
						var month=new Date().getMonth()+1;//月份	
						var quarter_num = Math.ceil(month/3);//季度数字
						
						$(".quarter_menu button[tabindex="+quarter_num+"]").attr('disabled','true');//导航默认
						$('#quarter'+quarter_num).css('display','block');//表格默认
						
						
						//季度切换绑定
						$(".quarter_menu button").bind('click', function() {
							quarter_num = $(this).attr('tabindex');
							//导航
							$(".quarter_menu button").each(function(index,obj){
								var _tabindex = $(obj).attr('tabindex');
								if(_tabindex!=quarter_num) $(this).removeAttr('disabled');
								else $(this).attr('disabled','true');
							});
							//表格
							$(".gridtable").each(function(index,obj){
								var _tabindex = $(obj).attr('id');
								if(_tabindex!='quarter'+quarter_num) $('#'+_tabindex).css('display','none');
								else $('#'+_tabindex).css('display','block');
							});
							//饼图
							plot_pie.series[0].data =  getPieData(pieData,quarter_num-1);
							plot_pie.replot();  
							
							
							//柱状图
							$(".category").each(function(index,obj){
								$(obj).css('display','none');//柱状图默认
							});
							$('#chart'+quarter_num).css('display','block');//柱状图默认
							
						});

						
						/********饼图********/

						/* 
							[
							 [['三星',1],['三星',12],['三星',23],['三星',13]],
							 [['LG',1],['LG',12],['LG',23],['LG',13]]
							] 
						*/
						var pieData = [];//统计数据  
						<c:forEach items="${report}" var="r" varStatus="re">
							pieData['${re.index}']=[];//数组
							pieData['${re.index}'][0]=['${r[0]}',eval("${(r[2]=='01'?r[1]:0)+(r[2]=='02'?r[1]:0)+(r[2]=='03'?r[1]:0)}") ];//季度一
							pieData['${re.index}'][1]=['${r[0]}',eval("${(r[2]=='04'?r[1]:0)+(r[2]=='05'?r[1]:0)+(r[2]=='06'?r[1]:0)}") ];//季度二
							pieData['${re.index}'][2]=['${r[0]}',eval("${(r[2]=='07'?r[1]:0)+(r[2]=='08'?r[1]:0)+(r[2]=='09'?r[1]:0)}") ];//季度三
							pieData['${re.index}'][3]=['${r[0]}',eval("${(r[2]=='10'?r[1]:0)+(r[2]=='11'?r[1]:0)+(r[2]=='12'?r[1]:0)}") ];//季度四
						</c:forEach>

						//获取数据
						var getPieData = function(data,num){
							var _data = [];
							$(data).each(function(i,obj){
								_data.push(obj[num]);
							});
							return _data;
						};
						
						_pieData = getPieData(pieData,quarter_num-1);
								
						plot_pie = $.jqplot('chart', [_pieData], {
							title : '业务统计表 ',//设置饼状图的标题
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
						
						var categoryDatas = [];//统计数据
						var series = [];//提示工具栏
						var ticks = [
						              ['一月份', '二月份', '三月份'],
						              ['四月份', '五月份', '六月份'],
						              ['七月份', '八月份', '九月份'],
						              ['十月份', '十一月份', '十二月份']
						            ];
						<c:forEach items="${report}" var="r" varStatus="re">
							series.push({label : '${r[0]}'}) ;
							categoryDatas['${re.index}']=[];
							categoryDatas['${re.index}'][0]=[eval("${r[2]=='01'?r[1]:0}") , eval("${r[2]=='02'?r[1]:0}") ,eval("${r[2]=='03'?r[1]:0}") ];
							categoryDatas['${re.index}'][1]=[eval("${r[2]=='04'?r[1]:0}") , eval("${r[2]=='05'?r[1]:0}") ,eval("${r[2]=='06'?r[1]:0}") ];
							categoryDatas['${re.index}'][2]=[eval("${r[2]=='07'?r[1]:0}") , eval("${r[2]=='08'?r[1]:0}") ,eval("${r[2]=='09'?r[1]:0}") ];
							categoryDatas['${re.index}'][3]=[eval("${r[2]=='10'?r[1]:0}") , eval("${r[2]=='11'?r[1]:0}") ,eval("${r[2]=='12'?r[1]:0}") ];
						</c:forEach>

						
				
					    

					     $.jqplot('chart1', getPieData(categoryDatas,1-1), {
					    	title : '业务统计表 ',//设置柱状图的标题
					        seriesDefaults:{
					            renderer:$.jqplot.BarRenderer,
					            pointLabels: { show: true },  
					            rendererOptions: {fillToZero: true}
					        },
					        series:series,
					        legend: {
					            show: true,
					            placement: 'outsideGrid'
					        },
					        axes: {
					            xaxis: {
					                renderer: $.jqplot.CategoryAxisRenderer,
					                ticks: ticks[1-1]
					            },
					            yaxis: {
					            	min: 0,           //y轴最小值
						            tickInterval: 10,        //网格线间隔大小
					                tickOptions: {formatString: '%d'}
					            }
					        }
					    });
					    
					    $.jqplot('chart2', getPieData(categoryDatas,2-1), {
					    	title : '业务统计表 ',//设置柱状图的标题
					        seriesDefaults:{
					            renderer:$.jqplot.BarRenderer,
					            pointLabels: { show: true },  
					            rendererOptions: {fillToZero: true}
					        },
					        series:series,
					        legend: {
					            show: true,
					            placement: 'outsideGrid'
					        },
					        axes: {
					            xaxis: {
					                renderer: $.jqplot.CategoryAxisRenderer,
					                ticks: ticks[2-1]
					            },
					            yaxis: {
					            	min: 0,           //y轴最小值
						            tickInterval: 10,        //网格线间隔大小
					                tickOptions: {formatString: '%d'}
					            }
					        }
					    });
					    
					    $.jqplot('chart3', getPieData(categoryDatas,3-1), {
					    	title : '业务统计表 ',//设置柱状图的标题
					        seriesDefaults:{
					            renderer:$.jqplot.BarRenderer,
					            pointLabels: { show: true },  
					            rendererOptions: {fillToZero: true}
					        },
					        series:series,
					        legend: {
					            show: true,
					            placement: 'outsideGrid'
					        },
					        axes: {
					            xaxis: {
					                renderer: $.jqplot.CategoryAxisRenderer,
					                ticks: ticks[3-1]
					            },
					            yaxis: {
					            	min: 0,           //y轴最小值
						            tickInterval: 10,        //网格线间隔大小
					                tickOptions: {formatString: '%d'}
					            }
					        }
					    });

					    
					    $.jqplot('chart4', getPieData(categoryDatas,4-1), {
					    	title : '业务统计表 ',//设置柱状图的标题
					        seriesDefaults:{
					            renderer:$.jqplot.BarRenderer,
					            pointLabels: { show: true },  
					            rendererOptions: {fillToZero: true}
					        },
					        series:series,
					        legend: {
					            show: true,
					            placement: 'outsideGrid'
					        },
					        axes: {
					            xaxis: {
					                renderer: $.jqplot.CategoryAxisRenderer,
					                ticks: ticks[4-1]
					            },
					            yaxis: {
					            	min: 0,           //y轴最小值
						            tickInterval: 10,        //网格线间隔大小
					                tickOptions: {formatString: '%d'}
					            }
					        }
					    }); 
					    
					  //柱状图
						$(".category").each(function(index,obj){
							$(obj).css('display','none');//柱状图默认
						});
					    $('#chart'+quarter_num).css('display','block');//柱状图默认
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
			width: 90%; table-layout: fixed;
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

div.category{
	margin-top: 20px;
	margin-left: 10px; 
	width: 660px; 
	height: 300px;
}
</style>
</head>
<body>

	<div class="quarter_menu" style="text-align: center;">
		<button tabindex="1">季度一</button>
		<button tabindex="2">季度二</button>
		<button tabindex="3">季度三</button>
		<button tabindex="4">季度四</button>
	</div>
	<table>
		<tr>
			<td>
				<div id="chart"
					style="margin-top: 20px; margin-left: 10px; width: 360px; height: 300px;"></div>
			</td>
			<td>
					<div id="chart1" class="category"></div>
					<div id="chart2" class="category"></div>
					<div id="chart3" class="category"></div>
					<div id="chart4" class="category"></div>
			</td>
		</tr>
		<tr align="center">
			<td colspan="2">
				<div style="overflow-x: auto; overflow-y: auto; height: 175px; width:auto;">
					<table id="quarter1" class="gridtable" style="margin-left: 40px; display: none;">
						<tr>
							<th>类型/月份</th>
							<c:forEach items="${report}" var="r">
								<th>${r[0] }</th>
							</c:forEach>
						</tr>
						<tr>
							<td>一月份</td>
							<c:forEach items="${report}" var="r">
								<td>${r[2]=='01'?r[1]:0}</td>
							</c:forEach>
	
						</tr>
						<tr>
							<td>二月份</td>
							<c:forEach items="${report}" var="r">
								<td>${r[2]=='02'?r[1]:0}</td>
							</c:forEach>
						</tr>
						<tr>
							<td>三月份</td>
							<c:forEach items="${report}" var="r">
								<td>${r[2]=='03'?r[1]:0}</td>
							</c:forEach>
						</tr>
						<tr>
							<td>合计</td>
							<c:forEach items="${report}" var="r">
								<td>
									${(r[2]=='01'?r[1]:0)+(r[2]=='02'?r[1]:0)+(r[2]=='03'?r[1]:0)}</td>
							</c:forEach>
						</tr>
	
					</table>
	
					<table id="quarter2" class="gridtable"
						style="margin-left: 40px; display: none;">
						<tr>
							<th>类型/月份</th>
							<c:forEach items="${report}" var="r">
								<th>${r[0] }</th>
							</c:forEach>
						</tr>
						<tr>
							<td>四月份</td>
							<c:forEach items="${report}" var="r">
								<td>${r[2]=='04'?r[1]:0}</td>
							</c:forEach>
	
						</tr>
						<tr>
							<td>五月份</td>
							<c:forEach items="${report}" var="r">
								<td>${r[2]=='05'?r[1]:0}</td>
							</c:forEach>
						</tr>
						<tr>
							<td>六月份</td>
							<c:forEach items="${report}" var="r">
								<td>${r[2]=='06'?r[1]:0}</td>
							</c:forEach>
						</tr>
						<tr>
							<td>合计</td>
							<c:forEach items="${report}" var="r">
								<td>
									${(r[2]=='04'?r[1]:0)+(r[2]=='05'?r[1]:0)+(r[2]=='06'?r[1]:0)}</td>
							</c:forEach>
						</tr>
	
					</table>
	
					<table id="quarter3" class="gridtable"
						style="margin-left: 40px; display: none;">
						<tr>
							<th>类型/月份</th>
							<c:forEach items="${report}" var="r">
								<th>${r[0] }</th>
							</c:forEach>
						</tr>
						<tr>
							<td>七月份</td>
							<c:forEach items="${report}" var="r">
								<td>${r[2]=='07'?r[1]:0}</td>
							</c:forEach>
	
						</tr>
						<tr>
							<td>八月份</td>
							<c:forEach items="${report}" var="r">
								<td>${r[2]=='08'?r[1]:0}</td>
							</c:forEach>
						</tr>
						<tr>
							<td>九月份</td>
							<c:forEach items="${report}" var="r">
								<td>${r[2]=='09'?r[1]:0}</td>
							</c:forEach>
						</tr>
						<tr>
							<td>合计</td>
							<c:forEach items="${report}" var="r">
								<td>
									${(r[2]=='07'?r[1]:0)+(r[2]=='08'?r[1]:0)+(r[2]=='09'?r[1]:0)}</td>
							</c:forEach>
						</tr>
	
					</table>
	
					<table id="quarter4" class="gridtable"
						style="margin-left: 40px; display: none;">
						<tr>
							<th>类型/月份</th>
							<c:forEach items="${report}" var="r">
								<th>${r[0] }</th>
							</c:forEach>
						</tr>
						<tr>
							<td>十月份</td>
							<c:forEach items="${report}" var="r">
								<td>${r[2]=='10'?r[1]:0}</td>
							</c:forEach>
	
						</tr>
						<tr>
							<td>十一月份</td>
							<c:forEach items="${report}" var="r">
								<td>${r[2]=='11'?r[1]:0}</td>
							</c:forEach>
						</tr>
						<tr>
							<td>十二月份</td>
							<c:forEach items="${report}" var="r">
								<td>${r[2]=='12'?r[1]:0}</td>
							</c:forEach>
						</tr>
						<tr>
							<td>合计</td>
							<c:forEach items="${report}" var="r">
								<td>
									${(r[2]=='10'?r[1]:0)+(r[2]=='11'?r[1]:0)+(r[2]=='12'?r[1]:0)}</td>
							</c:forEach>
						</tr>
	
					</table>
				</div>
			</td>
		</tr>
	</table>





</body>
</html>
