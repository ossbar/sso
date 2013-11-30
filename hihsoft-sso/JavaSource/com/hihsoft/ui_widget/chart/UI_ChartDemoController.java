package com.hihsoft.ui_widget.chart;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hihframework.exception.ControllerException;
import com.hihsoft.sso.business.service.TbusinessOrderService;

/**
 * 统计分析
 * @author burke
 *
 */
@Controller
@RequestMapping("/uiChatemoController.do")
public class UI_ChartDemoController {

	@Autowired
	private TbusinessOrderService tbusinessOrderService;
	

	
	/**
	 * [品牌统计表]-月表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params={"method=list","module=brand","target=month"})
	public ModelAndView brand_list_month(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String sql= "select productBrand, count(*) count,DATE_FORMAT(paymentTime,'%m') months,DATE_FORMAT(paymentTime,'%e') date from t_business_order where orderState=4  group by months,date,productBrand";
		List report = tbusinessOrderService.getValueObjectBySQL(sql);
		ModelAndView mv = new ModelAndView("/ui_widget/ui_chart_demo_month");
		mv.addObject("report", report);
	    return mv;
	}
	
	
	/**
	 * [品牌统计表]-季表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params={"method=list","module=brand","target=quarter"})
	public ModelAndView brand_list_quarter(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String sql= "select productBrand, count(*) count,DATE_FORMAT(paymentTime,'%m') months from t_business_order where orderState=4 group by months,productBrand";
		List report = tbusinessOrderService.getValueObjectBySQL(sql);
		ModelAndView mv = new ModelAndView("/ui_widget/ui_chart_demo_quarter");
		mv.addObject("report", report);
	    return mv;
	}
	
	
	/**
	 * [品牌统计表]-年表
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params={"method=list","module=brand","target=year"})
	public ModelAndView brand_list_year(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String sql= "select productBrand, count(*) count,DATE_FORMAT(paymentTime,'%m') months from t_business_order where orderState=4 group by months,productBrand";
		List report = tbusinessOrderService.getValueObjectBySQL(sql);
		ModelAndView mv = new ModelAndView("/ui_widget/ui_chart_demo_year");
		mv.addObject("report", report);
	    return mv;
	}
	
}
