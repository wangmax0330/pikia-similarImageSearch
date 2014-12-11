package com.pikia.app.componet.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pikia.app.componet.base.BaseDomain;
import com.pikia.app.componet.pagination.PaginationQueryContext;
import com.pikia.app.componet.pagination.SortPagedList;
import com.pikia.app.componet.repository.ModelRepository;
import com.pikia.app.componet.repository.MyBatisRepository;
import com.pikia.app.componet.service.ModelCrudService;

/**
 * 创建方法的原型，而真正的实现留给使用这个类的人
 */
public abstract class ModelCrudServiceSupport implements ModelCrudService {

	protected final Log logger = LogFactory.getLog(getClass());

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Object saveOrUpdate(Object model) {
		BaseDomain domain = (BaseDomain) model;
		if (domain == null)
			return null;
		if ((domain.getId() != null) && (domain.getId().longValue() > 0L)) {
			getModelRepository().update(model);
		} else {
			getModelRepository().save(model);
		}
		return model;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void delete(Long[] ids) {
		for (Long id : ids)
			getModelRepository().delete(id);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void delete(Long id) {
		getModelRepository().delete(id);
	}

	protected List<?> queryForPaginationList(MyBatisRepository re,
			PaginationQueryContext queryContext) {
		Object[] params = queryContext.getParams();
		int startIndex = queryContext.getStartIndex();
		int pageSize = queryContext.getPageSize();
		return re
				.queryForPagination(startIndex, pageSize,
						queryContext.getSortField(),
						queryContext.getSortType(), params);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public SortPagedList<?> list(PaginationQueryContext queryContext,
			Class<?> modelClass) {
		return list(queryContext, modelClass, null, null);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = true, rollbackFor = { Exception.class })
	public SortPagedList<?> list(PaginationQueryContext queryContext,
			Class<?> modelClass, List pageModelIds, Integer totalCount) {
		int pageIndex = queryContext.getPageIndex();
		int pageSize = queryContext.getPageSize();
		int startIndex;
		if (pageIndex <= 0) {
			startIndex = 0;
		} else {
			startIndex = (pageIndex - 1) * pageSize;
		}
		queryContext.setStartIndex(startIndex);

		SortPagedList pl = new SortPagedList();
		List pagedModels = new ArrayList();
		List<Long> idList = getPagedModelIds(queryContext);
		for (Long id : idList) {
			pagedModels.add(this.getModel(id));
		}

		pl.orderField(queryContext.getSortField())
				.orderType(queryContext.getSortType())
				.pageIndex(pageIndex)
				.pageSize(pageSize)
				.totalItemCount(
						totalCount == null ? getTotalCount(queryContext)
								: totalCount.intValue()).setItems(pagedModels);

		return pl;
	}

	protected abstract ModelRepository getModelRepository();

}
