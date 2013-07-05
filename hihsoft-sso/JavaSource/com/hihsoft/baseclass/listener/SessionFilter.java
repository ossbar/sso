/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.baseclass.listener;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hihsoft.baseclass.constants.Consts;
/**
 * Title:解决会话失效后，跳转到登录界面
 * Description:通过过滤器实现
 * Copyright: Copyright (c) 2013
 * Company:hihsoft.co.,ltd
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public class SessionFilter implements Filter {

 public void init(FilterConfig filterConfig)throws ServletException{ 
 
 }
 public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)throws ServletException,IOException{  
         HttpServletRequest req = (HttpServletRequest)request;   
         HttpServletResponse res = (HttpServletResponse)response;  
         res.setHeader("Pragma", "No-cache");
         res.setHeader("Cache-Control", "no-cache");
         res.setDateHeader("Expires", -10);
         String url = req.getRequestURI();  
    if(url.endsWith("index.jsp") ||url.endsWith(req.getContextPath()+"/tsysLoginController.do")){
        chain.doFilter(request,response);	  
 		}
 		else{
 			if(req.getSession().getAttribute(Consts.USER_INFO)==null||req.getSession() == null){
 	       String loginurl="http://" + request.getServerName() + ":" + request.getServerPort() + "/"+req.getContextPath();
 	        	 PrintWriter out = response.getWriter();
 	          	 out.print("<script>window.top.location.href='"+loginurl+"'</script>");
 	        	 out.close(); 
 	         }else {
 	          chain.doFilter(request,response);   
 	         }
 			
 	} 

 }   
 public void destroy(){
 
 }   
}