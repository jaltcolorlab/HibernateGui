/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.altcolorlab.simplehibernategui;

/**
 *
 * @author justink
 */
import java.net.URL;
import java.util.List; 
import java.util.Date;
import java.util.Iterator; 
import org.hibernate.SessionFactory;
//import com.altcolorlab.simplehibernategui.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class ManageEmployee {
  // public static SessionFactory factory;

    public ManageEmployee(){ 
        
     }
     
      
     public int addEmployee(String fname, String lname, int salary){    
      SessionFactory sf = HibernateUtil.getSessionFactory();   
      Session session = sf.openSession();
      Transaction tx = null;
      Integer employeeID = null;
      try{
         tx = session.beginTransaction();
         Employee employee = new Employee(fname, lname, salary);
         employeeID = (Integer) session.save(employee); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
     return employeeID; 
   }

   /* Method to UPDATE salary for an employee */
   public void updateEmployee(Integer EmployeeID, int salary ){
      SessionFactory sf = HibernateUtil.getSessionFactory();   
      Session session = sf.openSession();
      Transaction tx = null;
      try{
         tx = session.beginTransaction();
         Employee employee = 
                    (Employee)session.get(Employee.class, EmployeeID); 
         employee.setSalary( salary );
		 session.update(employee); 
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
         session.close(); 
      }
   }
}
