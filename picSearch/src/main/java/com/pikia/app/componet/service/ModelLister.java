package com.pikia.app.componet.service;

import java.util.List;

import com.pikia.app.componet.pagination.PaginationQueryContext;
import com.pikia.app.componet.pagination.SortPagedList;

public abstract interface ModelLister {
	public abstract SortPagedList<?> list(
			PaginationQueryContext paramPaginationQueryContext,
			Class<?> paramClass);

	public abstract SortPagedList<?> list(
			PaginationQueryContext paramPaginationQueryContext,
			Class<?> paramClass, List paramList, Integer paramInteger);
}
