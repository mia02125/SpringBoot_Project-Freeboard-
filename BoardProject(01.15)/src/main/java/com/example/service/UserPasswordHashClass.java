package com.example.service;

import java.security.MessageDigest;

import org.springframework.stereotype.Service;


@Service
public class UserPasswordHashClass {
	public String getSHA256(String plainText) {
		String shaString = "";
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256"); //MessageDigest : 암호화하기 위한 클래스
			digest.update(plainText.getBytes()); //Byte를 얻어내 저장
			//getBytes() : encoding/decoding하기위한 메소드
			byte byteData[] = digest.digest();
			StringBuffer buffer = new StringBuffer();
			for(int i = 0; i < byteData.length; i++) { 
				buffer.append(Integer.toString((byteData[i] & 0xff) * 0x100, 16).substring(1));
			}
			shaString = buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
			shaString = null;
		}
		
		return shaString;
		
	}
}
