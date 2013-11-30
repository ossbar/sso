/**
 * 为指定对象添加指定的属性
 * @param {Object} C 待添加的对象
 * @param {Object} D 要添加的属性
 * @param {Object} B 默认属性
 * @return {Object}
 */
function apply(C, D, B) {
	if (B) {
		apply(C, B);
	}
	if (C && D && typeof D == "object") {
		for (var A in D) {
			C[A] = D[A];
		}
	}
	return C;
};
apply(Function.prototype, {
	/**
	 * 为一个方法创建一个代理
	 * @param {} obj
	 * @param {} args
	 * @param {} appendArgs
	 * @return {}
	 */
	createDelegate : function(obj, args, appendArgs){
        var method = this;
        return function() {
            var callArgs = args || arguments;
            if(appendArgs === true){
                callArgs = Array.prototype.slice.call(arguments, 0);
                callArgs = callArgs.concat(args);
            }else if(typeof appendArgs == "number"){
                callArgs = Array.prototype.slice.call(arguments, 0);
                var applyArgs = [appendArgs, 0].concat(args);
                Array.prototype.splice.apply(callArgs, applyArgs);
            }
            return method.apply(obj || window, callArgs);
        };
    },
    /**
     * 使方法延迟执行
     * @param {} millis
     * @param {} obj
     * @param {} args
     * @param {} appendArgs
     * @return {Number}
     */
    defer : function(millis, obj, args, appendArgs){
        var fn = this.createDelegate(obj, args, appendArgs);
        if(millis){
            return setTimeout(fn, millis);
        }
        fn();
        return 0;
    }
});
apply(Array.prototype, {
	contains : function(obj) {
		return this.indexOf(obj) != -1;
	},
	indexOf : function(obj) {
		for(var i = 0; i < this.length; i++) {
			if (this[i] == obj) return i;
		}
		return -1;
	},
	remove : function(obj) {
		var index = this.indexOf(obj);
		if (index != -1) this.splice(index, 1);
	}
});
apply(String.prototype, {
	trim : function(){
        return String(this).replace(/^\s+|\s+$/g, "");
    }
});
/**
 * 获得一个HTML元素的绝对右位置
 * @param {} e
 * @return {}
 */
function getTop(e) {
	var offset = e.offsetTop;
	if (e.offsetParent != null) {
		offset += getTop(e.offsetParent);
	}
	return offset;
}
/**
 * 获得一个HTML元素的绝对左位置
 * @param {} e
 * @return {}
 */
function getLeft(e) {
	var offset = e.offsetLeft;
	if (e.offsetParent != null) {
		offset += getLeft(e.offsetParent);
	}
	return offset;
}
/**
 * 是否非空
 * @param {} v
 * @return {}
 */
function notNull(v) {
	return v != null && v != "" && typeof v != "undefined";
}
/**
 * 是否为空
 * @param {} v
 * @return {}
 */
function isNull(v) {
	return v == null || v == "" || typeof v == "undefined";
}
/**
 * 判断某个对象是否为数组
 * @param {Array} arr
 * @return {}
 */
function isArray(arr) {
	return (typeof arr == "object") && (typeof arr.length == "number")
				&& (typeof arr.slice == "function");
}
/**
 * 以当前时间生成ID
 * @return {}
 */
function generateId() {
	return new Date().getTime();
}
function dump(obj) {
	if (!obj) return;
	var s = "";
	if (isArray(obj)) {
		for (var i = 0; i < obj.length; i++) {
			s += "[" + i + ":" + obj[i] + "]\n";
		}
		return s;
	}
	for (var p in obj) {
		s += "[" + p + "=" + obj[p] + "]\n";
	}
	return s;
}
function log(obj) {
	if (!obj) return;
	var s = "";
	for (var p in obj) {
		s += "[" + p + "]\n";
	}
	return s;
}
/**
 * 启用/禁用容器中的控件
 * @param container
 * @param disable
 * @param exclude
 */
