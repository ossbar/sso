/* This notice must be untouched at all times.

Open-jACOB Draw2D
The latest version is available at
http://www.openjacob.org

Copyright (c) 2006 Andreas Herz. All rights reserved.
Created 5. 11. 2006 by Andreas Herz (Web: http://www.freegroup.de )

LICENSE: LGPL

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License (LGPL) as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA,
or see http://www.gnu.org/copyleft/lesser.html
*/
draw2d.MyWorkflow=function(/*:String*/id)
{
  draw2d.Workflow.call(this,id);
}

draw2d.MyWorkflow.prototype = new draw2d.Workflow;

/** @private **/
draw2d.MyWorkflow.prototype.type="MyWorkflow";



/**
 * Callback method for the double click event of user interaction.
 **/
draw2d.MyWorkflow.prototype.getContextMenu=function()
{
  var menu =new draw2d.Menu();
  var oThis = this;

  menu.appendMenuItem(new draw2d.MenuItem("Grid 10x10", null,function(x,y)
  {
      oThis.setGridWidth(10,10);
      oThis.setBackgroundImage("grid_10.png",true);
  }));

  menu.appendMenuItem(new draw2d.MenuItem("Grid 20x20", null,function(x,y)
  {
      oThis.setGridWidth(20,20);
      oThis.setBackgroundImage("grid_20.png",true);
  }));

  menu.appendMenuItem(new draw2d.MenuItem("Add Circle", null,function(x,y)
  {
      oThis.addFigure(new draw2d.Circle(30),x,y);
  }));


  return menu;
};