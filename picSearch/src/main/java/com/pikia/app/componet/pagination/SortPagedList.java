package com.pikia.app.componet.pagination;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.pikia.app.componet.base.BaseDomain;

public class SortPagedList<T> extends SimplePagedList<T> implements
		PagedList<T> {

	private String orderField;
	private String orderType;

	public SortPagedList() {
		pageSize(20);
	}

	public int getPreviousPageIndex() {
		return (getCurrentPageIndex() - 1) / 5 * 5;
	}

	public int getNextPageIndex() {
		return (getCurrentPageIndex() - 1) / 5 * 5 + 6;
	}

	public String getOrderField() {
		return this.orderField;
	}

	public SortPagedList<T> orderField(String orderField) {
		this.orderField = orderField;
		return this;
	}

	public String getOrderType() {
		return this.orderType;
	}

	public SortPagedList<T> orderType(String orderType) {
		this.orderType = orderType;
		return this;
	}

	public boolean hasNext5Pages() {
		if ((getCurrentPageIndex() - 1) / 5 * 5 + 6 > getTotalPageCount()) {
			return false;
		}
		return true;
	}

	public boolean hasPrevious5Pages() {
		if ((getCurrentPageIndex() - 1) / 5 > 0) {
			return true;
		}
		return false;
	}

	public int[] getCurrentPageIndexes() {
		int min = (getCurrentPageIndex() - 1) / 5 * 5 + 1;
		int max = (getCurrentPageIndex() - 1) / 5 * 5 + 5;
		if (max >= getTotalPageCount())
			max = getTotalPageCount();

		int[] pages = new int[max - min + 1];
		int i = 0;
		for (int j = min; j <= max; i++) {

			pages[i] = j;
			j++;
		}
		return pages;

	}

	public String serialize2Json(final String[] columnFields) {
		StringBuffer sb = new StringBuffer();
		sb.append(pageDescription());
		List items = getItems();
		int rowsLen = items.size();
		for (int idx = 0; idx < rowsLen; idx++) {
			Object tmp = items.get(idx);
			if (idx > 0) {
				sb.append(",");
			}

			if ((tmp instanceof BaseDomain)) {

			}

		}
		return null;
	}

	// 封装成json 格式
	public StringBuffer pageDescription() {
		StringBuffer sb = new StringBuffer();
		sb.append("{\"isSuc\":1,\"pagi\":{\"currPage\":")
				.append(getCurrentPageIndex()).append(",");
		sb.append("\"previousPage\":").append(getPreviousPageIndex())
				.append(",");
		sb.append("\"nextPage\":").append(getNextPageIndex()).append(",");
		sb.append("\"pages\":[");
		int[] currentPageIndexes = getCurrentPageIndexes();
		for (int i = 0; i < currentPageIndexes.length; i++) {
			if (i > 0) {
				sb.append(",");
			}
			sb.append(currentPageIndexes[i]);
		}
		sb.append("],");
		sb.append("\"hasPrevious5Page\":").append(hasPrevious5Pages())
				.append(",");
		sb.append("\"hasNext5Page\":").append(hasNext5Pages()).append(",");
		sb.append("\"pageSize\":").append(getPageSize()).append(",");
		sb.append("\"orderField\":\"").append(getOrderField()).append("\",");
		if (StringUtils.isNotBlank(getOrderType()))
			sb.append("\"orderType\":\"").append(getOrderType()).append("\",");
		else {
			sb.append("\"orderType\":\"asc\",");
		}
		sb.append("\"previousPage\":").append(getPreviousPageIndex())
				.append(",");
		sb.append("\"records\":").append(getTotalItemCount()).append(",");

		sb.append("\"pageIndex\":").append(getCurrentPageIndex()).append(",");
		sb.append("\"total\":").append(getTotalPageCount()).append("},");
		sb.append("\"rows\":[");
		return sb;
	}

	// 封装成json 格式
	public StringBuffer pageDescription(String rows) {
		StringBuffer sb = new StringBuffer();
		sb.append("{\"isSuc\":1,\"pagi\":{\"currPage\":")
				.append(getCurrentPageIndex()).append(",");
		sb.append("\"previousPage\":").append(getPreviousPageIndex())
				.append(",");
		sb.append("\"nextPage\":").append(getNextPageIndex()).append(",");
		sb.append("\"pages\":[");
		int[] currentPageIndexes = getCurrentPageIndexes();
		for (int i = 0; i < currentPageIndexes.length; i++) {
			if (i > 0) {
				sb.append(",");
			}
			sb.append(currentPageIndexes[i]);
		}
		sb.append("],");
		sb.append("\"hasPrevious5Page\":").append(hasPrevious5Pages())
				.append(",");
		sb.append("\"hasNext5Page\":").append(hasNext5Pages()).append(",");
		sb.append("\"pageSize\":").append(getPageSize()).append(",");
		sb.append("\"orderField\":\"").append(getOrderField()).append("\",");

		if (StringUtils.isNotBlank(getOrderType()))
			sb.append("\"orderType\":\"").append(getOrderType()).append("\",");
		else {
			sb.append("\"orderType\":\"asc\",");
		}
		sb.append("\"previousPage\":").append(getPreviousPageIndex())
				.append(",");
		sb.append("\"records\":").append(getTotalItemCount()).append(",");
		sb.append("\"pageIndex\":").append(getCurrentPageIndex()).append(",");
		sb.append("\"total\":").append(getTotalPageCount()).append("},");
		sb.append("\"rows\":");
		if ((rows != null) && (!"".equals(rows))) {
			if (rows.startsWith("["))
				sb.append(rows);
			else
				sb.append("[" + rows + "]");
		} else {
			sb.append("[]");
		}
		sb.append("}");
		return sb;
	}
}
