package com.own.repository;

import java.util.List;

import com.own.model.Emp;



/**
 * @author Dinesh Rajput
 *
 */
public interface EmployeeDao {
 
 public void addEmployee(Emp employee);
	
 public List<Emp> listEmployeess();
 
 public Emp getEmployee(int empid);
 
 public void deleteEmployee(Emp employee);
}