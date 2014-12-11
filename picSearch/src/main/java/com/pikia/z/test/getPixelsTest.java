package com.pikia.z.test;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;

import com.pikia.app.util.ImageHelper;

public class getPixelsTest {
	public static void calculate(BufferedImage bi) {

		double sum = 0;
		int width = bi.getWidth();
		int height = bi.getHeight();

		int sgray[] = new int[width * height];
		for (int i = 0; i < sgray.length; i++) {
			sgray[i] = 0;
		}
		Raster ra = bi.getData();
		/*
		 * 图像的类型，看看它是多少位的.如果是32位 的要考虑aphal值通道,通过Raster对象来读取/写入像素， 它自动帮你处理成为32位的.
		 */

//		Rectangle rect = ra.getBounds();
//		int w = (int) rect.getWidth();
//		int h = (int) rect.getHeight();

		System.out.println(width + ":" + height);
		//System.out.println(w + ":" + h);

		int pixels[] = new int[width * height];
		pixels = ra.getPixels(0, 0, width, height, pixels); // 获得图片每个点的像素

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				// int rgb = bi.getRGB(i, j);
				int rgb = pixels[i * j];

				/*
				 * 应为使用getRGB(i,j)获取的该点的颜色值是ARGB，
				 * 而在实际应用中使用的是RGB，所以需要将ARGB转化成RGB， 即bufImg.getRGB(i, j) &
				 * 0xFFFFFF。
				 */
				int r = (rgb & 0xff0000) >> 16;
				int g = (rgb & 0xff00) >> 8;
				int b = (rgb & 0xff);
				int gray = (int) (r * 0.3 + g * 0.59 + b * 0.11); // 计算灰度值
				sgray[gray]++;
			}
		}
	}

	public static void main(String[] args) {

		// String home = System.getProperty("user.home");
		// System.out.println("用户主目录：" + home);
		String path = System.getProperty("user.dir");
		String filename = path + File.separator + "images" + File.separator
				+ "source.jpg";
		System.out.println(filename);
		BufferedImage source = ImageHelper.readPNGImage(filename);
		calculate(source);
	}
}