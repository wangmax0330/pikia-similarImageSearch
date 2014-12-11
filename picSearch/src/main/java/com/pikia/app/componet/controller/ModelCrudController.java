package com.pikia.app.componet.controller;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.pikia.app.service.SessionService;
import com.pikia.app.util.ResponseUtils;

public abstract class ModelCrudController extends ModelCrudOperator implements
		ApplicationContextAware {
	protected final Log logger = LogFactory.getLog(getClass());
	@Resource
	protected SessionService sessionService;
	protected ApplicationContext context;

	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		this.context = context;
	}

//	public void ajaxList(HttpServletResponse response,
//			PaginationQueryContext queryContext, Class<?> modelClass) {
//		try {
//			SortPagedList results = doList(queryContext, modelClass);
//			ResponseUtils.writeMessage(response,
//					results.serialize2Json(queryContext.getColumnField()));
//		} catch (Exception ex) {
//			if (this.logger.isErrorEnabled()) {
//				this.logger.error("**Error occurred in method ajaxList", ex);
//			}
//			ResponseUtils.writeJsonErrorMessage(response, ex.getMessage(), null);
//		}
//	}

//	public Object ajaxUpdate(HttpServletRequest request,
//			HttpServletResponse response, Object model, Object id,
//			String modelName, String[] successMessageParameters) {
//		try {
//			Object updated = doUpdate(model, id);
//			ResponseUtils.writeJsonSuccessMessage(
//					response,
//					getMessage(modelName + ".controller.save.success",
//							successMessageParameters, this.sessionService
//									.getCurrentLocal(request.getSession())),
//					new String[] { id.toString() });
//			return updated;
//		} catch (Exception ex) {
//			if (this.logger.isErrorEnabled()) {
//				this.logger.error("**Error occurred in method ajaxUpdate", ex);
//			}
//			ResponseUtils
//					.writeJsonErrorMessage(response, ex.getMessage(), null);
//		}
//		return null;
//	}

//	public void ajaxDelete(HttpServletRequest request,
//			HttpServletResponse response, Class<?> modelClass, String modelName) {
//		try {
//			String idsString = request.getParameter("ids");
//
//			if (idsString == null) {
//				return;
//			}
//
//			String[] idArr = idsString.split(",");
//			Long[] ids = new Long[idArr.length];
//
//			for (int i = 0; i < idArr.length; i++) {
//				long id = Long.parseLong(idArr[i]);
//				ids[i] = Long.valueOf(id);
//			}
//
//			doDelete(modelClass, ids);
//
//			ResponseUtils.writeJsonSuccessMessage(
//					response,
//					getMessage(modelName + ".controller.delete.success", null,
//							this.sessionService.getCurrentLocal(request
//									.getSession())), null);
//		} catch (Exception ex) {
//			if (this.logger.isErrorEnabled()) {
//				this.logger.error("Error occurred in method ajaxDelete", ex);
//			}
//			ResponseUtils
//					.writeJsonErrorMessage(response, ex.getMessage(), null);
//		}
//	}

	protected String getMessage(String key, String[] params, Locale local) {
		String defaultMsg = "success";
		try {
			defaultMsg = this.context.getMessage(key, params, local);
		} catch (Exception e) {
		}
		return defaultMsg;
	}

}