function switchFrom(container, disable, excludes) {
	container.find("input,select,textarea").each(function(){
		if (excludes && excludes.contains(this.id || this.name)) {
			return true;
		} else {
			$(this).attr("disabled", disable);
		}
	});
}
/**
 * 将JSON字符串转换成JS对象
 * @param json
 */
function decode(json) {
	if (typeof json != "string") return json;
	if (isNull(json)) return null;
	return eval("(" + json + ")");
}
/**
 * 无跳转提交FOR。（注意： form的action必须设置正确）
 * @param {HTMLElement} form 要提交的FORM
 * @param {Function} success 提交成功以后要执行的代码,有两个参数req,status
 * @param {Function} failure 提交失败要执行的代码,有两个参数req,status
 */
function submit(form, success, failure) {
	form = $(form);
	if (!form || form.size() == 0) return;
	var param = form.serialize();
	$.ajax({
		url : form.attr("action") || "",
		data : param,
		success : function(data, status) {
			if ($.isFunction(success))
				success.call(this, data, status);
		},
		error : function(data, status) {
			if ($.isFunction(failure))
				failure.call(this, data, status);
		},
		cache: false,
		dataType : "json",
		type : "POST",
		contentType : "application/x-www-form-urlencoded;charset=utf-8"
	});
}
function isSuccess(res, status) {
	var txt = res.responseText || res;
	if (txt) {
		var obj = decode(txt);
		return obj.success && status == "success";
	}
	return false;
}
$(function(){
	$(document).ajaxStop(function() {
	});
	$.messager.defaults.ok = "确定";
	$.messager.defaults.cancel = "取消";
	/**
	 * type为提示框的图标，有四种类型:error,question,info,warning
	 */
	window.alert = function(msg,type,callback) {
		$.messager.alert("提示", msg, type, callback);
	};
	/**
	 * 此段代码用于解决easyui中datagrid的复选框单击时不选中行的问题
	 */
	$.fn.datagrid.defaults.view = $.extend($.fn.datagrid.defaults.view, {
		onAfterRender : function(target) {
			var view = $(target).datagrid("getPanel").children("div.datagrid-view");
						var rows = view.find("div.datagrid-body tr[datagrid-row-index]");
			rows.find(":checkbox").each(function(i, chk) {
				(function(chk, index, target) {
					$(chk).click(function() {
						$(target).datagrid(chk.checked ? "selectRow" : "unselectRow", i);
					});
				})(chk, i, target);
			});
			view.find("div.datagrid-header-check :checkbox").click(function() {
				$("tr[datagrid-row-index]", target).find(":checkbox").attr("checked", this.checked);
				$(target).datagrid(this.checked ? "selectAll" : "unselectAll");
			});
		},
		renderRow: function(target, fields, frozen, rowIndex, rowData){
			var opts = $.data(target, 'datagrid').options;
			var cc = [];
			if (frozen && opts.rownumbers){
				var rownumber = rowIndex + 1;
				if (opts.pagination){
					rownumber += (opts.pageNumber-1)*opts.pageSize;
				}
				cc.push('<td class="datagrid-td-rownumber"><div class="datagrid-cell-rownumber">'+rownumber+'</div></td>');
			}
			for(var i=0; i<fields.length; i++){
				var field = fields[i];
				var col = $(target).datagrid('getColumnOption', field);
				var fieldSp =field.split(".");
				var dta = rowData[fieldSp[0]];
				for(var j = 1; j<fieldSp.length; j++){
					dta = dta[fieldSp[j]];
				}
				if (col){
					// get the cell style attribute
					var styleValue = col.styler ? (col.styler(dta, rowData, rowIndex)||'') : '';
					var style = col.hidden ? 'style="display:none;' + styleValue + '"' : (styleValue ? 'style="' + styleValue + '"' : '');
					
					cc.push('<td field="' + field + '" ' + style + '>');
					
					var style = 'width:' + (col.boxWidth) + 'px;';
					style += 'text-align:' + (col.align || 'left') + ';';
					style += opts.nowrap == false ? 'white-space:normal;' : '';
					
					cc.push('<div style="' + style + '" ');
					if (col.checkbox){
						cc.push('class="datagrid-cell-check ');
					} else {
						cc.push('class="datagrid-cell ');
					}
					cc.push('">');
					
					if (col.checkbox){
						cc.push('<input type="checkbox"/>');
					} else if (col.formatter){
						cc.push(col.formatter(dta, rowData, rowIndex));
					} else {
						cc.push(dta);
					}
					
					cc.push('</div>');
					cc.push('</td>');
				}
			}
			return cc.join('');
		}
	});
	/**
	 * 去掉弹出窗口最小化按钮
	 */
	$.extend($.fn.window.defaults, {
		title : "",
		minimizable : false
	});
	/**
	 * 分页条汉化
	 */
	$.extend($.fn.pagination.defaults, {
		pageList : [10, 15, 20, 30, 40, 50],
		pageSize : 15,
		beforePageText : "当前第",
		afterPageText : "页,共  {pages} 页",
		displayMsg : "共 {total} 条记录"
	});
	/**
	 * 加上默认的排序功能,以及默认的分页大小
	 */
	$.extend($.fn.datagrid.defaults, {
		onSortColumn : function(sort, order) {
			$(this).datagrid('load', {"orders" : sort+" "+order});
		},
		pageList : [10, 15, 20, 30, 40, 50],
		pageSize : 15,
		loadMsg : "数据加载中。。。",
		striped: true
	});
	/**
	 * 屏蔽退格键返回上一页
	 */
	if (typeof window.event != 'undefined') {
		document.onkeydown = function() {
			var type = event.srcElement.type;
			if (event.srcElement.readOnly) return false;
			var code = event.keyCode;
			return (code == 8 || (code != 8 && code != 13) || (type == 'text' && code != 13)
					|| (type == 'textarea') || (type == 'submit' && code == 13))
		}
	} else { // FireFox/Others
		document.onkeypress = function(e) {
			var type = e.target.localName.toLowerCase();
			var code = e.keyCode;
			if (e.target.readOnly) return false;
			return (code == 8 || (code != 8 && code != 13) || (type == 'text' && code != 13)
					|| (type == 'textarea') || (type == 'submit' && code == 13))
		}
	}
	/**
	 * 此段代码用于修改弹出窗口加载前，显示提示消息而不显示上一次加载的页面
	 */
	jQuery.fn.extend({
		load: function( url, params, callback ) {
			if ( typeof url !== "string" && _load ) {
				return _load.apply( this, arguments );
			// Don't do a request if no elements are being requested
			} else if ( !this.length ) {
				return this;
			}
			var off = url.indexOf( " " );
			if ( off >= 0 ) {
				var selector = url.slice( off, url.length );
				url = url.slice( 0, off );
			}
			// Default to a GET request
			var type = "GET";
			// If the second parameter was provided
			if ( params ) {
				// If it's a function
				if ( jQuery.isFunction( params ) ) {
					// We assume that it's the callback
					callback = params;
					params = undefined;
	
				// Otherwise, build a param string
				} else if ( typeof params === "object" ) {
					params = jQuery.param( params, jQuery.ajaxSettings.traditional );
					type = "POST";
				}
			}
			var self = this;
			self.html("加载中,请稍候...");
			// Request the remote document
			jQuery.ajax({
				url: url,
				type: type,
				dataType: "html",
				data: params,
				contentType : "application/x-www-form-urlencoded;charset=utf-8",
				// Complete callback (responseText is used internally)
				complete: function( jqXHR, status, responseText ) {
					// Store the response as specified by the jqXHR object
					responseText = jqXHR.responseText;
					// If successful, inject the HTML into all the matched elements
					if ( jqXHR.isResolved() ) {
						// #4825: Get the actual response in case
						// a dataFilter is present in ajaxSettings
						jqXHR.done(function( r ) {
							responseText = r;
						});
						// See if a selector was specified
						self.html( selector ?
							// Create a dummy div to hold the results
							jQuery("<div>")
								// inject the contents of the document in, removing the scripts
								// to avoid any 'Permission Denied' errors in IE
								.append(responseText.replace(rscript, ""))
	
								// Locate the specified elements
								.find(selector) :
	
							// If not, just inject the full result
							responseText );
					}
					if ( callback ) {
						self.each( callback, [ responseText, status, jqXHR ] );
					}
				}
			});
			return this;
		}
	});
	/**
	 * 将form的各个参数以对象的形式返回
	 * @param {Boolean} toString 是否以String形式返回
	 * @author xjf@2011-09-26 
	 */
	$.fn.extend({
		getFormParams : function(toString) {
			if (toString === true) {
				return this.each(function() {
					var param = this.serialize();
					return isNull(param) ? "" : "&" + param;
				});
			} else {
				var obj = {};
				this.each(function() {
					var param = $(this).serialize();
					var strs = param.split("&");
					$(strs).each(function(i, n){
						if (!n) return true;
						var kv = n.split("=");
						if (kv[1]) {
							var val = decodeURIComponent(kv[1]);
							if (obj[kv[0]] && isArray(obj[kv[0]])) {
								obj[kv[0]].push(val);
							} else if (obj[kv[0]] && !isArray(obj[kv[0]])){
//								obj[kv[0]] = [obj[kv[0]]];
//								obj[kv[0]].push(val);
								obj[kv[0]] = obj[kv[0]] + "," + val;
							} else {
								obj[kv[0]] = val;
							}
						}
					});
				});
				return obj;
			}
		},
		/**
		 * 自定义下拉列表共有4个参数：textField:文本字段，valueField:值字段，params:参数，addEmpty:是否添加一个空选项
		 * @author xjf@2011-09-27
		 */
		hihcombo : function(config) {
			if (!config.url) return;
			var txtfield = config.textField || 0;
			var valfield = config.valueField || 1;
			var params = config.params || {};
			this.each(function(i, select) {
				$.ajax({
					url : config.url,
					type : "POST",
					data : params,
					dataType : config.dataType || "json",
					success : function(datas, status) {
						var html = [];
						if (config.addEmpty) html.push("<option value=''>全部</option>");
						$.each(datas, function(j, data) {
							html.push("<option value='" + data[valfield] + "'>" + data[txtfield] + "</option>");
						});
						$(select).append(html.join(""));
					}
				});
			});
		},
		/**
		 * 将一个FORM中的字段以HTML的形式转换隐藏域,用于获取FORM中的参数
		 */
		getParamsHtml : function() {
			var html = [];
			this.each(function(i, form) {
				var f = $(form);
				f.find("input,select,textarea").each(function(j, n) {
					if (n.name && n.value && f.children(n.name).length == 0) {
						html.push("<input type='hidden' name='" + n.name + "' value='" + n.value + "'/>");
					}
				});
			});
			return html.join("");
		},
		appendParamsForm : function(formName) {
			this.each(function(i, form) {
				formName = $(formName);
				$(form).append(formName.getParamsHtml());
			});
		},
		sizeAndPos : function() {
			var th = $(this);
			var offset = th.offset();
			offset.height = th.height();
			offset.width = th.width();
			return offset;
		},
		setSize : function(sizeObj) {
			return this.each(function(i, el) {
						var e = $(el);
						e.height(sizeObj.height || e.height());
						e.width(sizeObj.width || e.width());
						e.offset(sizeObj || e.offset());
					});
		},
		mask : function(html, noText) {
			return this.each(function(i, el) {
				var exists = $.data(el, "masker");
				if (exists) return true;
				var e = $(el);
				var top = e.height() / 2 - 8;
				var msg = null;
				if (typeof html === 'boolean') noText = html;
				if (noText !== false) {
					msg = $("<span></span>").css({
						"margin-left" : "auto",
						"margin-right" : "auto",
						"margin-top" : top,
						"width" : "auto",
						"height" : "16px",
						"padding" : "12px 5px 10px 30px",
						"color" : "#222",
						"z-index" : "99999",
						"border" : "3px solid #447799",
						"background" : "#fff ",//url('/sso/js_plugins/jquery/js/themes/default/images/pagination_loading.gif') no-repeat scroll 5px 10px
						"display" : "inline-block"
					}).html(html || "加载中，请稍候...");
				}
				var div = $("<div></div>").css({
							"position" : "absolute",
							"background-color" : "#FDFCFE",
							"z-index" : "99998",
							"text-align" : "center"
						}).setSize(e.sizeAndPos())
						.appendTo(document.body);
				if (noText !== false) {
					div.append(msg);
				} else {
					div.css({"opacity": 0.7, "background" : "#fff "});//url('/sso/css/images/loading.gif') no-repeat center center
				}
				$.data(el, "masker", div);
			});
		},
		unmask : function() {
			return this.each(function(i, el) {
				var div = $.data(el, "masker");
				$(div).remove();
				$.removeData(el, "masker");
			});
		}
	});
	$.extend({
		/**
		 * 发起一次ajax请求
		 * @author xjf@2011-11-02
		 */
		request : function(url, params, success, failure) {
			$.ajax({
				url : url || "",
				data : params,
				success : function(data, status) {
					if (typeof success == "function") {
						success.call(this, data, status);
					}
				},
				error : function(data, status) {
					if (typeof failure == "function") {
						failure.call(this, data, status);
					}
				},
				cache: false,
				dataType : "json",
				type : "POST",
				contentType : "application/x-www-form-urlencoded;charset=utf-8"
			});
		},
		cookie : function(name, value, options) { 
		    if (typeof value != 'undefined') { // name and value given, set cookie 
		        options = options || {}; 
		        if (value === null) { 
		            value = ''; 
		            options.expires = -1; 
		        } 
		        var expires = ''; 
		        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) { 
		            var date; 
		            if (typeof options.expires == 'number') { 
		                date = new Date(); 
		                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000)); 
		            } else { 
		                date = options.expires; 
		            } 
		            expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE 
		        } 
		        var path = options.path ? '; path=' + options.path : ''; 
		        var domain = options.domain ? '; domain=' + options.domain : ''; 
		        var secure = options.secure ? '; secure' : ''; 
		        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join(''); 
		    } else { // only name given, get cookie 
		        var cookieValue = null; 
		        if (document.cookie && document.cookie != '') { 
		            var cookies = document.cookie.split(';'); 
		            for (var i = 0; i < cookies.length; i++) { 
		                var cookie = jQuery.trim(cookies[i]); 
		                // Does this cookie string begin with the name we want? 
		                if (cookie.substring(0, name.length + 1) == (name + '=')) { 
		                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
		                    break; 
		                } 
		            } 
		        } 
		        return cookieValue; 
		    } 
		}
	});
	/**
	 * 设置默认的ajax选项，用以全局监控
	 */
	$.ajaxSetup({
		beforeSend　: function(xhr) {
			if (this.dataType == "html" || this.dataType == "script") return;
			$('body').mask(false);
		},
		complete : function() {
			if (this.dataType == "html" || this.dataType == "script") return;
			$('body').unmask();
		}
	});
	/**
	 * 表单提交时显示进度条，防止重复提交
	 */
	$("form").live('submit', function() {
		$(document.body).mask(false);
	});
	
	//此处是扩展tree的两个方法.
    $.extend($.fn.tree.methods,{
        getCheckedExt: function(jq){//扩展getChecked方法,使其能实心节点也一起返回
            var checked = $(jq).tree("getChecked");
            var checkbox2 = $(jq).find("span.tree-checkbox2").parent();
            $.each(checkbox2,function(){
                var node = $.extend({}, $.data(this, "tree-node"), {
                    target : this
                });
                checked.push(node);
            });
            return checked;
        },
        getSolidExt:function(jq){//扩展一个能返回实心节点的方法
            var checked =[];
            var checkbox2 = $(jq).find("span.tree-checkbox2").parent();
            $.each(checkbox2,function(){
                var node = $.extend({}, $.data(this, "tree-node"), {
                    target : this
                });
                checked.push(node);
            });
            return checked;
        }
    });
	
});
function getSelectedBy(selected, field) {
	var arr = [];
	for (var i = 0; i < selected.length; i++) {
		var v = selected[i][field];
		if (v) arr.push(v);
	}
	return arr.join(",");
}
function getSelectRow(target, callback) {
	if (isNull(target)) return target;
	var checked = $(target).children("tbody").find("tr");
	var datas = [];
	checked.each(function(i, tr) {
		var obj = {};
		if ($(tr).find(":checked").length == 0) return true;
		$(tr).find("td").each(function(j, td) {
			//if (callback && callback.call(td, j, td) === false) return true;
			obj[$(td).attr("field")] = $(td).children("input").val() || $(td).text();
		});
		datas.push(obj);
	});
	return datas;
}
function formartCardNo(cardno) {
	if (isNull(cardno)) return "";
	var s = "";
	for (var i = 0; i < cardno.length; i++) {
		if (i > 0 && i % 4 == 0) {
			s += " ";
		}
		s += cardno.charAt(i);
	}
	return s;
}
function parseDate(str) {
	if (!str) return str;
	str =  str.replace(/-/g,"/");
	var date = new Date(str);
	return date;
}
function isTimeout(str) {
	if (!str) return
	var date = parseDate(str);
	if (!date) return;
	var now = new Date();
	var day = parseInt(Math.abs(now  -  date)  /  1000  /  60  /  60  /24);
	return day >= 1;
}
/**
 * 打印指定的DIV中的内容
 * @param {String} divId
 * @return {Boolean}
 */
