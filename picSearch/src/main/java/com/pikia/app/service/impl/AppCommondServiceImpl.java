package com.pikia.app.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.pikia.app.service.AppCommonService;
import com.pikia.app.util.DateUtils;
import com.pikia.app.util.UploadAddress;

@Service
public class AppCommondServiceImpl implements AppCommonService {
	@Resource
	private UploadAddress uploadAddress;

	@Override
	public String uploadFile(CommonsMultipartFile file, String pathFolder,
			boolean waterMark, String thum, boolean whitemark) {
		return uploadFile(file.getBytes(), file.getOriginalFilename(),
				pathFolder, waterMark, thum, whitemark);
	}

	@Override
	public String uploadFile(byte[] bytes, String fileTrueName,
			String pathFolder, boolean waterMark, String thum, boolean whitemark) {
		try {
			Date date = new Date();
			String folder = new StringBuilder()
					.append(StringUtils.isBlank(pathFolder) ? "undefine"
							: pathFolder).append("/")
					.append(DateUtils.date2Str(date, "yyyy")).append("/")
					.append(DateUtils.date2Str(date, "MM")).append("/")
					.append(DateUtils.date2Str(date, "dd")).append("/")
					.append(DateUtils.date2Str(date, "HH")).append("/")
					.toString();

			String savePath = new StringBuilder()
					.append(this.uploadAddress.getUploadPicPath())
					.append(folder).toString();
			String uploadPath = this.uploadAddress.getUploadPicPath();
			File file = new File(savePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			if (bytes.length != 0) {
				String extName = fileTrueName.substring(
						fileTrueName.lastIndexOf(".")).toLowerCase();
				String name = new StringBuilder()
						.append(System.currentTimeMillis()).append("")
						.toString();
				File saveFile = new File(new StringBuilder()
						.append(savePath + File.separator).append(name)
						.append(extName).toString());

				FileCopyUtils.copy(bytes, saveFile);
				return new StringBuilder().append(folder).append(name)
						.append(extName).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	private byte[] getBytes(File file) {
		byte[] buffer = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}

	@Override
	public String uploadFile(File paramFile, String paramString1,
			boolean paramBoolean1, String paramString2, boolean paramBoolean2) {
		// TODO Auto-generated method stub
		return null;
	}
}
