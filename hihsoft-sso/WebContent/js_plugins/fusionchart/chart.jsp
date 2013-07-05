<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/common/meta.jsp"%>
<%@ include file="/common/title.jsp"%>
<html>
<head>

<script language="JavaScript"
	src="${ctx}/chart_plugins/fusionchart/js/FusionCharts.js"></script>
</head>

<body>
<div id="fusioncharts1"></div>
<script>
	var myChart = new FusionCharts("${ctx}/chart_plugins/fusionchart/Charts/MSCombiDY2D.swf", "myChartId", "640", "480", "0", "0");
  	myChart.setDataXML("${xml}");
	myChart.render("fusioncharts1");
</script>
</body>
</html>
