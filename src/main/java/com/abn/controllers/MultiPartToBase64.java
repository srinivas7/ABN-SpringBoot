package com.abn.controllers;

import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Encoder;

import org.springframework.web.multipart.MultipartFile;

public class MultiPartToBase64 {
	String img;
	
	public String convertToBase64(MultipartFile file) {
		String base64Prefix = "data:image/png;base64,";
		StringBuilder sb = new StringBuilder();
		sb.append("data:image/png;base64,");
		Encoder en = Base64.getEncoder();
		
		try {
			img = en.encodeToString(file.getBytes());
			img = sb+img;
			//System.out.println("image url is.."+ img);
		} catch (IOException e) {
			System.out.println("can't convert to image Base64 URL");
			e.printStackTrace();
		}
		return img;
	}
}
