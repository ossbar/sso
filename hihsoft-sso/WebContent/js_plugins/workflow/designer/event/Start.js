draw2d.Start=function(_url){
draw2d.ResizeImage.call(this,_url);
	this.outputPort=null;
	this.setDimension(30,30);
};
draw2d.Start.prototype=new draw2d.Node();
draw2d.Start.prototype.type="draw2d.Start";
draw2d.Start.prototype.createHTMLElement=function(){
	var item = draw2d.ResizeImage.prototype.createHTMLElement.call(this);
	return item;
};
draw2d.Start.prototype.setDimension=function(w, h){
	draw2d.ResizeImage.prototype.setDimension.call(this, w, h);
};
draw2d.Start.prototype.setWorkflow=function(_4fe5){
draw2d.ResizeImage.prototype.setWorkflow.call(this,_4fe5);
	if(_4fe5!==null&&this.outputPort===null){
		this.outputPort=new draw2d.MyOutputPort();
		this.outputPort.setMaxFanOut(1);
		this.outputPort.setWorkflow(_4fe5);
		this.outputPort.setName("output");
		this.outputPort.setBackgroundColor(new draw2d.Color(245,115,115));
		this.addPort(this.outputPort,this.width,this.height/2);
	}
};
