package com.pikia.app.domain;

import java.util.Date;

import com.pikia.app.componet.base.BaseDomain;

public class AppImageDomain extends BaseDomain {
	private String picUrl;
	private Date createTime;

	private String fingerPrint;
	
	private int editDistance;

	

	public int getEditDistance() {
		return editDistance;
	}

	public void setEditDistance(int editDistance) {
		this.editDistance = editDistance;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getFingerPrint() {
		return fingerPrint;
	}

	public void setFingerPrint(String fingerPrint) {
		this.fingerPrint = fingerPrint;
	}

}
