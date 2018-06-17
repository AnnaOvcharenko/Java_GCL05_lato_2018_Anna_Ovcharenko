package CRUD;

import model.Book;
import model.Reader;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.Iterator;
import java.util.List;

public class ReaderCRUD implements CRUD {

    Configuration configuration = new Configuration().configure();
    ServiceRegistryBuilder registry = new ServiceRegistryBuilder();

    @Override
    public int create(Object object) {
        registry.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = registry.buildServiceRegistry();
        Integer readerID=null;

        // builds a session factory from the service registry
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        // obtains the session
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        readerID=(Integer)session.save(object);

        session.getTransaction().commit();
        //session.close();
        return readerID;
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
        List reader=session.createQuery("FROM Reader").list();
        for (Iterator iterator = reader.iterator(); iterator.hasNext();){
            Reader readers = (Reader) iterator.next();
            System.out.print("id: " + readers.getReader_id());
            System.out.print(" : " + readers.getFirst_name());
            System.out.print(" autor: " + readers.getSecond_name()+"\n");

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


        Reader reader = (Reader) session.get(Reader.class, id);
        reader.setSecond_name(newData);
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



        Reader reader =(Reader)session.get(Reader.class,id);
        session.delete(reader);
        session.getTransaction().commit();
        //session.close();


    }
}


