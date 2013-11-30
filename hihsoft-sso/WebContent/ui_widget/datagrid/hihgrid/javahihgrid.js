/**
 * 事件：onpagefirst,onpagelast,onpageprev,onpagenext
 * 		onpagerefresh,click,dblclick
 */
function initGrid(form,events){
	var form = form || $("form").get(0);
	events = events || {};
	if (!form) return;
	for (var e in events) {
		form.bind(e, events[e]);
	}
	var page = form.find("input[name='page']");
	var pages = form.find("input[name='pages']");
	var rows = form.find("select[name='rows']");
	if (rows.length == 0) rows = form.find("input[name='rows']");
	form.keypress(function(e) {
		if (e.keyCode == 13) {
			return false;
		}
	});
	var switchButton = function() {
		if (parseInt(page.val()) == 1) {
			first.addClass("l-btn-disabled");
			priv.addClass("l-btn-disabled");
		}
		if (parseInt(page.val()) == parseInt(pages.val()) || parseInt(pages.val()) <= 0) {
			last.addClass("l-btn-disabled");
			next.addClass("l-btn-disabled");
		}
	}
	
	var first = form.find("a[icon=pagination-first]").click(function(){
		if (parseInt(page.val()) == 1) return;
		page.val(1);
		switchButton();
		if (typeof events["onpagefirst"] == "function") {
			events.onpagefirst.call(form, {curPage : page.val(), pageSize : rows.val()});
		} else {
			form.submit();
		}
	});
	var last = form.find("a[icon=pagination-last]").click(function(){
		if (parseInt(page.val()) >= parseInt(pages.val())) return;
		page.val(pages.val());
		switchButton();
		if (typeof events["onpagelast"] == "function") {
			events.onpagelast.call(form, {curPage : page.val(), pageSize : rows.val()});
		} else {
			form.submit();
		}
	});
	var priv = form.find("a[icon=pagination-prev],a[name=page-priv]").click(function(){
		if (parseInt(page.val()) <= 1) return;
		page.val(parseInt(page.val()) - 1);
		switchButton();
		if (typeof events["onpageprev"] == "function") {
			events.onpageprev.call(form, {curPage : page.val(), pageSize : rows.val()});
		} else {
			form.submit();
		}
	});
	var next = form.find("a[icon=pagination-next],a[name=page-next]").click(function(){
		if (parseInt(page.val()) >= parseInt(pages.val())) return;
		page.val(parseInt(page.val()) + 1);
		switchButton();
		if (typeof events["onpagenext"] == "function") {
			events.onpagenext.call(form, {curPage : page.val(), pageSize : rows.val()});
		} else {
			form.submit();
		}
	});
	switchButton();
	form.find("a[name=page]").click(function() {
		page.val($(this).text());
		switchButton();
		form.submit();
	});
	page.change(function() {
		var val = $(this).val();
		if (isNaN(val)) {
			alert("请输入数字!");
			return;
		} else if (parseInt(val) > parseInt(pages.val())) {
			alert("不能超过最大页数！");
			return;
		} else if (parseInt(val) <= 0) {
			alert("请输入大于0的值！");
			return;
		}
		if (typeof events["onjump"] == "function") {
			events.onjump.call(form, {curPage : page.val(), pageSize : rows.val()});
		} else {
			form.submit();
		}
	});
	var freshMethod = function(){
		var row = rows.val();
		if (isNaN(row)) {
			alert("请输入数字!");
			return;
		} else if (parseInt(row) > 300) {
			alert("每页最多显示300条，请重新输入！");
			return;
		} else if (parseInt(row) <= 0) {
			alert("请输入大于0的值！");
			return;
		}
		rows.val(parseInt(row));
		if (typeof events["onpagerefresh"] == "function") {
			events.onpagerefresh.call(form, {curPage : page.val(), pageSize : rows.val()});
		} else {
			form.submit();
		}
	};
	form.find("a[icon=pagination-load]").click(freshMethod);
	rows.change(function(){
		page.val("1");
		freshMethod();
	});
	form.find("#selectAll").click(function() {
		var checked = this.checked;
		form.find(".checkbox").each(function(i) {
			$(this).children(":checkbox").attr("checked",checked);
			if (checked) {
				$(this).parent().addClass("data-grid-row-selected");
			} else {
				$(this).parent().removeClass("data-grid-row-selected");
			}
		});
	});
	var click = function(event){
		var chk = $(this).find(":checkbox");
		if (chk.size() > 0 && event.target.tagName != "INPUT") {
			chk.attr("checked", !chk.attr("checked"));
		}
		if (chk.attr("checked")) {
			$(this).addClass("data-grid-row-selected");
		} else {
			$(this).removeClass("data-grid-row-selected");
		}
		form.trigger("onrowselect", [form, $(this)]);
	};
	form.find(".data-grid TBODY").children().each(function(i) {
		if (i%2 != 0) $(this).attr("class","row-color");
		if (typeof events["click"] == "function") {
			form.trigger("click", [event]);
		} else {
			$(this).click(click);//行单击事件
		}
		//行双击事件
		if (typeof events["dblclick"] == "function") {
			form.trigger("dblclick", [$(this)]);
		}
	});
	var sortObj = toObject(form.find("input[name=orders]").val());
	var sortHandler = function(field) {
		var old = sortObj[field];
		sortObj = {};//这句代码可以控制只有一个排序条件有效
		if (!old) {
			sortObj[field] = "ASC";
		} else if (old == "ASC") {
			sortObj[field] = "DESC";
		}
		var sort = form.find("input[name='orders']");
		sort.val(serialize(sortObj));
		if (typeof events["onsort"] == "function") {
			form.trigger("onsort", sortObj);
		} else {
			form.submit();
		}
	};
	form.find(".data-grid-header").children().each(function(i) {
		var th = $(this);
		th.resizable({
			handles : "e,w",
			minWidth : 10
		});
		var field = th.attr("sortname");
		if (!field) return true;
		var span = $(this).children("span");
		if (span.length == 0) span = $("<span class='datagrid-sort-icon'>&nbsp;</span>").appendTo(this);
		var old = (sortObj[field] || "").toUpperCase();
		if (!old) {
			th.removeClass("datagrid-sort-asc datagrid-sort-desc").remove("span");
		} else if (old == "ASC") {
			th.addClass("datagrid-sort-asc").removeClass("datagrid-sort-desc");
		} else if (old == "DESC"){
			th.addClass("datagrid-sort-desc").removeClass("datagrid-sort-asc");
		}
		
		th.click(sortHandler.createDelegate(sortHandler, [field]));
	});
	if (typeof window.getSelected != "function") {
		window.getSelected = function(single,scope) {
			var f = scope || form;
			var chks = f.find(".checkbox").find(":checked");
			if (chks.size() == 0) {
				alert("请选择记录！");
				return;
			}
			if (single && chks.size() > 1) {
				alert("只能选择1条记录！");
				return;
			}
			var ids = new Array();
			chks.each(function(i) {
				var val = $(this).val();
				if (val && val != "on") {
					ids.push(val);
				}
			});
			return ids.join(",");
		};
	}
}
function serialize(obj) {
	var s = "";
	for (var p in obj) {
		s += "," + p + " " + obj[p];
	}
	return s == "" ? "" : s.substring(1);
}
function toObject(str) {
	var obj = {};
	if (!str) return obj;
	var strs = str.split(",");
	for (var i = 0; i < strs.length; i++) {
		var s = strs[i].split(" ");
		if (s.length == 2) {
			obj[s[0]] = s[1];
		}
	}
	return obj;
}
