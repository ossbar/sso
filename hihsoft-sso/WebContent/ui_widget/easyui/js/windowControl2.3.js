/**
 * @author GodSon
 * http://www.btboys.com
 * date 2011-5-12 12:30	version 2.1
 * @How to use
 * var path = 'page/newFile.html';
 *	var confing = {
 *		url : path,
 *		title : "标题",
 *		width : 500,
 *		height : 150,
 *		maximizable : false,
 *		buttons : [{
 *				text : '继续>>',
 *				handler : function() {
 *					fun(GETWIN(this));
 *				}
 *		}]
 *	};
 *	var curDialogId = $.createWin(confing);
 *	销毁
 *  $(GETWIN(curDialogId)).destroy();
 */

/**
 * @param {Object} options
 * return windowId
 */
(function ($) {
    /**
		 * 创建UUID
		 */
    function S4() {
        return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
    }
    /**
		 * 生成windowId
		 */
    function CreateIndentityWindowId() {
        return "UUID-" + (S4() + S4() + "-" + S4() + "-" + S4() + "-" + S4() + "-" + S4() + S4() + S4());
    }
    /**
		 * 销毁
		 */
    function destroy(target) {
        $(target).dialog("destroy");
    }
		
    /**
		 * 获取当前操作的window
		 *  @param target 当前窗口的windowId 或者 当前窗口中的元素(element)
		 */
    function getWindow(target) {
        if (typeof target == "string") {
            return document.getElementById(target);
        } else {
            return $(target).closest(".window-body");
        }
    }
		
    //入口方法
    //入口方法
    $.createWin = function (options) {
        if (!options.url && !options.contents) {
            $.messager.alert("提示", "缺少必要参数!(url or content)");
            return false;
        }
			
        var windowId = CreateIndentityWindowId();
			
        if (options.winId) {
            windowId = options.winId;
        }else{
            options.winId = windowId;
        }
			
        //如果存在buttons(请看easyui对buttons的定义)，默认添加关闭按钮
        var defaultBtn = [{
            text : '关闭',
            handler : function () {
                $("#" + windowId).dialog("close");
            }
        }
        ];
        $.merge(options.buttons || [], defaultBtn);
			
        options = $.extend({}, $.createWin.defaults, options || {});
			
        if (options.isMax) {
            options.draggable = false;
            options.closed = true;
        }
			
        var dialog = $('<div/>').attr("id", windowId).text("加载中……");
			
        if (options.target != 'body') {
            options.inline = true;
        }
        dialog.appendTo($(options.target));
			
        dialog.dialog($.extend({},options,{
            onClose : function () {
                if (typeof options.onClose == "function") {
                    options.onClose.call(dialog);
                }
                destroy(this);
            },
            onMove : function (left, top) {
                if (typeof options.onMove == "function") {
                    options.onMove.call(dialog);
                }
                var o = $.data(this, 'panel').options;
                if (top < 0) {
                    $(this).dialog("move", {
                        "left" : left,
                        "top" : 0
                    });
                } else if (o.maximized) {
                    $(this).dialog("restore");
                    $(this).dialog("move", {
                        "left" : left + 100,
                        "top" : top
                    });
                }
                if (top > ($(o.target).height() - 20)) {
                    $(this).dialog("move", {
                        "left" : left,
                        "top" : ($(o.target).height() - 25)
                    });
                }
            }
        }));
			
        if (options.align) {
            var w = dialog.closest(".window");
            switch (options.align) {
                case "right":
                    dialog.dialog("move", {
                        left : w.parent().width() - w.width() - 10
                    });
                    break;
                case "tright":
                    dialog.dialog("move", {
                        left : w.parent().width() - w.width() - 10,
                        top : 0
                    });
                    break;
                case "bright":
                    dialog.dialog("move", {
                        left : w.parent().width() - w.width() - 10,
                        top : w.parent().height() - w.height() - 10
                    });
                    break;
                case "left":
                    dialog.dialog("move", {
                        left : 0
                    });
                    break;
                case "tleft":
                    dialog.dialog("move", {
                        left : 0,
                        top : 0
                    });
                    break;
                case "bleft":
                    dialog.dialog("move", {
                        left : 0,
                        top : w.parent().height() - w.height() - 10
                    });
                    break;
                case "top":
                    dialog.dialog("move", {
                        top : 0
                    });
                    break;
                case "bottom":
                    dialog.dialog("move", {
                        top : w.parent().height() - w.height() - 10
                    });
                    break;
            }
        }
			
        if (options.isMax) {
            dialog.dialog("maximize");
            dialog.dialog("open");
        }
			
        if (options.contents) {
            ajaxSuccess(options.contents);
        } else {
            if (!options.isIframe) {
                $.ajax({
                    url : options.url,
                    type : "POST",
                    data : options.data == null ? "" : options.data,
                    success : function (date) {
                        ajaxSuccess(date);
                    },
                    error : function () {
                        $.messager.alert("提示", "加载失败！");
                    }
                });
            } else {
                ajaxSuccess();
            }
        }
        return windowId;
			
        /**
			 * 页面加载成功处理
			 */
        function ajaxSuccess(date) {
            if (options.isIframe && !date) {
                dialog.find("div.dialog-content").html('<iframe width="100%" height="100%" frameborder="0" src="' + options.url + '" ></iframe>');
            } else {

                dialog.find("div.dialog-content").html(date);
            }
            $.parser.parse(dialog);
            options.onComplete.call(dialog, windowId);
        }
    };
		
    //关闭并销毁实体
    $.fn.destroy = function () {
        destroy(this);
    };
		
    window.GETWIN = getWindow;
		
    //默认参数
    $.createWin.defaults = $.extend({}, $.fn.dialog.defaults, {
        url : '', //窗口要加载的html片段地址
        data : '', //可带参数，data类型为jqurey.ajax的data参数类型
        target : 'body', //指定窗口打开的区域,是一个jq的选择器，例如#id
        height : 240,
        width : 400,
        collapsible : false,
        minimizable : false,
        maximizable : false,
        closable : true,
        modal : true,
        shadow : false,
        onComplete : function (windowId) {}
    //创建成功后的回调方法
    });
})(jQuery);
 