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
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil{
    
    private static SessionFactory sessionFactory;
    
    static{
        try{
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
            standardServiceRegistryBuilder.applySettings(configuration.getProperties());
            ServiceRegistry serviceRegistry = standardServiceRegistryBuilder.build();
            setSessionFactory(configuration.buildSessionFactory(serviceRegistry));
        }
        catch(HibernateException hex){
            hex.printStackTrace();
        }
    }
    
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
    
    public static void setSessionFactory(SessionFactory sessionFactory){
        HibernateUtil.sessionFactory = sessionFactory;
    }
    
    public static void shutdown(){
        getSessionFactory().close();
    }
}