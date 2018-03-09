package com.abn.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.abn.pojo.Image;
import com.abn.pojo.Images;

import java.util.HashMap;

import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "*")
@RestController
public class FirstController {
	@RequestMapping(value="/some", method=RequestMethod.GET)
	public HashMap<Long, Image> some() throws ParseException {
		JSONRead jsonRead = new JSONRead();
		HashMap<Long, Image> hmap;
		hmap = jsonRead.readJSONFile("src/main/resources/singleAlbum.json");
		System.out.println("images object is..."+hmap);
		return hmap;
	}
	@RequestMapping(value="/something", method=RequestMethod.GET)
	public int something(@RequestParam("id") int id) {
		System.out.println(id);
		return id;
	}
}