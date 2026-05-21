package com.PMS.model.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/*
    FactoryProvider is a Hibernate utility class used to create,
    provide, and manage a single SessionFactory instance
    throughout the application.

    It reads configuration from hibernate.cfg.xml and helps
    establish database connectivity efficiently using Hibernate ORM.

    Using a single SessionFactory improves performance and
    follows the Singleton design principle.
*/

public class FactoryProvider {
    private static SessionFactory factory;

    public static SessionFactory getFactory(){
        if(factory == null){
            factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        }

        return factory;
    }

    public static void closeFactory(){
        if(factory.isOpen()){
            factory.close();
        }
    }
}