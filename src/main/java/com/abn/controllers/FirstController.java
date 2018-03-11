package com.abn.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.abn.pojo.Album;
import com.abn.pojo.Albums;
import com.abn.pojo.Image;
import com.abn.pojo.Images;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "*")
@RestController
public class FirstController {
	
	AtomicInteger seq = new AtomicInteger();
	private static AtomicInteger ID_GENERATOR = new AtomicInteger(1000);
	
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
	
	@RequestMapping(value="/newAlbum", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createAlbum(@RequestBody Album newAlbum) throws IOException {
		JSONObject obj = new JSONObject();
		
		//creating new album
		JSONArray image = new JSONArray();
		ObjectMapper mapper = new ObjectMapper();
		JSONObject imageObj = new JSONObject();
		int albumId;
		System.out.println("newAlbum info.."+newAlbum.getAlbumName());

		//creating a new json file
		obj.put("ownerId",newAlbum.getOwnerId());
		obj.put("thumbnailEncryption",newAlbum.getThumbnailEncryption());
		obj.put("id",newAlbum.getId());
		obj.put("albumName",newAlbum.getAlbumName());
		
		ArrayList imagesAry = new ArrayList();
		ArrayList images = newAlbum.getImages();
		ListIterator li = images.listIterator();
		while(li.hasNext()) {
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
		Albums albums = albumsJson.readValue(new FileReader("src/main/resources/albums.json"), Albums.class);
		JSONObject albumsJsonObj = new JSONObject();
		JSONObject albumsSingleAlbumObj = new JSONObject();
		ArrayList list = albums.getCollectionList();
		//list.add(newAlbum);
		albumsSingleAlbumObj.put("ownerId", newAlbum.getOwnerId());
		albumsSingleAlbumObj.put("thumbnailEncryption", newAlbum.getThumbnailEncryption());
		albumsSingleAlbumObj.put("id", newAlbum.getId());
		albumsSingleAlbumObj.put("albumName", newAlbum.getAlbumName());
		
		list.add(albumsSingleAlbumObj);
		albumsJsonObj.put("collectionList",list);
		try (FileWriter file = new FileWriter("src/main/resources/albums.json")) {
			file.write(albumsJsonObj.toJSONString());
			System.out.println("Successfully Copied to albums JSON File...");
		}
		System.out.println("fasd");
	}
}
