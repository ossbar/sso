draw2d.RoundCornerFigure = function() {
	this.cornerWidth = 15;
	this.cornerHeight = 15;
	this.outputPort = null;
	this.inputPort = null;
	draw2d.Node.call(this);
	this.setDimension(100,50);
	this.originalHeight = -1;
};
draw2d.RoundCornerFigure.prototype = new draw2d.Node();
draw2d.RoundCornerFigure.prototype.type = "RoundCornerFigure";
draw2d.RoundCornerFigure.prototype.createHTMLElement = function() {
	var item = document.createElement("div");
	item.id = this.id;
	item.style.position = "absolute";
	item.style.left = this.x + "px";
	item.style.top = this.y + "px";
	item.style.height = this.width + "px";
	item.style.width = this.height + "px";
	item.style.margin = "0px";
	item.style.padding = "0px";
	item.style.outline = "none";
	item.style.zIndex = "" + draw2d.Figure.ZOrderBaseIndex;
	this.top_left = document.createElement("div");
	this.top_left.style.background = "url(circle.png) no-repeat top left";
	this.top_left.style.position = "absolute";
	this.top_left.style.width = this.cornerWidth + "px";
	this.top_left.style.height = this.cornerHeight + "px";
	this.top_left.style.left = "0px";
	this.top_left.style.top = "0px";
	this.top_left.style.fontSize = "2px";
	this.top_right = document.createElement("div");
	this.top_right.style.background = "url(circle.png) no-repeat top right";
	this.top_right.style.position = "absolute";
	this.top_right.style.width = this.cornerWidth + "px";
	this.top_right.style.height = this.cornerHeight + "px";
	this.top_right.style.left = "0px";
	this.top_right.style.top = "0px";
	this.top_right.style.fontSize = "2px";
	this.bottom_left = document.createElement("div");
	this.bottom_left.style.background = "url(circle.png) no-repeat bottom left";
	this.bottom_left.style.position = "absolute";
	this.bottom_left.style.width = this.cornerWidth + "px";
	this.bottom_left.style.height = this.cornerHeight + "px";
	this.bottom_left.style.left = "0px";
	this.bottom_left.style.top = "0px";
	this.bottom_left.style.fontSize = "2px";
	this.bottom_right = document.createElement("div");
	this.bottom_right.style.background = "url(circle.png) no-repeat bottom right";
	this.bottom_right.style.position = "absolute";
	this.bottom_right.style.width = this.cornerWidth + "px";
	this.bottom_right.style.height = this.cornerHeight + "px";
	this.bottom_right.style.left = "0px";
	this.bottom_right.style.top = "0px";
	this.bottom_right.style.fontSize = "2px";
	this.header = document.createElement("div");
	this.header.style.position = "absolute";
	this.header.style.left = this.cornerWidth + "px";
	this.header.style.top = "0px";
	this.header.style.height = (this.cornerHeight) + "px";
	this.header.style.backgroundColor = "#CCCCFF";
	this.header.style.borderTop = "3px solid #666666";
	this.header.style.fontSize = "10px";
	this.header.style.textAlign = "center";
	this.disableTextSelection(this.header);
	this.footer = document.createElement("div");
	this.footer.style.position = "absolute";
	this.footer.style.left = this.cornerWidth + "px";
	this.footer.style.top = "0px";
	this.footer.style.height = (this.cornerHeight - 1) + "px";
	this.footer.style.backgroundColor = "white";
	this.footer.style.borderBottom = "1px solid #666666";
	this.footer.style.fontSize = "2px";
	this.textarea = document.createElement("div");
	this.textarea.style.position = "absolute";
	this.textarea.style.left = "0px";
	this.textarea.style.top = this.cornerHeight + "px";
	this.textarea.style.backgroundColor = "white";
	this.textarea.style.borderTop = "2px solid #666666";
	this.textarea.style.borderLeft = "1px solid #666666";
	this.textarea.style.borderRight = "1px solid #666666";
	this.textarea.style.overflow = "auto";
	this.textarea.style.fontSize = "9pt";
	this.textarea.style.fontWeight = "bolder";
	this.textarea.style.textAlign = "center";
	this.textarea.style.verticalAlign = "middle";
	this.disableTextSelection(this.textarea);
	item.appendChild(this.top_left);
	item.appendChild(this.header);
	item.appendChild(this.top_right);
	item.appendChild(this.textarea);
	item.appendChild(this.bottom_left);
	item.appendChild(this.footer);
	item.appendChild(this.bottom_right);
	return item;
};
draw2d.RoundCornerFigure.prototype.setDimension = function(w, h) {
	try{
		draw2d.Node.prototype.setDimension.call(this, w, h);
		if (this.top_left !== null) {
			this.top_right.style.left = (this.width - this.cornerWidth) + "px";
			this.bottom_right.style.left = (this.width - this.cornerWidth) + "px";
			this.bottom_right.style.top = (this.height - this.cornerHeight) + "px";
			this.bottom_left.style.top = (this.height - this.cornerHeight) + "px";
			this.textarea.style.width = (this.width - 2) + "px";
			this.textarea.style.height = (this.height - this.cornerHeight * 2)
					+ "px";
			this.header.style.width = (this.width - this.cornerWidth * 2) + "px";
			this.footer.style.width = (this.width - this.cornerWidth * 2) + "px";
			this.footer.style.top = (this.height - this.cornerHeight) + "px";
		}
		if (this.outputPort !== null) {
			this.outputPort.setPosition(this.width + 5, this.height / 2);
		}
		if (this.inputPort !== null) {
			this.inputPort.setPosition(-5, this.height / 2);
		}
	}catch(e){
	}
};
draw2d.RoundCornerFigure.prototype.setTitle = function(title) {
	this.header.innerHTML = title;
};
draw2d.RoundCornerFigure.prototype.setContent = function(_5014) {
	this.textarea.innerHTML = _5014;
};
draw2d.RoundCornerFigure.prototype.onDragstart = function(x, y) {
	var _5017 = draw2d.Node.prototype.onDragstart.call(this, x, y);
	if (this.header === null) {
		return false;
	}
	if (y < this.cornerHeight && x < this.width
			&& x > (this.width - this.cornerWidth)) {
		this.toggle();
		return false;
	}
	if (this.originalHeight == -1) {
		if (this.canDrag === true && x < parseInt(this.header.style.width)
				&& y < parseInt(this.header.style.height)) {
			return true;
		}
	} else {
		return _5017;
	}
};
draw2d.RoundCornerFigure.prototype.setCanDrag = function(flag) {
	draw2d.Node.prototype.setCanDrag.call(this, flag);
	this.html.style.cursor = "";
	if (this.header === null) {
		return;
	}
	if (flag) {
		this.header.style.cursor = "move";
	} else {
		this.header.style.cursor = "";
	}
};
draw2d.RoundCornerFigure.prototype.setWorkflow = function(_5019) {
	draw2d.Node.prototype.setWorkflow.call(this, _5019);
	if (_5019 !== null && this.inputPort === null) {
		this.inputPort = new draw2d.MyInputPort();
		this.inputPort.setWorkflow(_5019);
		this.inputPort.setName("input");
		this.addPort(this.inputPort, -5, this.height / 2);
		this.outputPort = new draw2d.MyOutputPort();
		this.outputPort.setMaxFanOut(5);
		this.outputPort.setWorkflow(_5019);
		this.outputPort.setName("output");
		this.addPort(this.outputPort, this.width + 5, this.height / 2);
	}
};
draw2d.RoundCornerFigure.prototype.toggle = function() {
	if (this.originalHeight == -1) {
		this.originalHeight = this.height;
		this.setDimension(this.width, this.cornerHeight * 2);
		this.setResizeable(false);
	} else {
		this.setDimension(this.width, this.originalHeight);
		this.originalHeight = -1;
		this.setResizeable(true);
	}
};
draw2d.RoundCornerFigure.prototype.setIcon = function(_url){
	this.icon = document.createElement("div");
	this.icon.style.background = "url("+_url+") no-repeat top left";
	this.icon.style.position = "absolute";
	this.icon.style.width = this.cornerWidth + "px";
	this.icon.style.height = this.cornerHeight + "px";
	this.icon.style.left = "10px";
	this.icon.style.top = "2px";
	this.getHTMLElement().appendChild(this.icon);
};
draw2d.RoundCornerFigure.prototype.onDoubleClick=function(){
	alert("DoubleClick");
};
draw2d.RoundCornerFigure.prototype.getContextMenu=function(){
	var menu =new draw2d.Menu();
  var oThis = this;
  menu.appendMenuItem(new draw2d.MenuItem("Delete", null,function(x,y)
  {
      alert("Delete");
  }));

  menu.appendMenuItem(new draw2d.MenuItem("Refresh", null,function(x,y)
  {
      alert("Refresh");
  }));

  menu.appendMenuItem(new draw2d.MenuItem("Properties", null,function(x,y)
  {
      alert("Properties");
  }));
  return menu;
};