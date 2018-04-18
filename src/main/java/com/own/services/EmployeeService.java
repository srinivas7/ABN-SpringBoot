package com.own.services;

import java.util.List;

import com.own.model.Emp;


public interface EmployeeService {
 
 public void addEmployee(Emp employee);

 public List<Emp> listEmployeess();
 
 public Emp getEmployee(int empid);
 
 public void deleteEmployee(Emp employee);
}