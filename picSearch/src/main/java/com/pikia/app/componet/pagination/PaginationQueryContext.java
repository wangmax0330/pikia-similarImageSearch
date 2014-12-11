package com.pikia.app.componet.pagination;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

public class PaginationQueryContext {
	// current page index
	private int pageIndex;
	private int pageSize;
	private Object[] params;
	private String sortField;
	private String sortType;
	private String[] columnField;
	// start page index
	private int startIndex;
	private HttpServletRequest request;

	public PaginationQueryContext(HttpServletRequest request) {
	
		String pageIndexStr = request.getParameter("pageIndex");
		this.pageIndex = (StringUtils.isNotBlank(pageIndexStr) ? new Integer(
				pageIndexStr).intValue() : 1);
		String pageSizeStr = request.getParameter("pageSize");
		this.pageSize = (StringUtils.isNotBlank(pageSizeStr) ? new Integer(
				pageSizeStr).intValue() : 20);
		this.sortField = request.getParameter("sortField");
		this.sortType = request.getParameter("sortType");
		this.request = request;
	}

	public int getPageIndex() {
		return this.pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String[] getColumnField() {
		return columnField;
	}

	public void setColumnField(String[] columnField) {
		this.columnField = columnField;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

}
