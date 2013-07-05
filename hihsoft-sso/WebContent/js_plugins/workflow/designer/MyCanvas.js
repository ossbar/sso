draw2d.MyCanvas=function(id){
		draw2d.Workflow.call(this,id);
		this.html.style.backgroundImage="";//remove default backgourd
		this.html.className="MyCanvas";
};
draw2d.MyCanvas.prototype = new draw2d.Workflow();
draw2d.MyCanvas.prototype.type = "MyCanvas";