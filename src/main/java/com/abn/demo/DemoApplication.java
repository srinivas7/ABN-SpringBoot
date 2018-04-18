package com.abn.demo;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.hibernate.cfg.AnnotationConfiguration;

import com.abn.pojo.Emp;

@SuppressWarnings("deprecation")
@SpringBootApplication
@ComponentScan("com.abn")
public class DemoApplication {
	
	private static SessionFactory factory; 
	
	public static void main(String[] args) {
		System.out.println("fasdfadsf");
		SpringApplication.run(DemoApplication.class, args);
		
		try {
//	         factory = new AnnotationConfiguration().
//	                   configure().
//	                   //addPackage("com.xyz") //add package if used.
//	                   addAnnotatedClass(Emp.class).
//	                   buildSessionFactory();
	      } catch (Throwable ex) { 
	         System.err.println("Failed to create sessionFactory object." + ex);
	         throw new ExceptionInInitializerError(ex); 
	      }
	      
		DemoApplication ME = new DemoApplication();

	      /* Add few employee records in database */
//	      Integer empID1 = ME.addEmployee("Zara", "Ali", 1000);
//	      Integer empID2 = ME.addEmployee("Daisy", "Das", 5000);
//	      Integer empID3 = ME.addEmployee("John", "Paul", 10000);
	    
		
	}
	
//	 public Integer addEmployee(String fname, String lname, int salary){
//	      Session session = factory.openSession();
//	      Transaction tx = null;
//	      Integer employeeID = null;
//	      
//	      try {
//	         tx = session.beginTransaction();
//	         Emp employee = new Emp();
//	         employee.setId(121);
//	         employee.setName("asdf");
//	         employeeID = (Integer) session.save(employee); 
//	         tx.commit();
//	      } catch (HibernateException e) {
//	         if (tx!=null) tx.rollback();
//	         e.printStackTrace(); 
//	      } finally {
//	         session.close(); 
//	      }
//	      return employeeID;
//	   }
}
