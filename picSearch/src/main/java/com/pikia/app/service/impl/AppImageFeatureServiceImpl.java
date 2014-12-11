package com.pikia.app.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.pikia.app.componet.pagination.PaginationQueryContext;
import com.pikia.app.componet.repository.ModelRepository;
import com.pikia.app.componet.repository.MyBatisRepository;
import com.pikia.app.componet.service.impl.ModelCrudServiceSupport;
import com.pikia.app.domain.AppImageDomain;
import com.pikia.app.domain.ImageTaskDetail;
import com.pikia.app.repository.AppImageRepository;
import com.pikia.app.service.AppImageFeatureService;
import com.pikia.app.service.SimilarImageService;
import com.pikia.app.util.UploadAddress;

@Service
public class AppImageFeatureServiceImpl extends ModelCrudServiceSupport
		implements AppImageFeatureService {

	@Resource
	private SimilarImageService similarImageService;

	@Resource
	private UploadAddress uploadAddress;
	@Resource
	private AppImageRepository appImageRepository;

	@Override
	public List<ImageTaskDetail> getToDoImageList() {
		return null;
	}

	@Override
	public List getPagedModelIds(PaginationQueryContext queryContext) {
		String name = queryContext.getRequest().getParameter("name");
		List<Object> paramLs = new ArrayList<Object>();
		if (StringUtils.isNotBlank(name)) {
			paramLs.add("%" + name + "%");
		}
		queryContext.setParams(paramLs.toArray());
		return this.queryForPaginationList(new MyBatisRepository() {
			@Override
			public List queryForPagination(int startIndex, int pageSize,
					String sortField, String sortType, Object... params) {
				return appImageRepository.getPagedModelIds(startIndex,
						pageSize, sortField, sortType, params[0]);
			}
		}, queryContext);
	}

	@Override
	public int getTotalCount(PaginationQueryContext queryContext) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String GenerateFingerPrint(AppImageDomain appImageDomain) {

		String filename = uploadAddress.getUploadPicPath() + File.separator
				+ appImageDomain.getPicUrl();
		String fingerPrint = similarImageService.produceFingerPrint(filename);
		if (StringUtils.isNotBlank(fingerPrint)) {
			return fingerPrint;
		}
		return null;
	}

	@Override
	protected ModelRepository getModelRepository() {
		return appImageRepository;
	}

	@Override
	public Object getModel(Long paramLong) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int GenerateHamming(AppImageDomain sourceImageDomain,
			AppImageDomain targetImageDomain) {
		return similarImageService.hammingDistance( 
				sourceImageDomain.getFingerPrint(),
				targetImageDomain.getFingerPrint());
	}

}
