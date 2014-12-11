package com.pikia.app.componet.pagination;

import java.util.List;

public class SimplePagedList<T> implements PagedList<T> {
	private int pageIndex = 1;
	private int pageSize = 10;
	private int totalItemCount = 0;
	private List<T> items;

	public SimplePagedList() {

	}

	public SimplePagedList(int pageIndex, int pageSize, int totalItemCount,
			List<T> items) {
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.totalItemCount = totalItemCount;
		this.items = items;
	}

	public SimplePagedList<T> pageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
		return this;
	}

	public SimplePagedList<T> pageSize(int pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	public SimplePagedList<T> totalItemCount(int totalItemCount) {
		this.totalItemCount = totalItemCount;
		return this;
	}

	public SimplePagedList<T> items(List<T> items) {
		this.items = items;
		return this;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	@Override
	public List getItems() {
		return this.items;
	}

	@Override
	public int getTotalPageCount() {
		int totalPage = this.totalItemCount / this.pageSize;
		if ((this.totalItemCount % this.pageSize > 0) || (totalPage == 0)) {
			totalPage++;
		}
		return totalPage;
	}

	@Override
	public int getTotalItemCount() {
		return this.totalItemCount;
	}

	@Override
	public int getCurrentPageIndex() {
		return this.pageIndex;
	}

	@Override
	public int getPageSize() {
		return this.pageSize;
	}

	@Override
	public int getNextPageIndex() {
		return this.pageIndex + 1;
	}

	@Override
	public int getPreviousPageIndex() {
		return this.pageIndex - 1;
	}

}
