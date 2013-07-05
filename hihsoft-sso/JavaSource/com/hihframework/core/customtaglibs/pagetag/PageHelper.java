package com.hihframework.core.customtaglibs.pagetag;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hihframework.core.customtaglibs.util.ParamUtil;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * <p> Title:前端页面的分页辅助类</p>
 * <p> Description:保存分页条上的一些状态信息</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public class PageHelper {
	/**
	 * 如果需要在分页条上保存一些状态信息，需要用到request对象
	 */
	private HttpServletRequest request;

	private HttpSession session;

	public PageHelper(HttpServletRequest request) {
		this.request = request;
		this.session = this.request.getSession();
	}

	/**
	 * 用于计算用户自定义每页显示记录数
	 * 
	 * @return 每页显示记录数
	 */
	public int doCustomPerPage(int defaultPerPage) {
		int perPage = defaultPerPage;
		String cUserPerPage = null;
		String cPerPage = ParamUtil.getString(this.request, "perPage", "");
		if (!"".equals(cPerPage)) {
			this.session.setAttribute("cPerPage", cPerPage);
		}
		cUserPerPage = (String) this.session.getAttribute("cPerPage");
		if (null != cUserPerPage) {
			perPage = Integer.parseInt(cUserPerPage);
		}
		return perPage;
	}

	/**
	 * 根据模板生成翻页条
	 * 
	 * @param pageObject
	 * @return 返回翻页条字符串
	 */
	public String getPageBreakStr(Page pageObject) {
		// 取分页器参数
		int page = pageObject.getPage();
		int total = pageObject.getTotal();
		int perPage = pageObject.getPerPage();
		int viewPage = pageObject.getViewPage();
		int cNum = pageObject.getCNum();

		/**
		 * 计算前、后可见页码起始位置
		 */
		int startPage = ((page - 1) / viewPage) * viewPage + 1;
		int remberStart = 0;

		String contextPath = pageObject.getContextPath();
		String url = contextPath + "/" + pageObject.getUrl();
		String param = pageObject.getParam();
		/**
		 * 图片滤镜样式
		 */
		String imgStyle = "style=\"filter:gray\"";
		/**
		 * 是否翻页可用 首页 上一页 下一页 最后一页 true 可用 false 不可用
		 */
		boolean display1 = false;
		boolean display2 = false;
		boolean display3 = false;
		boolean display4 = false;
		/**
		 * 是否有url参数
		 */
		boolean isHaveParam = false;

		if (null != param && !"".equals(param)) {
			isHaveParam = true;
		}

		/**
		 * 绝对路径
		 */
		String realPath = pageObject.getRealPath();
		/**
		 * 翻页模板名
		 */
		String pageTemplate = pageObject.getPageTemplate();

		/**
		 * 计算总页数
		 */
		int totalPage = sumPage(total, perPage);
		page = getCpage(total, perPage, page);

		/**
		 * 保存当前页码
		 */
		this.request.setAttribute("cpage", String.valueOf(page));

		// 计算页数序列(每次显示10个页数)1..10 11..20 21..30 ....
		StringBuffer pageBarBuffer = new StringBuffer();

		StringBuffer prevPageNumStart = new StringBuffer();
		StringBuffer prevPageNumEnd = new StringBuffer();

		StringBuffer nextPageNumStart = new StringBuffer();
		StringBuffer nextPageNumEnd = new StringBuffer();

		/**
		 * 是否在前？页和后？页图片上使用样式
		 */
		boolean isPrevPageNumStyle = false;
		boolean isNextPageNumStyle = false;

		pageBarBuffer.append("&nbsp;&nbsp;");
		if (startPage > viewPage) {
			prevPageNumStart.append("<a href=\"" + url);
			prevPageNumStart.append("page=" + (startPage - 1));
			if (isHaveParam) {
				prevPageNumStart.append("&" + param);
			}
			prevPageNumStart.append("\">");
			prevPageNumEnd.append("</a>");
		} else {
			isPrevPageNumStyle = true;
		}

		/**
		 * 分页页码条
		 */
		for (remberStart = startPage; remberStart < startPage + viewPage; remberStart++) {
			if (remberStart == page) {
				pageBarBuffer
						.append("<font color=\"red\" face=\"arial,sans-serif\">");
				pageBarBuffer.append("<b>");
				pageBarBuffer.append(remberStart + "</b></font>&nbsp;");
			} else {
				pageBarBuffer
						.append("<a href=\"" + url + "page=" + remberStart);
				if (isHaveParam) {
					pageBarBuffer.append("&" + param);
				}
				pageBarBuffer.append("\">");
				pageBarBuffer
						.append("<font face=\"arial,sans-serif\" color=\"#000000\">");
				pageBarBuffer.append("<b>");
				pageBarBuffer.append(remberStart + "</b></font></a>&nbsp;");
			}

			if (remberStart == totalPage)
				break;
		}

		if ((remberStart - startPage) == viewPage) {
			nextPageNumStart.append("<a href=\"" + url);
			nextPageNumStart.append("page=" + remberStart);
			if (isHaveParam) {
				nextPageNumStart.append("&" + param);
			}
			nextPageNumStart.append("\">");
			nextPageNumEnd.append("</a>");
		} else {
			isNextPageNumStyle = true;
		}

		pageBarBuffer.append("&nbsp;");

		// 首页
		StringBuffer firstPageBuffer = new StringBuffer();
		if (totalPage > 1 && page != 1) {
			firstPageBuffer.append(url + "page=1");
			if (isHaveParam) {
				firstPageBuffer.append("&" + param);
			}
			display1 = true;
		}

		// 上一页
		StringBuffer perPageBuffer = new StringBuffer();
		if (page > 1) {
			perPageBuffer.append(url + "page=" + (page - 1));
			if (isHaveParam) {
				perPageBuffer.append("&" + param);
			}
			display2 = true;
		}

		// 下一页
		StringBuffer nextPageBuffer = new StringBuffer();
		if (page < totalPage) {
			nextPageBuffer.append(url + "page=" + (page + 1));
			if (isHaveParam) {
				nextPageBuffer.append("&" + param);
			}
			display3 = true;
		}

		// 最后一页
		StringBuffer lastPageBuffer = new StringBuffer();
		if (totalPage > 1 && page < totalPage) {
			lastPageBuffer.append(url + "page=" + totalPage);
			if (isHaveParam) {
				lastPageBuffer.append("&" + param);
			}
			display4 = true;
		}

		// 每页显示记录数
		StringBuffer numPageBuffer = new StringBuffer();
		numPageBuffer.append("<select name=\"perPage\" ");
		numPageBuffer.append(" onchange=\"");
		numPageBuffer.append(" if(this.options[this.selectedIndex]");
		numPageBuffer.append(".value!=''){");
		numPageBuffer.append(" location='" + url);
		if (isHaveParam) {
			numPageBuffer.append(param + "&");
		}
		numPageBuffer.append("page=1");
		numPageBuffer.append("&perPage='+this.options[this.selectedIndex]");
		numPageBuffer.append(".value;}\">");
		for (int i = 1; i <= cNum; i++) {
			numPageBuffer.append("<option value=\"" + (i * 10) + "\"");
			if ((i * 10) == perPage) {
				numPageBuffer.append(" selected>" + (i * 10) + "</option>");
			} else {
				numPageBuffer.append(">" + (i * 10) + "</option>");
			}
		}
		numPageBuffer.append("</select>");

		// 跳转到第几页
		StringBuffer jumpPageBuffer = new StringBuffer();
		jumpPageBuffer.append("<select name=\"pages\"");
		jumpPageBuffer.append(" onchange=\"");
		jumpPageBuffer.append(" if(this.options[this.selectedIndex]");
		jumpPageBuffer.append(".value!=''){location='" + url);
		if (isHaveParam) {
			jumpPageBuffer.append(param + "&");
		}
		jumpPageBuffer.append("page='+this.options[this.selectedIndex]");
		jumpPageBuffer.append(".value;}\">");
		for (int i = 1; i <= totalPage; i++) {
			jumpPageBuffer.append("<option value=\"" + i + "\"");
			if (i == page) {
				jumpPageBuffer.append("selected");
			}
			jumpPageBuffer.append(">" + i + "</option>");
		}
		jumpPageBuffer.append("</select>");

		Configuration cfg = new Configuration();
		Template newsTemplate = null;
		StringWriter out = new StringWriter();
		try {
			cfg.setDirectoryForTemplateLoading(new File(realPath
					+ pageObject.getPageTemplatePath()));
			cfg.setObjectWrapper(new DefaultObjectWrapper());

			/**
			 * 因为本项目所有页面均为UTF-8编码，故此采用UTF-8, 如果页面编码为GBK,此处也需要改成GBK
			 */
			newsTemplate = cfg.getTemplate(pageTemplate, "UTF-8");

			/**
			 * 以下主要是进行数据填充,将数据填充到模型中
			 */
			Map root = new HashMap();
			root.put("total", String.valueOf(total));
			root.put("page", String.valueOf(page));
			root.put("allPage", String.valueOf(totalPage));

			if (display1) {
				root.put("firstPageStart",
						"<a href=\"" + firstPageBuffer.toString() + "\">");
				root.put("firstPageEnd", "</a>");
				root.put("firstStyle", "");
			} else {
				root.put("firstStyle", imgStyle);
				root.put("firstPageStart", "");
				root.put("firstPageEnd", "");
			}
			if (display2) {
				root.put("prevPageStart",
						"<a href=\"" + perPageBuffer.toString() + "\">");
				root.put("prevPageEnd", "</a>");
				root.put("prevStyle", "");
			} else {
				root.put("prevStyle", imgStyle);
				root.put("prevPageStart", "");
				root.put("prevPageEnd", "");
			}
			if (display3) {
				root.put("nextPageStart",
						"<a href=\"" + nextPageBuffer.toString() + "\">");
				root.put("nextPageEnd", "</a>");
				root.put("nextStyle", "");
			} else {
				root.put("nextStyle", imgStyle);
				root.put("nextPageStart", "");
				root.put("nextPageEnd", "");
			}
			if (display4) {
				root.put("lastPageStart",
						"<a href=\"" + lastPageBuffer.toString() + "\">");
				root.put("lastPageEnd", "</a>");
				root.put("lastStyle", "");
			} else {
				root.put("lastStyle", imgStyle);
				root.put("lastPageStart", "");
				root.put("lastPageEnd", "");
			}
			if (isPrevPageNumStyle) {
				root.put("prevPageNumStyle", imgStyle);
			} else {
				root.put("prevPageNumStyle", "");
			}
			if (isNextPageNumStyle) {
				root.put("nextPageNumStyle", imgStyle);
			} else {
				root.put("nextPageNumStyle", "");
			}
			root.put("prevPageNumStart", prevPageNumStart.toString());
			root.put("prevPageNumEnd", prevPageNumEnd.toString());
			root.put("nextPageNumStart", nextPageNumStart.toString());
			root.put("nextPageNumEnd", nextPageNumEnd.toString());
			root.put("pagePrevPage", pageBarBuffer.toString());
			root.put("pageBar", pageBarBuffer.toString());
			root.put("numPage", numPageBuffer.toString());
			root.put("jumpPage", jumpPageBuffer.toString());
			try {
				newsTemplate.process(root, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toString();
	}

	public int sumPage(int total, int perPage) {
		int totalPage = (total + perPage - 1) / perPage;
		return totalPage;
	}

	public int getCpage(int total, int perPage, int cPage) {
		if (cPage - sumPage(total, perPage) == 1) {
			cPage--;
		}

		if (cPage - sumPage(total, perPage) > 1 || cPage < 1) {
			cPage = 1;
		}
		return cPage;
	}
}
