draw2d.BaseTask=function(_url){
draw2d.ResizeImage.call(this,_url);
	this.outputPort = null;
	this.inputPort = null;
	this.setDimension(110,60);
};
draw2d.BaseTask.prototype=new draw2d.ResizeImage();
draw2d.BaseTask.prototype.type="BaseTask";
draw2d.BaseTask.prototype.createHTMLElement=function(){
	var item = draw2d.ResizeImage.prototype.createHTMLElement.call(this);
	this.textarea = document.createElement("div");
	this.textarea.className="task-textarea";
	this.textarea.style.position = "absolute";
	//this.textarea.style.zIndex = "" + draw2d.Figure.ZOrderBaseIndex;
	this.textarea.style.left = "0px";
	this.textarea.style.top = "0px";
	item.appendChild(this.textarea);
	return item;
};
draw2d.BaseTask.prototype.setDimension=function(w, h){
	try{
		draw2d.ResizeImage.prototype.setDimension.call(this, w, h);
		this.textarea.style.left = (this.width/10)+"px";
		this.textarea.style.top = (this.height/3)+"px";
		this.textarea.style.width = (this.width - 20) + "px";
		this.textarea.style.height = (this.height - 40)+ "px";
		if (this.outputPort !== null) {
			this.outputPort.setPosition(this.width + 5, this.height / 2);
		}
		if (this.inputPort !== null) {
			this.inputPort.setPosition(-5, this.height / 2);
		}
	}catch(e){
	}
};
draw2d.BaseTask.prototype.setWorkflow=function(_5019){
	draw2d.ResizeImage.prototype.setWorkflow.call(this,_5019);
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
draw2d.BaseTask.prototype.setContent = function(_5014) {
	this.textarea.innerHTML=_5014;
};
