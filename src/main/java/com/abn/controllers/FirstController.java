package com.abn.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "*")
@RestController
public class FirstController {
	@RequestMapping(value="/some", method=RequestMethod.GET)
	public String some() throws ParseException {
		JSONRead jsonRead = new JSONRead();
		jsonRead.readJSONFile("src/main/resources/states.json");
		return "some";
	}
	@RequestMapping(value="/something", method=RequestMethod.GET)
	public int something(@RequestParam("id") int id) {
		System.out.println(id);
		return id;
	}
}