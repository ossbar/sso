package com.hihsoft.ui_widget.htmleditor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hihsoft.baseclass.web.controller.javahihBaseController;

@Controller
@RequestMapping("/uiHtmlEditorDemoController.do")
public class UI_CkEditorDemoController extends javahihBaseController {

	@RequestMapping(method=RequestMethod.GET)
	public ModelAndView demo() {
		return new ModelAndView("ui_widget/ui_htmleditor_demo");
	}
}
