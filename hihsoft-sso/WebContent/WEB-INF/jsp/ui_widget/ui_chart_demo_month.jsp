
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
<%@ page import="com.hihframework.core.utils.*" %>
<%@ page import="java.util.*" %>

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
(function($){
	$.getUrlParam = function(name){
			var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);
			if (r!=null) return unescape(r[2]); return null;
		}
	})(jQuery);
	function getMonthDays(nowYear,myMonth){  
		--myMonth;
		var monthStartDate = new Date(nowYear, myMonth, 1);  
		var monthEndDate = new Date(nowYear, myMonth + 1, 1);  
		var days = (monthEndDate - monthStartDate)/(1000 * 60 * 60 * 24);  
		return days;  
	}  
	$(document)
			.ready(
					function() {
						/*月份导航*/
						var month = $.getUrlParam('month');
						if(month==null){
							 month=new Date().getMonth()+1;//月份	
						}
						//默认月份
						$(".month_menu button[tabindex="+month+"]").attr('disabled','true');//导航默认

						//获取数据
						var getPieData = function(data,num){
							var _data = [];
							$(data).each(function(i,obj){
								_data.push(obj[num]);
							});
							return _data;
						};
					
					   /*  饼图   */
					  var data = function(){
						   var _data = [];
						   $(".month_count td[class='month_count_td']").each(function(i,obj){
								  title = $($(".month_count th[class='month_count_th']")[i]).text();
							  	  count = $(obj).text();
								  _data.push([title,parseInt(count)]);
						  });
						   return _data;
					   }();
					
					  plot_pie = $.jqplot('chart', [data], {
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

					    /*  柱状图   */
						var series = function(){
						    _series = [];
							<c:forEach items="${report}" var="r">
								_series.push({label:'${r[0] }'});
							</c:forEach>
							return _series;
						}();//提示工具栏
						
						var categoryDatas = [];//统计数据
						
						ticks = function(){
						    var _ticks = [];
							$(".month_info td[class='month_info_td']").each(function(i,obj){
									  tick = $(obj).text();
									  _ticks.push(tick);
									  var _categoryData = [];
									  $(".month_info td:nth-child("+(i+2)+")").each(function(j,obj1){
											  var bt = $(obj1).text();
											 _categoryData.push(parseInt(bt));
									  });
									  if(_categoryData && _categoryData.length>28){
										  categoryDatas.push(_categoryData);
									  }
							});
						    return _ticks;
						}();
						
						

						$.jqplot('chart1', categoryDatas, {
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
					                ticks: ticks
					            },
					            yaxis: {
					            	min: 0,           //y轴最小值
						            tickInterval: 10,        //网格线间隔大小
					                tickOptions: {formatString: '%d'}
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
			width: 2660px; 
			height: 300px;
		}
</style>
</head>
<body>
	<div class="month_menu" style="text-align: center;">
		<button tabindex="1" onclick="window.location.href='${ctx}/statistiCanalysisController.do?method=list&module=business&target=month&month=1'">一月</button>
		<button tabindex="2" onclick="window.location.href='${ctx}/statistiCanalysisController.do?method=list&module=business&target=month&month=2'">二月</button>
		<button tabindex="3" onclick="window.location.href='${ctx}/statistiCanalysisController.do?method=list&module=business&target=month&month=3'">三月</button>
		<button tabindex="4" onclick="window.location.href='${ctx}/statistiCanalysisController.do?method=list&module=business&target=month&month=4'">四月</button>
		<button tabindex="5" onclick="window.location.href='${ctx}/statistiCanalysisController.do?method=list&module=business&target=month&month=5'">五月</button>
		<button tabindex="6" onclick="window.location.href='${ctx}/statistiCanalysisController.do?method=list&module=business&target=month&month=6'">六月</button>
		<button tabindex="7" onclick="window.location.href='${ctx}/statistiCanalysisController.do?method=list&module=business&target=month&month=7'">七月</button>
		<button tabindex="8" onclick="window.location.href='${ctx}/statistiCanalysisController.do?method=list&module=business&target=month&month=8'">八月</button>
		<button tabindex="9" onclick="window.location.href='${ctx}/statistiCanalysisController.do?method=list&module=business&target=month&month=9'">九月</button>
		<button tabindex="10" onclick="window.location.href='${ctx}/statistiCanalysisController.do?method=list&module=business&target=month&month=10'">十月</button>
		<button tabindex="11" onclick="window.location.href='${ctx}/statistiCanalysisController.do?method=list&module=business&target=month&month=11'">十一月</button>
		<button tabindex="12" onclick="window.location.href='${ctx}/statistiCanalysisController.do?method=list&module=business&target=month&month=12'">十二月</button>
	</div>
	<table>
		<tr>
			<td>
				<div id="chart"
					style="margin-top: 20px; margin-left: 10px; width: 360px; height: 300px;"></div>
			</td>
			<td>
				<div style="overflow-x: auto;overflow-y: hidden;width: 600px; height: 340px;">
					<div id="chart1" class="category"></div>
				</div>	
			</td>
		</tr>
		<tr align="center">
			<td colspan="2">
			<div style="overflow-x: auto; overflow-y: auto; height: 150px; width:auto;">
				<table  class="gridtable" style="margin-left: 40px;">
					<tr class="month_count">
						<th>姓名/月份</th>
						<c:forEach items="${report}" var="r">
							<th class='month_count_th'>${r[0] }</th>
						</c:forEach>
					</tr>
					 <%
					 	int month = DateUtils.getLastDateOfMonth(new Date()).getMonth()+1;//当前月
					 	String m = request.getParameter("month");
					 	if(m!=null && m.length()>0){
					 		month = Integer.parseInt(m);//月
					 	}
					 	Date d = new Date();d.setMonth(month-1);//设置月
					 	//表格数据内容
						int days = DateUtils.getLastDateOfMonth(d).getDate();//当前日
						List r = (List)request.getAttribute("report");
						for(int i = 0;i<days;i++){
							out.print("<tr class='month_info'>");
							out.print("<td class='month_info_td'>");out.print((i+1)+"日");out.print("</td>");
							for(int j=0;j<r.size();j++){
								Object [] objs = (Object[])r.get(j);//数据
								int _month = Integer.parseInt(objs[2].toString());//月
								int _day =Integer.parseInt(objs[3].toString());//日
								out.print("<td class='month_info_td_text'>");out.print(month==_month&&i==_day?objs[1]:0);out.print("</td>");
							}
							out.print("</tr>");
						}
						//合计
						out.print("<tr class='month_count'>");
						out.print("<td>");out.println("合计");out.print("</td>");
						for(int j=0;j<r.size();j++){
							Object [] objs = (Object[])r.get(j);//数据
							int _month = Integer.parseInt(objs[2].toString());//月
							int _day =Integer.parseInt(objs[3].toString());//日
							int count = 0;
							if(month==_month){
								for(int i = 0;i<days;i++){
									count+=(i==_day?Integer.parseInt(objs[1].toString()):0);
								}
							}
							out.print("<td class='month_count_td'>");out.print(count);out.print("</td>");
						}
						out.print("</tr>");
					%> 
				</table>
			</div>
			</td>
		</tr>
	</table>

</body>
</html>
