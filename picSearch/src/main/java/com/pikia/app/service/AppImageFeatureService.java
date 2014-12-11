package com.pikia.app.service;

import java.util.List;

import com.pikia.app.componet.service.ModelCrudService;
import com.pikia.app.domain.AppImageDomain;
import com.pikia.app.domain.ImageTaskDetail;

/**
 * 生成图片特征接口
 * 
 * @author methew
 * 
 */
public interface AppImageFeatureService extends ModelCrudService {

	// 自动处理图片
	public List<ImageTaskDetail> getToDoImageList();

	// 生图片的指纹
	public String GenerateFingerPrint(AppImageDomain appImageDomain);

	// 生成汉明距离
	public int GenerateHamming(AppImageDomain sourceImageDomain,
			AppImageDomain targetImageDomain);

	// public

}