function printDiv(divId){
	var head = $("head").attr("innerHTML");
	var headstr = "<html><head>"+head+"</head><body>";
	var footstr = "</body></html>";
	var newstr = document.getElementById(divId).innerHTML;
	var oldstr = document.body.innerHTML;
	document.body.innerHTML = headstr+newstr+footstr;
	window.print(); 
	document.body.innerHTML = oldstr;
	return false;
}
/**
 * 显示一个用户选择框
 * @param {Function} onOk 在选择好用户，点击确定时调用，参数为选择的用户，如果没有选择则为空字符串
 * @param {Number} width
 * @param {Number} height
 * @param {String} title
  * @param {isSingle} true/false是只能选一个
 */
function showUserWindow(onOk, params, width, height, title, isSingle, noTree,
		canEmpty) {
	var win;
	var url = "/sso/js_plugins/custom/selectuser.jsp";
	if (noTree) {
		url += "?noTree=noTree";
	}
	params = params || {};
	var queue = [];
	var menu = $("<div/>").appendTo('body').width("100px")
			.append("<div iconCls='icon-ok' add>标记为已选</div>")
			.append("<div class='menu-sep'></div>")
			.append("<div iconCls='icon-remove' remove>清空队列</div>").menu({
				onClick : function(item) {
					if (item.iconCls == "icon-ok") {
						var sels = $("#userGrid").datagrid("getSelections");
						for (var i = 0; i < sels.length; i++) {
							if (queue.indexOf(sels[i]) == -1) {
								queue.push(sels[i]);
							}
						}
						showTip("标记成功！");
						$("#selectedUsers").text("已选用户："
								+ getSelectedBy(queue, "username"));
					} else {
						queue.splice(0, queue.length);
						showTip("已选队列已清空！");
						$("#userGrid").datagrid("clearSelections");
						$("#selectedUsers").empty();
					}
				}
			});
	$.createWin({
		title : title || "选择用户",
		url : url,
		data : params,
		height : height || 480,
		width : width || 770,
		shadow : true,
		bgiframe : true,
		buttons : [{
					text : '确定',
					handler : function() {
						var selected = $("#userGrid").datagrid("getSelections");
						for (var i = 0; i < selected.length; i++) {
							var s = selected[i];
							if (queue.indexOf(s) != -1) {
								selected.splice(i, 1);
							}
						}
						var len = selected.length + queue.length;
						if (canEmpty === true && len == 0) {
							showTip("请选择一个用户");
							return;
						}
						if (isSingle === true && len > 1) {
							showTip("一次只能选择一个用户");
							return;
						}
						if (typeof onOk == "function") {
							onOk.call(this, queue.concat(selected), win);
						}
					}
				}],
		onComplete : function(wid) {
			win = $(this);
			var orgTree, grid, dutyTree, param = {}, form = $("form[name=queryForm]");
			if ($("#orgTree").length > 0) {
				orgTree = $("#orgTree").tree({
					url : "/sso/tsysLoginController.do?method=getAssignedOrgTree&full="
							+ params.full,
					onClick : function(n) {
						param = {
							"orgId" : n.id
						};
						grid.datagrid("reload");
					}
				});
				dutyTree = $("#dutyTree").tree({
							url : "/sso/tsysLoginController.do?method=getDutyTree",
							onClick : function(n) {
								param = {
									"filter_dutyid" : n.id
								};
								grid.datagrid("reload");
							}
						});
			}

			grid = $("#userGrid").datagrid({
				fit : true,
				striped : true,
				border : false,
				pageList : [10, 15, 20, 30, 40, 50],
				pageSize : 15,
				url : "/sso/tsysLoginController.do?method=getUserToJson&full="
						+ params.full,
				loadMsg : "数据加载中。。。",
				queryParams : params,
				columns : [[{
							field : "userid",
							checkbox : true
						}, {
							field : 'username',
							title : '用户名',
							width : 180,
							sortable : true
						}]],
				onBeforeLoad : function(options) {
					var p = form.getFormParams();
					apply(options, param, p);
				},
				pagination : true,
				onRowContextMenu : function(e, i, row) {
					e.preventDefault();
					grid.datagrid("selectRow", i);
					menu.menu("show", {
								left : e.clientX,
								top : e.clientY
							});
				}
			});
			$("#btn-query").click(function() {
						param = {};
						grid.datagrid("reload");
					})
			$("#btn-reset").click(function() {
						form[0].reset();
					});
		}
	});
}
/**
 * 上传文件通用弹出窗口
 * @author xjf
 */
