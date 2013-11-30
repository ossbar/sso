
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<link rel="stylesheet" type="text/css"
	href="${ctx}/ui_widget/datagrid/flexigrid/themes/blue/flexigrid.css">
<script type="text/javascript"
	src="${ctx}/ui_widget/datagrid/flexigrid/jquery.flexigrid.js"></script>
<%@ include file="/WEB-INF/jsp/common/title.jsp"%>
<script type="text/javascript">
$(function() {
	$("#flex").flexigrid({
        url : '${ctx}/uiDataGridDemoController.do?method=listDatas',// ajax方式对应的url地址
        type: 'POST',// 数据发送方式 
        dataType : 'json',// 数据加载的类型(xml/json)
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        sortname : "flatid",
        sortorder:"desc",		        
        usepager : false,
        title : '子系统列表',		        
        useRp : true,
        checkbox : true,// 是否要多选框
        rowId : 'flatid',// 多选框绑定行的id
        singleSelect: false, //仅允许选择单行 
        rp : 15,
        rpOptions : [2, 15, 20, 30, 40, 50, 100],
        showTableToggleBtn : false,
        width : 'auto',// 宽度值 
        height: $("body").height()-143,// 插件的高度，单位为px   
        striped: true,// 是否显示斑纹效果，默认是奇偶交互的形式
		resizable: false,// 是否可伸缩   
        errormsg: '发生错误', //错误提升信息
        usepager: true, // 是否分页
        nowrap: true, //是否不换行 		  
        procmsg: '正在处理数据，请稍候 ...', //正在处理的提示信息  		 
		        colModel : [{
			            display : '序号',
			            name : 'id',
			            width : 25,// 得加上 要不IE报错
			            sortable : true,
			            align : 'center'
		            }, {
			            display : '系统代码',
			            name : 'flatcode',
			            width : 200,
			            sortable : true,
			            align : 'center'
		            }, {
			            display : '子系统名称',
			            name : 'flatname',
			            width : 200,
			            sortable : true,
			            align : 'center'
		            }, {
			            display : '简称',
			            name : 'shortname',
			            width : 200,
			            sortable : true,
			            align : 'center'
		            }, {
			            display : '排序号',
			            name : 'flatdesc',
			            width : 200,
			            sortable : true,
			            align : 'center'
		            }, {
			            display : '备注说明',
			            name : 'remark',
			            width : 100,
			            sortable : true,
			            align : 'center'
		            }],
		            searchitems : [ {
		            	display : '系统代码',
		            	name : 'flatcode'
		            	}, {
		            	display : '子系统名称',
		            	name : 'flatname',
		            	isdefault : true
		            	} ],
		       
	        });
    });
</script>
</head>
<body>
<hih:form bean="request">
	<form method="post" target="_self" action="
		${ctx}/uiDataGridDemoController.do?method=list " name="listForm">
		<input type="hidden" name="error" value='' />
		<div
			id="container">
			<div id="grid-body" region="center">
				<table id="flex"></table>
			</div>
		</div>
	</form>
</hih:form>
</body>
</html>