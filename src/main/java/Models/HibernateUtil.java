package Models;

import Entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateUtil {
    private static SessionFactory factory;

    private static SessionFactory buildFactory(){
        try{
            Configuration config = new Configuration();
            config.addAnnotatedClass(Employee.class);
            config.addAnnotatedClass(Product.class);
            config.addAnnotatedClass(Customer.class);
            config.addAnnotatedClass(Order.class);
            config.addAnnotatedClass(Cart.class);
            config.configure("hibernate.cfg.xml");
            System.out.println("Hibernate Annotation config loaded");

            ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
            System.out.println("Hibernate Annotation service Registry created");

            return config.buildSessionFactory(registry);
        } catch (Throwable ex){
            System.err.println("Initial Session Factory creation failed." + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    static SessionFactory getFactory(){
        if(factory == null)
            factory = buildFactory();
        return factory;
    }
}
