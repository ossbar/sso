package com.hihframework.core.customtaglibs.pagetag;

/**
 * <p> Title:前端页面的分页辅助类</p>
 * <p> Description:</p>
 * <p> Copyright: Copyright (c) 2013 </p>
 * <p> Company:hihsoft.co.,ltd </p>
 * @author hihsoft.co.,ltd
 * @version 1.0
 */
public class Page {
	/**
	 * 默认每页显示记录数
	 */
	private int perPage = 20;

	/**
	 * 页码
	 */
	private int page = 1;

	/**
	 * 记录总数
	 */
	private int total = 0;

	/**
	 * 开始记录数(新的游标起始位置)
	 */
	private int startRs = 0;

	/**
	 * 翻页的url(路径),不带参数,参数由其它属性指定
	 */
	private String url = "";

	/**
	 * 翻页参数,多个参数用&分隔
	 */
	private String param = "";

	/**
	 * 上下环境
	 */
	private String contextPath = "";

	/**
	 * 分页条可视页码1..10 11..20
	 */
	private int viewPage = 10;

	/**
	 * 要执行的hql语句
	 */
	private String hql;

	/**
	 * 项目在服务器上的真实路径
	 */
	private String realPath;

	/**
	 * 翻页条外观模板
	 */
	private String pageTemplate;

	/**
	 * 翻页外观模板路径
	 */
	private String pageTemplatePath;

	/**
	 * 用户自定义页数倍率
	 * 10 20 30 40....
	 */
	private int cNum = 4;

	public String getPageTemplatePath() {
		return pageTemplatePath;
	}

	public void setPageTemplatePath(String pageTemplatePath) {
		this.pageTemplatePath = pageTemplatePath;
	}

	public String getPageTemplate() {
		return pageTemplate;
	}

	public void setPageTemplate(String pageTemplate) {
		this.pageTemplate = pageTemplate;
	}

	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public String getHql() {
		return hql;
	}

	public void setHql(String hql) {
		this.hql = hql;
	}

	public Page() {
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPage() {
		return page;
	}

	public int getTotal() {
		return total;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getStartRs() {
		return startRs;
	}

	public void setStartRs(int startRs) {
		this.startRs = startRs;
	}

	public int getViewPage() {
		return viewPage;
	}

	public void setViewPage(int viewPage) {
		this.viewPage = viewPage;
	}

	public int getCNum() {
		return cNum;
	}

	public void setCNum(int num) {
		cNum = num;
	}
}