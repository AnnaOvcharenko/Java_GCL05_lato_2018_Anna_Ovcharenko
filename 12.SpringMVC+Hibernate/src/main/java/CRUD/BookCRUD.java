package CRUD;

import model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.Iterator;
import java.util.List;

public class BookCRUD implements CRUD {

    Configuration configuration = new Configuration().configure();
    ServiceRegistryBuilder registry = new ServiceRegistryBuilder();

    @Override
    public int create(Object object) {
        registry.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = registry.buildServiceRegistry();
        Integer bookID=null;

        // builds a session factory from the service registry
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        // obtains the session
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        bookID=(Integer)session.save(object);

        session.getTransaction().commit();
        //session.close();
        return bookID;
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
            List book=session.createQuery("FROM Book").list();
            for (Iterator iterator = book.iterator(); iterator.hasNext();){
                Book books = (Book) iterator.next();
                System.out.print("id: " + books.getBook_id());
                System.out.print(" title: " + books.getTitle());
                System.out.print(" autor: " + books.getAutor()+"\n");
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


        Book book = (Book) session.get(Book.class, id);
        book.setTitle(newData);
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



       Book book =(Book)session.get(Book.class,id);
        session.delete(book);
        session.getTransaction().commit();
        //session.close();


    }
}
