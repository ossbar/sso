draw2d.Task = function() {
	this.cornerWidth = 15;
	this.cornerHeight = 15;
	this.outputPort = null;
	this.inputPort = null;
	draw2d.Node.call(this);
	this.setDimension(120,60);
	this.originalHeight = -1;
};
draw2d.Task.prototype = new draw2d.Node();
draw2d.Task.prototype.type = "Task";
draw2d.Task.prototype.createHTMLElement = function() {
	var item = document.createElement("div");
	item.id = this.id;
	item.style.position = "absolute";
	item.style.left = this.x + "px";
	item.style.top = this.y + "px";
	item.style.height = this.width + "px";
	item.style.width = this.height + "px";
	item.className="task";
	item.style.zIndex = "" + draw2d.Figure.ZOrderBaseIndex;
	
	this.top_left = document.createElement("div");
	this.top_left.className="task-top-left";
	this.top_left.style.width = this.cornerWidth + "px";
	this.top_left.style.height = this.cornerHeight + "px";
	
	this.top_right = document.createElement("div");
	this.top_right.className="task-top-right";
	this.top_right.style.width = this.cornerWidth + "px";
	this.top_right.style.height = this.cornerHeight + "px";
	
	this.bottom_left = document.createElement("div");
	this.bottom_left.className="bottom-top-left";
	this.bottom_left.style.width = this.cornerWidth + "px";
	this.bottom_left.style.height = this.cornerHeight + "px";
	
	this.bottom_right = document.createElement("div");
	this.bottom_right.className="bottom-top-right";
	this.bottom_right.style.width = this.cornerWidth + "px";
	this.bottom_right.style.height = this.cornerHeight + "px";
	
	this.header = document.createElement("div");
	this.header.className="task-header";
	this.header.style.position = "absolute";
	this.header.style.left = this.cornerWidth + "px";
	this.header.style.top = "0px";
	this.header.style.height = (this.cornerHeight) + "px";
	this.disableTextSelection(this.header);
	
	this.footer = document.createElement("div");
	this.footer.className="task-footer";
	this.footer.style.position = "absolute";
	this.footer.style.left = this.cornerWidth + "px";
	this.footer.style.top = "0px";
	this.footer.style.height = (this.cornerHeight - 1) + "px";
	
	this.textarea = document.createElement("div");
	this.textarea.className="task-textarea";
	this.textarea.style.position = "absolute";
	this.textarea.style.left = "0px";
	this.textarea.style.top = this.cornerHeight + "px";
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
draw2d.Task.prototype.setDimension = function(w, h) {
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
draw2d.Task.prototype.setTitle = function(title) {
	this.header.innerHTML = title;
};
draw2d.Task.prototype.setContent = function(_5014) {
	this.textarea.innerHTML = _5014;
};
draw2d.Task.prototype.onDragstart = function(x, y) {
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
draw2d.Task.prototype.setCanDrag = function(flag) {
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
draw2d.Task.prototype.setWorkflow = function(_5019) {
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
draw2d.Task.prototype.toggle = function() {
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

draw2d.Task.prototype.onDoubleClick=function(){
	
};
draw2d.Task.prototype.getContextMenu=function(){
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
draw2d.Task.prototype.setIcon = function(){
	this.icon = document.createElement("div");
	this.icon.style.position = "absolute";
	this.icon.style.width = this.cornerWidth + "px";
	this.icon.style.height = this.cornerHeight + "px";
	this.icon.style.left = "10px";
	this.icon.style.top = "2px";
	this.getHTMLElement().appendChild(this.icon);
	return this.icon;
};