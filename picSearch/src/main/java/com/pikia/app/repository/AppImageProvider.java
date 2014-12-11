package com.pikia.app.repository;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.jdbc.SelectBuilder;
import org.apache.tools.ant.taskdefs.Sleep;

import freemarker.template.utility.StringUtil;

public class AppImageProvider {
	
	public String getPagedModelIds(Map<String, Object> parameter) {
		SelectBuilder.BEGIN();
		SelectBuilder.SELECT_DISTINCT("t.ID IID");
		SelectBuilder.FROM("APP_IMAGE t");
		SelectBuilder.WHERE(" t.EDITDISTANSE <13 ");
		SelectBuilder.ORDER_BY(" t.EDITDISTANSE ASC ");
		String sql = SelectBuilder.SQL();
		return sql + "  limit #{startIndex},#{pageSize}";
	}

	public String getTotalCount(Map<String, Object> parameters) {
		String name = (String) parameters.get("name");
		SelectBuilder.BEGIN();
		SelectBuilder.SELECT("COUNT(C.ID)");
		SelectBuilder.FROM("APP_IMAGE C");
		SelectBuilder.WHERE(" C.EDITDISTANSE <13 ");
		if (StringUtils.isNotBlank(name)) {
			// SelectBuilder.WHERE("");
		}
		return SelectBuilder.SQL();
	}
	
	
	public String getModelIdList(Map<String, Object> parameter) {
		SelectBuilder.BEGIN();
		SelectBuilder.SELECT_DISTINCT("t.ID IID");
		SelectBuilder.FROM("APP_IMAGE t");
		SelectBuilder.ORDER_BY(" t.EDITDISTANSE ASC ");
		String sql = SelectBuilder.SQL();
		return sql + "  limit #{startIndex},#{pageSize}";
	}

	public String getTotalList(Map<String, Object> parameters) {
		String name = (String) parameters.get("name");
		SelectBuilder.BEGIN();
		SelectBuilder.SELECT("COUNT(C.ID)");
		SelectBuilder.FROM("APP_IMAGE C");
		if (StringUtils.isNotBlank(name)) {
			// SelectBuilder.WHERE("");
		}
		return SelectBuilder.SQL();
	}
}