function showUploader(config) {
	var win;
	var uploader;
	if (!config || !config.uploadUrl) {
		alert("上传地址未设置！");
		return;
	}
	//默认选项
	var defaults = {
		params : {},//要传递的参数
		height : 450,//窗口的高度
		width : 415,//窗口的宽度
		uploadUrl : "",
		exts : '*.*',//选择文件时允许的文件类型
		descs : '所有文件',//文件类型描述
		onFileDone : function() {},//当有文件上传完成时触发
		onAllDone : function() {},//当所有文件上传完成时触发
		onError : function(file,errorCode,errorMsg) {},//当上传错误时触发
		onBeforeUpload : function() {},//点击上传按钮时,文件即将上传前触发
		onClear : function() {}//点击清除队列时触发
	};
	var config = $.extend(defaults, config);
	$.createWin({
		title : "上传文件",
		url : "./common/uploadfile.jsp",
		data : config.params,
		height : config.height,
		width : config.width,
		shadow : true,
		buttons : [],
		bgiframe : true,
		onComplete : function(w) {
			win = $(this);
			uploader = $('#file_upload');
			uploader.uploadify({
				'swf' : './js_plugins/upload/uploadify.swf',
				'uploader' : config.uploadUrl,
				'cancelImage' : './js_plugins/upload/uploadify-cancel.png',
				'folder' : '/',
				'buttonText' : '选择文件',
				'queueID' : 'fileDiv',
				'auto' : false,
				'checkExisting' : false,
				'postData' : config.params,
				'queueSizeLimit' : 20,
				'fileDataName' : 'file',
				'multi' : true,
				'simUploadLimit' : 20,
				'onUploadSuccess' : function(f, data, response) {
					config.onFileDone.call(win, f, data, response);
				},
				'onQueueComplete' : function(state) {
					config.onAllDone.call(win, state);
				},
				'onUploadError' : config.onError,
				'fileTypeExts' : config.exts,
				'fileTypeDesc' : config.descs
			});
			$("#btnUpload").click(function() {
				if (config.onBeforeUpload.call(this, win, uploader) !== false) {
					uploader.uploadifyUpload('*');
				}
			});
			$("#btnCancel").click(function() {
				if (config.onClear.call(this, win, uploader) !== false) {
					uploader.uploadifyCancel('*');
				}
			});
		}
	});
}

function showTip(txt) {
	$.messager.show({
		title : "提示",
		msg : txt,
		timeout : 2200
	});
}
