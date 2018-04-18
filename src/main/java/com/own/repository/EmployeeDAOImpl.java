package com.own.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.own.model.Emp;


@Repository("employeeDao")
public class EmployeeDAOImpl implements EmployeeDao {

 @Autowired
 private SessionFactory sessionFactory;
 
 public void addEmployee(Emp employee) {
   sessionFactory.getCurrentSession().saveOrUpdate(employee);
 }

 @SuppressWarnings("unchecked")
 public List<Emp> listEmployeess() {
  return (List<Emp>) sessionFactory.getCurrentSession().createCriteria(Emp.class).list();
 }

 public Emp getEmployee(int empid) {
  return (Emp) sessionFactory.getCurrentSession().get(Emp.class, empid);
 }

 public void deleteEmployee(Emp employee) {
  sessionFactory.getCurrentSession().createQuery("DELETE FROM Employee WHERE empid = "+employee.getId()).executeUpdate();
 }
}