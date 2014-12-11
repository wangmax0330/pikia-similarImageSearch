package com.pikia.app.service;

import java.util.List;

import com.pikia.app.componet.pagination.PaginationQueryContext;
import com.pikia.app.componet.pagination.SortPagedList;
import com.pikia.app.componet.service.ModelCrudService;
import com.pikia.app.domain.AppImageDomain;

/**
 * 图片保存,修改接口
 * 
 * @author methew
 * 
 */
public interface AppImageService extends ModelCrudService {

	public Object saveOrUpdate(AppImageDomain domain);

	public String getJsonList(List<AppImageDomain> imgList);

	// 更新数据库所有图片的EDITDISTANCE
	public void updateEditDistance(AppImageDomain domain);

}
