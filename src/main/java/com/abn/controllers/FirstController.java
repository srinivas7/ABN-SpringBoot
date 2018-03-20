package com.abn.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.abn.pojo.Album;
import com.abn.pojo.Albums;
import com.abn.pojo.AlbumsAlbum;
import com.abn.pojo.Image;
import com.abn.pojo.Images;
import com.abn.services.StorageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.abn.controllers.MultiPartToBase64;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class FirstController {
	
	AtomicInteger seq = new AtomicInteger();
	private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);
	@Autowired
	StorageService storageService;
	List<String> files = new ArrayList<String>();
	
	@RequestMapping(value="/singleAlbum/{id}", method=RequestMethod.GET)
	public ResponseEntity<HashMap<String, ArrayList<Image>>> getSAData(@PathVariable("id") int alubmId, HttpServletRequest request) throws ParseException {
		
		JSONRead jsonRead = new JSONRead();
		HashMap<String, ArrayList<Image>> hmap = new HashMap<String, ArrayList<Image>>();
		ArrayList<Image> images;
		images = jsonRead.readJSONFile("src/main/resources/singleAlbum.json");
		hmap.put("images", images);
		int someId = ID_GENERATOR.getAndIncrement();
		request.setAttribute("id", someId);
		System.out.println("session obj is"+ request.getAttribute("id"));
		
		
		int nextVal = seq.incrementAndGet();
		
		System.out.println("auto generated value is.."+ someId);
		return new ResponseEntity<HashMap<String, ArrayList<Image>>>(hmap,HttpStatus.OK);
		
	}
	@RequestMapping(value="/something", method=RequestMethod.GET)
	public int something(@RequestParam("id") int id) {
		System.out.println(id);
		return id;
	}
	
	@RequestMapping(value="/imageUpload", method=RequestMethod.POST)
	public ResponseEntity<String> imageUpload(@RequestParam("file") MultipartFile file, @RequestParam("data") Object data) {
		System.out.println("image upload"+ file);
		System.out.println("image upload payload data is.."+ data);
		System.out.println("image upload to string .."+ file.toString());
		MultiPartToBase64 mpb = new MultiPartToBase64();
		String message = "";
		try {
			//storageService.store(file);
			//files.add(file.getOriginalFilename());
			mpb.convertToBase64(file);
 
			message = "You successfully uploaded " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
			System.out.println("something went wrong.."+e );
			message = "FAIL to upload " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}
	
	@RequestMapping(value="/newAlbum", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createAlbum(@RequestBody Album newAlbum) throws IOException {
		JSONObject obj = new JSONObject();
		
		//creating new album
		JSONArray image = new JSONArray();
		ObjectMapper mapper = new ObjectMapper();
		JSONObject imageObj = new JSONObject();
		int albumId;
		System.out.println("newAlbum info.."+newAlbum.toString());

		//creating a new json file
		obj.put("ownerId",newAlbum.getOwnerId());
		obj.put("thumbnailEncryption",newAlbum.getThumbnailEncryption());
		obj.put("id",newAlbum.getId());
		obj.put("albumName",newAlbum.getAlbumName());
		
		ArrayList imagesAry = new ArrayList();
		ArrayList<Image> images = newAlbum.getImages();
		ListIterator<Image> li = images.listIterator();
		

		
		while(li.hasNext()) {
			System.out.println("next element is..."+li.next());
			
			Image img = (Image) li.next();
			imageObj.put("ownerAccountId",img.getOwnerAccountId());
			imageObj.put("url",img.getUrl());
			imageObj.put("dateTaken",img.getDateTaken());
			imageObj.put("id",img.getId());
			
			imagesAry.add(imageObj);
		}
		obj.put("images", imagesAry);
 		albumId = ID_GENERATOR.getAndIncrement();
 		System.out.println("album id..."+albumId);
		try (FileWriter file = new FileWriter("src/main/resources/"+albumId+".json")) {
			file.write(obj.toJSONString());
			System.out.println("Successfully created JSON Object to File..."+ obj.toJSONString());
		}
		
		
		// updating to albums.json
		
		//updating albums.json with new album name
		ObjectMapper albumsJson  =   new ObjectMapper();
		
		System.out.println("before");
		Albums albums = albumsJson.readValue(new FileReader("src/main/resources/albums.json"), Albums.class);
		
		System.out.println("aaa");
		JSONObject albumsJsonObj = new JSONObject();
		JSONObject albumsSingleAlbumObj = new JSONObject();
		ArrayList<AlbumsAlbum> list = albums.getCollectionList();
		AlbumsAlbum aa = new AlbumsAlbum();
		aa.setAlbumName(newAlbum.getAlbumName());
		aa.setAssetId(newAlbum.getId());
		aa.setId(newAlbum.getId());
		aa.setThumbnailEncryption(newAlbum.getThumbnailEncryption());
		
		list.add(aa);
		
		Gson gson = new Gson();
		String listData = gson.toJson(list);
		System.out.println("the data is.."+listData);
		
		albumsSingleAlbumObj.put("ownerId", newAlbum.getOwnerId());
		albumsSingleAlbumObj.put("thumbnailEncryption", newAlbum.getThumbnailEncryption());
		albumsSingleAlbumObj.put("id", newAlbum.getId());
		albumsSingleAlbumObj.put("albumName", newAlbum.getAlbumName());
		System.out.println("sfasdfas..."+albumsSingleAlbumObj);
		//list.add(albumsSingleAlbumObj);
		System.out.println(aa);
		albumsJsonObj.put("collectionList",listData);
		try (FileWriter file = new FileWriter("src/main/resources/albums.json")) {
			file.write(albumsJsonObj.toJSONString());
			System.out.println("Successfully Copied to albums JSON File...");
		}
		System.out.println("fasd");
	}
}
