/**
 * Copyright (c) 2013-2015 www.javahih.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.hihsoft.sso.business.web.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.hihframework.core.utils.DateUtils;
import com.hihframework.core.utils.StringHelpers;
import com.hihframework.exception.ControllerException;
import com.hihframework.osplugins.json.JsonUtil;
import com.hihsoft.baseclass.web.controller.javahihBaseController;
import com.hihsoft.sso.business.model.TsysFlat;
import com.hihsoft.sso.business.model.TsysUpload;
import com.hihsoft.sso.business.service.TsysFlatService;
import com.hihsoft.sso.business.service.TsysUploadService;
/**
 * Title:附件上传的控制层服务
 * Description:
 * Copyright: Copyright (c) 2013
 * Company:hihsoft.co.,ltd
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
@Controller
@RequestMapping("/fileUploadController.do")
public class FileUploadController extends javahihBaseController {
	@Autowired
	private TsysUploadService tsysUploadService;
	@Autowired
	private TsysFlatService tsysFlatService;
	private static final String FILE_PATH = "uploads";

	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=forward")
	public ModelAndView forward(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("/common/uploadfile");
		String hql = "from TsysUpload";
		Map<String, Object> filter = WebUtils.getParametersStartingWith(
				request, "srh_");// 得到查询条件
		String orders = getParam(request, "orders");
		Set<String> set = filter.keySet();
		for (Iterator<String> it = set.iterator(); it.hasNext();) {
			String key = it.next();
			Object value = filter.get(key);
			if (StringHelpers.notNull(value)) {
				hql += " and " + key + "='" + value + "'";
			}
		}
		if (orders != null && !"".equals(orders))
			hql += " order by " + orders;
		addOrders(request, orders);
		List<TsysUpload> list = calcPage(request, tsysUploadService, hql);
		List<TsysFlat> flats = tsysFlatService.getAllTsysFlat();
		for (TsysUpload up : list) {
			String curflat = up.getFlatid();
			if (StringHelpers.isNull(curflat)) continue;
			for (TsysFlat flat : flats) {
				if (curflat.equals(flat.getFlatid())) {
					up.setFlatid(flat.getFlatname());
				}
			}
		}
		mv.addObject("list", list);
		return mv;
	}
	/**
	 * 修改
	 * @param request
	 * @param response
	 * @return
	 * @throws ControllerException
	 */
	@RequestMapping(params = "method=modify")
	public ModelAndView modify(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("/upload/uploadfile");
		String id=request.getParameter("id");
		TsysUpload tsysUpload=tsysUploadService.getTsysUploadById(id);
	    mv.addObject("tsysUpload", tsysUpload);
		return mv;
	}
	/**
	* 保存
	* @param request
	* @param response
	* @return
	* @throws ControllerException
	*/
	@RequestMapping(params = "method=save")
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String countStr = getParam(request, "count");
		if (StringHelpers.notNull(countStr)) {
			int count = Integer.parseInt(countStr);
			for (int i = 1; i <= count; i++) {
				String id = getParam(request, "id" + i);
				if (StringHelpers.isNull(id)) continue;
				TsysUpload upload = tsysUploadService.getTsysUploadById(id);
				if (upload == null) continue;
				upload.setSecondName(getParam(request, "secondName" + i));
				upload.setRemark(getParam(request, "remark" + i));
				String flatid = getParam(request, "flatid" + i);
				if (StringHelpers.notNull(flatid)) {
					upload.setFlatid(flatid);
				}
				tsysUploadService.saveOrUpdateTsysUpload(upload);
			}
		}
		return forward(request, response);
	}
	
	/**
	* 删除
	* @param request
	* @param response
	* @return
	* @throws ControllerException
	*/
	@RequestMapping(params = "method=delete")
	public ModelAndView delete(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		String ids = request.getParameter("ids");
		List<TsysUpload> list = new ArrayList<TsysUpload>();
		if (ids != null && !"".equals(ids)) {
			String[] arr = ids.split(",");
			for (String id : arr) {
				TsysUpload obj = tsysUploadService.getTsysUploadById(id);
				File file = new File(obj.getFileUrl());
				if (file.exists()) file.delete();
				list.add(obj);
			}
		}
		tsysUploadService.deleteBatchVO(list);
		return forward(request, response);
	}
	/**
	 * 上传文件
	 * @param request
	 * @param response
	 * @throws ControllerException
	 */
	@RequestMapping(params = "method=upload", method=RequestMethod.POST)
	public void upload(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		String url = getServletContext().getRealPath("/") + File.separator + FILE_PATH;
		File dir = new File(url);
		if (!dir.exists()) {
			dir.mkdirs();
			dir = null;
		}
		DefaultMultipartHttpServletRequest multiRequest = (DefaultMultipartHttpServletRequest) request;
	    Iterator<String> it= multiRequest.getFileNames();
	    List<TsysUpload> list = new ArrayList<TsysUpload>();
	    while (it.hasNext()) {
	    	CommonsMultipartFile file = (CommonsMultipartFile) multiRequest.getFile(it.next());
	    	String oldName = file.getOriginalFilename();
	    	String suffix = oldName.substring(oldName.lastIndexOf("."));
	    	File f = new File(url + File.separator + new Date().getTime() + suffix);
	    	if (f.exists()) {
	    		f.delete();
	    	}
	    	try {
				if (!f.createNewFile()) continue;
			} catch (IOException e1) {
						e1.printStackTrace();
			} 
	    	try {
				FileCopyUtils.copy(file.getBytes(), f);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
	    	TsysUpload upload = new TsysUpload();
	    	upload.setCreateMan(getUser(request).getUsername());
	    	upload.setCreateTime(DateUtils.getNowDateTime());
	    	upload.setFileName(oldName);
	    	upload.setFileUrl(f.getAbsolutePath());
	    	upload.setFlatid((String) getSession(request, "curFlatId"));
	    	upload.setSecondName(oldName);
	    	tsysUploadService.saveOrUpdateTsysUpload(upload);
	    	list.add(upload);
	    }
	    renderJson(response, JsonUtil.toString(list));
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "method=rename")
	public ModelAndView rename(HttpServletRequest request,
			HttpServletResponse response) throws ControllerException {
		ModelAndView mv = new ModelAndView("/common/rename");
		String idStr = getParam(request, "ids");
		String[] ids = idStr.split(",");
		String hql = "from TsysUpload where uploadid in ("+StringHelpers.join(ids, ",", "'")+")";
		List<TsysUpload> list = tsysUploadService.getTsysUploadByHQL(hql);
		mv.addObject("files", list);
		mv.addObject("flats", tsysFlatService.getAllTsysFlat());
		mv.addObject("count", list.size());
		return mv;
	}
	
	@RequestMapping(params = "method=download")
	public void download(HttpServletRequest request, HttpServletResponse response) {
        try {
        	String fileId = getParam(request, "fileId");
        	if (StringHelpers.isNull(fileId)) return;
        	TsysUpload upload = tsysUploadService.getTsysUploadById(fileId);
        	
        	String failPage = "<html><font size='3'>系统找不到指定的文件.<a href='#' onclick='javascript:window.close()'>点击这里返回</a></font></html>";
        	if (upload == null) {
				renderHtml(response, failPage);
        		return;
        	}
        	String path = upload.getFileUrl();
            // path是指欲下载的文件的路径。
            File file = new File(path);
            if (!file.exists()) {
            	renderHtml(response, failPage);
        		return;
            }
            // 取得文件名。
            String filename = upload.getSecondName();
            // 取得文件的后缀名。
            String name = upload.getFileName();
			String ext = name.substring(name.lastIndexOf("."));

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("GBK"), "ISO-8859-1") + ext);
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            log.error(ex);
        }
	}
	
}
