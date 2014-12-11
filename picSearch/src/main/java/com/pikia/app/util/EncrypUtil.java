package com.pikia.app.util;

import java.security.Key;
import java.security.MessageDigest;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * AES DES MD5 加密解密工具包
 * 
 * @author methew
 * 
 */
public class EncrypUtil {

	/**
	 * AES 加密算法
	 * 
	 * @param strKey
	 * @param strIn
	 * @return
	 * @throws Exception
	 */
	public static String AESencrypt(String strKey, String strIn)
			throws Exception {
		SecretKeySpec skeySpec = AESgetKey(strKey);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(strIn.getBytes());

		return new BASE64Encoder().encode(encrypted);
	}

	/**
	 * AES 解密算法
	 * 
	 * @param strKey
	 * @param strIn
	 * @return
	 * @throws Exception
	 */
	public static String AESdecrypt(String strKey, String strIn)
			throws Exception {
		SecretKeySpec skeySpec = AESgetKey(strKey);
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
		byte[] encrypted1 = new BASE64Decoder().decodeBuffer(strIn);

		byte[] original = cipher.doFinal(encrypted1);
		String originalString = new String(original);
		return originalString;
	}

	private static SecretKeySpec AESgetKey(String strKey) throws Exception {
		byte[] arrBTmp = strKey.getBytes();
		byte[] arrB = new byte[16]; // 创建一个空的16位字节数组（默认值为0）

		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}

		SecretKeySpec skeySpec = new SecretKeySpec(arrB, "AES");

		return skeySpec;
	}

	/**
	 * DES 加密算法
	 * 
	 * @param strKey
	 * @param strIn
	 * @return
	 * @throws Exception
	 */
	public static String DESencrypt(String strKey, String strIn)
			throws Exception {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		Key key = getKey(strKey);

		Cipher encryptCipher = Cipher.getInstance("DES");
		encryptCipher.init(Cipher.ENCRYPT_MODE, key);

		return byteArr2HexStr(encryptCipher.doFinal((strIn.getBytes())));
	}

	/**
	 * DES 解密算法
	 * 
	 * @param strKey
	 * @param strIn
	 * @return
	 * @throws Exception
	 */
	public static String DESdecrypt(String strKey, String strIn)
			throws Exception {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		Key key = getKey(strKey);

		Cipher decryptCipher = Cipher.getInstance("DES");
		decryptCipher.init(Cipher.DECRYPT_MODE, key);
		return new String(decryptCipher.doFinal((hexStr2ByteArr(strIn))));
	}

	private static Key getKey(String strKey) throws Exception {

		byte[] arrBTmp = strKey.getBytes(); // 获取字节流
		byte[] arrB = new byte[8]; // 创建一个空的8位字节数组（默认值为0）

		// 将原始字节数组转换为8位
		for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
			arrB[i] = arrBTmp[i];
		}

		// 生成密钥
		Key key = new javax.crypto.spec.SecretKeySpec(arrB, "DES");

		return key;
	}

	private static String byteArr2HexStr(byte[] arrB) throws Exception {
		int iLen = arrB.length;
		// 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
		StringBuffer sb = new StringBuffer(iLen * 2);
		for (int i = 0; i < iLen; i++) {
			int intTmp = arrB[i];
			// 把负数转换为正数
			while (intTmp < 0) {
				intTmp = intTmp + 256;
			}
			// 小于0F的数需要在前面补0
			if (intTmp < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(intTmp, 16));
		}
		return sb.toString();
	}

	private static byte[] hexStr2ByteArr(String strIn) throws Exception {
		byte[] arrB = strIn.getBytes();
		int iLen = arrB.length;

		// 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
		byte[] arrOut = new byte[iLen / 2];
		for (int i = 0; i < iLen; i = i + 2) {
			String strTmp = new String(arrB, i, 2);
			arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return arrOut;
	}

	/**
	 * MD5 加密算法
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static String getMD5Str(String strIn) throws Exception {
		MessageDigest messageDigest = null;
		messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.reset();
		messageDigest.update(strIn.getBytes("UTF-8"));
		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			} else {
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
		}

		return md5StrBuff.toString();
	}

	public static void main(String[] args) throws Exception {
		String Code = "中文ABc123";
		String key = "1q2w3e4r";
		String codE;
		System.out.println("**********AES**************");
		codE = EncrypUtil.AESencrypt(key, Code);
		System.out.println("原文：" + Code);
		System.out.println("密钥：" + key);
		System.out.println("密文：" + codE);
		System.out.println("解密：" + EncrypUtil.AESdecrypt(key, codE));

		System.out.println("**********MD5**************");
		System.out.println(EncrypUtil.getMD5Str("666666"));
		System.out.println("**********DES***************");
		try {
			Code = "中B123";
			key = "1q2w3e4r";
			codE = EncrypUtil.DESencrypt(key, Code);

			System.out.println("加密前的字串：" + Code);
			System.out.println("加密所用密钥：" + key);
			System.out.println("加密后的字串：" + codE);
			System.out.println("解密后的字串：" + EncrypUtil.DESdecrypt(key, codE));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
