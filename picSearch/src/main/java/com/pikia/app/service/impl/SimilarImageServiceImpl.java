package com.pikia.app.service.impl;

import java.awt.image.BufferedImage;

import org.springframework.stereotype.Service;

import com.pikia.app.service.SimilarImageService;
import com.pikia.app.util.ImageHelper;

@Service
public class SimilarImageServiceImpl implements SimilarImageService {
	/**
	 * 计算"汉明距离"（Hamming distance）。 如果不相同的数据位不超过5，就说明两张图片很相似；如果大于10，就说明这是两张不同的图片。
	 * 
	 * @param sourceHashCode
	 *            源hashCode
	 * @param hashCode
	 *            与之比较的hashCode
	 */
	@Override
	public int hammingDistance(String sourceHashCode, String hashCode) {
		int difference = 0;
		int len = sourceHashCode.length();
		for (int i = 0; i < len; i++) {
			if (sourceHashCode.charAt(i) != hashCode.charAt(i)) {
				difference++;
			}
		}
		return difference;
	}

	/**
	 * 生成图片指纹
	 * 
	 * @param filename
	 *            文件名
	 * @return 图片指纹
	 */
	@Override
	public String produceFingerPrint(String filename) {
		// 读取文件
		BufferedImage source = ImageHelper.readPNGImage(filename);
		// 第一步，缩小尺寸。
		// 将图片缩小到8x8的尺寸，总共64个像素。这一步的作用是去除图片的细节，只保留结构、明暗等基本信息，摒弃不同尺寸、比例带来的图片差异。
		int width = 8;
		int height = 8;
		BufferedImage thumb = ImageHelper.thumb(source, width, height, false);

		// 第二步，简化色彩。
		// 将缩小后的图片，转为64级灰度。也就是说，所有像素点总共只有64种颜色。
		// 获得图片每个点的像素
		int[] pixels = new int[width * height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				pixels[i * height + j] = ImageHelper.rgbToGray(thumb.getRGB(i,
						j));
			}
		}
		// 第三步，计算平均值。
		// 计算所有64个像素的灰度平均值。
		int avgPixel = ImageHelper.average(pixels);
		// 第四步，比较像素的灰度。
		// 将每个像素的灰度，与平均值进行比较。大于或等于平均值，记为1；小于平均值，记为0。
		int[] comps = new int[width * height];
		for (int i = 0; i < comps.length; i++) {
			if (pixels[i] >= avgPixel) {
				comps[i] = 1;
			} else {
				comps[i] = 0;
			}
		}
		// 第五步，计算哈希值。
		// 将上一步的比较结果，组合在一起，就构成了一个64位的整数，这就是这张图片的指纹。组合的次序并不重要，只要保证所有图片都采用同样次序就行了。

		StringBuffer hashCode = new StringBuffer();

		// s[0]*31^(n-1) + s[1]*31^(n-2) +…+ s[n-1] n是字符串长度
		for (int i = 0; i < comps.length; i += 4) {
			int result = comps[i] * (int) Math.pow(2, 3) + comps[i + 1]
					* (int) Math.pow(2, 2) + comps[i + 2]
					* (int) Math.pow(2, 1) + comps[i + 2];
			hashCode.append(binaryToHex(result));
		}
		// 得到指纹以后，就可以对比不同的图片，看看64位中有多少位是不一样的。
		return hashCode.toString();
	}

	/**
	 * 二进制转为十六进制
	 * 
	 * @param int binary
	 * @return char hex
	 */
	private char binaryToHex(int binary) {
		char ch = ' ';
		switch (binary) {
		case 0:
			ch = '0';
			break;
		case 1:
			ch = '1';
			break;
		case 2:
			ch = '2';
			break;
		case 3:
			ch = '3';
			break;
		case 4:
			ch = '4';
			break;
		case 5:
			ch = '5';
			break;
		case 6:
			ch = '6';
			break;
		case 7:
			ch = '7';
			break;
		case 8:
			ch = '8';
			break;
		case 9:
			ch = '9';
			break;
		case 10:
			ch = 'a';
			break;
		case 11:
			ch = 'b';
			break;
		case 12:
			ch = 'c';
			break;
		case 13:
			ch = 'd';
			break;
		case 14:
			ch = 'e';
			break;
		case 15:
			ch = 'f';
			break;
		default:
			ch = ' ';
		}
		return ch;
	}

	@Override
	public float similarityRate(String source, String target) {

		char[] s = source.toCharArray();
		char[] t = target.toCharArray();
		int slen = source.length();
		int tlen = target.length();
		int total = slen + tlen;
		int i = EditDistance(source, target, s, t, slen, tlen);
		float result = ((float) (total - i) / total);
		return result;
	}

	@Override
	public int EditDistance(String source, String target) {
		char[] s = source.toCharArray();
		char[] t = target.toCharArray();
		int slen = source.length();
		int tlen = target.length();
		int d[][] = new int[slen + 1][tlen + 1];
		for (int i = 0; i <= slen; i++) {
			d[i][0] = i;
		}
		for (int i = 0; i <= tlen; i++) {
			d[0][i] = i;
		}
		for (int i = 1; i <= slen; i++) {
			for (int j = 1; j <= tlen; j++) {
				if (s[i - 1] == t[j - 1]) {
					d[i][j] = d[i - 1][j - 1];
				} else {
					int insert = d[i][j - 1] + 1;
					int del = d[i - 1][j] + 1;
					int update = d[i - 1][j - 1] + 1;
					d[i][j] = Math.min(insert, del) > Math.min(del, update) ? Math
							.min(del, update) : Math.min(insert, del);
				}
			}
		}
		return d[slen][tlen];
	}

	// 动态规划法
	private int EditDistance(String source, String target, char[] s, char[] t,
			int slen, int tlen) {
		int d[][] = new int[slen + 1][tlen + 1];
		for (int i = 0; i <= slen; i++) {
			d[i][0] = i;
		}
		for (int i = 0; i <= tlen; i++) {
			d[0][i] = i;
		}
		for (int i = 1; i <= slen; i++) {
			for (int j = 1; j <= tlen; j++) {
				if (s[i - 1] == t[j - 1]) {
					d[i][j] = d[i - 1][j - 1];
				} else {
					int insert = d[i][j - 1] + 1;
					int del = d[i - 1][j] + 1;
					int update = d[i - 1][j - 1] + 1;
					d[i][j] = Math.min(insert, del) > Math.min(del, update) ? Math
							.min(del, update) : Math.min(insert, del);
				}
			}
		}
		return d[slen][tlen];
	}

	// 递归实现 --- 穷举法（枚举法）
	private int EditDistanceChange(String source, String target) {
		if (target.length() != 0 && source.length() == 0) {
			return EditDistanceChange(source, target.substring(1)) + 1;
		} else if (target.length() == 0 && source.length() != 0) {
			return EditDistanceChange(source.substring(1), target) + 1;
		} else if (target.length() != 0 && source.length() != 0) {
			// 当源字符第一个值和目标字符第一个值相同时
			if (source.charAt(0) == target.charAt(0)) {
				return EditDistanceChange(source.substring(1),
						target.substring(1));
			} else {
				int insert = EditDistanceChange(source.substring(1), target) + 1;
				int del = EditDistanceChange(source, target.substring(1)) + 1;
				int update = EditDistanceChange(source.substring(1),
						target.substring(1)) + 1;
				return Math.min(insert, del) > Math.min(del, update) ? Math
						.min(del, update) : Math.min(insert, del);
			}
		} else {
			return 0;
		}
	}
}
