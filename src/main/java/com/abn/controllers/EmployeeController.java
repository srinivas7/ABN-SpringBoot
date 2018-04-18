package com.abn.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.own.model.Emp;
import com.own.services.EmployeeService;

@Controller
public class EmployeeController {
 
  @Autowired
  private EmployeeService employeeService;
 
  @RequestMapping(value = "/test", method = RequestMethod.GET)
  public String test() {
	  return "test";
  }

 
}