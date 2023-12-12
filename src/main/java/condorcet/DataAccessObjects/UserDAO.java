package condorcet.DataAccessObjects;
import java.util.List;

import condorcet.Interfaces.DAO;
import condorcet.Models.Entities.User;
import condorcet.Utility.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class UserDAO implements DAO {
    @Override
    public User findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    @Override
    public void saveEntity(Object entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        User user = (User) entity;
        Transaction tx1 = session.beginTransaction();
        session.persist(user);
         tx1.commit();
        session.close();
    }

    @Override
    public List<User> findAll() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        List<User> users = (List<User>)session.createQuery("from User", User.class).list();
        session.close();
        return users;
    }

    @Override
    public void deleteEntity(Object entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        User user = (User) entity;
        Transaction tr1 = session.beginTransaction();
        session.delete(user);
        tr1.commit();
        session.close();

    }

    @Override
    public void updateEntity(Object entity) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        User user = (User) entity;
        Transaction tr1 = session.beginTransaction();
        session.update(user);
        tr1.commit();

        session.close();
    }
}

