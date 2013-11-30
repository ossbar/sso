/*
 * Flexigrid for jQuery - New Wave Grid
 * 
 * Copyright (c) 2008 Paulo P. Marinas (webplicity.net/flexigrid) Dual licensed
 * under the MIT (MIT-LICENSE.txt) and GPL (GPL-LICENSE.txt) licenses.
 * 
 * $Date: 2008-07-14 00:09:43 +0800 (Tue, 14 Jul 2008) $
 */

(function($) {

	$.addFlex = function(t, p) {

		if (t.grid)
			return false; // return if already exist

		// apply default properties
		p = $.extend({
			height : 'auto', // 插件的高度，单位为px
			width : 'auto', // 宽度值，auto表示根据每列的宽度自动计算
			striped : true, // 是否显示斑纹效果，默认是奇偶交互的形式
			novstripe : false,
			minwidth : 30, // 列的最小宽度
			minheight : 80, // 列的最小高度
			resizable : true, // 是否可伸缩
			url : false, // ajax方式对应的url地址
			method : 'POST', // 数据发送方式
			dataType : 'xml', // 数据加载的类型
			checkbox : false,// 是否要多选框
			errormsg : '连接错误!',// 错误提示信息
			usepager : false, // 是否分页
			nowrap : true, // 是否不换行
			page : 1, // 默认当前页
			total : 1, // 总页面数
			useRp : true, // 是否可以动态设置每页显示的结果数
			rp : 15, // 每页默认的结果数
			rpOptions : [10, 15, 20, 30, 40,50,200],// 可选择设定的每页结果数
			title : false,// 是否包含标题
			pagestat : '显示第 {from} 条到 {to} 条,共 {total} 条数据',// 显示当前页和总页面的样式
			procmsg : '正在处理,请稍候 ...',// 正在处理的提示信息
			query : '',// 搜索查询的条件
			qtype : '',// 搜索查询的类别
			nomsg : '没有数据存在!',// 无结果的提示信息
			minColToggle : 1, // 允许显示的最小列数
			showToggleBtn : true, // 是否允许显示隐藏列，该属性有bug设置成false点击头脚本报错
			hideOnSubmit : true,// 隐藏提交
			autoload : true,// 自动加载
			blockOpacity : 0.5,// 透明度设置
			onToggleCol : false,// 当在行之间转换时，可在此方法中重写默认实现，基本无用
			onChangeSort : false,// 当改变排序时，可在此方法中重写默认实现，自行实现客户端排序
			onSuccess : false,// 成功后执行
			onSubmit : false,
			onChecked:false,
			checkCommd:false,
			clickId:false,
			radio : false
			  // 调用自定义的计算函数
		  }, p);

		$(t).show() // show if hidden
		    .attr({
			        cellPadding : 0,
			        cellSpacing : 0,
			        border : 0
		        }) // remove padding and spacing
		    .removeAttr('width') // remove width properties
		;

		// create grid class
		var g = {
			hset : {},
			rePosDrag : function() {

				var cdleft = 0 - this.hDiv.scrollLeft;
				if (this.hDiv.scrollLeft > 0)
					cdleft -= Math.floor(p.cgwidth / 2);
				$(g.cDrag).css({
					    top : g.hDiv.offsetTop + 1
				    });
				var cdpad = this.cdpad;

				$('div', g.cDrag).hide();

				$('thead tr:first th:visible', this.hDiv).each(function() {
					    var n = $('thead tr:first th:visible', g.hDiv).index(this);

					    var cdpos = parseInt($('div', this).width());
					    var ppos = cdpos;
					    if (cdleft == 0)
						    cdleft -= Math.floor(p.cgwidth / 2);
					    cdpos = cdpos + cdleft + cdpad;
					    
					    if (p.checkbox) {
						    // add checkbox width
						    $('div:eq(' + n + ')', g.cDrag).css({
							        'left' : cdpos + 22 + 'px'
						        }).show();
					    } else {
						    $('div:eq(' + n + ')', g.cDrag).css({
							        'left' : cdpos + 'px'
						        }).show();
					    }
					    
					    if(p.radio) {
					    	$('div:eq(' + n + ')', g.cDrag).css({
						        'left' : cdpos + 22 + 'px'
					        }).show();
					    }else {
					    	$('div:eq(' + n + ')', g.cDrag).css({
						        'left' : cdpos + 'px'
					        }).show();
					    }

					    cdleft = cdpos;
				    });

			},
			fixHeight : function(newH) {
				newH = false;
				if (!newH)
					newH = $(g.bDiv).height();
				var hdHeight = $(this.hDiv).height();
				$('div', this.cDrag).each(function() {
					    $(this).height(newH + hdHeight);
				    });

				var nd = parseInt($(g.nDiv).height());

//				if (nd > newH)
//					$(g.nDiv).height(newH).width(200);
				//add by zhujw修复显示和隐藏不能定位的问题
				if (newH > 0 && nd > newH) 
					$(g.nDiv).height(newH).width('auto');
				else
					$(g.nDiv).height('auto').width('auto');

				$(g.block).css({
					    height : newH,
					    marginBottom : (newH * -1)
				    });

				var hrH = g.bDiv.offsetTop + newH;
				if (p.height != 'auto' && p.resizable)
					hrH = g.vDiv.offsetTop;
				$(g.rDiv).css({
					    height : hrH
				    });

			},
			dragStart : function(dragtype, e, obj) { // default drag function start

				if (dragtype == 'colresize') // column resize
				{
					$(g.nDiv).hide();
					$(g.nBtn).hide();
					var n = $('div', this.cDrag).index(obj);
					var ow = $('th:visible div:eq(' + n + ')', this.hDiv).width();
					$(obj).addClass('dragging').siblings().hide();
					$(obj).prev().addClass('dragging').show();

					this.colresize = {
						startX : e.pageX,
						ol : parseInt(obj.style.left),
						ow : ow,
						n : n
					};
					$('body').css('cursor', 'col-resize');
				} else if (dragtype == 'vresize') // table resize
				{
					var hgo = false;
					$('body').css('cursor', 'row-resize');
					if (obj) {
						hgo = true;
						$('body').css('cursor', 'col-resize');
					}
					this.vresize = {
						h : p.height,
						sy : e.pageY,
						w : p.width,
						sx : e.pageX,
						hgo : hgo
					};

				}

				else if (dragtype == 'colMove') // column header drag
				{
					$(g.nDiv).hide();
					$(g.nBtn).hide();
					this.hset = $(this.hDiv).offset();
					this.hset.right = this.hset.left + $('table', this.hDiv).width();
					this.hset.bottom = this.hset.top + $('table', this.hDiv).height();
					this.dcol = obj;
					this.dcoln = $('th', this.hDiv).index(obj);

					this.colCopy = document.createElement("div");
					this.colCopy.className = "colCopy";
					this.colCopy.innerHTML = obj.innerHTML;
					if ($.browser.msie) {
						this.colCopy.className = "colCopy ie";
					}
					$(this.colCopy).css({
						    position : 'absolute',
						    float : 'left',
						    display : 'none',
						    textAlign : obj.align
					    });
					$('body').append(this.colCopy);
					$(this.cDrag).hide();

				}

				$('body').noSelect();

			},
			dragMove : function(e) {

				if (this.colresize) // column resize
				{
					var n = this.colresize.n;
					var diff = e.pageX - this.colresize.startX;
					var nleft = this.colresize.ol + diff;
					var nw = this.colresize.ow + diff;
					if (nw > p.minwidth) {
						$('div:eq(' + n + ')', this.cDrag).css('left', nleft);
						this.colresize.nw = nw;
					}
				} else if (this.vresize) // table resize
				{
					var v = this.vresize;
					var y = e.pageY;
					var diff = y - v.sy;

					if (!p.defwidth)
						p.defwidth = p.width;

					if (p.width != 'auto' && !p.nohresize && v.hgo) {
						var x = e.pageX;
						var xdiff = x - v.sx;
						var newW = v.w + xdiff;
						if (newW > p.defwidth) {
							this.gDiv.style.width = newW + 'px';
							p.width = newW;
						}
					}

					var newH = v.h + diff;
					if ((newH > p.minheight || p.height < p.minheight) && !v.hgo) {
						this.bDiv.style.height = newH + 'px';
						p.height = newH;
						this.fixHeight(newH);
					}
					v = null;
				} else if (this.colCopy) {
					$(this.dcol).addClass('thMove').removeClass('thOver');
					if (e.pageX > this.hset.right || e.pageX < this.hset.left
					    || e.pageY > this.hset.bottom || e.pageY < this.hset.top) {
						// this.dragEnd();
						$('body').css('cursor', 'move');
					} else
						$('body').css('cursor', 'pointer');
					$(this.colCopy).css({
						    top : e.pageY + 10,
						    left : e.pageX + 20,
						    display : 'block'
					    });
				}

			},
			dragEnd : function() {
				if (this.colresize) {
					var n = this.colresize.n;
					var nw = this.colresize.nw;

					$('th:visible div:eq(' + n + ')', this.hDiv).css('width', nw);
					$('tr', this.bDiv).each(function() {
						    $('td:visible div:eq(' + n + ')', this).css('width', nw);
					    });
					this.hDiv.scrollLeft = this.bDiv.scrollLeft;

					$('div:eq(' + n + ')', this.cDrag).siblings().show();
					$('.dragging', this.cDrag).removeClass('dragging');
					this.rePosDrag();
					this.fixHeight();
					this.colresize = false;
				} else if (this.vresize) {
					this.vresize = false;
				} else if (this.colCopy) {
					$(this.colCopy).remove();
					if (this.dcolt != null) {
						if (this.dcoln > this.dcolt)

							$('th:eq(' + this.dcolt + ')', this.hDiv).before(this.dcol);
						else
							$('th:eq(' + this.dcolt + ')', this.hDiv).after(this.dcol);

						this.switchCol(this.dcoln, this.dcolt);
						$(this.cdropleft).remove();
						$(this.cdropright).remove();
						this.rePosDrag();

					}

					this.dcol = null;
					this.hset = null;
					this.dcoln = null;
					this.dcolt = null;
					this.colCopy = null;

					$('.thMove', this.hDiv).removeClass('thMove');
					$(this.cDrag).show();
				}
				$('body').css('cursor', 'default');
				$('body').noSelect(false);
			},
			toggleCol : function(cid, visible) {

				var ncol = $("th[axis='col" + cid + "']", this.hDiv)[0];
				var n = $('thead th', g.hDiv).index(ncol);
				var cb = $('input[value=' + cid + ']', g.nDiv)[0];

				if (visible == null) {
					visible = ncol.hide;
				}

				if ($('input:checked', g.nDiv).length < p.minColToggle && !visible)
					return false;

				if (visible) {
					ncol.hide = false;
					$(ncol).show();
					cb.checked = true;
				} else {
					ncol.hide = true;
					$(ncol).hide();
					cb.checked = false;
				}

				$('tbody tr', t).each(function() {
					    if (visible)
						    $('td:eq(' + n + ')', this).show();
					    else
						    $('td:eq(' + n + ')', this).hide();
				    });

				this.rePosDrag();

				if (p.onToggleCol)
					p.onToggleCol(cid, visible);

				return visible;
			},
			switchCol : function(cdrag, cdrop) { // switch columns

				$('tbody tr', t).each(function() {
					if (cdrag > cdrop)
						$('td:eq(' + cdrop + ')', this).before($('td:eq(' + cdrag + ')',
						    this));
					else
						$('td:eq(' + cdrop + ')', this).after($('td:eq(' + cdrag + ')',
						    this));
				});

				// switch order in nDiv
				if (cdrag > cdrop)
					$('tr:eq(' + cdrop + ')', this.nDiv).before($('tr:eq(' + cdrag + ')',
					    this.nDiv));
				else
					$('tr:eq(' + cdrop + ')', this.nDiv).after($('tr:eq(' + cdrag + ')',
					    this.nDiv));

				if ($.browser.msie && $.browser.version < 7.0)
					$('tr:eq(' + cdrop + ') input', this.nDiv)[0].checked = true;

				this.hDiv.scrollLeft = this.bDiv.scrollLeft;
			},
			scroll : function() {
				this.hDiv.scrollLeft = this.bDiv.scrollLeft;
				this.rePosDrag();
			},
			addData : function(data) { // parse data

				if (p.preProcess)
					data = p.preProcess(data);

				$('.pReload', this.pDiv).removeClass('loading');
				this.loading = false;

				if (!data) {
					$('.pPageStat', this.pDiv).html(p.errormsg);
					return false;
				}

				if (p.dataType == 'xml')
					p.total = +$('rows total', data).text();
				else
					p.total = data.total;

				if (p.total == 0) {
					$('tr, a, td, div', t).unbind();
					$(t).empty();
					p.pages = 1;
					p.page = 1;
					this.buildpager();
					$('.pPageStat', this.pDiv).html(p.nomsg);
					return false;
				}

				p.pages = Math.ceil(p.total / p.rp);

				if (p.dataType == 'xml')
					p.page = +$('rows page', data).text();
				else
					p.page = data.page;

				this.buildpager();

				// build new body
				var tbody = document.createElement('tbody');
				// 修改json格式
				if (p.dataType == 'json') {
					$.each(data.rows, function(i, row) {
						    var tr = document.createElement('tr');
						    if (i % 2 && p.striped)
							    tr.className = 'erow';
						    if (row.id)
							    tr.id = 'row' + row.id;
						    // by anson
						    var tdVal = [];
						    // 给每行添加id
						    if (p.rowId) {
							    $.each(data.rows[i], function(x, y) {
								        if (p.rowId == x) {
									        tr.setAttribute('id', y);
								        }
							        })
						    }
						    if (p.colModel) {
							    for (j = 0; j < p.colModel.length; j++) {
								    var cm = p.colModel[j];
								    // 取列名
								    var seleceName = cm.name;
								    if(cm.displayname)
								    {
								    	seleceName = cm.displayname;
								    }
								    	
								    // json Bug修复://打包文件中未加入,请自行加入
								    if (typeof(data.rows[i][seleceName]) == 'undefined') {
									    data.rows[i][seleceName] = ''
								    }
								    // 过滤key
								    $.each(data.rows[i], function(x, y) {
									        if (seleceName == x) {
										        tdVal.push(y)
									        }
								        })
							    }
						    }
						    // add cell
						    $('thead tr:first th', g.hDiv).each(function() {
							        var td = document.createElement('td');
							        var idx = $(this).attr('axis').substr(3);
							        td.align = this.align;
							        td.innerHTML = tdVal[idx];
							        // td.innerHTML = row.cell[idx];
							        $(tr).append(td);
							        td = null;
						        });
						    if ($('thead', this.gDiv).length < 1) // handle if grid has no// headers
						    {
							    for (idx = 0; idx < cell.length; idx++) {
								    var td = document.createElement('td');
								    // td.innerHTML = row.cell[idx];
								    td.innerHTML = tdVal[idx];
								    $(tr).append(td);
								    td = null;
							    }
						    }

						    // 添加多选
						    if (p.checkbox) {
							    var cth = $('<th/>');
							    var cthch = $('<input type="checkbox" value="'
							        + $(tr).attr('id') + '"/>');
							    var objTr = $(tr);
//							    cthch.addClass("noborder").click(function() {//add matychen 不要noborder，加入后ie8显示有问题。
							    cthch.click(function() {
								        if (this.checked) {
								        	if(p.onChecked){	//记录选择历史  2013.4.20  zhen.tan 
								        		p.checkCommd(this);
								        	}
									        objTr.addClass('trSelected');
									        
								        } else {
								        	if(p.onChecked){   //记录选择历史  2013.4.20  zhen.tan 
								        		p.checkCommd(this);
								        	}
									        objTr.removeClass('trSelected');
								        }
							        })
							    cth.addClass("cth").attr({
								        width : "22"
							        }).append(cthch);
							    $(tr).prepend(cth);
						    }
						    
						    if (p.radio) {
							    var cth = $('<th/>');
							    var cthch = $('<input type="radio" name="rmm" value="'
							        + $(tr).attr('id') + '"/>');
							    var objTr = $(tr);
//							    cthch.addClass("noborder").click(function() {//add matychen 不要noborder，加入后ie8显示有问题。
							    cthch.click(function() {
								        if (this.checked) {
								        	if(p.onChecked){	//记录选择历史  2013.4.20  zhen.tan 
								        		p.checkCommd(this);
								        	}
								        	objTr.addClass('trSelected');
									        objTr.siblings().removeClass('trSelected');
									        
								        } else {
								        	if(p.onChecked){   //记录选择历史  2013.4.20  zhen.tan 
								        		p.checkCommd(this);
								        	}
									        objTr.removeClass('trSelected');
								        }
							        })
							    cth.addClass("cth").attr({
								        width : "22"
							        }).append(cthch);
							    $(tr).prepend(cth);
						    }
						    $(tbody).append(tr);
						    tr = null;
					    });
				} else if (p.dataType == 'xml') {
					i = 1;
					$("rows row", data).each(function() {
						    i++;
						    var tr = document.createElement('tr');
						    if (i % 2 && p.striped)
							    tr.className = 'erow';
						    var nid = $(this).attr('id');
						    if (nid)
							    tr.id = 'row' + nid;
						    nid = null;
						    var robj = this;
						    $('thead tr:first th', g.hDiv).each(function() {
							        var td = document.createElement('td');
							        var idx = $(this).attr('axis').substr(3);
							        td.align = this.align;
							        td.innerHTML = $("cell:eq(" + idx + ")", robj).text();
							        $(tr).append(td);
							        td = null;
						        });
						    if ($('thead', this.gDiv).length < 1) // handle if grid has no  headers
						    {
							    $('cell', this).each(function() {
								        var td = document.createElement('td');
								        td.innerHTML = $(this).text();
								        $(tr).append(td);
								        td = null;
							        });
						    }
						    $(tbody).append(tr);
						    tr = null;
						    robj = null;
					    });
				}
				$('tr', t).unbind();
				$(t).empty();
				$(t).append(tbody);
				this.addCellProp();
				this.addRowProp();
				// this.fixHeight($(this.bDiv).height());
				this.rePosDrag();
				tbody = null;
				data = null;
				i = null;

				if (p.onSuccess)
					p.onSuccess();
				if (p.hideOnSubmit)
					$(g.block).remove();// $(t).show();
				this.hDiv.scrollLeft = this.bDiv.scrollLeft;
				if ($.browser.opera)
					$(t).css('visibility', 'visible');
			},
			changeSort : function(th) { // change sortorder
				if (this.loading)
					return true;
				$(g.nDiv).hide();
				$(g.nBtn).hide();
				if (p.sortname == $(th).attr('abbr')) {
					if (p.sortorder == 'asc')
						p.sortorder = 'desc';
					else
						p.sortorder = 'asc';
				}

				$(th).addClass('sorted').siblings().removeClass('sorted');
				$('.sdesc', this.hDiv).removeClass('sdesc');
				$('.sasc', this.hDiv).removeClass('sasc');
				$('div', th).addClass('s' + p.sortorder);
				p.sortname = $(th).attr('abbr');

				if (p.onChangeSort)
					p.onChangeSort(p.sortname, p.sortorder);
				else
					this.populate();
			},
			buildpager : function() { // rebuild pager based on new properties
				$('.pcontrol input', this.pDiv).val(p.page);
				$('.pcontrol span', this.pDiv).html(p.pages);

				var r1 = (p.page - 1) * p.rp + 1;
				var r2 = r1 + p.rp - 1;

				if (p.total < r2)
					r2 = p.total;

				var stat = p.pagestat;

				stat = stat.replace(/{from}/, r1);
				stat = stat.replace(/{to}/, r2);
				stat = stat.replace(/{total}/, p.total);

				$('.pPageStat', this.pDiv).html(stat);
				
			},
			populate : function() { // get latest data
				if (this.loading)
					return true;
				if (p.onSubmit) {
					var gh = p.onSubmit();
					if (!gh)
						return false;
				}
				this.loading = true;
				if (!p.url)
					return false;

				$('.pPageStat', this.pDiv).html(p.procmsg);

				$('.pReload', this.pDiv).addClass('loading');

				$(g.block).css({
					    top : g.bDiv.offsetTop
				    });

				if (p.hideOnSubmit)
					$(this.gDiv).prepend(g.block); // $(t).hide();

				if ($.browser.opera)
					$(t).css('visibility', 'hidden');

				if (!p.newp)
					p.newp = 1;

				if (p.page > p.pages)
					p.page = p.pages;
				// var param = {page:p.newp, rp: p.rp, sortname: p.sortname, sortorder:
				// p.sortorder, query: p.query, qtype: p.qtype};
				var param = [{
					    name : 'page',
					    value : p.newp
				    }, {
					    name : 'rp',
					    value : p.rp
				    }, {
					    name : 'sortname',
					    value : p.sortname
				    }, {
					    name : 'sortorder',
					    value : p.sortorder
				    }, {
					    name : 'query',
					    value : p.query
				    }, {
					    name : 'qtype',
					    value : p.qtype
				    }];

				if (p.params) {
					for (var pi = 0; pi < p.params.length; pi++)
						param[param.length] = p.params[pi];
				}

				$.ajax({
					    type : p.method,
					    url : p.url,
					    data : param,
					    dataType : p.dataType,
					    success : function(data) {
					    	//alert(t.id)
					    	/*for(var dd in t)
					    	{
					    		if(t[dd]=="task_manage_list")
					    		alert(dd+":"+t[dd]);
					    	}*/
					    	//alert(t) 
						    g.addData(data);
						    p.data=data;		
						    //alert(t.attr("id"));
						   
						    var hdiv = $("#"+t.id).parent().parent().find("div[class='hDiv'] table tr:first");
						    var ddiv = $("#"+t.id).parent().parent().find("div[class='cDrag']");
						    hdiv.find("td").width(24);
						    var cw;
						    $("#"+t.id+" tr:first > td").each(function(i){	
						    	$(this).css('padding','0px');
						    	
						    	//alert();
						    	cw = $(this).width();
						    	var hw = hdiv.find("th:eq("+i+")").width();
						    	if(cw<hw)
						    	{
						    		cw = hw;
						    	}
						    	//cw+=10; 
						    	hdiv.find("th:eq("+i+")").width(cw).css('padding','0px 5px');
						    	$(this).width(cw);
						    	
						    	ddiv.find("div:eq("+i+")").css("left",hdiv.find("th:eq("+i+")").offset().left+cw+8);
						    });
						    
						    $("#"+t.id+" tr > td").each(function(i){	
						    	$(this).css("padding","0px 5px");
						    });
						     
					    },
					    error : function(data) {
						    try {
							    if (p.onError)
								    p.onError(data);
						    } catch (e) {
						    }
					    }
				    });
			},
			doSearch : function() {
				p.query = $('input[name=q]', g.sDiv).val();
				p.qtype = $('select[name=qtype]', g.sDiv).val();
				p.newp = 1;
				this.populate();
			},
			changePage : function(ctype) { // change page
				if (this.loading)
					return true;
				switch (ctype) {
					case 'first' :
						p.newp = 1;
						break;
					case 'prev' :
						if (p.page > 1)
							p.newp = parseInt(p.page) - 1;
						break;
					case 'next' :
						if (p.page < p.pages)
							p.newp = parseInt(p.page) + 1;
						break;
					case 'last' :
						p.newp = p.pages;
						break;
					case 'input' :
						var nv = parseInt($('.pcontrol input', this.pDiv).val());
						if (isNaN(nv))
							nv = 1;
						if (nv < 1)
							nv = 1;
						else if (nv > p.pages)
							nv = p.pages;
						$('.pcontrol input', this.pDiv).val(nv);
						p.newp = nv;
						break;
				}
				if (p.newp == p.page)
					return false;
				if (p.onChangePage)
					p.onChangePage(p.newp);
				else
					this.populate();

			},
			addCellProp : function() {
				$('tbody tr td', g.bDiv).each(function() {
					var tdDiv = document.createElement('div');
					var n = $('td', $(this).parent()).index(this);
					var pth = $('th:eq(' + n + ')', g.hDiv).get(0);

					if (pth != null) {
						if (p.sortname == $(pth).attr('abbr') && p.sortname) {
							this.className = 'sorted';
						}
						$(tdDiv).css({
							    textAlign : pth.align,
							    width : $('div:first', pth)[0].style.width
						    });

						if (pth.hide)
							$(this).css('display', 'none');

					}
					if (p.nowrap == false)
						$(tdDiv).css('white-space', 'normal');

					if (this.innerHTML == '')
						this.innerHTML = '&nbsp;';
					// tdDiv.value = this.innerHTML; //store preprocess value
					tdDiv.innerHTML = this.innerHTML;
					var prnt = $(this).parent()[0];
					var pid = false;
					if (prnt.id)
						pid = prnt.id.substr(3);

					if (pth != null) {
						if (pth.process)
							pth.process(tdDiv, pid);
					}

					$(this).empty().append(tdDiv).removeAttr('width'); // wrap content
					  // add editable event here 'dblclick'
				  });
			},
			getCellDim : function(obj) // get cell prop for editable event
			{
				var ht = parseInt($(obj).height());
				var pht = parseInt($(obj).parent().height());
				var wt = parseInt(obj.style.width);
				var pwt = parseInt($(obj).parent().width());
				var top = obj.offsetParent.offsetTop;
				var left = obj.offsetParent.offsetLeft;
				var pdl = parseInt($(obj).css('paddingLeft'));
				var pdt = parseInt($(obj).css('paddingTop'));
				return {
					ht : ht,
					wt : wt,
					top : top,
					left : left,
					pdl : pdl,
					pdt : pdt,
					pht : pht,
					pwt : pwt
				};
			},
			addRowProp : function() {
				$('tbody tr', g.bDiv).each(function() {
					    $(this).click(function(e) {
						        var obj = (e.target || e.srcElement);
						        if (obj.href || obj.type)
							        return true;
						        $(this).toggleClass('trSelected');
						        // 添加多选框
						        if (p.checkbox) {
							        if ($(this).hasClass('trSelected')) {
								        $(this).find('input')[0].checked = true;
							        } else {
								        $(this).find('input')[0].checked = false
							        }
							        if(p.onChecked){	//记录选择历史  2013.4.20  zhen.tan 
						        		p.checkCommd($(this).find('input')[0]);
						        	}
						        }
						        
						        if (p.radio) {
							        if ($(this).hasClass('trSelected')) {
								        $(this).find('input')[0].checked = true;
							        } else {
								        $(this).find('input')[0].checked = false
							        }
							        if(p.onChecked){	//记录选择历史  2013.4.20  zhen.tan 
						        		p.checkCommd($(this).find('input')[0]);
						        	}
						        }
						        
						        if(p.clickId) {//行点击获取ID 2013.7.22 yao.huang
						        	p.clickId($(this).attr("id"));
						        }

						        if (p.singleSelect)
						        {
						        	$(this).siblings().removeClass('trSelected');
						        }
							        
					        }).mousedown(function(e) {
						        if (e.shiftKey) {
							        $(this).toggleClass('trSelected');
							        g.multisel = true;
							        this.focus();
							        $(g.gDiv).noSelect();
						        }
					        }).mouseup(function() {
						        if (g.multisel) {
							        g.multisel = false;
							        $(g.gDiv).noSelect(false);
						        }
					        }).hover(function(e) {
						        if (g.multisel) {
							        $(this).toggleClass('trSelected');
						        }
					        }, function() {
					        });

					    if ($.browser.msie && $.browser.version < 7.0) {
						    $(this).hover(function() {
							        $(this).addClass('trOver');
						        }, function() {
							        $(this).removeClass('trOver');
						        });
					    }
				    });

			},
			pager : 0
		};

		// create model if any
		if (p.colModel) {
			thead = document.createElement('thead');
			tr = document.createElement('tr');

			for (i = 0; i < p.colModel.length; i++) {
				var cm = p.colModel[i];
				var th = document.createElement('th');

				th.innerHTML = cm.display;

				if (cm.name && cm.sortable)
					$(th).attr('abbr', cm.name);

				// th.idx = i;
				$(th).attr('axis', 'col' + i);

				if (cm.align)
					th.align = cm.align;

				if (cm.width)
					$(th).attr('width', cm.width);

				if (cm.hide) {
					th.hide = true;
				}

				if (cm.process) {
					th.process = cm.process;
				}

				$(tr).append(th);
			}
			$(thead).append(tr);
			$(t).prepend(thead);
		} // end if p.colmodel

		// init divs
		g.gDiv = document.createElement('div'); // create global container
		g.mDiv = document.createElement('div'); // create title container
		g.hDiv = document.createElement('div'); // create header container
		g.bDiv = document.createElement('div'); // create body container
		g.vDiv = document.createElement('div'); // create grip
		g.rDiv = document.createElement('div'); // create horizontal resizer
		g.cDrag = document.createElement('div'); // create column drag
		g.block = document.createElement('div'); // creat blocker
		g.nDiv = document.createElement('div'); // create column show/hide popup
		g.nBtn = document.createElement('div'); // create column show/hide button
		g.iDiv = document.createElement('div'); // create editable layer
		g.tDiv = document.createElement('div'); // create toolbar
		g.sDiv = document.createElement('div');

		if (p.usepager)
			g.pDiv = document.createElement('div'); // create pager container
		g.hTable = document.createElement('table');

		// set gDiv
		g.gDiv.className = 'datagrid';
		if (p.width != 'auto')
			g.gDiv.style.width = p.width + 'px';

		// add conditional classes
		if ($.browser.msie)
			$(g.gDiv).addClass('ie');

		if (p.novstripe)
			$(g.gDiv).addClass('novstripe');

		$(t).before(g.gDiv);
		$(g.gDiv).append(t);

		// set toolbar
		if (p.buttons) {
			g.tDiv.className = 'tDiv';
			var tDiv2 = document.createElement('div');
			tDiv2.className = 'tDiv2';

			for (i = 0; i < p.buttons.length; i++) {
				var btn = p.buttons[i];
				if (!btn.separator) {
					var btnDiv = document.createElement('div');
					btnDiv.className = 'fbutton';
					btnDiv.innerHTML = "<div><span>" + btn.name + "</span></div>";
					if (btn.bclass)
						$('span', btnDiv).addClass(btn.bclass).css({
							    paddingLeft : 20
						    });
					btnDiv.onpress = btn.onpress;
					btnDiv.name = btn.name;
					if (btn.onpress) {
						$(btnDiv).click(function() {
							    this.onpress(this.name, g.gDiv);
						    });
					}
					$(tDiv2).append(btnDiv);
					if ($.browser.msie && $.browser.version < 7.0) {
						$(btnDiv).hover(function() {
							    $(this).addClass('fbOver');
						    }, function() {
							    $(this).removeClass('fbOver');
						    });
					}

				} else {
					$(tDiv2).append("<div class='btnseparator'></div>");
				}
			}
			$(g.tDiv).append(tDiv2);
			$(g.tDiv).append("<div style='clear:both'></div>");
			$(g.gDiv).prepend(g.tDiv);
		}

		// set hDiv
		g.hDiv.className = 'hDiv';

		$(t).before(g.hDiv);

		// set hTable
		g.hTable.cellPadding = 0;
		g.hTable.cellSpacing = 0;
		$(g.hDiv).append('<div class="hDivBox"></div>');
		$('div', g.hDiv).append(g.hTable);
		var thead = $("thead:first", t).get(0);
		if (thead)
			$(g.hTable).append(thead);
		thead = null;

		if (!p.colmodel)
			var ci = 0;

		// setup thead
		$('thead tr:first th', g.hDiv).each(function() {
			var thdiv = document.createElement('div');

			if ($(this).attr('abbr')) {
				$(this).click(function(e) {

					    if (!$(this).hasClass('thOver'))
						    return false;
					    var obj = (e.target || e.srcElement);
					    if (obj.href || obj.type)
						    return true;
					    g.changeSort(this);
				    });

				if ($(this).attr('abbr') == p.sortname) {
					this.className = 'sorted';
					thdiv.className = 's' + p.sortorder;
				}
			}

			if (this.hide)
				$(this).hide();

			if (!p.colmodel) {
				$(this).attr('axis', 'col' + ci++);
			}

			$(thdiv).css({
				    textAlign : this.align,
				    width : this.width + 'px'
			    });
			thdiv.innerHTML = this.innerHTML;

			$(this).empty().append(thdiv).removeAttr('width').mousedown(function(e) {
				    g.dragStart('colMove', e, this);
			    }).hover(function() {
				if (!g.colresize && !$(this).hasClass('thMove') && !g.colCopy)
					$(this).addClass('thOver');

				if ($(this).attr('abbr') != p.sortname && !g.colCopy && !g.colresize
				    && $(this).attr('abbr'))
					$('div', this).addClass('s' + p.sortorder);
				else if ($(this).attr('abbr') == p.sortname && !g.colCopy
				    && !g.colresize && $(this).attr('abbr')) {
					var no = '';
					if (p.sortorder == 'asc')
						no = 'desc';
					else
						no = 'asc';
					$('div', this).removeClass('s' + p.sortorder).addClass('s' + no);
				}

				if (g.colCopy) {
					var n = $('th', g.hDiv).index(this);

					if (n == g.dcoln)
						return false;

					if (n < g.dcoln)
						$(this).append(g.cdropleft);
					else
						$(this).append(g.cdropright);

					g.dcolt = n;

				} else if (!g.colresize) {

					var nv = $('th:visible', g.hDiv).index(this);
					var onl = parseInt($('div:eq(' + nv + ')', g.cDrag).css('left'));
					var nw = parseInt($(g.nBtn).width())
					    + parseInt($(g.nBtn).css('borderLeftWidth'));
					nl = onl - nw + Math.floor(p.cgwidth / 2);

					$(g.nDiv).hide();
					$(g.nBtn).hide();

					$(g.nBtn).css({
						    'left' : nl,
						    top : g.hDiv.offsetTop
					    }).show();

					var ndw = parseInt($(g.nDiv).width());

					$(g.nDiv).css({
						    top : g.bDiv.offsetTop
					    });

					if ((nl + ndw) > $(g.gDiv).width())
						$(g.nDiv).css('left', onl - ndw + 1);
					else
						$(g.nDiv).css('left', nl);

					if ($(this).hasClass('sorted'))
						$(g.nBtn).addClass('srtd');
					else
						$(g.nBtn).removeClass('srtd');

				}

			}, function() {
				$(this).removeClass('thOver');
				if ($(this).attr('abbr') != p.sortname)
					$('div', this).removeClass('s' + p.sortorder);
				else if ($(this).attr('abbr') == p.sortname) {
					var no = '';
					if (p.sortorder == 'asc')
						no = 'desc';
					else
						no = 'asc';

					$('div', this).addClass('s' + p.sortorder).removeClass('s' + no);
				}
				if (g.colCopy) {
					$(g.cdropleft).remove();
					$(g.cdropright).remove();
					g.dcolt = null;
				}
			}); // wrap content
		});

		// set bDiv
		g.bDiv.className = 'bDiv';
		$(t).before(g.bDiv);
		$(g.bDiv).css({
			    height : (p.height == 'auto') ? 'auto' : p.height + "px"
		    }).scroll(function(e) {
			    g.scroll()
		    }).append(t);

		if (p.height == 'auto') {
			$('table', g.bDiv).addClass('autoht');
		}
		// add td properties
		g.addCellProp();

		// add row properties
		g.addRowProp();

		// set cDrag

		var cdcol = $('thead tr:first th:first', g.hDiv).get(0);

		if (cdcol != null) {
			g.cDrag.className = 'cDrag';
			g.cdpad = 0;

			g.cdpad += (isNaN(parseInt($('div', cdcol).css('borderLeftWidth')))
			    ? 0
			    : parseInt($('div', cdcol).css('borderLeftWidth')));
			g.cdpad += (isNaN(parseInt($('div', cdcol).css('borderRightWidth')))
			    ? 0
			    : parseInt($('div', cdcol).css('borderRightWidth')));
			g.cdpad += (isNaN(parseInt($('div', cdcol).css('paddingLeft')))
			    ? 0
			    : parseInt($('div', cdcol).css('paddingLeft')));
			g.cdpad += (isNaN(parseInt($('div', cdcol).css('paddingRight')))
			    ? 0
			    : parseInt($('div', cdcol).css('paddingRight')));
			g.cdpad += (isNaN(parseInt($(cdcol).css('borderLeftWidth')))
			    ? 0
			    : parseInt($(cdcol).css('borderLeftWidth')));
			g.cdpad += (isNaN(parseInt($(cdcol).css('borderRightWidth')))
			    ? 0
			    : parseInt($(cdcol).css('borderRightWidth')));
			g.cdpad += (isNaN(parseInt($(cdcol).css('paddingLeft')))
			    ? 0
			    : parseInt($(cdcol).css('paddingLeft')));
			g.cdpad += (isNaN(parseInt($(cdcol).css('paddingRight')))
			    ? 0
			    : parseInt($(cdcol).css('paddingRight')));

			$(g.bDiv).before(g.cDrag);

			var cdheight = $(g.bDiv).height();
			var hdheight = $(g.hDiv).height();

			$(g.cDrag).css({
				    top : -hdheight + 'px'
			    });

			$('thead tr:first th', g.hDiv).each(function() {
				    var cgDiv = document.createElement('div');
				    $(g.cDrag).append(cgDiv);
				    if (!p.cgwidth)
					    p.cgwidth = $(cgDiv).width();
				    $(cgDiv).css({
					        height : cdheight + hdheight
				        }).mousedown(function(e) {
					        g.dragStart('colresize', e, this);
				        });
				    if ($.browser.msie && $.browser.version < 7.0) {
					    g.fixHeight($(g.gDiv).height());
					    $(cgDiv).hover(function() {
						        g.fixHeight();
						        $(this).addClass('dragging')
					        }, function() {
						        if (!g.colresize)
							        $(this).removeClass('dragging')
					        });
				    }
			    });

			// g.rePosDrag();

		}
		// add strip
		if (p.striped)
			$('tbody tr:odd', g.bDiv).addClass('erow');
		if (p.resizable && p.height != 'auto') {
			g.vDiv.className = 'vGrip';
			$(g.vDiv).mousedown(function(e) {
				    g.dragStart('vresize', e)
			    }).html('<span></span>');
			$(g.bDiv).after(g.vDiv);
		}

		if (p.resizable && p.width != 'auto' && !p.nohresize) {
			g.rDiv.className = 'hGrip';
			$(g.rDiv).mousedown(function(e) {
				    g.dragStart('vresize', e, true);
			    }).html('<span></span>').css('height', $(g.gDiv).height());
			if ($.browser.msie && $.browser.version < 7.0) {
				$(g.rDiv).hover(function() {
					    $(this).addClass('hgOver');
				    }, function() {
					    $(this).removeClass('hgOver');
				    });
			}
			$(g.gDiv).append(g.rDiv);
		}

		// add pager
		if (p.usepager) {
			g.pDiv.className = 'pDiv';
			g.pDiv.innerHTML = '<div class="pDiv2"></div>';
			$(g.bDiv).after(g.pDiv);
			//var html = ' <div class="pGroup"> <div class="pFirst pButton" title="首页"><span></span></div><div class="pPrev pButton" title="上一页"><span></span></div> </div> <div class="btnseparator"></div> <div class="pGroup"><span class="pcontrol">第 <input type="text" size="4" value="1" />页,共 <span> 1 </span>页</span></div> <div class="btnseparator"></div> <div class="pGroup"> <div class="pNext pButton" title="下一页"><span></span></div><div class="pLast pButton" title="尾页"><span></span></div> </div> <div class="btnseparator"></div> <div class="pGroup"> <div class="pReload pButton"  title="刷新"><span></span></div> </div> <div class="btnseparator"></div> <div class="pGroup"><span class="pPageStat"></span></div>';
			var html = ' <div class="pGroup"> <div class="pFirst pButton" title="首页"><span></span></div><div class="pPrev pButton" title="上一页"><span></span></div> </div> <div class="btnseparator"></div> <div class="pGroup"><span class="pcontrol">第 <input type="text" size="4" value="1" />页,共 <span> 1 </span>页</span></div> <div class="btnseparator"></div> <div class="pGroup"> <div class="pNext pButton" title="下一页"><span></span></div><div class="pLast pButton" title="尾页"><span></span></div> </div> <div class="btnseparator"></div> <div class="pGroup"> <div class="pReload pButton"  title="刷新"><span></span></div> </div> <div class="btnseparator"></div> <div class="pGroup"><span class="pPageStat"></span></div>';
			
			$('div', g.pDiv).html(html);

			$('.pReload', g.pDiv).click(function() {
				    g.populate()
			    });
			$('.pFirst', g.pDiv).click(function() {
				    g.changePage('first')
			    });
			$('.pPrev', g.pDiv).click(function() {
				    g.changePage('prev')
			    });
			$('.pNext', g.pDiv).click(function() {
				    g.changePage('next')
			    });
			$('.pLast', g.pDiv).click(function() {
				    g.changePage('last')
			    });
			$('.pcontrol input', g.pDiv).keydown(function(e) {
				    if (e.keyCode == 13)
					    g.changePage('input')
			    });
			if ($.browser.msie && $.browser.version < 7)
				$('.pButton', g.pDiv).hover(function() {
					    $(this).addClass('pBtnOver');
				    }, function() {
					    $(this).removeClass('pBtnOver');
				    });

			if (p.useRp) {
				var opt = "";
				for (var nx = 0; nx < p.rpOptions.length; nx++) {
					if (p.rp == p.rpOptions[nx])
						sel = 'selected="selected"';
					else
						sel = '';
					opt += "<option value='" + p.rpOptions[nx] + "' " + sel + " >"
					    + p.rpOptions[nx] + "&nbsp;&nbsp;</option>";
				};
				$('.pDiv2', g.pDiv).prepend("<div class='pGroup'><select name='rp'>"
				    + opt + "</select></div> <div class='btnseparator'></div>");
				$('select', g.pDiv).change(function() {
					    if (p.onRpChange)
						    p.onRpChange(+this.value);
					    else {
						    p.newp = 1;
						    p.rp = +this.value;
						    g.populate();
					    }
				    });
			}

			// add search button
			if (p.searchitems) {
				$('.pDiv2', g.pDiv)
				    .prepend("<div class='pGroup'> <div class='pSearch pButton'><span></span></div> </div>  <div class='btnseparator'></div>");
				$('.pSearch', g.pDiv).click(function() {
					    $(g.sDiv).slideToggle('fast', function() {
						        $('.sDiv:visible input:first', g.gDiv).trigger('focus');
					        });
				    });
				// add search box
				g.sDiv.className = 'sDiv';

				sitems = p.searchitems;

				var sopt = "";
				for (var s = 0; s < sitems.length; s++) {
					if (p.qtype == '' && sitems[s].isdefault == true) {
						p.qtype = sitems[s].name;
						sel = 'selected="selected"';
					} else
						sel = '';
					sopt += "<option value='" + sitems[s].name + "' " + sel + " >"
					    + sitems[s].display + "&nbsp;&nbsp;</option>";
				}

				if (p.qtype == '')
					p.qtype = sitems[0].name;

				$(g.sDiv)
				    .append("<div class='sDiv2'>快速查找 <input type='text' size='30' name='q' class='qsbox' /> <select name='qtype'>"
				        + sopt + "</select> <input type='button' value='清除' /></div>");

				$('input[name=q],select[name=qtype]', g.sDiv).keydown(function(e) {
					    if (e.keyCode == 13)
						    g.doSearch()
				    });
				$('input[value="清除"]', g.sDiv).click(function() {
					    $('input[name=q]', g.sDiv).val('');
					    p.query = '';
					    g.doSearch();
				    });
				$(g.bDiv).after(g.sDiv);

			}

		}
		$(g.pDiv, g.sDiv).append("<div style='clear:both'></div>");

		// add title
		if (p.title) {
			g.mDiv.className = 'mDiv';
			g.mDiv.innerHTML = '<div class="ftitle">' + p.title + '</div>';
			$(g.gDiv).prepend(g.mDiv);
			if (p.showTableToggleBtn) {
				$(g.mDiv)
				    .append('<div class="ptogtitle" title="Minimize/Maximize Table"><span></span></div>');
				$('div.ptogtitle', g.mDiv).click(function() {
					    $(g.gDiv).toggleClass('hideBody');
					    $(this).toggleClass('vsble');
				    });
			}
			// g.rePosDrag();
		}

		// setup cdrops
		g.cdropleft = document.createElement('span');
		g.cdropleft.className = 'cdropleft';
		g.cdropright = document.createElement('span');
		g.cdropright.className = 'cdropright';

		// add block
		g.block.className = 'gBlock';
		var gh = $(g.bDiv).height();
		var gtop = g.bDiv.offsetTop;
		$(g.block).css({
			    width : g.bDiv.style.width,
			    height : gh,
			    background : 'white',
			    position : 'relative',
			    marginBottom : (gh * -1),
			    zIndex : 1,
			    top : gtop,
			    left : '0px'
		    });
		$(g.block).fadeTo(0, p.blockOpacity);

		// add column control
		if ($('th', g.hDiv).length) {

			g.nDiv.className = 'nDiv';
			g.nDiv.innerHTML = "<table cellpadding='0' cellspacing='0'><tbody></tbody></table>";
			$(g.nDiv).css({
				    marginBottom : (gh * -1),
				    display : 'none',
				    top : gtop
			    }).noSelect();

			var cn = 0;

			$('th div', g.hDiv).each(function() {
				var kcol = $("th[axis='col" + cn + "']", g.hDiv)[0];
				var chk = 'checked="checked"';
				if (kcol.style.display == 'none')
					chk = '';

				if (p.checkbox) {
					$('tbody', g.nDiv)
					    .append('<tr><td class="ndcol1"><input type="checkbox" ' + chk
					        + ' class="togCol" value="' + cn
					        + '" /></td><td class="ndcol2">' + this.innerHTML
					        + '</td></tr>');
				}
				if(p.radio){
					$('tbody', g.nDiv)
				    .append('<tr><td class="ndcol1"><input name="rmm" type="radio" ' + chk
				        + ' class="togCol" value="' + cn
				        + '" /></td><td class="ndcol2">' + this.innerHTML
				        + '</td></tr>');
				}
				cn++;
			});
			// 添加多选框
			if (p.checkbox) {
				$('tr', g.hDiv).each(function() {

					var cth = $('<td/>');

					var cthch = $('<input type="checkbox"/>');

					cthch.click(function() {
						if (this.checked) {
							$('tbody tr', g.bDiv).each(function() {
								    $(this).addClass('trSelected').find('input')[0].checked = true;
								    if(p.onChecked){//记录选择历史  2013.7.4  yuncai.zhu 
								    	p.checkCommd($(this).find('input')[0]);
								    }
							    })
						} else {
							$('tbody tr', g.bDiv).each(function() {								
								$(this).removeClass('trSelected').find('input')[0].checked = false;
								if(p.onChecked){	//记录选择历史  2013.7.4  yuncai.zhu 
									p.checkCommd($(this).find('input')[0]);
					        	}
							})
						}
					})

					cth.addClass("cth").attr({
						    width : "22"
					    }).append(cthch);

					$(this).prepend(cth);

				})
			};
			
			
			if (p.radio) {
				$('tr', g.hDiv).each(function() {

					var cth = $('<td/>');

					/*
					var cthch = $('<input type="radio" name="rmm"/>');
					cthch.click(function() {
						if (this.checked) {
							$('tbody tr', g.bDiv).each(function() {
								    $(this).addClass('trSelected').find('input')[0].checked = true;
								    if(p.onChecked){//记录选择历史  2013.7.4  yuncai.zhu 
								    	p.checkCommd($(this).find('input')[0]);
								    }
							    })
						} else {
							$('tbody tr', g.bDiv).each(function() {								
								$(this).removeClass('trSelected').find('input')[0].checked = false;
								if(p.onChecked){	//记录选择历史  2013.7.4  yuncai.zhu 
									p.checkCommd($(this).find('input')[0]);
					        	}
							})
						}
					})

					cth.addClass("cth").attr({
						    width : "22"
					}).append(cthch);
					*/
					cth.addClass("cth").append("<input type='radio'  style='margin-right:2px;visibility:hidden'/>");

					$(this).prepend(cth);

				})
			};
			

			if ($.browser.msie && $.browser.version < 7.0)
				$('tr', g.nDiv).hover(function() {
					    $(this).addClass('ndcolover');
				    }, function() {
					    $(this).removeClass('ndcolover');
				    });

			$('td.ndcol2', g.nDiv).click(function() {
				if ($('input:checked', g.nDiv).length <= p.minColToggle
				    && $(this).prev().find('input')[0].checked)
					return false;
				return g.toggleCol($(this).prev().find('input').val());
			});

			$('input.togCol', g.nDiv).click(function() {

				if ($('input:checked', g.nDiv).length < p.minColToggle
				    && this.checked == false)
					return false;
				$(this).parent().next().trigger('click');
				  // return false;
			});

			$(g.gDiv).prepend(g.nDiv);

			$(g.nBtn).addClass('nBtn').html('<div></div>').attr('title',
			    '隐藏/显示列').click(function() {
				    $(g.nDiv).toggle();
				    return true;
			    });

			if (p.showToggleBtn)
				$(g.gDiv).prepend(g.nBtn);

		}

		// add date edit layer
		$(g.iDiv).addClass('iDiv').css({
			    display : 'none'
		    });
		$(g.bDiv).append(g.iDiv);

		// add flexigrid events
		$(g.bDiv).hover(function() {
			    $(g.nDiv).hide();
			    $(g.nBtn).hide();
		    }, function() {
			    if (g.multisel)
				    g.multisel = false;
		    });
		$(g.gDiv).hover(function() {
		    }, function() {
			    $(g.nDiv).hide();
			    $(g.nBtn).hide();
		    });

		// add document events
		$(document).mousemove(function(e) {
			    g.dragMove(e)
		    }).mouseup(function(e) {
			    g.dragEnd()
		    }).hover(function() {
		    }, function() {
			    g.dragEnd()
		    });

		// browser adjustments
		if ($.browser.msie && $.browser.version < 7.0) {
			$('.hDiv,.bDiv,.mDiv,.pDiv,.vGrip,.tDiv, .sDiv', g.gDiv).css({
				    width : '100%'
			    });
			$(g.gDiv).addClass('ie6');
			if (p.width != 'auto')
				$(g.gDiv).addClass('ie6fullwidthbug');
		}

		g.rePosDrag();
		g.fixHeight();

		// make grid functions accessible
		t.p = p;
		t.grid = g;

		// load data
		if (p.url && p.autoload) {
			g.populate();
		}

		return t;

	};

	var docloaded = false;

	$(document).ready(function() {
		    docloaded = true
	    });

	$.fn.flexigrid = function(p) {

		return this.each(function() {
			    if (!docloaded) {
				    $(this).hide();
				    var t = this;
				    $(document).ready(function() {
					        $.addFlex(t, p);
				        });
			    } else {
				    $.addFlex(this, p);
			    }
		    });

	}; // end flexigrid

	$.fn.flexReload = function(p) { // function to reload grid

		return this.each(function() {
			    if (this.grid && this.p.url)
				    this.grid.populate();
		    });

	}; // end flexReload

	$.fn.flexOptions = function(p) { // function to update general options

		return this.each(function() {
			    if (this.grid)
				    $.extend(this.p, p);
		    });

	}; // end flexOptions

	$.fn.flexToggleCol = function(cid, visible) { // function to reload grid

		return this.each(function() {
			    if (this.grid)
				    this.grid.toggleCol(cid, visible);
		    });

	}; // end flexToggleCol

	$.fn.flexAddData = function(data) { // function to add data to grid

		return this.each(function() {
			    if (this.grid)
				    this.grid.addData(data);
		    });

	};

	$.fn.noSelect = function(p) { // no select plugin by me :-)

		if (p == null)
			prevent = true;
		else
			prevent = p;

		if (prevent) {

			return this.each(function() {
				    if ($.browser.msie || $.browser.safari)
					    $(this).bind('selectstart', function() {
						        return false;
					        });
				    else if ($.browser.mozilla) {
					    $(this).css('MozUserSelect', 'none');
					    $('body').trigger('focus');
				    } else if ($.browser.opera)
					    $(this).bind('mousedown', function() {
						        return false;
					        });
				    else
					    $(this).attr('unselectable', 'on');
			    });

		} else {

			return this.each(function() {
				    if ($.browser.msie || $.browser.safari)
					    $(this).unbind('selectstart');
				    else if ($.browser.mozilla)
					    $(this).css('MozUserSelect', 'inherit');
				    else if ($.browser.opera)
					    $(this).unbind('mousedown');
				    else
					    $(this).removeAttr('unselectable', 'on');
			    });

		}

	}; // end noSelect
	$.fn.getData = function() { // function to get data from grid
		var griddata = {total:1,page:1,rows:[]};
		this.each( function() {
		if (this.grid)    
		    griddata = this.p.data;
		});
		return griddata;	
	};
	
})(jQuery);