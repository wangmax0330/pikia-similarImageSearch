package com.pikia.app.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageHelper {
	//当前工作目录
	public static String path = System.getProperty("user.dir");

	/*
	 * * 生成缩略图 保存:ImageIO.write(BufferedImage, imgType[jpg/png/...], File);
	 * 
	 * @param source 原图片
	 * 
	 * @param width 缩略图宽
	 * 
	 * @param height 缩略图高
	 * 
	 * @param b 是否等比缩放
	 */
	public static BufferedImage thumb(BufferedImage source, int width,
			int height, boolean b) {
		// taggetW,targetH 分别表示目标的长和宽
		int type = source.getType();
		BufferedImage target = null;
		double sx = (double) width / source.getWidth();
		double sy = (double) height / source.getHeight();
		// 判断是否是等比缩放. 为等比缩放计算输出的图片宽度及高度
		if (b) {
			if (sx > sy) {
				sx = sy;
				width = (int) (sx * source.getWidth());
			} else {
				sy = sx;
				height = (int) (sy * source.getHeight());
			}
		}
		if (type == BufferedImage.TYPE_CUSTOM) {
			ColorModel cm = source.getColorModel();
			// WritableRaster 扩展了 Raster 以提供像素写入功能
			WritableRaster raster = cm.createCompatibleWritableRaster(width,
					height);
			// alphaPremultiplied
			// 返回是否已预乘以 alpha。
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else {
			target = new BufferedImage(width, height, type);
		}

		Graphics2D g = target.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		;
		g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		return target;
	}

	/**
	 * 图片水印
	 * 
	 * @param imgPath待处理图片
	 * @param markPath水印图片
	 * @param x
	 *            水印位于图片左上角的 x 坐标值
	 * @param y
	 *            水印位于图片左上角的 y 坐标值
	 * @param alpha
	 *            水印透明度 0.1f ~ 1.0f
	 */
	public static void waterMark(String imgPath, String markPath, int x, int y,
			float alpha) {
		try {
			// 加载处理图片文件
			Image img = ImageIO.read(new File(imgPath));
			BufferedImage image = new BufferedImage(img.getWidth(null),
					img.getHeight(null), BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(img, 0, 0, null);

			// 加载图片水印文件
			Image src = ImageIO.read(new File(markPath));
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));
			g.drawImage(src, x, y, null);
			g.dispose();

			// 保存处理后的文件
			FileOutputStream out = new FileOutputStream(imgPath);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 文字水印
	 * 
	 * @param imgPath
	 *            待处理图片
	 * @param text
	 *            水印文字
	 * @param font
	 *            水印字体信息
	 * @param color
	 *            水印字体颜色
	 * @param x
	 *            水印位于图片左上角的 x 坐标值
	 * @param y
	 *            水印位于图片左上角的 y 坐标值
	 * @param alpha
	 *            水印透明度 0.1f ~ 1.0f
	 */
	public static void textMark(String imgPath, String text, Font font,
			Color color, int x, int y, float alpha) {
		try {
			// String 字体，int 风格，int 字号
			Font Dfont = (font == null) ? new Font("宋体", 20, 13) : font;
			Image img = ImageIO.read(new File(imgPath));
			BufferedImage image = new BufferedImage(img.getWidth(null),
					img.getHeight(null), BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.drawImage(img, 0, 0, null);
			g.setColor(color);
			g.setFont(Dfont);

			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));
			g.drawString(text, x, y);
			g.dispose();
			FileOutputStream out = new FileOutputStream(imgPath);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 读取JPEG图片
	 * 
	 * @param filename
	 *            文件名
	 * @return BufferedImage 图片对象
	 */
	public static BufferedImage readJPEGImage(String filename) {
		try {
			InputStream imageIn = new FileInputStream(new File(filename));
			// 得到输入的编码器,将文件流进行jpg 格式编码
			JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(imageIn);
			// 得到编码后的图片对象
			BufferedImage sourceImage = decoder.decodeAsBufferedImage();
			return sourceImage;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ImageFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 读取JPEG图片
	 * 
	 * @param filename
	 *            文件名
	 * @return BufferedImage 图片对象
	 */
	public static BufferedImage readPNGImage(String filename) {
		try {
			File inputFile = new File(filename);
			BufferedImage sourceImage = ImageIO.read(inputFile);
			return sourceImage;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (ImageFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 灰度值计算
	 * 
	 * @param pixels
	 *            像素
	 * @return int 灰度值
	 */
	public static int rgbToGray(int pixels) {
		// int _alpha = (pixels >> 24) & 0xFF;
		// 应为使用getRGB(i,j)获取的该点的颜色值是ARGB，
		// 而在实际应用中使用的是RGB，所以需要将ARGB转化成RGB，
		// 即bufImg.getRGB(i, j) & 0xFFFFFF。
		int _red = (pixels >> 16) & 0xFF;
		int _green = (pixels >> 8) & 0xFF;
		int _blue = (pixels) & 0xFF;
		// 通过浮点算法：Gray=R*0.3+G*0.59+B*0.11 转换灰度值
		return (int) (0.3 * _red + 0.59 * _green + 0.11 * _blue);
	}

	/**
	 * 计算数组的平均值
	 * 
	 * @param pixels
	 *            数组
	 * @return int 平均值
	 */
	public static int average(int[] pixels) {
		float m = 0;
		for (int i = 0; i < pixels.length; ++i) {
			m += pixels[i];
		}
		m = m / pixels.length;
		return (int) m;
	}

}
