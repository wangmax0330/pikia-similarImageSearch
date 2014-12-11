package com.pikia.app.service;

import java.io.File;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public abstract interface AppCommonService {
	public abstract String uploadFile(
			CommonsMultipartFile paramCommonsMultipartFile,
			String paramString1, boolean paramBoolean1, String paramString2,
			boolean paramBoolean2);

	public abstract String uploadFile(byte[] paramArrayOfByte,
			String paramString1, String paramString2, boolean paramBoolean1,
			String paramString3, boolean paramBoolean2);

	public abstract String uploadFile(File paramFile, String paramString1,
			boolean paramBoolean1, String paramString2, boolean paramBoolean2);
}
