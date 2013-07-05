$(document).ready(function(){
	var move = function(src, dest, toLeft, order) {
		src.find(":checked").each(function(i) {
			var chk = $(this);
			var tr = chk.parent().parent();
			if (chk.parent().attr("tagName") == "TH") return;
			var name = (toLeft ? "chkleft" : "chkright");
			var td1 = "<td><input type='checkbox' name='"+name+"' value='"+chk.val()+"' checked/></td>";
			var label = tr.find(".lbl");
			var td2 = label.parent().attr("outerHTML");
			var html = (toLeft ? (td1 + td2) : td1+td2+"<TD><INPUT TYPE='text' VALUE='120' NAME='width' SIZE='2'/><INPUT TYPE='hidden' VALUE='"+(order++)+"'/></TD>");
			dest.append("<TR>"+html+"</TR>");
			tr.remove();
		});
	};
	$("#toleft").click(function(){
		var tb = $("#righttable");
		var ltb = $("#lefttable tbody");
		move(tb, ltb, true);
	});
	$("#toright").click(function(){
		var tb = $("#lefttable");
		var rtb = $("#righttable tbody");
		var oldcount = rtb.find("tr").size();
		move(tb, rtb, false, oldcount);
	});
	//向右移动全部
	$("#allright").click(function(){
		var chkall = $("#chkallleft");
		chkall.attr("checked", true);
		chkall.trigger("click");
		$("#toright").click();
	});
	//向左移动全部
	$("#allleft").click(function(){
		var chkall = $("#chkallright");
		chkall.attr("checked", true);
		chkall.trigger("click");
		$("#toleft").click();
	});
	$("#chkallleft").click(function() {
		var chk = $(this).attr("checked");
		$(":checkbox[name=chkleft]").each(function() {
			$(this).attr("checked", chk);
		});
	});
	$("#chkallright").click(function() {
		var chk = $(this).attr("checked");
		$(":checkbox[name=chkright]").each(function() {
			$(this).attr("checked", chk);
		});
	});
	var upOrDown = function(index, type) {
		if (isNaN(index)) return;
		index = parseInt(index);
		var tb = $("#righttable");
		var position = tb.find("tr:eq("+index+")");
		var size = tb.find("tr").size();
		tb.find(":checked[id!='chkallright']").each(function(i) {
			var chk = $(this);
			var tr = chk.parent().parent();
			var order = tr.find(":hidden").val();
			if ((type == "up" && order != 1)) {
				tr.insertBefore(position);
			} else if (type == 'down' && order != size -1) { 
				tr.insertAfter(position);
			} else {
				position = tb.find("tr:eq("+(type == "up" ? index-- : index++)+")");
			}
		});
		tb.find(":hidden").each(function(i) {
			var hid = $(this);
			hid.val(i+1);
		});
	};
	$("#first").click(function(){
		upOrDown(1, 'up');
	});
	$("#last").click(function(){
		var tb = $("#righttable");
		var rows = tb.find("tr");
		var index = rows.size() - 1;
		upOrDown(index, 'down');
	});
	$("#up").click(function(){
		var tb = $("#righttable");
		var index = $(tb.find(":checked[id!='chkallright']").get(0)).parent().parent().find(":hidden").val();
		if (!index || index == 1) return;
		upOrDown(parseInt(index) - 1 , 'up');
	});
	$("#down").click(function(){
		var tb = $("#righttable");
		var selected = tb.find(":checked[id!='chkallright']");
		var index = $(selected.get(selected.size() - 1)).parent().parent().find(":hidden").val();
		if (!index) return;
		upOrDown(parseInt(index) + 1, 'down');
	});
});