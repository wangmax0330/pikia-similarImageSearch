package com.pikia.app.domain;

import com.pikia.app.componet.base.BaseDomain;

public class ImageTaskDetail extends BaseDomain {
	private String picUrl;
	private String MD5;

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getMD5() {
		return MD5;
	}

	public void setMD5(String mD5) {
		MD5 = mD5;
	}
}
