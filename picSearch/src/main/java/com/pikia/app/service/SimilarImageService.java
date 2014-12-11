package com.pikia.app.service;

public interface SimilarImageService {
	
	// 生成图片指纹
	public String produceFingerPrint(String filename);
	
	// 计算字符串汉明距离
	public int hammingDistance(String sourceHashCode, String hashCode);
	
	// 字符串相似度
	public float similarityRate(String source, String target);

	// 字符串的最小编辑距离
	public int EditDistance(String source, String target);

}
