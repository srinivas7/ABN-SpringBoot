package com.abn.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;
import java.util.Base64;

@Service
public class StorageService {
	 
	private final Path rootLocation = Paths.get("C:\\Users\\Srinivas\\git\\images");
 
	public void store(MultipartFile file) {
		try {
			System.out.println(file.getInputStream());
			Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
			//convertToBase64(file);
		} catch (Exception e) {
			throw new RuntimeException("FAIL!");
		}
	}
	
	public String convertToBase64(MultipartFile file) throws IOException {
		System.out.println("bytes array is..."+file.getBytes());
		return "";
	}
	
 
	public Resource loadFile(String filename) {
		try {
			Path file = rootLocation.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
	}
 
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}
 
	public void init() {
		try {
			Files.createDirectory(rootLocation);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage!");
		}
	}
}