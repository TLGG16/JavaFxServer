package condorcet.DataAccessObjects;
import java.util.List;

import condorcet.Interfaces.DAO;
import condorcet.Models.Entities.Person;
import condorcet.Models.Entities.User;
import condorcet.Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class PersonDAO implements DAO {
    @Override
    public Person findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Person user = session.get(Person.class, id);
        session.close();
        return user;
    }

    @Override
    public void saveEntity(Object entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Person person = (Person) entity;
        Transaction tx1 = session.beginTransaction();
        session.save(person);
        tx1.commit();
        session.close();
    }

    @Override
    public List<Person> findAll() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        List<Person> persons = (List<Person>)session.createQuery("from Person", Person.class).list();
        session.close();
        return persons;
    }
    @Override
    public void deleteEntity(Object entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Person person = (Person) entity;
        Transaction tr1 = session.beginTransaction();
        session.delete(person);
        tr1.commit();
        session.close();
    }

    @Override
    public void updateEntity(Object entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Person person = (Person) entity;
        Transaction tr1 = session.beginTransaction();
        session.update(person);
        tr1.commit();
        session.close();
    }
}