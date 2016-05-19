/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.altcolorlab.simplehibernategui;

import static com.sun.glass.ui.Cursor.setVisible;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


/**
 * FXML Controller class
 *
 * @author justink
 */
public class FXMLController implements Initializable {

        @FXML private TextField tfFirstName;
        @FXML private TextField tfLastName;
        @FXML private TextField tfSalary;
        @FXML private  TableView<Employee> tableview;
        @FXML private  TableColumn<Employee, Integer> ID;
        @FXML private  TableColumn<Employee, String> fNameCol;
        @FXML private  TableColumn<Employee, String> lNameCol;
        @FXML private  TableColumn<Employee, Integer> salaryCol;
        @FXML public   ObservableList<Employee> data;
      //Population of the data table with database information
      @Override
      public void initialize(URL location, ResourceBundle resources){

      //Setting up each column and then population the column with the desired data
      ID.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
      fNameCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("firstName"));
      lNameCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("lastName"));
      salaryCol.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("salary"));
      //instantiating an arraylist      
      data = FXCollections.observableArrayList();
      //getting the initial sessionFactory from HibernateUtil Class
      SessionFactory sf = HibernateUtil.getSessionFactory();
      //opening the session
      Session session = sf.openSession();
      //setting transaction to null
      Transaction tx = null;
      try{
         //starting the transaction 
         tx = session.beginTransaction();
         //creating a list and querying from the POJO
         List employees = session.createQuery("FROM Employee").list();
         //iterating over the database
         for (Iterator iterator = employees.iterator(); iterator.hasNext();){
            
            Employee employee = (Employee) iterator.next();
            data.add(employee);
         }
         tx.commit();
      }catch (HibernateException e) {
         if (tx!=null) tx.rollback();
         e.printStackTrace(); 
      }finally {
          //closing session
         session.close();
         //adding the data to the table
         tableview.setItems(data);
      }
        }
        
        @FXML
         private void handleButtonAction(){
             String firstName=tfFirstName.getText();
             String lastName=tfLastName.getText();
             Integer money=Integer.parseInt(tfSalary.getText());
             Integer employeeId;
             ManageEmployee emp1 = new ManageEmployee();
             employeeId=emp1.addEmployee(firstName, lastName, money);
              //Setting up each column and then population the column with the desired data
              ID.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
              fNameCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("firstName"));
              lNameCol.setCellValueFactory(new PropertyValueFactory<Employee,String>("lastName"));
              salaryCol.setCellValueFactory(new PropertyValueFactory<Employee,Integer>("salary"));
              //instantiating an arraylist      
              data = FXCollections.observableArrayList();
              //getting the initial sessionFactory from HibernateUtil Class
              SessionFactory sf = HibernateUtil.getSessionFactory();
               //opening the session
              Session session = sf.openSession();
              //setting transaction to null
              Transaction tx = null;
                try{
               //starting the transaction 
                tx = session.beginTransaction();
               //creating a list and querying from the POJO
                List employees = session.createQuery("FROM Employee").list();
              //iterating over the database
                for (Iterator iterator = employees.iterator(); iterator.hasNext();){
            
                     Employee employee = (Employee) iterator.next();
                     data.add(employee);
                }
         tx.commit();
              }catch (HibernateException e) {
                if (tx!=null) tx.rollback();
                    e.printStackTrace(); 
             }finally {
          //closing session
              session.close();
         //adding the data to the table
             tableview.setItems(data);
         //clears text fields    
             tfFirstName.clear();
             tfLastName.clear();
             tfSalary.clear();
             }
         }
         
      
        
    
}
