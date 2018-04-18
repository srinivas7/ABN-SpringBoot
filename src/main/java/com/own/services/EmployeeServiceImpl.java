package com.own.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.own.model.Emp;
import com.own.repository.EmployeeDao;


@Service("employeeService")
//@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {

 @Autowired
 private EmployeeDao employeeDao;
 
 @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
 public void addEmployee(Emp employee) {
  employeeDao.addEmployee(employee);
 }
 
 public List<Emp> listEmployeess() {
  return employeeDao.listEmployeess();
 }

 public Emp getEmployee(int empid) {
  return employeeDao.getEmployee(empid);
 }
 
 public void deleteEmployee(Emp employee) {
  employeeDao.deleteEmployee(employee);
 }

}