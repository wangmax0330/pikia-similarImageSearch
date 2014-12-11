package com.pikia.app.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.pikia.app.componet.pagination.PaginationQueryContext;
import com.pikia.app.componet.repository.ModelRepository;
import com.pikia.app.componet.repository.MyBatisRepository;
import com.pikia.app.componet.service.impl.ModelCrudServiceSupport;
import com.pikia.app.domain.AppImageDomain;
import com.pikia.app.repository.AppImageRepository;
import com.pikia.app.service.AppImageFeatureService;
import com.pikia.app.service.AppImageService;
import com.pikia.app.util.DateUtils;
import com.pikia.app.util.JsonUtils;

@Service
public class AppImageServiceImpl extends ModelCrudServiceSupport implements
		AppImageService {
	@Resource
	protected AppImageRepository appImageRepository;
	@Resource
	protected AppImageFeatureService appImageFeatureService;

	@Override
	public Object getModel(Long id) {
		return appImageRepository.get(id);
	}

	@Override
	public List getPagedModelIds(PaginationQueryContext queryContext) {
		HttpServletRequest request = queryContext.getRequest();
		List<Object> paramLs = new ArrayList<Object>();
		queryContext.setParams(paramLs.toArray());
		return this.queryForPaginationList(new MyBatisRepository() {
			@Override
			public List<Long> queryForPagination(int startIndex, int pageSize,
					String sortField, String sortType, Object... params) {
				return appImageRepository.getPagedModelIds(startIndex,
						pageSize, sortField, null, null);
			}
		}, queryContext);
	}

	@Override
	public int getTotalCount(PaginationQueryContext queryContext) {
		return appImageRepository.getTotalCount(null);
	}

	@Override
	public String getJsonList(List<AppImageDomain> imgList) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (AppImageDomain tmp : imgList) {
			list.add(getJson(tmp));
		}
		return JsonUtils.JSON_List2String(list);
	}

	public Map<String, Object> getJson(AppImageDomain tmp) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		jsonMap.put("id", tmp.getId());
		jsonMap.put("picUrl", tmp.getPicUrl());
		jsonMap.put("createTime",
				DateUtils.date2Str(tmp.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
		return jsonMap;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Object saveOrUpdate(AppImageDomain domain) {
		return super.saveOrUpdate(domain);
	}

	@Override
	protected ModelRepository getModelRepository() {
		return this.appImageRepository;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateEditDistance(AppImageDomain domain) {
		int total = appImageRepository.getTotalList(null);
		// 分页的数量
		int num = 15;

		int count = (int) (total / num);
		// 每个分页的图片处理
		for (int i = 0; i < (count + 1); i++) {
			List<Long> imageList = appImageRepository.getModelIdList(i * num,
					num, null, null, null);
			for (Long id : imageList) {
				AppImageDomain image = appImageRepository.get(id);
				int hamming = appImageFeatureService.GenerateHamming(image,
						domain);
				image.setEditDistance(hamming);
				System.out.println(image.getPicUrl() + "    "
						+ image.getFingerPrint() + "   "
						+ image.getCreateTime());
				appImageRepository.update(image);
			}
		}

		System.out.println("所有数据处理成功");
	}
}
