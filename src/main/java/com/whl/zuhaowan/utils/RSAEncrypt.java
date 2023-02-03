package com.whl.zuhaowan.utils;


import com.whl.zuhaowan.contants.Constant;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class RSAEncrypt {
	public static void main(String[] args) throws Exception {
		Map<Integer, String> keyMap = genKeyPair();
		String message = "heheheh";
		String pulbicKey = keyMap.get(0);
		String privateKey = keyMap.get(1);
		/*System.out.println("随机生成的公钥为:" + pulbicKey);
		System.out.println("============================================");
		System.out.println("随机生成的私钥为:" + privateKey);
		System.out.println("============================================");
		String messageEn = encrypt(message, pulbicKey);
		System.out.println("加密后的字符串为:" + messageEn);
		System.out.println("============================================");
		String messageDe = decrypt(messageEn, privateKey);
		System.out.println("还原后的字符串为:" + messageDe);*/
		String password = "123@qwe";
		//String keyPassword2 = encrypt(password,pulbicKey);
		String keyPassword = encrypt(password, Constant.RSA_PUBLIC);
		System.out.println("keyPassword:"+keyPassword);
		System.out.printf("password:"+decrypt(keyPassword,Constant.RSA_PRIVATE));

	}
	/**
	 * 随机生成密钥对
	 */
	public static Map<Integer, String> genKeyPair()
			throws NoSuchAlgorithmException {
		// KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
		Map<Integer, String> keyMap = new HashMap<Integer, String>();
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		// 初始化密钥对生成器，密钥大小为96-1024位
		keyPairGen.initialize(512, new SecureRandom());
		// 生成一个密钥对，保存在keyPair中
		KeyPair keyPair = keyPairGen.generateKeyPair();
		// 得到私钥
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate(); 
		// 得到公钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic(); 
		String publicKeyString = (Base64.getEncoder()).encodeToString(publicKey.getEncoded());
		// 得到私钥字符串
		String privateKeyString = (Base64.getEncoder()).encodeToString((privateKey.getEncoded()));
		/*
		 *  将公钥和私钥保存到Map
		 *  0表示公钥
		 *  1表示私钥
		 */
		keyMap.put(0, publicKeyString); 
		keyMap.put(1, privateKeyString); 
		return keyMap;
	}

	/**
	 * RSA公钥加密
	 * @param str 加密字符串
	 * @param publicKey 公钥
	 * @return 密文
	 * @throws Exception 加密过程中的异常信息
	 */
	public static String encrypt(String str, String publicKey) throws Exception {
		// base64编码的公钥
		byte[] decoded = (Base64.getDecoder()).decode(publicKey);
		RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA")
				.generatePublic(new X509EncodedKeySpec(decoded));
		// RSA加密
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		String outStr = (Base64.getEncoder()).encodeToString(cipher.doFinal(str
				.getBytes("UTF-8")));
		return outStr;
	}

	/**
	 * RSA私钥解密
	 * @param str 加密字符串
	 * @param privateKey 私钥
	 * @return 铭文
	 * @throws Exception 解密过程中的异常信息
	 */
	public static String decrypt(String str, String privateKey)
			throws Exception {
		// 64位解码加密后的字符串
		byte[] inputByte = (Base64.getDecoder()).decode(str);
		// base64编码的私钥
		byte[] decoded = (Base64.getDecoder()).decode(privateKey);
		RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA")
				.generatePrivate(new PKCS8EncodedKeySpec(decoded));
		// RSA解密
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.DECRYPT_MODE, priKey);
		String outStr = new String(cipher.doFinal(inputByte));
		return outStr;
	}
}
