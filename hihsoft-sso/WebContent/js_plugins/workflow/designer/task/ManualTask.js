draw2d.ManualTask=function(){
	draw2d.Task.call(this);
	this.setTitle("Manual Task");
	this.setIcon();
};
draw2d.ManualTask.prototype=new draw2d.Task();
draw2d.ManualTask.prototype.type="draw2d.ManualTask";
draw2d.ManualTask.prototype.setWorkflow=function(_5019){
	draw2d.Task.prototype.setWorkflow.call(this,_5019);
};
draw2d.ManualTask.prototype.getContextMenu=function(){
	var menu = draw2d.Task.prototype.getContextMenu.call(this);
  return menu;
};
draw2d.ManualTask.prototype.setIcon = function(){
	var icon=draw2d.Task.prototype.setIcon.call(this);
	icon.className="manual-task-icon";
};