
function jump(url, u, p, f, ctx) {
	url = encodeURI(encodeURI(url + "/?method=index&u=" + u + "&p=" + p + "&f=" + f));
	$.ajax({
		url : url + "&test=1",
		async : false,
		success : function(req, status) {
			$(document.body).mask();
			var method = (f == curFlatId ? "index&u=" + u + "&p=" + p + "&f=" + f : "logout&jumpTo="+encodeURIComponent(url));
			window.location.href = ctx + "/tsysLoginController.do?method=" + method;
		},
		error : function() {
			alert("该系统正在维护中，暂时无法使用");
		}
	});
}
function getMenus(baseUrl, flatid) {
	if (!baseUrl || !flatid)
		return;
	$.ajax({
		url : baseUrl + "/tsysLoginController.do?method=getMenus&flatid="
				+ flatid,
		async : false,
		success : function(data) {
			var panels = $("#nav").accordion("panels");
			$(panels).each(function(i, t) {
				$("#nav").accordion("remove", t.panel("options").title);
			});
			var tabs = $('#tabs').tabs("tabs");
			$(tabs).each(function(i, t) {
				$('#tabs').tabs("close", t.panel("options").title);
			});
			initMenus(data);
		},
		error : function() {
			alert("加载菜单出错，请联系管理员！");
		},
		dataType : "json"
	});
}
function getHelpMenu(ctx) {
	$.ajax({
		url : ctx + "/tsysLoginController.do?method=getHelpMenu",
		success : function(data) {
			var menu = $("<div></div>");
			$.each(data, function(i, d) {
				menu.append("<div id='" + d.uploadid + "'>"+d.secondName+"</div>");
			});
			menu.children("div").width(200);
			menu.menu({onClick : function(item) {
				window.open(ctx + "/tsysLoginController.do?method=downloadFile&fileId="+item.id);
			}});
			if ($.isArray(data) && data.length > 0) {
				$("#helper").click(function(e) {
					menu.menu("show", {
						left : e.pageX,
						top : e.pageY
					});
				});
			} else {
				$("#helper").click(function(e) {
					alert("暂无帮助信息");
				});
			}
		}
	})
}
function initMenus(_menus) {
	InitLeftMenu(_menus);
	tabClose();
	tabCloseEven();

	$('#tabs').tabs({
		border : false,
		onBeforeClose : function(title) {
			var curTab = $('#tabs').tabs('getTab', title);
			var iframe = $(curTab).find("iframe");
			var doc = iframe[0].contentWindow || iframe[0].contentDocument;
			doc.document.write('');
			doc.document.close();
			iframe[0].src = "javascript:void(0)";
			doc.close();
			doc = null;
			iframe[0].parentNode.removeChild(iframe[0]);
			iframe.remove();
			iframe = null;
			curTab = null;
		}
	});
}
// 初始化左侧
function InitLeftMenu(menus) {
	if (!menus || !menus.length)
		return;
	var handler = function(node) {
		var attr = node.attributes || {};
		if (!attr.url) return;
		addTab(node.text, attr.url, "icon "+(node.iconCls || "icon-ok"));
	};
	var nav = $('#nav');
	$.each(menus, function(i, n) {
		if (!n.children)
			return true;
		nav.accordion('add', {
			title : n.text,
			content : "<ul></ul>",
			iconCls : '' + (n.iconCls || "")
		});
		var p = nav.accordion('getPanel', n.text);
		p.find("ul").tree({
			data : n.children,
			onClick : handler
		});
		nav.find(".tree-icon")
			.removeClass("tree-file tree-folder")
			.css({"height" : "18px", "vertical-align" : "middle"});
	});

	// 选中第一个
	var panels = nav.accordion('panels');
	if (!panels.length)
		return;
	var t = panels[0].panel('options').title;
	nav.accordion('select', t);
}
// 获取左侧导航的图标
function getIcon(_menus, menuid) {
	var icon = 'icon ';
	$.each(_menus.menus, function(i, n) {
		$.each(n.menus, function(j, o) {
			if (o.menuid == menuid) {
				icon += o.icon;
			}
		});
	});

	return icon;
}

function addTab(subtitle, url, icon) {
	if (!$('#tabs').tabs('exists', subtitle)) {
		$('#tabs').tabs('add', {
			title : subtitle,
			content : createFrame(url),
			closable : true,
			icon : icon,
			border : false,
			fit : true
		});
	} else {
		$('#tabs').tabs('select', subtitle);
		//$('#mm-tabupdate').click();
	}
	tabClose();
}

function createFrame(url) {
	var s = '<iframe scrolling="auto" frameborder="0"  src="' + url
			+ '" style="width:100%;height:100%;"></iframe>';
	return s;
}

function tabClose() {
	/* 双击关闭TAB选项卡 */
	$(".tabs-inner").dblclick(function() {
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close', subtitle);
	});
	/* 为选项卡绑定右键 */
	$(".tabs-inner").bind('contextmenu', function(e) {
		$('#mm').menu('show', {
					left : e.pageX,
					top : e.pageY
				});

		var subtitle = $(this).children(".tabs-closable").text();

		$('#mm').data("currtab", subtitle);
		$('#tabs').tabs('select', subtitle);
		return false;
	});
}
// 绑定右键菜单事件
function tabCloseEven() {
	// 刷新
	$('#mm-tabupdate').click(function() {
		var currTab = $('#tabs').tabs('getSelected');
		var f = currTab.find("iframe");
		if (f.length) {
			f.attr("src", f.attr("src"));
		}
	});
	// 关闭当前
	$('#mm-tabclose').click(function() {
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close', currtab_title);
	});
	// 全部关闭
	$('#mm-tabcloseall').click(function() {
		$('.tabs-inner span').each(function(i, n) {
					var t = $(n).text();
					$('#tabs').tabs('close', t);
				});
	});
	// 关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function() {
		$('#mm-tabcloseright').click();
		$('#mm-tabcloseleft').click();
	});
	// 关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function() {
		var nextall = $('.tabs-selected').nextAll();
		if (nextall.length == 0) {
			// msgShow('系统提示','后边没有啦~~','error');
			alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i, n) {
					var t = $('a:eq(0) span', $(n)).text();
					$('#tabs').tabs('close', t);
				});
		return false;
	});
	// 关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function() {
		var prevall = $('.tabs-selected').prevAll();
		if (prevall.length == 0) {
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i, n) {
					var t = $('a:eq(0) span', $(n)).text();
				
					$('#tabs').tabs('close', t);
				});
		return false;
	});
	// 退出
	$("#mm-exit").click(function() {
		$('#mm').menu('hide');
	});
}

// 弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}
