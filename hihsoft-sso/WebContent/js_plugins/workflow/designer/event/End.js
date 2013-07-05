draw2d.End=function(_url){
draw2d.ResizeImage.call(this,_url);
this.inputPort=null;
this.setDimension(30,30);
};
draw2d.End.prototype=new draw2d.Node();
draw2d.End.prototype.type="draw2d.End";
draw2d.End.prototype.createHTMLElement=function(){
	var item = draw2d.ResizeImage.prototype.createHTMLElement.call(this);
	return item;
};
draw2d.End.prototype.setDimension=function(w, h){
	draw2d.ResizeImage.prototype.setDimension.call(this, w, h);
};
draw2d.End.prototype.setWorkflow=function(_505d){
	draw2d.ResizeImage.prototype.setWorkflow.call(this,_505d);
	if(_505d!==null&&this.inputPort===null){
		this.inputPort=new draw2d.MyInputPort();
		this.inputPort.setName("input");
		this.inputPort.setWorkflow(_505d);
		this.inputPort.setBackgroundColor(new draw2d.Color(115,115,245));
		this.addPort(this.inputPort,0,this.height/2);
	}
};
