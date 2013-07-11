<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp"%>
<html>
<head>
<%@ include file="/WEB-INF/jsp/common/meta.jsp"%>
<script type="text/javascript"
	src='${ctx}/js_plugins/jquery/js/outlook2.js'>
</script>
<script type="text/javascript">
var win,curFlatId;
$(function() {
	curFlatId = "${curFlatId}";
	var defaultMenu;
	if (!curFlatId) {
		defaultMenu = $("#menu0");
		curFlatId = defaultMenu.attr("flatid");
	} else {
		defaultMenu = $("div[flatid='"+curFlatId+"']");
	}
	$("div[flatid="+defaultMenu.attr("flatid")+"]").children("div").each(function(){
		var css = {"background" : "url('${ctx}/js_plugins/jquery/images/menu_lefts.png) no-repeat"};
		var menu = $(this).attr("menu");
		if (menu == "cent") {
			css.background = "url'${ctx}/js_plugins/jquery/images/menu_cens.png) repeat";
		} else if (menu =="right") {
			css.background = "url('${ctx}/js_plugins/jquery/images/menu_rights.png) no-repeat";
		}
		$(this).css(css);
	});
	getMenus("${ctx}", defaultMenu.attr("flatid"));
	getHelpMenu("${ctx}");
	win = $("#modifyWindow").window({
		title : "修改密码",
		modal : true
	});
	$("#changePwd").click(function() {
		win.window("open");
		$("#window-body").load("${ctx}/taclUserinfoController.do?method=password");
	});
	$("#logout").click(function() {
		$(document.body).append("<form id='logform' target='_self' method='post'></form>");
		var fm = $("#logform");
		fm.attr("action", "${ctx}/tsysLoginController.do?method=logout");
		fm.submit();
	});
});
</script>
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
	<noscript>
		<div
			style="position: absolute; z-index: 100000; height: 2046px; top: 0px; left: 0px; width: 100%; background: white; text-align: center;">
			<img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
		</div>
	</noscript>
	<div region="north" split="false" border="false"
		style="overflow: hidden; height: 73px; font-family: Verdana, 微软雅黑, 黑体">
		<div class="maintop2">
			<div class="maintop1"></div>
			<div style="width: auto; height: 45px; margin: 0; float: right;">
				<div class="maintop3"></div>
				<div class="maintop4">
					<a href="#1" style="text-decoration: none;"><b>控制面板</b></a> | 
					<a href="#2" id="helper" style="text-decoration: none;"><b>帮助</b></a> | 
					<a id="changePwd" href="#4" style="text-decoration: none;"><b>修改密码</b></a> | 
					<a id="logout" href="javascript:void(0)" style="text-decoration: none;"><b>退出</b></a>
					<div class="bs">
	                	<a class="styleswitch a3" style="CURSOR: pointer" title="天蓝色" rel="blue"></a>	
						<a class="styleswitch a2" style="CURSOR: pointer" title="竹绿色" rel="green"></a> 
						<a class="styleswitch a4" style="CURSOR: pointer" title="黑灰色" rel="gray"></a>	
						<a class="styleswitch a5" style="CURSOR: pointer" title="艳红色" rel="pink"></a>		
					</div>
				</div>
				<div class="maintop5"></div>
			</div>
		</div>
		<div class="maintop6">
			<div id="menu"
				style="width: auto; height: 22px; font-size: 12px; margin: 8px 0 0 8px; display: inline; float: left;">
				<c:forEach items="${flatlist}" var="tsysflat"
					varStatus="paStatus">
				<div id="menu${paStatus.index}" flatid="${tsysflat.flatid}" onclick="jump('${tsysflat.flaturl}', '${userinfo.loginname}', '${userinfo.userpw}', '${tsysflat.flatid}', '${ctx}')"
					style="width: auto; height: 22px; line-height: 22px; cursor: pointer; margin: 0 1px; display: inline; float: left;">
					<div menu="left" 
						style="width: 5px; height: 22px; background: url(js_plugins/jquery/images/menu_left.png) no-repeat; margin: 0; float: left;"></div>
					<div menu="cent"
						style="font-family:'微软雅黑', Courier, monospace;height: 22px; background: url(js_plugins/jquery/images/menu_cen.png) repeat; margin: 0; float: left;">
						${tsysflat.shortname}</div>
					<div menu="right"
						style="width: 5px; height: 22px; background: url(js_plugins/jquery/images/menu_right.png) no-repeat; margin: 0; float: left;"></div>
				</div>
				</c:forEach>
	</div>
	<div
		style="font-family:'微软雅黑', Courier New, monospace;width: auto; height: 22px; line-height: 22px; font-size: 13px; margin: 8px 8px 0 0; display: inline; float: right;">
		【当前登录用户】：${sessionScope.userinfo.tsysOrg.orgname}------${sessionScope.userinfo.username}</div>
	</div>
	</div>
	</div>
	<div region="south" split="false"
		style="height: 3px; background: #D2E0F2;">
	</div>
	<div region="west" hide="true" split="true" title="导航菜单"
		style="width: 168px;" id="west">
		<div id="nav" class="easyui-accordion" fit="true" border="false">
			<!--  导航内容 -->

		</div>

	</div>
	<div id="mainPanle" region="center"
		style="background: #EEEEEE; overflow-y: hidden">
		<div id="tabs" class="easyui-tabs" fit="true" border="false">
			<div title="欢迎使用"
				style="padding: 20px; overflow: hidden; color: red;">
				<h1 style="font-size: 24px;">正华进销存系统</h1>
			</div>
		</div>
	</div>
	<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>
	<div id="modifyWindow" style="width: 350px;height: 230px;" closed="true">
		<div id="window-body"></div>
	</div>
	<div id="loading" class="hih-mask-container" style="display: none;">
		<div class="hih-mask-txt tree-loading">加载中...</div>
	</div>
</body>
</html>