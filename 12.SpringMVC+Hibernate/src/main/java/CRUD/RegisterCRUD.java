package CRUD;


import model.Register;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.Iterator;
import java.util.List;

public class RegisterCRUD implements CRUD {

    Configuration configuration = new Configuration().configure();
    ServiceRegistryBuilder registry = new ServiceRegistryBuilder();

    @Override
    public int create(Object object) {
        registry.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = registry.buildServiceRegistry();
        Integer registerID=null;

        // builds a session factory from the service registry
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        // obtains the session
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        registerID=(Integer)session.save(object);

        session.getTransaction().commit();
        //session.close();
        return registerID;
    }

    @Override
    public void read() {
        registry.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = registry.buildServiceRegistry();

        // builds a session factory from the service registry
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        // obtains the session
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List register=session.createQuery("FROM Register").list();
        for (Iterator iterator = register.iterator(); iterator.hasNext();){
            Register registers = (Register) iterator.next();
            System.out.print("id: " + registers.getRegister_id());
            System.out.print(" reader_id: " + registers.getReader_id());
            System.out.print(" book_id: " + registers.getBook_id()+"\n");

        }


    }

    @Override
    public void update(int id, String newData) {
        registry.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = registry.buildServiceRegistry();

        // builds a session factory from the service registry
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        // obtains the session
        Session session = sessionFactory.openSession();
        session.beginTransaction();


        Register register = (Register) session.get(Register.class, id);
        register.setBook_id(Integer.parseInt(newData));
        session.getTransaction().commit();
        // session.close();

    }

    @Override
    public void delete(int id) {
        registry.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = registry.buildServiceRegistry();

        // builds a session factory from the service registry
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        // obtains the session
        Session session = sessionFactory.openSession();
        session.beginTransaction();



        Register register =(Register)session.get(Register.class,id);
        session.delete(register);
        session.getTransaction().commit();
        //session.close();


    }
}


