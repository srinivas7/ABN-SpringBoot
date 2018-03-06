package com.abn.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "*")
@RestController
public class FirstController {
	@RequestMapping(value="/some", method=RequestMethod.GET)
	public String some() {
		return "some";
	}
	@RequestMapping(value="/something", method=RequestMethod.GET)
	public int something(@RequestParam("id") int id) {
		System.out.println(id);
		return id;
	}
}