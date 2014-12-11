package com.pikia.app.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.pikia.app.componet.controller.ModelCrudController;
import com.pikia.app.componet.pagination.PaginationQueryContext;
import com.pikia.app.componet.pagination.SortPagedList;
import com.pikia.app.domain.AppImageDomain;
import com.pikia.app.service.AppCommonService;
import com.pikia.app.service.AppImageFeatureService;
import com.pikia.app.service.AppImageService;
import com.pikia.app.util.ResponseUtils;

@Controller
public class AppImageController extends ModelCrudController {

	@Resource
	private AppCommonService commonService;
	@Resource
	private AppImageService appImageService;
	@Resource
	private AppImageFeatureService appImageFeatureService;

	/**
	 * 上传图片并返回图片地址
	 */
	@RequestMapping(value = "/image/pic/upload", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void uploadPic(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Map<String, MultipartFile> files = multipartRequest.getFileMap();
			CommonsMultipartFile file = (CommonsMultipartFile) files
					.get("Filedata");
			// UploadAddress uploadAddress = (UploadAddress)
			// context.getBean("uploadAddress");

			// 获取文件原来的名字
			String fileName = file.getOriginalFilename();
			String picUrl = commonService.uploadFile(file, "hat", false, null,
					false);
			// 生成特征码
			ResponseUtils.writeJsonSuccessMessage(response, picUrl, null);

		} catch (Exception e) {
			logger.error(e, e);
			ResponseUtils.writeJsonErrorMessage(response, e.getMessage(), null);
		}
	}

	/**
	 * 对图片搜索
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/image/search", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String imageSearch(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String picUrl = request.getParameter("pic");
			AppImageDomain imageDomain = new AppImageDomain();
			imageDomain.setPicUrl(picUrl);
			imageDomain.setFingerPrint(appImageFeatureService
					.GenerateFingerPrint(imageDomain));
			imageDomain.setCreateTime(new Date());
			appImageService.saveOrUpdate(imageDomain);
			// 生成汉明距离
			System.out.println("++++++++++++++++生成汉明距离+++++++++++++++");
			appImageService.updateEditDistance(imageDomain);
		} catch (Exception e) {
			logger.error(e, e);
		}
		return "/image/result";
	}

	/**
	 * 查看所有识别过的图片
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/image/list", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void imageList(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PaginationQueryContext queryContext = new PaginationQueryContext(
				request);
		queryContext.setStartIndex((queryContext.getPageIndex() - 1)
				* queryContext.getPageSize());
		queryContext.setPageSize(queryContext.getPageSize());

		SortPagedList<AppImageDomain> pagedList = (SortPagedList<AppImageDomain>) appImageService
				.list(queryContext, AppImageDomain.class,
						appImageService.getPagedModelIds(queryContext),
						appImageService.getTotalCount(queryContext));

		List<AppImageDomain> list = pagedList.getItems();

		ResponseUtils.writeMessage(response,
				pagedList.pageDescription(appImageService.getJsonList(list))
						.toString());

	}
}
