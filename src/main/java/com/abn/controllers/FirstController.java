package com.abn.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.abn.pojo.Image;
import com.abn.pojo.Images;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "*")
@RestController
public class FirstController {
	@RequestMapping(value="/singleAlbum", method=RequestMethod.GET)
	public HashMap<String, ArrayList<Image>> some() throws ParseException {
		JSONRead jsonRead = new JSONRead();
		HashMap<String, ArrayList<Image>> hmap = new HashMap<String, ArrayList<Image>>();
		ArrayList<Image> images;
		images = jsonRead.readJSONFile("src/main/resources/singleAlbum.json");
		hmap.put("images", images);
		System.out.println("images object is..."+hmap);
		return hmap;
	}
	@RequestMapping(value="/something", method=RequestMethod.GET)
	public int something(@RequestParam("id") int id) {
		System.out.println(id);
		return id;
	}
}